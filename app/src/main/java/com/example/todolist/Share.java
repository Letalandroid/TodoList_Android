package com.example.todolist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Share extends AppCompatActivity {

    private Button btnShare;
    private Button btnCancel;

    private void init() {
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

        Bundle params = getIntent().getExtras();
        btnShare = findViewById(R.id.btnShare);
        btnCancel = findViewById(R.id.btnCancelar);
        TextView txtTitle = findViewById(R.id.txtTitle);
        ImageView ivPoster = findViewById(R.id.ivPoster);

        if (params != null) {
            txtTitle.setText(params.getString("title"));
            ivPoster.setImageURI(getIntent().getParcelableExtra("image"));
        } else {
            Toast.makeText(this, "Los datos no se lograron cargar", Toast.LENGTH_SHORT).show();
        }

        btnShare.setOnClickListener(view -> {
            Intent it = new Intent();
            it.setAction(Intent.ACTION_SEND);
            it.putExtra(Intent.EXTRA_TEXT, txtTitle.getText().toString());
            it.putExtra(Intent.EXTRA_STREAM, (Uri) getIntent().getParcelableExtra("image"));
            it.setType("image/*");
            startActivity(Intent.createChooser(it, null));
        });

        btnCancel.setOnClickListener(view -> {
            finish();
        });
    }
}