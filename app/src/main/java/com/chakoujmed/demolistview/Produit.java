package com.chakoujmed.demolistview;

public class Produit {
    //attributs
    int   id;
    private  String designation;
    private  double prix;

    public Produit(int id, String designation, double prix) {
        this.id = id;
        this.designation = designation;
        this.prix = prix;
    }

    public Produit(String designation, double prix) {
        this.designation = designation;
        this.prix = prix;
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }




}
