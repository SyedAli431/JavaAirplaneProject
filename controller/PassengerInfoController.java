package controller;
import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import passenger.Passenger;

/**
 * @author Syed Ali
 * @date 12-06-2023
 * @version 1.0
 */
public class PassengerInfoController {

    private FlightBookingController main;

    @FXML
    private TextField Addresstxt;

    @FXML
    private TextField FirstNametxt;

    @FXML
    private TextField LastNametxt;

    @FXML
    private TextField PhoneNumbertxt;

    @FXML
    private Button Sumbitbtn;

    @FXML
    private Button btnBack;

    // later will be used to create customerInformation objects out of the data the user enters in the textFields

    String firstName;
    String lastName;
    int phoneNumber;
    String address;
 
    /**
     * Initializes reference to main controller for accessing main controller
     * methods.
     * @param mainController Reference to main controller.
     */
    public void init(FlightBookingController mainController) {
        main = mainController;
    }

    @FXML
    public void Submit(ActionEvent event) throws IOException {
        
        // grab each value the user enters in each textField and store them into corresponding variables

        boolean displayMissingInfoAlert = false;

        firstName=FirstNametxt.getText();
        lastName=LastNametxt.getText();
        address=Addresstxt.getText();

        String alertHeader = "";
        String alertContent = "";
        
        //check if fields are empty
        if(firstName.equals("") || lastName.equals("") || address.equals("") ||
            PhoneNumbertxt.getText().equals("")) {
                
            displayMissingInfoAlert = true;

            //append warning messages for empty fields
            alertHeader += "One or multiple fields may be empty!\n";
            alertContent += "Please enter missing data.\n";
        }

        //ensure phone number is not empty
        if(!PhoneNumbertxt.getText().equals("")) {
            try{

                //attempt to parse phone number as integer
                phoneNumber=Integer.parseInt(PhoneNumbertxt.getText());
            }
            //phone number contains charactes that are not numbers
            catch(NumberFormatException e) {
                displayMissingInfoAlert = true;

                //append warning messages for invalid phone number
                alertHeader += "Phone number input error!\n";
                alertContent += "Phone number must be filled in and only contain numbers.\n";
            }
        }

        //display an alert dialog if there's invalid input in one or more fields
        if(displayMissingInfoAlert) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Error: Missing information");
            alert.setHeaderText(alertHeader);
            alert.setContentText(alertContent);
            alert.showAndWait();
        }
        //otherwise, proceed to next scene
        else {
        // Store the users entered information as a passaenger object
            Passenger p1 = new Passenger(firstName,lastName,phoneNumber,address);

            //send passenger object to payment info controller
            PaymentInfoController controller = main.getPaymentInfoController();
            controller.setPassenger(p1);

            main.nextScene();
        }
    }// close sumbit method
    
    @FXML
    void backPressed(ActionEvent event) {
        main.previousScene();
    }

}// close class

