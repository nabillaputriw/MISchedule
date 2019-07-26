package com.mischedule.mischedule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class DosenActivity extends AppCompatActivity {
    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen);
        pdfView = findViewById(R.id.pdfView);

        pdfView.fromAsset("dosen.pdf")
                .enableSwipe(true)
                .load();
    }
}
