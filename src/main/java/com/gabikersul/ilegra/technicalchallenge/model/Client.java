package com.gabikersul.ilegra.technicalchallenge.model;

public class Client {

    private String identifier;
    private String cnpj;
    private String name;
    private String businessArea;


    public Client(String identifier, String cnpj, String name, String businessArea){
        this.identifier = identifier;
        this.cnpj = cnpj;
        this.name = name;
        this.businessArea = businessArea;
    }

    @Override
    public String toString() {
        return "Client{" +
                "identifier='" + identifier + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", name='" + name + '\'' +
                ", businessArea='" + businessArea + '\'' +
                '}';
    }
}
