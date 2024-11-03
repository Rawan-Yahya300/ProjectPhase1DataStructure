package application;

import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;


public class Driver extends Application {
	static DLinkedList<District> districts = new DLinkedList<>(); //the list of districts
	//the nodes to navigate throw districts
	static DNode<District> curr = null;
	static DNode<District> prev = null;
	LocationScreen locSc = new LocationScreen(curr); //object of location screen
	Scene locScene = new Scene(locSc.getGrid(), 1550, 800); //scene of the location screen
	
	@Override
	public void start(Stage primaryStage) {
		try {

			
			DistrictScreen disSc = new DistrictScreen(); //object of district screen 
			Button chooseFile = new Button("choos file and upload data"); // this button is to choose file
			Button DistrictScreenBtn = new Button("District Screen"); //button to display the district screen
			Scene DisScene = new Scene(disSc.getGrid(), 1550, 800); //scene of district screen
			VBox vbox = new VBox(10);
			vbox.getChildren().addAll(chooseFile, DistrictScreenBtn);
			Scene scene = new Scene(vbox, 1550, 800);
			vbox.setAlignment(Pos.CENTER);

			primaryStage.setScene(scene);
			primaryStage.setTitle("choose file");
			primaryStage.show();

			//read from file
			chooseFile.setOnAction(e -> { // this action to Let the user to choose the file
				FileChooser filechooser = new FileChooser(); // create a file chooser
				filechooser.setTitle("Choose file"); // title of file chooser
				filechooser.setInitialDirectory(new File("C:\\")); // the initial directory when the file chooser opened
				// the type of files appears on file chooser
				filechooser.getExtensionFilters().addAll(new ExtensionFilter("csv files", "*.csv")); 
				File selectedFile = filechooser.showOpenDialog(primaryStage);
				if (selectedFile != null) { // if the selected file not null
					try {
						Scanner sc = new Scanner(selectedFile); // read the information of martyrs from the file
						while (sc.hasNext()) {
							String[] line = sc.nextLine().split(",");
							if (line.length == 6) { // if the lines contains all information read to store
								if (Character.toUpperCase(line[5].charAt(0)) == 'F'
										|| Character.toUpperCase(line[5].charAt(0)) == 'M') { // if the gender F or M store the martyr
									String[] date = line[1].split("/");
									if (date.length == 3) { // check the date
										try {
											int age = Integer.parseInt(line[2]);
											if (age > 0 && age < 150) {
												// create a date from the information
												Date dateOfDeath = new Date(Integer.parseInt(date[2]) - 1900, 
														Integer.parseInt(date[0]) - 1, Integer.parseInt(date[1]));
												Martyr martyr = new Martyr(line[0], dateOfDeath, age,
														new Location(line[3]), new District(line[4]),
														line[5].charAt(0)); // create a martyr from the information	
												//check if the district does not exist , insert it
												if (districts.find(martyr.getDistrict()) == null) {
													districts.insert(martyr.getDistrict());
												}
												//check if the location does not exist , insert it
												if ((districts.find(martyr.getDistrict()).getLocations().find(martyr.getLocation())) == null) {
													districts.find(martyr.getDistrict()).getLocations().insert(martyr.getLocation());
												}
												//insert martyr
												(districts.find(martyr.getDistrict())).getLocations().find(martyr.getLocation()).getMartyrs().insert(martyr);
											}
										} catch (Exception ex) {

										}
									}
								}
							}
						}
						curr = districts.getHead();  //set the current at the beginning of districts list
						//display all districts and their locations
						DNode<District> curr1 = districts.getHead().getNext();
						int i = 1;
						while (curr1 != null) {
							System.out.println(i + " " + curr1.getData().toString() + " "
									+ curr1.getData().getLocations().toString());
							curr1 = curr1.getNext();
							i++;
						}
					} catch (FileNotFoundException e1) {

					}
				}

			});
			
			DistrictScreenBtn.setOnAction(e -> { //display district screen action

				primaryStage.setScene(DisScene); 
				primaryStage.setTitle("District Screen");
			});
			disSc.getInsertBtn().setOnAction(e -> { //insert district action
				disSc.insertAction(); //we call the method to insert district
				if(curr == null) {
					curr = districts.getHead();
					
				}

			});
			disSc.getUpdateBtn().setOnAction(e -> { //update district action
				disSc.updateAction(); //we call the method to update district
				

			});
			disSc.getDeleteBtn().setOnAction(e -> { //delete district action
				disSc.deleteAction();    //call the method to delete district

			});
			disSc.getBack().setOnAction(e -> {  //back to the first stage action
				curr = districts.getHead();  //return the current district to the beginning
				prev = null;
				primaryStage.setTitle("choose file");
				//clear all TextField and labels
				disSc.getInsertTxt().setText(""); 
				disSc.getInsertResult().setText(""); 
				
			    disSc.getUpdatedTxt().setText(""); 
				disSc.getNewTxt().setText(""); 
			    disSc.getUpdateResult().setText(""); 
			    
			    
				 disSc.getDeleteTxt().setText(""); 
				 disSc.getDeleteResult().setText("");
				 disSc.getStatistics().setText("statistics:"); 
				 disSc.getNumOfMartyrs().setText(""); 
			     disSc.getNumOfMale().setText(""); 
				 disSc.getNumOfFemale().setText("");
				 disSc.getAvgAge().setText(""); 
				 disSc.getDateHasMaxMar().setText(""); 
			 
				 disSc.getNumOfMarInThisDayTxt().setText(""); 
		         disSc.getNumOfMarInThisDayResult().setText(""); 
					
				
				 disSc.getLocationScreenLbl().setText("");
				primaryStage.setScene(scene);
			});
			disSc.getNext().setOnAction(e -> {  //navigate next action
				if (curr != null ) { //if the current not null , get its next and call the method that display statistics
					prev = curr;
					curr = curr.getNext();
					disSc.navigateNext(curr);
				}
			});
			disSc.getPrev().setOnAction(e -> {  //navigate previous action
				//if the current not the first district , get its previous and then call the method that display its statistics 
				if (curr != districts.getHead().getNext() && curr != null && prev != null) { 
					prev = prev.getPrev();
					curr = curr.getPrev();
					if(curr.getData() != null)
					disSc.navigatePrev(curr);
					//if we arrive the end of district , go back to the last district
				}else if(curr == null && prev != null) { 
					curr = prev;
					prev = curr.getPrev();
					disSc.navigatePrev(curr);
				}

			});
			disSc.getNumOfMarInThisDayBtn().setOnAction(e -> { //number of martyr in specific date action
				disSc.numOfMarInThisDayAction(); //call the method that calculate the number of martyr in specific date
			});

			disSc.getLocationScreen().setOnAction(e -> {  //display location screen action
				if (curr != null && curr != districts.getHead()) { 
					//if the district not null , display the location screen with its locations , start with first location
					locSc.setDistrict(curr);
					primaryStage.setTitle("Location Screen");
					primaryStage.setScene(locScene);

				} else {
					disSc.getLocationScreenLbl().setText("There is no district to open its locations");
				}
			});
			locSc.getInsertLocBtn().setOnAction(e -> { //insert location action
				locSc.insertLocationAction();  //invoke the method to insert new location
			});
			locSc.getBack().setOnAction(e -> { //back to the district screen action
				//clear the TextFields and Labels
				locSc.getStatistics().setText("statistics");
				locSc.getNumOfMartyrs().setText("");
				locSc.getNumOfMale().setText("");
				locSc.getNumOfFemale().setText("");
				locSc.getAvgAge().setText("");
				locSc.setCurrLoc(null);
				locSc.getInsertLocTxt().setText("");
				locSc.getInsertLocResult().setText("");
				locSc.getUpdatedLocTxt().setText("");
				locSc.getNewLocTxt().setText("");
				locSc.getUpdateLocResult().setText("");
				locSc.getDeleteLocTxt().setText("");
				locSc.getDeleteLocResult().setText("");
				locSc.getYoungestMartyr().setText("");
				locSc.getOldestMartyr().setText("");
				locSc.getInsertMartyrTxt().setText("");
				locSc.getInsertMartyrResult().setText("");
				locSc.getUpdatedMartyrTxt().setText("");
				locSc.getNewMartyrTxt().setText("");
				locSc.getUpdateMartyrResult().setText("");
				locSc.getDeleteMartyrNameTxt().setText("");
				locSc.getDeleteMartyrResult().setText("");
				locSc.getPartOfMartyrNameTxt().setText("");
				locSc.getSearchedMartyrLbl().setText("");
				primaryStage.setTitle("District Screen");
				primaryStage.setScene(DisScene);
			});
			locSc.getUpdateLocBtn().setOnAction(e -> { //update location action
				locSc.updateLocationAction(); //call the method of update location
			});
			locSc.getDeleteLocBtn().setOnAction(e -> { //delete location action
				locSc.deleteLocationAction(); //call the method to delete location
			});
			locSc.getNext().setOnAction(e -> {  //navigate next location action
				if (locSc.getCurrLoc() != null) {  
					//if the current not null get its next and call method to display its statistics
					locSc.setCurrLoc(locSc.getCurrLoc().getNext());
					locSc.nextLocAction(locSc.getCurrLoc());

				} 
				//if we arrive the end of list , go back to the first location
				else if (locSc.getCurrLoc() == null&& locSc.getDistrict().getData().getLocations().getHead().getNext() != null) {
					locSc.setCurrLoc(locSc.getDistrict().getData().getLocations().getHead().getNext());
					locSc.nextLocAction(locSc.getCurrLoc());
					
				} else {
					locSc.nextLocAction(null);
				}
			});
			locSc.getInsertMartyrBtn().setOnAction(e -> { //insert martyr action
				locSc.insertMartyrAction(); //call the method to insert martyr
//				if (locSc.getCurrLoc() != null)
//					System.out.println(locSc.getCurrLoc().getData().toString() + " "
//							+ locSc.getCurrLoc().getData().getMartyrs().toString());
			});
			locSc.getDeleteMartyrBtn().setOnAction(e -> { //delete martyr action
				locSc.deleteMartyrAction(); //call the method to delete martyr
			});
			locSc.getUpdateMartyrBtn().setOnAction(e -> { //update martyr action
				if (locSc.getCurrLoc() != null) //display martyrs before update
					System.out.println(locSc.getCurrLoc().getData().toString() + " "
							+ locSc.getCurrLoc().getData().getMartyrs().toString());
				locSc.updateMartyrAction();  //call method to update martyr
				if (locSc.getCurrLoc() != null)  //display martyr after update
					System.out.println(locSc.getCurrLoc().getData().toString() + " "
							+ locSc.getCurrLoc().getData().getMartyrs().toString());
			});
			locSc.getSearchAboutMartyrByPartOfName().setOnAction(e -> { //search about martyr using part of name action
				locSc.findMartyrAction(); //call the method to find the martyr
			});

		} catch (Exception e) {
			
		}
	}

	public static void print() {

	}

	public static void main(String[] args) {
		launch(args);
	}
}
