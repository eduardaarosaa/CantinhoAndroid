package com.app.sample.recipe.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.sample.recipe.ActivityMain;
import com.app.sample.recipe.Conexao.Api;
import com.app.sample.recipe.Conexao.Models.Publicacoes;
import com.app.sample.recipe.Conexao.Models.RetornoWs;
import com.app.sample.recipe.Conexao.Models.Usuario;
import com.app.sample.recipe.Conexao.RestClient;
import com.app.sample.recipe.Conexao.Sessao;
import com.app.sample.recipe.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReceitasFragment extends Fragment implements OnClickListener {

    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_publicar_receitas, null);
        // activate fragment menu
        setHasOptionsMenu(true);

        final EditText titulo = view.findViewById(R.id.titulo);
        final EditText ingredientes = view.findViewById(R.id.ingredients);
        final EditText preparo = view.findViewById(R.id.preparo);
        final EditText foto = view.findViewById(R.id.foto);
//
        Button finishSignin = view.findViewById(R.id.finishSignin);

        finishSignin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Sessao editar = new Sessao(getActivity());
                String id = editar.getIdsessao();

                String titulo1 = titulo.getText().toString().trim();
                String ingredientes1 = ingredientes.getText().toString().trim();
                String preparo1 = preparo.getText().toString().trim();
                String foto1 = foto.getText().toString().trim();

                Api api = RestClient.getClient(getActivity()).create(Api.class);
                Call<RetornoWs> Conexao = api.add_publicacao(new Publicacoes("1", titulo1, ingredientes1, preparo1, foto1));

                Conexao.enqueue(new Callback<RetornoWs>() {
                    @Override
                    public void onResponse(Call<RetornoWs> call, Response<RetornoWs> response) {

                        Toast.makeText(getActivity(), response.body().getRetorno(), Toast.LENGTH_SHORT).show();
                        if (response.body().getStatus().equals("1")) {
//                                finish();
//                            ((ActivityMain) getActivity()).setFistFragment();

                            titulo.setText("");
                            ingredientes.setText("");
                            preparo.setText("");
                            foto.setText("");
                        }
                    }

                    @Override
                    public void onFailure(Call<RetornoWs> call, Throwable t) {

                    }
                });


            }
        });

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Snackbar.make(view, item.getTitle() + " clicked", Snackbar.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(View v) {
        finish();
    }

    private void finish() {
        System.exit(0);

    }
}
