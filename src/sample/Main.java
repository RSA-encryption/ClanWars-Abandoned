package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.classes.loginHandler;

import java.awt.*;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/fxml/1.fxml"));
        loginHandler controller = new loginHandler(primaryStage);
        loader.setController(controller);
        Parent root = (Parent)loader.load();
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root,1400,800));;
        primaryStage.show();
    }


    public static void main(String[] args) {launch(args);}
}
