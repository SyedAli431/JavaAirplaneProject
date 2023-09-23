package controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.Alert.AlertType;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import flight.Flight;

/**
 * @author Farshid Fitzner Amini
 * @date 12-06-2023
 * @version 1.0
 */
public class FlightInfoController {

    private final static int LIST_TO_INDEX = 0;
    private final static int LIST_FROM_INDEX = 1;
    private final static int LIST_NUM_PASS_INDEX = 2;
    private final static int LIST_TIME_INDEX = 3;
    private final static int LIST_DATE_INDEX = 4;

    /**
     * Reference to main controller.
     */
    private FlightBookingController main;

    @FXML
    private DatePicker DatePick;

    @FXML
    private MenuItem itmThunder;

    @FXML
    private MenuItem itmToronto;

    @FXML
    private MenuButton btnselectTo;

    @FXML
    private MenuButton btnselectFrom;

    @FXML 
    private MenuItem itmNorthBay;

    @FXML
    private SplitMenuButton TimeSelect;

    @FXML
    private MenuItem itmtime1;

    @FXML
    private MenuItem itmTime2;

    @FXML 
    private Button Search;
    
    @FXML
    private ComboBox<Integer> cmbNumTicket;

    public String selectedItemFrom;
    public String selectedItemTo;
    public String selectedItemPass;
    public String selectedItemTime;

    /**
     * Stores data from all GUI fields
     */
    private ArrayList<String> Scene1;
    
    /**
     * Initializes reference to main controller for accessing main controller
     * methods.
     * @param mainController Reference to main controller.
     */
    public void init(FlightBookingController mainController) {
        main = mainController;
    }

    public void initialize(){

       Scene1 = new ArrayList<String>();
       for (int i = 0; i < 5; i++){
        Scene1.add("");
       }

       ObservableList<Integer> numList = FXCollections.observableArrayList();
       for(int i = 1; i <= 10; i++) {
            numList.add(i);
       }
       cmbNumTicket.setItems(numList);
    
       cmbNumTicket.setOnAction(event -> {
            Scene1.set(2, cmbNumTicket.getSelectionModel().getSelectedItem().
                    toString());
       });

        itmThunder.setOnAction(event -> {
            btnselectTo.textProperty().bind(itmThunder.textProperty());
            Scene1.set(0,"YQT");
            selectedItemTo = "YQT";
        });
        itmToronto.setOnAction(event -> {
            btnselectTo.textProperty().bind(itmToronto.textProperty());
            Scene1.set(0,"YYZ");
            selectedItemTo = "YYZ";
        });
        itmNorthBay.setOnAction(event -> {
            btnselectFrom.textProperty().bind(itmNorthBay.textProperty());
            Scene1.set(1,"YYB");
            selectedItemFrom = "YYB";
        });
        

        itmtime1.setOnAction(event -> {
            TimeSelect.textProperty().bind(itmtime1.textProperty());
            Scene1.set(3,itmtime1.getText());
            selectedItemTime = itmtime1.getText();
        
        });

    
        itmTime2.setOnAction(event -> {
            TimeSelect.textProperty().bind(itmTime2.textProperty());
            Scene1.set(3,itmTime2.getText());
            selectedItemTime = itmTime2.getText();
        
        });
         
        DatePick.setOnAction(event -> {
            Scene1.set(LIST_DATE_INDEX, DatePick.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        });

            Search.setOnAction(e -> {

                boolean displayMissingInfoAlert = false;

                //set display missing info alert to true if a field is empty
                for(String str : Scene1) {
                    if(str.equals("")) {
                        displayMissingInfoAlert = true;
                        break;
                    }
                }

                //display alert dialog if a field is empty
                if(displayMissingInfoAlert) {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Error: Missing information");
                    alert.setHeaderText("One or multiple fields may be empty!\n");
                    alert.setContentText("Please enter missing data.\n");
                    alert.showAndWait();
                }
                //otherwise, data is valid, proceed to next scene
                else {

                for (int i = 0; i < 4; i++){
                    if (i == 0){
                        System.out.println("Your flight details are:");
                    }
                    System.out.println(Scene1.get(i));
                }

                //create and initialize flight object with data from fields
                Flight flight = new Flight(Scene1.get(LIST_DATE_INDEX),
                    Scene1.get(LIST_TIME_INDEX),
                    Scene1.get(LIST_FROM_INDEX),
                    Scene1.get(LIST_TO_INDEX));

                //send flight object to payment info controller
                main.getPaymentInfoController().setFlightInfo(flight);

                /* send flight object and number of passengers being booked
                 * to seat selection controller
                */
                main.getSeatSelectionController().setFlightInfo(flight);
                main.getSeatSelectionController().setNumPassengers(Integer.parseInt(Scene1.get(LIST_NUM_PASS_INDEX)));


                main.nextScene();
            }

        });
        
        
       
    
    }
   
    /**
     * Retrieve flight information.
     * @return Flight information.
     */
    public Flight getFlight() {
        return new Flight(Scene1.get(LIST_DATE_INDEX),
            Scene1.get(LIST_TIME_INDEX),
            Scene1.get(LIST_FROM_INDEX),
            Scene1.get(LIST_TO_INDEX));
    }

}