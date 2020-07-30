package com.gabikersul.ilegra.technicalchallenge.model;

public class Salesperson implements  Comparable<Salesperson>{

    private String identifier;
    private String cpf;
    private String name;
    private Double salary;
    private Integer totalSales;

    public Salesperson(String identifier, String cpf, String name, double salary) {
        this.identifier = identifier;
        this.cpf = cpf;
        this.name = name;
        this.salary = salary;
        this.totalSales = 0;
    }

    public String getName() {
        return name;
    }

    public Integer getTotalSales() {
        return totalSales;
    }

    public void incrementTotalSale() {
        this.totalSales++;
    }

    @Override
    public String toString() {
        return "Salesperson{" +
                "identifier='" + identifier + '\'' +
                ", cpf='" + cpf + '\'' +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public int compareTo(Salesperson salesperson) {
        return this.getTotalSales().compareTo(salesperson.getTotalSales());
    }
}
