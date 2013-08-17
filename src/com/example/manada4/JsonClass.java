package com.example.manada4;

public class JsonClass {
	/* 
	 * De acuerdo al tipo de mensaje que se envia realiza el parseo Json de acuerdo al programa.
	 */
	private String json_msg;
//Constructor
	JsonClass(){
		json_msg=null;
		
	}
/**
 * Formatea mensaje de registro a Json.	
 * @param alias
 * @param contrasena
 * @param nombre
 * @param apellido_paterno
 * @param apellido_materno
 * @param movil
 * @param rut
 * @param email
 * @param fecha_nacimiento
 * @param direccion
 * @param foto
 * @param antecedentes_emergencia
 * @param pais
 * @param region
 * @param ciudad
 * @param comuna
 * @return json_msg
 */
String msg_registro(
		String alias,
		String contrasena,
		String nombre,
		String apellido_paterno,
		String apellido_materno,
		String movil,
		String rut,
		String email,
		String fecha_nacimiento,
		String direccion,
		String foto,
		String antecedentes_emergencia,
		String pais,
		String region,
		String ciudad,
		String comuna){
		
	json_msg="{";
	json_msg=json_msg+"'tipo': 'MSG_REGISTRO',";
	json_msg=json_msg+"'data':{";		
	json_msg=json_msg+"'alias':'"+alias+"', ";			
	json_msg=json_msg+"'contrasena':'"+contrasena+"',  ";	
	json_msg=json_msg+" 'nombre':'"+nombre+"',";	
	json_msg=json_msg+" 'apellido_paterno':'"+apellido_paterno+"',";	
	json_msg=json_msg+" 'apellido_materno':'"+apellido_materno+"',";	
	json_msg=json_msg+"'movil':'"+movil+"', ";	
	json_msg=json_msg+"'rut':'"+rut+"', ";	
	json_msg=json_msg+"'email':'"+email+"', ";	
	json_msg=json_msg+"'fecha_nacimiento':'"+fecha_nacimiento+"', ";
	json_msg=json_msg+"'direccion':'"+direccion+"', ";	
	json_msg=json_msg+"'foto':'"+foto+"', ";	
	json_msg=json_msg+"'antecedentes_emergencia':'"+antecedentes_emergencia+"', ";	
	json_msg=json_msg+"'pais':'"+pais+"', ";	
	json_msg=json_msg+"'region':'"+region+"', ";	
	json_msg=json_msg+"'ciudad':'"+ciudad+"', ";	
	json_msg=json_msg+"'comuna':'"+comuna+"' ";	
	json_msg=json_msg+"}} ";
				
	return json_msg;
		
	}
/**
 * formatea mensaje de posicion a Json.
 * @param id
 * @param latitud
 * @param longitud
 * @param fecha
 * @return json_msg
 */
	String msg_posicion(
			int id,
			double latitud,
			double longitud,
			String fecha
			){
		
		json_msg="{'tipo':'MSG_POSICION'";
		json_msg=json_msg+"'data':{";
		json_msg=json_msg+"'id_usuario':'"+id+"',";
		json_msg=json_msg+"'latitud':'"+latitud+"',";
		json_msg=json_msg+"'longitud':'"+longitud+"',";
		json_msg=json_msg+"'fecha':'"+fecha+"',";
		json_msg=json_msg+"}}";
		
		return json_msg;
	}
	/**
	 * formatea mensaje de actualizar contactos a Json
	 * @param id
	 * @param descripcion json array con descripcion y telefono
	 * @return json_msg
	 */
	
	String msg_actualiza_contactos(
			int id,
			String[] descripcion
			){
		
		json_msg="{'tipo':'MSG_ACTUALIZA_CONTACTOS'";
		json_msg=json_msg+"'data':{";
		json_msg=json_msg+"'id_usuario':'"+id+"',";
		json_msg=json_msg+"contactos["+descripcion+"]}}";
		
		return json_msg;
	}
	/**
	 * formatea mensaje de crear comunidad a Json
	 * @param id
	 * @param nombre
	 * @param descripcion
	 * @param latitud
	 * @param longitud
	 * @param cobertura
	 * @return
	 */
	
	String msg_crea_comunidad(
			int id,
			String nombre,
			String descripcion,
			double latitud,
			double longitud,
			String cobertura
			){
		
		json_msg="{'tipo':'MSG_CREA_COMUNIDAD'";
		json_msg=json_msg+"'data':{";
		json_msg=json_msg+"'id_usuario':'"+id+"',";
		json_msg=json_msg+"'nombre':'"+nombre+"',";
		json_msg=json_msg+"'descripcion':'"+descripcion+"',";
		json_msg=json_msg+"'latitud':'"+latitud+"',";
		json_msg=json_msg+"'longitud':'"+longitud+"',";
		json_msg=json_msg+"'cobertura':'"+cobertura+"'}}";
		
		return json_msg;
	}
	/**
	 * formatea mensaje de agrega a comunidad a Json
	 * @param id_usuario
	 * @param id_comunidad
	 * @return
	 */
	String msg_agrega_a_comunidad(
			int id_usuario,
			int id_comunidad
			
			){
		
		json_msg="{'tipo':'MSG_AGREGA_A_COMUNIDAD'";
		json_msg=json_msg+"'data':{";
		json_msg=json_msg+"'id_usuario':'"+id_usuario+"',";
		json_msg=json_msg+"'id_comunidad':'"+id_comunidad+"'}}";

		return json_msg;
	}
	/**
	 * formatea mensaje de panico alarma a Json
	 * @param id
	 * @param latitud
	 * @param longitud
	 * @param tipo_incidente
	 * @param tipo_alarma
	 * @return
	 */
	String msg_panico_alarma(
			int id,
			Double latitud,
			Double longitud,
			int tipo_incidente,
			int tipo_alarma
			){
		
		json_msg="{'tipo':'MSG_PANICO_ALARMA'";
		json_msg=json_msg+"'data':{";
		json_msg=json_msg+"'id':'"+id+"',";
		json_msg=json_msg+"'latitud':'"+latitud+"',";
		json_msg=json_msg+"'longitud':'"+longitud+"',";
		json_msg=json_msg+"'tipo_incidente':'"+tipo_incidente+"',";
		json_msg=json_msg+"'tipo_alarma':'"+tipo_alarma+"'}}";

		return json_msg;
	}

}
