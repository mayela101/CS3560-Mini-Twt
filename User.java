import java.util.*;

/**
 * Represents a user within Mini Twitter.
 * Implements Observer: Extending Observer Interface.
 */
public class User extends Subject implements Observer, systemEntry {
    private final String unique_ID;
    private final Set<User> followers, following;
    private final Set<User_Group> userGroupList;
    private final LinkedList<String> newsFeed = new LinkedList<>();
    private final LinkedList<String> myTweets = new LinkedList<>();

    /**
     * Constructs a new User with the given name.
     *
     * @param name The unique name of the user.
     */
    public User(String name) {
        unique_ID = name;
        followers = new HashSet<>();
        following = new HashSet<>();
        userGroupList = new HashSet<>();
    }

    /**
     * Allows this user to follow another user.
     *
     * @param user The user to follow.
     */
    public void followUser(User user) 
    {
        followers.add(user);
        user.getFollowersList().add(this);
    }

    /**
     * Retrieves the list of user groups this user belongs to.
     *
     * @return The set of user groups.
     */
    public Set<User_Group> getUserGroupList() 
    {
        return userGroupList;
    }

    /**
     * Retrieves the list of followers of this user.
     *
     * @return The set of followers.
     */
    public Set<User> getFollowersList() 
    {
        return followers;
    }

    /**
     * Retrieves the list of users this user is following.
     *
     * @return The set of users being followed.
     */
    public Set<User> getFollowingList() 
    {
        return following;
    }

    /**
     * Retrieves the list of tweets posted by this user.
     *
     * @return The list of tweets.
     */
    public LinkedList<String> getMyTweets() 
    {
        return myTweets;
    }

    /**
     * Retrieves the feed of this user, which includes tweets from followed users.
     *
     * @return The news feed as a linked list of strings.
     */
    public LinkedList<String> getNewsFeed() 
    {
        return newsFeed;
    }

    /**
     * Posts a new tweet from this user.
     *
     * @param tweet The content of the tweet.
     */
    public void tweetMessage(String tweet) 
    {
        myTweets.add(tweet);
        newsFeed.add("-" + this.unique_ID + " : " + tweet);
        notifyObservers(tweet);
    }

    /**
     * Accepts a visitor for visiting this user (Visitor pattern).
     *
     * @param visitor The visitor object.
     */
    @Override
    public void accept(Visitor visitor) 
    {
        // Implementation may be added based on requirements
    }

    /**
     * Retrieves the name of this user.
     *
     * @return The unique name of the user.
     */
    @Override
    public String getName() 
    {
        return unique_ID;
    }

    /**
     * Retrieves the unique identifier of this user.
     *
     * @return The unique identifier as a string.
     */
    @Override
    public String getUnique_ID() 
    {
        return unique_ID;
    }

    /**
     * Updates this user's news feed with a new tweet.
     *
     * @param subject The subject (user) that posted the tweet.
     * @param tweet   The content of the tweet.
     */
    @Override
    public void update(Subject subject, String tweet) 
    {
        if (subject instanceof User user) {
            this.newsFeed.add("-" + user.getUnique_ID() + " : " + tweet);
        }
    }
}