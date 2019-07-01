package com.example.apptendero;

public class Entidad2 {
    private int imgFoto;
    private String titulo;
    private String contenido;
    private String Eliminar;
    private String numero;
    private String Orden;

    public Entidad2(int imgFoto, String titulo, String contenido, String numero, String Orden){
        this.imgFoto=imgFoto;
        this.titulo=titulo;
        this.contenido=contenido;
        this.numero=numero;

        this.Orden=Orden;

    }

    public int getImgFoto(){return imgFoto;}

    public String getTitulo(){return titulo;}

    public String getContenido(){ return contenido;}

    public String getNumero(){ return numero;}



    public  String getOrden(){ return Orden;}
}


