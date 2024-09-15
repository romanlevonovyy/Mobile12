package com.vitalik.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;

public class EquipmentDetailsActivity extends AppCompatActivity {

    private TextView madename;
    private TextView description;
    private TextView atmotusk;
    private TextView vologist;
    private TextView location;
    private TextView datastart;
    private TextView napryamok;
    private TextView speedviter;
    private TextView dataservice;
    private TextView temperaturel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_details);

        getIncomingIntent();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("madename") &&
                getIntent().hasExtra("description") &&
                getIntent().hasExtra("atmotusk") &&
                getIntent().hasExtra("vologist") &&
                getIntent().hasExtra("location") &&
                getIntent().hasExtra("datastart") &&
                getIntent().hasExtra("napryamok") &&
                getIntent().hasExtra("speedviter") &&
                getIntent().hasExtra("dataservice") &&
                getIntent().hasExtra("temperaturel")) {
            String madenameInfo = getIntent().getStringExtra("madename");
            String descriptionInfo = getIntent().getStringExtra("description");
            String atmotuskInfo = getIntent().getStringExtra("atmotusk");
            String vologistInfo = getIntent().getStringExtra("vologist");
            String locationInfo = getIntent().getStringExtra("location");
            String datastartInfo = getIntent().getStringExtra("datastart");
            String napryamokInfo = getIntent().getStringExtra("napryamok");
            String speedviterInfo = getIntent().getStringExtra("speedviter");
            String dataserviceInfo = getIntent().getStringExtra("dataservice");
            String temperaturelInfo = getIntent().getStringExtra("temperaturel");

            setInfo(madenameInfo, descriptionInfo, atmotuskInfo, vologistInfo, locationInfo, datastartInfo,napryamokInfo, speedviterInfo,dataserviceInfo,temperaturelInfo);
        }
    }

    private void initViews(){
        madename = findViewById(R.id.equipment_details_madename);
        description = findViewById(R.id.equipment_details_description);
        atmotusk = findViewById(R.id.equipment_details_atmotusk);
        vologist = findViewById(R.id.equipment_details_vologist);
        location = findViewById(R.id.equipment_details_location);
        datastart = findViewById(R.id.equipment_details_datastart);
        napryamok = findViewById(R.id.equipment_details_napryamok);
        speedviter = findViewById(R.id.equipment_details_speedviter);
        dataservice = findViewById(R.id.equipment_details_dataservice);
        temperaturel = findViewById(R.id.equipment_details_temperaturel);

    }

    private void setInfo(String equipmentTypeInfo, String descriptionInfo, String atmotuskInfo, String vologistInfo, String locationInfo, String datastartInfo, String napryamokInfo, String speedviterInfo, String dataserviceInfo, String temperaturelInfo) {
        initViews();
        madename.setText(equipmentTypeInfo);
        description.setText(descriptionInfo);
        atmotusk.setText(atmotuskInfo);
        vologist.setText(vologistInfo);
        location.setText(locationInfo);
        datastart.setText(datastartInfo);
        napryamok.setText(napryamokInfo);
        speedviter.setText(speedviterInfo);
        dataservice.setText(dataserviceInfo);
        temperaturel.setText(temperaturelInfo);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(EquipmentDetailsActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
