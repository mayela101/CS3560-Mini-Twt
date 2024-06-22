import java.util.*;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * The Subject class extends DefaultMutableTreeNode and serves as
 * a subject in the OBSERVER design pattern. It maintains a list of observers
 * and provides methods to attach, detach, and notify them of updates.
 */
public class Subject extends DefaultMutableTreeNode 
{
    /** The list of observers subscribed to this subject. */
    protected List<Observer> observerList = new ArrayList<>();

    /**
     * Attaches an observer to this subject.
     *
     * @param observer the observer to attach
     */
    public void attach(Observer observer) 
    {
        observerList.add(observer);
    }

    /**
     * Detaches an observer from this subject.
     *
     * @param observer the observer to detach
     */
    public void detach(Observer observer) 
    {
        observerList.remove(observer);
    }

    /**
     * Notifies all attached observers with a message.
     *
     * @param message the message to send to observers
     */
    public void notifyObservers(String message) 
    {
        for (Observer observer : observerList) 
        {
            observer.update(this, message);
        }
    }
}