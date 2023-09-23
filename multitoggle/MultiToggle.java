package multitoggle;

/**
 * @author Jennyston Jeyachandra
 * @date 12-06-2023
 * @version 1.0
 */
public interface MultiToggle {

    /** Returns the MultiToggleGroup that this MultiToggle belongs to.
     * @return The MultiToggleGroup this MultiToggle belongs to.
     */
    MultiToggleGroup getMultiToggleGroup();

    /**
     * Assigns the MultiToggleGroup this MultiToggle belongs to.
     * @param group The MultiToggleGroup this MultiToggle will belong to.
     */
    void setMultiToggleGroup(MultiToggleGroup group);

    /**
     * Indicates if this MultiToggle is selected or not.
     * @return Whether MultiToggle is selected or not.
     */
    boolean isSelected();

    /**
     * Sets the selected state of this MultiToggle.
     * @param selected Whether MultiToggle is selected or not.
     */
    void setSelected(boolean selected);

}
