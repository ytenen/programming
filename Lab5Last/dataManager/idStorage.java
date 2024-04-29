/**
 * The {@code idStorage} class is responsible for managing unique IDs for organizations in the application.
 * It provides a static method to retrieve a new ID for each organization, ensuring uniqueness.
 */
package dataManager;

public class idStorage {

    /**
     * The current ID value, initialized to 1.
     */
    static long currentId = 1;

    /**
     * Gets the next available ID and increments the current ID value.
     *
     * @return The next available ID.
     */
    static public long getId() {
        currentId += 1;
        return currentId - 1;
        // return currentId++;
    }
}
