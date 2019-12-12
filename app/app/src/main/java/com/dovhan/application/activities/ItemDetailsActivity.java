package com.dovhan.application.activities;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.dovhan.application.R;
import com.dovhan.application.adapters.ItemAdapter;
import com.dovhan.application.api.FoodMachineApi;
import com.dovhan.application.entities.FoodMachine;
import com.dovhan.application.utils.ApplicationEx;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDetailsActivity extends AppCompatActivity {
    private static final int TARGET_WIDTH = 100;
    private static final int TARGET_HEIGHT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.machine_details));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        displayVendingFromIntent();
    }

    private void displayVendingFromIntent() {
        if (getIntent().hasExtra("vending_name") &&
                getIntent().hasExtra("vending_company") &&
                getIntent().hasExtra("vending_goods") &&
                getIntent().hasExtra("vending_address") &&
                getIntent().hasExtra("vending_img_url")) {
            String vendingName = getIntent().getStringExtra("vending_name");
            String vendingGoods = getIntent().getStringExtra("vending_goods");
            String vendingAddress = getIntent().getStringExtra("vending_address");
            String vendingCompany = getIntent().getStringExtra("vending_company");
            String imageName = getIntent().getStringExtra("vending_img_url");
            String message = (getIntent().hasExtra("message")) ? getIntent().getStringExtra("message") : "No message";

            setupFields(vendingName, vendingGoods, vendingAddress, vendingCompany, imageName, message);
        }
    }

    private void setupFields(String vendingName, String vendingGoods, String vendingAddress,
                             String vendingCompany, String imageName, String message) {
        TextView name = findViewById(R.id.ven_name_detailed);
        TextView goods = findViewById(R.id.ven_goods_detailed);
        TextView address = findViewById(R.id.ven_address_detailed);
        TextView company = findViewById(R.id.ven_company_detailed);
        ImageView imageView = findViewById(R.id.ven_img_detailed);
        Picasso.get()
                .load(imageName)
                .placeholder(R.drawable.vending_placeholder)
                .resize(TARGET_WIDTH, TARGET_HEIGHT)
                .into(imageView);
        name.setText(vendingName);
        goods.setText(vendingGoods);
        address.setText(vendingAddress);
        company.setText(vendingCompany);
        showMessage(message);
    }

    private void showMessage(final String message) {
        final TextView editText = new TextView(getApplicationContext());
        AlertDialog dialog = new AlertDialog.Builder(getApplicationContext())
                .setTitle("Received message")
                .setMessage(message)
                .setView(editText)
                .setNegativeButton("Close", null)
                .create();
        dialog.show();
    }
}
