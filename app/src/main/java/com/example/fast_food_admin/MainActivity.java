package com.example.fast_food_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

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

public class MainActivity extends AppCompatActivity {

    //Declarando objetos
    private EditText editTextBusca;

    //Banco
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    //ListView
    private ListView listView;
    private List<Usuario> usuarios = new ArrayList<>();
    private ArrayAdapter<Usuario> arrayAdapterUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chamaLogin();
        conectaBanco();
        pesquisaUsuario();

        editTextBusca = findViewById(R.id.edit_text_busca);
        listView = findViewById(R.id.list_view_usuarios);
    }

    private void conectaBanco(){
        FirebaseApp.initializeApp(MainActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void chamaLogin(){
        Intent intent= new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void pesquisaUsuario(){
        databaseReference.child("usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Usuario usuario = snapshot.getValue(Usuario.class);
                    usuarios.add(usuario);
                }

                arrayAdapterUsuario = new AdapterPesquisa(MainActivity.this,
                        (ArrayList<Usuario>) usuarios);
                listView.setAdapter(arrayAdapterUsuario);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //selecionaUsuario(usuarios.get(i));
                        //return;
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void selecionaUsuario(final Usuario usuario){

    }
}
