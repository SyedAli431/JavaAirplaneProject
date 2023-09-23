package flight;

/**
 * @author Farshid Fitzner Amini
 * @date 12-06-2023
 * @version 1.0
 */
public class Flight {

    private String date;
    private String time;
    private String depart;
    private String dest;
    
    /**
     * Constructs Flight object.
     * @param date Flight date.
     * @param time Flight time.
     * @param depart Flight departure.
     * @param dest Flight destination.
     */
    public Flight(String date, String time, String depart, String dest) {
        this.date = date;
        this.time = time;
        this.depart = depart;
        this.dest = dest;
    }

    /**
     * Retrieve flight date.
     * @return Flight date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Assign flight date.
     * @param date Flight date.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Retrieve flight Time.
     * @return Flight time.
     */
    public String getTime() {
        return time;
    }

    /**
     * Assign flight time.
     * @param time Flight time.
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Retrieve flight departure.
     * @return Flight departure.
     */
    public String getDepart() {
        return depart;
    }

    /**
     * Assign flight departure.
     * @param depart Flight departure.
     */
    public void setDepart(String depart) {
        this.depart = depart;
    }

    /**
     * Retrieve flight destination.
     * @return Flight destination.
     */
    public String getDest() {
        return dest;
    }

    /**
     * Assign flight destination.
     * @param dest Flight destination.
     */
    public void setDest(String dest) {
        this.dest = dest;
    }

}
