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

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import es.msalaguila.realtimechat.Data.RegisteredUser;
import es.msalaguila.realtimechat.NewMessage.NewMessageAdapter;
import es.msalaguila.realtimechat.R;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private List<RegisteredUser> usersLoaded;
  private final View.OnClickListener clickListener;

  public ChatAdapter(View.OnClickListener clickListener) {

    this.clickListener = clickListener;
  }

  public void addItem(RegisteredUser item) {
    usersLoaded.add(item);
    notifyDataSetChanged();
  }

  public void addItems(List<RegisteredUser> items) {
    usersLoaded.addAll(items);
    notifyDataSetChanged();
  }

  public void setItems(List<RegisteredUser> items) {
    usersLoaded = items;
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
    View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.to_chat_cell, parent, false);
    return new ChatAdapter.ToViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    // holder.itemView.setTag(usersLoaded.get(position));
    // holder.itemView.setOnClickListener(clickListener);

   /* holder.house_name.setText(houseList.get(position).apartmentName);
    holder.price.setText(houseList.get(position).price);
    holder.ref_number.setText(houseList.get(position).referenceNumber);*/
  }

  @Override
  public int getItemCount() {
    return 50;
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
  }

  class ToViewHolder extends RecyclerView.ViewHolder {
    final TextView textView;

    ToViewHolder(View view) {
      super(view);
      // house_name = view.findViewById(R.id.house_name);
      textView = view.findViewById(R.id.toChatTextView);
    }
  }
}