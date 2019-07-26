package com.mischedule.mischedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ListView listmhs;
    ArrayList<Mahasiswa> data_mhs;
    ArrayList<String> data_nama_mahasiswa;
    ArrayAdapter<String> adapter_nama_mahasiswa;
    Mahasiswa mhs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//setContentView(R.layout.activity_main);
        if(getResources().getDisplayMetrics().widthPixels>getResources().getDisplayMetrics().heightPixels) {
        }
        CardView cvdosen,cvprodi, cvakademik, cvmatakuliah;
        {


            cvdosen = findViewById(R.id.dosen);
            cvprodi = findViewById(R.id.prodi);
            cvakademik = findViewById(R.id.akademik);
            cvmatakuliah = findViewById(R.id.matakuliah);

            cvdosen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this,DosenActivity.class);
                    startActivity(i);
                }
            });

            cvprodi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent u = new Intent(MainActivity.this, ProdiActivity.class);
                    startActivity(u);
                }
            });

            cvakademik.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent u = new Intent(MainActivity.this, AkademikActivity.class);
                    startActivity(u);
                }
            });
            cvmatakuliah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent u = new Intent(MainActivity.this, MataKuliahActivity.class);
                    startActivity(u);
                }
            });

        }
        data_mhs = new ArrayList<Mahasiswa>();
        data_nama_mahasiswa = new ArrayList<String>();
        adapter_nama_mahasiswa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data_nama_mahasiswa);
        listmhs = (ListView)findViewById(R.id.list_mahasiswa);

        listmhs.setAdapter(adapter_nama_mahasiswa);

        FloatingActionButton fab = findViewById(R.id.btn_add_mahasiswa);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add_mhs = new Intent(MainActivity.this, AddMahasiswa.class);
                startActivityForResult(add_mhs, 100);
            }
        });



        listmhs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "" + data_mhs.get(position).getNama(), Toast.LENGTH_SHORT).show();
                Intent detailmhs = new Intent(MainActivity.this, DetailMahasiswa.class);
                detailmhs.putExtra("nim", data_mhs.get(position).getNim());
                detailmhs.putExtra("nama", data_mhs.get(position).getNama());
                detailmhs.putExtra("prodi", data_mhs.get(position).getProdi());
                detailmhs.putExtra("kelas", data_mhs.get(position).getKelas());
                detailmhs.putExtra("status", data_mhs.get(position).getStatus());
                String jdwl = "";
                for (String jadwal : data_mhs.get(position).getJadwal()){
                    jdwl+=jadwal+",";
                }
                detailmhs.putExtra("jadwal", jdwl);
                startActivity(detailmhs);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("DATA-RESULT", "request : "+requestCode+", resultcode : "+resultCode);
        if(requestCode == 100){
            if(resultCode == RESULT_OK){
                mhs = new Mahasiswa(
                        data.getStringExtra("nim"),
                        data.getStringExtra("nama"),
                        data.getStringExtra("prodi"),
                        data.getStringExtra("kelas"),
                        data.getStringExtra("status")
                );
                String jdwl = data.getStringExtra("jadwal");
                List<String> data_jdwl = Arrays.asList(jdwl.split(","));
                mhs.setJadwal(data_jdwl);
                data_mhs.add(mhs);
                addlist(mhs.getNim());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void addlist(String mhs){
        data_nama_mahasiswa.add(mhs);
        adapter_nama_mahasiswa.notifyDataSetChanged();
    }


};

