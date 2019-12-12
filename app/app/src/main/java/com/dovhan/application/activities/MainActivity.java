package com.dovhan.application.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.dovhan.application.R;
import com.dovhan.application.activities.auth.SignInActivity;
import com.dovhan.application.adapters.TabsAdapter;
import com.dovhan.application.api.FoodMachineApi;
import com.dovhan.application.entities.FoodMachine;
import com.dovhan.application.utils.ApplicationEx;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private ViewPager viewPager;
    private TabsAdapter tabsAdapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logFirebaseToken();

        if (getIntent().hasExtra("name")) {
            String name = getIntent().getStringExtra("name");
            String message = getIntent().getStringExtra("message");
            setContentView(R.layout.fragment_tabs);
            setupViews();
            loadSpecificFoodMachine(name, message);
        }  else {
            setContentView(R.layout.fragment_tabs);
            setupViews();
        }
    }

    private void logFirebaseToken() {
        final String TAG = "FCM Token";

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "getInstanceId failed", task.getException());
                        return;
                    }

                    String token = Objects.requireNonNull(task.getResult()).getToken();

                    Log.d(TAG, token);
                });
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

    private void loadSpecificFoodMachine(final String name, final String message) {
        final FoodMachineApi apiService = getApplicationEx().getFoodMachineApi();
        final Call<List<FoodMachine>> call = apiService.getFoodMachines();

        call.enqueue(new Callback<List<FoodMachine>>() {
            @Override
            public void onResponse(final Call<List<FoodMachine>> call,
                                   final Response<List<FoodMachine>> response) {
                try {
                    String responseBody = Objects.requireNonNull(response.body()).toString();

                    Type type = new TypeToken<List<FoodMachine>>() {
                    }.getType();
                    List<FoodMachine> arrayList = getFromJSONtoArrayList(responseBody, type);

                    for (FoodMachine foodMachine: arrayList) {
                        if (foodMachine.getName().equals(name)) {
                            Intent intent = new Intent(getApplicationContext(), ItemDetailsActivity.class);

                            intent.putExtra("vending_name", name);
                            intent.putExtra("message", message);
                            intent.putExtra("vending_company", foodMachine.getCompany());
                            intent.putExtra("vending_goods", foodMachine.getGood());
                            intent.putExtra("vending_address", foodMachine.getAddress());
                            intent.putExtra("vending_img_url", foodMachine.getPicture());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<FoodMachine>> call, Throwable t) {
            }
        });
    }

    private static boolean isValid(String jsonString) {
        try {
            new JsonParser().parse(jsonString);
            return true;
        } catch (JsonSyntaxException e) {
            return false;
        }
    }

    private static <T> List<T> getFromJSONtoArrayList(String jsonString, Type type) {
        if (!isValid(jsonString))
            return new ArrayList<>();
        return new Gson().fromJson(jsonString, type);
    }
}