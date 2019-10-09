package com.example.fast_food_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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
    private List<Cupom> tarefas = new ArrayList<Cupom>();
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




        databaseReference.child("usuario").child("S5NGdaBCCfYQvBBPMw01UWa2G4B2").child("cupons").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                tarefas.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    jorge = snapshot.getValue(Cupom.class);
                    tarefas.add(jorge);
                }

                arrayAdapterUsuario = new CupomAdapter(CupomActivity.this,
                                                                    (ArrayList<Cupom>) tarefas);
                listView.setAdapter(arrayAdapterUsuario);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

}
