import DAO.FileInDAO;
import model.*;
import service.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args){
        while (true) {
            Service service = new Service();
            FileInDAO fileInDAO = new FileInDAO();

            fileInDAO.readFolder().forEach(file -> {
                List<String> lines = fileInDAO.readAllLines(file.getFileName());
                Pattern pattern = Pattern.compile("(\\d{3})ç(\\d+)ç(.+)ç(\\S+)");

                List<String> salespeopleLine = lines.stream().filter(line -> line.startsWith("001")).collect(Collectors.toList());
                List<String> clientsLine = lines.stream().filter(line -> line.startsWith("002")).collect(Collectors.toList());
                List<String> salesLine = lines.stream().filter(line -> line.startsWith("003")).collect(Collectors.toList());

                List<Salesperson> salespeople = new ArrayList<>();

                salespeopleLine.forEach(field -> {
                    List<String> dataLine = Stream.of(field)
                            .map(pattern::matcher)
                            .filter(Matcher::find)
                            .flatMap(matcher -> IntStream.rangeClosed(1, matcher.groupCount()).mapToObj(matcher::group))
                            .collect(Collectors.toList());
                    salespeople.add(new Salesperson(dataLine.get(0), dataLine.get(1),
                            dataLine.get(2), Double.parseDouble(dataLine.get(3))));
                });

                List<Client> clients = new ArrayList<>();

                clientsLine.forEach(field -> {
                    List<String> dataLine = Stream.of(field)
                            .map(pattern::matcher)
                            .filter(Matcher::find)
                            .flatMap(matcher -> IntStream.rangeClosed(1, matcher.groupCount()).mapToObj(matcher::group))
                            .collect(Collectors.toList());
                    clients.add(new Client(dataLine.get(0), dataLine.get(1),
                            dataLine.get(2), dataLine.get(3)));
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
                Salesperson salesperson = new Salesperson();
                service.createReport(clients, salespeople, sales, file);
            });
        }
    }
}
