package com.takatutustudio.appbiodata;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edtNamaLengkap, edtUsia, edtDomisili;
    Button   btnSave;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Deklarasi
        edtNamaLengkap     = findViewById(R.id.edt_nama_lengkap);
        edtUsia            = findViewById(R.id.edt_usia);
        edtDomisili        = findViewById(R.id.edt_domisili);
        btnSave            = findViewById(R.id.btn_save);

        pd  = new ProgressDialog(this);

        // Fungsi btnSave
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ProgressDialog
                pd.setMessage("Send data...");
                pd.setCancelable(false);
                pd.show();

                String snama     = edtNamaLengkap.getText().toString();
                String susia     = edtUsia.getText().toString();
                String sdomisili = edtDomisili.getText().toString();
            }
        });
    }
}
