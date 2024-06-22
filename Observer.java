/**
 * The Observer interface defines the contract for objects that
 * observe changes in a subject and receive updates.
 */
public interface Observer 
{
    /**
     * Updates the observer with new information from the subject.
     *
     * @param subject the subject being observed
     * @param message the message containing details about the update
     */
    public void update(Subject subject, String message);

}