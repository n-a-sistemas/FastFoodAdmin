package com.example.fast_food_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.fast_food_admin.adapter.CupomAdapter;
import com.example.fast_food_admin.modelo.Cupom;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CupomActivity extends AppCompatActivity {

    //Banco
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    //ListView
    private List<Cupom> cupoms = new ArrayList<Cupom>();
    private ArrayAdapter<Cupom> arrayAdapterUsuario;
    private ListView listView;

    //SharedPreferences
    private SharedPreferences sharedPreferences;
    private Cupom jorge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupom);

        listView = findViewById(R.id.list_view_cupom);

        conectarBanco();
        eventoBanco();
    }

    private void conectarBanco(){
        FirebaseApp.initializeApp(CupomActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void eventoBanco(){

        Intent intent = getIntent();
        final String id = intent.getStringExtra("IDC");


        databaseReference.child("usuario").child(id).child("cupons").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                cupoms.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    jorge = snapshot.getValue(Cupom.class);
                    cupoms.add(jorge);
                }

                arrayAdapterUsuario = new CupomAdapter(CupomActivity.this,
                                                                    (ArrayList<Cupom>) cupoms);
                listView.setAdapter(arrayAdapterUsuario);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        cupoms.remove(i);
                        excluiCupom(cupoms);

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
    private void excluiCupom(final List<Cupom> cupoms){


        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.app_name);
        builder.setMessage("Você deseja comprar esse cupom?");
        builder.setIcon(R.drawable.hamburguer);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent intent = getIntent();

                final String idUsuario = intent.getStringExtra("IDC");

                databaseReference.child("usuario").child(idUsuario).child("cupons").setValue(cupoms);

            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }

        });
        AlertDialog alert = builder.create();
        alert.show();


    }

}
