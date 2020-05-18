package application;

import Controllers.DataBaseController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/main.fxml"));
        primaryStage.setTitle("Customer Management");
        primaryStage.getIcons().add(new Image(new FileInputStream("src/CSS/atomix_user31.png")));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        try {
            initDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        launch(args);
    }

    private static void initDB() throws SQLException {
        DataBaseController.createCustomerTable();
    }
}
