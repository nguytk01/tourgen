package tourgen.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class SchoolManager extends java.util.Observable{
    private java.util.ArrayList<School> schoolList;
    private HashMap<String, School> schoolHashMap;

    public SchoolManager() {
        schoolList = new java.util.ArrayList<School>();
        schoolHashMap = new HashMap<String, School>();
    }

    public void addSchool(SchoolFormMVCData info){
        School school = new School(info);
        schoolList.add(school);
        IOperationResult result = new OperationResult(info.getTicket(), OperationResultEnum.SUCCESS, "", school);
        setChanged();
        notifyObservers(result);
    }

    public void removeSchool(SchoolFormMVCData info){
        School school = new School(info);
        int index = schoolList.indexOf(school);
        IOperationResult result;
        if (index != -1){
            schoolList.remove(index);
            result = new OperationResult(info.getTicket(), OperationResultEnum.SUCCESS, "", school);
        }
        result= new OperationResult(info.getTicket(), OperationResultEnum.FAILURE, "Remove failure", null);
        setChanged();
        notifyObservers(result);
    }

    public void editSchool(SchoolFormMVCData info){
        School school = new School(info);
        int index = schoolList.indexOf(school);

        IOperationResult result;
        if (index != -1){
            schoolList.get(index).setEnroll(info.getEnrollmentNumber());
            schoolList.get(index).setBStatus(info.getBoysParticipationStatus());
            schoolList.get(index).setGStatus(info.getGirlsParticipationStatus());
            result = new OperationResult(info.getTicket(), OperationResultEnum.SUCCESS, "", school);
        }
        result= new OperationResult(info.getTicket(), OperationResultEnum.FAILURE, "Remove failure", null);
        setChanged();
        notifyObservers(result);
    }
    
    public List<School> getSchoolList(){
    	return Collections.unmodifiableList(schoolList);
    }
    
    public void initSchools() {
    	InputStream stream = this.getClass().getClassLoader().getResourceAsStream("schoolDataAlphabetData.txt");
    	Scanner scanner = new Scanner(stream);
    	scanner.useDelimiter("\\||\\r?\\n|\\r");
    	School school;
    	while (scanner.hasNext()) {
    		int enrollmentNumber = Integer.parseInt(scanner.next());
    		String displayName = scanner.next();
    		String schoolName = scanner.next();
    		String streetAddress = scanner.next();
    		String cityName = scanner.next();
    		int zipCode = Integer.parseInt(scanner.next());
    		//scanner.next();
    		
    		school = new School(displayName, schoolName, streetAddress, cityName, zipCode, enrollmentNumber, true, false);
    		schoolHashMap.put(displayName, school);
    		schoolList.add(school);
    	}
    	
    	try {
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    School getSchoolFromDisplayName(String displayName) {
    	if (schoolHashMap.containsKey(displayName.trim()) == false) {
    		System.out.println(">" + displayName + "<");
    		return null;
    	} else return schoolHashMap.get(displayName.trim());
    	
    }
}