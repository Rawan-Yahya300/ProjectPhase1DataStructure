package application;

import java.util.Date;

public class Martyr implements Comparable<Martyr> {
        //declare the martyr`s attributes
	 private String name;
	 private Date dateOfDeath;
	 private int age;
	 private Location location;
	 private District district;
	 private char gender;
	 
	public Martyr(String name, Date dateOfDeath, int age, Location location, District district, char gender) {
		//define the martyr`s attributes
		this.name = name;
		this.dateOfDeath = dateOfDeath;
		this.age = age;
		this.location = location;
		this.district = district;
		this.gender = gender;
	}
	//getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDateOfDeath() {
		return dateOfDeath;
	}
	public void setDateOfDeath(Date dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	@Override
	public int compareTo(Martyr o) { //compareTo according the age
		if(age > o.getAge())
			return 1;
		else if(age > o.getAge())
			return -1;
					
		return 0;
	}
	@Override
	public String toString() { //toString for martyr information
		return  name + ","
	+ (dateOfDeath.getMonth()+1)+"/"+(dateOfDeath.getDate())+"/"+(dateOfDeath.getYear()+1900) + ","  + age + "," + location
				+ "," + district + "," + gender;
	}
	
	
	
 }
