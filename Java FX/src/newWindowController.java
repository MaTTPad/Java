import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.lang.model.type.NullType;
import java.io.IOException;
public class newWindowController {

    @FXML
    public void initialize() throws IOException {

    }
    @FXML
    private TextField hourTextField;

    @FXML
    private TextField minuteTextField;

    @FXML
    private Label hourLabel;

    @FXML
    private Label minuteLabel;
    @FXML
    private Button newAlarmButton;

    private ObservableList<Alarm> tableItems ;

    public void setTableItems(ObservableList<Alarm> tableItems) {
        this.tableItems = tableItems ;
    }

    @FXML
    void newAlarm(ActionEvent event) throws IOException {

        if(!hourTextField.getText().trim().equals("") && !minuteTextField.getText().trim().equals(""))
        {
            int hour= Integer.parseInt(hourTextField.getText());
            int min = Integer.parseInt(minuteTextField.getText());
            if(hour>=0 && hour<=24 && min>=0 && min<=60)
            {

                tableItems.add(new Alarm(hour,min,0));
                final Node source = (Node) event.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                stage.close();

            }

            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("Incorrect data!");
                alert.setContentText("Hour must be greater than or equal to 0, and less than or equal to 24. " +
                        "Min must be greater than or equal to 0, and less than or equal to 60. ");


                alert.showAndWait();
            }

        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Empty text field!");
            alert.setContentText("You must enter data.");


            alert.showAndWait();
        }

    }
}