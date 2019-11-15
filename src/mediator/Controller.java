package mediator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    List<Employee> employees;
    List<String> countries;
    List<String[]>provinces;
    List<String[]>cities;


    public List<Employee> getEmployees() {
        return employees;
    }

    private int calculateNumberOfLines(File file) throws FileNotFoundException {
        int wc = 0;
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            wc++;
            sc.nextLine();
        }
        return wc;
    }

    private static String[] parseCity(String str) {
        //manta[0] is country, manta[1] is province and city together

        String[] mantaRay = new String[3];
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ',') {
                mantaRay[0] = str.substring(0, i);
                mantaRay[1] = str.substring(i + 1);
                break;
            }
        }
        String prov = mantaRay[1];

        if (prov.charAt(0) == '"') {
            boolean firstEncounter = true;
            for (int i = 0; i < mantaRay[1].length(); i++) {
                if (prov.charAt(i) == ',') {
                    if (firstEncounter) {
                        firstEncounter = false;
                    } else {
                        mantaRay[1] = prov.substring(1, i - 1);
                        mantaRay[2] = prov.substring(i + 1);
                    }
                }
            }
        } else {
            for (int i = 0; i < mantaRay[1].length(); i++) {
                if (mantaRay[1].charAt(i) == ',') {
                    mantaRay[1] = prov.substring(0, i);
                    mantaRay[2] = prov.substring(i + 1);
                }
            }
        }
        return mantaRay;
    }


    private String[] parseProvince(String str) {
        String[] manta = new String[2];
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ',') {
                manta[0] = str.substring(0, i);
                if (str.charAt(i + 1) == '"') {
                    manta[1] = str.substring(i + 2, str.length() - 1);
                } else manta[1] = str.substring(i + 1);
                break;
            }
        }
        return manta;
    }

    private void loadData() throws FileNotFoundException {
        System.out.println("loading data...");
        File CountryFile = new File("Countries.txt");
        File ProvFile = new File("Provinces_States.txt");
        File CityFile = new File("Cities.txt");

        int numCountry = calculateNumberOfLines(CountryFile);
        int numProv = calculateNumberOfLines(ProvFile);
        int numCity = calculateNumberOfLines(CityFile);

        countries = new ArrayList<>(numCountry);
        List<String> provinceList = new ArrayList<>(numProv);
        provinces = new ArrayList<>(numProv);
        List<String> cityList = new ArrayList<>(numCity);
        cities = new ArrayList<>(numCity);

        Scanner sc = new Scanner(CountryFile);

        while (sc.hasNextLine()) {
            countries.add(sc.nextLine());
        }

        sc = new Scanner(ProvFile);
        while (sc.hasNextLine()) {
            provinceList.add(sc.nextLine());
        }

        sc = new Scanner(CityFile);
        while (sc.hasNextLine()) {
            cityList.add(sc.nextLine());
        }

        for (String province: provinceList) {
            provinces.add(parseProvince(province));
        }

        for (String city: cityList) {
            cities.add(parseCity(city));
        }

        for (String[] province: provinces) {
            System.out.println(province[0] + "      " + province[1]);
        }

        for (String[] city: cities) {
            System.out.println(city[0] + "      " + city[1] + "      " + city[2]);
        }

    }

    private void populateEmployeeProfile() {
        employees.add(new Employee(123456, "John Doe", "Canada", "Alberta", "Edmonton", "1h2 h3g", "1000 This St"));
        employees.add(new Employee(234567, "Jane Doe", "Canada", "Ontario", "Cornwall", "2a3 b4c", "2000 That St"));
        employees.add(new Employee(345678, "Jannie Doe", "United Kingdom", "Doncaster", "Doncaster", "3d4 e5f", "3000 Another St"));
        employees.add(new Employee(456789, "Jean Doe", "United States", "Pennsylvania", "Lincoln", "34532", "4000 The Other St"));


    }

    @FXML
    private void addNewProfile() throws IOException {
        //TODO

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddNewEmployee.fxml"));
        Parent addNewProfile = (Parent) fxmlLoader.load();
        AddNewEmployeeController addNewEmployeeController = (AddNewEmployeeController) fxmlLoader.getController();
        addNewEmployeeController.setModel(employees, countries, provinces, cities);

        Scene scene = new Scene(addNewProfile);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setTitle("Add New Profile");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
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
