package com.crud.rest.entity;

public class Estadistica {

    private Long cantidad;
    private String sexo;

    public Estadistica () {}

    public Estadistica(Long cantidad, String sexo) {
        this.cantidad = cantidad;
        this.sexo = sexo;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}