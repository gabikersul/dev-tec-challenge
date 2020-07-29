package model;

import java.util.List;

public class Sale {

    private String identifier;
    private String saleID;
    private List<Product> productList;
    private String salespersonName;
    private Double productTotalPrice;

    public Sale(){

    }

    public Sale(String identifier, String saleID, String salespersonName) {
        this.identifier = identifier;
        this.saleID = saleID;
        this.salespersonName = salespersonName;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getSaleID() {
        return saleID;
    }

    public void setSaleID(String saleID) {
        this.saleID = saleID;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public String getSalespersonName() {
        return salespersonName;
    }

    public void setSalespersonName(String salespersonName) {
        this.salespersonName = salespersonName;
    }

    public Double getProductTotalPrice(){
        return productTotalPrice;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "identifier='" + identifier + '\'' +
                ", saleID='" + saleID + '\'' +
                ", productList=" + productList +
                ", salespersonName='" + salespersonName + '\'' +
                '}';
    }
}
