package com.example.fast_food_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.fast_food_admin.modelo.Pergunta;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AddPerguntasActivity extends AppCompatActivity {

    //Declrando objetos
    private EditText editTextPergunta;
    private EditText editTextResposta1;
    private EditText editTextResposta2;
    private EditText editTextResposta3;
    private EditText editTextResposta4;
    private EditText editTextRespostaCerta;

    //Banco
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private static int j = 0;
    private List<Pergunta> perguntaList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_perguntas);

        editTextPergunta = findViewById(R.id.edit_text_pergunta);
        editTextResposta1 = findViewById(R.id.edit_text_resposta1);
        editTextResposta2 = findViewById(R.id.edit_text_resposta2);
        editTextResposta3 = findViewById(R.id.edit_text_resposta3);
        editTextResposta4 = findViewById(R.id.edit_text_resposta4);
        editTextRespostaCerta = findViewById(R.id.edit_text_respostaCerta);

        conectarBanco();
    }

    private void conectarBanco(){
        FirebaseApp.initializeApp(AddPerguntasActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void adicionaPergunta(View view){

        //Pegando os valores dos editTexts

        String pergunta = editTextPergunta.getText().toString();
        String resposta1 = editTextResposta1.getText().toString();
        String resposta2 = editTextResposta2.getText().toString();
        String resposta3 = editTextResposta3.getText().toString();
        String resposta4 = editTextResposta4.getText().toString();
        String respostaCerta = editTextRespostaCerta.getText().toString();

        List<String> lista = new ArrayList<>();
        lista.add(resposta1);
        lista.add(resposta2);
        lista.add(resposta3);
        lista.add(resposta4);
        lista.add(respostaCerta);

        if (!pergunta.equals("") && !resposta1.equals("") && !resposta2.equals("")
                && !resposta3.equals("") && !resposta4.equals("") && !respostaCerta.equals("")){
            lePerguntas(respostaCerta, pergunta, lista);
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.app_name);
            builder.setMessage("Você precisa preencher todos os campos!");
            builder.setIcon(R.drawable.hamburguer);
            AlertDialog alert = builder.create();
            alert.show();
        }
        limpaPerguntas();
    }

    private void lePerguntas(final String respostaCerta, final String pergunta, final List lista){
        j = 0;
        databaseReference.child("Perguntas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Long teste = dataSnapshot.getChildrenCount();
                Pergunta perg = new Pergunta(teste.toString(), respostaCerta, pergunta, lista);
                databaseReference.child("Perguntas").child(teste.toString()).child(teste.toString()).setValue(perg);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void limpaPerguntas(){

        editTextPergunta.setText("");
        editTextResposta1.setText("");
        editTextResposta2.setText("");
        editTextResposta3.setText("");
        editTextResposta4.setText("");
        editTextRespostaCerta.setText("");
    }
}
