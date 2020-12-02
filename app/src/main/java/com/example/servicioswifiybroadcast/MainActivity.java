package com.example.servicioswifiybroadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button playMusica, stopMusica, startCrono, stopCrono,startComprobarWifi, stopComprobarWifi;
private TextView textView;
OnBateriaCambia onBateriaCambia = new OnBateriaCambia();
void actualizarCronometro(String cadena){
    textView.setText(cadena);
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter intentFilter = new IntentFilter("android.intent.action.ACTION_POWER_CONNECTED");
        intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        intentFilter.addAction("android.intent.action.BATTERY_LOW");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");

        getBaseContext().registerReceiver(onBateriaCambia,intentFilter);


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



    class OnBateriaCambia extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)){
                Log.i("ESTADO", "Cable conectado");

            }else if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)){
                Log.i("ESTADO", "Cable desconectado");
                Toast.makeText(MainActivity.this, "Cable desconectado", Toast.LENGTH_SHORT).show();
            }else if(intent.getAction().equals(Intent.ACTION_BATTERY_LOW)){
                Log.i("ESTADO", "Batería baja");
            }else if(intent.getAction().equals("android.net.wifi.STATE_CHANGE")){
                Log.i("ESTADO", "Cambio wifi");
            }
        }
    }
}