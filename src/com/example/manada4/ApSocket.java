package com.example.manada4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.AsyncTask;
import android.util.Log;

public class ApSocket extends AsyncTask <String, String, String>{
	Socket socket;
	public String ans2;
	public MndToast toast=new MndToast();
	String nIp;
	int nPuerto;
	String nMsg;
	String alo="Algo de prueba";
	OutputStreamWriter oos;
	BufferedReader dis;

//Clase que sirve para conectar el socket
	
		/*class ConectaSocket extends AsyncTask<String, Void, String>{
			
			DataInputStream dis;
			OutputStreamWriter oos;
			
			//boolean scc;
			

		//public boolean conecta_socket(){
			//String ip="192.168.1.102";
			//int port=65455;
			*/@Override
			protected void onPreExecute(){
				//MainActivity.config_id.setText("Preparado para conectar");
				//textIn.setText("a punto de Conectar... ");
				
				toast.MostrarToast(MainActivity.context, "Conectandose al Socket");
			}

			@Override
			protected String doInBackground(String...params){
				//int count=params.length;
					nIp=params[0];
					nPuerto=Integer.parseInt(params[1]);
					nMsg=params[2];
				
				
				//publishProgress(nMsg);
					
				try{
					
					//socket=new Socket("192.168.1.102",65455);
					
					socket=new Socket(nIp,nPuerto);
					publishProgress("st_1");
					
					
		
					if(socket.isConnected()==true){
						oos=new OutputStreamWriter(socket.getOutputStream());
						BufferedReader dis = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						publishProgress("Socket Conectado...");
												
						oos.write(nMsg);
						oos.flush();
						ans2=dis.readLine();
						ans2=ans2.replaceAll("&quot;", "'");
									
						return ans2;
						
						
						

					}
					else{
						publishProgress("Socket NO Conectado...");
						return null;
						
						//textIn.setText("NO CONECTADO... ");
					}
		
				}catch(UnknownHostException e){
					Log.e("Manada3","Host desconocido",e);
					e.printStackTrace();
		
		
				}catch(IOException e){
					Log.e("Manada3","IOException"+e,e);
					e.printStackTrace();
		
				}finally{
					if(socket!=null){
						try{
							socket.close();
				
						}catch(IOException e){
							Log.e("Manada3","CErrar socket",e);
							e.printStackTrace();
				
						}
					}
					if(oos!=null){
						try{
							oos.close();
				
						}catch(IOException e){
							Log.e("Manada3","CErrar oos",e);
							e.printStackTrace();
				
						}
					}
					if(dis!=null){
						try{
							dis.close();
				
						}catch(IOException e){
							Log.e("Manada3","CErrar dis",e);
							e.printStackTrace();
				
						}
					}
				}
				
				return null;
				
			}
		
			@Override
			protected void onProgressUpdate(String...progress){
				if(progress[0]=="st_1"){
					toast.MostrarToast(MainActivity.context, "Soquet conectado");
				}
				//MainActivity.config_id.setText(progress);
			}
			@Override
			protected void onPostExecute(String result){
				//MainActivity.config_id.setText(result);
				//MainActivity.st_sk=result;
				toast.MostrarToast(MainActivity.context, result);
			}

			
	
		
		
}
		
	

