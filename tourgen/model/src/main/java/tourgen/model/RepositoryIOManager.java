package tourgen.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class RepositoryIOManager extends java.util.Observable implements Serializable {
	
	public RepositoryIOManager() {
		
	}
	
	/**
	 * Load the repository from a persistent storage.
	 */
	public static Repository loadRepository(String fileName)
	{	
		ObjectInputStream inputFile;
		try
		{
			File input = new File(fileName);
			inputFile = new ObjectInputStream (new FileInputStream (input));
				try
				{
					Repository newRepository = (Repository) inputFile.readObject();
					inputFile.close();
					return newRepository;
				}
				catch (ClassNotFoundException e)
				{
					e.printStackTrace();
				}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Save the current repository to persistent storage.
	 */
	public static void saveRepository(String fileName, Repository repository)
	{	
		try
		{
			File output = new File(fileName);
			ObjectOutputStream outputFile = new ObjectOutputStream (new FileOutputStream(output));
			try
			{
				outputFile.writeObject(Repository.getInstance());
				outputFile.close();
			}
			catch(IOException i)
			{
				i.printStackTrace();
			}
			outputFile.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static SchoolManager loadSchoolManager(String fileName)
	{	
		ObjectInputStream inputFile;
		try
		{
			File input = new File(fileName);
			inputFile = new ObjectInputStream (new FileInputStream (input));
				try
				{
					SchoolManager newManager = (SchoolManager) inputFile.readObject();
					inputFile.close();
					return newManager;
				}
				catch (ClassNotFoundException e)
				{
					e.printStackTrace();
				}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static void saveSchoolManager(String fileName, SchoolManager schoolmanager)
	{	
		try
		{
			File output = new File(fileName);
			ObjectOutputStream outputFile = new ObjectOutputStream (new FileOutputStream(output));
			try
			{
				outputFile.writeObject(schoolmanager);
				outputFile.close();
			}
			catch(IOException i)
			{
				i.printStackTrace();
			}
			outputFile.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static SchoolManager loadEverythingUp(){
		SchoolManager manager = loadSchoolManager("schoolManager.bin");
		if (manager != null){
			Repository.getInstance().setBoyList(manager.getRepository().getBoyList());
			Repository.getInstance().setGirlList(manager.getRepository().getGirlList());
		}
		return manager;
	}
}
