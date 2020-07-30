import DAO.FileInDAO;
import DAO.ReportDAO;
import model.*;
import service.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) throws IOException {
        Service service = new Service();
        FileInDAO fileInDAO = new FileInDAO();

        fileInDAO.readFolder().forEach(file -> {
            List<String> lines = fileInDAO.readAllLines(file.getFileName());
            Pattern pattern = Pattern.compile("(\\d{3})ç(\\d+)ç(.+)ç(\\S+)");

            List<String> salespeopleLine = lines.stream().filter(xline -> xline.startsWith("001")).collect(Collectors.toList());
            List<String> clientsLine = lines.stream().filter(xline -> xline.startsWith("002")).collect(Collectors.toList());
            List<String> salesLine = lines.stream().filter(xline -> xline.startsWith("003")).collect(Collectors.toList());

            List<Salesperson> salespeople = new ArrayList<>();

            salespeopleLine.forEach(field -> {
                List<String> dataLine = Stream.of(field)
                        .map(pattern::matcher)
                        .filter(Matcher::find)
                        .flatMap(matcher -> IntStream.rangeClosed(1, matcher.groupCount()).mapToObj(matcher::group))
                        .collect(Collectors.toList());
                Salesperson salesperson = new Salesperson(dataLine.get(0), dataLine.get(1),
                        dataLine.get(2), Double.parseDouble(dataLine.get(3)));
                salespeople.add(salesperson);
            });

            List<Client> clients = new ArrayList<>();

            clientsLine.forEach(field -> {
                List<String> dataLine = Stream.of(field)
                        .map(pattern::matcher)
                        .filter(Matcher::find)
                        .flatMap(matcher -> IntStream.rangeClosed(1, matcher.groupCount()).mapToObj(matcher::group))
                        .collect(Collectors.toList());
                Client client = new Client(dataLine.get(0), dataLine.get(1),
                        dataLine.get(2), dataLine.get(3));
                clients.add(client);
            });

            List<Sale> sales = new ArrayList<>();

            salesLine.forEach(field -> {
                List<String> dataLine = Stream.of(field)
                        .map(pattern::matcher)
                        .filter(Matcher::find)
                        .flatMap(matcher -> IntStream.rangeClosed(1, matcher.groupCount()).mapToObj(matcher::group))
                        .collect(Collectors.toList());
                Sale sale = new Sale(dataLine.get(0), dataLine.get(1), dataLine.get(3));

                List<Product> products = new ArrayList<>();
                String[] productsString = dataLine.get(2).split(",");
                Arrays.stream(productsString).forEach(string -> {
                    String[] productField = string.replace("[", "").replace("]", "").split("-");
                    Product product = new Product(productField[0],
                            Integer.parseInt(productField[1]), Double.parseDouble(productField[2]));
                    products.add(product);
                });
                sale.setProductList(products);
                sales.add(sale);
            });
            System.out.println(sales);
            Salesperson salesperson = new Salesperson();
            Report report = service.createReport(clients, salespeople, sales, salesperson);
            service.saveReport(report, file);
        });
    }
}
