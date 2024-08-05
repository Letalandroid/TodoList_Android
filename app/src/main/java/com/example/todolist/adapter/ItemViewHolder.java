package com.example.todolist.adapter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.ListItem;
import com.example.todolist.R;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    private TextView txtTitle;
    private ImageView ivImage;
    private ImageButton btnRemove;

    public ItemViewHolder(View view) {
        super(view);
        this.txtTitle = view.findViewById(R.id.txtTitle);
        this.ivImage = view.findViewById(R.id.ivImage);
        this.btnRemove = view.findViewById(R.id.btnRemove);
    }

    void render(ListItem itemsListModel) {
        txtTitle.setText(itemsListModel.getTitle());
        ivImage.setImageURI(itemsListModel.getImage());
        btnRemove.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), String.valueOf(itemsListModel.getId()), Toast.LENGTH_SHORT).show();
        });
    }
}
