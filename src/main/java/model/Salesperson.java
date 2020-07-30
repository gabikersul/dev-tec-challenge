package model;

public class Salesperson implements  Comparable<Salesperson>{

    private String identifier;
    private String cpf;
    private String name;
    private Double salary;
    private Integer totalSales;

    public Salesperson() {

    }

    public Salesperson(String identifier, String cpf, String name, double salary) {
        this.identifier = identifier;
        this.cpf = cpf;
        this.name = name;
        this.salary = salary;
        this.totalSales = 0;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Integer totalSales) {
        this.totalSales = totalSales;
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
