/*
 *
 *  *                                     /@
 *  *                      __        __   /\/
 *  *                     /==\      /  \_/\/
 *  *                   /======\    \/\__ \__
 *  *                 /==/\  /\==\    /\_|__ \
 *  *              /==/    ||    \=\ / / / /_/
 *  *            /=/    /\ || /\   \=\/ /
 *  *         /===/   /   \||/   \   \===\
 *  *       /===/   /_________________ \===\
 *  *    /====/   / |                /  \====\
 *  *  /====/   /   |  _________    /      \===\
 *  *  /==/   /     | /   /  \ / / /         /===/
 *  * |===| /       |/   /____/ / /         /===/
 *  *  \==\             /\   / / /          /===/
 *  *  \===\__    \    /  \ / / /   /      /===/   ____                    __  _         __  __                __
 *  *    \==\ \    \\ /____/   /_\ //     /===/   / __ \__  ______  ____ _/ /_(_)____   / / / /__  ____ ______/ /_
 *  *    \===\ \   \\\\\\\/   ///////     /===/  / / / / / / / __ \/ __ `/ __/ / ___/  / /_/ / _ \/ __ `/ ___/ __/
 *  *      \==\/     \\\\/ / //////       /==/  / /_/ / /_/ / / / / /_/ / /_/ / /__   / __  /  __/ /_/ / /  / /_
 *  *      \==\     _ \\/ / /////        |==/   \___\_\__,_/_/ /_/\__,_/\__/_/\___/  /_/ /_/\___/\__,_/_/   \__/
 *  *        \==\  / \ / / ///          /===/
 *  *        \==\ /   / / /________/    /==/
 *  *          \==\  /               | /==/
 *  *          \=\  /________________|/=/
 *  *            \==\     _____     /==/
 *  *           / \===\   \   /   /===/
 *  *          / / /\===\  \_/  /===/
 *  *         / / /   \====\ /====/
 *  *        / / /      \===|===/
 *  *        |/_/         \===/
 *  *                       =
 *  *
 *  * Copyright(c) Developed by John Alves at 2018/$today.mouth/9 at 2:31:40 for quantic heart studios
 *
 */

package com.app.sample.recipe.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sample.recipe.ActivityMain;
import com.app.sample.recipe.Conexao.Api;
import com.app.sample.recipe.Conexao.Models.Publicacoes;
import com.app.sample.recipe.Conexao.Models.RetornoWs;
import com.app.sample.recipe.Conexao.RestClient;
import com.app.sample.recipe.Conexao.Sessao;
import com.app.sample.recipe.R;
import com.app.sample.recipe.Utils.ActivityUtil;
import com.app.sample.recipe.View.TermsAndPrivacityPolicy.PrivacityPolicy;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Activity activity;
    //
    private EditText edtLogin;
    private EditText edtSenha;
    //
    private TextView tv_recoverpass;
    private TextView tv_signin;
    private TextView tv_pricavity_policy;
    //
    private Button btnLogin;

    private Sessao sessao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessao = new Sessao(LoginActivity.this);


        verifica_sessao();


        initVars();
        initActions();
//        TesteConexao();

    }

    private void verifica_sessao() {

        if (sessao.getLogado()) {
            telaUsuario();
        }

    }

//    private void TesteConexao() {
//        Api api = RestClient.getClient(this).create(Api.class);
//        Call<RetornoWs> Conexao = api.RetornaPublic();
//
//        Conexao.enqueue(new Callback<RetornoWs>() {
//            @Override
//            public void onResponse(Call<RetornoWs> call, Response<RetornoWs> response) {
//
//
//                List<Publicacoes> publicacoes = response.body().getPublicacoes();
//
//                for (int i = 0; i < publicacoes.size(); i++) {
//
//                    Log.w("Publicacao " + i, publicacoes.get(i).getTitulo());
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<RetornoWs> call, Throwable t) {
//                Log.w("Publicacao ", t.getMessage());
//
//            }
//        });
//    }

    private void initVars() {

        activity = LoginActivity.this;
        //
        edtLogin = findViewById(R.id.edtLogin);
        edtSenha = findViewById(R.id.edtSenha);
        //
        tv_recoverpass = findViewById(R.id.tv_recoverpass);
        tv_signin = findViewById(R.id.tv_signin);
        tv_pricavity_policy = findViewById(R.id.tv_pricavity_policy);
        //
        btnLogin = findViewById(R.id.btnLogin);

    }

    private void initActions() {

        //RecoverPass
        tv_recoverpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, RecoverPasswordActivity.class);
                startActivity(intent);
            }
        });

        //signin
        tv_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, SigninActivity.class);
                startActivity(intent);
            }
        });

        //privacity policy
        tv_pricavity_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, PrivacityPolicy.class);
                startActivity(intent);
            }
        });

        //Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String login = edtLogin.getText().toString().trim();
                String senha = edtSenha.getText().toString().trim();

                Api api = RestClient.getClient(LoginActivity.this).create(Api.class);
                Call<RetornoWs> Conexao = api.RetornarLogin(login, senha);

                Conexao.enqueue(new Callback<RetornoWs>() {
                    @Override
                    public void onResponse(Call<RetornoWs> call, Response<RetornoWs> response) {
                        Toast.makeText(activity, response.body().getRetorno(), Toast.LENGTH_SHORT).show();
                        if (response.body().getStatus().equals("1")) {

                            sessao.setidsessao(response.body().getDados().getId());
                            sessao.setnomesessao(response.body().getDados().getNome());
                            sessao.setcpfsessao(response.body().getDados().getCpf());
                            sessao.setemailsessao(edtLogin.getText().toString().trim());
                            sessao.setsenhasessao(edtSenha.getText().toString().trim());

                            sessao.setlogado();
                            telaUsuario();
                        }
                    }

                    @Override
                    public void onFailure(Call<RetornoWs> call, Throwable t) {

                    }
                });


            }
        });
    }

    private void telaUsuario() {
        ActivityUtil.callActivity(LoginActivity.this, ActivityMain.class, true);

    }

}