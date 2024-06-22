/**
 * The Composite interface defines the structure for objects
 * in a composite design pattern. It ensures that all objects have
 * a unique identifier and a string representation.
 */
public interface Composite 
{

    /**
     * Retrieves the unique identifier of the composite object.
     *
     * @return the unique identifier as a String
     */
    public String getUnique_ID();

    /**
     * Returns a string representation of the composite object.
     *
     * @return a String representing the composite object
     */
    @Override //TODO-Auto generated Override Annotation
    public String toString();

}