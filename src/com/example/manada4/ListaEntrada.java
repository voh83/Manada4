package com.example.manada4;

public class ListaEntrada {
	
	private int idImagen; 
	private String textoEncima; 
	private String textoDebajo; 

	public ListaEntrada (int idImagen, String textoEncima) { 
	    this.idImagen = idImagen; 
	    this.textoEncima = textoEncima; 
	   
	}

	public String get_textoEncima() { 
	    return textoEncima; 
	}

	public int get_idImagen() {
	    return idImagen; 
	} 
	

}
