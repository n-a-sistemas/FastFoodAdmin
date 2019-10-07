package com.example.fast_food_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.fast_food_admin.adapter.AdapterPesquisa;
import com.example.fast_food_admin.modelo.Administrador;
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
    private List<Administrador> administradors = new ArrayList<>();
    private ArrayAdapter<Administrador> arrayAdapterUsuario;

    //SharedPreferences
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextBusca = findViewById(R.id.edit_text_busca);
        listView = findViewById(R.id.list_view_usuarios);

        chamaLogin();
        conectaBanco();
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

    public void pesquisaUsuario(View v){
        databaseReference.child("usuario").
                orderByChild("email")
                .startAt(editTextBusca.getText().toString())
                .endAt(editTextBusca.getText().toString() + "\uf8ff")
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Administrador administrador = snapshot.getValue(Administrador.class);
                    administradors.add(administrador);
                }

                arrayAdapterUsuario = new AdapterPesquisa(MainActivity.this,
                        (ArrayList<Administrador>) administradors);
                listView.setAdapter(arrayAdapterUsuario);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //selecionaUsuario(administradors.get(i));
                        //return;
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void selecionaUsuario(final Administrador administrador){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logout, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_sair){
            sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("LOGIN", "false");
            editor.apply();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
