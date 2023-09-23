package seat;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jennyston Jeyachandra
 * @date 12-06-2023
 * @version 1.0
 */
public class SeatsLoader {
    
    private final static int NUM_FLIGHT_INFO_ELEMENTS = 4;
    private final static int DATE_ELEMENT_INDEX = 0;
    private final static int TIME_ELEMENT_INDEX = 1;
    private final static int DEPART_ELEMENT_INDEX = 2;
    private final static int DEST_ELEMENT_INDEX = 3;

    /**
     * Loads seat arrangement from url into Map. Map key is seat location, value
     * is Seat object.
     * @param url Location of seat arrangement file.
     * @return Map with seats. Key is seat location,
     */
    public static Map<String, Seat> load(URL url) {
        
        Map<String, Seat> seatMap = new HashMap<>();
        BufferedReader reader;

        try {
            File file = new File(url.toURI());
            reader = new BufferedReader(new FileReader(file));

            String line;

            do {
                //read next line
                line = reader.readLine();

                //check if end of file is not reached
                if(line != null) {

                    //separate line into String array (delimit ',')
                    String[] lineFields = line.split(",");
                    
                    //parse seat number (row)
                    int seatNumber = Integer.parseInt(
                        lineFields[0].substring(0, 
                        lineFields[0].length()-1));

                    //parse seat letter (column)
                    char seatLetter = lineFields[0].charAt(
                        lineFields[0].length()-1);

                    //create seat object and set seat type from file
                    Seat seat = new Seat(seatNumber, seatLetter);
                    seat.setSeatType(SeatType.getTypeFromId(lineFields[1]));

                    //put key (seat number+letter) and value (seat) pair in map
                    seatMap.put(Integer.toString(seatNumber) + seatLetter, 
                    seat);
                }
            } while(line != null); //iterate through file until end is reached

        } catch (FileNotFoundException e) {

            System.err.println("File " + url + " is missing!");

        } catch (IOException e) {

            System.err.println("An I/O error in " + url + " has occurred!");
        } catch (URISyntaxException e) {

            System.err.println("URL to URI conversion of " +
                url + " failed!");

        }

       return seatMap;
    }

    /**
     * Loads occupied seats from file based on provided date, time, departure, 
     * and destination into List. List contains seat locations as String.
     * @param date Flight date.
     * @param time Flight time.
     * @param depart Flight departure.
     * @param dest Flight destination.
     * @param url Occupied seats file location.
     * @return
     */
    public static List<String> loadOccupied(String date, String time, 
        String depart, String dest, URL url){
        
        List<String> occupiedList = new ArrayList<>();
        BufferedReader reader;

        try {
            File file = new File(url.toURI());
            reader = new BufferedReader(new FileReader(file));


            //find start of matching flight info header
             boolean found = false;
             String line;
            do {
                //read next line
                line = reader.readLine();

                //check if end of file is not reached
                if(line != null) {
                    
                    //check if line matches flight info
                    if(checkFlightInfoLineMatch(line, date, time, depart, 
                        dest)) {
                        
                        //flight info header is found
                        found = true;
                    }
                }
            //read file until end is reached or flight info header is found
            } while(line != null && !found);
            
            //load occupied seat info and add to list
            boolean headerReached = false;
            if(found) {
                do {
                    line = reader.readLine();

                if(line != null) {
                    headerReached = line.split(",").length > 1;
                    //check if end of file or header is not reached
                    if(!headerReached) {

                        //add seat row and column to occupied seat list
                        occupiedList.add(line);
                    }
                }
                //read file until end of file is reached
                } while(!headerReached && line != null);
            }

        } catch (FileNotFoundException e) {

            System.err.println("File " + url + " is missing!");

        } catch (IOException e) {
            System.err.println("An I/O error in " + url + " has occurred!");

        } catch (URISyntaxException e) {

            System.err.println("URL to URI conversion of " +
                url + " failed!");

        }

       return occupiedList;
    }

    private static boolean checkFlightInfoLineMatch(String line, String date, 
        String time, String depart, String dest) {

        //store separated comma delimited Strings into array
        String[] lineFields = line.split(",");

        //ensure number of fields match flight info header
        if(lineFields.length != NUM_FLIGHT_INFO_ELEMENTS) {

            //line is not a flight info header
            return false;
        }
        
        //check if flight information matches fields
        if(lineFields[DATE_ELEMENT_INDEX].equalsIgnoreCase(date) &&
            lineFields[TIME_ELEMENT_INDEX].equalsIgnoreCase(time) &&
            lineFields[DEPART_ELEMENT_INDEX].equalsIgnoreCase(depart) &&
            lineFields[DEST_ELEMENT_INDEX].equalsIgnoreCase(dest)) {
            
            return true;
        }
        else {
            return false;
        }
    }

}
