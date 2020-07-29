import DAO.ReportDAO;
import model.*;
import service.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        ReportDAO reportDAO = new ReportDAO();
        Report report = new Report();
        Service service = new Service();

        Files.walk(Paths.get("./data/in"))
                .filter(Files::isRegularFile)
                .forEach(file -> {
                    List<String> lines = new ArrayList<>();
                    try {
                        lines = Files.readAllLines(Paths.get(file.getParent()+"/"+file.getFileName()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    List<Salesperson> salespeople = new ArrayList<>();
                    List<Client> clients = new ArrayList<>();
                    List<Sale> sales = new ArrayList<>();
                    List<Product> products = new ArrayList<>();

                    for (String line: lines) {
                        if (line.startsWith("001")) {
                            String[] dataLine = line.split("ç");
                            Salesperson salesperson = new Salesperson(dataLine[0], dataLine[1], dataLine[2], Double.parseDouble(dataLine[3]));
                            salespeople.add(salesperson);
                            System.out.println(salespeople);
                        }
                        else if (line.startsWith("002")) {
                            String[] dataLine = line.split("ç");
                            Client client = new Client(dataLine[0], dataLine[1], dataLine[2], dataLine[3]);
                            clients.add(client);
                            System.out.println(clients);
                        }
                        else if (line.startsWith("003")) {
                            String[] dataLine = line.split("ç");
                            Sale sale = new Sale(dataLine[0], dataLine[1], dataLine[3]);
                            String[] propertyProducto = dataLine[2].replace("[", "").split("]");
                            for (String aaa: propertyProducto) {
                                String[] aaabbb = aaa.split(",");
                                for (String a: aaabbb){
                                    String[] ab = a.split("-");
                                    Product product = new Product(ab[0], Integer.parseInt(ab[1]), Double.parseDouble(ab[2]));
                                    products.add(product);
                                }
                                sale.setProductList(products);
                            }
                            sales.add(sale);
                            System.out.println(sales);
                        }
                        else {
                            System.out.println("invalid");
                        }
                    }
                    int quantityClients = service.calculateQuantityOfClients(clients);
                    System.out.println(quantityClients);
                    int quantitySellers = service.calculateQuantityOfSalespeople(salespeople);
                    System.out.println(quantitySellers);

                    Salesperson salesperson = new Salesperson();
                    report.setQuantityOfSalespeople(quantitySellers);
                    report.setQuantityOfClients(quantityClients);

                    Report report1 = new Report();
                    report1= service.createReport(clients, salespeople,salesperson);
                    service.saveReport(report1, file);

                });
    }
}
