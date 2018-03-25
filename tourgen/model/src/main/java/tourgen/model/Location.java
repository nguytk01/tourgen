package tourgen.model;

class Location {
	private String streetAddress;
	private String cityName;
	private int zipCode;
	
	Location (String streetAddressArg, String cityNameArg, int zipCodeArg){
		streetAddress = streetAddressArg;
		cityName = cityNameArg;
		zipCode = zipCodeArg;
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
	
	
}
