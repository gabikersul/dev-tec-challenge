package handler;

import model.Client;
import model.Product;
import model.Sale;
import model.Salesperson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DataHandler {

    private Pattern pattern;

    public DataHandler(Pattern pattern){
        this.pattern = pattern;
    }
    public List<String>  getDataLine(List<String> lines, String cod){
        return lines.stream().filter(line -> line.startsWith(cod)).collect(Collectors.toList());
    }

    public List<Salesperson> getSalespeople(List<String> salespeopleLine){
        List<Salesperson> salespeople = new ArrayList<>();

        salespeopleLine.forEach(field -> {
            List<String> dataLine = Stream.of(field)
                    .map(this.pattern::matcher)
                    .filter(Matcher::find)
                    .flatMap(matcher -> IntStream.rangeClosed(1, matcher.groupCount()).mapToObj(matcher::group))
                    .collect(Collectors.toList());
            salespeople.add(new Salesperson(dataLine.get(0), dataLine.get(1),
                    dataLine.get(2), Double.parseDouble(dataLine.get(3))));
        });
        return salespeople;
    }

    public List<Client> getClients(List<String> clientsLine){
        List<Client> clients = new ArrayList<>();

        clientsLine.forEach(field -> {
            List<String> dataLine = Stream.of(field)
                    .map(this.pattern::matcher)
                    .filter(Matcher::find)
                    .flatMap(matcher -> IntStream.rangeClosed(1, matcher.groupCount()).mapToObj(matcher::group))
                    .collect(Collectors.toList());
            clients.add(new Client(dataLine.get(0), dataLine.get(1),
                    dataLine.get(2), dataLine.get(3)));
        });
        return clients;
    }

    public List<Sale> getSales(List<String> salesLine){
        List<Sale> sales = new ArrayList<>();

        salesLine.forEach(field -> {
            List<String> dataLine = Stream.of(field)
                    .map(this.pattern::matcher)
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
        return sales;
    }

}
