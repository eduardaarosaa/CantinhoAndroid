package com.app.sample.recipe;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sample.recipe.Conexao.Sessao;
import com.app.sample.recipe.Utils.ActivityUtil;
import com.app.sample.recipe.View.LoginActivity;
import com.app.sample.recipe.data.Constant;
import com.app.sample.recipe.fragment.ExploreFragment;
import com.app.sample.recipe.fragment.FavoritesFragment;
import com.app.sample.recipe.fragment.ReceitasFragment;
import com.app.sample.recipe.fragment.EditaFragment;


public class ActivityMain extends AppCompatActivity {

    private Toolbar toolbar;
    public ActionBar actionBar;
    private NavigationView navigationView;
    private Sessao sessao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessao = new Sessao(ActivityMain.this);

        initToolbar();
        initDrawerMenu();



        // set initial view
        displayFragment(R.id.nav_explore, getString(R.string.title_nav_explore));

        // for system bar in lollipop
//        Tools.systemBarLolipop(this);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }

    private void initDrawerMenu() {
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerOpened(View drawerView) {
                hideKeyboard();
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                menuItem.setChecked(true);
                updateDrawerCounter();
                displayFragment(menuItem.getItemId(), menuItem.getTitle().toString());
                drawer.closeDrawers();
                return true;
            }
        });
        View headerView = navigationView.getHeaderView(0);
        TextView nomeUsuario = headerView.findViewById(R.id.nome);
        TextView emailUsuario = headerView.findViewById(R.id.email);

        nomeUsuario.setText(sessao.getnomesessao());
        emailUsuario.setText(sessao.getemailsessao());
    }

    public void setFistFragment() {
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        fragment = new ExploreFragment();
        fragment.setArguments(bundle);

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_content, fragment);
            fragmentTransaction.commit();
        }
    }

    private void displayFragment(int id, String title) {
        actionBar.setDisplayShowCustomEnabled(false);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(title);

        Fragment fragment = null;
        Bundle bundle = new Bundle();
        switch (id) {
            case R.id.nav_explore:
                fragment = new ExploreFragment();
                fragment.setArguments(bundle);

                break;
            case R.id.nav_favorites:
                fragment = new FavoritesFragment();
                fragment.setArguments(bundle);

                break;

            case R.id.nav_receitas:
                fragment = new ReceitasFragment();
                fragment.setArguments(bundle);
                break;
            case R.id.nav_setting:
                Sessao sair = new Sessao(ActivityMain.this);
                sair.setdeslogado();
                ActivityUtil.callActivity(ActivityMain.this, LoginActivity.class, true);
                break;

            case R.id.nav_editar:
                fragment = new EditaFragment();
                fragment.setArguments(bundle);

                break;
        }


        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_content, fragment);
            fragmentTransaction.commit();
        }
    }

    private void updateDrawerCounter() {
        setMenuStdCounter(R.id.nav_favorites, Constant.getItemFavorites(this).size());
    }

    //set counter in drawer
    private void setMenuStdCounter(@IdRes int itemId, int count) {
        TextView view = (TextView) navigationView.getMenu().findItem(itemId).getActionView();
        view.setText(count > 0 ? String.valueOf(count) : null);
    }

    @Override
    protected void onResume() {
        updateDrawerCounter();
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            doExitApp();
        }
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private long exitTime = 0;

    public void doExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, "Precione mais uma vez para sair", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

}
