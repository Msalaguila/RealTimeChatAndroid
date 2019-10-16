package es.msalaguila.realtimechat.Chat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import es.msalaguila.realtimechat.Data.Message;
import es.msalaguila.realtimechat.Data.RegisteredUser;
import es.msalaguila.realtimechat.R;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  public List<Message> chatMessages;
  private final View.OnClickListener clickListener;
  private FirebaseAuth mAuth = FirebaseAuth.getInstance();
  String currentUserID = mAuth.getCurrentUser().getUid();

  private static int TYPE_FROM = 1;
  private static int TYPE_TO = 2;

  public ChatAdapter(View.OnClickListener clickListener) {
    this.chatMessages = new ArrayList<>();
    this.clickListener = clickListener;
  }

  public void addMessage(Message item) {
    chatMessages.add(item);
    notifyDataSetChanged();
  }

  public void addMessages(List<Message> items) {
    chatMessages.addAll(items);
    notifyDataSetChanged();
  }

  public void setMessages(List<Message> items) {
    chatMessages = items;
    notifyDataSetChanged();
  }


  @Override
  public int getItemViewType(int position) {
    Message currentMessage = chatMessages.get(position);
    if (currentMessage.getFromID().equals(currentUserID)) {
      return TYPE_TO;
    } else {
      return TYPE_FROM;
    }
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
    View view;
    if (position == TYPE_TO) { // for call layout
      // for call layout
      view = LayoutInflater.from(parent.getContext()).inflate(R.layout.to_chat_cell, parent, false);
      return new ToViewHolder(view);
    } else {
      view = LayoutInflater.from(parent.getContext()).inflate(R.layout.from_chat_cell, parent, false);
      return new FromViewHolder(view);
    }
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    if (getItemViewType(position) == TYPE_TO) {
      ((ToViewHolder) holder).setToDetails(chatMessages.get(position));
    } else {
      ((FromViewHolder) holder).setFromDetails(chatMessages.get(position));
    }

    // holder.itemView.setTag(usersLoaded.get(position));
    // holder.itemView.setOnClickListener(clickListener);
   /* Message currentMessage = chatMessages.get(position);
    ((ToViewHolder) holder).setToDetails(chatMessages.get(position));*/
    /*if (currentMessage.getFromID().equals(currentUserID)) {
      ((ToViewHolder) holder).setToDetails(chatMessages.get(position));
    } else {
      ((FromViewHolder) holder).setFromDetails(chatMessages.get(position));
    }*/
   /* holder.house_name.setText(houseList.get(position).apartmentName);
    holder.price.setText(houseList.get(position).price);
    holder.ref_number.setText(houseList.get(position).referenceNumber);*/
  }

  @Override
  public int getItemCount() {
    return chatMessages.size();
  }

  private void loadImageFromURL(ImageView imageView, String imageUrl){
    RequestManager reqManager = Glide.with(imageView.getContext());
    RequestBuilder reqBuilder = reqManager.load(imageUrl);
    RequestOptions reqOptions = new RequestOptions();
    reqOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
    reqBuilder.apply(reqOptions);
    reqBuilder.into(imageView);
  }

  class FromViewHolder extends RecyclerView.ViewHolder {
    final CircleImageView profileImage;
    final TextView textView;

    FromViewHolder(View view) {
      super(view);
      textView = view.findViewById(R.id.fromChatTextView);
      profileImage = view.findViewById(R.id.profileImageFromChat);
    }

    private void setFromDetails(Message message) {
      textView.setText(message.getMessage());
      loadImageFromURL(profileImage, message.getProfileImageURL());
    }
  }

  class ToViewHolder extends RecyclerView.ViewHolder {
    final TextView textView;

    ToViewHolder(View view) {
      super(view);
      textView = view.findViewById(R.id.toChatTextView);
    }

    private void setToDetails(Message message) {
      textView.setText(message.getMessage());
    }
  }
}