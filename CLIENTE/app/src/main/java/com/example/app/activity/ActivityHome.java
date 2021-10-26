package com.example.app.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.app.R;
import com.example.app.adapter.ViewPagerAdapter;
import com.example.app.fragments.CarritoFragment;
import com.example.app.fragments.InicioFragment;
import com.example.app.fragments.MisComprasFragment;
import com.example.app.fragments.PerfilFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ActivityHome extends AppCompatActivity {
    private InicioFragment inicioFragment;
    private MisComprasFragment misComprasFragment;
    private PerfilFragment perfilFragment;
    private CarritoFragment carritoFragment;
    private BottomNavigationView navigationButton;
    private ViewPager viewPager;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        initListeners();
    }

    private void init(){
        navigationButton = findViewById(R.id.bnvMenu);
        toolbar = findViewById(R.id.toolbar);
        inicioFragment = new InicioFragment();
        misComprasFragment = new MisComprasFragment();
        perfilFragment = new PerfilFragment();
        carritoFragment = new CarritoFragment();

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(inicioFragment);
        fragmentList.add(misComprasFragment);
        fragmentList.add(perfilFragment);
        fragmentList.add(carritoFragment);

        viewPager = findViewById(R.id.viewPager);
        ViewPagerAdapter vpa = new ViewPagerAdapter //Instanciamos la clase
                (getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
                fragmentList);

        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(vpa);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                navigationButton.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
    private void initListeners() {
        navigationButton.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.optInicio:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.optMisCompras:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.optConfiguracion:
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.optCarrito:
                    viewPager.setCurrentItem(3);
            }
            return true;
        });
    }
}