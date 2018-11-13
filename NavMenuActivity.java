package com.example.user.melodiam.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.user.melodiam.R;

public class NavMenuActivity extends AppCompatActivity {

    private BottomNavigationView navMenu;
    private FrameLayout mainFrame;
    private android.support.v4.app.Fragment perfilFragment;
    private android.support.v4.app.Fragment listasFragment;
    private android.support.v4.app.Fragment albunsFragment;
    private android.support.v4.app.Fragment amigosFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_menu);
        this.inicializaComponentes();



        navMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.perfil_nav:
                        setFragment(perfilFragment);
                        return true;

                    case R.id.listas_nav:
                        setFragment(listasFragment);
                        return true;

                    case R.id.pesquisa_nav:
                        setFragment(albunsFragment);
                        return true;

                    case R.id.amigos_nav:
                        setFragment(amigosFragment);
                        return true;

                    default:
                        return false;
                }

            }



        });

    }

    public void inicializaComponentes() {
        this.navMenu = (BottomNavigationView) findViewById(R.id.nav_menu);
        this.mainFrame = (FrameLayout) findViewById(R.id.main_frame);
        this.perfilFragment = new PerfilFragment();
        this.listasFragment = new ListasFragment();
        this.albunsFragment = new PesquisaFragment();
        this.amigosFragment = new AmigosFragment();
    }
    private void setFragment(android.support.v4.app.Fragment fragment) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}
