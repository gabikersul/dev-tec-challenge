package com.gabikersul.ilegra.technicalchallenge.model;

import java.util.List;


public class Sale implements Comparable<Sale> {

    private String identifier;
    private String saleID;
    private List<Product> productList;
    private String salespersonName;


    public Sale(String identifier, String saleID, String salespersonName) {
        this.identifier = identifier;
        this.saleID = saleID;
        this.salespersonName = salespersonName;
    }

    public String getSaleID() {
        return saleID;
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


    @Override
    public String toString() {
        return "Sale{" +
                "identifier='" + identifier + '\'' +
                ", saleID='" + saleID + '\'' +
                ", productList=" + productList +
                ", salespersonName='" + salespersonName + '\'' +
                '}';
    }

    @Override
    public int compareTo(Sale sale) {
        double thisTotal = this.productList.stream().mapToDouble(Product::getProductTotalPrice).sum();
        double otherTotal = sale.getProductList().stream().mapToDouble(Product::getProductTotalPrice).sum();
        if (thisTotal > otherTotal) {
            return -1;
        }
        else if (thisTotal < otherTotal) {
            return 1;
        }
        return 0;
    }
}
