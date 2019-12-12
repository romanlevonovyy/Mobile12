package com.dovhan.application.fragments;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dovhan.application.R;
import com.dovhan.application.activities.auth.SignInActivity;
import com.dovhan.application.adapters.ItemAdapter;
import com.dovhan.application.api.FoodMachineApi;
import com.dovhan.application.entities.FoodMachine;
import com.dovhan.application.utils.ApplicationEx;
import com.dovhan.application.utils.ConnectionChangeReceiver;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataListFragment extends Fragment {
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private SwipeRefreshLayout refreshLayout;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private View content;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_main, container, false);

        setupViews(rootView);
        checkConnection();
        loadVendings();
        setSwipeToRefresh();
        return rootView;
    }

    private void checkConnection() {
        IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        ConnectionChangeReceiver receiver = new ConnectionChangeReceiver(content);
        Objects.requireNonNull(getActivity()).registerReceiver(receiver, filter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signOut();
        progressBar.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(getActivity(), SignInActivity.class);
        startActivity(intent);
        return true;
    }

    private void setSwipeToRefresh() {
        refreshLayout.setOnRefreshListener(() -> {
            loadVendings();
            new Handler().postDelayed(() -> refreshLayout.setRefreshing(false), 0);
        });
    }

    private void setupViews(View rootView) {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        refreshLayout = rootView.findViewById(R.id.swipe_refresher);
        progressBar = rootView.findViewById(R.id.progress_bar_main);
        content = rootView.findViewById(R.id.content_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAuth = getApplicationEx().getAuth();
    }

    private void loadVendings() {
        progressBar.setVisibility(View.VISIBLE);
        final FoodMachineApi apiService = getApplicationEx().getFoodMachineApi();
        final Call<List<FoodMachine>> call = apiService.getFoodMachines();

        call.enqueue(new Callback<List<FoodMachine>>() {
            @Override
            public void onResponse(final Call<List<FoodMachine>> call,
                                   final Response<List<FoodMachine>> response) {
                itemAdapter = new ItemAdapter(content.getContext(), response.body());
                recyclerView.setAdapter(itemAdapter);
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<FoodMachine>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private ApplicationEx getApplicationEx() {
        return ((ApplicationEx) Objects.requireNonNull(getActivity()).getApplication());
    }
}
