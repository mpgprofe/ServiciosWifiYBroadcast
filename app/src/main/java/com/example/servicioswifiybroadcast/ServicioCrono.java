package com.example.servicioswifiybroadcast;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;

public class ServicioCrono extends Service {

    private Timer temporizador = new Timer();
    private int INTERVALO = 100; //En milisegundos
    static MainActivity principal;
    private double contador = 0;
    Handler handler;
    public ServicioCrono() {
    }

    public static void setActividadPrincipal (MainActivity esta){
        principal = esta;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                principal.actualizarCronometro(String.format("%.2f",contador));

            }
        };
        iniciarCrono();

    }

    private void iniciarCrono() {
        temporizador.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                contador+=0.01;
                handler.sendEmptyMessage(0);
            }
        }, 0, INTERVALO);
    }

    @Override
    public void onDestroy() {
        temporizador.cancel();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}