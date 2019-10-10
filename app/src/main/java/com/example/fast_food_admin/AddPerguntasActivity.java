package com.example.fast_food_admin;

import androidx.annotation.NonNull;
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

        for (int i = 0; i < 20; i++) {

            Pergunta perg = new Pergunta();

            //Pegando os valores dos editTexts
            String pergunta = editTextPergunta.getText().toString();
            String resposta1 = editTextResposta1.getText().toString();
            String resposta2 = editTextResposta2.getText().toString();
            String resposta3 = editTextResposta3.getText().toString();
            String resposta4 = editTextResposta4.getText().toString();
            String resposta5 = editTextRespostaCerta.getText().toString();

            databaseReference.child(pergunta).child(perg.getUuid()).child(perg.getTitulo_pergunta()).setValue(perg);
            databaseReference.child(resposta1).child(perg.getUuid()).setValue(perg);
            databaseReference.child(resposta2).child(perg.getUuid()).setValue(perg);
            databaseReference.child(resposta3).child(perg.getUuid()).setValue(perg);
            databaseReference.child(resposta4).child(perg.getUuid()).setValue(perg);
            databaseReference.child(resposta5).child(perg.getUuid()).child(perg.getResposta_correta()).setValue(perg);


        }
    }
}
