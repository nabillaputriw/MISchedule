package com.mischedule.mischedule;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mischedule.mischedule.Mahasiswa;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetailMahasiswa extends AppCompatActivity {

    Mahasiswa mhs;

    ArrayList<String> data_jadwal;
    ArrayAdapter<String> adapter_data_jadwal;
    ListView listjdwl;

    String[] hari = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu"};



    TextView txt_nim;
    TextView txt_nama;
    TextView txt_prodi;
    TextView txt_kelas;
    TextView txt_status;
    ImageView img;
    Uri imageUri;
    Button btn;
    private final int GET_FROM_GALLERY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mahasiswa);

        img = findViewById(R.id.image);
        btn = findViewById(R.id.btnupload);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {Intent gallery = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery, GET_FROM_GALLERY);};
        });
        String nim = getIntent().getStringExtra("nim");
        String nama = getIntent().getStringExtra("nama");
        String prodi = getIntent().getStringExtra("prodi");
        String kelas = getIntent().getStringExtra("kelas");
        String status = getIntent().getStringExtra("status");
        String jdwl = getIntent().getStringExtra("jadwal");
        List<String> data_jdwl = Arrays.asList(jdwl.split(","));
        mhs = new Mahasiswa(nim,nama,prodi,kelas,status);
        mhs.setJadwal(data_jdwl);


        data_jadwal = new ArrayList<String>();
        adapter_data_jadwal = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data_jadwal);
        listjdwl = (ListView) findViewById(R.id.list_jadwal_detail);

        listjdwl.setAdapter(adapter_data_jadwal);


        for(String jadwalin : mhs.getJadwal()){
            data_jadwal.add(jadwalin);
        }

        int size = data_jadwal.size();

        for(int i = size; i < 6; i++){
            data_jadwal.add(hari[i]+" : ");
        }

        txt_nim = (TextView) findViewById(R.id.text_nim);
        txt_nama = (TextView) findViewById(R.id.text_nama);
        txt_prodi = (TextView) findViewById(R.id.text_prodi);
        txt_kelas = (TextView) findViewById(R.id.text_kelas);
        txt_status = (TextView) findViewById(R.id.text_status);

        setView();
    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, GET_FROM_GALLERY);
    }
    public void setView(){
        txt_nim.setText(mhs.getNim());
        txt_nama.setText(mhs.getNama());
        txt_prodi.setText(mhs.getProdi());
        txt_kelas.setText(mhs.getKelas());
        txt_status.setText(mhs.getStatus());

        adapter_data_jadwal.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            imageUri = data.getData();
            img.setImageURI(imageUri);
        }
    }
}
