package mediator;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class ModEmployeeController implements Initializable {

    @FXML
    private ComboBox eidChoice;

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

    @FXML
    private Button saveBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button delBtn;

    private List<Employee> employees;
    private List<String> countries;
    private List<String[]> provinces, cities;

    void setModel(List<Employee> employees, List<String> countries, List<String[]> provinces, List<String[]> cities) {
        this.employees = employees;
        this.countries = countries;
        this.provinces = provinces;
        this.cities = cities;
        //countryChoice.setItems(FXCollections.observableArrayList(countries));
        List<Integer> eidList = new LinkedList<>();
        for (Employee employee: employees) {
            eidList.add(employee.getEid());
        }
        eidChoice.setItems(FXCollections.observableArrayList(eidList));
    }

    @FXML
    private void handleEvent(javafx.event.ActionEvent e) {
        widgetChanged((javafx.scene.control.Control)e.getSource());
    }

    public void widgetChanged(javafx.scene.control.Control widget) {
        System.out.println("widget changed...");


        if (widget == eidChoice && eidChoice.getValue() != null) {
            Employee temp = null;
            int eid = (Integer)eidChoice.getValue();
            for (Employee emp: employees) {
                if (emp.getEid() == eid) temp = emp;
                break;
            }
            name.setText(temp.getName());
            countryChoice.setItems(FXCollections.observableArrayList(countries));
            countryChoice.setValue(temp.getCountry());
            countryChoice.setDisable(false);

            List<String> provinceList = new LinkedList<>();
            for (String[] str: provinces) {
                if (str[0].equals(temp.getCountry())) {
                    provinceList.add(str[1]);
                }
            }
            provChoice.setItems(FXCollections.observableArrayList(provinceList));
            provChoice.setValue(temp.getProv());
            provChoice.setDisable(false);

            //provChoice.setItems();

        }

        if (widget == countryChoice && countryChoice.getValue() != null) {
            String country = (String)countryChoice.getValue();
            List<String> provinceList = new LinkedList<>();
            for (String[] str: provinces) {
                if (str[0].equals(country)) {
                    provinceList.add(str[1]);
                }
            }
            provChoice.setItems(FXCollections.observableArrayList(provinceList));
            provChoice.setValue(null);
            provChoice.setDisable(false);

            cityChoice.setValue(null);
            cityChoice.setDisable(true);

            postCode.setDisable(true);
            addr.setDisable(true);
            saveBtn.setDisable(true);

        } else if (widget == provChoice && provChoice.getValue() != null && countryChoice.getValue() != null) {
            String country = (String)countryChoice.getValue();
            String prov = (String)provChoice.getValue();
            List<String> cityList = new LinkedList<>();
            for (String[] str: cities) {
                if (str[0].equals(country) && str[1].equals(prov)) {
                    cityList.add(str[2]);
                }
            }
            cityChoice.setItems(FXCollections.observableArrayList(cityList));
            cityChoice.setValue(null);
            cityChoice.setDisable(false);

            postCode.setDisable(true);
            addr.setDisable(true);
            saveBtn.setDisable(true);

        } else if (widget == cityChoice && cityChoice.getValue() != null && provChoice.getValue() != null && countryChoice.getValue() != null) {
            postCode.setDisable(false);
            addr.setDisable(false);
            saveBtn.setDisable(false);
        }
    }

    @FXML
    private void save(javafx.event.ActionEvent e) {

    }

    @FXML
    private void cancel(javafx.event.ActionEvent e) {
        ((Stage)(((javafx.scene.control.Control)e.getSource()).getScene().getWindow())).close();
    }

    @FXML
    private void delete(javafx.event.ActionEvent e) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setDisable(true);
        countryChoice.setDisable(true);
        provChoice.setDisable(true);
        cityChoice.setDisable(true);
        postCode.setDisable(true);
        addr.setDisable(true);
        saveBtn.setDisable(true);
        delBtn.setDisable(true);
    }
}
