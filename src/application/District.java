package application;

import java.util.Date;

public class District implements Comparable<District> {

	private String district; // name of district
	private LinkedList<Location> locations; // list of locations

	public District(String district) { // constructor

		this.district = district;
		locations = new LinkedList<>();
	}
 
	// getters and setters
	
	public String getDistrict() { 
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public LinkedList<Location> getLocations() {
		return locations;
	}

	public void setLocations(LinkedList<Location> location) {
		this.locations = location;
	}

	@Override
	public int compareTo(District o) { // compare to according to the name of district

		return district.compareToIgnoreCase(o.getDistrict());
	}

	public String toString() {
		return district;
	}

	public int numOfLocations() { // calculate number of its locations  //time O(n)
		return locations.length();
	}

	public int numOfMartyrs() { // calculate number of martyrs    //time O(n^2)
		int num = 0;
		Node<Location> curr = locations.getHead().getNext();
		while (curr != null) { // calculate number of martyr for each location
			num += curr.getData().numOfMartyrs();
			curr = curr.getNext();
		}

		return num;
	}

	public int numOfMaleMartyrs() { // calculate number of male martyrs   //time O(n^2)
		int num = 0;
		Node<Location> curr = locations.getHead().getNext();
		while (curr != null) { // calculate number of male martyr for each location
			num += curr.getData().numOfMaleMartyrs();
			curr = curr.getNext();
		}

		return num;
	}

	public int numOfFemaleMartyrs() { // calculate number of female martyrs     //time O(n^2)
		int num = 0;
		Node<Location> curr = locations.getHead().getNext();
		while (curr != null) { // calculate number of female martyr for each location
			num += curr.getData().numOfFemaleMartyrs();
			curr = curr.getNext();
		}

		return num;
	}

	public int numOfMartyrsInThisDate(Date date) { // calculate number of martyr in specific date    //time O(n^2)
		int num = 0;
		Node<Location> curr = locations.getHead().getNext();
		while (curr != null) { // calculate number of martyr in this day in each location
			num += curr.getData().numOfMartyrsInThisDate(date);
			curr = curr.getNext();
		}

		return num;
	}

	public int averageAge() { // calculate average of ages      //time O(n^2)
		int sumOfAges = 0;
		Node<Location> curr = locations.getHead().getNext();
		while (curr != null) { // calculate sum of ages in this district
			sumOfAges += curr.getData().sumOfAges();
			curr = curr.getNext();
		}

		if (numOfMartyrs() != 0) // calculate the average , divide the sum of ages by the number of martyr in
									// this district
			return sumOfAges / numOfMartyrs();
		else
			return 0;
	}

	public Date dateHasMaxNumOfMartyrs() { // calculate the date that has the maximum number of martyrs     //time O(n^3)

		LinkedList<Date> listDates = new LinkedList<>(); // make a list from all dates in this district
		Node<Location> currLoc = locations.getHead().getNext(); // start from first location and its martyr
		while (currLoc != null) {
			Node<Martyr> currMar = currLoc.getData().getMartyrs().getHead().getNext();
			while (currMar != null) { // check the martyr if its date not in list , add it to list
				if (listDates.find(currMar.getData().getDateOfDeath()) == null)
					listDates.insert(currMar.getData().getDateOfDeath());
				currMar = currMar.getNext();

			}
			currLoc = currLoc.getNext();
		}
		// we start from first date and looking for the date that has maximum number
		// martyrs
		Node<Date> currDate = listDates.getHead().getNext();
		int maxNum = 0;
		Date maxDate = null;
		if (currDate != null) {
			maxNum = numOfMartyrsInThisDate(currDate.getData());
			maxDate = currDate.getData();
			while (currDate != null) {
				/*
				 * if the current date has more martyrs than the maximum date , let the maximum
				 * date equals the current date and maximum number of martyrs equals its number
				 * of martyr
				 */
				if (numOfMartyrsInThisDate(currDate.getData()) > numOfMartyrsInThisDate(maxDate)) {
					maxNum = numOfMartyrsInThisDate(currDate.getData());
					maxDate = currDate.getData();
				}
				currDate = currDate.getNext();
			}
		}
		return maxDate; // return the max date
	}

}
