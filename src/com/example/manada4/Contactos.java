package com.example.manada4;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Contactos extends Activity {
	Button button1,button2,button3,button4,button9,button10;
	public static EditText n_con_1,n_con_2,n_con_3,tel_con_1,tel_con_2,tel_con_3;
	public static final String C_MODO  = "modo" ;
	public static final int C_VISUALIZAR = 551 ;
	public static final int C_CREAR = 552 ;
	ListView lista;
	ContactosDbAdapter dbAdapter;
	Cursor cursor;
	ContactosCursorAdapter contactoAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contactos);
		button1=(Button)findViewById(R.id.button1);
		button2=(Button)findViewById(R.id.button2);
		button3=(Button)findViewById(R.id.button3);
		button4=(Button)findViewById(R.id.button4);
		button9=(Button)findViewById(R.id.button9);
		button10=(Button)findViewById(R.id.button10);
		n_con_1=(EditText)findViewById(R.id.n_con_1);
		n_con_2=(EditText)findViewById(R.id.n_con_2);
		n_con_3=(EditText)findViewById(R.id.n_con_3);
		tel_con_1=(EditText)findViewById(R.id.tel_con_1);
		tel_con_2=(EditText)findViewById(R.id.tel_con_2);
		tel_con_3=(EditText)findViewById(R.id.tel_con_3);
	 
		
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("Manada3","Se presiono boton guardar base de datos");
				ContactosDbHelper dbHelper = new ContactosDbHelper(getBaseContext());
				 
				   SQLiteDatabase db = dbHelper.getWritableDatabase();
				 
				   Toast.makeText(getBaseContext(), "Base de datos preparada", Toast.LENGTH_LONG).show();
			}
		});
		
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("Manada3","Se presiono boton cargar datos de DB");
				ContactosDbHelper dbHelper = new ContactosDbHelper(getBaseContext());
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				dbHelper.cargar_datos_sql(db, "CONTACTOS");
				 
				   Toast.makeText(getBaseContext(), "Base de datos preparada", Toast.LENGTH_LONG).show();
			}
		});

		button3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("Manada3","Se presiono boton update datos de DB");
				ContactosDbHelper dbHelper = new ContactosDbHelper(getBaseContext());
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				dbHelper.update_datos_sql(db, "CONTACTOS",3);
				 
				   Toast.makeText(getBaseContext(), "Base de datos preparada", Toast.LENGTH_LONG).show();
			}
		});
		button9.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("Manada3","Se presiono boton update datos de DB");
				ContactosDbHelper dbHelper = new ContactosDbHelper(getBaseContext());
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				dbHelper.update_datos_sql(db, "CONTACTOS",2);
				 
				   Toast.makeText(getBaseContext(), "Base de datos preparada", Toast.LENGTH_LONG).show();
			}
		});
		button10.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("Manada3","Se presiono boton update datos de DB");
				ContactosDbHelper dbHelper = new ContactosDbHelper(getBaseContext());
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				dbHelper.update_datos_sql(db, "CONTACTOS",1);
				 
				   Toast.makeText(getBaseContext(), "Base de datos preparada", Toast.LENGTH_LONG).show();
			}
		});
		
	}
	
	

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contactos, menu);
		return true;
	}

}
