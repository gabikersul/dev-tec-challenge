package com.gabikersul.ilegra.technicalchallenge;

import com.gabikersul.ilegra.technicalchallenge.model.Product;
import com.gabikersul.ilegra.technicalchallenge.model.Report;
import com.gabikersul.ilegra.technicalchallenge.model.Sale;
import com.gabikersul.ilegra.technicalchallenge.model.Salesperson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.gabikersul.ilegra.technicalchallenge.service.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ServiceTest {

    private Service service;

    @Before
    public void init(){
        this.service = new Service();
    }

    @Test
    public void shouldSaveReportTest(){
        Path fileName = Paths.get("test.txt");
        Salesperson salesperson = new Salesperson("001","130.889.026-51", "Pedro", 5000.0);
        Report report = new Report();
        report.setBiggestSaleID("1");
        report.setQuantityOfClients(4);
        report.setQuantityOfSalespeople(2);
        report.setWorstSalesperson(salesperson);

        Assert.assertTrue(service.saveReport(report, fileName));
    }

    @Test
    public void shouldGetBestSaleIDTest(){
        Product product1 = new Product("1", 1, 10.5);
        Product product2 = new Product("2", 1, 20.1);
        Product product3 = new Product("3", 1, 30.1);

        Sale sale1 = new Sale("003", "1", "Lucas");
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        sale1.setProductList(productList);

        Sale sale2 = new Sale("003", "2", "Gabriela");
        List<Product> productList2 = new ArrayList<>();
        productList2.add(product3);
        sale2.setProductList(productList2);

        Sale sale3 = new Sale("003","3","Renan");
        List<Product> productList3 = new ArrayList<>();
        productList3.add(product1);
        sale3.setProductList(productList3);

        List<Sale> sales = new ArrayList<>();
        sales.add(sale1);
        sales.add(sale2);
        sales.add(sale3);

        Assert.assertEquals("1", service.getBestSaleID(sales));

    }

    @Test
    public void shouldGetWorstSalespersonTest() {
        Sale sale1 = new Sale("003", "1", "Lucas");
        Sale sale2 = new Sale("003", "2", "Lucas");
        Sale sale3 = new Sale("003", "3", "Gabriela");
        Sale sale4 = new Sale("003", "4", "Renan");
        Sale sale5 = new Sale("003", "5", "Renan");
        Sale sale6 = new Sale("003", "6", "Renan");

        List<Sale> sales = new ArrayList<>();
        sales.add(sale1);
        sales.add(sale2);
        sales.add(sale3);
        sales.add(sale4);
        sales.add(sale5);
        sales.add(sale6);

        Salesperson salesperson1 = new Salesperson("001", "111.111.111-11", "Lucas", 4500.0);
        Salesperson salesperson2 = new Salesperson("001", "111.111.111-12", "Gabriela", 4500.0);
        Salesperson salesperson3 = new Salesperson("001", "111.111.111-13", "Renan", 4500.0);

        List<Salesperson> salespeople = new ArrayList<>();
        salespeople.add(salesperson1);
        salespeople.add(salesperson2);
        salespeople.add(salesperson3);

        Assert.assertEquals(salesperson2, service.getWorstSalesperson(sales, salespeople));
    }


}
