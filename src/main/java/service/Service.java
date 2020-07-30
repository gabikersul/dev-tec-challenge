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

    public String getBestSaleID(List<Sale> sales){
        if(sales.isEmpty()){
            throw new IllegalArgumentException("No sale registered.");
        }
        Collections.sort(sales);
        return sales.get(0).getSaleID();
    }

    public Salesperson getWorstSalesperson(List<Sale> sales, List<Salesperson> salespeople){
        if(sales.isEmpty()) {
            throw new IllegalArgumentException("No sale registered.");
        }
        for (Sale sale : sales) {
            salespeople.stream().filter(salesperson -> salesperson.getName()
                    .equals(sale.getSalespersonName()))
                    .forEach(Salesperson::incrementTotalSale);
        }
        Collections.sort(salespeople);
        return salespeople.get(0);
    }

    public boolean createReport(List<Client> clients, List<Salesperson> salespeople, List<Sale> sales, Path fileName){
        Integer quantityOfClients = calculateQuantityOfClients(clients);
        Integer quantityOfSalespeople = calculateQuantityOfSalespeople(salespeople);
        String bestSaleID = getBestSaleID(sales);
        Salesperson worstSalesperson = getWorstSalesperson(sales, salespeople);

        return saveReport(new Report(quantityOfClients, quantityOfSalespeople, bestSaleID, worstSalesperson), fileName);
    }

    public boolean saveReport(Report report, Path fileName){
        return reportDAO.saveReport(report, String.valueOf(fileName.getFileName()).replace(".txt", ".json"));
    }

}
