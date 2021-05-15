package sample.classes;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.*;


public class bansHandler extends handlerUtils implements Initializable {
    private double xOffset = 0;
    private double yOffset = 0;
    private HashMap<String, String> userData;
    private List<ProcessedJSON.Array> data;
    @FXML
    private ImageView LogOut, Bans, Rest;
    @FXML
    private Label name;
    @FXML
    private VBox info, info1;
    @FXML
    private GridPane grid;
    @FXML
    private void MouseClickedRootAction(MouseEvent event) throws IOException {
        StringBuffer buffer = new StringBuffer();
        buffer.append(event.getSource().toString());
        int id = Integer.parseInt(buffer.substring(buffer.indexOf(",")-1,buffer.indexOf(",")));
        this.changeScene(event, id ,userData);
    }
    public bansHandler(HashMap<String, String> data, Stage stage){
        this.userData = data;
        EventHandler<MouseEvent> click = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = stage.getX() - event.getScreenX();
                yOffset = stage.getY() - event.getScreenY();
            }
        };
        EventHandler<MouseEvent> drag = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() + xOffset);
                stage.setY(event.getScreenY() + yOffset);
            }
        };
        stage.addEventFilter(MouseEvent.MOUSE_PRESSED, click);
        stage.addEventFilter(MouseEvent.MOUSE_DRAGGED, drag);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            name.setText(userData.get("username"));
            String post = "{\"username\":\"" + userData.get("username") + "\",\"password\":\"" + userData.get("password") + "\",\"type\":\"bans\"}";
            dataInnit(this.info, post);
            post = "{\"username\":\"" + userData.get("username") + "\",\"password\":\"" + userData.get("password") + "\",\"type\":\"comms\"}";
            dataInnit(this.info1, post);
        } catch (MalformedURLException | ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
