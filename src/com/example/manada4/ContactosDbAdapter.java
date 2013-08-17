package com.example.manada4;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ContactosDbAdapter {

	/**
	    * Definimos constante con el nombre de la tabla
	    */
	   public static final String C_TABLA = "CONTACTOS" ;
	 
	   /**
	    * Definimos constantes con el nombre de las columnas de la tabla
	    */
	   public static final String C_COLUMNA_ID   = "_id";
	   public static final String C_COLUMNA_NOMBRE = "con_nombre";
	   public static final String C_COLUMNA_TELEFONO = "con_telefono";

	 
	   private Context contexto;
	   private ContactosDbHelper dbHelper;
	   private SQLiteDatabase db;
	 
	   /**
	    * Definimos lista de columnas de la tabla para utilizarla en las consultas a la base de datos
	    */
	   private String[] columnas = new String[]{ C_COLUMNA_ID, C_COLUMNA_NOMBRE, C_COLUMNA_TELEFONO} ;
	 
	   public ContactosDbAdapter(Context context)
	   {
	      this.contexto = context;
	   }
	 
	   public ContactosDbAdapter abrir() throws SQLException
	   {
	      dbHelper = new ContactosDbHelper(contexto);
	      db = dbHelper.getWritableDatabase();
	      return this;
	   }
	 
	   public void cerrar()
	   {
	      dbHelper.close();
	   }
	 
	   /**
	    * Devuelve cursor con todos las columnas de la tabla
	    */
	   public Cursor getCursor() throws SQLException
	   {
	      Cursor c = db.query( true, C_TABLA, columnas, null, null, null, null, null, null);
	 
	      return c;
	   }
	
}
