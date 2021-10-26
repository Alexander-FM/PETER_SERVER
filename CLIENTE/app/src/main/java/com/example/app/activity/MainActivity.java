package com.example.app.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app.R;
import com.example.app.viewmodel.UsuarioViewModel;

public class MainActivity extends AppCompatActivity {
    private EditText edtMail, edtPassword;
    private Button btnIniciarSesión;
    private UsuarioViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initViewModel();
        this.init();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(UsuarioViewModel.class);
    }

    private void init() {
        edtMail = findViewById(R.id.edtMail);
        edtPassword = findViewById(R.id.edtPassword);
        btnIniciarSesión = findViewById(R.id.btnIniciarSesion);
        btnIniciarSesión.setOnClickListener(v -> {
            viewModel.login(edtMail.getText().toString(), edtPassword.getText().toString()).observe(this, response -> {
                if (response.getRpta() == 1) {
                    Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(this, ActivityHome.class);
                    startActivity(i);//Inicia la actividad
                    overridePendingTransition(R.anim.rigth_in, R.anim.rigth_out);//Hacer Animaciones
                } else {
                    Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}