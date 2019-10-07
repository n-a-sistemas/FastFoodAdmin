package com.example.fast_food_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.fast_food_admin.adapter.AdapterCupom;
import com.example.fast_food_admin.modelo.Administrador;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ResgataActivity extends AppCompatActivity {

    //Banco
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    //ListView
    private List<Administrador> admins = new ArrayList<Administrador>();
    private ArrayAdapter<Administrador> arrayAdapterCupom;
    private ListView listViewCupom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgata_cupom);

        conectarBanco();
    }

    private void conectarBanco(){
        FirebaseApp.initializeApp(ResgataActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void eventoBanco(){
        databaseReference.child("usuario").child("cupons").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Administrador admin = snapshot.getValue(Administrador.class);
                    admins.add(admin);
                }

                arrayAdapterCupom = new AdapterCupom(ResgataActivity.this,
                        (ArrayList<Administrador>) admins);
                listViewCupom.setAdapter(arrayAdapterCupom);

                listViewCupom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        //excluirDado(admins.get(i));
                        //return true;
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void excluirDado(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.app_name);
        builder.setMessage("Você deseja remover esse cupom?");
        builder.setIcon(R.drawable.hamburguer);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                databaseReference.child("usuario").child("cupons").removeValue();
                //trabalhar melhor nessa parte, preciso excluir do banco apenas os
                //cupons
            }
        });
    }
}
