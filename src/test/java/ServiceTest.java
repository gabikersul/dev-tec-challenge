import model.Product;
import model.Report;
import model.Sale;
import model.Salesperson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ServiceTest {

    private Service service;
    double delta = 0.0;

    @Before
    public void init(){
        service = new Service();
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
    public void shouldGetTheBiggestSalePriceTest(){
        Sale sale1 = new Sale("003", "1", "Lucas");
        Product product1 = new Product("1", 1, 10.5);
        Product product2 = new Product("2", 1, 20.1);
        Product product3 = new Product("3", 1, 30.1);
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
        service.calculatePriceBestSale(sales);
        System.out.println(service.calculatePriceBestSale(sales));
        Assert.assertEquals(30.6,service.calculatePriceBestSale(sales).getAsDouble(), delta);

    }
}
