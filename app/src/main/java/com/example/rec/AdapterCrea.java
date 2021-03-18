package com.example.rec;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterCrea extends RecyclerView.Adapter<AdapterCrea.ViewHolderCrea> {

    ArrayList<Crea> listaItems;

    public AdapterCrea(ArrayList<Crea> listaItems) {
        this.listaItems = listaItems;
    }

    @NonNull
    @Override
    public ViewHolderCrea onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crea_adapter_item, null, false);
        return new ViewHolderCrea(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCrea holder, int position) {
        holder.etiNombre.setText(listaItems.get(position).getNombre());
        holder.foto.setImageResource(listaItems.get(position).getIcono());
    }

    @Override
    public int getItemCount() {
        return listaItems.size();
    }

    public class ViewHolderCrea extends RecyclerView.ViewHolder {

        TextView etiNombre;
        ImageView foto;

        public ViewHolderCrea(@NonNull View itemView) {
            super(itemView);
            etiNombre = itemView.findViewById(R.id.frg_crea_txt);
            foto = (ImageView) itemView.findViewById(R.id.frg_crea_persona);
        }
    }

}
