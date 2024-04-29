/**
 * The {@code Coordinates} class represents the coordinates of an organization's location.
 * It includes both the x and y values defining the position.
 */
package organization;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@XmlRootElement
public class Coordinates implements Serializable {

    /**
     * The x-coordinate of the location.
     */
    private double x;

    /**
     * The y-coordinate of the location.
     */
    private Integer y;

    /**
     * Constructs a {@code Coordinates} object with the specified x and y values.
     *
     * @param x The x-coordinate of the location.
     * @param y The y-coordinate of the location.
     */
    public Coordinates(double x, Integer y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns a string representation of the coordinates in the format "x: value, y: value".
     *
     * @return The string representation of the coordinates.
     */
    @Override
    public String toString() {
        return "x: " + x + ", y: " + y;
    }

    /**
     * Gets the x-coordinate of the location.
     *
     * @return The x-coordinate as a {@code double}.
     */
    public double getX() {
        return x;
    }

    /**
     * Sets the x-coordinate of the location.
     *
     * @param x The new x-coordinate value.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Gets the y-coordinate of the location.
     *
     * @return The y-coordinate as an {@code Integer}.
     */
    public Integer getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of the location.
     *
     * @param y The new y-coordinate value.
     */
    public void setY(Integer y) {
        this.y = y;
    }
}
