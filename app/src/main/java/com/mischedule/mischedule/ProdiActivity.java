package com.mischedule.mischedule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class ProdiActivity extends AppCompatActivity {
    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodi);
        pdfView = findViewById(R.id.pdfView);

        pdfView.fromAsset("prodi.pdf")
                .enableSwipe(true)
                .load();
    }
}
