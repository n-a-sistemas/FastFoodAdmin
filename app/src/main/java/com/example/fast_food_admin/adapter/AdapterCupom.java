package com.example.fast_food_admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fast_food_admin.R;
import com.example.fast_food_admin.modelo.Administrador;

import java.util.ArrayList;
import java.util.List;

public class AdapterCupom extends ArrayAdapter <Administrador>{

    private Context context;
    private List<Administrador> admin;

    public AdapterCupom (Context context, ArrayList<Administrador> admin){

        super(context, 0, admin);
        this.context = context;
        this.admin = admin;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listaCupomAdmin = convertView;

        if (listaCupomAdmin == null){
            listaCupomAdmin = LayoutInflater.from(context)
                    .inflate(R.layout.layout_resgata_cupom, parent, false);
        }

        Administrador adminCupom = admin.get(position);

        TextView nomeCupom = listaCupomAdmin.findViewById(R.id.text_view_nome);
        nomeCupom.setText(adminCupom.getNome());

        TextView pontosCupom = listaCupomAdmin.findViewById(R.id.text_view_pontos);
        pontosCupom.setText(adminCupom.getPontos());

        ImageView imageViewCupom = listaCupomAdmin.findViewById(R.id.image_view_cupom);

        if(nomeCupom.equals("Refri")){
            imageViewCupom.setImageResource(R.drawable.refri3);
        }
        else if (nomeCupom.equals("Hamburguer")){
            imageViewCupom.setImageResource(R.drawable.hamburguer3);
        }
        else if (nomeCupom.equals("Combo")){
            imageViewCupom.setImageResource(R.drawable.fastfood2);
        }

        return listaCupomAdmin;
    }
}
