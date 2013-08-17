package com.example.manada4;



import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Menu;

public class MapaActivity extends android.support.v4.app.FragmentActivity {

	private GoogleMap mMap;	
	CameraUpdate cam;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mapa);
		
		Log.e("Manada3","Largo de guardians "+GpsMap.guardians.length);
		for(int ww=0;ww<GpsMap.guardians.length;ww++){
			
		}
		
		 //mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
	    mMap = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
	    
	    mMap.addMarker(new MarkerOptions()
	    .position(new LatLng(-33.3887, -70.5416))
	    .title("Casa")
	    //.icon(BitmapDescriptorFactory.fromAsset("ic_launcher.png")));
	    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))); //Colors del icono
	    
	    LatLng user1 = new LatLng(-33.3887, -70.5416);
	    CameraPosition camPos = new CameraPosition.Builder()
	            .target(user1)   //Centramos el mapa en Madrid
	            .zoom(15)         //Establecemos el zoom en 19
	            .bearing(0)      //Establecemos la orientaci�n con el noreste arriba
	            .build();
	     
	    CameraUpdate camUpd3 =
	        CameraUpdateFactory.newCameraPosition(camPos);
	     
	    mMap.animateCamera(camUpd3);
	}
	


}
