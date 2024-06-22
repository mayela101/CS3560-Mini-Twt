import java.util.*;
import javax.swing.*;
import java.awt.*;

/**
 * Represents the graphical user interface (GUI) view for a user in a social media system.
 */
public class UserView extends JFrame 
{

    private static UserView UserViewInstance;

    private static final LinkedList<String> tweetStrings = new LinkedList<>();
    private static final LinkedList<String> followingStrings = new LinkedList<>();

    private JButton followUserButton;
    private JButton postTweetButton;

    private JList<String> followingList;
    private JList<String> tweetList;

    private JScrollPane followingViewPane;
    private JScrollPane tweetViewPane;

    private JTextField followUserField;
    private JTextField postTweetField;

    /**
     * Constructs a new UserView for the specified user.
     *
     * @param user The user for whom the view is created.
     */
    public UserView(User user) 
    {
        initializeUserView(user);
    }

    /**
     * Retrieves the singleton instance of UserView for a specific user.
     *
     * @param user The user for whom the view is requested.
     * @return The singleton instance of UserView.
     */
    public static UserView getUserView(User user) 
    {
        if (UserViewInstance == null) {
            UserViewInstance = new UserView(user);
        }
        return UserViewInstance;
    }

    /**
     * Initializes the graphical components and layout of the UserView.
     *
     * @param user The user associated with this view.
     */
    private void initializeUserView(User user) 
    {
        getContentPane().setBackground(Color.pink);
        setTitle("User: " + user.getName());
        setResizable(false);

        followUserField = new JTextField();
        followUserButton = new JButton();

        // Action listener for following another user
        followUserButton.addActionListener(e -> 
        {
            followingStrings.add(followUserField.getText());
            followUserField.setText(""); // Clear input field
            updateFollowingList();
        });

        followingViewPane = new JScrollPane();
        followingList = new JList<>();
        postTweetButton = new JButton();

        // Action listener for posting a tweet
        postTweetButton.addActionListener(e -> 
        {
            tweetStrings.add(user.getName() + ": " + postTweetField.getText());
            adminControlPanel.totalMessageCount++;
            checkForPositiveMessage(postTweetField.getText());
            postTweetField.setText(""); // Clear input field
            updateTweetList();
        });

        postTweetField = new JTextField();
        tweetViewPane = new JScrollPane();
        tweetList = new JList<>();

        followUserButton.setText("Follow User");
        updateFollowingList();
        followingViewPane.setViewportView(followingList);

        postTweetButton.setText("Post Tweet");
        updateTweetList();
        tweetViewPane.setViewportView(tweetList);

        // Layout configuration using GroupLayout
        /*====================================================================================================================================================================*/
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        // Horizontal layout
        layout.setHorizontalGroup
        (
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(followingViewPane, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(followUserField, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(followUserButton, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(postTweetField, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(postTweetButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(tweetViewPane, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                    .addContainerGap())
        );

        // Vertical layout
        layout.setVerticalGroup
        (
            layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(followUserField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(followUserButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(followingViewPane, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(postTweetField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(postTweetButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tweetViewPane, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }
    /*====================================================================================================================================================================*/

    /**
     * Updates the list of users being followed in the GUI.
     */
    private void updateFollowingList() 
    {
        followingList.setModel(new javax.swing.AbstractListModel<String>() 
        {
            final LinkedList<String> currentFollowingStrings = new LinkedList<>(followingStrings);

            @Override
            public int getSize() 
            {
                return currentFollowingStrings.size();
            }

            @Override
            public String getElementAt(int index) 
            {
                return currentFollowingStrings.get(index);
            }
        });
    }

    /**
     * Updates the list of tweets in the GUI.
     */
    private void updateTweetList() {
        tweetList.setModel(new javax.swing.AbstractListModel<String>() 
        {
            final LinkedList<String> updatedTweets = new LinkedList<>(tweetStrings);

            @Override
            public int getSize() 
            {
                return updatedTweets.size();
            }

            @Override
            public String getElementAt(int index) 
            {
                return updatedTweets.get(index);
            }
        });
    }

    /**
     * Checks a tweet message for positive keywords and updates the admin panel accordingly.
     *
     * @param message The tweet message to check.
     */
    private void checkForPositiveMessage(String message) 
    {
        String[] positiveKeywords = {"good", "great", "excellent", "lovely", "superb", "love"};
        for (String positiveWord : positiveKeywords) 
        {
            if (message.contains(positiveWord)) 
            {
                adminControlPanel.positiveMessageCount++; 
            }
        }
    }
}