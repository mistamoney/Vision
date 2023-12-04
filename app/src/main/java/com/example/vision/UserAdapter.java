package com.example.vision;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private ArrayList<UserItem> userItemList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView usernameTextView;
        public ImageView deleteImageView;

        public UserViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView_user);
            usernameTextView = itemView.findViewById(R.id.textView_username);
            deleteImageView = itemView.findViewById(R.id.imageView_delete);

            deleteImageView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onDeleteClick(position);
                }
            });
        }
    }

    public UserAdapter(ArrayList<UserItem> userItemList) {
        this.userItemList = userItemList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserItem currentUserItem = userItemList.get(position);
        holder.imageView.setImageResource(currentUserItem.getImageResource());
        holder.usernameTextView.setText(currentUserItem.getUsername());
    }

    @Override
    public int getItemCount() {
        return userItemList.size();
    }
}
