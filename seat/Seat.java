package seat;

/**
 * @author Jennyston Jeyachandra
 * @date 12-06-2023
 * @version 1.0
 */
public class Seat {

    private final double ECONOMY_COST = 234.99;
    private final double FIRST_CLASS_COST = 311.99;

    private int rowNum;
    private char columnLetter;
    private SeatType seatType;
    private boolean occupied;

    private double cost;

    /**
     * Construct Seat.
     * @param rowNum Row number of seat.
     * @param columnLetter Column Letter of seat.
     */
    public Seat(int rowNum, char columnLetter) {
        this.rowNum = rowNum;
        this.columnLetter = columnLetter;
    }

    /**
     * Retrieve Seat row number.
     * @return Row number.
     */
    public int getRowNum() {
        return rowNum;
    }

    /**
     * Assign Seat row number.
     * @param rowNum Row number.
     */
    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }


    /**
     * Retrieve Seat column letter.
     * @return Colunm letter.
     */
    public char getColumnLetter() {
        return columnLetter;
    }


    /**
     * Assign Seat column letter.
     * @param columnLetter Column letter.
     */
    public void setColumnLetter(char columnLetter) {
        this.columnLetter = columnLetter;
    }

    /**
     * Retrieve seat type.
     * @return Seat type.
     */
    public SeatType getSeatType() {
        return seatType;
    }


    /**
     * Assign seat type.
     * @param seatType Seat type.
     */
    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;

        //determine cost based on seat type
        if(seatType == SeatType.ECONOMY) {
            cost = ECONOMY_COST;
        }
        else if(seatType == SeatType.FIRST_CLASS) {
            cost = FIRST_CLASS_COST;
        }
        else{
            cost = 0;
        }
    }

    /**
     * Retrieve seat cost.
     * @return Seat cost.
     */
    public double getCost() {
        return cost;
    }

    /**
     * Retrieve occupied status.
     * @return True if occupied, false otherwise.
     */
    public boolean isOccupied() {
        return occupied;
    }

    /**
     * Set occupied status.
     * @param occupied True if occupied, false otherwise.
     */
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    @Override
    public String toString() {
        return rowNum + Character.toString(columnLetter);
    }

    @Override
    public boolean equals(Object object) {
        if(object instanceof Seat) {

            //compare by seat row and column
            Seat seat = (Seat)object;
            return getRowNum() == seat.getRowNum() && 
                getColumnLetter() == seat.getColumnLetter();
        }
        else {
            return false;
        }
    }
}