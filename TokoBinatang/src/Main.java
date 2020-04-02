/*
 * Copyright (c) Michael "ayangd" Dlone, 2020.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		ArrayList<Binatang> listBinatang = new ArrayList<Binatang>();
		Scanner sc = new Scanner(System.in);
		boolean running = true;
		
		// Testing testing
//		listBinatang.add(new Binatang("Asep", "Domba", 7, 120000));
//		listBinatang.add(new Binatang("Budi", "Rusa", 80, 130000));
//		listBinatang.add(new Binatang("Acok", "Anjing", 40, 1000000));
//		listBinatang.add(new Binatang("Alice", "Serigala", 37, 800000));
//		listBinatang.add(new Binatang("Honey", "Ayam", 73, 11000));
//		listBinatang.add(new Binatang("Ucok", "Anjing", 41, 1000001));
		
		while (running) {
			// Input menu
			System.out.println("\n\n\n\n\n\n\n\n\n");
			System.out.println("> Toko Binatang <\n"
					+ "1. Beli binatang\n"
					+ "2. Tambah binatang\n"
					+ "3. Lihat binatang\n"
					+ "4. Cari binatang\n"
					+ "5. Keluar");
			int choice;
	//		while (true) {
				// Pakai nextInt();
	//			try {
	//				choice = sc.nextInt();
	//			} catch (InputMismatchException e) {
	//				sc.nextLine();
	//				System.out.println("Tolong input nomor.");
	//				continue;
	//			}
	//			break;
				
				// Tanpa nextInt();
	//			String input = sc.nextLine();
	//			if (input.matches("\\d+")) {
	//				choice = Integer.parseInt(input);
	//				if (choice != 0 && choice <= 5)
	//					break;
	//				System.out.println("Tolong input nomor dari 1 sampai 5.");
	//			} else {
	//				System.out.println("Tolong input nomor.");
	//			}
	//		}
			choice = Util.inputAngka(sc, 1, 5, "Input");
			
			// Cek opsi
			System.out.println("\n\n");
			switch (choice) {
			case 1:
				int beliIndex = 1;
				System.out.println("No. | Nama Binatang | Jenis Binatang | Umur | Harga Binatang");
				for (Binatang bb: listBinatang) {
					System.out.printf("%3d | %-13s | %-14s | %4d | %14d\n",
							beliIndex++, bb.nama, bb.jenisBinatang, bb.umur, bb.harga);
				}
				int beli = Util.inputAngka(sc, 1, listBinatang.size(), "Pilih binatang");
				listBinatang.remove(beli - 1);
				System.out.println("Biantang terjual.");
				break;
			
			case 2:
				System.out.print("Nama binatang: ");
				String nama = sc.nextLine();
				System.out.print("Jenis binatang: ");
				String jenisBinatang = sc.nextLine();
				int umur = Util.inputAngka(sc, 3, 100, "Umur binatang");
				int harga = Util.inputAngka(sc, 10000, 5000000, "Harga binatang");
				Binatang b = new Binatang(nama, jenisBinatang, umur, harga);
				listBinatang.add(b);
				System.out.println("Binatang sudah ditambah.");
				break;
				
			case 3:
				// for each Binatang b in listBinatang
				int index = 1;
				System.out.println("No. | Nama Binatang | Jenis Binatang | Umur | Harga Binatang");
				for (Binatang bb: listBinatang) {
//					System.out.printf("Nama binatang: %s\n"
//							+ "Jenis binatang: %s\n"
//							+ "Umur binatang: %d bulan\n"
//							+ "Harga binatang: %d\n",
//							bb.nama, bb.jenisBinatang, bb.umur, bb.harga);
					System.out.printf("%3d | %-13s | %-14s | %4d | %14d\n",
							index++, bb.nama, bb.jenisBinatang, bb.umur, bb.harga);
				}
				break;
				
			case 4:
				System.out.print("Cari binatang dengan jenis: ");
				String jenisBinatang2 = sc.nextLine();
				int index2 = 1;
				System.out.println("No. | Nama Binatang | Jenis Binatang | Umur | Harga Binatang");
				for (Binatang bb: listBinatang) {
					if (bb.jenisBinatang.equals(jenisBinatang2))
						System.out.printf("%3d | %-13s | %-14s | %4d | %14d\n",
								index2, bb.nama, bb.jenisBinatang, bb.umur, bb.harga);
					index2++;
				}
				break;
				
			case 5:
				running = false;
				System.out.println("Terima kasih sudah beli binatang kami.");
				break;
			}
			
			if (choice != 5) {
				System.out.print("\n\nTekan enter untuk lanjut...");
				sc.nextLine();
			}
		}
	}

}
