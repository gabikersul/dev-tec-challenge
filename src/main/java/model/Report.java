package model;

public class Report {

    private Integer quantityOfClients;
    private Integer quantityOfSalespeople;
    private String biggestSaleID;
    private Salesperson worstSalesperson;


    public Report(){

    }
    public Report(Integer quantityOfClients, Integer quantityOfSalespeople, String biggestSaleID, Salesperson worstSalesperson) {
        this.quantityOfClients = quantityOfClients;
        this.quantityOfSalespeople = quantityOfSalespeople;
        this.biggestSaleID = biggestSaleID;
        this.worstSalesperson = worstSalesperson;
    }

    public Integer getQuantityOfClients() {
        return quantityOfClients;
    }

    public void setQuantityOfClients(Integer quantityOfClients) {
        this.quantityOfClients = quantityOfClients;
    }

    public Integer getQuantityOfSalespeople() {
        return quantityOfSalespeople;
    }

    public void setQuantityOfSalespeople(Integer quantityOfSalespeople) {
        this.quantityOfSalespeople = quantityOfSalespeople;
    }

    public String getBiggestSaleID() {
        return biggestSaleID;
    }

    public void setBiggestSaleID(String biggestSaleID) {
        this.biggestSaleID = biggestSaleID;
    }

    public Salesperson getWorstSalesperson() {
        return worstSalesperson;
    }

    public void setWorstSalesperson(Salesperson worstSalesperson) {
        this.worstSalesperson = worstSalesperson;
    }

    @Override
    public String toString() {
        return "Report{" +
                "quantityOfClients=" + quantityOfClients +
                ", quantityOfSellers=" + quantityOfSalespeople +
                ", biggestSaleID='" + biggestSaleID + '\'' +
                ", worstSeller=" + worstSalesperson +
                '}';
    }
}
