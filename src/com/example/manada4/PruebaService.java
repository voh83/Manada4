package com.example.manada4;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Prueba Service modificacion de el de  prueba test
 */

import android.R.string;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

/**
 * Es GPS Service quedó con ese nombre.
 * @author Valentina
 *
 */
public class PruebaService extends Service {
	
	
	Boolean gpsEn;
	LocationListener loclistener;
	public double locLatitud2;
	public double locLongitud2;
	public Location locLatitud;
	private NotificationManager nm;
	LocationManager mLocationManager;
	
	   


    static final int MSG_REGISTER_CLIENT = 1;
    static final int MSG_UNREGISTER_CLIENT = 2;
    static final int MSG_SET_INT_VALUE = 3;
    static final int MSG_SET_STRING_VALUE = 4;
    static final int MSG_SET_RESP_A = 5;
    static final int MSG_SET_RESP_B = 6;
    
    final Messenger mMessenger = new Messenger(new IncomingHandler()); // Target we publish for clients to send messages to IncomingHandler.


    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
    class IncomingHandler extends Handler { // Handler of incoming messages from clients.
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case MSG_SET_RESP_A:
                //incrementby = msg.arg1;
            	Toast.makeText(getBaseContext(), "MSG_SET_RESP_A: "+MSG_SET_RESP_A,Toast.LENGTH_SHORT).show();
            	startLoggingService();
                break;
            case MSG_SET_RESP_B:
               // incrementby = msg.arg1;
            	Toast.makeText(getBaseContext(), "MSG_SET_RESP_B:"+MSG_SET_RESP_B,Toast.LENGTH_SHORT).show();
            	MainActivity.looperStatus=false;
            	
                break;
            default:
                super.handleMessage(msg);
            }
        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Manada3", "Service Started.");
        showNotification();
        //timer.scheduleAtFixedRate(new TimerTask(){ public void run() {onTimerTick();}}, 0, 100L);
        
    }
    private void showNotification() {
        nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        // In this sample, we'll use the same text for the ticker and the expanded notification
        CharSequence text = getText(R.string.service_started);
        // Set the icon, scrolling text and timestamp
        Notification notification = new Notification(R.drawable.advertencia, text, System.currentTimeMillis());
        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        // Set the info for the views that show in the notification panel.
        notification.setLatestEventInfo(this, getText(R.string.service_label), text, contentIntent);
        // Send the notification.
        // We use a layout id because it is a unique number.  We use it later to cancel.
        nm.notify(R.string.service_started, notification);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("Manada3", "Received start id " + startId + ": " + intent);
        return START_STICKY; // run until explicitly stopped.
    }


    @Override
    public void onDestroy() {
    	super.onDestroy();
        //if (timer != null) {timer.cancel();}
        nm.cancel(R.string.service_started); // Cancel the persistent notification.
        Log.i("Manada3", "Service Stopped.");
        
        
    }
    
    
    
//Comienza servicio de GPS.
	
	private void startLoggingService() {
		mLocationManager=(LocationManager)MainActivity.context.getSystemService(Context.LOCATION_SERVICE);
		Calendar calendar=Calendar.getInstance();
		final String hora=String.valueOf(calendar.get(Calendar.YEAR))+String.valueOf(calendar.get(Calendar.MONTH)+1)+String.valueOf(calendar.get(Calendar.DATE));
		Log.i("Manada3","La hora exacta "+hora);
		if(mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)==true){
			
			//skt.MostrarToast(MainActivity.context, "GPS Habilitado...");
			Log.i("Manada3", "GPS habilitado");
			gpsEn=true;
			MainActivity.looperStatus=true;
			
		}else{
			//skt.MostrarToast(MainActivity.context, "GPS Deshabilitado...");
			//skt.MostrarToast(MainActivity.context, "Favor habilitar GPS...");
			Log.i("Manada3", "GPS Deshabilitado");
			Toast.makeText(getBaseContext(), "GPS DESHABILITADO",Toast.LENGTH_SHORT).show();
			gpsEn=false;
			MainActivity.looperStatus=false;
		}
		
		if(gpsEn==true){
			
			locLatitud=mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			//Log.i("Manada3", "locLatitud:"+locLatitud);
			//mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, loclistener);
			
			loclistener=new LocationListener() {
				@Override
				public void onLocationChanged(Location location) {
					
					if(MainActivity.looperStatus==false){
						mLocationManager.removeUpdates(loclistener);
						MainActivity.looperStatus=false;
					}
					// TODO Auto-generated method stub
					Log.i("Manada3","GPSLoggerService.onLocationChanged()");
					locLatitud2 = location.getLatitude();
					locLongitud2 = location.getLongitude();
					Log.i("Manada3", "Coordenadas:"+locLatitud2+" "+locLongitud2);
					ApSocket soquet=new ApSocket();
					JsonClass jmsg=new JsonClass();
					try {
						String arr=soquet.execute("192.168.1.103","65455",jmsg.msg_posicion(1, locLatitud2, locLongitud2, hora)).get();
						Log.i("Manada3","Retorno del socket :"+arr);
						JsonToArray js=new JsonToArray();
						String[][] guardians=new String[2][2];
						if(arr!=null){
						guardians=js.msg_panico_alarma(arr);						
						}else{
							Log.i("Manada3","No hubo comunicacion del socket"+arr);
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						Log.e("Manada3","InterruptedException Return Asynctask fail :"+e);
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						Log.e("Manada3","ExecutionException Return Asynctask fail :"+e);
						e.printStackTrace();
					}
					//soquet.ans2.notify();
				}
	
	
				@Override
				public void onProviderDisabled(String arg0) {
					// TODO Auto-generated method stub
					Log.i("Manada3","Provider Disabled");
					stopSelf();
					//Looper.myLooper().quit();
					loclistener=null;
				}
	
	
				@Override
				public void onProviderEnabled(String arg0) {
					// TODO Auto-generated method stub
					Log.i("Manada3","Provider Enabled");
				}
	
	
				@Override
				public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
					// TODO Auto-generated method stub
					Log.i("Manada3","Status Changed");
				}

				
			};
			
			
			if(mLocationManager==null){
				mLocationManager=(LocationManager)MainActivity.context.getSystemService(Context.LOCATION_SERVICE);
			}
			mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15000, 0, loclistener,Looper.getMainLooper());
			
			locLatitud2 = locLatitud.getLatitude();
			locLongitud2 = locLatitud.getLongitude();
			Log.i("Manada3", "Coordenadas:"+locLatitud2+" "+locLongitud2);
			
		}else{
			Log.i("Manada3","Error 300");
		}
		
	}

}