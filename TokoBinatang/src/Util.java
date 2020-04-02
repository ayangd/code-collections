/*
 * Copyright (c) Michael "ayangd" Dlone, 2020.
 */

import java.util.Scanner;

public class Util {
	public static int inputAngka(Scanner sc) {
		while (true) {
			String input = sc.nextLine();
			if (input.matches("\\d+")) {
//				int angka = Integer.parseInt(input);
//				return angka;
				return Integer.parseInt(input);
			} else {
				System.out.println("Tolong input angka.");
			}
		}
	}
	
	public static int inputAngka(Scanner sc, int min, int max) {
		while (true) {
			int angka = inputAngka(sc);
			if (angka >= min && angka <= max)
				return angka;
			System.out.printf("Tolong input angka dari %d sampai %d.\n", min, max);
		}
	}
	
	public static int inputAngka(Scanner sc, int min, int max, String text) {
		while (true) {
			System.out.printf("%s[%d-%d]: ", text, min, max);
			String input = sc.nextLine();
			if (input.matches("\\d+")) {
				int angka = Integer.parseInt(input);
				if (angka >= min && angka <= max)
					return angka;
				System.out.printf("Tolong input angka dari %d sampai %d.\n", min, max);
			} else {
				System.out.println("Tolong input angka.");
			}
		}
	}
}
