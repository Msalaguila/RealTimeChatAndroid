package es.msalaguila.realtimechat.Home;

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

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import es.msalaguila.realtimechat.Chat.ChatAdapter;
import es.msalaguila.realtimechat.Data.HomeMessage;
import es.msalaguila.realtimechat.Data.Message;
import es.msalaguila.realtimechat.R;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  public List<HomeMessage> chatMessages;
  private final View.OnClickListener clickListener;

  private static int NORMAL_TYPE = 1;

  public HomeAdapter(View.OnClickListener clickListener) {
    this.chatMessages = new ArrayList<>();
    this.clickListener = clickListener;
  }

  public void addMessage(HomeMessage item) {
    chatMessages.add(item);
    notifyDataSetChanged();
  }

  public void addMessages(List<HomeMessage> items) {
    chatMessages.addAll(items);
    notifyDataSetChanged();
  }

  public void setMessages(List<HomeMessage> items) {
    chatMessages = items;
    notifyDataSetChanged();
  }


  @Override
  public int getItemViewType(int position) {
    return NORMAL_TYPE;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
    View view;
    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_cell, parent, false);
    return new HomeAdapter.NormalViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    if (getItemViewType(position) == NORMAL_TYPE) {
      ((HomeAdapter.NormalViewHolder) holder).setToDetails(chatMessages.get(position));
    }
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

  class NormalViewHolder extends RecyclerView.ViewHolder {
    final TextView message;
    final CircleImageView profileImageView;
    final TextView name;
    final TextView dateText;

    NormalViewHolder(View view) {
      super(view);
      message = view.findViewById(R.id.messageTextViewHomeCell);
      profileImageView = view.findViewById(R.id.profileImageFromHomeCell);
      name = view.findViewById(R.id.nameTextViewHomeCell);
      dateText = view.findViewById(R.id.dateTextViewHomeCell);

    }

    private void setToDetails(HomeMessage homeMessage) {
      message.setText(homeMessage.getMessage());
      loadImageFromURL(profileImageView, homeMessage.getProfileImageURL());
      name.setText(homeMessage.getProfileName());

      Date date = new Date(homeMessage.getTimestamp()*1000);
      SimpleDateFormat format = new SimpleDateFormat("HH:mm");

      // String s = new SimpleDateFormat("HH:mm:ss").format(timestamp);
      dateText.setText(format.format(date));
    }
  }
}
