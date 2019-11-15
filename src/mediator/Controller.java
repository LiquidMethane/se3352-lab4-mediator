package mediator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
