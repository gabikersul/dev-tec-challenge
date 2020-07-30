package service;

import DAO.ReportDAO;
import model.*;

import java.nio.file.Path;
import java.util.*;

public class Service {

    private ReportDAO reportDAO;

    public Service(){
        this.reportDAO = new ReportDAO();
    }

    public Integer calculateQuantityOfClients(List<Client> clients){
        if(clients.isEmpty()){
            throw new IllegalArgumentException("No client registered.");
        }
        return clients.size();
    }

    public Integer calculateQuantityOfSalespeople(List<Salesperson> salespeople){
        if(salespeople.isEmpty()){
            throw new IllegalArgumentException("No salesperson registered.");
        }
        return salespeople.size();
    }

    public OptionalDouble calculatePriceBestSale(List<Sale> sales){
        if(sales.isEmpty()){
            throw new IllegalArgumentException("No sale registered.");
        }
        return sales.stream()
                .mapToDouble(sale -> sale.getProductList()
                        .stream()
                        .mapToDouble(Product::getProductTotalPrice)
                        .sum())
                .max();
    }

    public String getBestSaleID(List<Sale> sales){ //todo: pegar o id da melhor venda - só consigo pegar a melhor venda no metodo de cima
        return null;
    }

    public OptionalDouble calculatePriceWorstSale(List<Sale> sales){ //todo: não está funcionando a lógica p/ pegar o pior vendedor
        if(sales.isEmpty()){
            throw new IllegalArgumentException("No sale registered.");
        }
        return sales.stream()
                .mapToDouble(sale -> sale.getProductList()
                        .stream()
                        .mapToDouble(Product::getProductTotalPrice)
                        .sum())
                .min();
    }


    public Report createReport(List<Client> clients, List<Salesperson> salespeople, List<Sale> sales, Salesperson worstSalesperson){
        Integer quantityOfClients = calculateQuantityOfClients(clients);
        Integer quantityOfSalespeople = calculateQuantityOfSalespeople(salespeople);
        List<Sale> sals = sales;
        Collections.sort(sals, Sale::compareTo);
        String bestSaleID = sals.get(0).getSaleID();


        System.out.println("BEST SALEEEE "+bestSaleID);
        return new Report(quantityOfClients, quantityOfSalespeople, bestSaleID, worstSalesperson);
    }

    public boolean saveReport(Report report, Path fileName){
        return reportDAO.saveReport(report, String.valueOf(fileName.getFileName()).replace(".txt", ".json"));
    }

}
