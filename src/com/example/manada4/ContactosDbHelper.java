package com.example.manada4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class ContactosDbHelper extends SQLiteOpenHelper {
	 
	   private static int version = 1;
	   private static String name = "ContacDB" ;
	   private static CursorFactory factory = null;
	   SQLiteDatabase db;
	   static String id[]=new String[3];
	  static String nombre[] =new String[3];
	  static String telefono[]=new String[3];
	   
	 
	   public  ContactosDbHelper(Context context)
	   {
	      super(context, name, factory, version);
	   }
	 
	   public void onCreate( SQLiteDatabase db)
	   {
		   Log.i(this.getClass().toString(), "Creando base de datos");
		   
		   db.execSQL( "CREATE TABLE CONTACTOS(" +
		          " _id INTEGER PRIMARY KEY," +
		          " con_nombre TEXT NOT NULL, " +
		          " con_telefono TEXT)" 
		           );
		 
		   db.execSQL( "CREATE UNIQUE INDEX con_nombre ON CONTACTOS(con_nombre ASC)" );
		 
		   Log.i(this.getClass().toString(), "Tabla CONTACTOS creada");
		 
		   /*
		    * Insertamos datos iniciales
		    */
		   db.execSQL("INSERT INTO CONTACTOS(_id, con_nombre,con_telefono) VALUES(1,'ARDILES',''+56998244687)");
		   db.execSQL("INSERT INTO CONTACTOS(_id, con_nombre,con_telefono) VALUES(2,'JC',''+56989028415)");
		   db.execSQL("INSERT INTO CONTACTOS(_id, con_nombre,con_telefono) VALUES(3,'Juan Gomez',''+56996798394)");
		   /*db.execSQL("INSERT INTO HIPOTECA(_id, hip_nombre) VALUES(2,'BBVA')");
		   db.execSQL("INSERT INTO HIPOTECA(_id, hip_nombre) VALUES(3,'La Caixa')");
		   db.execSQL("INSERT INTO HIPOTECA(_id, hip_nombre) VALUES(4,'Cajamar')");
		   db.execSQL("INSERT INTO HIPOTECA(_id, hip_nombre) VALUES(5,'Bankia')");
		   db.execSQL("INSERT INTO HIPOTECA(_id, hip_nombre) VALUES(6,'Banco Sabadell')");
		   db.execSQL("INSERT INTO HIPOTECA(_id, hip_nombre) VALUES(7,'Banco Popular')");*/
		 
		   //Log.i(this.getClass().toString(), "Datos iniciales HIPOTECA insertados");
		 
		   Log.i(this.getClass().toString(), "Base de datos creada");
	 
	   }
	 
	   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	   {
		   
	 
	   }
	   
	   /**
	    * Carga datos de una tabla, permite guardar hasta 3 telefonos.
	    */
	
	public void cargar_datos_sql(SQLiteDatabase db,String TABLA){
		//Mejora: se puede mejorar consultando primero el numero de datos guardados.
			
		   int y=0;
		   Cursor fila=db.rawQuery("select * from "+TABLA, null);
		   while(fila.moveToNext()==true){
			   id[y]=fila.getString(0);
			   nombre[y]=fila.getString(1);
			   telefono[y]=fila.getString(2);
			   Log.i("Manada3",Integer.toString(y));
			   y=y+1;
		   }
		   //Se puede mejorar con ciclo for para carga de informacón.
		  Log.i("Manada3","Cursor llego al final "+id[2]+" "+nombre[2]+" "+telefono[2]);
		  Contactos.n_con_1.setText(nombre[0]);
		  Contactos.tel_con_1.setText(telefono[0]);
		  Contactos.n_con_2.setText(nombre[1]);
		  Contactos.tel_con_2.setText(telefono[1]);
		  Contactos.n_con_3.setText(nombre[2]);
		  Contactos.tel_con_3.setText(telefono[2]);
	   }
	
	public String[] cargar_datos_sql_2msg(SQLiteDatabase db,String TABLA){
		//Mejora: se puede mejorar consultando primero el numero de datos guardados.
			
		   int y=0;
		   Cursor fila=db.rawQuery("select * from "+TABLA, null);
		   while(fila.moveToNext()==true){
			   id[y]=fila.getString(0);
			   nombre[y]=fila.getString(1);
			   telefono[y]=fila.getString(2);
			   Log.i("Manada3",Integer.toString(y));
			   y=y+1;
		   }
		   
		   Log.i("Manada3","ok, carga_datos_sql_2msg");
		   return telefono;
		   
	   }
	
	
	public void update_datos_sql(SQLiteDatabase db,String TABLA,int yy){
		
		if(yy==1){
			String nombre=Contactos.n_con_1.getText().toString();
			String telefono=Contactos.tel_con_1.getText().toString();
			ContentValues registro = new ContentValues();
	        registro.put("con_nombre", nombre);
	        registro.put("con_telefono", telefono);
	        int cant = db.update(TABLA, registro, "_id=" + yy, null);
	        db.close();
	        if (cant == 1)
	        	Log.i("Manada3","Se modificaron los datos"+nombre+" "+telefono);
	            
	        else
	        	Log.i("Manada3","No existe id 0 en la base de datos");
	        	
	    }else if(yy==2){
	    	String nombre=Contactos.n_con_2.getText().toString();
			String telefono=Contactos.tel_con_2.getText().toString();
			ContentValues registro = new ContentValues();
	        registro.put("con_nombre", nombre);
	        registro.put("con_telefono", telefono);
	        int cant = db.update(TABLA, registro, "_id=" + yy, null);
	        db.close();
	        if (cant == 1)
	        	Log.i("Manada3","Se modificaron los datos"+nombre+" "+telefono);
	            
	        else
	        	Log.i("Manada3","No existe id 1 en la base de datos");
	    }else if(yy==3){
	    	String nombre=Contactos.n_con_3.getText().toString();
			String telefono=Contactos.tel_con_3.getText().toString();
			ContentValues registro = new ContentValues();
	        registro.put("con_nombre", nombre);
	        registro.put("con_telefono", telefono);
	        int cant = db.update(TABLA, registro, "_id=" + yy, null);
	        db.close();
	        if (cant == 1)
	        	Log.i("Manada3","Se modificaron los datos"+nombre+" "+telefono);
	            
	        else
	        	Log.i("Manada3","No existe id 2 en la base de datos");
	    }else{
	    	Log.i("Manada3","Error 00001");
	    }
	}
		
	   
	  /* public long insert(ContentValues reg)
	   {
	      if (db == null)
	         abrir();
	       
	      return db.insert(C_TABLA, null, reg);
	   }*/

}
