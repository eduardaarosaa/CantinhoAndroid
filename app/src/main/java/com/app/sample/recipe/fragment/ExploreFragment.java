package com.app.sample.recipe.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.sample.recipe.ActivityMain;
import com.app.sample.recipe.ActivityRecipeDetails;
import com.app.sample.recipe.Conexao.Api;
import com.app.sample.recipe.Conexao.Models.Publicacoes;
import com.app.sample.recipe.Conexao.Models.RetornoWs;
import com.app.sample.recipe.Conexao.RestClient;
import com.app.sample.recipe.R;
import com.app.sample.recipe.adapter.RecipeGridAdapter;
import com.app.sample.recipe.data.Tools;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExploreFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private RecipeGridAdapter mAdapter;
    private LinearLayout lyt_notfound;
    private SearchView searchView;
    private List<Publicacoes> publicacoes;
    private SwipeRefreshLayout refresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_explore, null);
        // activate fragment menu
        setHasOptionsMenu(true);

        refresh = view.findViewById(R.id.refresh);
        refresh.setColorSchemeColors(getActivity().getResources().getColor(R.color.colorPrimary));
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                iniciaLista();
            }
        });

        iniciaLista();

        return view;


    }

    private void setItemsVisibility(Menu menu, MenuItem exception, boolean visible) {
        for (int i = 0; i < menu.size(); ++i) {
            MenuItem item = menu.getItem(i);
            if (item != exception) item.setVisible(visible);
        }
    }

    @Override
    public void onResume() {
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
        super.onResume();
    }

    private void iniciaLista() {
        Api api = RestClient.getClient(getActivity()).create(Api.class);
        Call<RetornoWs> Conexao = api.RetornaPublic();

        Conexao.enqueue(new Callback<RetornoWs>() {
            @Override
            public void onResponse(Call<RetornoWs> call, Response<RetornoWs> response) {

                if (response.body().getStatus().equals("1")) {
                    publicacoes = response.body().getPublicacoes();

//                    for (int i = 0; i < publicacoes.size(); i++) {
//                        Log.w("Publicacao " + i, publicacoes.get(i).getTitulo());
//                    }

                    recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
                    lyt_notfound = (LinearLayout) view.findViewById(R.id.lyt_notfound);
                    LinearLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), Tools.getGridSpanCount(getActivity()));
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    mAdapter = new RecipeGridAdapter(getActivity(), publicacoes);
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.setOnItemClickListener(new RecipeGridAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View v, Publicacoes obj, int position) {
                            ActivityRecipeDetails.navigate((ActivityMain) getActivity(), v.findViewById(R.id.image), obj);
                        }
                    });

//
                } else {
                    Toast.makeText(getActivity(), response.body().getRetorno(), Toast.LENGTH_SHORT).show();
                }

                refresh.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<RetornoWs> call, Throwable t) {
                Log.w("Publicacao ", t.getMessage());
                refresh.setRefreshing(false);
            }
        });
    }

}
