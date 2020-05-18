package Util;

import javafx.scene.control.Alert;

public class AlertUser {

    public static void alertMessage(String message, Alert.AlertType alertType){
        Alert alert = new Alert(alertType);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
