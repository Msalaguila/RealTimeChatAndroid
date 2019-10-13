package es.msalaguila.realtimechat.NewMessage;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import es.msalaguila.realtimechat.R;

public class NewMessageAdapter extends RecyclerView.Adapter<NewMessageAdapter.ViewHolder> {

  // private List<SimpleHouse> houseList;
  private final View.OnClickListener clickListener;

  public NewMessageAdapter(View.OnClickListener clickListener) {

    // this.houseList = new ArrayList();
    this.clickListener = clickListener;
  }

  /*public void addItem(SimpleHouse item) {
    houseList.add(item);
    notifyDataSetChanged();
  }

  public void addItems(List<SimpleHouse> items) {
    houseList.addAll(items);
    notifyDataSetChanged();
  }

  public void setItems(List<SimpleHouse> items) {
    houseList = items;
    notifyDataSetChanged();
  }*/




  @NonNull
  @Override
  public NewMessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
    View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.new_message_cell, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull NewMessageAdapter.ViewHolder viewHolder, int position) {

  }

  @Override
  public int getItemCount() {
    return 5;
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    final TextView profileName;
    final TextView profileEmail;

    ViewHolder(View view) {
      super(view);
      // house_name = view.findViewById(R.id.house_name);
      profileName = view.findViewById(R.id.nameTextViewNewMessage);
      profileEmail = view.findViewById(R.id.emailTextViewNewMessage);
    }
  }
}
