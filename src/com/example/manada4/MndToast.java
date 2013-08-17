package com.example.manada4;


import android.content.Context;
import android.widget.Toast;

public class MndToast{
	
	//Constructor
	public MndToast(){
		
	}
	public void MostrarToast(Context context,String t_msg){
		Toast toast1 =
	            Toast.makeText(context,
	            		t_msg, Toast.LENGTH_SHORT);
	 
	        toast1.show();
	}

	

}
