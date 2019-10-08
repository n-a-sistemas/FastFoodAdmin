package com.example.fast_food_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.fast_food_admin.adapter.AdapterCupom;
import com.example.fast_food_admin.modelo.Usuario;
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
    private List<Usuario> admins = new ArrayList<Usuario>();
    private ArrayAdapter<Usuario> arrayAdapterCupom;
    private ListView listViewCupom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgata_cupom);

        listViewCupom = findViewById(R.id.list_view_telacupom);

        conectarBanco();
        eventoBanco();
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
                    Usuario admin = snapshot.getValue(Usuario.class);
                    admins.add(admin);
                }

                arrayAdapterCupom = new AdapterCupom(ResgataActivity.this,
                        (ArrayList<Usuario>) admins);
                listViewCupom.setAdapter(arrayAdapterCupom);

                listViewCupom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        databaseReference.child("usuario")
                                .child(admins.get(i).getUid()).setValue(admins);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
