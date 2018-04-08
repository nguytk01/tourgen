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
	private Repository repo;
	
    public SchoolManager(Repository repoArg) {
		repo = repoArg;
        schoolList = new java.util.ArrayList<School>();
        schoolHashMap = new HashMap<String, School>();
    }

    public void addSchool(SchoolFormMVCData info){
        School school = new School(info);
        IOperationResult result;
        int index = getSchoolIndexForAddition(info);
        if (index != -1) {
        	result =  new OperationResult(info.getTicket(), OperationResultEnum.FAILURE, "Duplicate information found in the database.", null);
        } else {
        	schoolList.add(school);
        	result = new OperationResult(info.getTicket(), OperationResultEnum.SUCCESS, "", school);
        }
        setChanged();
        notifyObservers(result);
    }
    
    int getSchoolIndex(SchoolFormMVCData info) {
    	for (int i = 0; i < schoolList.size(); i++) {
    		if (schoolList.get(i).getName().equals(info.getSchoolName()) 
    				&& schoolList.get(i).getName().equals(info.getSchoolName())
    				&& schoolList.get(i).getName().equals(info.getSchoolName())) {
    			return i;
    		}
    	}
    	return -1;
    }
    
    int getSchoolIndexForAddition(SchoolFormMVCData info) {
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

    public void removeSchool(SchoolFormMVCData info){
        School school = null;
        int index = getSchoolIndex(info);
        IOperationResult result;
        if (index != -1){
            /*boolean repoRemoveResult = repo.removeSchool(schoolList.get(index));
			if (result = false) {
				result= new OperationResult(info.getTicket(), OperationResultEnum.FAILURE, "Unable to remove the school. It has an important role in the tournament format", null);
				setChanged();
				notifyObservers(result);
				return;
			}*/
        	school = schoolList.get(index);
			schoolList.remove(index);
            result = new OperationResult(info.getTicket(), OperationResultEnum.SUCCESS, "", school);
			
        } else {
        result= new OperationResult(info.getTicket(), OperationResultEnum.FAILURE, "Remove failure", null);
        }
        setChanged();
        notifyObservers(result);
    }

    public void editSchool(SchoolFormMVCData info){
        
        int index = getSchoolIndex(info);
        School school = null;
        IOperationResult result;
        if (index != -1){
        	school = schoolList.get(index);
            schoolList.get(index).setEnroll(info.getEnrollmentNumber());
            schoolList.get(index).setBStatus(info.getBoysParticipationStatus());
            schoolList.get(index).setGStatus(info.getGirlsParticipationStatus());
            result = new OperationResult(info.getTicket(), OperationResultEnum.SUCCESS, "", school);
        }else { 
        	result= new OperationResult(info.getTicket(), OperationResultEnum.FAILURE, "Remove failure", null);
        }
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
    		System.out.println("cannot find school in schoolManager >" + displayName + "<");
    		return null;
    	} else return schoolHashMap.get(displayName.trim());
    	
    }
}
