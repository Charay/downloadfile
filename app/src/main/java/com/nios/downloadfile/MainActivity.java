package com.nios.downloadfile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nios.downloadfile.config.Constant;
import com.nios.downloadfilelib.utils.DownloadFileUtil;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DownloadFileUtil.downloadFileFromServer(Constant.baseUrl,Constant.fromUri,Constant.toDir,Constant.filename);
            }
        });
    }
}
