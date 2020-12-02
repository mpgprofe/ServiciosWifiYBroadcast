package com.example.servicioswifiybroadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
Button playMusica, stopMusica, startCrono, stopCrono,startComprobarWifi, stopComprobarWifi;
private TextView textView;

void actualizarCronometro(String cadena){
    textView.setText(cadena);
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playMusica = findViewById(R.id.buttonPlay);
        stopMusica = findViewById(R.id.buttonStop);
        startCrono = findViewById(R.id.buttonIniciarCrono);
        stopCrono = findViewById(R.id.buttonStopCrono);
        textView = findViewById(R.id.textViewCrono);
        startComprobarWifi = findViewById(R.id.buttonComprobarWifi);
        stopComprobarWifi = findViewById(R.id.buttonPararWifi);

        startComprobarWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(getBaseContext(), EstadoWifi.class));
            }
        });
        stopComprobarWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(getBaseContext(), EstadoWifi.class));
            }
        });




        ServicioCrono.setActividadPrincipal(this);




        stopCrono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(getBaseContext(), ServicioCrono.class));
            }
        });
        startCrono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(getBaseContext(), ServicioCrono.class));
            }
        });

        playMusica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(getBaseContext(), ServicioMusica.class));
            }
        });

        stopMusica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(getBaseContext(), ServicioMusica.class));
            }
        });

    }
}