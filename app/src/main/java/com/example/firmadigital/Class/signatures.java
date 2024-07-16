package com.example.firmadigital.Class;

public class signatures {
    private Integer Id;
    private byte [] FirmaDigital;
    private String Descripcion;

    public signatures(byte[] firmaDigital, Integer id, String descripcion) {
        this.Id = id;
        this.FirmaDigital = firmaDigital;
        this.Descripcion = descripcion;
    }

    public signatures() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public byte[] getFirmaDigital() {
        return FirmaDigital;
    }

    public void setFirmaDigital(byte[] firmaDigital) {
        FirmaDigital = firmaDigital;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

}
