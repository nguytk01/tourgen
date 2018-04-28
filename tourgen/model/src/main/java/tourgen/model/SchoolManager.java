package tourgen.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class SchoolManager extends java.util.Observable implements Serializable {
  /**
   * Default serialization ID.
   */
  private static final long serialVersionUID = 1L;
  private java.util.ArrayList<School> schoolList;
  private HashMap<String, School> schoolHashMap;
  private Repository repo;
  
  /**
   * constructs a SchoolManagerobject.
   */
  public SchoolManager() {
    repo = Repository.getInstance1();
    schoolList = new java.util.ArrayList<School>();
    schoolHashMap = new HashMap<String, School>();
  }

  /**
   * add school from the school form values.
   * @param info is the school form data object
   */
  public void addSchool(SchoolFormMvcData info) {
    School school = new School(info);
    IOperationResult result;
    int index = getSchoolIndexForAddition(info);
    if (index != -1) {
      result = new OperationResult(info.getTicket(), OperationResultEnum.FAILURE,
          "Duplicate information found in the database.", null);
    } else {
      schoolList.add(school);
      result = new OperationResult(info.getTicket(), OperationResultEnum.SUCCESS, "", school);
    }
    setChanged();
    notifyObservers(result);
  }

  int getSchoolIndex(SchoolFormMvcData info) {
    for (int i = 0; i < schoolList.size(); i++) {
      if (schoolList.get(i).getName().equals(info.getSchoolName())
          && schoolList.get(i).getName().equals(info.getSchoolName())
          && schoolList.get(i).getName().equals(info.getSchoolName())) {
        return i;
      }
    }
    return -1;
  }

  int getSchoolIndexForAddition(SchoolFormMvcData info) {
    for (int i = 0; i < schoolList.size(); i++) {
      if (schoolList.get(i).getName().equals(info.getSchoolName())
          && schoolList.get(i).getStreetAddress().equals(info.getStreetAddress())
          && schoolList.get(i).getZipCode() == info.getZipCode()
          && schoolList.get(i).getCityName().equals(info.getCityName())) {
        return i;
      }
    }
    return -1;
  }
  
  /**
   * remove school selected on the form.
   * @param info is the school form data object
   */
  public void removeSchool(SchoolFormMvcData info) {
    School school = null;
    int index = getSchoolIndex(info);
    IOperationResult result;
    if (index != -1) {
      /*
       * boolean repoRemoveResult = repo.removeSchool(schoolList.get(index)); if
       * (result = false) { result= new OperationResult(info.getTicket(),
       * OperationResultEnum.FAILURE,
       * "Unable to remove the school. It has an important role in the tournament format"
       * , null); setChanged(); notifyObservers(result); return; }
       */
      school = schoolList.get(index);
      schoolList.remove(index);
      result = new OperationResult(info.getTicket(), OperationResultEnum.SUCCESS, "", school);

    } else {
      result = new OperationResult(info.getTicket(), OperationResultEnum.FAILURE,
          "Remove failure", null);
    }
    setChanged();
    notifyObservers(result);
  }
  
  /**
   * edits school from the school form values.
   * @param info is the school form data object
   */
  public void editSchool(SchoolFormMvcData info) {

    int index = getSchoolIndex(info);
    School school = null;
    IOperationResult result;
    if (index != -1) {
      school = schoolList.get(index);
      schoolList.get(index).setEnroll(info.getEnrollmentNumber());
      schoolList.get(index).setBStatus(info.getBoysParticipationStatus());
      schoolList.get(index).setGStatus(info.getGirlsParticipationStatus());
      result = new OperationResult(info.getTicket(), OperationResultEnum.SUCCESS, "", school);
    } else {
      result = new OperationResult(info.getTicket(), OperationResultEnum.FAILURE,
          "Remove failure", null);
    }
    setChanged();
    notifyObservers(result);
  }

  public List<School> getSchoolList() {
    return Collections.unmodifiableList(schoolList);
  }

  /**
   * edits school database from a text file.
   */
  public void initSchools() {
    InputStream stream = this.getClass().getClassLoader()
            .getResourceAsStream("schoolDataAlphabetData.txt");
    InputStream coordinatesStream = this.getClass().getClassLoader()
            .getResourceAsStream("schoolCoordinatesData.csv");

    Scanner scanner = new Scanner(stream);
    scanner.useDelimiter("\\||\\r?\\n|\\r");

    Scanner coordinatesFileScanner = new Scanner(coordinatesStream);

    School school;
    while (scanner.hasNext()) {
      int enrollmentNumber = Integer.parseInt(scanner.next());
      String displayName = scanner.next();
      String schoolName = scanner.next();
      String streetAddress = scanner.next();
      String cityName = scanner.next();
      int zipCode = Integer.parseInt(scanner.next());

      // scanner.next();
      school = new School(displayName, schoolName, streetAddress, cityName,
                          zipCode, enrollmentNumber, true, false);
      school.setEligibleToHost(true);
      double[] coor = getCoordinatesFromStream(coordinatesFileScanner);
      school.getSchoolLoc().setLatitude(coor[0]);
      school.getSchoolLoc().setLongitude(coor[1]);
      schoolHashMap.put(displayName, school);
      schoolList.add(school);
    }
    scanner.close();
    try {
      stream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  double[] getCoordinatesFromStream(Scanner streamLineScanner) {
    String line = null;
    String[] lineComponents;
    double[] result = null;
    if (streamLineScanner.hasNextLine()) {
      line = streamLineScanner.nextLine();
      // System.out.println("line is " + line);
      lineComponents = line.split("\\|");
      // System.out.println("line length: " + lineComponents.length);
      result = new double[] { Double.parseDouble(lineComponents[4]),
          Double.parseDouble(lineComponents[5]) };
      return result;
    } else {
      return null;
    }
  }

  School getSchoolFromDisplayName(String displayName) {
    if (schoolHashMap.containsKey(displayName.trim()) == false) {
      System.out.println("cannot find school in schoolManager >" + displayName + "<");
      return null;
    } else {
      return schoolHashMap.get(displayName.trim());
    }
  }

  public Repository getRepository() {
    return repo;
  }

  List<School> getSnapshotOfCurrentListOfSchoolsNotWillingToHost() {
    List<School> listOfSchoolsNotWillingToHost = new java.util.ArrayList<School>();
    for (School school : schoolList) {
      if (!school.isEligibleToHost()) {
        listOfSchoolsNotWillingToHost.add(school);
      }
    }
    return listOfSchoolsNotWillingToHost;
    
  }
  
  public void toggleSchoolHostEligibility(School school){
	  school.setEligibleToHost(!school.isEligibleToHost());
  }
  
}
