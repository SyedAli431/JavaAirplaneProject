package seat;
import javafx.scene.paint.Color;

/**
 * @author Jennyston Jeyachandra
 * @date 12-06-2023
 * @version 1.0
 */
public enum SeatType {
    ECONOMY("E", Color.GREEN),
    FIRST_CLASS("F", Color.web("#008cffea"));

    private String id;
    private Color color;

    private SeatType(String id, Color color) {
        this.id = id;
        this.color = color;
    }

    /**
     * Retrieve SeatType identifier
     * @return SeatType identifier
     */
    public String getId() {
        return id;
    }

    /**
     * Retrieves SeatType color
     * @return SeatType color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Retrieves SeatType matching the provided id
     * @param id SeatType identifier
     * @return SeatType
     */
    public static SeatType getTypeFromId(String id) {

        //get SeatType enum constants as array
        SeatType[] stArray = SeatType.values();

        //iterate through seat types and return id matching seat type
        for(SeatType seatType : stArray) {
            if(seatType.id.equals(id)) {
                return seatType;
            }
        }
         return null;
    }
}
