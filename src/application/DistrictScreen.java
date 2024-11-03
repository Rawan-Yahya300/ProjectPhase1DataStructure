package application;

import java.util.Date;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class DistrictScreen {

	//Declare all Buttons , TextFilds , Label and panes we needed
	private Button insertBtn;
	private TextField insertTxt;
	private Label insertResult;
	
	private Button updateBtn;
	private TextField updatedTxt;
	private TextField newTxt;
	private Label updateResult;
	
	private Button deleteBtn;
	private TextField deleteTxt;
	private Label deleteResult;
	
	private Button next;
	private Button prev;
	private Label statistics;
	private Label numOfMartyrs;
	private Label numOfMale;
	private Label numOfFemale;
	private Label AvgAge;
	private Label dateHasMaxMar;
	
	private Button numOfMarInThisDayBtn;
	private TextField numOfMarInThisDayTxt;
	private Label numOfMarInThisDayResult;
	
	private Button locationScreen;
	private Label locationScreenLbl;
	private Button back;
	
	private GridPane grid ;
	
	public DistrictScreen() {
		//define the nodes 
		insertBtn = new Button("Insert District");
		insertTxt = new TextField();
		insertResult = new Label();
		
		updateBtn = new Button("Update District");
	    updatedTxt = new TextField();
		newTxt = new TextField();
	    updateResult = new Label();
	    
	     deleteBtn = new Button("Delete District");
		 deleteTxt = new TextField();
		 deleteResult = new Label();
		
		 next = new Button("Next District");
		 prev = new Button("Previous District");
		 statistics = new Label("statistics:");
		 numOfMartyrs = new Label();
	     numOfMale = new Label();
		 numOfFemale = new Label();
		 AvgAge = new Label();
		 dateHasMaxMar = new Label();
	    
		 numOfMarInThisDayBtn = new Button("number of Martyrs in this day:");
		 numOfMarInThisDayTxt = new TextField();
         numOfMarInThisDayResult = new Label();
			
		 locationScreen = new Button("Location Screen");
		 locationScreenLbl = new Label();
	     back = new Button("Back");
			
	     grid = new GridPane();
	     //add the nodes to the GridPane as needed
	     grid.add(insertBtn, 0, 0);
	     grid.add(insertTxt, 1, 0);
	     grid.add(insertResult, 2, 0);
	     
	     grid.add(updateBtn, 0, 1);
	     grid.add(updatedTxt, 1, 1);
	     grid.add(newTxt, 2, 1);
	     grid.add(updateResult, 3, 1);
	     
	     grid.add(deleteBtn, 0, 2);
	     grid.add(deleteTxt, 1, 2);
	     grid.add(deleteResult, 2, 2);
	     
	     grid.add(next, 2, 3);
	     grid.add(prev, 0, 3);
	     grid.add(statistics, 1, 3);
	     grid.add(numOfMartyrs, 1, 4);
	     grid.add(numOfMale, 1, 5);
	     grid.add(numOfFemale, 1, 6);
	     grid.add(AvgAge, 1, 7);
	     grid.add(dateHasMaxMar, 1, 8);
	     
	     grid.add(numOfMarInThisDayBtn, 0, 9);
	     grid.add(numOfMarInThisDayTxt, 1, 9);
	     grid.add(numOfMarInThisDayResult, 2, 9);
	     
	     grid.add(locationScreen, 0, 10);
	     grid.add(locationScreenLbl, 1, 10);
	     grid.add(back, 0, 11);
	     
	     grid.setAlignment(Pos.CENTER);
	     grid.setVgap(15);
	     grid.setHgap(15);


	}
    
	
	//make the body of events of this screen
	
	public void insertAction() {     //action of insert district      //time: O(n)
		if(!insertTxt.getText().isEmpty()) {      //check if the user enter the name of district to insert it
			District newDistrict = new District(insertTxt.getText());
		    if(Driver.districts.find(newDistrict) == null) { //if the district does not exist , insert it
			  Driver.districts.insert(newDistrict);
		      insertResult.setText("Process is done");
		    }
		    else { //if the district exist , don`t insert it again
		    	insertResult.setText("The district already exist");
		    }
		}else {
			insertResult.setText("Enter the name of district please");
		}
		
	}
	
	public void updateAction() {    //Action of update district      //time O(n)
		//check if the user enter the name of district to update it , and new name
		if ((!updatedTxt.getText().isEmpty()) && (!newTxt.getText().isEmpty())) {
			District updated = new District(updatedTxt.getText());
			District newDis = new District(newTxt.getText());
			if (Driver.districts.find(updated) != null) { //check if the district we want to update it is exist or no
				if (Driver.districts.find(newDis) == null) { //check if the new name is name for another district
					//update the district by change its name and delete it then add it again
					District original = Driver.districts.find(updated);
					Driver.districts.delete(Driver.districts.find(updated));
					newDis = original;
					newDis.setDistrict(newTxt.getText());
					Driver.districts.insert(newDis);
					updateResult.setText("Process Done");

				} else {
					updateResult.setText("Sorry,the new name is already a name of another District");
				}
			} else {
				updateResult.setText("The District does not exist");
			}
		} else {
			updateResult.setText("Enter the name of District and new name");
		}
	}
	
	public void deleteAction() {  //delete district action    //time O(n)
		if(!deleteTxt.getText().isEmpty()) { //check if the user enter the name of district to delete it
			District district = new District(deleteTxt.getText());
			if(Driver.districts.find(district) != null) { //check if the district exist or not
				
				Driver.districts.delete(district);   //if it is exist , delete it
				if(Driver.districts.getHead().getNext() == null) {
					Driver.curr = Driver.districts.getHead();
					Driver.prev = null;
				}
				deleteResult.setText("Process Done");
			}
			else {
				deleteResult.setText("The district does not exist");
			}
		}
		else {
			deleteResult.setText("Enter the name of the district");
		}
	}
	
	public void navigateNext(DNode<District> curr) { //navigate next action   //time O(n^3)
		if(curr != null && curr.getData() != null) {    //if the district not null , display its statistics
			statistics.setText("statistics of: "+curr.getData().getDistrict());
			numOfMartyrs.setText("Total number of martyrs: "+curr.getData().numOfMartyrs());
		    numOfMale.setText("Total number of male martyrs: "+curr.getData().numOfMaleMartyrs());;
			numOfFemale.setText("Total number of Female martyrs: "+curr.getData().numOfFemaleMartyrs());;
			AvgAge.setText("Average martyrs ages: "+curr.getData().averageAge());
			if(curr.getData().dateHasMaxNumOfMartyrs() != null) {
			dateHasMaxMar.setText("The date that has the maximum number of martyrs:\n"
					+ ""+ (curr.getData().dateHasMaxNumOfMartyrs().getMonth()+1)+"/"+curr.getData().dateHasMaxNumOfMartyrs().getDate()+"/"+(curr.getData().dateHasMaxNumOfMartyrs().getYear()+1900));
			}
			else {
				dateHasMaxMar.setText("There is no martyrs");
			}
		}
		else {  //if it is  null , remove the previous statistics
			statistics.setText("statistics:");
			numOfMartyrs.setText("No more districts");
			numOfMale.setText("");
			numOfFemale.setText("");
			AvgAge.setText("");
			dateHasMaxMar.setText("");
		}
		
	}
	public void navigatePrev(DNode<District> curr) { //navigate previous action   //time O(n^3)
		if(curr != null && curr.getData() != null) {  //if the district not null , display its statistics
			statistics.setText("statistics of: "+curr.getData().getDistrict());
			numOfMartyrs.setText("Total number of martyrs: "+curr.getData().numOfMartyrs());
		    numOfMale.setText("Total number of male martyrs: "+curr.getData().numOfMaleMartyrs());;
			numOfFemale.setText("Total number of Female martyrs: "+curr.getData().numOfFemaleMartyrs());;
			AvgAge.setText("Average martyrs ages: "+curr.getData().averageAge());;
			if(curr.getData().dateHasMaxNumOfMartyrs() != null) {
			dateHasMaxMar.setText("The date that has the maximum number of martyrs: \n"
					+ ""+ (curr.getData().dateHasMaxNumOfMartyrs().getMonth()+1)+"/"+curr.getData().dateHasMaxNumOfMartyrs().getDate()+"/"+(curr.getData().dateHasMaxNumOfMartyrs().getYear()+1900));
			}
			else {
				dateHasMaxMar.setText("There is no martyrs");
			}
			
		}
		else {  //if it is  null , remove the previous statistics
			statistics.setText("statistics:");
			numOfMartyrs.setText("No more districts");
			numOfMale.setText("");
			numOfFemale.setText("");
			AvgAge.setText("");
			dateHasMaxMar.setText("");
		}
		
	}
	 public void numOfMarInThisDayAction() { //number of martyr in specific date action (in all districts)  //time O(n^3)
		 String[] date = numOfMarInThisDayTxt.getText().split("/");
			if (date.length == 3) {  //check if the user enter a date
				try {
				Date dateOfDeat = new Date(Integer.parseInt(date[2]) - 1900, Integer.parseInt(date[0]) - 1, Integer.parseInt(date[1]));
				DNode<District> curr = Driver.districts.getHead().getNext(); //start with first district 
				int numOfMartyrs = 0;
				while(curr != null) {  //calculate number of martyr in this day in each district
					numOfMartyrs += curr.getData().numOfMartyrsInThisDate(dateOfDeat);
					curr = curr.getNext();
				}
				numOfMarInThisDayResult.setText("Toltal number of martyrs in this day: "+numOfMartyrs);//display it in the label
				}catch(Exception ex) {
					numOfMarInThisDayResult.setText("Enter date in this syntax: month/day/year");
				}
			}
			else {
				numOfMarInThisDayResult.setText("Enter date in this syntax: month/day/year");
			}
	 }
	 
	 
	 //getters  as needed
	public Button getInsertBtn() {
		return insertBtn;
	}

	public TextField getInsertTxt() {
		return insertTxt;
	}

	public Label getInsertResult() {
		return insertResult;
	}

	public Button getUpdateBtn() {
		return updateBtn;
	}

	public TextField getUpdatedTxt() {
		return updatedTxt;
	}

	public TextField getNewTxt() {
		return newTxt;
	}

	public Label getUpdateResult() {
		return updateResult;
	}

	public Button getDeleteBtn() {
		return deleteBtn;
	}

	public TextField getDeleteTxt() {
		return deleteTxt;
	}

	public Label getDeleteResult() {
		return deleteResult;
	}

	public Button getNext() {
		return next;
	}

	public Button getPrev() {
		return prev;
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

	public Label getDateHasMaxMar() {
		return dateHasMaxMar;
	}

	public Button getNumOfMarInThisDayBtn() {
		return numOfMarInThisDayBtn;
	}

	public TextField getNumOfMarInThisDayTxt() {
		return numOfMarInThisDayTxt;
	}

	public Label getNumOfMarInThisDayResult() {
		return numOfMarInThisDayResult;
	}

	public Button getLocationScreen() {
		return locationScreen;
	}

	public Button getBack() {
		return back;
	}

	public Label getLocationScreenLbl() {
		return locationScreenLbl;
	}

	public GridPane getGrid() {
		
		return grid;
	}
 
}
