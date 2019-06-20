package com.app.sample.recipe.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.sample.recipe.Conexao.Models.exibir_comentarios;
import com.app.sample.recipe.R;

import java.util.List;

public class ComentariosAdapter extends RecyclerView.Adapter<ComentariosAdapter.ComentarioHolder> {

    List<exibir_comentarios> list;
    Activity activity;

    public ComentariosAdapter(Activity activity, List<exibir_comentarios> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public ComentariosAdapter.ComentarioHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comentario, parent, false);
        ComentariosAdapter.ComentarioHolder vh = new ComentarioHolder(v);
        return vh;
    }

    public class ComentarioHolder extends RecyclerView.ViewHolder {

        TextView nome;
        TextView data;
        TextView comentario;

        public ComentarioHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.nome);
            data = itemView.findViewById(R.id.data);
            comentario = itemView.findViewById(R.id.comentarios);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ComentarioHolder viewHolder, int i) {
        exibir_comentarios comentarios = list.get(i);

        viewHolder.nome.setText(comentarios.getNome());
        viewHolder.data.setText(comentarios.getDate_time());
        viewHolder.comentario.setText(comentarios.getComentario());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
