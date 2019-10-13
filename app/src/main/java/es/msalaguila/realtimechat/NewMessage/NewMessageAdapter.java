package es.msalaguila.realtimechat.NewMessage;

import android.net.Uri;
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
import es.msalaguila.realtimechat.R;

public class NewMessageAdapter extends RecyclerView.Adapter<NewMessageAdapter.ViewHolder> {

  private List<RegisteredUser> usersLoaded;
  private final View.OnClickListener clickListener;

  public NewMessageAdapter(View.OnClickListener clickListener) {

    this.usersLoaded = new ArrayList();
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
  public NewMessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
    View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.new_message_cell, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull NewMessageAdapter.ViewHolder holder, int position) {
    holder.itemView.setTag(usersLoaded.get(position));
    holder.itemView.setOnClickListener(clickListener);

   /* holder.house_name.setText(houseList.get(position).apartmentName);
    holder.price.setText(houseList.get(position).price);
    holder.ref_number.setText(houseList.get(position).referenceNumber);*/

   holder.profileName.setText(usersLoaded.get(position).getName());
   holder.profileEmail.setText(usersLoaded.get(position).getEmail());
   loadImageFromURL(holder.profileImage, usersLoaded.get(position).getProfileImageUrl());
  }

  @Override
  public int getItemCount() {
    return usersLoaded.size();
  }

  private void loadImageFromURL(ImageView imageView, String imageUrl){
    RequestManager reqManager = Glide.with(imageView.getContext());
    RequestBuilder reqBuilder = reqManager.load(imageUrl);
    RequestOptions reqOptions = new RequestOptions();
    reqOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
    reqBuilder.apply(reqOptions);
    reqBuilder.into(imageView);
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    final TextView profileName;
    final TextView profileEmail;
    final CircleImageView profileImage;

    ViewHolder(View view) {
      super(view);
      // house_name = view.findViewById(R.id.house_name);
      profileName = view.findViewById(R.id.nameTextViewNewMessage);
      profileEmail = view.findViewById(R.id.emailTextViewNewMessage);
      profileImage = view.findViewById(R.id.profilePhotoImageViewNewMessage);
    }
  }
}
