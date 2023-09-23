package passenger;

/**
 * @author Syed Ali
 * @date 12-06-2023
 * @version 1.0
 */
public class Passenger {

private String firstName;
private String lastName;
private int phoneNumber;
private String address;

/**
 * Construts Passenger object.
 * @param firstName Passenger's first name.
 * @param lastName Passenger's last name.
 * @param phoneNumber Passenger's phone number.
 * @param address Passenger's address.
 */
public Passenger(String firstName, String lastName, int phoneNumber, String address) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.address = address;

}

/**
 * Retrieves passenger's first name.
 * @return Passenger's first name.
 */
public String getFirstName() {
    return firstName;
}

/**
 * Assigns passenger's first name.
 * @param firstName Passenger's first name.
 */
public void setFirstName(String firstName) {
    this.firstName = firstName;
}

/**
 * Retrieves passenger's last name.
 * @return Passenger's last name.
 */
public String getLastName() {
    return lastName;
}

/**
 * Assigns passenger's last name.
 * @param lastName Passenger's last name.
 */
public void setLastName(String lastName) {
    this.lastName = lastName;
}

/**
 * Retrieves passenger's phone number.
 * @return Passenger's phone number.
 */
public int getPhoneNumber() {
    return phoneNumber;
}

/**
 * Assigns passenger's phone number.
 * @param phoneNumber Passenger's phone number.
 */
public void setPhoneNumber(int phoneNumber) {
    this.phoneNumber = phoneNumber;
}

/**
 * Retrieves passenger's address.
 * @return Passenger's address.
 */
public String getAddress() {
    return address;
}

/**
 * Assigns passenger's address.
 * @param address passenger's address.
 */
public void setAddress(String address) {
    this.address = address;
}


@Override
public String toString() {
    return "Passenger [firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber
            + ", address=" + address + "]";
}












    
}//close class 
