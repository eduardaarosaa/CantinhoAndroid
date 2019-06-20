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
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sample.recipe.Conexao.Api;
import com.app.sample.recipe.Conexao.Models.RetornoWs;
import com.app.sample.recipe.Conexao.Models.Usuario;
import com.app.sample.recipe.Conexao.RestClient;
import com.app.sample.recipe.R;
import com.app.sample.recipe.View.TermsAndPrivacityPolicy.TermsOfUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigninActivity extends AppCompatActivity {
    private Activity activity;
    private EditText edtName;
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtCpf;
    private TextView btnTermsOfUser;
    private CheckBox chbTerms;
    private Button finishSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        initVars();
        initActions();


    }

    private void initVars() {

        activity = SigninActivity.this;
        //
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtCpf = findViewById(R.id.edtCpf);
        //
        btnTermsOfUser = findViewById(R.id.btnTermsOfUser);
        chbTerms = findViewById(R.id.chbTerms);
        //
        finishSignin = findViewById(R.id.finishSignin);

    }

    private void initActions() {
        btnTermsOfUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        finishSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verifyCheckboxState(activity, chbTerms, "Aceite os termos")) {

                    String name = edtName.getText().toString().trim();
                    String email = edtEmail.getText().toString().trim();
                    String password = edtPassword.getText().toString().trim();
                    String cpf = edtCpf.getText().toString().trim();

                    Api api  = RestClient.getClient(SigninActivity.this).create(Api.class);
                    Call<RetornoWs> Conexao = api.Criar_usuarios(new Usuario(name, email,password, cpf));

                    Conexao.enqueue(new Callback<RetornoWs>() {
                        @Override
                        public void onResponse(Call<RetornoWs> call, Response<RetornoWs> response) {

                            Toast.makeText(activity, response.body().getRetorno(), Toast.LENGTH_SHORT).show();
                            if(response.body().getStatus().equals("1")){

                                finish();
                        }}

                        @Override
                        public void onFailure(Call<RetornoWs> call, Throwable t) {

                        }
                    });

                }
            }
        });

    }
    public void btnTermsOfUser(View v) {
        startActivity(new Intent(getBaseContext(), TermsOfUser.class));
    }

    public static boolean verifyCheckboxState(Activity activity, CheckBox checkBox, String textErro) {
        boolean c = checkBox.isChecked();
        if (c) {
            return true;
        } else {
            if (!textErro.equals("")) {
                Toast.makeText(activity, textErro, Toast.LENGTH_SHORT).show();
            }
            return false;
        }

    }

}