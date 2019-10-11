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
import com.example.fast_food_admin.modelo.Cupom;

import java.util.ArrayList;
import java.util.List;

public class CupomAdapter extends ArrayAdapter {

    private Context context;
    private List<Cupom> cupons;

    public CupomAdapter(Context context, ArrayList<Cupom> cupons){
        super(context, 0, cupons);

        this.context = context;
        this.cupons = cupons;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listaItem = convertView;

        if (listaItem == null){
            listaItem = LayoutInflater.from(context).inflate(R.layout.layout_cupom,
                    parent, false);
        }

        Cupom cupomAtual = cupons.get(position);

        TextView nome = listaItem.findViewById(R.id.text_view_nome);
        nome.setText(cupomAtual.getToken());

        TextView ID = listaItem.findViewById(R.id.text_view_codigo);
        ID.setText(cupomAtual.getNome());

        ImageView cupom = listaItem.findViewById(R.id.image_view_cupom);

        if (nome.getText().equals("Hamburguer")){
            cupom.setImageResource(R.drawable.hamburguer);
        }
        else if (nome.getText().equals("Refri")){
            cupom.setImageResource(R.drawable.refri3);
        }
        else {
            cupom.setImageResource(R.drawable.fastfood2);
        }

        return listaItem;
    }
}
