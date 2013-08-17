package com.example.manada4;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JsonToArray {
	
	public String json_array;
	JSONObject jObj;
	// JSON Node names
	public static final String TAG_TIPO = "tipo";
	public static final String TAG_RESULTADO= "resultado";
	public static final String TAG_DESCRIPCION = "descripcion";
	public static final String TAG_DATA = "data";
	public static final String TAG_ID_INCIDENTE = "id_incidente";
	public static final String TAG_LIST_GUARDIANES = "listado_guardianes";
	public static final String TAG_ID_USUARIO = "id_usuario";
	public static final String TAG_ALIAS = "alias";
	public static final String TAG_LATITUD = "latitud";
	public static final String TAG_LONGITUD = "longitud";
	public static final String TAG_TIPO_AMIGO = "tipo_amigo";
	public static final String TAG_FOTO = "imageView_imagen";
	
	//Arreglo que guarda información de contactos y comunidad temporal.
	public String[][] guardianes=new String[10][10];

	// contacts JSONArray
	JSONArray listado_guardianes = null;

	public JsonToArray(){}

	
	/**
	 * Carga los eventos en y los guarda en la lista.
	 * @return
	 */
	public ArrayList<HashMap<String, String>> recJson(){
		
		  json_array=null;
		  json_array="{'tipo': 'MSG_PANICO_ALARMA',";
		  json_array=json_array+"'resultado':'0',";
		  json_array=json_array+"'descripcion':'Exito',";
		  json_array=json_array+"'data':{";
		  json_array=json_array+"'id_incidente':'1',";
		  json_array=json_array+"'listado_guardianes':[";
		  json_array=json_array+"{'id_usuario':'5' , 'alias':'dcespedes', 'latitud':'-33.453872', 'longitud':'-70.678011', 'tipo_amigo':'CONTACTO_TEMPORAL' },";
		  json_array=json_array+"{ 'id_usuario':'6' , 'alias':'dunamuno', 'latitud':'-33.521254', 'longitud':'-70.671422', 'tipo_amigo':'CONTACTO_COMUNIDAD' },";
		  json_array=json_array+"{ 'id_usuario':'3' , 'alias':'jtapia', 'latitud':'-33.575702', 'longitud':'-70.645316', 'tipo_amigo':'CONTACTO_DIRECTO' },";
		  json_array=json_array+"{ 'id_usuario':'2' , 'alias':'mzumaeta', 'latitud':'-33.453518', 'longitud':'-70.609959', 'tipo_amigo':'CONTACTO_DIRECTO' },";
		  json_array=json_array+"{ 'id_usuario':'4' , 'alias':'elarrain', 'latitud':'-33.382148', 'longitud':'-70.553732', 'tipo_amigo':'CONTACTO_DIRECTO' }";
		  json_array=json_array+"]";
		  json_array=json_array+"}";
		  json_array=json_array+"}";
		  
			
		  
		  //DE String a JsonOnject
		  try {
				jObj = new JSONObject(json_array);
			} catch (JSONException e) {
				Log.e("Manada 3", "Error parsing data " + e.toString());
			}
		  
		//hashmap to listview.
		  ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
		 
		  //Pasando datos de Json a texto.
		  try {
				// Getting Array of Contacts
			  	String mnd_tipo=jObj.getString(TAG_TIPO);
			  	String mnd_resultado = jObj.getString(TAG_RESULTADO);
				String mnd_descripcion = jObj.getString(TAG_DESCRIPCION);
			  	
				// Data is JSON Object
				JSONObject mnd_data = jObj.getJSONObject(TAG_DATA);
				String mnd_id_incidente= mnd_data.getString(TAG_ID_INCIDENTE);
				
				listado_guardianes = mnd_data.getJSONArray(TAG_LIST_GUARDIANES);
				String[] ar_alias=new String[listado_guardianes.length()];
				String[] ar_latitud=new String[listado_guardianes.length()];
				String[] ar_longitud=new String[listado_guardianes.length()];
				// looping through All Contacts
				for(int i = 0; i < listado_guardianes.length(); i++){
					JSONObject c = listado_guardianes.getJSONObject(i);
					
					String mnd_id_usuario= c.getString(TAG_ID_USUARIO);
					String mnd_alias= c.getString(TAG_ALIAS);
					String mnd_resp_latitud= c.getString(TAG_LATITUD);
					String mnd_resp_longitud= c.getString(TAG_LONGITUD);
					String mnd_tipo_amigo= c.getString(TAG_TIPO_AMIGO);
					ar_alias[i]=mnd_alias;
					ar_latitud[i]=mnd_resp_latitud;
					ar_longitud[i]=mnd_resp_longitud;
				}
					// creating new HashMap
			  	HashMap<String, String> map = new HashMap<String, String>();
					
					// adding each child node to HashMap key => value
			  	
		  		map.put(TAG_FOTO, Integer.toString(R.drawable.peligro));
				map.put(TAG_RESULTADO,mnd_resultado);
				
				//String[] arr=new String[mnd_id_usuario.size()];
				

				Log.i("Manada3","datos :"+ar_alias[2]+ar_latitud[2]+ar_longitud[2]);
				
				
				
				
				// adding HashList to ArrayList
				contactList.add(map);
					
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		  
		  return contactList;

		
	}
	
	public String[][] msg_panico_alarma(String msg){
	
		 //DE String a JsonOnject
		  try {
				jObj = new JSONObject(msg);
			} catch (JSONException e) {
				Log.e("Manada 3", "Error parsing data " + e.toString());
			}
		  
		//hashmap to listview.
		 // ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
		 
		  //Pasando datos de Json a texto.
		  try {
				// Getting Array of Contacts
			  	String mnd_tipo=jObj.getString(TAG_TIPO);
			  	String mnd_resultado = jObj.getString(TAG_RESULTADO);
				String mnd_descripcion = jObj.getString(TAG_DESCRIPCION);
			  	
				// Data is JSON Object
				JSONObject mnd_data = jObj.getJSONObject(TAG_DATA);
				String mnd_id_incidente= mnd_data.getString(TAG_ID_INCIDENTE);
				
				listado_guardianes = mnd_data.getJSONArray(TAG_LIST_GUARDIANES);
				String[] ar_alias=new String[listado_guardianes.length()];
				String[] ar_latitud=new String[listado_guardianes.length()];
				String[] ar_longitud=new String[listado_guardianes.length()];
				// looping through All Contacts
				for(int i = 0; i < listado_guardianes.length(); i++){
					JSONObject c = listado_guardianes.getJSONObject(i);
					
					String mnd_id_usuario= c.getString(TAG_ID_USUARIO);
					String mnd_alias= c.getString(TAG_ALIAS);
					String mnd_resp_latitud= c.getString(TAG_LATITUD);
					String mnd_resp_longitud= c.getString(TAG_LONGITUD);
					String mnd_tipo_amigo= c.getString(TAG_TIPO_AMIGO);
					ar_alias[i]=mnd_alias;
					ar_latitud[i]=mnd_resp_latitud;
					ar_longitud[i]=mnd_resp_longitud;
				}
				
				for(int ss=0;ss<ar_alias.length;ss++){
						guardianes[ss][0]=ar_alias[ss];
						guardianes[ss][1]=ar_latitud[ss];
						guardianes[ss][2]=ar_longitud[ss];
				}
				
				
				
					// creating new HashMap
			  	//HashMap<String, String> map = new HashMap<String, String>();
					
					// adding each child node to HashMap key => value
			  	
		  		//map.put(TAG_FOTO, Integer.toString(R.drawable.peligro));
				//map.put(TAG_RESULTADO,mnd_resultado);
				
				//String[] arr=new String[mnd_id_usuario.size()];
				

				//Log.i("Manada3","datos :"+ar_alias[2]+ar_latitud[2]+ar_longitud[2]);
				
				
				
				
				// adding HashList to ArrayList
				//contactList.add(map);
					
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		  	return guardianes;
		  
		  //return contactList;

		
	}	

	
	
	
}
