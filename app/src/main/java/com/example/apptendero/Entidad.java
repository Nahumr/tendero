package com.example.apptendero;

public class Entidad {

    private int imgFoto;
    private String titulo;
    private String contenido;
    private String contenido2;
    private String numero;

    public Entidad(int imgFoto, String titulo, String contenido, String numero, String contenido2){
        this.imgFoto=imgFoto;
        this.titulo=titulo;
        this.contenido=contenido;
        this.contenido2=contenido2;
        this.numero=numero;
    }

    public int getImgFoto(){return imgFoto;}

    public String getTitulo(){return titulo;}

    public String getContenido(){ return contenido;}

    public String getContenido2(){ return contenido2;}

    public String getNumero(){ return numero;}
}
