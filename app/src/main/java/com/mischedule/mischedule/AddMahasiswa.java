package com.mischedule.mischedule;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AddMahasiswa extends AppCompatActivity {

    Intent dataResult = new Intent();

    ArrayList<String> data_jadwal;
    ArrayAdapter<String> adapter_data_jadwal;
    ListView listjdwl;

    EditText ed_nim;
    EditText ed_nama;
    EditText ed_prodi;
    EditText ed_kelas;
    EditText ed_status;

    FloatingActionButton add_jdwl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mahasiswa);

        data_jadwal = new ArrayList<String>();
        adapter_data_jadwal = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data_jadwal);
        listjdwl = (ListView)findViewById(R.id.list_jadwal);

        listjdwl.setAdapter(adapter_data_jadwal);

        ed_nim = (EditText)findViewById(R.id.ed_nim);
        ed_nama = (EditText)findViewById(R.id.ed_nama);
        ed_prodi = (EditText)findViewById(R.id.ed_prodi);
        ed_kelas = (EditText)findViewById(R.id.ed_kelas);
        ed_status = (EditText)findViewById(R.id.ed_status);


        add_jdwl = findViewById(R.id.btn_add_jadwal);

        add_jdwl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputJadwal();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_simpan){
            String jdwl = "";

            ed_nim = (EditText)findViewById(R.id.ed_nim);
            ed_nama = (EditText)findViewById(R.id.ed_nama);
            ed_prodi = (EditText)findViewById(R.id.ed_prodi);
            ed_kelas = (EditText)findViewById(R.id.ed_kelas);
            ed_status = (EditText)findViewById(R.id.ed_status);


            dataResult.putExtra("nim", ed_nim.getText().toString());
            dataResult.putExtra("nama", ed_nama.getText().toString());
            dataResult.putExtra("prodi", ed_prodi.getText().toString());
            dataResult.putExtra("kelas", ed_kelas.getText().toString());
            dataResult.putExtra("status", ed_status.getText().toString());
            for (String jadwal: data_jadwal) {
                jdwl+=jadwal+",";
            }
            dataResult.putExtra("jadwal", jdwl);


            setResult(RESULT_OK,dataResult);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void addjadwal(String jadwal){
        data_jadwal.add(jadwal);
        adapter_data_jadwal.notifyDataSetChanged();
    }


    public void inputJadwal(){
        LayoutInflater factory = LayoutInflater.from(this);
        final View add_jadwal = factory.inflate(R.layout.input_jadwal, null);

        int size = data_jadwal.size();

        final EditText ip_hari = (EditText) add_jadwal.findViewById(R.id.ed_hari);
        final EditText ip_jam = (EditText) add_jadwal.findViewById(R.id.ed_jam);
        final EditText ip_matkul = (EditText) add_jadwal.findViewById(R.id.ed_matkul);
        final EditText ip_ruang = (EditText) add_jadwal.findViewById(R.id.ed_kelas);
        final AlertDialog.Builder input_jadwal = new AlertDialog.Builder(this);

        String hari = "";
        switch (size){
            case 0:
                hari="Senin";
                break;
            case 1:
                hari="Selasa";
                break;
            case 2:
                hari="Rabu";
                break;
            case 3:
                hari="Kamis";
                break;
            case 4:
                hari="Jumat";
                break;
            case 5:
                hari="Sabtu";
                add_jdwl.hide();
                break;
        }

        ip_hari.setText(hari, TextView.BufferType.EDITABLE);
        ip_hari.setEnabled(false);

        input_jadwal.setIcon(android.R.drawable.ic_input_add).setTitle("Input Jadwal")
                .setView(add_jadwal).setPositiveButton("SIMPAN",
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String result = ip_hari.getText().toString() + " : " + ip_jam.getText().toString()
                                + " " + ip_matkul.getText().toString() + " " + ip_ruang.getText().toString();
                        addjadwal(result);
                    }
                }
        ).setNegativeButton("BATAL",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });
        input_jadwal.show();
    }
}
