package tourgen.model;


public class School{

    private int schoolID;
    private Location diffLoc;
    private String name;
    private Location SchoolLoc;
    private Location hostLoc;
    private int enroll;
    private boolean Bstatus;
    private boolean Gstatus;
    private Location hostAdd;
    private String  hostName;


 public School(String schoolName, String streetAddress, String cityName, int zipCode, int enrollmentNumber, boolean girlsPartcipationStatus, boolean boysParticipationStatus)
 {
    name = schoolName;
    schoolLoc school = new schoolLoc(streetAddress, cityName, zipCode);
    enroll = enrollmentNumber;
    Gstatus = girlsPartcipationStatus;
    Bstatus = boysPartcipationStatus;
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
     return schoolLocation.getStreetAddress();
 }

 public String getCityName(){
    return schoolLocation.getCityName();
 }

 public int getZipCode(){
     return schoolLocation.getZipCode();
 }

 public void setDiffLoc(Location loc)
 {
     diffLoc = loc;
 }
 public Location getSchoolLoc()
 {
     return SchoolLoc;
 }

 public void setSchoolLoc(Location loc)
 {
     SchoolLoc= loc;
 }


 public String getName()
 {
     return name;
 }
 public void setName(String names)
 {
     name = names;
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

 public boolean getB()
 {
     return Bstatus;
 }

 public void setBstatus(boolean boys)
 {
     Bstatus = boys;
 }

 public boolean getG()
 {
     return Gstatus;
 }

 public void setGstatus(boolean girls)
 {
     Gstatus = girls;
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
     if(object instanceof School){
       //compare school's name, address
        if(name.equals(School.getName()) && schoolLoc.equals(School.getSchoolLoc()))
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