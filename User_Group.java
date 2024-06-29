import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The  User_Group class implements {@code systemEntry} and represents a group of users
 * in a hierarchical structure. It maintains a list of users and sub-groups.
 * 
 * Implements Composite design pattern:  Serves as composite.
 * Implements Composite design pattern:  Implementation of @code systemEntry (Component)
 *            to provide a common way to access attributes.
 */
public class User_Group implements systemEntry 
{

    /** The unique identifier of the user group. */
    private final String unique_ID;

    /** The list of users directly belonging to this group. */
    private final List<User> userList;

    /** The list of sub-groups belonging to this group. */
    private final List<User_Group> userGroupList;

    public long groupCreationTime;

    /**
     * Constructs a new {@code User_Group} with a given name and initial set of users.
     *
     * @param name  the name (unique identifier) of the user group
     * @param users the initial set of users to include in this group
     */
    public User_Group(String name, User... users) 
    {
        userList = new ArrayList<>();
        userGroupList = new ArrayList<>();
        userList.addAll(Arrays.asList(users));
        unique_ID = name;
        groupCreationTime = System.currentTimeMillis();
    }

    /**
     * Adds a member user to this user group if not already present and not assigned to any other group.
     *
     * @param user the user to add to this group
     * 
     * Implements Composite design pattern: Manipulaes leaf(s).
     */
    public void addMember(User user) {
        if (userList.contains(user) || !user.getUserGroupList().isEmpty()) 
        {
            // User is already in this group or belongs to another group, do nothing
        } 
        else 
        {
            userList.add(user);
        }
    }

    /**
     * Retrieves the list of sub-groups belonging to this user group.
     *
     * @return the list of sub-groups
     */
    public List<User_Group> getUserGroupList() 
    {
        return userGroupList;
    }

    /**
     * Adds a sub-group to this user group.
     *
     * @param newGroup the sub-group to add
     */
    public void addGroupUsers(User_Group newGroup) 
    {
        this.userGroupList.add(newGroup);
    }

    /**
     * Accepts a visitor for visiting operations (placeholder method).
     *
     * @param visitor the visitor to accept
     */
    @Override
    public void accept(Visitor visitor) 
    {
        // Placeholder method, specific implementation depends on the use case
    }

    /**
     * Retrieves the name (unique identifier) of this user group.
     *
     * @return the name of the user group
     */
    @Override
    public String getName() 
    {
        return unique_ID;
    }

    /**
     * Retrieves the unique identifier of this user group.
     *
     * @return the unique identifier of the user group
     */
    @Override
    public String getUnique_ID() 
    {
        return unique_ID;
    }


    public long getGroupCreationTime()
    {
        return groupCreationTime;
    }
}