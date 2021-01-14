package GUI;

import GUI.Controller.mainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("startWindow.fxml"));
        Parent root = loader.load();
        mainWindowController controller = loader.getController();
        controller.setStartWindow(stage);
        Scene scene = new Scene(root, 900, 700);
        scene.getStylesheets().add(getClass().getResource("startWindow.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Toy Language Interpreter");
        stage.show();
    }

    public static void main(String[] args){ launch(args);}
}
