package tourgen.model;

import java.io.Serializable;

public class Location implements Serializable{
	private String streetAddress;
	private String cityName;
	private int zipCode;
	private double latitude;
	private double longitude;
	private String name;
	private String fullName;
	private String latLonString;

	public Location (String streetAddressArg, String cityNameArg, int zipCodeArg){
		streetAddress = streetAddressArg;
		cityName = cityNameArg;
		zipCode = zipCodeArg;
	}
	Location(String nameArg){
		name = nameArg;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public String getCityName() {
		return cityName;
	}

	public int getZipCode() {
		return zipCode;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getCoordinateString(){
		if (this.longitude != 0 && this.latitude != 0){
			if (this.latLonString == null){
				this.latLonString = new Double(this.latitude).toString() + "," + new Double(this.longitude).toString();
			}
			return this.latLonString;
		} else return null;
	}

	public boolean equals(Object obj) {
		if(obj instanceof Location) {
		Location loc = (Location) obj;
			if(streetAddress.equals(loc.getStreetAddress())
        			&& cityName.equals(loc.getCityName()) 
	        		&& (zipCode == loc.getZipCode())) {
				return true;
			} else {
				return false;
			}
	    	} else {
			return false;
		}
	}

	public String getName() {
		return name;
	}

	void setName(String nameArg){
		name = nameArg;		
	}
	
	public int hashCode(){
		return name.hashCode();
	}

	public void setFullName(String fullNameArg){
		fullName = fullNameArg;
	}

	public String getFullStreetAddress(){
		if (fullName != null && fullName.length() > 0)
			return fullName + ", " + streetAddress + ", " + cityName + ", " + (new Integer(zipCode)).toString();
		else return null;
	}
}
