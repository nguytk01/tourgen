package tourgen.model;

class Location {
	private String streetAddress;
	private String cityName;
	private int zipCode;
	private double latitude;
	private double longitude;
	private String name;

	Location (String streetAddressArg, String cityNameArg, int zipCodeArg){
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
	
	 public boolean equals(Object obj)
	 {
     if(obj instanceof Location) {
       
    	Location loc = (Location) obj;
        if(streetAddress.equals(loc.getStreetAddress())
        		&& cityName.equals(loc.getCityName()) 
        		&& (zipCode == loc.getZipCode()))
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
	public String getName() {
		return name;
	}
	
	
}
