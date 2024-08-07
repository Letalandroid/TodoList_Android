package com.example.todolist.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.Share;
import com.example.todolist.ListItem;
import com.example.todolist.R;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    private TextView txtTitle;
    private ImageView ivImage;
    private FrameLayout flTask;

    public ItemViewHolder(View view) {
        super(view);
        this.txtTitle = view.findViewById(R.id.txtTitle);
        this.ivImage = view.findViewById(R.id.ivImage);
        this.flTask = view.findViewById(R.id.frl_1);
    }

    void render(ListItem itemsListModel) {
        txtTitle.setText(itemsListModel.getTitle());
        ivImage.setImageURI(itemsListModel.getImage());
        flTask.setOnClickListener(view -> {
            if (!itemsListModel.getTitle().isEmpty() && itemsListModel.getImage() != null) {
                Intent intent = new Intent(view.getContext(), Share.class);
                intent.putExtra("title", itemsListModel.getTitle());
                intent.putExtra("image", itemsListModel.getImage());
                view.getContext().startActivity(intent);
            } else {
                Toast.makeText(view.getContext(), "Volver a intentar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
