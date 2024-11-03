package application;

import java.util.Date;

public class Location implements Comparable<Location> {

	private String location; // name of location
	private LinkedList<Martyr> martyrs; // list of martyrs

	public Location(String location) { // constructor
		this.location = location;
		martyrs = new LinkedList<>();
	}

	@Override
	public int compareTo(Location o) { // CopmpareTo according to name of location
		return location.compareToIgnoreCase(o.getLocation());
	}

	public String toString() { // toString
		return location;
	}

	public int numOfMartyrs() { // calculate number of martyr in this location  //time O(n)
		return martyrs.length();
	}

	public int numOfMaleMartyrs() { // calculate number of male martyr in this location   //time O(n)
		int num = 0;
		Node<Martyr> curr = martyrs.getHead().getNext();
		while (curr != null) { // start with first martyr, if he is male , increment counter
			if (Character.toUpperCase(curr.getData().getGender()) == 'M')
				num++;
			curr = curr.getNext();
		}
		return num;
	}

	public int numOfFemaleMartyrs() { // calculate number of female martyr in this location   //time O(n)
		int num = 0;
		Node<Martyr> curr = martyrs.getHead().getNext();
		while (curr != null) { // start with first martyr, if she is female , increment counter
			if (Character.toUpperCase(curr.getData().getGender()) == 'F')
				num++;
			curr = curr.getNext();
		}
		return num;
	}

	public int numOfMartyrsInThisDate(Date date) { // calculate number of martyr in specific date  //time O(n)
		int num = 0;
		Node<Martyr> curr = martyrs.getHead().getNext(); // start with first martyr
		while (curr != null) {
			// if the date equals the dateOfDeath for this martyr , increment counter
			if (date.equals(curr.getData().getDateOfDeath()))
				num++;
			curr = curr.getNext();
		}
		return num;

	}

	public int averageAge() { // calculate average ages  //time O(n)
		if (martyrs.length() != 0) // divide sum of ages by number of martyrs
			return sumOfAges() / martyrs.length();
		else
			return 0;
	}

	public int sumOfAges() { // calculate sum of ages   //time O(n)
		int sum = 0;
		Node<Martyr> curr = martyrs.getHead().getNext(); // start with first martyr
		while (curr != null) {
			sum += curr.getData().getAge(); // add the martyr`s age to the sum
			curr = curr.getNext();
		}
		return sum;
	}

	public Martyr youngestMartyr() { // calculate youngest martyr in this location  //time O(n)
		Node<Martyr> curr = martyrs.getHead().getNext();
		int minAge = 0;
		Martyr youngestMartyr = null;
		if(curr != null) {
		 minAge = curr.getData().getAge();
		 youngestMartyr = curr.getData();
		}
		while (curr != null) {
			/*
			 * if the age of current martyr less than the minimum age ,let the age of this
			 * martyr the minimum age , and let it the youngest martyr
			 */
			if (curr.getData().getAge() < minAge) {
				minAge = curr.getData().getAge();
				youngestMartyr = curr.getData();
			}
			curr = curr.getNext();
		}
		return youngestMartyr;

	}

	public Martyr oldestMartyr() { // calculate oldest martyr in this location   //time O(n)
		Node<Martyr> curr = martyrs.getHead().getNext();
		int maxAge = 0;
		Martyr oldesttMartyr = null;
		if(curr != null) {
		 maxAge = curr.getData().getAge();
		 oldesttMartyr = curr.getData();
		}
		while (curr != null) {
			/*
			 * if the age of current martyr greater than the max age ,let the age of this
			 * martyr the max age , and let it the oldest martyr
			 */
			if (curr.getData().getAge() > maxAge) {
				maxAge = curr.getData().getAge();
				oldesttMartyr = curr.getData();
			}
			curr = curr.getNext();
		}
		return oldesttMartyr;
	}

	public Martyr findMartyrUsingName(String name) { // find martyr using its name  //time O(n)
		Node<Martyr> curr = martyrs.getHead().getNext();
		while (curr != null) {
			// if the martyr`s name equals the name , return the martyr
			if (curr.getData().getName().equals(name))
				return curr.getData();
			curr = curr.getNext();

		}
		return null;
	}

	public Martyr findMartyrUsingPartofName(String part) { // find the martyr using part of his/her name   //time O(n)
		Node<Martyr> curr = martyrs.getHead().getNext();
		while (curr != null) {
			// if the martyr`s name contains the part , return the martyr
			if (curr.getData().getName().contains(part))
				return curr.getData();
			curr = curr.getNext();

		}
		return null;
	}

	// getters and setters
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LinkedList<Martyr> getMartyrs() {
		return martyrs;
	}

	public void setMartyrs(LinkedList<Martyr> martyrs) {
		this.martyrs = martyrs;
	}

}
