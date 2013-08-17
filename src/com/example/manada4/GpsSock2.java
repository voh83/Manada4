package com.example.manada4;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;


public class GpsSock2 extends Service {
	public LocationManager mLocationManager=(LocationManager)MainActivity.context.getSystemService(Context.LOCATION_SERVICE);
	boolean gpsEn;
	public Location locLatitud;
	public static String locLatitud2;
	public static String locLongitud2;
	public LocationListener loclistener;
	String resultado;
	
	
	@Override
	  public void onCreate() {
		LocationManager mLocationManager=(LocationManager)MainActivity.context.getSystemService(Context.LOCATION_SERVICE);
	  }

	@Override
	  public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("Manada3", "Service Start");
		startLoggingService();
	      return START_NOT_STICKY;
	  }
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	  public void onDestroy() {
		Log.i("Manada3", "Servicio Destruido");
	  }
	

	//Comienza servicio de GPS.
	private void startLoggingService() {
		
		if(mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)==true){
			
			//skt.MostrarToast(MainActivity.context, "GPS Habilitado...");
			Log.i("Manada3", "GPS habilitado");
			gpsEn=true;
			
		}else{
			Log.i("Manada3", "GPS Deshabilitado");
			gpsEn=false;
		}
		
		if(gpsEn==true){
			Log.i("Manada3","gpsEng = true");
			
			try{
				locLatitud=mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			}catch(SecurityException e){
				Log.i("Manada3","Security Excepction"+e);
			}catch(IllegalArgumentException e){
				Log.i("Manada3","IAE"+e);
			}
			//mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, loclistener);
			
			loclistener=new LocationListener() {
				
				@Override
				public void onLocationChanged(Location location) {
					// TODO Auto-generated method stub
					Log.i("Manada3","onLocationChanged()");
					locLatitud2 = String.valueOf(location.getLatitude());
					locLongitud2 = String.valueOf(location.getLongitude());
					Log.i("Manada3", "Coordenadas 1:"+locLatitud2+" "+locLongitud2);
				}
	
	
				@Override
				public void onProviderDisabled(String arg0) {
					// TODO Auto-generated method stub
					Log.i("Manada3","Provider Disabled");
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
			mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, loclistener);
		}else{
			Log.i("Manada3","Error 300");
		}
		
	}

	


	


}
	

	


/*public class GpsSock extends Service{
	ApGPS gpscon;
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		//return super.onStartCommand(intent, flags, startId);
		gpscon=new ApGPS();
		gpscon.obCoord();
		return onStartCommand(intent,flags,startId);
	
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	
	
}*/

