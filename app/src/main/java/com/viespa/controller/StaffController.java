package com.viespa.controller;

import com.viespa.models.Role;
import com.viespa.models.Staff;
import com.viespa.utils.DateForm;
import com.viespa.utils.Md5;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class StaffController implements Initializable {

    private final String default_password = Md5.getMD5("123123");
    @FXML
    TableView<Staff> table_staff;

    @FXML
    TableColumn<Staff, String> column_account;

    @FXML
    TableColumn<Staff, String> column_fullname;

    @FXML
    TableColumn<Staff, String> column_phone;

    @FXML
    TableColumn<Staff, String> column_address;

    @FXML
    TableColumn<Staff, String> column_role;

    @FXML
    TableColumn<Staff, String> column_joindate;

    @FXML
    TableColumn<Staff, String> column_enddate;

    @FXML
    private TableColumn<Staff, String> column_status;

    @FXML
    TextField input_fullname;

    @FXML
    TextField input_phone;

    @FXML
    TextField input_email;

    @FXML
    TextField input_address;

    @FXML
    DatePicker input_dob;

    @FXML
    DatePicker input_joindate;

    @FXML
    DatePicker input_enddate;

    @FXML
    TextField input_account;

    @FXML
    ChoiceBox<String> input_role;

    @FXML
    ChoiceBox<String> input_status;

    @FXML
    Button buttonAddNew;
    int id;
    int myIndex;
    @FXML
    private Button buttonCancel;
    @FXML
    private Button buttonUpdate;

    @FXML
    void buttonCancel() {
        input_fullname.setText("");
        input_phone.setText("");
        input_email.setText("");
        input_address.setText("");
        input_dob.setValue(null);
        input_joindate.setValue(null);
        input_account.setText("");
        input_role.setValue("");
        input_status.setValue("");
        buttonUpdate.setDisable(true);
        buttonAddNew.setDisable(false);
        input_enddate.setDisable(true);
        table_staff.getSelectionModel().select(null);
    }

    public void buttonAddNew() throws SQLException {
        String val_fullname = input_fullname.getText().trim();
        String val_phone = input_phone.getText().trim();
        String val_email = input_email.getText().trim();
        String val_address = input_address.getText().trim();
        LocalDate val_dob = input_dob.getValue();
        LocalDate val_joindate = input_joindate.getValue();
        String val_account = input_account.getText().trim().toLowerCase();
        String val_role = Role.queryRoleId(input_role.getValue());
        String val_status ;
        if(input_status.getValue().equals("Active")){
            val_status = "0";
        }else {
            val_status = "1";
        }

        if (val_fullname.isEmpty() || val_phone.isEmpty() || val_email.isEmpty() || val_address.isEmpty() || val_dob == null || val_joindate == null || val_account.isEmpty() || val_role == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Input can not empty for this request");
            alert.show();
            return;
        }

        if (Integer.parseInt(val_role) == 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Staff cannot set role 1");
            alert.show();
            return;
        } else if (Staff.checkDuplicate(val_account)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("The account is already on the system ! ");
            alert.show();
            return;
        } else {
            Staff.addStaff(val_account, default_password, val_fullname, val_address, val_email, val_phone, Integer.parseInt(val_role), val_dob, val_joindate,val_status);
            input_fullname.setText("");
            input_phone.setText("");
            input_email.setText("");
            input_address.setText("");
            input_dob.setValue(null);
            input_joindate.setValue(null);
            input_account.setText("");
            input_role.setValue("");
            input_status.setValue("");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Create Staff Success !");
            alert.setContentText("Default password : 123123");
            alert.show();
        }

        table();
    }

    public void buttonUpdate() throws SQLException {
        myIndex = table_staff.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(table_staff.getItems().get(myIndex).getId()));

        String val_fullname = input_fullname.getText().trim();
        String val_phone = input_phone.getText().trim();
        String val_email = input_email.getText().trim();
        String val_address = input_address.getText().trim();
        LocalDate val_dob = input_dob.getValue();
        LocalDate val_joindate = input_joindate.getValue();
        String val_account = input_account.getText().trim().toLowerCase();
        String val_role = Role.queryRoleId(input_role.getValue());
        LocalDate val_enddate = input_enddate.getValue();
        String val_status ;
        if(Objects.equals(input_status.getValue(), "Active")){
            val_status = "0";
        }else {
            val_status = "1";
        }

        if (val_fullname.isEmpty() || val_phone.isEmpty() || val_email.isEmpty() || val_address.isEmpty() || val_dob == null || val_joindate == null || val_account.isEmpty() || val_role == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Input can not empty for this request");
            alert.show();
            return;
        }

        if (Integer.parseInt(val_role) == 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Staff cannot set role 1");
            alert.show();
            return;
        } else {
            Staff.updateStaff(id, val_account, val_fullname, val_address, val_email, val_phone, Integer.parseInt(val_role), val_dob, val_joindate, val_enddate,val_status);
            input_fullname.setText("");
            input_phone.setText("");
            input_email.setText("");
            input_address.setText("");
            input_dob.setValue(null);
            input_joindate.setValue(null);
            input_account.setText("");
            input_role.setValue("");
            input_status.setValue("");
        }

        table();
    }

    public void table() {
        ObservableList<Staff> staffs = Staff.getAllStaffs();
        table_staff.setItems(staffs);
        column_account.setCellValueFactory(f -> f.getValue().accountProperty());
        column_fullname.setCellValueFactory(f -> f.getValue().fullNameProperty());
        column_phone.setCellValueFactory(f -> f.getValue().phoneProperty());
        column_address.setCellValueFactory(f -> f.getValue().addressProperty());
        column_role.setCellValueFactory(f -> f.getValue().roleProperty());
        column_joindate.setCellValueFactory(f -> DateForm.convert(String.valueOf(f.getValue().joinDateProperty().getValue())));
        column_enddate.setCellValueFactory(f -> f.getValue().endDateProperty().getValue() != null
                ? DateForm.convert(String.valueOf(f.getValue().endDateProperty().getValue()))
                : new SimpleStringProperty(""));
        column_status.setCellValueFactory(f -> f.getValue().statusProperty().getValue().equals("0")
                ? new SimpleStringProperty("Active")
                : new SimpleStringProperty("Inactive"));

        input_enddate.setDisable(true);
        buttonUpdate.setDisable(true);
        buttonAddNew.setDisable(false);

        table_staff.setRowFactory(it -> {
            TableRow<Staff> myRow = new TableRow<>();

            myRow.setOnMouseClicked(event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = table_staff.getSelectionModel().getSelectedIndex();
                    id = Integer.parseInt(String.valueOf(table_staff.getItems().get(myIndex).getId()));
                    input_fullname.setText(table_staff
                            .getItems()
                            .get(myIndex)
                            .getFullname());
                    input_phone.setText(table_staff
                            .getItems()
                            .get(myIndex)
                            .getPhone());
                    input_email.setText(table_staff
                            .getItems()
                            .get(myIndex)
                            .getEmail());
                    input_address.setText(table_staff
                            .getItems()
                            .get(myIndex)
                            .getAddress());
                    input_dob.setValue(table_staff
                            .getItems()
                            .get(myIndex)
                            .getDob());
                    input_joindate.setValue(table_staff
                            .getItems()
                            .get(myIndex)
                            .getJoinDate());
                    input_enddate.setValue(table_staff
                            .getItems()
                            .get(myIndex)
                            .getEndDate());
                    input_account.setText(table_staff
                            .getItems()
                            .get(myIndex)
                            .getAccount());
                    input_role.setValue(table_staff
                            .getItems()
                            .get(myIndex)
                            .getRole());
                    if(table_staff.getItems().get(myIndex).getStatus().equals("0")){
                        input_status.setValue("Active");
                    }else {
                        input_status.setValue("Inactive");
                    }

                    input_enddate.setDisable(false);
                    buttonUpdate.setDisable(false);
                    buttonAddNew.setDisable(true);
                }
            });
            return myRow;
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table();
        ObservableList<Role> roles = Role.getAllRole();
        assert roles != null;
        roles.stream().map(Role::getRole).forEach(t -> input_role.getItems().add(t));

        input_status.getItems().add("Active");
        input_status.getItems().add("Inactive");
    }
}
