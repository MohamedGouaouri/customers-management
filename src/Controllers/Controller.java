package Controllers;

import application.kernel.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TableView<Customer> customersTable;

    @FXML
    private TableColumn<Customer, Integer> idColumn;

    @FXML
    private TableColumn<Customer, String> nameColumn;

    @FXML
    private TableColumn<Customer, String> emailColumn;

    @FXML
    private TableColumn<Customer, String> phoneColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCustomerTable();
    }

    private void initCustomerTable() {
        // fill customer table from database records
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        customersTable.getColumns().add(idColumn);
        customersTable.getColumns().add(nameColumn);
        customersTable.getColumns().add(emailColumn);
        customersTable.getColumns().add(phoneColumn);
        String sql = "SELECT * FROM customers";

        Connection connection = DataBaseController.getConnection();
        try{
            Statement statement = connection.createStatement();
            ResultSet customers = statement.executeQuery(sql);
            while (customers.next()){
                customersTable.getItems().add(new Customer(customers.getInt("id"),
                        customers.getString("name"),
                        customers.getString("email"),
                        customers.getString("phone")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void addCustomer(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../FXML/addCustomerView.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Add Customer");
        stage.setResizable(false);
        stage.showAndWait();
        customersTable.getItems().clear();
        updateCustomersTable();
    }

    @FXML
    void deleteCustomer(ActionEvent event) throws SQLException {
        //System.out.println(customersTable.getSelectionModel().getSelectedItem().getId());
        // get selected data
        Customer customerToDelete = customersTable.getSelectionModel().getSelectedItem();
        // prepare sql statement
        String sql = "DELETE FROM customers WHERE id = ?";
        Connection connection = DataBaseController.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, customerToDelete.getId());
        preparedStatement.executeUpdate();
        // update the table
        customersTable.getItems().clear();
        updateCustomersTable();
    }

    @FXML
    void editCustomer(ActionEvent event) throws IOException {
        selectedCustomer = customersTable.getSelectionModel().getSelectedItem();
        Parent parent = FXMLLoader.load(getClass().getResource("../FXML/editCustomerView.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Edit Customer");
        stage.setResizable(false);
        stage.showAndWait();
    }

    public  void updateCustomersTable(){
        // fill customer table from database records
        String sql = "SELECT * FROM customers";
        Connection connection = DataBaseController.getConnection();
        try{
            Statement statement = connection.createStatement();
            ResultSet customers = statement.executeQuery(sql);
            while (customers.next()){
                customersTable.getItems().add(new Customer(customers.getInt("id"),
                        customers.getString("name"),
                        customers.getString("email"),
                        customers.getString("phone")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private Customer selectedCustomer;
    public Customer getTableSelectedItem(){

        return selectedCustomer;
    }
}
