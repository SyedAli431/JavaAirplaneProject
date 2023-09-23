package seat;

import javafx.scene.paint.Color;
import multitoggle.MultiToggleButton;

/**
 * @author Jennyston Jeyachandra
 * @date 12-06-2023
 * @version 1.0
 */
public class SeatButton extends MultiToggleButton {
    
    private Seat seat;

    /**
     * Construct SeatButton object.
     * @param seat Seat object to be contained by SeatButton.
     */
    public SeatButton(Seat seat) {
        super(seat);
        this.seat = seat;
        setOccupied(seat.isOccupied());
        setSeatType(seat.getSeatType());
    }

    /**
     * Retrieve Seat occupied status.
     * @return True if occupied, false otherwise.
     */
    public boolean isOccupied() {
        return seat.isOccupied();
    }

    /**
     * Assign Seat occupied status.
     * @param occupied True if occupied, flase otherwise.
     */
    public void setOccupied(boolean occupied) {
        seat.setOccupied(occupied);

        //update seat button color
        setDefaultColor(occupied ? Color.RED : seat.getSeatType().getColor());
        setDisable(occupied);
    }

    /**
     * Retrieve seat type.
     * @return seat type.
     */
    public SeatType getSeatType() {
        return seat.getSeatType();
    }

    /**
     * Retrieve Seat object.
     * @return Seat object.
     */
    public Seat getSeat() {
        return seat;
    }

    /**
     * Assign seat type.
     * @param seatType seat type.
     */
    public void setSeatType(SeatType seatType) {
        seat.setSeatType(seatType);

        //update seat button color
        setDefaultColor(seat.isOccupied() ? Color.RED : 
            seat.getSeatType().getColor());
    }

    /**
     * Retrieve seat column letter.
     * @return Seat column letter.
     */
    public char getSeatColumnLetter() {
        return seat.getColumnLetter();
    }

    /**
     * Retrieve seat row number.
     * @return Seat row number.
     */
    public int getSeatRowNum() {
        return seat.getRowNum();
    }

    /**
     * Retrieve seat cost.
     * @return seat cost.
     */
    public double getSeatCost() {
        return seat.getCost();
    }
}
