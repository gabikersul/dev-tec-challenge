package model;

public class Salesperson {

    private String identifier;
    private String cpf;
    private String name;
    private Double salary;

    public Salesperson() {

    }

    public Salesperson(String identifier, String cpf, String name, double salary) {
        this.identifier = identifier;
        this.cpf = cpf;
        this.name = name;
        this.salary = salary;
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

    @Override
    public String toString() {
        return "Salesperson{" +
                "identifier='" + identifier + '\'' +
                ", cpf='" + cpf + '\'' +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
