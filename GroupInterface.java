/**
 * The GroupInterface defines methods for visiting users
 * and user groups within a hierarchical structure.
 */
public interface GroupInterface 
{
    /**
     * Visits a user and performs actions specific to the user.
     *
     * @param user the user to visit
     */
    public void visitUser(User user);

    /**
     * Visits a user group and performs actions specific to the user group.
     *
     * @param group the user group to visit
     */
    public void visitGroup(User_Group group);
}