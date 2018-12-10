package com.example.rioir.fat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageButton floatButton;
    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Navigasi
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Tab
        tabLayout = findViewById(R.id.idtablayout);
        viewPager = findViewById(R.id.idviewpager);
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());

        //menambah fragment
        adapter.AddFragment(new FragmentHome(),"");
        adapter.AddFragment(new FragmentMaps(),"");
        adapter.AddFragment(new FragmentNotification(),"");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_white);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_location_white);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_notification_white);

        //float
        floatButton = findViewById(R.id.btn_insert);
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TambahData.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_dashboard:
                Intent i = new Intent(this.getApplicationContext(), MainActivity.class);
                startActivity(i);
                break;

//            case R.id.nav_admin:
//                Intent intent3 = new Intent(this.getApplicationContext(), Login.class);
//                startActivity(intent3);
//                break;

            case R.id.nav_logout:
                SharedPreferences handler = getSharedPreferences("Login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = handler.edit();
                editor.clear();
                editor.commit();
                finish();

                Intent intent2 = new Intent(this.getApplicationContext(), Login.class);
                startActivity(intent2);
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        new FragmentHome()).commit();
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (mToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

}
//    public void selectItemDrawer(MenuItem item) {
//
//        android.support.v4.app.Fragment myFragment = null;
//        Class fragmentclass = null;
//
//        switch (item.getItemId()){
//            case R.id.db:
//                fragmentclass = SecondActivity.class;
//                break;
//            case R.id.logout:
//                fragmentclass = SecondActivity.class;
//                break;
//            default:
//                fragmentclass = SecondActivity.class;
//        }
//        try{
//            myFragment = (android.support.v4.app.Fragment) fragmentclass.newInstance();
//        }  catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.fragment_container, myFragment).commit();
//        item.setChecked(true);
//        setTitle(item.getTitle());
//        mDrawerLayout.closeDrawers();
//    }
//
//    private void setupDrawerContent(NavigationView navigationView){
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                selectItemDrawer(item);
//                return true;
//            }
//        });
//    }

