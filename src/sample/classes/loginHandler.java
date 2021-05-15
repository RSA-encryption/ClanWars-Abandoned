package sample.classes;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class loginHandler extends handlerUtils{
    private double xOffset = 0;
    private double yOffset = 0;
    public HashMap<String, String> userdata = new HashMap<>();
    @FXML
    private Button loginButton;
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;
    @FXML
    private Label error;
    @FXML
    public void loginAttempt(MouseEvent event) throws IOException {
        try {
            userdata.put("username", username.getText());
            userdata.put("password", password.getText());
            URL url = new URL("http://62.171.140.202/endpoint/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setDoOutput(true);
            String post = "{\"authorize\":\"true\",\"username\":\"" + userdata.get("username") + "\",\"password\":\"" + userdata.get("password") + "\"}";
            try(OutputStream out = conn.getOutputStream()) {
                byte[] input = post.getBytes("utf-8");
                out.write(input, 0, input.length);
            }
            if (conn.getResponseCode() == 200) { //Login api returned 200 which means our login was ok
                changeScene(event, 2 ,userdata);
            }
            else{
                error.setText("Invalid username or password.");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public loginHandler(Stage stage){
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

}
