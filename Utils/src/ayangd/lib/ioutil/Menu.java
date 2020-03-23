package ayangd.lib.ioutil;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Menu {
	private String title;
	private ArrayList<String> options;
	
	public Menu() {
		title = null;
		options = new ArrayList<>();
	}
	
	public Menu setTitle(String title) {
		this.title = title;
		return this;
	}
	
	public Menu addOption(String option) {
		options.add(option);
		return this;
	}
	
	public Menu addOptions(String[] options) {
		for (String o: options) {
			this.options.add(o);
		}
		return this;
	}
	
	public Menu addOptions(AbstractList<String> options) {
		this.options.addAll(options);
		return this;
	}
	
	public int runMenu(Scanner sc) throws IllegalStateException {
		// Print title
		if (title != null) {
			System.out.println(title);
			for (int i = 0; i < title.length(); i++) {
				System.out.print("=");
			}
			System.out.println();
		}
		
		// Print options
		int option = 1;
		for (String s: options) {
			System.out.printf("%d. %s\n", option++, s);
		}
		
		// Check for options
		while (true) {
			try {
				System.out.print("> ");
				String l = sc.nextLine();
				if (l.length() == 0) continue;
				if (l.matches("\\d")) {
					// Input is numeric
					int choice = Integer.parseInt(l);
					if (choice > 0 && choice <= options.size())
						return choice;
				} else {
					// Input is string
					int choiceCheck = 1;
					int choiceChoosed = 0;
					boolean passOnce = false;
					boolean doReturn = true;
					
					// Check one by one
					for (String s: options) {
						// Skip if input is longer than the option
						if (s.length() >= l.length()) {
							// Check if input equals start of the option, case-insensitive
							if (l.equalsIgnoreCase(s.substring(0, l.length()))) {
								// Check for duplicate passes
								if (passOnce) {
									doReturn = false;
									System.out.println("Please type more.");
									break;
								}
								passOnce = true;
								choiceChoosed = choiceCheck;
							}
						}
						choiceCheck++;
					}
					if (doReturn && passOnce)
						return choiceChoosed;
					
					System.out.println("Please choose the option.");
				}
			} catch (NoSuchElementException e) {
				throw new IllegalStateException("Stream exhausted.");
			} catch (IllegalStateException e) {
				throw new IllegalStateException("Stream closed.");
			}
		}
	}
}
