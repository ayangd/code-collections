package ayangd.hrmanage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.Scanner;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

import ayangd.lib.ioutil.IntegerFieldInput;
import ayangd.lib.ioutil.Menu;
import ayangd.lib.ioutil.StringFieldInput;

public class Main {
	public static void main(String[] args) {
		// Tables
		EmployerTable teacherTable = new EmployerTable("Teacher");
		EmployerTable issTable = new EmployerTable("ISS");
		EmployerTable securityTable = new EmployerTable("Security");
		
		// Scanner
		Scanner sc = new Scanner(System.in);
		
		// Menus
		Menu mainMenu = new Menu()
				.setTitle("HR Manage Program")
				.addOption("Hire Employee")
				.addOption("View Employee")
				.addOption("Fire Employee")
				.addOption("Exit");
		
		Menu typeMenu = new Menu()
				.setTitle("Select Employer Type")
				.addOption("Teacher")
				.addOption("ISS")
				.addOption("Security");
		
		// Input Fields
		StringFieldInput nameInput = new StringFieldInput()
				.setPrompt("Employer name[length > 4, 2 words]")
				.setMinimumLength(5)
				.setMinimumWord(2)
				.setMaximumWord(2);
		
		IntegerFieldInput ageInput = new IntegerFieldInput()
				.setPrompt("Employer age[between 18 and 75]")
				.setMinimumValue(18)
				.setMaximumValue(75);
		
		IntegerFieldInput indexInput = new IntegerFieldInput()
				.setPrompt("Employer index")
				.setMinimumValue(1);
		
		try {
			FileInputStream fs = new FileInputStream("hrmanage.db");
			InflaterInputStream is = new InflaterInputStream(fs);
			ObjectInputStream os = new ObjectInputStream(is);
			teacherTable = (EmployerTable) os.readObject();
			issTable = (EmployerTable) os.readObject();
			securityTable = (EmployerTable) os.readObject();
			os.close();
		} catch (FileNotFoundException e) {
			// Don't complain. Just make a new one.
		} catch (StreamCorruptedException e) {
			System.err.println("Data is obviously corrupted.");
		} catch (IOException e) {
			System.err.println("Can't read data. Either corrupted or gone.");
		} catch (ClassNotFoundException e) {
			System.err.println("Can't identify data. Must be corrupted.");
		} catch (SecurityException e) {
			System.err.println("System doesn't allow us to read.");
		}
		
		while (true) {
			int choice = mainMenu.runMenu(sc);
			
			if (choice == 4)
				break;
			
			if (choice == 1) {
				int typeChoice = typeMenu.runMenu(sc);
				Employer e = new Employer(
						nameInput.runField(sc),
						ageInput.runField(sc));
				
				if (typeChoice == 1) {
					teacherTable.add(e);
				} else if (typeChoice == 2) {
					issTable.add(e);
				} else {
					securityTable.add(e);
				}
				
			} else if (choice == 2) {
				teacherTable.showTable();
				System.out.println();
				
				issTable.showTable();
				System.out.println();
				
				securityTable.showTable();
				System.out.println();
				
			} else if (choice == 3) {
				int typeChoice = typeMenu.runMenu(sc);
				int max = 0;
				if (typeChoice == 1) {
					teacherTable.showTable();
					max = teacherTable.size();
				} else if (typeChoice == 2) {
					issTable.showTable();
					max = issTable.size();
				} else {
					securityTable.showTable();
					max = securityTable.size();
				}
				System.out.println();
				
				if (max == 0) {
					System.out.println("No data available.");
				} else {
					int index = indexInput.setMaximumValue(max).runField(sc) - 1;
					if (typeChoice == 1) {
						teacherTable.remove(index);
					} else if (typeChoice == 2) {
						issTable.remove(index);
					} else {
						securityTable.remove(index);
					}
					System.out.println("Removed employee successfully.");
				}
			}
			
			// Update database
			try {
				FileOutputStream fs = new FileOutputStream("hrmanage.db");
				DeflaterOutputStream ds = new DeflaterOutputStream(fs);
				ObjectOutputStream os = new ObjectOutputStream(ds);
				os.writeObject(teacherTable);
				os.writeObject(issTable);
				os.writeObject(securityTable);
				os.close();
			} catch (IOException e) {
				System.err.println("System says it writing can't be done.");
			} catch (SecurityException e) {
				System.err.println("System doesn't allow us to write.");
			}
		}
	}
}
