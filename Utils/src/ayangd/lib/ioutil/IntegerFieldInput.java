/*
 * Copyright (c) Michael "ayangd" Dlone, 2020.
 */

package ayangd.lib.ioutil;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class IntegerFieldInput {
	private int minimumValue, maximumValue;
	private String prompt;
	
	public IntegerFieldInput() {
		minimumValue = Integer.MIN_VALUE;
		maximumValue = Integer.MAX_VALUE;
		prompt = null;
	}
	
	public IntegerFieldInput setMinimumValue(int min) {
		minimumValue = min;
		return this;
	}
	
	public IntegerFieldInput setMaximumValue(int max) {
		maximumValue = max;
		return this;
	}
	
	public IntegerFieldInput setPrompt(String prompt) {
		this.prompt = prompt;
		return this;
	}
	
	public int runField(Scanner sc) {
		while (true) {
			int input = 0;
			try {
				if (prompt != null)
					System.out.printf("%s: ", prompt);
				
				String strInput = sc.nextLine();
				if (!strInput.matches("\\d+")) {
					System.out.println("Please input a number.");
					continue;
				}
				input = Integer.parseInt(strInput);
				
				// Check for input criteria
				if (input >= minimumValue && input <= maximumValue)
					return input;
				
				// Output the appropriate value boundary.
				if (minimumValue == Integer.MIN_VALUE)
					System.out.printf("Please input value below %d.\n", maximumValue);
				else if (maximumValue == Integer.MAX_VALUE)
					System.out.printf("Please input value above %d.\n", minimumValue);
				else
					System.out.printf("Please input value between %d and %d\n", minimumValue, maximumValue);
				
			} catch (NumberFormatException e) {
				System.out.println("Please input a number.");
			} catch (NoSuchElementException e) {
				throw new IllegalStateException("Stream exhausted.");
			} catch (IllegalStateException e) {
				throw new IllegalStateException("Stream closed.");
			}
		}
	}
}
