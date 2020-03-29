package com.takatutustudio.appbiodata;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.takatutustudio.appbiodata.api.ApiRequestBiodata;
import com.takatutustudio.appbiodata.api.Retroserver;
import com.takatutustudio.appbiodata.model.ResponsModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        // ApiRequestBiodata
        final ApiRequestBiodata api = Retroserver.getClient().create(ApiRequestBiodata.class);

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

                // Mengirim data yang didapatkan dari inputan ke ApiRequestBiodata
                Call<ResponsModel> sendbio = api.sendBiodata(snama,susia,sdomisili);
                sendbio.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        pd.hide();
                        Log.d("RETRO",  "response : " + response.body().toString());
                        String kode = response.body().getKode();

                        if (kode.equals("1")) {
                            Toast.makeText(MainActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();

                            // Menghapus editText ketika data telah tersimpan ke database
                            edtNamaLengkap.getText().clear();
                            edtUsia.getText().clear();
                            edtDomisili.getText().clear();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Data Error tidak berhasil disimpan", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        pd.hide();
                        Log.d("RETRO", "Failure :" + "Gagal mengirim Request");
                    }
                });
            }
        });
    }
}
