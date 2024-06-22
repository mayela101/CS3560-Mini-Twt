/**
 * The TotalMessageVisitor class is an abstract implementation of {@code GroupInterface}
 * that calculates the total number of messages (tweets) within a user or group hierarchy.
 */
public abstract class TotalMessageVisitor implements GroupInterface 
{

    /** The total number of messages accumulated by visiting users. */
    private int TotalMessage = 0;

    /**
     * Visits a user and adds the number of tweets they have made to the total message count.
     *
     * @param user the user to visit
     */
    @Override
    public void visitUser(User user) 
    {
        setMessageTotal(getMessageTotal() + user.getMyTweets().size());
    }

    /**
     * Placeholder method for visiting a user group without specific functionality in this context.
     *
     * @param group the user group to visit
     */
    @Override
    public void visitGroup(User_Group group) 
    {
        // No specific implementation needed for visiting groups in this context
    }

    /**
     * Retrieves the total number of messages counted by this visitor.
     *
     * @return the total number of messages as an {@code int}
     */
    public int getMessageTotal() 
    {
        return TotalMessage;
    }

    /**
     * Sets the total number of messages counted by this visitor.
     *
     * @param totalMessage the total number of messages to set
     */
    public void setMessageTotal(int totalMessage) 
    {
        TotalMessage = totalMessage;
    }
}