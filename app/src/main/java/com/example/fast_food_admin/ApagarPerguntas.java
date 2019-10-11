package com.example.fast_food_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fast_food_admin.adapter.AdapterPesquisa;
import com.example.fast_food_admin.modelo.Usuario;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ApagarPerguntas extends AppCompatActivity {

    private TextView textViewNumero;
    private TextView textViewPergunta;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private ListView listViewApagaPergunta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apagar_perguntas);

        textViewNumero = findViewById(R.id.text_view_numero);
        textViewPergunta = findViewById(R.id.text_view_perguntaApagar);

        conectarBanco();

    }

    private void conectarBanco(){
        FirebaseApp.initializeApp(ApagarPerguntas.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }



}
