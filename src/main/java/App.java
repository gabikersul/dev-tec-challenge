import DAO.ImportDAO;
import handler.DataHandler;
import model.Client;
import model.Sale;
import model.Salesperson;
import service.Service;

import java.util.List;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args){

        while (true) {
            Service service = new Service();
            ImportDAO importDAO = new ImportDAO();
            DataHandler dataHandler = new DataHandler(Pattern.compile("(\\d{3})ç(\\d+)ç(.+)ç(\\S+)"));

            importDAO.readFolder().forEach(file -> {
                List<String> lines = importDAO.readAllLines(file.getFileName());

                List<String> salespeopleLine = dataHandler.getDataLine(lines, "001");
                List<String> clientsLine = dataHandler.getDataLine(lines, "002");
                List<String> salesLine = dataHandler.getDataLine(lines, "003");

                List<Salesperson> salespeople = dataHandler.getSalespeople(salespeopleLine);
                List<Client> clients = dataHandler.getClients(clientsLine);
                List<Sale> sales = dataHandler.getSales(salesLine);
                service.createReport(clients, salespeople, sales, file);
            });
        }
    }
}
