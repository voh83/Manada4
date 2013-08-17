package com.example.manada4;


import com.example.manada4.MainActivity;
import com.example.manada4.R;
import com.example.manada4.JsonToArray;
import com.example.manada4.ListaAdaptador;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	Messenger mService = null;
    boolean mBound;
    static Boolean looperStatus=false;
	
	Button button1,button2,button3,button4,button6,button9;
	Intent mServiceIntent,servIntent,MapaIntent,cargIntent,intent_serv;
	static Context context;
	ListView listview1;
	ListaAdaptador lista_adaptador;
	TextView texto_superior_entrada;
	JsonToArray jarr;
	ListAdapter listAdapter;

	
	
private ServiceConnection mConnection = new ServiceConnection() {
		
        public void onServiceConnected(ComponentName className, IBinder service) {
            
        	mService = new Messenger(service);
        	mBound=true;
        	Toast.makeText(getBaseContext(), "Conectado-Binding:"+mService,Toast.LENGTH_SHORT).show();
        }

        public void onServiceDisconnected(ComponentName className) {
            // This is called when the connection with the service has been unexpectedly disconnected - process crashed.
            mService = null;
            mBound=false;
            Toast.makeText(getBaseContext(), "Desconectado-Unbinding",Toast.LENGTH_SHORT).show();
        }
    };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		button1=(Button)findViewById(R.id.button1);
		button2=(Button)findViewById(R.id.button2);
		button3=(Button)findViewById(R.id.button3);
		button4=(Button)findViewById(R.id.button4);
		button6=(Button)findViewById(R.id.button6);
		button9=(Button)findViewById(R.id.button9);
		listview1=(ListView)findViewById(R.id.list);
		context=getApplicationContext();
		
		//Codigo de listado de eventos.
		//ArrayList<ListaEntrada> datos = new ArrayList<ListaEntrada>();  
		jarr=new JsonToArray();
		jarr.recJson();
		
		listAdapter=new SimpleAdapter(MainActivity.context, jarr.recJson(), R.layout.entrada,new String[]{JsonToArray.TAG_FOTO,JsonToArray.TAG_ID_USUARIO}, new int[]{R.id.imageView_imagen,R.id.textView_superior});
		
		listview1.setAdapter(listAdapter);
		

		
		/*listview1.setAdapter(new ListaAdaptador(MainActivity.context,R.layout.entrada,datos) {
			
			@Override
			public void onEntrada(Object entrada, View view) {
				// TODO Auto-generated method stub

	            TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textView_superior); 
	            texto_superior_entrada.setText(((ListaEntrada) entrada).get_textoEncima()); 
	            
	            ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imageView_imagen); 
	            imagen_entrada.setImageResource(((ListaEntrada) entrada).get_idImagen());
			}
		});*/
	
		
		
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("Manada3","presiono boton 1");
				MapaIntent = new Intent(getBaseContext(),MapaActivity.class);
				MapaIntent.setData(Uri.parse("holi"));
				
				// Starts the IntentService
				startActivity(MapaIntent);
			}
		});
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("Manada3", "Se presiono parar servicio");
				
				if(looperStatus!=false){
					if (mBound) {
			            if (mService != null) {
			                try {
			                    Message msg = Message.obtain(null, PruebaService.MSG_SET_RESP_B, 0, 0);
			                    //msg.replyTo = mMessenger;
			                    mService.send(msg);
			                } catch (RemoteException e) {}
			            }
			        }
				}
				if (mBound) {
		            MainActivity.this.unbindService(mConnection);
		            mBound = false;
		        }else{
		        	Log.i("PruebaService", "PAso al Else.");
		        }
				
				
				servIntent = new Intent(MainActivity.this,PruebaService.class);
				//Looper.myLooper().quit();
				stopService(servIntent);
			
			}
		});
		button3.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("Manada3", "Se presiono Prueba Service");
		        
		        Toast.makeText(getBaseContext(), "Conectado-Binding",Toast.LENGTH_SHORT).show();
				
	            Log.i("Manada3", "mBound:"+mBound+" mService:"+mService);
	            if (mBound) {
		            if (mService != null) {
		                try {
		                    Message msg = Message.obtain(null, PruebaService.MSG_SET_RESP_A, 0, 0);
		                    //msg.replyTo = mMessenger;
		                    mService.send(msg);
		                } catch (RemoteException e) {
		                }
		            }
		        }
	            
			}
		});
		button6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("Manada3", "Se presiono cargar contactos");
				cargIntent = new Intent(getBaseContext(),Contactos.class);
				cargIntent.setData(Uri.parse("holi"));
				// Starts the IntentService
				startActivity(cargIntent);
			}
		});
		button4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("Manada3", "Se presionó Pánico ");
				ContactosDbHelper dbHelper = new ContactosDbHelper(getBaseContext());
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				String[] telefono1=dbHelper.cargar_datos_sql_2msg(db, "CONTACTOS");
				int largo=telefono1.length;
				for(int dd=0;dd<largo;dd++){
					try{
						SmsManager sms=SmsManager.getDefault();
						//sms.sendTextMessage(telefono1[dd], null, "MENSAJE DE PANICO", null, null);
					}catch(IllegalArgumentException e){
						Log.e("Manada3","Error de argumento :"+e);
					}
				}
				GpsMap gpsmap = new GpsMap();
				gpsmap.consCoord();
				MapaIntent = new Intent(MainActivity.context,MapaActivity.class);
				//MapaIntent.setData(Uri.parse(guardians));
				// Starts the IntentService
				MainActivity.context.startActivity(MapaIntent);
				
				Log.i("Manada3","Mensajes enviados satisfactoriamente");
				
			}
		});
		
		
	}

	@Override
	protected void onStart() {
        super.onStart();
        
        
        // Unbind from the service
        servIntent = new Intent(MainActivity.this,PruebaService.class);
		//Looper.myLooper().quit();
		startService(servIntent);
		
		bindService(new Intent(MainActivity.this,PruebaService.class), mConnection, Context.BIND_AUTO_CREATE);
        mBound = true;
        
        //Log.i("PruebaService", "Parte Service :");
        //bindService(new Intent(MainActivity.this,PruebaService.class), mConnection, Context.BIND_AUTO_CREATE);
        //mBound = true;
        //Toast.makeText(getBaseContext(), "Conectado-Binding",Toast.LENGTH_SHORT).show();
    }
	
	@Override
	protected void onDestroy() {
        super.onStop();
        Log.i("PruebaService", "Estado mBound :"+mBound+"Estado mConnection :"+mConnection);
        // Unbind from the service
        if (mBound && mConnection!=null) {
            MainActivity.this.unbindService(mConnection);
            mBound = false;
        }
    }

}
