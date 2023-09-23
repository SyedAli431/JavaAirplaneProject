package controller;
import java.io.IOException;
import java.util.Random;

import flight.Flight;

import java.io.FileWriter;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import passenger.Passenger;
import payment.Payment;
import seat.SeatWriter;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * @author Bhavik Patel
 * @date 12-06-2023
 * @version 1.0
 */
public class PaymentInfoController {

    @FXML
    private TextField Cardnametxt;

    @FXML
    private TextField Cardnumbertxt;

    @FXML
    private TextField Expirydatetxt;

    @FXML
    private TextField Securitycodetxt;

    @FXML
    private TextField Postalcodetxt;

    @FXML
    private Button Confirmbtn;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblFrom;

    @FXML
    private Label lblName;

    @FXML
    private Label lblPhone;

    @FXML
    private Label lblTo;

    @FXML
    private Label lblTotal;


    private FlightBookingController main;

    private String cardName;
    private String cardNum;
    private String expiryDate;
    private String securityCode;
    private String postalCode;

    /**
     * Initializes reference to main controller for accessing main controller
     * methods.
     * @param mainController Reference to main controller.
     */
    public void init(FlightBookingController mainController) {
        main = mainController;
    }


    @FXML
    public void Confirm(ActionEvent event) throws IOException{

        //gather data from text fields
        cardName=Cardnametxt.getText();
        cardNum=Cardnumbertxt.getText();
        expiryDate=Expirydatetxt.getText();
        securityCode=Securitycodetxt.getText();
        postalCode=Postalcodetxt.getText();




        boolean displayMissingInfoAlert = false;
        boolean numInputError = false;

        String alertHeader = "";
        String alertContent = "";

        //check if any field is empty
        if(cardName.equals("") || cardNum.equals("") || expiryDate.equals("") ||
            securityCode.equals("") || postalCode.equals("")) {

                //append error messages to be displayed in alert dialog
                alertHeader += "One or multiple fields may be empty!\n";
                alertContent += "Please enter missing data.\n";

                displayMissingInfoAlert = true;
        }

        //check if card number contains non numeric characters
        if(cardNum.matches("[^0-9]{1,}")) {

            //append card number error message
            alertContent += "Card number must only contain numbers.\n";

            displayMissingInfoAlert = true;
            numInputError = true;
        }

        //check if expiry date contains non numeric characters
        if(expiryDate.matches("[^0-9]{1,}")) {
            
            //append expiry date error message
            alertContent += "Expiry date must only contain numbers.\n";
            
            displayMissingInfoAlert = true;
            numInputError = true;
        }

        //check if security code contains non numeric characters
        if(securityCode.matches("[^0-9]{1,}")) {

            //append security code error message
            alertContent += "Security code must only contain numbers.\n";

            displayMissingInfoAlert = true;
            numInputError = true;
        }

        if(numInputError) {
            alertHeader += "One or multiple fields must only contain numbers.\n";
        }

        //display alert warning dialog if there was an error in entered fields
        if(displayMissingInfoAlert) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Error: Missing information");
            alert.setHeaderText(alertHeader);
            alert.setContentText(alertContent);
            alert.showAndWait();
        }
        //otherwise, payment is successful
        else {

            //populate Payment object with provided info and write to file
            Payment payment= new Payment(cardName, cardNum, expiryDate, 
                securityCode, postalCode);
            writePayment(payment);

            //inform user booking was successful and display reservation number
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Booking successful!");
            alert.setHeaderText("Your flight has been booked!");
            alert.setContentText("Reservation Number: " + generateReservationNum());
            alert.showAndWait();
            
            //add selected seats to occupied seats file
            String time = main.getFlightInfoController().getFlight().getTime();
            SeatWriter.write(lblDate.getText(), time, lblTo.getText(), 
                lblFrom.getText(),
                main.getSeatSelectionController().getSelectedSeats(), 
                getClass().getResource("../res/occupied_seats.txt"));

            main.closeApplication();
        }
    }


    private void writePayment(Payment payment) throws IOException {
        FileWriter writer = new FileWriter("payment.txt");
        writer.write(payment.getCardName() + "," + payment.getCardNum()+ "," + 
            payment.getExpiryDate()+ "," + payment.getPostalCode()+ "," + 
            payment.getSecurityCode());
        writer.close();
    }

    private static String generateReservationNum() {

        //generate reservation number using the format A##A#A
        Random rnd = new Random();

        String possibleLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String reservationNumber = "" +
            possibleLetters.charAt(rnd.nextInt(possibleLetters.length()-1)) + 
            rnd.nextInt(9) + rnd.nextInt(9) +
            possibleLetters.charAt(rnd.nextInt(possibleLetters.length()-1)) +
            rnd.nextInt(9) +
            possibleLetters.charAt(rnd.nextInt(possibleLetters.length()-1));

        return reservationNumber;
    }

    /**
     * Assigns passenger to be display.
     * @param passenger Passenger.
     */
    public void setPassenger(Passenger passenger) {
        lblAddress.setText(passenger.getAddress());
        lblPhone.setText(Integer.toString(passenger.getPhoneNumber()));
        lblName.setText(passenger.getFirstName() + " " + 
            passenger.getLastName());
        
    }

    /**
     * Assigns flight information.
     * @param flight Flight information.
     */
    public void setFlightInfo(Flight flight) {
        lblDate.setText(flight.getDate());
        lblFrom.setText(flight.getDepart());
        lblTo.setText(flight.getDest());
    }

    /**
     * Assigns total cost.
     * @param total Total cost.
     */
    public void setTotal(double total) {
        lblTotal.setText(String.format("$%.2f", total));
    }
    
    @FXML
    void back(ActionEvent event) {
        main.previousScene();
    }
}
