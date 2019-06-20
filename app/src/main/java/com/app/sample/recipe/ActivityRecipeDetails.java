package com.app.sample.recipe;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.sample.recipe.Conexao.Api;
import com.app.sample.recipe.Conexao.Models.Publicacoes;
import com.app.sample.recipe.Conexao.Models.RetornoWs;
import com.app.sample.recipe.Conexao.Models.exibir_comentarios;
import com.app.sample.recipe.Conexao.RestClient;
import com.app.sample.recipe.adapter.ComentariosAdapter;
import com.app.sample.recipe.data.FavoriteUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityRecipeDetails extends AppCompatActivity {

    public static final String EXTRA_OBJCT = "com.app.sample.publicacoes.OBJ";

    // give preparation animation activity transition
    public static void navigate(AppCompatActivity activity, View transitionImage, Publicacoes obj) {
        Intent intent = new Intent(activity, ActivityRecipeDetails.class);
        intent.putExtra(EXTRA_OBJCT, obj);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, EXTRA_OBJCT);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    private Publicacoes publicacoes;
    private FloatingActionButton fab;
    private View parent_view;
    private RecyclerView listaDeComentarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        parent_view = findViewById(android.R.id.content);

        // animation transition
        ViewCompat.setTransitionName(findViewById(R.id.app_bar_layout), EXTRA_OBJCT);

        publicacoes = (Publicacoes) getIntent().getSerializableExtra(EXTRA_OBJCT);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        listaDeComentarios = findViewById(R.id.comentarios);

        iniciaListaComentarios();

        fabToggle();

        setupToolbar(publicacoes.getTitulo());

//        ((ImageView) findViewById(R.id.image)).setImageResource(publicacoes.getPhoto());
        Picasso.with(this).load(publicacoes.getImagem()).into(((ImageView) findViewById(R.id.image)));

//        LinearLayout ingredients = (LinearLayout) findViewById(R.id.ingredients);
        TextView ingredients = (TextView) findViewById(R.id.ingredients);
        TextView description = (TextView) findViewById(R.id.description);

        ingredients.setText(publicacoes.getIngrendies());
        description.setText(publicacoes.getDescricao());

//        String[] title_ingredients = getResources().getStringArray(R.array.ingredients);
//        addIngredientsList(ingredients, title_ingredients);
//
//        instructions.setText(Html.fromHtml(getString(R.string.instruction)));

//        instructions.setText(publicacoes.);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FavoriteUtils.isPublicacoesExist(getApplicationContext(), publicacoes)) {
                    FavoriteUtils.removeFavorite(getApplicationContext(), publicacoes);
//                    FavoriteUtils.delFavoriteId(getApplicationContext(), publicacoes.getId() + "");
                    Snackbar.make(parent_view, publicacoes.getTitulo() + " Removido dos favoritos", Snackbar.LENGTH_SHORT).show();

                    if (removerFavorito != null) {
                        removerFavorito.removendoFavorito();
                    }
                } else {
                    FavoriteUtils.addFavorite(getApplicationContext(), publicacoes);
//                    FavoriteUtils.addFavoriteId(getApplicationContext(), publicacoes.getId() + "");
                    Snackbar.make(parent_view, publicacoes.getTitulo() + " Adcionado aos favoritos", Snackbar.LENGTH_SHORT).show();
                }
                fabToggle();
            }
        });
    }

    private void setupToolbar(String name) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");

        ((TextView) findViewById(R.id.toolbar_title)).setText(name);
    }

    private void fabToggle() {
        if (FavoriteUtils.isPublicacoesExist(this, publicacoes)) {
            fab.setImageResource(R.drawable.ic_nav_favorites);
        } else {
            fab.setImageResource(R.drawable.ic_nav_favorites_outline);
        }
    }

    private void addIngredientsList(LinearLayout linearLayout, String[] title) {
        for (int i = 0; i < title.length; i++) {
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.VERTICAL);
            CheckBox checkBox = new CheckBox(this);
            checkBox.setTextColor(getResources().getColor(R.color.material_grey_600));
            checkBox.setText(title[i]);
            ll.addView(checkBox);
            linearLayout.addView(ll);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else {
            Snackbar.make(parent_view, item.getTitle() + " clicked", Snackbar.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void iniciaListaComentarios() {
        Api api = RestClient.getClient(ActivityRecipeDetails.this).create(Api.class);
        Call<RetornoWs> Conexao = api.getComentarios();

        Conexao.enqueue(new Callback<RetornoWs>() {
            @Override
            public void onResponse(Call<RetornoWs> call, Response<RetornoWs> response) {

                if (response.body().getStatus().equals("1")) {

                    List<exibir_comentarios> list = response.body().getExibir_comentarios();

                    LinearLayoutManager layoutManager = new LinearLayoutManager(ActivityRecipeDetails.this, LinearLayoutManager.VERTICAL, false);
                    ComentariosAdapter adapter = new ComentariosAdapter(ActivityRecipeDetails.this, list);
                    listaDeComentarios.setLayoutManager(layoutManager);
                    listaDeComentarios.setAdapter(adapter);

                    for (int i = 0; i < list.size(); i++) {
//                        Log.w("comentario", list.get(i).getNome());
                    }

                }

            }

            @Override
            public void onFailure(Call<RetornoWs> call, Throwable t) {
                Log.w("Publicacao ", t.getMessage());
            }
        });
    }

    //==============================================================================================
    //
    // Interface
    //
    //==============================================================================================

    private static RemoverFavorito removerFavorito;

    public static void setRetornoRemoverFavorito(RemoverFavorito retornoRemoverFavorito) {
        removerFavorito = retornoRemoverFavorito;
    }

    public interface RemoverFavorito {
        void removendoFavorito();
    }

}
