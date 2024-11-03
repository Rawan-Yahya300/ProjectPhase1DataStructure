package application;

import java.util.Date;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LocationScreen {
	// Declare Buttons,TextFields,Labels,Panes and nodes we needed
	private DNode<District> district; // this is the district that we display its locations
	private Node<Location> currLoc;

	private Button insertLocBtn;
	private Label insertLocName;
	private TextField insertLocTxt;
	private Label insertLocResult;

	private Button updateLocBtn;
	private Label updateLbl;
	private TextField updatedLocTxt;
	private TextField newLocTxt;
	private Label updateLocResult;

	private Button deleteLocBtn;
	private Label deleteLocationName;
	private TextField deleteLocTxt;
	private Label deleteLocResult;

	private Button next;
	private Label statistics;
	private Label numOfMartyrs;
	private Label numOfMale;
	private Label numOfFemale;
	private Label AvgAge;
	private Label youngestMartyr;
	private Label oldestMartyr;

	private Button insertMartyrBtn;
	private Label insertMartyrSyntax;
	private TextField insertMartyrTxt;
	private Label insertMartyrResult;

	private Button updateMartyrBtn;
	private Label updatedMartyrLbl;
	private TextField updatedMartyrTxt;
	private Label newMartyrLbl;
	private TextField newMartyrTxt;
	private Label updateMartyrResult;

	private Button deleteMartyrBtn;
	private Label deleteMartyrNameLbl;
	private TextField deleteMartyrNameTxt;
	private Label deleteMartyrResult;

	private Button searchAboutMartyrByPartOfName;
	private Label partOfMartyrNameLbl;
	private TextField partOfMartyrNameTxt;
	private Label searchedMartyrLbl;

	private Button back;

	private GridPane grid;

	public LocationScreen(DNode<District> district) {
		// define the objects and nodes
		this.district = district;
		if (district != null) {
			currLoc = this.district.getData().getLocations().getHead();
		}
		grid = new GridPane();
		grid.setVgap(10);
		grid.setHgap(10);
		grid.setAlignment(Pos.CENTER);

		insertLocBtn = new Button("Insert new Location");
		insertLocName = new Label("Enter name of Location");
		insertLocTxt = new TextField();
		insertLocResult = new Label();

		updateLocBtn = new Button("Update Location");
		updateLbl = new Label("Enter the name of location to update it,\n enter new name");
		updatedLocTxt = new TextField();
		newLocTxt = new TextField();
		updateLocResult = new Label();

		deleteLocBtn = new Button("Delete Location");
		deleteLocationName = new Label("Enter the name of the location to delete");
		deleteLocTxt = new TextField();
		deleteLocResult = new Label();

		next = new Button("Next Location");
		statistics = new Label("statistics:");
		numOfMartyrs = new Label();
		numOfMale = new Label();
		numOfFemale = new Label();
		AvgAge = new Label();
		youngestMartyr = new Label();
		oldestMartyr = new Label();

		insertMartyrBtn = new Button("Insert Martyr");
		insertMartyrSyntax = new Label("Enter this information:\n name,date,age,gender");
		insertMartyrTxt = new TextField();
		insertMartyrResult = new Label();

		updateMartyrBtn = new Button("Update Martyr");
		updatedMartyrLbl = new Label("Enter the name of martyr to update");
		updatedMartyrTxt = new TextField();
		newMartyrLbl = new Label("Enter new information for martyr as this:\n name,date,age,gender");
		newMartyrTxt = new TextField();
		updateMartyrResult = new Label();

		deleteMartyrBtn = new Button("Delete Martyr");
		deleteMartyrNameLbl = new Label("Enter the name of martyr to delete");
		deleteMartyrNameTxt = new TextField();
		deleteMartyrResult = new Label();

		searchAboutMartyrByPartOfName = new Button("Search about martyr");
		partOfMartyrNameLbl = new Label("Enter part of his/her name");
		partOfMartyrNameTxt = new TextField();
		searchedMartyrLbl = new Label();

		back = new Button("back");
		// put the nodes in the GridPane
		grid.add(insertLocBtn, 0, 0);
		grid.add(insertLocName, 1, 0);
		grid.add(insertLocTxt, 2, 0);
		grid.add(insertLocResult, 3, 0);

		grid.add(updateLocBtn, 0, 1);
		grid.add(updateLbl, 1, 1);
		grid.add(updatedLocTxt, 2, 1);
		grid.add(newLocTxt, 3, 1);
		grid.add(updateLocResult, 4, 1);

		grid.add(deleteLocBtn, 0, 2);
		grid.add(deleteLocationName, 1, 2);
		grid.add(deleteLocTxt, 2, 2);
		grid.add(deleteLocResult, 3, 2);

		grid.add(next, 0, 3);
		grid.add(statistics, 1, 3);
		grid.add(numOfMartyrs, 1, 4);
		grid.add(numOfMale, 1, 5);
		grid.add(numOfFemale, 1, 6);
		grid.add(AvgAge, 1, 7);
		grid.add(youngestMartyr, 1, 8);
		grid.add(oldestMartyr, 1, 9);

		grid.add(insertMartyrBtn, 0, 10);
		grid.add(insertMartyrSyntax, 1, 10);
		grid.add(insertMartyrTxt, 2, 10);
		grid.add(insertMartyrResult, 3, 10);

		grid.add(updateMartyrBtn, 0, 11);
		grid.add(updatedMartyrLbl, 1, 11);
		grid.add(updatedMartyrTxt, 2, 11);
		grid.add(newMartyrLbl, 3, 11);
		grid.add(newMartyrTxt, 4, 11);
		grid.add(updateMartyrResult, 5, 11);

		grid.add(deleteMartyrBtn, 0, 12);
		grid.add(deleteMartyrNameLbl, 1, 12);
		grid.add(deleteMartyrNameTxt, 2, 12);
		grid.add(deleteMartyrResult, 3, 12);

		grid.add(searchAboutMartyrByPartOfName, 0, 13);
		grid.add(partOfMartyrNameLbl, 1, 13);
		grid.add(partOfMartyrNameTxt, 2, 13);
		grid.add(searchedMartyrLbl, 3, 13);

		grid.add(back, 0, 14);

	}

	// the body of actions
	public void insertLocationAction() { // insert location to the district action //time O(n)
		if (!insertLocTxt.getText().isEmpty()) { // check if the user enter the name of the new location
			Location newLocation = new Location(insertLocTxt.getText());
			if (district.getData().getLocations().find(newLocation) == null) { // if the location does not exist ,
																				// insert it
				district.getData().getLocations().insert(newLocation);
				insertLocResult.setText("Process Done");
			} else { // if it is exist , don`t add it
				insertLocResult.setText("The location is already exist");
			}
		} else {
			insertLocResult.setText("Enter the name of location please");
		}
	}

	public void updateLocationAction() { // update location action //time O(n)
		// check if the user enter the name of location , and new name
		if ((!updatedLocTxt.getText().isEmpty()) && (!newLocTxt.getText().isEmpty())) {
			Location updated = new Location(updatedLocTxt.getText());
			Location newLoc = new Location(newLocTxt.getText());
			if (district.getData().getLocations().find(updated) != null) { // check if the location we want to update it
																			// is exist or not
				if (district.getData().getLocations().find(newLoc) == null) {// check if the new name is name for
																				// another location
					// update the name of the location , remove it , and then add it again
					Location original = district.getData().getLocations().find(updated);
					district.getData().getLocations().delete(district.getData().getLocations().find(updated));
					newLoc = original;
					newLoc.setLocation(newLocTxt.getText());
					district.getData().getLocations().insert(newLoc);
					updateLocResult.setText("Process Done");

				} else { // if the new name is name for another location , don`t update
					updateLocResult.setText("Sorry,the new name is already a name of another location");
				}
			} else {
				updateLocResult.setText("The location does not exist");
			}
		} else {
			updateLocResult.setText("Enter the name of location and new name");
		}
	}

	public void deleteLocationAction() { // delete location action //time O(n)
		if (!deleteLocTxt.getText().isEmpty()) { // check if the user enter the name of the location
			Location location = new Location(deleteLocTxt.getText());
			if (district.getData().getLocations().find(location) != null) { // if the location exist , remove it
				district.getData().getLocations().delete(location);
				deleteLocResult.setText("Process Done");
			} else {
				deleteLocResult.setText("The location does not exist");
			}
		} else {
			deleteLocResult.setText("Enter the name of the location");
		}
	}

	public void nextLocAction(Node<Location> location) { // next location action //time O(n)
		if (location != null) { // display the current location statistics
			statistics.setText("statistics of " + location.getData().getLocation());
			numOfMartyrs.setText("number of martyrs: " + location.getData().numOfMartyrs());
			numOfMale.setText("number of male matyrs: " + location.getData().numOfMaleMartyrs());
			numOfFemale.setText("number of Female matyrs: " + location.getData().numOfFemaleMartyrs());
			AvgAge.setText(" Average martyrs ages: " + location.getData().averageAge());
			if (location.getData().youngestMartyr() != null)
				youngestMartyr.setText("Youngest martyr: " + location.getData().youngestMartyr().toString());
			else
				youngestMartyr.setText("There is no martyrs");
			if (location.getData().oldestMartyr() != null)
				oldestMartyr.setText("oldest martyr: " + location.getData().oldestMartyr().toString());
			else
				oldestMartyr.setText("There is no martyrs");
			if (location == district.getData().getLocations().getHead().getNext()) {
				location = location.getNext();
			}
		} else { // if there is no location , remove the previous statistics
			statistics.setText("There is no locations");
			numOfMartyrs.setText("");

			numOfMale.setText("");

			numOfFemale.setText("");

			AvgAge.setText("");
			youngestMartyr.setText("");
			oldestMartyr.setText("");
		}

	}

	public void insertMartyrAction() { // insert martyr action //time O(n)
		String[] information = insertMartyrTxt.getText().split(",");
		if (information.length == 4) { // check if the user enter all information we needed correctly
			try {
				String name = information[0];
				String[] dateStr = information[1].split("/");
				if (dateStr.length == 3) { // check if the date correct
					  // create a date from the information
					Date dateOfDeath = new Date(Integer.parseInt(dateStr[2]) - 1900, Integer.parseInt(dateStr[0]) - 1, Integer.parseInt(dateStr[1]));
					int age = Integer.parseInt(information[2]);
					char gender = information[3].charAt(0);
					if (Character.toUpperCase(gender) == 'F' || Character.toUpperCase(gender) == 'M') {// check if the gender male or female
						if (currLoc != null && district != null) {
							Martyr martyr = new Martyr(name, dateOfDeath, age, currLoc.getData(), district.getData(),gender);
							currLoc.getData().getMartyrs().insert(martyr); // insert the martyr to this location and this district
							insertMartyrResult.setText("done");
						} else {
							insertMartyrResult.setText("There is no location");
						}
					} else {
						insertMartyrResult.setText("Gender should be M/F");
					}
				} else {
					insertMartyrResult.setText("Enter Date: month/day/year");
				}
			} catch (Exception ex) {
				insertMartyrResult.setText("Enter true information");
			}
		} else {
			insertMartyrResult.setText("Enter complete information");
		}
	}

	public void deleteMartyrAction() { // delete Martyr from this location //time O(n)
		if (!deleteMartyrNameTxt.getText().isEmpty()) { // check if the user enter the name of martyr
			if (currLoc != null) {
				// check if the martyr exist remove it
				if (currLoc.getData().findMartyrUsingName(deleteMartyrNameTxt.getText()) == null) {
					deleteMartyrResult.setText("The martyr does not exist");
				} else {
					currLoc.getData().getMartyrs().delete(currLoc.getData().findMartyrUsingName(deleteMartyrNameTxt.getText()));
					deleteMartyrResult.setText("Process Done");
				}
			} else {
				deleteMartyrResult.setText("There is no location");
			}
		} else {
			deleteMartyrResult.setText("Enter the name of martyr");
		}
	}

	public void updateMartyrAction() { // update martyr action //time O(n)
		if (currLoc != null && district != null) {
			// check if the user enter the name of martyr and check if the martyr exist
			if (!updatedMartyrTxt.getText().isEmpty()
					&& currLoc.getData().findMartyrUsingName(updatedMartyrTxt.getText()) != null) {
				String[] information = newMartyrTxt.getText().split(",");
				if (information.length == 4) {
					try {
						String name = information[0];
						String[] dateStr = information[1].split("/");
						if (dateStr.length == 3) {
							// create a date from the information
							Date dateOfDeath = new Date(Integer.parseInt(dateStr[2]) - 1900, Integer.parseInt(dateStr[0]) - 1, Integer.parseInt(dateStr[1]));
							int age = Integer.parseInt(information[2]);
							char gender = information[3].charAt(0);
							if (Character.toUpperCase(gender) == 'F' || Character.toUpperCase(gender) == 'M') {// check the gender,if it is correctly
								// remove the original martyr , and insert the martyr with new information
								Martyr martyr = new Martyr(name, dateOfDeath, age, currLoc.getData(),district.getData(), gender);
								currLoc.getData().getMartyrs().delete(currLoc.getData().findMartyrUsingName(updatedMartyrTxt.getText()));
								currLoc.getData().getMartyrs().insert(martyr);
								updateMartyrResult.setText("Process Done");
							} else {
								updateMartyrResult.setText("Gender should be M/F");
							}

						} else {
							updateMartyrResult.setText("Enter Date: month/day/year");
						}
					} catch (Exception ex) {
						updateMartyrResult.setText("Enter true information");
					}
				} else {
					updateMartyrResult.setText("Enter complete information");
				}
			} else {
				updateMartyrResult.setText("you don`t enter the name of martyr\n or the martyr does not exist");
			}
		} else {
			updateMartyrResult.setText("There is no location");
		}
	}

	public void findMartyrAction() { // find martyr using part of name action   //time O(n)
		if (!partOfMartyrNameTxt.getText().isEmpty()) { // check if the user enter the part of name
			if (currLoc != null) {
				if (currLoc.getData().findMartyrUsingPartofName(partOfMartyrNameTxt.getText()) != null) { // search about martyr using part of name
					searchedMartyrLbl.setText(currLoc.getData().findMartyrUsingPartofName(partOfMartyrNameTxt.getText()).toString());
				} else {
					searchedMartyrLbl.setText("The martyr does not exist");
				}
			} else {
				searchedMartyrLbl.setText("There is no location");
			}
		} else {
			searchedMartyrLbl.setText("Enter part of the martyr's name");
		}
	}

	// getters and setters as needed
	public DNode<District> getDistrict() {
		return district;
	}

	public Button getInsertLocBtn() {
		return insertLocBtn;
	}

	public Label getInsertLocName() {
		return insertLocName;
	}

	public TextField getInsertLocTxt() {
		return insertLocTxt;
	}

	public Label getInsertLocResult() {
		return insertLocResult;
	}

	public Button getUpdateLocBtn() {
		return updateLocBtn;
	}

	public Label getUpdateLbl() {
		return updateLbl;
	}

	public TextField getUpdatedLocTxt() {
		return updatedLocTxt;
	}

	public TextField getNewLocTxt() {
		return newLocTxt;
	}

	public Label getUpdateLocResult() {
		return updateLocResult;
	}

	public Button getDeleteLocBtn() {
		return deleteLocBtn;
	}

	public Label getDeleteLocationName() {
		return deleteLocationName;
	}

	public TextField getDeleteLocTxt() {
		return deleteLocTxt;
	}

	public Label getDeleteLocResult() {
		return deleteLocResult;
	}

	public Button getNext() {
		return next;
	}

	public Label getStatistics() {
		return statistics;
	}

	public Label getNumOfMartyrs() {
		return numOfMartyrs;
	}

	public Label getNumOfMale() {
		return numOfMale;
	}

	public Label getNumOfFemale() {
		return numOfFemale;
	}

	public Label getAvgAge() {
		return AvgAge;
	}

	public Label getYoungestMartyr() {
		return youngestMartyr;
	}

	public Label getOldestMartyr() {
		return oldestMartyr;
	}

	public Button getInsertMartyrBtn() {
		return insertMartyrBtn;
	}

	public Label getInsertMartyrSyntax() {
		return insertMartyrSyntax;
	}

	public TextField getInsertMartyrTxt() {
		return insertMartyrTxt;
	}

	public Label getInsertMartyrResult() {
		return insertMartyrResult;
	}

	public Button getUpdateMartyrBtn() {
		return updateMartyrBtn;
	}

	public Label getUpdatedMartyrLbl() {
		return updatedMartyrLbl;
	}

	public TextField getUpdatedMartyrTxt() {
		return updatedMartyrTxt;
	}

	public Label getNewMartyrLbl() {
		return newMartyrLbl;
	}

	public TextField getNewMartyrTxt() {
		return newMartyrTxt;
	}

	public Label getUpdateMartyrResult() {
		return updateMartyrResult;
	}

	public Button getDeleteMartyrBtn() {
		return deleteMartyrBtn;
	}

	public Label getDeleteMartyrNameLbl() {
		return deleteMartyrNameLbl;
	}

	public TextField getDeleteMartyrNameTxt() {
		return deleteMartyrNameTxt;
	}

	public Label getDeleteMartyrResult() {
		return deleteMartyrResult;
	}

	public Button getSearchAboutMartyrByPartOfName() {
		return searchAboutMartyrByPartOfName;
	}

	public Label getPartOfMartyrNameLbl() {
		return partOfMartyrNameLbl;
	}

	public TextField getPartOfMartyrNameTxt() {
		return partOfMartyrNameTxt;
	}

	public Label getSearchedMartyrLbl() {
		return searchedMartyrLbl;
	}

	public GridPane getGrid() {
		return grid;
	}

	public Button getBack() {
		return back;
	}

	public Node<Location> getCurrLoc() {
		return currLoc;
	}

	public void setCurrLoc(Node<Location> currLoc) {
		this.currLoc = currLoc;
	}

	public void setDistrict(DNode<District> district) {
		this.district = district;
	}

}
