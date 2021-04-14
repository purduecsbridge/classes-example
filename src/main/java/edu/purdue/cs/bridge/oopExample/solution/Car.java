package edu.purdue.cs.bridge.oopExample.solution;

/**
 * Purdue CS Bridge – Lab Example 2
 * Car.java
 * Represents a simple Car object.
 *
 * @version 02/15/2020
 */
public class Car {

    /**
     * The make of the {@link Car}.
     */
    private String make;

    /**
     * The model of the {@link Car}.
     */
    private String model;

    /**
     * The number of {@link Car} objects made.
     */
    private static int numCars;

    /**
     * Creates a new {@link Car} object.
     * @param make the make of the {@link Car}
     * @param model the make of the {@link Car}
     */
    public Car(String make, String model) {
        this.make = make;
        this.model = model;

        numCars++;
    }

    /**
     * Returns the make of the {@link Car}.
     * @return the make of the {@link Car}
     */
    public String getMake() {
        return make;
    }

    /**
     * Returns the model of the {@link Car}.
     * @return the model of the car {@link Car}
     */
    public String getModel() {
        return model;
    }

    /**
     * Returns the number of {@link Car} objects made.
     * @return the number of {@link Car} objects made
     */
    public static int getNumCars() {
        return numCars;
    }

    /**
     * Sets the make of the {@link Car} to the given {@link String}.
     * @param make the new make of the {@link Car}
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * Sets the model of the {@link Car} to the given {@link String}.
     * @param model the new model of the {@link Car}
     */
    public void setModel(String model) {
        this.model = model;
    }

}
