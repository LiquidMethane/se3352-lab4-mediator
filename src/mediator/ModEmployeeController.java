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

    private EmployeeList employeeList;
    private List<String> countries;
    private List<String[]> provinces, cities;

    void setModel(EmployeeList employeeList, List<String> countries, List<String[]> provinces, List<String[]> cities) {
        this.employeeList = employeeList;
        this.countries = countries;
        this.provinces = provinces;
        this.cities = cities;
        List<Integer> eidList = employeeList.getEidList();
        System.out.println(eidList);
        eidChoice.setItems(FXCollections.observableArrayList(eidList));
    }

    @FXML
    private void handleEvent(javafx.event.ActionEvent e) {
        widgetChanged((javafx.scene.control.Control)e.getSource());
    }

    public void widgetChanged(javafx.scene.control.Control widget) {
        System.out.println("widget changed...");


        if (widget == eidChoice && eidChoice.getValue() != null) {
            System.out.print("eid set");
            Employee temp = employeeList.find((Integer)eidChoice.getValue());

            //set name, addr and postcode
            name.setText(temp.getName());
            addr.setText(temp.getAddr());
            postCode.setText(temp.getPostcode());
            name.setDisable(false);
            addr.setDisable(false);
            postCode.setDisable(false);

            //enable delete button
            delBtn.setDisable(false);

            //build country combobox
            countryChoice.setItems(FXCollections.observableArrayList(countries));
            countryChoice.setValue(temp.getCountry());
            countryChoice.setDisable(false);

            //build province combobox
            List<String> provinceList = new LinkedList<>();
            for (String[] str: provinces) {
                if (str[0].equals(temp.getCountry())) {
                    provinceList.add(str[1]);
                }
            }
            provChoice.setItems(FXCollections.observableArrayList(provinceList));
            provChoice.setValue(temp.getProv());
            provChoice.setDisable(false);


            //build city combobox
            List<String> cityList = new LinkedList<>();
            for (String[] str: cities) {
                if (str[0].equals(temp.getCountry()) && str[1].equals(temp.getProv())) {
                    cityList.add(str[2]);
                }
            }
            cityChoice.setItems(FXCollections.observableArrayList(cityList));
            cityChoice.setValue(temp.getCity());
            cityChoice.setDisable(false);




        } else if (widget == countryChoice && countryChoice.getValue() != null) {
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
        Employee employee = new Employee((Integer)eidChoice.getValue(),
                name.getText(),
                (String)countryChoice.getValue(),
                (String)provChoice.getValue(),
                (String)cityChoice.getValue(),
                postCode.getText(),
                addr.getText());

        employeeList.modify(employee);
        cancel(e);
    }

    @FXML
    private void cancel(javafx.event.ActionEvent e) {
        ((Stage)(((javafx.scene.control.Control)e.getSource()).getScene().getWindow())).close();
    }

    @FXML
    private void delete(javafx.event.ActionEvent e) {
        employeeList.remove((Integer)eidChoice.getValue());
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
