package com.dovhan.application.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.dovhan.application.R;
import com.dovhan.application.activities.auth.SignInActivity;
import com.dovhan.application.adapters.TabsAdapter;
import com.dovhan.application.utils.ApplicationEx;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private ViewPager viewPager;
    private TabsAdapter tabsAdapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_tabs);
        setupViews();
    }

    private void setupViews(){
        viewPager = findViewById(R.id.viewpager);
        tabsAdapter = new TabsAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(tabsAdapter);
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        progressBar = findViewById(R.id.progress_bar_main);
        mAuth = getApplicationEx().getAuth();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signOut();
        progressBar.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sign_out_menu, menu);
        return true;
    }
    private ApplicationEx getApplicationEx() {
        return ((ApplicationEx) getApplication());
    }

}