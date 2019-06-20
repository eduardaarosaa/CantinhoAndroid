package com.app.sample.recipe.Conexao;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Sessao {

    private Activity nActivity;
    private SharedPreferences Pre;


    public Sessao(Activity activity) {

        nActivity = activity;

        Pre = activity.getSharedPreferences("Sessao", Context.MODE_PRIVATE);

    }

    private String idsessao = "idsessao";

    public void setidsessao(String id) {

        SharedPreferences.Editor set = Pre.edit();

        set.putString(idsessao, id);

        set.commit();

    }

    public String getIdsessao() {

        return Pre.getString(idsessao, "");
    }


    private String nomesessao = "nomesessao";

    public void setnomesessao(String id) {

        SharedPreferences.Editor set = Pre.edit();

        set.putString(nomesessao, id);

        set.commit();

    }

    public String getnomesessao() {

        return Pre.getString(nomesessao, "");
    }

    private String cpfsessao = "cpfsessao";

    public void setcpfsessao(String id) {

        SharedPreferences.Editor set = Pre.edit();

        set.putString(cpfsessao, id);

        set.commit();

    }

    public String getcpfsessao() {

        return Pre.getString(cpfsessao, "");
    }

    private String emailsessao = "emailsessao";

    public void setemailsessao(String id) {

        SharedPreferences.Editor set = Pre.edit();

        set.putString(emailsessao, id);

        set.commit();

    }

    public String getemailsessao() {

        return Pre.getString(emailsessao, "");
    }

    private String senhasessao = "senhasessao";

    public void setsenhasessao(String id) {

        SharedPreferences.Editor set = Pre.edit();

        set.putString(senhasessao, id);

        set.commit();

    }

    public String getsenhasessao() {

        return Pre.getString(senhasessao, "");
    }

    //==============================================================================================

    private String logado = "logado";

    public void setlogado() {

        SharedPreferences.Editor set = Pre.edit();

        set.putBoolean(logado, true);

        set.commit();

    }

    public void setdeslogado() {

        SharedPreferences.Editor set = Pre.edit();

        set.putBoolean(logado, false);

        set.commit();

    }


    public Boolean getLogado() {

        return Pre.getBoolean(logado, false);
    }

}
