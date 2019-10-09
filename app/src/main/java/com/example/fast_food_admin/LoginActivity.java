package com.example.fast_food_admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.fast_food_admin.modelo.Usuario;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    //SharedPreferences
    private SharedPreferences sharedPreferences;
    private Usuario usuario = new Usuario();

    //Banco
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public Boolean valido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        String resultado = sharedPreferences.getString("LOGIN", "");

        sharedPreferences = getSharedPreferences("ID", Context.MODE_PRIVATE);

        conectarBancoAdmin();

        if (!Boolean.parseBoolean(resultado)){
            criarLogin();
        }
        else {
            finish();
        }
    }

    private void criarLogin(){
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setIsSmartLockEnabled(false)
                        .setTheme(R.style.LoginAdmin)
                        .build(), 231);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 231){
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK){

                if (response.isNewUser()){
                    this.usuario.setUid(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    this.usuario.setEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                    this.usuario.setNome(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
                    this.usuario.setValido(false);
                    this.usuario.setAdmin(false);

                        databaseReference
                                .child("usuario")
                                .child(usuario.getUid())
                                .setValue(usuario);
                }

                sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("LOGIN", "true");
                editor.putString("ID", usuario.getUid());
                editor.apply();
                finish();

                /*if (usuario.isAdmin()) {
                    //editor.putString("ADMIN", "true");
                    editor.putString("LOGIN", "true");
                }
                else {
                    //editor.putString("ADMIN", "false");
                    editor.putString("LOGIN", "false");
                }*/
            }

            else{
                if (response == null){
                    finish();
                }
            }
        }
    }

    public void conectarBancoAdmin(){

        FirebaseApp.initializeApp(LoginActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

}
