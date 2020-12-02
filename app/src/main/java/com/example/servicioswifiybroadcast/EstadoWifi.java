package com.example.servicioswifiybroadcast;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class EstadoWifi extends Service {
    boolean funcionando = false;
    CompruebaWifi compruebaWifi;

    public EstadoWifi() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        compruebaWifi = new CompruebaWifi();
    }

    @Override
    public void onDestroy() {
        compruebaWifi.activo=false;
        //compruebaWifi.interrupt();
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        compruebaWifi.start();

        return super.onStartCommand(intent, flags, startId);
    }

    public class CompruebaWifi extends Thread {

        boolean estadoAnterior = false;
        boolean activo =true;
        boolean hayWifi() {
            Context context;
            ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

            if (connectivityManager != null) {
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                if (networkInfo != null) {
                    if (networkInfo.isConnected()) return true;
                }
            }
            return false;
        }

        @Override
        public void run() {

            while(activo){

                funcionando = hayWifi();

                if (funcionando != estadoAnterior) {
                    estadoAnterior= funcionando;

                    Log.i("ESTADOWIFI",
                            funcionando?"Se ha activado la wifi":"Se ha desactivado la wifi");
                }

                try {
                    this.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Log.i("ESTADOWIFI", "Fin comprobar estado wifi");


        }
    }

}