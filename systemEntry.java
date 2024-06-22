/**
 * The {@code systemEntry} interface extends {@code Visitable} and defines methods
 * that represent properties of a system entry, including its name and unique identifier.
 * 
 * Implements Composite design pattern:  Serves as component.
 */
public interface systemEntry extends Visitable 
{

    /**
     * Retrieves the name of the system entry.
     *
     * @return the name of the system entry as a {@code String}
     */
    public String getName();

    /**
     * Retrieves the unique identifier of the system entry.
     *
     * @return the unique identifier of the system entry as a {@code String}
     */
    public String getUnique_ID();
}