package com.mischedule.mischedule;

import android.graphics.Bitmap;
import java.util.List;public class Mahasiswa {
    private Bitmap foto;
    private String nim;
    private String nama;
    private String prodi;
    private String kelas;
    private String status;
    private List<String> jadwal;


    public Mahasiswa(){};

    public Mahasiswa(String nim, String nama, String prodi, String kelas, String status) {
        this.nim = nim;
        this.nama = nama;
        this.prodi = prodi;
        this.kelas = kelas;
        this.status = status;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getJadwal() {
        return jadwal;
    }

    public void setJadwal(List<String> jadwal) {
        this.jadwal = jadwal;
    }
}

