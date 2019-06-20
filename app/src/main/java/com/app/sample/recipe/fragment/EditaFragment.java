package com.app.sample.recipe.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.app.sample.recipe.Conexao.Api;
import com.app.sample.recipe.Conexao.Models.RetornoWs;
import com.app.sample.recipe.Conexao.Models.Usuario;
import com.app.sample.recipe.Conexao.RestClient;
import com.app.sample.recipe.Conexao.Sessao;
import com.app.sample.recipe.R;
import com.app.sample.recipe.View.SigninActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditaFragment extends Fragment implements OnClickListener {

    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_editar_perfil, null);
        // activate fragment menu
        setHasOptionsMenu(true);

        final EditText nome = view.findViewById(R.id.edtName);
        final EditText email = view.findViewById(R.id.edtEmail);
        final EditText senha = view.findViewById(R.id.edtPassword);
        final EditText cpf = view.findViewById(R.id.edtCpf);

        Button  finishSignin = view.findViewById(R.id.finishSignin);

        final Sessao sessao = new Sessao(getActivity());

        nome.setText(sessao.getnomesessao());
        email.setText(sessao.getemailsessao());
        senha.setText(sessao.getsenhasessao());
        cpf.setText(sessao.getcpfsessao());

        finishSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = sessao.getIdsessao();

                    String name = nome.getText().toString().trim();
                    String mail = email.getText().toString().trim();
                    String password = senha.getText().toString().trim();
                    String cpf1 = cpf.getText().toString().trim();

                    Api api  = RestClient.getClient(getActivity()).create(Api.class);
                    Call<RetornoWs> Conexao = api.editar_usuario(new Usuario(id, name, mail,password, cpf1));

                    Conexao.enqueue(new Callback<RetornoWs>() {
                        @Override
                        public void onResponse(Call<RetornoWs> call, Response<RetornoWs> response) {

                            Toast.makeText(getActivity(), response.body().getRetorno(), Toast.LENGTH_SHORT).show();
//                            if(response.body().getStatus().equals("1")){
//
//                                finish();
//                            }
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
