import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.tree.*;

/**
 * 
 * It follows the Singleton design pattern to ensure only one instance exists.
 */
public class adminControlPanel extends JFrame 
{

    // Singleton instance
    private static adminControlPanel adminInstance;

    private JFrame popUpDialogBox;

    protected static HashMap<String, User> users;
    protected static HashMap<String, User_Group> user_groups;

    static int totalMessageCount = 0;
    static double positiveMessageCount = 0;

    private String newUserName;
    private String newUserGroupName;
    

    // GUI Components
    private JButton addUserButton;
    private JButton addGroupButton;
    private JButton openUserViewButton;
    private JButton totalMessageCountButton;
    private JButton totalUserCountButton;
    private JButton totalPositiveCountButton;
    private JButton totalGroupCountButton;


    private JButton unique_IDVerificationButton;
    private JButton groupUnique_IDVerificationButton;
    private JButton lastUpdatedUserButton;
    

    private JScrollPane treeViewPane;
    private JTextField addUserField;
    private JTextField addGroupField;
    private JTree treeView;

    public String lastCreatedUser;
    private String lastUpdatedUser;
    public long lastCreatedUserTime;

    /**
     * Private constructor for the Singleton pattern.
     * Initializes the control panel and its components.
     */
    private adminControlPanel() 
    {
        if(adminInstance == null) 
        {
           adminInstance = this;
           users = new HashMap<>();
           user_groups = new HashMap<>();
           initadminControlPanel();
        }
    }

    /**
     * Returns the Singleton instance of {@code adminControlPanel}.
     *
     * @return the Singleton instance
     */
    public static adminControlPanel getInstance() 
    {
        if (adminInstance == null) {
            adminInstance = new adminControlPanel();
        }
        return adminInstance;
    }

    /**
     * Initializes the control panel's GUI components and layout.
     */
    private void initadminControlPanel() 
    {
        setTitle("Admin Control Panel");
        setResizable(false);

        // Set background color to pink
        getContentPane().setBackground(Color.pink);
        treeViewPane = new JScrollPane();
        treeView = initializeTree();
        addUserField = new JTextField();
        addGroupField = new JTextField();
        addUserButton = new JButton();
        addGroupButton = new JButton();
        openUserViewButton = new JButton();
        totalMessageCountButton = new JButton();
        totalUserCountButton = new JButton();
        totalPositiveCountButton = new JButton();
        totalGroupCountButton = new JButton();

        unique_IDVerificationButton = new JButton();
        groupUnique_IDVerificationButton = new JButton();
        lastUpdatedUserButton = new JButton();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        treeViewPane.setViewportView(treeView);

        // Configure add user button
        addUserButton.setText("Add User");
        addUserField.setText("");
        addUserButton.addActionListener(e -> 
        {
            newUserName = addUserField.getText();
            addUser(newUserName);
        });

        // Configure add group button
        addGroupButton.setText("Add Group");
        addGroupField.setText("");
        addGroupButton.addActionListener(e -> 
        {
            newUserGroupName = addGroupField.getText();
            addUserGroup(newUserGroupName);
        });

        // Configure open user view button
        openUserViewButton.setText("Open User View");
        openUserViewButton.addActionListener(e -> 
        {
            DefaultMutableTreeNode selectedUser = (DefaultMutableTreeNode) treeView.getSelectionPath().getLastPathComponent();
            UserView userView = UserView.getUserView(users.get(selectedUser.toString()));
            EventQueue.invokeLater(() -> userView.setVisible(true));
        });

        // Configure total message count button
        totalMessageCountButton.setText("Show Messages Total");
        totalMessageCountButton.addActionListener(e -> 
        {
            JOptionPane.showMessageDialog(popUpDialogBox, "Total Number of Messages: " + totalMessageCount);
        });

        // Configure total user count button
        totalUserCountButton.setText("Show User Total");
        totalUserCountButton.addActionListener(e -> 
        {
            JOptionPane.showMessageDialog(popUpDialogBox, "Total Number of Users: " + users.size());
        });

        // Configure total positive count button
        totalPositiveCountButton.setText("Show Positive Percentage");
        totalPositiveCountButton.addActionListener(e -> 
        {
            double positiveMessagePercentage = (totalMessageCount == 0) ? 0 : (positiveMessageCount / totalMessageCount) * 100;
            String formattedPercentage = String.format("%.2f", positiveMessagePercentage); // Limiting to 2 decimal places
            JOptionPane.showMessageDialog(popUpDialogBox, "Positive Tweet Percentage: " + formattedPercentage + "%");
        });

        // Configure total group count button
        totalGroupCountButton.setText("Show Group Total");
        totalGroupCountButton.addActionListener(e -> 
        {
            JOptionPane.showMessageDialog(popUpDialogBox, "Total Number of Groups: " + user_groups.size());
        });

        //Configure Unique ID verification button
        unique_IDVerificationButton.setText("Verify User IDs");
        unique_IDVerificationButton.addActionListener(e -> 
        {
            if(verifyUnique_IDs())
            {
                JOptionPane.showMessageDialog(popUpDialogBox, "All Users have a valid ID.");
            }
            else
            {
                JOptionPane.showMessageDialog(popUpDialogBox, "Error: User IDs are invalid.");
            }
        });

        //Configure Group Unique ID Verification button
        groupUnique_IDVerificationButton.setText("Verify Group IDs");
        groupUnique_IDVerificationButton.addActionListener(e -> 
        {
            if(verifyGroupUnique_IDs())
            {
                JOptionPane.showMessageDialog(popUpDialogBox, "Error: Group IDs are invalid.");
            }
            else
            {
                JOptionPane.showMessageDialog(popUpDialogBox, "All Groups have a valid ID.");
            }
        });

        
        //Configure Last Updated User button
        lastUpdatedUserButton.setText("Last Updated User");
        lastUpdatedUserButton.addActionListener(e ->
        {

            JOptionPane.showMessageDialog(popUpDialogBox, "Last Updated User: " + lastUpdatedUser);
        });


        // Configure layout using GroupLayout
        configureLayout();
        pack();
    }

    /**
     * Configure the configuration layout using Online GUI Builder.
     */
    private void configureLayout() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        // Horizontal layout configuration
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()

                    .addComponent(treeViewPane, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(addUserField, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addUserButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(addGroupField, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addGroupButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(openUserViewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(totalUserCountButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(totalGroupCountButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(totalMessageCountButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(totalPositiveCountButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(unique_IDVerificationButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(groupUnique_IDVerificationButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(lastUpdatedUserButton, GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        
                        )
                            
                    .addContainerGap())

        );

        // Vertical layout configuration
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(treeViewPane, GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(addUserField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(addUserButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(addGroupField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(addGroupButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(openUserViewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(totalUserCountButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(totalGroupCountButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(totalMessageCountButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(totalPositiveCountButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(unique_IDVerificationButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(groupUnique_IDVerificationButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lastUpdatedUserButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)

                                ))
                                
                    .addContainerGap())
        );
    }

    /**
     * Initializes the tree view component with the root group and returns the configured {@code JTree}.
     *
     * @return the initialized {@code JTree}
     */
    public JTree initializeTree() 
    {
        User_Group rootGroup = new User_Group("Root");
        user_groups.put("Root", rootGroup);

        DefaultMutableTreeNode rootNode = createRootNode(rootGroup);
        DefaultTreeModel treeModel = createTreeModel(rootNode);

        treeView = createTree(treeModel);
        expandTree(treeView);
        
        return treeView;
    }

    /**
     * Creates the root node for the tree.
     *
     * @param rootGroup the root user group
     * @return the root node
     */
    private DefaultMutableTreeNode createRootNode(User_Group rootGroup) 
    {
        return new DefaultMutableTreeNode(rootGroup.getName());
    }

    /**
     * Creates the tree model for the given root node.
     *
     * @param rootNode the root node
     * @return the tree model
     */
    private DefaultTreeModel createTreeModel(DefaultMutableTreeNode rootNode) 
    {
        DefaultTreeModel model = new DefaultTreeModel(rootNode);

        model.setAsksAllowsChildren(true);

        return model;
    }

    /**
     * Creates and configures a {@code JTree} with the given tree model.
     *
     * @param treeModel the tree model
     * @return the configured {@code JTree}
     */
    private JTree createTree(DefaultTreeModel treeModel) 
    {
        return new JTree(treeModel);
    }

    /**
     * Configures the given tree to expand selected paths.
     *
     * @param tree the tree to configure
     */
    private void configureTree(JTree tree) 
    {
        tree.setExpandsSelectedPaths(true);
    }

    /**
     * Expands all rows in the given tree.
     *
     * @param tree the tree to expand
     */
    public void expandTree(JTree tree)
     {
        configureTree(tree);

        for (int i = 0; i < tree.getRowCount(); i++) 
        {
            tree.expandRow(i);
        }
    }

    /**
     * Validates the given username for alphanumeric characters (using alphanumeric RegEx).
     *
     * @param username the username to validate
     * @return {@code true} if the username is valid, {@code false} otherwise
     */
    private boolean isValidUsername(String username) 
    {
        String pattern = "^[a-zA-Z0-9]+$";

        return username.matches(pattern);
    }

    /**
     * Validates the given group name for alphanumeric characters (using alphanumeric RegEx).
     *
     * @param groupName the group name to validate
     * @return {@code true} if the group name is valid, {@code false} otherwise
     */
    private boolean isValidGroupName(String groupName) 
    {
        String pattern = "^[a-zA-Z0-9]+$";

        return groupName.matches(pattern);
    }

    /**
     * Adds a new user with the given name if valid.
     *
     * @param newUserName the name of the new user
     */
    private void addUser(String newUserName) 
    {
        if (isValidUsername(newUserName)) 
        {
            if (!users.containsKey(newUserName)) 
            {
                createUser(newUserName);
                updateTreeView(newUserName);
                
            } 

            else
            {
                showUserExistsMessage(newUserName);
            }
        } 
        else 
        {
            showInvalidUsernameMessage();
        }
    }

    

    /**
     * Creates a new user and adds it to the user map.
     *
     * @param newUserName the name of the new user
     */
    private void createUser(String newUserName) 
    {
        User newUser = new User(newUserName);

        users.put(newUserName, newUser);

        System.out.println(newUserName + "(User Creation Time: " + newUser.getUserCreationTime() + ")");
        lastCreatedUserTime = newUser.getUserCreationTime();
        lastCreatedUser = newUser.getName();
        
    }

    /**
     * Updates the tree view to include the new user.
     *
     * @param newUserName the name of the new user
     */
    private void updateTreeView(String newUserName) 
    {
        DefaultMutableTreeNode selectedUser = (DefaultMutableTreeNode) treeView.getSelectionPath().getLastPathComponent();

        selectedUser.add(new DefaultMutableTreeNode(newUserName, false));
        addUserField.setText("");

        DefaultTreeModel model = (DefaultTreeModel) treeView.getModel();

        model.reload();
        expandTree(treeView);
    }

    /**
     * Displays a message indicating the user already exists.
     *
     * @param newUserName the name of the existing user
     */
    private void showUserExistsMessage(String newUserName) 
    {
        JOptionPane.showMessageDialog(this, "User " + newUserName + " already exists!");
    }

    /**
     * Displays a message indicating the username is invalid.
     */
    private void showInvalidUsernameMessage() 
    {
        JOptionPane.showMessageDialog(this, "Invalid group name. Please use alphanumeric characters only.");
    }

    /**
     * Adds a new user group with the given name if valid.
     *
     * @param newGroupName the name of the new user group
     */
    private void addUserGroup(String newGroupName) 
    {
        if (isValidGroupName(newGroupName)) 
        {
            if (!user_groups.containsKey(newGroupName)) 
            {
                createUserGroupAndAddToMap(newGroupName);
                updateTreeViewWithGroup(newGroupName);
            } 
            
            else 
            {
                displayGroupExistsMessage(newGroupName);
            }
        } 
        
        else 
        {
            displayInvalidGroupNameMessage();
        }
    }

    /**
     * Creates a new user group and adds it to the user group map.
     *
     * @param newGroupName the name of the new user group
     */
    private void createUserGroupAndAddToMap(String newGroupName) 
    {
        User_Group newGroup = new User_Group(newGroupName);
        user_groups.put(newGroupName, newGroup);

        System.out.println(newGroupName + "(" + newGroup.getGroupCreationTime() + ")");
        
    }

    /**
     * Updates the tree view to include the new user group.
     *
     * @param newGroupName the name of the new user group
     */
    private void updateTreeViewWithGroup(String newGroupName) 
    {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeView.getSelectionPath().getLastPathComponent();

        selectedNode.add(new DefaultMutableTreeNode(newGroupName, true));
        addGroupField.setText("");

        DefaultTreeModel model = (DefaultTreeModel) treeView.getModel();

        model.reload();
        expandTree(treeView);
    }

    /**
     * Displays a message indicating the user group already exists.
     *
     * @param newGroupName the name of the existing user group
     */
    private void displayGroupExistsMessage(String newGroupName) 
    {
        JOptionPane.showMessageDialog(this, "Group " + newGroupName + " already exists!");
    }

    /**
     * Displays a message indicating the group name is invalid.
     */
    private void displayInvalidGroupNameMessage() 
    {
        JOptionPane.showMessageDialog(this, "Invalid group name. Please use alphanumeric characters only.");
    }

    /**
     * Returns the map of users.
     *
     * @return the map of users
     */
    public HashMap<String, User> getUsers() 
    {
        return users;
    }

    /**
     * Returns the map of user groups.
     *
     * @return the map of user groups
     */
    public HashMap<String, User_Group> getGroups() 
    {
        return user_groups;
    }

    public boolean verifyUnique_IDs()
    {
        Set<String> unique_IDs = new HashSet<>();
        for(User user : users.values())
        {
            String unique_ID = user.getUnique_ID();

            if(unique_ID.contains(" "))
            {
                return false;
            }

            if(!unique_IDs.add(unique_ID))
            {
                return false;
            }
        
        }
        return true;
    }

    public boolean verifyGroupUnique_IDs()
    {
        ArrayList<String> groupUnique_IDs = new ArrayList<>();
        for(String groupUnique_ID : user_groups.keySet())
        {
            if(groupUnique_ID.contains(" ") || groupUnique_ID.contains(groupUnique_ID))
            {
                return false;
            }
            groupUnique_IDs.add(groupUnique_ID);
        }
        return true;
    }

    public void getLastUpdatedUser(User user)
    {
        UserView userView = UserView.getUserView(user);
        lastUpdatedUser = userView.getLastUpdatedUser();

    }

}