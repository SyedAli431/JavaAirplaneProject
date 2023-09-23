package controller;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Syed Ali
 * @author Jennyston Jeyachandra
 * @author Farshid Fitzner Amini
 * @author Bhavik Patel
 * @date 12-06-2023
 * @version 1.0
 */
public class FlightBookingController {

    private String[] fxmlNames = {"/view/FlightInfo.fxml", 
        "/view/PassengerInfo.fxml", "/view/SeatSelection.fxml", 
        "/view/PaymentInfo.fxml"};

    //scene index constants
    private final int FLIGHT_INFO_INDEX = 0;
    private final int PASSENGER_INFO_INDEX = 1;
    private final int SEAT_SELECTION_INDEX = 2;
    private final int PAYMENT_INFO_INDEX = 3;

    @FXML
    private AnchorPane contentPane;

    /**
     * Stores the index of the currently displayed scene.
     */
    private int currentSceneIndex;

    //controllers
    private FlightInfoController flightInfoController;
    private PassengerInfoController passengerInfoController;
    private SeatSelectionController seatSelectionController;
    private PaymentInfoController paymentInfoController;

    private Parent[] roots;

    public void initialize() throws IOException {

        //initialize all FXML loaders and roots
        FXMLLoader[] loaders = new FXMLLoader[fxmlNames.length];
        roots = new Parent[fxmlNames.length];
        for(int i = 0; i < loaders.length; i++) {
            loaders[i] = new FXMLLoader(getClass().getResource(fxmlNames[i]));
            roots[i] = loaders[i].load();
        }

        //initialize controllers
        flightInfoController = loaders[FLIGHT_INFO_INDEX].getController();
        passengerInfoController = loaders[PASSENGER_INFO_INDEX].getController();
        seatSelectionController = loaders[SEAT_SELECTION_INDEX].getController();
        paymentInfoController = loaders[PAYMENT_INFO_INDEX].getController();

        //pass this reference so controllers can access parent
        flightInfoController.init(this);
        passengerInfoController.init(this);
        seatSelectionController.init(this);
        paymentInfoController.init(this);

        //display first root (Flight Info) and reset current scene index
        contentPane.getChildren().add(roots[0]);
        currentSceneIndex = 0;
    }

    /**
     * Closes the application.
     */
    public void closeApplication() {
        Stage stage = (Stage)contentPane.getScene().getWindow();
        stage.close();
    }

    /**
     * Switches to next scene.
     */
    public void nextScene() {
        currentSceneIndex++;

        /*ensure index does not exceed the index of the last scene, if so, 
        cap at index of last scene*/
        if(currentSceneIndex >= roots.length) {
            currentSceneIndex = roots.length-1;
        }

        //swap scenes
        contentPane.getChildren().clear();
        contentPane.getChildren().add(roots[currentSceneIndex]);
    }

    /**
     * Switches to previous scene.
     */
    public void previousScene() {
        currentSceneIndex--;

        //enusre index is not negative, if so, cap at 0
        if(currentSceneIndex < 0) {
            currentSceneIndex = 0;
        }

        //swap scenes
        contentPane.getChildren().clear();
        contentPane.getChildren().add(roots[currentSceneIndex]);
    }

    /**
     * Obtains FlightInfoController instance.
     * @return FlightInfoController instance.
     */
    public FlightInfoController getFlightInfoController() {
        return flightInfoController;
    }

    /**
     * Obtains PassengerInfoController instance.
     * @return PassengerInfoController instance.
     */
    public PassengerInfoController getPassengerInfoController() {
        return passengerInfoController;
    }

    /**
     * Obtains SeatSelectionController instance.
     * @return SeatSelectionController instance.
     */
    public SeatSelectionController getSeatSelectionController() {
        return seatSelectionController;
    }

    /**
     * Obtains PaymentInfoController instance.
     * @return PaymentInfoController instance.
     */
    public PaymentInfoController getPaymentInfoController() {
        return paymentInfoController;
    }
}
