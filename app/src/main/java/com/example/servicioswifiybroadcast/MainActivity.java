package com.example.servicioswifiybroadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button playMusica, stopMusica;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playMusica = findViewById(R.id.buttonPlay);
        stopMusica = findViewById(R.id.buttonStop);

    }
}