package com.example.fast_food_admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fast_food_admin.R;
import com.example.fast_food_admin.modelo.Administrador;

import java.util.ArrayList;
import java.util.List;

public class AdapterPesquisa extends ArrayAdapter<Administrador> {

    //ListView
    private Context context;
    private List<Administrador> administradors;

    //Declarando objetos
    private TextView textViewNome;
    private TextView textViewEmail;

    public AdapterPesquisa(Context context, ArrayList<Administrador> list){
        super (context, 0, list);
        this.context = context;
        administradors = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listUsuarios = convertView;

        //Iniciando o layout_list_view na ListView
        if (listUsuarios == null){
            listUsuarios = LayoutInflater.from(context)
                    .inflate(R.layout.layout_list, parent, false);
        }

        Administrador itemAdministrador = administradors.get(position);

        TextView nome = listUsuarios.findViewById(R.id.text_view_nome);
        nome.setText(itemAdministrador.getNome());

        TextView email = listUsuarios.findViewById(R.id.text_view_email);
        email.setText(itemAdministrador.getEmail());

        return listUsuarios;
    }
}
