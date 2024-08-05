package com.example.todolist;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.adapter.ItemAdapter;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView title;
    TextView txtImage;
    Uri uriImage;
    ImageButton btnAdd;
    Button btnLoadPicture;
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
        tasks_list.add(new ListItem(1, "Comprar", null));
        tasks_list.add(new ListItem(2, "Limpiar", null));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        setDataInit();
        loadRecyclerView();

        title = findViewById(R.id.txtTitle);
        txtImage = findViewById(R.id.txtImage);
        btnAdd  = findViewById(R.id.btnAdd);
        btnLoadPicture  = findViewById(R.id.btn_select_image);

        btnAdd.setOnClickListener(view -> {
            String txtTitle = title.getText().toString();
            if (!txtTitle.isEmpty() && uriImage != null) {
                tasks_list.add(new ListItem((!tasks_list.isEmpty()) ? tasks_list.size() + 1 : 1, txtTitle, uriImage));
                title.setText("");
                loadRecyclerView();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Volver a intentar por favor",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnLoadPicture.setOnClickListener(view -> {
            ImagePicker.with(this)
                    .crop()
                    .compress(1024)
                    .maxResultSize(1080, 1080)
                    .start();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        txtImage.setText("");

        if (requestCode == 2404 && resultCode == -1) {
            txtImage.setText("Imagen cargada correctamente");
            uriImage = data.getData();
        } else {
            Toast.makeText(getApplicationContext(),
                            "No se seleccionó la imagen",
                            Toast.LENGTH_SHORT).show();
        }
    }

    private void loadRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerItem);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new ItemAdapter(tasks_list));
    }
}