package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.LinearLayout;
import com.google.android.material.snackbar.Snackbar;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WelcomeActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private LinearLayout linearLayout;
    private HospitalAdapter hospitalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initViews();
        registerNetworkMonitoring();
        showHospitaList();
    }

    private void registerNetworkMonitoring() {
        final IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        final NetworkChangeReceiver receiver = new NetworkChangeReceiver(linearLayout);
        this.registerReceiver(receiver, filter);
    }

    private void initViews(){
        recyclerView = findViewById(R.id.data_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        linearLayout = findViewById(R.id.main);
        swipeRefreshLayout = findViewById(R.id.data_list_swipe_refresh);
        updateHospitalData();
    }

    private  void showHospitaList(){
        final ApiService apiService = ((ApplicationEx) getApplication()).getApiService ();
        final Call<List<Hospital>> call = apiService.getHospitalData();

        call.enqueue(new Callback<List<Hospital>>() {
            @Override
            public void onResponse(final Call<List<Hospital>> call, final Response<List<Hospital>> response) {
                hospitalAdapter = new HospitalAdapter(response.body());
                recyclerView.setAdapter(hospitalAdapter);
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Hospital>> call, Throwable t) {
                Snackbar.make(linearLayout, getString(R.string.error_update), Snackbar.LENGTH_LONG).show();
            }
        });
    }
    private void updateHospitalData(){
        swipeRefreshLayout.setOnRefreshListener(() -> {
                    showHospitaList();
                    swipeRefreshLayout.setRefreshing(true);
                }
        );
    }
}