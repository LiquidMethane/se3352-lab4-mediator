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

    EmployeeList employeeList;

    List<String> countries;
    List<String[]>provinces;
    List<String[]>cities;


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

//        for (String[] province: provinces) {
//            System.out.println(province[0] + "      " + province[1]);
//        }
//
//        for (String[] city: cities) {
//            System.out.println(city[0] + "      " + city[1] + "      " + city[2]);
//        }

    }



    @FXML
    private void addNewProfile() throws IOException {
        //TODO

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddNewEmployee.fxml"));
        Parent addNewProfile = (Parent) fxmlLoader.load();
        AddNewEmployeeController addNewEmployeeController = (AddNewEmployeeController) fxmlLoader.getController();
        addNewEmployeeController.setModel(employeeList, countries, provinces, cities);

        Scene scene = new Scene(addNewProfile);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setTitle("Add New Profile");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    @FXML
    private void modifyProfile() throws IOException {
        //TODO

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModEmployee.fxml"));
        Parent modProfile = (Parent) fxmlLoader.load();
        ModEmployeeController modEmployeeController = (ModEmployeeController) fxmlLoader.getController();
        modEmployeeController.setModel(employeeList, countries, provinces, cities);

        Scene scene = new Scene(modProfile);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setTitle("Modify Profile");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    @FXML
    private void close() {
        //TODO
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        employeeList = new EmployeeList();
        employeeList.populate();

        try {
            loadData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
