package multitoggle;
import java.util.ArrayList;

/**
 * @author Jennyston Jeyachandra
 * @date 12-06-2023
 * @version 1.0
 */
public class MultiToggleGroup {
    
    private int maxSelectable;
    private int selectedCount;

    private ArrayList<MultiToggle> selectedToggles;


    /**
     * Constructs MultiToggleGroup object.
     * @param maxSelectable Maximum number of MultiToggles that can be selected.
     */
    public MultiToggleGroup(int maxSelectable) {
        this.maxSelectable = maxSelectable;
        selectedCount = 0;
        selectedToggles = new ArrayList<>();
    }

    /**
     * Clears all MultiToggles that are registered as selected.
     */
    public void clearSelection() {
        for(MultiToggle toggle : selectedToggles) {
            toggle.setSelected(false);
        }

        selectedToggles.clear();
        selectedCount = 0;
    }

    /**
     * Toggles selected state of MultiToggle. MultiToggles can only be set 
     * selected if maximum number of MultiToggles selectable is not reached.
     * @param value MuliToggle to have selected state toggled.
     */
    public void selectToggle(MultiToggle value) {

        /*select if button is not selected AND maxSelectable has not been 
        reached*/
        if(selectedCount < maxSelectable && !value.isSelected()) {
            selectedToggles.add(value);
            selectedCount++;
            value.setSelected(true);
        }

        //deselect if button is already selected
        else if (value.isSelected()) {
            selectedToggles.remove(value);
            selectedCount--;
            value.setSelected(false);
        }
    }

    /**
     * Retrieves all MultiToggle objects that are selected.
     * @return ArrayList with selected MultiToggle objects.
     */
    public ArrayList<MultiToggle> getSelected() {
        return selectedToggles;
    }

    /**
     * Retrieves maximum number of MultiToggles selectable.
     * @return Maximum number of MultiToggles selectable.
     */
    public int getMaxSelectable() {
        return maxSelectable;
    }

    /**
     * Sets maximum number of MultiToggles selectable.
     * @param maxSelectable Maximum number of MultiToggles selectable.
     */
    public void setMaxSelectable(int maxSelectable) {
        this.maxSelectable = maxSelectable;
    }

    /**
     * Retrieves number of MultiToggles selected.
     * @return Number of MultiToggles selected.
     */
    public int getSelectedCount() {
        return selectedCount;
    }
}
