package tourgen.model;


public class School{

    private int schoolID;
    private Location diffLoc;
    private String name;
    private Location schoolLoc;
    private Location hostLoc;
    private int enroll;
    private boolean bStatus;
    private boolean gStatus;
    private Location hostAdd;
    private String  hostName;
    private String displayName;


 public School(String displayNameArg, String schoolName, String streetAddress, String cityName, int zipCode, int enrollmentNumber, boolean gStatus, boolean bStatus)
 {
	displayName = displayNameArg;
    name = schoolName;
    schoolLoc = new Location(streetAddress, cityName, zipCode);
    enroll = enrollmentNumber;
    gStatus = gStatus;
    bStatus = bStatus;
 }
 
 public School ( SchoolFormMVCData info) {
	 name = info.getSchoolName();
	 schoolLoc = new Location(info.getStreetAddress(), info.getCityName(), info.getZipCode());
	 enroll = info.getEnrollmentNumber();
	 bStatus = info.getBoysParticipationStatus();
	 gStatus = info.getGirlsParticipationStatus();
	 displayName = info.getSchoolDisplayName();
 }

 public int getID()
 {
     return schoolID;
 }

 public void setID(int ID)
 {
     schoolID = ID;
 }
 public Location getDiffLoc()
 {
     return diffLoc;
 }

 public String getStreetAddress(){
     return schoolLoc.getStreetAddress();
 }

 public String getCityName(){
    return schoolLoc.getCityName();
 }

 public int getZipCode(){
     return schoolLoc.getZipCode();
 }

 public void setDiffLoc(Location loc)
 {
     diffLoc = loc;
 }
 public Location getSchoolLoc()
 {
     return schoolLoc;
 }

 public void setSchoolLoc(Location loc)
 {
     schoolLoc= loc;
 }

 public String getName()
 {
     return name;
 }
 
 public void setName(String name)
 {
     this.name = name;
 }

 public Location getHostLoc()
 {
     return hostLoc;
 }
 
 public void setHostLoc(Location loc)
 {
     hostLoc = loc;
 }
 
 public int getEnroll()
 {
     return enroll;
 }

 public void setEnroll(int enrollment)
 {
     enroll = enrollment;
 }

 public boolean getBStatus()
 {
     return bStatus;
 }

 public void setBStatus(boolean boys)
 {
     bStatus = boys;
 }

 public boolean getGStatus()
 {
     return gStatus;
 }

 public void setGStatus(boolean girls)
 {
     gStatus = girls;
 }

 public Location getHostAdd()
 {
     return hostAdd;
 }
 
 public void setHostAdd(Location loc)
 {
     hostAdd = loc;
 }

 public String getHostName()
 {
     return hostName;
 }
 
 public void setHostName(String host)
 {
     hostName = host;
 }


 public boolean equals(Object obj)
 {
	 if(obj instanceof School) {
	       //compare school's name, address
		 	School sch = (School) obj;
	        if(name.equals(sch.getStreetAddress())
	        		&& schoolLoc.equals(sch.getCityName()))
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

 public String toString() {
	 return name;
 }

public String getDisplayName() {
	return displayName;
}

}