package multitoggle;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

/**
 * @author Jennyston Jeyachandra
 * @date 12-06-2023
 * @version 1.0
 */
public class MultiToggleButton extends Button implements MultiToggle{

    private Color defaultColor;
    private Color defaultHoverColor;

    private Color selectedColor;
    private Color selectedHoverColor;

    private String defaultStyle;
    private String defaultHoverStyle;

    private String selectedStyle;
    private String selectedHoverStyle;

    private boolean selected;

    private MultiToggleGroup group;

    
    /**
     * Contructs MultiToggleButton object.
     */
    public MultiToggleButton() {
        
        //initialize default colors
        defaultColor = Color.GREEN;
        defaultHoverColor = calcOffsetColor(defaultColor, -0.3);

        //initialize default styles
        defaultStyle = generateCSS(defaultColor, 10, 20, 20, 20, 20);
        defaultHoverStyle = generateCSS(defaultHoverColor, 10, 20, 20, 20, 20);

        //initialize selected colors
        selectedColor = Color.web("#ff8c00");
        selectedHoverColor = calcOffsetColor(selectedColor, -0.3);

        //initialize selected styles
        selectedStyle = generateCSS(selectedColor, 10, 20, 20, 20, 20);
        selectedHoverStyle = generateCSS(selectedHoverColor,10 , 20, 20, 20, 
             20);

        //assign initial style
        setStyle(defaultStyle);

        //handle hover button style changes
        hoverProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if(!isDisable()) {
                    if (isHover()) {
                        setStyle(selected ? selectedHoverStyle : 
                            defaultHoverStyle);
                    }
                    else {
                        setStyle(selected ? selectedStyle : defaultStyle);
                    }
                }
            }
            
        });

        //handle disable button style
        disableProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                
                //use default style if disabled
                if(isDisable()) {
                    setStyle(defaultStyle);
                }

                //otherwise, use appropriate style based on hover
                else {
                    setStyle(isHover() ? defaultHoverStyle :
                        defaultStyle);
                }
            }
            
        });
    }

    /**
     * Constructs MultiToggleButton object.
     * @param userData Data to be stored in MultiToggleButton.
     */
    public MultiToggleButton(Object userData) {
        
        //call default constructor
        this();

        //assign user data
        this.setUserData(userData);
    }

    @Override
    public MultiToggleGroup getMultiToggleGroup() {
        return group;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;

        //determine appropriate style based on hover and if button is selected
        if(isHover()) {
            setStyle(selected ? selectedHoverStyle : defaultHoverStyle);
        }
        else {
            setStyle(selected ? selectedStyle : defaultStyle);
        }
    }

    @Override
    public void setMultiToggleGroup(MultiToggleGroup group) {
        this.group = group;
        
        this.addEventHandler(ActionEvent.ACTION, e ->
            group.selectToggle((MultiToggle)e.getSource()));
    }
    
    //Generates CSS String with provided color, radius, width, and height.
    private static String generateCSS(Color color, double backgroundRadius,
        double minWidth, double minHeight, double maxWidth, double maxHeight) {
        return 
            "-fx-background-color: " + color.toString().replace("0x", "#") + 
            "; " +
            "-fx-background-radius: " + backgroundRadius + "em; " +
             "-fx-min-width: " + minWidth + "px; " +
             "-fx-min-height: " + minHeight + "px; " +
             "-fx-max-width: " + maxWidth + "px; " +
             "-fx-max-height: " + maxHeight + "px; " +
             "-fx-opacity: 1.0";
    }

    //Offsets provided color.
    private static Color calcOffsetColor(Color color, double offset) {
        
        //apply offset to each color component, limit to valid color range (0.0
        // to 1.0)
        double red = constrain(color.getRed() + offset, 0.0, 1.0);
        double green = constrain(color.getGreen() + offset, 0.0, 1.0);
        double blue = constrain(color.getBlue() + offset, 0.0, 1.0);

        //return with 1.0 alpha (no transparency)
        return new Color(red, green, blue, 1.0);
    }

    //limits value range
    private static double constrain(double value, double min, double max) {

        return Math.min(Math.max(value, min), max);
    }

    /**
     * Retrieves default color.
     * @return Default color.
     */
    public Color getDefaultColor() {
        return defaultColor;
    }

    /**
     * Assigns default color.
     * @param defaultColor Default color.
     */
    public void setDefaultColor(Color defaultColor) {
        this.defaultColor = defaultColor;

        //determine hover color
        defaultHoverColor = calcOffsetColor(defaultColor, -0.3);

        //generate default style and default hover style
        defaultStyle = generateCSS(defaultColor, 10, 20, 20, 20, 20);
        defaultHoverStyle = generateCSS(defaultHoverColor, 10, 20, 20, 20, 20);

        //assign new color only if MultiToggleButton isn't already selected
        if(!selected) {
            //ignore hover if disabled
            if(isDisable()) {
                setStyle(defaultStyle);
            }
            else {
                setStyle(isHover() ? defaultHoverStyle : defaultStyle);
            }
        }
    }
 
    /**
     * Retrieves selected color.
     * @return Selected color.
     */
    public Color getSelectedColor() {
        return selectedColor;
    }

    /**
     * Assigns selected color.
     * @param selectedColor Selected color.
     */
    public void setSelectedColor(Color selectedColor) {
        this.selectedColor = selectedColor;

        //determine hover color
        selectedHoverColor = calcOffsetColor(selectedColor, -0.3);

        //generate selected style and selected hover style
        selectedStyle = generateCSS(selectedColor, 10, 20, 20, 20, 20);
        selectedHoverStyle = generateCSS(selectedHoverColor,10 , 20, 20, 20, 
             20);

        //assign new color only if MultiToggleButton is selected
        if(selected) {
            setStyle(isHover() ? selectedHoverStyle : selectedStyle);
        }
    }

}
