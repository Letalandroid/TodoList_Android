package com.example.todolist;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.adapter.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView title;
    ImageButton btnAdd;
    List<ListItem> tasks_list = new ArrayList<>();

    private void init() {
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void setDataInit () {
        tasks_list.add(new ListItem(1, "Comprar", "assets/img/test.jpg"));
        tasks_list.add(new ListItem(2, "Limpiar", "assets/img/test.jpg"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        setDataInit();
        loadRecyclerView();

        title = findViewById(R.id.txtTitle);
        btnAdd  = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(view -> {
            String txtTitle = title.getText().toString();
            Log.d("Notas", txtTitle);
            if (!txtTitle.isEmpty()) {
                tasks_list.add(new ListItem(tasks_list.size() + 1, txtTitle, ""));
                title.setText("");
                loadRecyclerView();
            }
        });
    }

    private void loadRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerItem);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new ItemAdapter(tasks_list));
    }
}