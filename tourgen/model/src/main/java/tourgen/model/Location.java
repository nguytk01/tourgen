package tourgen.model;

class Location {
	private String streetAddress;
	private String cityName;
	private int zipCode;
	private double latitude;
	private double longitude;

	Location (String streetAddressArg, String cityNameArg, int zipCodeArg){
		streetAddress = streetAddressArg;
		cityName = cityNameArg;
		zipCode = zipCodeArg;
		//getLatitude
		//getLongitude
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
	
	 public boolean equals(Object obj)
 {
     if(object instanceof Location){
       //compare school's name, address
        if(Location.equals(getStreetAddress()) && Location.equals(getCityname()) && Location.equals(getZipCode()))
        {
            return true;
        }
        else
        {
            return false;
        }
    } else {
        return false;
    }

 }
	
	
}
