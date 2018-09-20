import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class clockPannelController  {

    ObservableList<Alarm> observableList;
    @FXML
    public void initialize() throws IOException {

        GraphicsContext gc=canvas.getGraphicsContext2D();
       for(int i=0;i<10;i++)
        {
            File imageFile = new File("digit"+i+".png");
            String fileLocation = imageFile.toURI().toString();
            digits[i]= new Image(fileLocation);
        }

        for(int i=0;i<10;i++)
        {
            File imageFile = new File("digitmini"+i+".jpg");
            String fileLocation = imageFile.toURI().toString();
            digitsmini[i]= new Image(fileLocation);
        }

        List<Alarm> list = new ArrayList<Alarm>();
        list.add(new Alarm(19,41,10));
        list.add(new Alarm(21,40,10));
        list.add(new Alarm(12,13,20));
        list.add(new Alarm(19,30,20));
        observableList = FXCollections.observableList(list);
        FXCollections.sort(observableList,new alarmComparator());

        column1.setCellValueFactory(new PropertyValueFactory<Alarm,Integer>("Hour"));
        column2.setCellValueFactory(new PropertyValueFactory<Alarm,Integer>("Minutes"));
        table.setItems(observableList);


        AnimationTimer timer = new AnimationTimer() {
            long t1=0;
            boolean visible=false;
            @Override
            public void handle(long now) {
                Calendar cal = new GregorianCalendar();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int min = cal.get(Calendar.MINUTE);
                int sec = cal.get(Calendar.SECOND);

                drawNumber(gc,40,125,hour);
                drawNumber(gc,220,125,min);
                drawMiniNumber(gc,400,125,sec);

                long t2=now;
                if(t2-t1>1e+9)
                {
                    seperator.setVisible(visible);
                    visible=!visible;
                    t1=t2;
                    gc.clearRect(0,0,600,600);
                    for (Alarm i:observableList)
                    {
                        if(i.getHour()==hour && i.getMinutes()==min && i.getSeconds()==sec)
                        {
                            System.out.println("BUDZIK!!!");
                            String musicFile = "alarmSound.mp3";     // For example
                            Media sound = new Media(new File(musicFile).toURI().toString());
                            MediaPlayer mediaPlayer = new MediaPlayer(sound);
                            mediaPlayer.play();
                        }
                    }
                }

                FXCollections.sort(observableList,new alarmComparator());
                table.setItems(observableList);

            }

        };
        timer.start();
    }


    Image [] digits=new Image[10];
    Image [] digitsmini=new Image[10];


    void drawNumber(GraphicsContext gc, int x, int y, int value)
    {
        int pc=value/10;
        int dc=value%10;

        gc.drawImage(digits[pc],x,y);
        gc.drawImage(digits[dc],x+digits[pc].getWidth()+5,y);
    }

    void drawMiniNumber(GraphicsContext gc, int x, int y, int value)
    {
        int pc=value/10;
        int dc=value%10;

        gc.drawImage(digitsmini[pc],x,y);
        gc.drawImage(digitsmini[dc],x+digitsmini[pc].getWidth()+5,y);
    }

    @FXML
    private Canvas canvas;

    @FXML
    private Label seperator;

    @FXML
    private TableView<Alarm> table;

    @FXML
    private TableColumn<Alarm, Integer> column1;

    @FXML
    private TableColumn<Alarm, Integer> column2;

    @FXML
    private ContextMenu contextMenu;

    @FXML
    void removeAlarm(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
            table.getItems().remove(selectedIndex);
    }

    @FXML
    private Button addAlarmButton;

    @FXML
    void newAlarm(ActionEvent event) {
        Stage stage = new Stage();

        Parent root;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("newWindow.fxml"));

            root=fxmlLoader.load();
            newWindowController controller=fxmlLoader.getController();
            controller.setTableItems(observableList);

           // Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Scene scene=new Scene(root);
            stage.setTitle("Add alarm.");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }


}



