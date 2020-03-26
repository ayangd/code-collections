package ayangd.lib.ioutil;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class StringFieldInput {
	private int minimumLength, maximumLength;
	private int minimumWord, maximumWord;
	private boolean checkWordCount;
	private String prompt;
	
	public StringFieldInput() {
		minimumLength = 0;
		maximumLength = Integer.MAX_VALUE;
		minimumWord = 0;
		maximumWord = Integer.MAX_VALUE;
		checkWordCount = false;
		prompt = null;
	}
	
	public StringFieldInput setPrompt(String prompt) {
		this.prompt = prompt;
		return this;
	}
	
	public StringFieldInput setMinimumLength(int min) {
		minimumLength = min;
		return this;
	}
	
	public StringFieldInput setMaximumLength(int max) {
		maximumLength = max;
		return this;
	}
	
	public StringFieldInput setMinimumWord(int min) {
		minimumWord = min;
		checkWordCount = true;
		return this;
	}
	
	public StringFieldInput setMaximumWord(int max) {
		maximumWord = max;
		checkWordCount = true;
		return this;
	}
	
	public String runField(Scanner sc) throws IllegalStateException {
		while (true) {
			String input = "";
			try {
				if (prompt != null)
					System.out.printf("%s: ", prompt);
				
				input = sc.nextLine();
				
				// Check for criteria
				if (checkWordCount && input.length() >= minimumLength && input.length() <= maximumLength) {
					int wordCount = input.split(" ").length;
					if (wordCount >= minimumWord && wordCount <= maximumWord)
						return input;
				}
				
				if (input.length() < minimumLength || input.length() > maximumLength) {
					if (minimumLength == 0)
						System.out.printf("Please input with maximum length of %d character%s.\n", maximumLength, maximumLength > 1 ? "s" : "");
					else if (maximumLength == Integer.MAX_VALUE)
						System.out.printf("Please input with minimum length of %d character%s.\n", minimumLength, minimumLength > 1 ? "s" : "");
					else
						System.out.printf("Please input with length between %d and %d.\n", minimumLength, maximumLength);
					// Don't print further
					continue;
				}
				
				if (checkWordCount) {
					if (minimumWord == maximumWord && minimumWord != 0)
						System.out.printf("Please input with exactly %d word%s.\n", minimumWord, minimumWord > 1 ? "s" : "");
					else if (minimumWord == 0)
						System.out.printf("Please input with maximum %d word%s.\n", maximumWord, maximumWord > 1 ? "s" : "");
					else if (maximumWord == 0)
						System.out.printf("Please input with minimum %d word%s.\n", minimumWord, maximumWord > 1 ? "s" : "");
					else
						System.out.printf("Please input with word count between %d and %d.\n", minimumLength, maximumLength);
				}
			} catch (NoSuchElementException e) {
				throw new IllegalStateException("Stream exhausted.");
			} catch (IllegalStateException e) {
				throw new IllegalStateException("Stream closed.");
			}
		}
	}
}
