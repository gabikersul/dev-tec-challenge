package DAO;

import com.google.gson.GsonBuilder;
import exception.FileException;
import model.Report;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;

public class ReportDAO {

    private final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final String REPORT_FILE = "./data/out/";

    public boolean saveReport(Report report, String fileName){
        try(FileWriter writer = new FileWriter(REPORT_FILE + fileName)) {
            GSON.toJson(report, writer);
            return true;
        }catch (IOException e){
            throw new FileException("Error saving report: " + e);
        }
    }

}
