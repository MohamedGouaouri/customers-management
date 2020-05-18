package Controllers;

import Util.AlertUser;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerController {

    @FXML
    private JFXTextField customerName;

    @FXML
    private JFXTextField customerEmail;

    @FXML
    private JFXTextField customerPhoneNumber;

    @FXML
    public void add(ActionEvent actionEvent) {

        String sql = "INSERT INTO customers (name, email, phone) VALUES (?, ?, ?)";
        Connection connection = DataBaseController.getConnection();
        boolean inputIsValid = validateUserInput();
        if (connection != null && inputIsValid){
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, customerName.getText());
                preparedStatement.setString(2, customerEmail.getText());
                preparedStatement.setString(3, customerPhoneNumber.getText());
                preparedStatement.execute();
                AlertUser.alertMessage("Customer Added Successfully", Alert.AlertType.CONFIRMATION);

            } catch (SQLException e) {
                AlertUser.alertMessage("Customer Cannot be added to the database", Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        }
    }
    @FXML
    public void edit(ActionEvent actionEvent) {

    }
    private boolean validateUserInput() {
        return true;
    }

    @FXML
    public void reset(ActionEvent actionEvent) {
        customerName.setText("");
        customerEmail.setText("");
        customerPhoneNumber.setText("");
    }


}
