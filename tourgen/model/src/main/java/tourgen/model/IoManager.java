package tourgen.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class IoManager extends java.util.Observable implements Serializable {

  public IoManager() {

  }

  /**
   * Load the repository from a persistent storage.
   */
  public static Repository loadRepository(String fileName) {
    ObjectInputStream inputFile;
    try {
      File input = new File(fileName);
      inputFile = new ObjectInputStream(new FileInputStream(input));
      try {
        java.util.List<Object> newGirlList = (java.util.List<Object>) inputFile.readObject();
        inputFile.close();
        Repository.getInstance1().setGirlList(newGirlList);
        //return newGirlList;
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    } catch (FileNotFoundException e) {
      /* file not found exception will be skipped */
      /* data will be initialized from text file */
      // e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Save the current repository to persistent storage.
   */
  public static void saveRepository(String fileName) {
    try {
      File output = new File(fileName);
      ObjectOutputStream outputFile = new ObjectOutputStream(new FileOutputStream(output));
      try {
        outputFile.writeObject(Repository.getInstance1().getGirlList());
        outputFile.close();
        return;
      } catch (IOException i) {
        i.printStackTrace();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Load a school manager instance up from a file name.
   * @param fileName the file name of the school manager's persistent data store.
   * @return an instance of SchoolManager
   */
  public static SchoolManager loadSchoolManager(String fileName) {
    ObjectInputStream inputFile;
    try {
      File input = new File(fileName);
      inputFile = new ObjectInputStream(new FileInputStream(input));
      try {
        SchoolManager newManager = (SchoolManager) inputFile.readObject();
        inputFile.close();
        return newManager;
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    } catch (FileNotFoundException e) {
      /* file not found exception will be skipped */
      /* data will be initialized from text file */
      // e.printStackTrace();
      System.out.println("cannot find file name " + fileName);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  /**
   * this method saves the school manager passed to it in a particular file.
   * @param fileName is the name of the file to write to
   * @param schoolmanager is the instance of SchoolManager to be saved
   */
  public static void saveSchoolManager(String fileName, SchoolManager schoolmanager) {
    try {
      File output = new File(fileName);
      ObjectOutputStream outputFile = new ObjectOutputStream(new FileOutputStream(output));
      try {
        outputFile.writeObject(schoolmanager);
        outputFile.close();
      } catch (IOException i) {
        i.printStackTrace();
      }
      outputFile.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Save distance matrix to a file.
   * @param fileName a file name
   */
  
  public static void saveDistanceMatrix(String fileName) {
    try {
      File output = new File(fileName);
      //System.out.println("saveDistanceMatrix ?");
      ObjectOutputStream outputFile = new ObjectOutputStream(new FileOutputStream(output));
      //System.out.println("saveDistanceMatrix ?");
      try {
        outputFile.writeObject(TourgenDistanceMatrix.getInstance());
        System.out.println("saveDistanceMatrix ?");
        outputFile.close();
      } catch (IOException i) {
        i.printStackTrace();
      }
      outputFile.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Load distance matrix from a file.
   * @param fileName the name of the file.
   */
  public static Object loadDistanceMatrix(String fileName) {
    ObjectInputStream inputFile;
    
    try {
      File input = new File(fileName);
      inputFile = new ObjectInputStream(new FileInputStream(input));
      //System.out.println("succes ?");
      try {
        Object hashMap = inputFile.readObject();
        //System.out.println("succes ?");
        //System.out.println(hashMap);
        TourgenDistanceMatrix.setInstance(hashMap);
        inputFile.close();
        return hashMap;
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    } catch (FileNotFoundException e) {
      /* file not found exception will be skipped */
      /* data will be initialized from text file */
      // e.printStackTrace();
      System.out.println("cannot find file name " + fileName);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  /**
   * This is a convenient method to load everything up from files stored on disk.
   * @return a schoolManager
   */
  public static SchoolManager loadEverythingUp() {
    SchoolManager manager = loadSchoolManager("schoolManager.bin");
    loadRepository("tournaments.bin");
    loadDistanceMatrix("distanceMatrix.bin");
    
    //Repository.getInstance().setGirlList(repo.getGirlList());
    if (manager != null) {
      System.out.println("manager is not null");
      System.out.println("manager.repository " + manager.getRepository());
      System.out.println(manager.getRepository().getGirlList().size());
      //System.out.println(repo);
      //System.out.println(repo.getGirlList().size());
      //System.exit(0);
      //Repository.getInstance().setBoyList(manager.getRepository().getBoyList());
      //Repository.getInstance().setGirlList(manager.getRepository().getGirlList());
    }
    return manager;
  }
 

  /**
   * Save the distance matrix, the repository and the school manager's data to disk.
   * @param manager the school manager object
   */
  public static void saveEverything(SchoolManager manager) {
    /* This will also save repository since schoolManager has a repository*/
    Repository.getInstance1().removeAllPropertyChangeListenersForSerialization();
    saveSchoolManager("schoolManager.bin", manager);
    saveRepository("tournaments.bin");
    System.out.println("saveDistanceMatrix ?");
    saveDistanceMatrix("distanceMatrix.bin");
    
    //saveRepository("repository.bin",Repository.getInstance());
  }
}
