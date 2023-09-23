package controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import flight.Flight;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import multitoggle.MultiToggle;
import multitoggle.MultiToggleGroup;
import seat.Seat;
import seat.SeatButton;
import seat.SeatsLoader;

/**
 * @author Jennyston Jeyachandra
 * @date 12-06-2023
 * @version 1.0
 */
public class SeatSelectionController {

    private static final int NUM_SEAT_COLUMNS_SPLIT = 3;

    private static final String SEAT_ARRANGMENT_PATH = 
        "../res/seat_arrangement.txt";
    private static final String OCCUPIED_SEATS_PATH = 
        "../res/occupied_seats.txt";


    private FlightBookingController main;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnPrevious;

    @FXML
    private Label lblPassengerRemain;

    @FXML
    private HBox seatSelectionPane;

    private MultiToggleGroup multiGroup;

    private int numPassengers;

    private Map<String, SeatButton> btnSeats;

    /**
     * Initializes reference to main controller for accessing main controller
     * methods.
     * @param mainController Reference to main controller.
     */
    public void init(FlightBookingController mainController) {
        main = mainController;
    }

    @FXML
    public void initialize() {

        //indicate number of passenger seats to be selected
        lblPassengerRemain.setText("Remaining Passengers: " + 0);

        /*set max selectable to 0 since number of passengers will be determined
        later*/
        multiGroup = new MultiToggleGroup(0);

        //load SeatButton Map
        btnSeats = createSeatButtons();

        /*this event handler will update remaining passengers when a SeatButton
        is clicked*/
        EventHandler<Event> multiBtnClickHandle =
            e -> updateRemainingPassengers();
        
        for(SeatButton btnSeat : btnSeats.values()) {
            
            //assign the same MultiToggleGroup to every SeatButton
            btnSeat.setMultiToggleGroup(multiGroup);

            //assign remaining passenger event handler to every seat
            btnSeat.addEventHandler(ActionEvent.ACTION, multiBtnClickHandle);

        }

        //set up left and right GridPanes which will display SeatButtons
        GridPane leftGridPane = new GridPane();
        leftGridPane.setHgap(5);
        leftGridPane.setVgap(5);

        GridPane rightGridPane = new GridPane();
        rightGridPane.setHgap(5);
        rightGridPane.setVgap(5);

        //add all SeatButtons to left and right GridPanes
        for(SeatButton btnSeat : btnSeats.values()) {

            //obtain SeatButton Seat location (row and col)
            //make row start at 0 since GridPane row starts at 0
            int row = btnSeat.getSeatRowNum() - 1;

            /*make column start at 0 since grid column starts at 0 (remove 
            'A' offset)*/
            int col = btnSeat.getSeatColumnLetter() - 'A';

            /*if column is before the seat column split, add SeatButton to left
            GridPane, otherwise right*/
            if(col < NUM_SEAT_COLUMNS_SPLIT) {
                leftGridPane.add(btnSeat, col, row);
            }
            else {
                rightGridPane.add(btnSeat, col, row);
            }
        }

        seatSelectionPane.getChildren().add(leftGridPane);
        seatSelectionPane.getChildren().add(rightGridPane);
        seatSelectionPane.setSpacing(30);
    }

    /**
     * Assigns number of passengers being booked.
     * @param passengers Number of passengers.
     */
    public void setNumPassengers(int passengers) {
        numPassengers = passengers;

        multiGroup.clearSelection();
        multiGroup.setMaxSelectable(passengers);
        updateRemainingPassengers();
    }

    public void updateRemainingPassengers() {
        //update remaining passenger label
        lblPassengerRemain.setText("Remaining Passengers: " + 
            (numPassengers - multiGroup.getSelectedCount()));
    }

    private Map<String, SeatButton> createSeatButtons() {

        Map<String, SeatButton> btnSeats = new HashMap<>();

        //load seat type arrangement
        Map<String, Seat> seats = SeatsLoader.load(
            getClass().getResource(SEAT_ARRANGMENT_PATH));

        //create SeatButton instances
        for(Seat seat : seats.values()) {
            btnSeats.put(seat.toString(), new SeatButton(seat));
        }

        return btnSeats;
    }

    private void applyOccupiedSeats(String date, String time, 
        String depart, String dest) {

        //clear selected seats and clear occupied seat state for all seats
        multiGroup.clearSelection();
        for(SeatButton btnSeat : btnSeats.values()) {
            btnSeat.setOccupied(false);
        }

        //load occupied seats from file for specified flight info
        List<String> occupiedSeats = SeatsLoader.loadOccupied(date, time,
            depart, dest, getClass().getResource(OCCUPIED_SEATS_PATH));

        //apply occupied property to appropriate seats
        for(String occupied : occupiedSeats) {
            btnSeats.get(occupied).setOccupied(true);
        }
    }

    @FXML
    public void submit(ActionEvent event) {
        //if all passengers do not have seat selected
        if(numPassengers != multiGroup.getSelectedCount()) {
            //issue warning dialog
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Unselected seats");
            alert.setHeaderText((numPassengers - 
            multiGroup.getSelectedCount()) + 
            " passenger(s) have unselected seat(s)");
            alert.setContentText("Not all passengers have seats selected. " + 
                "Please select more seats.");
            alert.show();
        }
        //otherwise, all passengers have seats, proceed to next scene
        else {
            main.getPaymentInfoController().setTotal(getTotalCost());
            main.nextScene();
        }
    }

    private double getTotalCost() {
        double total = 0;
        
        //calculate total based on selected seat types
        List<MultiToggle> list = multiGroup.getSelected();
        for(MultiToggle toggle : list) {
            total += ((SeatButton) toggle).getSeatCost();
        }

        return total;
    }

    /**
     * Retrieve all selected seats as List.
     * @return Selected seats in List.
     */
    public List<Seat> getSelectedSeats() {

        //create a list and populate with selected seats
        List<Seat> seats= new ArrayList<>();

        List<MultiToggle> list = multiGroup.getSelected();
        for(MultiToggle toggle : list) {
            seats.add(((SeatButton)toggle).getSeat());
        }

        return seats;
        
    }

    /**
     * Set flight info so occupied seats are loaded for specified flight.
     * @param flight Flight information.
     */
    public void setFlightInfo(Flight flight) {
        applyOccupiedSeats(flight.getDate(), flight.getTime(), 
            flight.getDepart(), flight.getDest());
    }

    @FXML
    void backPressed(ActionEvent event) {
        main.previousScene();
    }
}