package com.example.manada4;

import java.util.concurrent.ExecutionException;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

public class GpsMap {
	
	Boolean gpsEnab;
	LocationListener loclisten;
	public double locLat2;
	public double locLong2;
	public Location locLat;
	public Intent MapaIntent;
	public static String[][] guardians;
	
	
	
	
	public GpsMap() {}

	protected void consCoord() {
		// TODO Auto-generated method stub
		
		startLoggingService();
	}


//Comienza servicio de GPS.
	
	private void startLoggingService() {
		LocationManager mLocationManager=(LocationManager)MainActivity.context.getSystemService(Context.LOCATION_SERVICE);
		
		if(mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)==true){
			
			//skt.MostrarToast(MainActivity.context, "GPS Habilitado...");
			Log.i("Manada3", "GPS habilitado");
			gpsEnab=true;
			
		}else{
			//skt.MostrarToast(MainActivity.context, "GPS Deshabilitado...");
			//skt.MostrarToast(MainActivity.context, "Favor habilitar GPS...");
			Log.i("Manada3", "GPS Deshabilitado");
			gpsEnab=false;
		}
		
		if(gpsEnab==true){
			locLat=mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			//mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, loclistener);
			
			loclisten=new LocationListener() {
				
				@Override
				public void onLocationChanged(Location location) {
					
					// TODO Auto-generated method stub
					Log.i("Manada3","GPSLoggerService.onLocationChanged()");
					locLat2 = location.getLatitude();
					locLong2 = location.getLongitude();
					Log.i("Manada3", "Coordenadas:"+locLat2+" "+locLong2);
					ApSocket soquet=new ApSocket();
					JsonClass jmsg=new JsonClass();
					try {
						String arr=soquet.execute("192.168.1.101","65455",jmsg.msg_posicion(1, locLat2, locLong2, "13-06-2013")).get();
						Log.i("Manada3","Retorno del socket :"+arr);
						JsonToArray js=new JsonToArray();
						guardians=new String[2][2];
						guardians=js.msg_panico_alarma(arr);
						
						
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
					
					loclisten=null;
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
					Log.i("Manada3","Provider Disabled");
					Looper.myLooper().quit();
					
				}

				
			};
			
			
			if(mLocationManager==null){
				mLocationManager=(LocationManager)MainActivity.context.getSystemService(Context.LOCATION_SERVICE);
			}
			mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15000, 0, loclisten,Looper.getMainLooper());
			locLat2 = locLat.getLatitude();
			locLong2 = locLat.getLongitude();
			Log.i("Manada3", "Coordenadas:"+locLat2+" "+locLong2);
			
		}else{
			Log.i("Manada3","Error 300");
		}
		
	}

	
}
