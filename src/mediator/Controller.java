package mediator;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    List<Employee> employees;
    List<String> countries, provinces, cities;

    private int calculateNumberOfLines(File file) throws FileNotFoundException {
        int wc = 0;
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            wc++;
            sc.nextLine();
        }
        return wc;
    }

    private void loadData() throws FileNotFoundException {
        System.out.println("loading data...");
        File CountryFile = new File("Countries.txt");
        File ProvFile = new File("Provinces_States.txt");
        File CityFile = new File("Cities.txt");

        countries = new ArrayList<>(calculateNumberOfLines(CountryFile));
        provinces = new ArrayList<>(calculateNumberOfLines(ProvFile));
        cities = new ArrayList<>(calculateNumberOfLines(CityFile));

        Scanner sc = new Scanner(CountryFile);

        while (sc.hasNextLine()) {
            countries.add(sc.nextLine());
        }

        sc = new Scanner(ProvFile);
        while (sc.hasNextLine()) {
            provinces.add(sc.nextLine());
        }
        sc = new Scanner(CityFile);
        while (sc.hasNextLine()) {
            cities.add(sc.nextLine());
        }

        //System.out.println(provinces);
    }

    @FXML
    private void addNewProfile() {
        //TODO
    }

    @FXML
    private void modifyProfile() {
        //TODO
    }

    @FXML
    private void close() {
        //TODO
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        employees = new LinkedList<>();

        try {
            loadData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
