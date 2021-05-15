package sample.classes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

public class handlerUtils {
    @FXML
    protected ImageView exit;
    @FXML
    protected void close(MouseEvent event){
        Stage currentStage = (Stage) exit.getScene().getWindow();
        currentStage.close();
    }
    protected void changeScene(MouseEvent event, int id, Object data) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/fxml/" + id + ".fxml"));
        switch(id){
            case 1:{
                loginHandler controller = new loginHandler(stage);
                loader.setController(controller);
                break;
            }
            case 2:{
                bansHandler controller = new bansHandler((HashMap<String, String>) data, stage);
                loader.setController(controller);
                break;
            }
        }
        Parent root = (Parent)loader.load();
        stage.setScene(new Scene(root,1400,800));
        stage.show();
    }
    protected void dataInnit(VBox info, String post) throws IOException {
        URL url = new URL("http://62.171.140.202/endpoint/");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type","application/json; utf-8");
        conn.setDoOutput(true);
        try(OutputStream out = conn.getOutputStream()) {
            byte[] input = post.getBytes("utf-8");
            out.write(input, 0, input.length);
        }
        BufferedReader InputReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        JsonParser parser = new JsonParser();
        JsonObject json = new JsonObject();
        json = (JsonObject) parser.parse(InputReader.readLine());
        JsonReader reader = new JsonReader(new StringReader(json.toString()));
        reader.setLenient(true);
        Gson gson = new Gson();
        ProcessedJSON response = gson.fromJson( reader, ProcessedJSON.class);
        List<ProcessedJSON.Array> data = response.getData();
        ListIterator<ProcessedJSON.Array> Iterate = data.listIterator();
        if(Iterate.hasNext()){
            ProcessedJSON.Array entry = Iterate.next();
            info.getChildren().add(addGrid(entry));
            while (Iterate.hasNext()) {
                entry = Iterate.next();
                if(Iterate.hasNext())
                    info.getChildren().add(addGrid(entry));
                else info.getChildren().add(addGrid(entry));
            }
        }
    }
    protected Pane addGrid(ProcessedJSON.Array entry){
        double[][] values = new double[][]{{100.0,10.0},{135.0,10.0},{135.0,10.0},{135.0,10.0},{135.0,10.0},{240.0,0.0}};
        GridPane grid = new GridPane();
        VBox.setVgrow(grid,Priority.NEVER);
        grid.addColumn(5);
        grid.setUserData(entry);
        grid.getStyleClass().add("grid");
        grid.setGridLinesVisible(true);
        for(double[] v: values){
            ColumnConstraints s = new ColumnConstraints();
            s.setHalignment(HPos.CENTER);
            s.setHgrow(Priority.NEVER);
            s.setMaxWidth(v[0]);
            s.setMinWidth(v[1]);
            s.setPrefWidth(v[0]);
            grid.getColumnConstraints().add(s);
        }
        RowConstraints s = new RowConstraints();
        s.setPrefHeight(40.0);
        s.setVgrow(Priority.NEVER);
        grid.getRowConstraints().add(s);
        ImageView img = new ImageView(new Image("sample/images/tf2.png")); //Centering doesn't work on image view ?? using pane instead
        img.setFitWidth(30.0);
        img.setFitHeight(30.0);
        GridPane.setColumnIndex(img, 0);
        grid.getChildren().add(img);
        values = new double[][]{{20.0,75.0},{23.0,120.0},{25.0,112.0},{21.0,109.0}};
        for(int i = 0; i < values.length; i++){
            Label l = new Label();
            l.setAlignment(Pos.CENTER);
            l.setTextAlignment(TextAlignment.CENTER);
            l.getStyleClass().add("paneLabels");
            l.setMaxHeight(values[i][0]);
            l.setMaxWidth(values[i][1]);
            l.prefHeight(values[i][0]);
            GridPane.setColumnIndex(l,i+1);
            GridPane.setHalignment(l, HPos.CENTER);
            GridPane.setValignment(l, VPos.CENTER);
            switch(i){
                case 0: l.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date(Long.parseLong(entry.getCreated()) * 1000)));break;
                case 1: {
                    float lenght = Float.parseFloat(entry.getLength()) / 3600;
                    if(entry.getLength().equals("0")) l.setText("Permanent");
                    else if(lenght < 1){
                        lenght = Float.parseFloat(entry.getLength()) / 60;
                        l.setText(lenght + " minutes");
                    }
                    else l.setText(lenght + " hours");
                    l.getStyleClass().add("expiration");
                    l.getStyleClass().add("expired");
                    l.setMaxWidth(l.USE_COMPUTED_SIZE);
                    l.setPadding(new Insets( 0.0, 8.0, 0.0, 8.0));
                    break;
                }
                case 2: l.setText(entry.getName());break;
                case 3: l.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date(Long.parseLong(entry.getCreated()) * 1000)));break;
            }
            grid.getChildren().add(l);
        }
        Pane pane = new Pane();
        Button bt = new Button();
        pane.prefHeight(23.0);
        bt.setId("edit");
        bt.setLayoutX(30.0);
        bt.setLayoutY(7.0);
        bt.setMnemonicParsing(false);
        bt.prefHeight(25.0);
        bt.setMinWidth(57.0);
        bt.getStyleClass().add("edit");
        bt.setText("Edit");
        bt.setTextAlignment(TextAlignment.CENTER);
        bt.setTextFill(Color.WHITE);
        pane.getChildren().add(bt);
        bt = new Button();
        bt.setId("delete");
        bt.setLayoutX(119.0);
        bt.setLayoutY(7.0);
        bt.setMnemonicParsing(false);
        bt.prefHeight(25.0);
        bt.setMinWidth(103.0);
        bt.getStyleClass().add("delete");
        bt.setText("Delete");
        bt.setTextAlignment(TextAlignment.CENTER);
        bt.setTextFill(Color.WHITE);
        pane.getChildren().add(bt);
        GridPane.setColumnIndex(pane, values.length + 1);
        grid.getChildren().add(pane);
        return grid;
    }
}
