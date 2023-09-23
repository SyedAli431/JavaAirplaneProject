package seat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Jennyston Jeyachandra
 * @author Syed Ali
 * @date 12-06-2023
 * @version 1.0
 */
public class SeatWriter {

    private final static int NUM_FLIGHT_INFO_ELEMENTS = 4;
    private final static int DATE_ELEMENT_INDEX = 0;
    private final static int TIME_ELEMENT_INDEX = 1;
    private final static int DEPART_ELEMENT_INDEX = 2;
    private final static int DEST_ELEMENT_INDEX = 3;

    /**
     * Writes occupied seats to url under provided flight info.
     * @param date Flight date.
     * @param time Flight time.
     * @param dest Flight destination.
     * @param depart Flight departure.
     * @param seats Flight seats.
     * @param url Occupied seats url.
     */
    public static void write(String date, String time, String dest, 
        String depart, List<Seat> seats, URL url) {

            //load file as individual lines into List
            List<String> lines = new LinkedList<>();
            BufferedReader reader;
            try {
                File file = new File(url.toURI());
                reader = new BufferedReader(new FileReader(file));

                //read line-by-line, add line to list until end of file reached
                String line;
                do{
                    line = reader.readLine();
                    if(line != null) {
                        lines.add(line);
                    }
                } while(line != null);


            } catch (FileNotFoundException | URISyntaxException e) {
                System.err.println("File " + url + " is missing!");
            } catch (IOException e) {
                System.err.println("An I/O error in " + url + " has occurred!");
            }

            //find the flight info header that matches provided flight info
            boolean headerFound = false;
            int insertIndex;
            for(insertIndex = 0; insertIndex < lines.size(); insertIndex++) {

                //check if match is found, terminate loop if found
                if(checkFlightInfoLineMatch(lines.get(insertIndex), date, time, 
                    depart, dest)) {

                    headerFound = true;
                    
                    //start inserting seats one line after header
                    insertIndex++;
                    break;
                }
            }

            /*create header if not found and start inserting seats one line 
            after header (at end of file)*/
            if(!headerFound) {

                //create header
                String header = date + "," + time + "," + depart + "," + dest;
                lines.add(header);
                insertIndex = lines.size();
            }

            //insert seats in list
            for(Seat seat : seats) {
                lines.add(insertIndex, seat.toString());
            }
            writeList(lines, url);
            
    }

    private static void writeList(List<String> lines, URL url) {
        try {
            //write List line-by-line to file
            FileWriter fileWriter = new FileWriter(new File(url.toURI()), false);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for(String line : lines) {
                if(line == null) {
                    continue;
                }
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException | URISyntaxException e) {
            System.err.println("An I/O error in " + url + " has occurred!");
        }
    }

    private static boolean checkFlightInfoLineMatch(String line, String date, 
    String time, String depart, String dest) {

        if(line == null) {
            return false;
        }

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
