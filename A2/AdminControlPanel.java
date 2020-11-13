package A2;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import visitorPackage.CountMessageVisitor;
import visitorPackage.CountUserGroupVisitor;
import visitorPackage.CountUserVisitor;
import visitorPackage.PositiveMessageVisitor;

/**
 * The admin control panel used as the main GUI for the mini twitter application.
 * @author Payton Perchez
 */
public class AdminControlPanel {

	private static AdminControlPanel reference;
	private Stage stage;
	
	/**
	 * Instantiates a new AdminControlPanel and its various UI components.
	 */
	private AdminControlPanel() {
		
		stage = new Stage();
		stage.setTitle("Admin Control Panel");
		
		HBox adminControlPanel = new HBox();
		
		//Label used for notifying admin of errors and other info
		Label adminMessageLabel = new Label();
		
		//Tree icon images
		Image folderIcon = new Image("file:/C:/Users/payto/OneDrive/Pictures/Folder Icon.png");
		Image fileIcon = new Image("file:/C:/Users/payto/OneDrive/Pictures/File Icon.png");
		
		//Tree components
		TreeView<TwitterEntry> treeView = new TreeView<TwitterEntry>();
		treeView.setPrefHeight(255);
		TreeItem<TwitterEntry> root = new TreeItem<TwitterEntry>(new UserGroup("Root"), new ImageView(folderIcon));
		root.setExpanded(true);
		treeView.setRoot(root);
		
		//Admin controls container
		VBox adminControls = new VBox();
		adminControls.setAlignment(Pos.TOP_CENTER);
		adminControls.setSpacing(15);
		
		//Add controls container
		GridPane addControls = new GridPane();
		addControls.setPadding(new Insets(7));
		addControls.setHgap(10);
		addControls.setVgap(5);
		
		//Controls for adding users and groups
		Label userLabel = new Label("New User:");
		Label groupLabel = new Label("New Group:");
		TextField newUserID = new TextField();
		TextField newGroupID = new TextField();
		
		Button addUser = new Button("Add User");
		addUser.setPrefWidth(74.5);
		addUser.setOnAction(e -> {
			
			//No duplicates allowed
			if(findUser((UserGroup)(treeView.getRoot().getValue()), newUserID.getText()) == null) {
				
				//Blank ID's not allowed
				if(!newUserID.getText().isBlank()) {
					
					TreeItem<TwitterEntry> selectedItem = treeView.getSelectionModel().getSelectedItem();
					TreeItem<TwitterEntry> newItem = new TreeItem<TwitterEntry>(new User(newUserID.getText()), new ImageView(fileIcon));
					
					//Add the new entry to the root group if nothing is selected
					if(selectedItem == null) {
						
						root.getChildren().add(newItem);
						((UserGroup)root.getValue()).addEntry(newItem.getValue());
						
					//Add the new entry to the parent of the selected user
					}else if((selectedItem.getValue() instanceof User)) {
						
						selectedItem.getParent().getChildren().add(newItem);
						((UserGroup)selectedItem.getParent().getValue()).addEntry(newItem.getValue());
						
					//Add the new entry to the selected group
					}else {
						
						selectedItem.getChildren().add(newItem);
						((UserGroup)selectedItem.getValue()).addEntry(newItem.getValue());
						
					}//end if
					
				}else {
					
					adminMessageLabel.setText("Error: the new user must have an ID");
					
				}//end if
				
			}else {
				
				adminMessageLabel.setText("Error: an entry with that ID already exists");
				
			}//end if
			
		});
		
		Button addGroup = new Button("Add Group");
		addGroup.setOnAction(e -> {
			
			//No duplicates allowed
			if(findGroup((UserGroup)(treeView.getRoot().getValue()), newGroupID.getText()) == null) {
				
				//Blank ID's not allowed
				if(!newGroupID.getText().isBlank()) {
					
					TreeItem<TwitterEntry> selectedItem = treeView.getSelectionModel().getSelectedItem();
					TreeItem<TwitterEntry> newItem = new TreeItem<TwitterEntry>(new UserGroup(newGroupID.getText()), new ImageView(folderIcon));
					newItem.setExpanded(true);
					
					//Add the new entry to the root group if nothing is selected
					if(selectedItem == null) {
						
						root.getChildren().add(newItem);
						((UserGroup)root.getValue()).addEntry(newItem.getValue());
					
					//Add the new entry to the parent of the selected user
					}else if(selectedItem.getValue() instanceof User) {
						
						selectedItem.getParent().getChildren().add(newItem);
						((UserGroup)selectedItem.getParent().getValue()).addEntry(newItem.getValue());
						
					//Add the new entry to the selected group
					}else {
						
						selectedItem.getChildren().add(newItem);
						((UserGroup)selectedItem.getValue()).addEntry(newItem.getValue());
						
					}//end if
					
				}else {
					
					adminMessageLabel.setText("Error: the new user group must have an ID");
					
				}//end if
				
			}else {
				
				adminMessageLabel.setText("Error: an entry with that ID already exists");
				
			}//end if
			
		});
		
		//Combine the adding controls into a GridPane, format: (Node, column, row)
		addControls.add(userLabel, 0, 0);
		addControls.add(groupLabel, 0, 1);
		addControls.add(newUserID, 1, 0);
		addControls.add(newGroupID, 1, 1);
		addControls.add(addUser, 2, 0);
		addControls.add(addGroup, 2, 1);
		
		Button openUserView = new Button("Open User View");
		openUserView.setPrefWidth(308);
		openUserView.setOnAction(e -> {
			
			TreeItem<TwitterEntry> selectedItem = treeView.getSelectionModel().getSelectedItem();
			
			//An entry must be selected
			if(selectedItem != null) {
				
				//Cannot open UserGroups
				if(selectedItem.getValue() instanceof User) {
					
					User selectedUser = (User)selectedItem.getValue();
					
					//User controls container
					VBox userControlPanel = new VBox();
					userControlPanel.setAlignment(Pos.TOP_CENTER);
					
					//Label used for notifying user of errors and other info
					Label userMessageLabel = new Label();
					userMessageLabel.setPadding(new Insets(7));
					
					//Controls for following other users
					HBox followControls = new HBox();
					followControls.setPadding(new Insets(7));
					followControls.setSpacing(5);
					TextField specifiedUserID = new TextField();
					
					Button followUser = new Button("Follow User");
					followUser.setOnAction(followEvent -> {
						
						//Users cannot follow themselves
						if(!(specifiedUserID.getText()).equals(selectedUser.toString())) {
							
							User locatedUser = findUser((UserGroup)(treeView.getRoot().getValue()), specifiedUserID.getText());
							
							//The user being followed must exist
							if(locatedUser != null) {
								
								selectedUser.follow(locatedUser);
								
							}else {
								
								userMessageLabel.setText("Error: the user cannot be found");
								
							}//end if
							
						}else {
							
							userMessageLabel.setText("Error: you cannot follow yourself");
							
						}//end if
						
					});
					
					//Combine follow controls into an HBox
					followControls.getChildren().addAll(specifiedUserID, followUser);
					
					//ListView of users being followed
					ListView<String> following = selectedUser.getFollowing();
					following.setPrefHeight(150);
					following.setPrefWidth(150);
					
					//Controls for tweeting messages
					HBox tweetControls = new HBox();
					tweetControls.setPadding(new Insets(7));
					tweetControls.setSpacing(5);
					TextField tweetMessage = new TextField();
					
					Button postTweet = new Button("Post Tweet");
					postTweet.setOnAction(tweetEvent -> {
						
						selectedUser.tweet(tweetMessage.getText());
						
					});
					
					//Combine tweet controls into an HBox
					tweetControls.getChildren().addAll(tweetMessage, postTweet);
					
					//ListView of followed user messages (tweets)
					ListView<String> feed = selectedUser.getFeed();
					feed.setPrefHeight(150);
					feed.setPrefWidth(150);
					
					//Combine user controls into a VBox
					userControlPanel.getChildren().addAll(followControls, following, tweetControls, feed, userMessageLabel);
					
					//Display the user control panel
					Scene userScene = new Scene(userControlPanel);
					Stage userView = new Stage();
					userView.setTitle(selectedItem.getValue().toString());
					userView.setScene(userScene);
					userView.show();
					
					//Reset the message label every time the user clicks on the GUI
					userScene.setOnMouseClicked(clickEvent -> {
						
						userMessageLabel.setText("");
						
					});
					
				}else {
					
					adminMessageLabel.setText("Error: please select a user, not a user group");
					
				}//end if
				
			}else {
				
				adminMessageLabel.setText("Error: please select a user to view");
				
			}//end if
			
		});
		
		//Analytic controls
		GridPane analyticsControls = new GridPane();
		analyticsControls.setAlignment(Pos.BOTTOM_CENTER);
		analyticsControls.setPadding(new Insets(7));
		analyticsControls.setHgap(5);
		analyticsControls.setVgap(5);
		
		Button showUserTotal = new Button("Show User Total");
		showUserTotal.setPrefWidth(152);
		showUserTotal.setOnAction(e -> {
			
			//Count the total number of users
			CountUserVisitor visitor = new CountUserVisitor();
			visitor.visitEntry((UserGroup)(treeView.getRoot().getValue()));
			adminMessageLabel.setText("Total number of users: " + visitor.getCount());
			
		});
		
		Button showMessagesTotal = new Button("Show Messages Total");
		showMessagesTotal.setPrefWidth(152);
		showMessagesTotal.setOnAction(e -> {
			
			//Count the total number of messages (tweets)
			CountMessageVisitor visitor = new CountMessageVisitor();
			visitor.visitEntry((UserGroup)(treeView.getRoot().getValue()));
			adminMessageLabel.setText("Total number of messages: " + visitor.getCount());
			
		});
		
		Button showGroupTotal = new Button("Show Group Total");
		showGroupTotal.setPrefWidth(152);
		showGroupTotal.setOnAction(e -> {
			
			//Count the total number of groups excluding the root
			CountUserGroupVisitor visitor = new CountUserGroupVisitor();
			visitor.visitEntry((UserGroup)(treeView.getRoot().getValue()));
			adminMessageLabel.setText("Total number of user groups: " + (visitor.getCount() - 1));
			
		});
		
		Button showPositivePercentage = new Button("Show Positive Percentage");
		showPositivePercentage.setPrefWidth(152);
		showPositivePercentage.setOnAction(e -> {
			
			//Find the percentage of positive messages (tweets)
			PositiveMessageVisitor visitor = new PositiveMessageVisitor();
			visitor.visitEntry((UserGroup)(treeView.getRoot().getValue()));
			adminMessageLabel.setText("Total number of users: " + String.format("%.2f", visitor.getPositivePercentage()) + "%");
			
		});
		
		//Combine the analytics controls into a GridPane, format: (Node, column, row)
		analyticsControls.add(showUserTotal, 0, 0);
		analyticsControls.add(showMessagesTotal, 0, 1);
		analyticsControls.add(showGroupTotal, 1, 0);
		analyticsControls.add(showPositivePercentage, 1, 1);
		
		//Combine all the admin controls into a VBox
		adminControls.getChildren().addAll(addControls, openUserView, analyticsControls, adminMessageLabel);
		
		//Combine the tree view and admin control VBox into an HBox
		adminControlPanel.getChildren().addAll(treeView, adminControls);
		Scene scene = new Scene(adminControlPanel);
		stage.setScene(scene);
		
		//Reset the message label every time the user clicks on the GUI
		scene.setOnMouseClicked(e -> {
			
			adminMessageLabel.setText("");
			
		});
		
	}//end AdminControlPanel
	
	/**
	 * Searches a given UserGroup for a User with an ID that matches the specified target string.
	 * @param root The given UserGroup.
	 * @param target The ID being searched for.
	 * @return The User corresponding to the target String or null if none were found.
	 */
	private User findUser(UserGroup root, String target) {
		
		for(TwitterEntry entry : root.getEntries()) {
			
			if(entry instanceof User) {
				
				//Check if the User's ID matches the target String
				if(target.equals(entry.toString())) {
					
					return (User)entry;
					
				}//end if
				
			}else {
				
				//Search for the user within the UserGroup
				User result = findUser((UserGroup)entry, target);
				
				//Return the correct User if it was found
				if(result != null) {
					
					return result;
					
				}//end if
				
			}//end if
			
		}//end for
		
		return null;
		
	}//end findUser
	
	/**
	 * Searches a given UserGroup for a UserGroup with an ID that matches the specified target string.
	 * @param root The given UserGroup.
	 * @param target The ID being searched for.
	 * @return The UserGroup corresponding to the target String or null if none were found.
	 */
	private UserGroup findGroup(UserGroup root, String target) {
		
		for(TwitterEntry entry : root.getEntries()) {
			
			//Skip over Users
			if(!(entry instanceof User)) {
				
				//Check if the UserGroup's ID matches the target String
				if(target.equals(entry.toString())) {
					
					return (UserGroup)entry;
					
				}else {
					
					//Search for the UserGroup within the UserGroup
					UserGroup result = findGroup((UserGroup)entry, target);
					
					//Return the correct UserGroup if it was found
					if(result != null) {
						
						return result;
						
					}//end if
					
				}//end if
				
			}//end if
			
		}//end for
		
		return null;
		
	}//end findGroup
	
	/**
	 * Returns the reference to an AdminControlPanel object and instantiates one if the reference points to null.
	 * @return The reference to an AdminControlPanel object.
	 */
	public static AdminControlPanel getInstance() {
		
		if(reference == null) {
			
			reference = new AdminControlPanel();
			
		}//end if
		
		return reference;
		
	}//end getInstance
	
	/**
	 * Displays the admin control panel.
	 */
	public void show() {
		
		if(reference != null) {
			
			stage.show();
			
		}else {
			
			throw new IllegalStateException("Error: panel not instantiated yet");
			
		}//end if
		
	}//end show
	
}//end AdminControlPanel
