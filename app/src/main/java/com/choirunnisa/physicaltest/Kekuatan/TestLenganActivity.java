package com.choirunnisa.physicaltest.Kekuatan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.choirunnisa.physicaltest.AdapterApps.BegAdapter;
import com.choirunnisa.physicaltest.AdapterApps.FitnessAdapter;
import com.choirunnisa.physicaltest.HasilActivity;
import com.choirunnisa.physicaltest.Kelincahan.TestAgilityActivity;
import com.choirunnisa.physicaltest.ModelApps.AgiltyModel;
import com.choirunnisa.physicaltest.ModelApps.AgiltyModel;
import com.choirunnisa.physicaltest.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.ArrayList;
import java.util.List;

public class TestLenganActivity extends AppCompatActivity {
    private ActionBar actionBar;

    Button buttonSave;
    //    String gender;

    RadioButton rb_laki, rb_perempuan;
    String str;
    String hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_lengan);
        buttonSave = findViewById(R.id.proses);
        rb_laki = findViewById(R.id.laki);
        rb_perempuan = findViewById(R.id.perempuan);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Test Kekuatan Punggung");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewDeliveryProductList);

        BegAdapter adapter = new BegAdapter(getPunggungList());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editNama = (EditText) findViewById(R.id.nama);
                String opName = editNama.getText().toString();
                EditText editJenis = (EditText) findViewById(R.id.jenis);
                String opJenis = editJenis.getText().toString();
                EditText editinput = (EditText) findViewById(R.id.input);
                String opInput = editinput.getText().toString();
                RadioGroup rb_fitness = findViewById(R.id.jk);

                if (opName.equals("") || opInput.length() == 0 || rb_fitness.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Mohon Lengkapi Terlebih Dahulu",
                            Toast.LENGTH_SHORT).show();
                } else {


                    double input = Double.parseDouble(opInput);

                    if (rb_laki.isChecked()) {
                        str = "Laki-laki";
                        if (input > 153.50) {
                            hasil = "Baik Sekali";
                        } else if (input == 112.50 || input >= 153) {
                            hasil = "Baik";
                        } else if (input == 76.50 || input >= 112) {
                            hasil = "Sedang";
                        } else if (input == 52.6 || input >= 76) {
                            hasil = "Kurang";
                        } else if (input <= 52) {
                            hasil = "Kurang Sekali";
                        }

                    } else if (rb_perempuan.isChecked()) {
                        str = "Perempuan";
                        if (input > 103.50) {
                            hasil = "Baik Sekali";
                        } else if (input == 78.50 || input >= 103) {
                            hasil = "Baik";
                        } else if (input == 57.50 || input >= 78) {
                            hasil = "Sedang";
                        } else if (input == 28.50 || input >= 57) {
                            hasil = "Kurang";
                        } else if (input <= 28) {
                            hasil = "Kurang Sekali";
                        }
                    }

                    Intent intent = new Intent(TestLenganActivity.this, HasilActivity.class);

                    intent.putExtra("nama", opName);
                    intent.putExtra("jenis", opJenis);
                    intent.putExtra("input", opInput);
                    intent.putExtra("hasil", hasil);
                    intent.putExtra("jenis_kelamin", str);
                    startActivity(intent);
                }
            }
        });


    }

    private List getPunggungList() {
        List punggungList = new ArrayList<>();

        punggungList.add(new AgiltyModel(1, "Kurang Sekali", "<=52","<=28"));
        punggungList.add(new AgiltyModel(2, "Kurang", "52.5-76","28.5-57"));
        punggungList.add(new AgiltyModel(3, "Sedang", "76.5-112","57.5-78"));
        punggungList.add(new AgiltyModel(4, "Baik", "112.5-153","78.5-103"));
        punggungList.add(new AgiltyModel(5, "Baik Sekali", ">=153.5",">=102.5"));

        return punggungList;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
