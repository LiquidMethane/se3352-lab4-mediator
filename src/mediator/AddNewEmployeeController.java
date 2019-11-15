package mediator;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddNewEmployeeController implements Initializable {

    @FXML
    private TextField eid;

    @FXML
    private TextField name;

    @FXML
    private ComboBox countryChoice;

    @FXML
    private ComboBox provChoice;

    @FXML
    private ComboBox cityChoice;

    @FXML
    private TextField postCode;

    @FXML
    private TextField addr;

    private List<Employee> employees;
    private List<String> countries, provinces, cities;

    void setModel(List<Employee> employees, List<String> countries, List<String> provinces, List<String> cities) {
        this.employees = employees;
        this.countries = countries;
        this.provinces = provinces;
        this.cities = cities;
        countryChoice.setItems(FXCollections.observableArrayList(countries));
    }

    @FXML
    private void handleEvent(javafx.event.ActionEvent e) {
        widgetChanged((javafx.scene.control.Control)e.getSource());
    }

    public void widgetChanged(javafx.scene.control.Control widget) {
        System.out.println("widget changed...");
        if (widget == countryChoice) {
            provChoice.setDisable(false);
        }
    }

    @FXML
    private void save() {

    }

    @FXML
    private void cancel() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        provChoice.setDisable(true);
        cityChoice.setDisable(true);
        postCode.setDisable(true);
        addr.setDisable(true);
    }

}
