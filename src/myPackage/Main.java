package myPackage;

// Tentang Program:
// Program ini berfungsi untuk memasukkan data-data pemain sepak bola (tidak harus pemain resmi).
// Data-data tersebut akan disimpan dalam suatu ArrayList.
// Selain memasukkan data-data, program ini bisa memunculkan data-data yang ada di ArrayList, 
// mengubah suatu data dari pemain, dan menghapus data pemain tersebut dari ArrayList.
// Untuk mempermudah penjelasan, saya akan merujuk pengguna program ini sebagai 'pengguna'.

// Library yang dibutuhkan:
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

// Ada atribut pemain yang bernama "stamina" yang dihasilkan secara acak berdasarkan umur.
// Rentang umur dan posisi yang berbeda memiliki batasan stamina (yang angkanya saya pilih secara acak) yang berbeda-beda.
// Rumus mengambil angka secara acak dari batasan: (Math.random() * (max - min)) + min + 1
// Max Stamina dari seorang pemain = 100, Min = 50

public class Main {
	Scanner scan = new Scanner(System.in); // Membuat objek dari Scanner untuk membantu mendapat input dari pengguna.
	Random rand = new Random(); // Randomizer untuk menghasilkan ID unik.
	ArrayList<Player> player_list = new ArrayList<>(); // ArrayList yang akan menjadi tempat menampung data-data pemain.

	public final void start_menu() {
		// Menu Awal dari Program.
		System.out.println("\r\n"
				+ "  _________                                \r\n"
				+ " /   _____/ ____   ____  ____  ___________ \r\n"
				+ " \\_____  \\ /  _ \\_/ ___\\/ ___\\/ __ \\_  __ \\\r\n"
				+ " /        (  <_> )  \\__\\  \\__\\  ___/|  | \\/\r\n"
				+ "/_______  /\\____/ \\___  >___  >___  >__|   \r\n"
				+ "        \\/            \\/    \\/    \\/       \r\n"
				+ "");
		System.out.println("+===========================================+");
		System.out.println("+ A Program for registering soccer players  +");
		System.out.println("+===========================================+");
		System.out.println("1. Add a Player"); // Memasukkan pemain baru.
		System.out.println("2. Show List of Players"); // Memunculkan daftar pemain.
		System.out.println("3. Update Player's Information"); // Memperbarui data pemain.
		System.out.println("4. Delete Player's Information"); // menghapus data pemain.
		System.out.println("5. Exit Program"); // keluar dari program.
		System.out.println("+===========================================+");
		System.out.print("Your Choice: ");
	}
	
	public final void pressEnterToContinue() {
		// Fungsi/Metode untuk memberi jeda sebelum bergerak ke aksi berikutnya.
		System.out.println("\nPress Enter To Continue..."); scan.nextLine();
	}
	
	public final String genID(String pos) {
		// Fungsi untuk menghasilkan ID yang terdiri dari 5 karakter
		String player_id;
		
		// 2 karakter awal disesuaikan dengan posisi pemain
		if(pos.equalsIgnoreCase("Forward")) player_id = "FW";
		else if(pos.equalsIgnoreCase("Center")) player_id = "CT";
		else if(pos.equalsIgnoreCase("Back")) player_id = "BK";
		else player_id = "KP";
		
		// 3 karakter akhir terdiri dari angka 0 sampai 9 secara acak
		for(int i=0;i<3;i++) {
			int temp = rand.nextInt(10);
			player_id += temp;
		}
		return player_id;
	}
	
	public final boolean checkID(String player_id) {
		// Fungsi untuk mengecek apakah ID yang dihasilkan sudah digunakan pemain lain atau belum.
		// Jika ArrayList kosong, sudah pasti ID yang dihasilkan unik
		if(player_list.isEmpty()) return true;
		else {
			// Jika ArrayList tidak kosong, ID yang dihasilkan harus dicek dengan pemain lain
			Player curr;
			for(int i=0;i<player_list.size();i++) {
				curr = player_list.get(i);
				if(player_id.equals(curr.getPlayer_id())) return false;
			}
			return true;
		}
	}
	
	public final void addPlayer() {
		// Fungsi untuk menambah pemain.
		String name, nationality, club, position, player_id, tempAge, tempJerseyNumber;
		
		// Catatan Khusus.
		System.out.println("\nNote: Please Input Name, Nationality AND Position Correctly, as these attributes"
				+ " cannot be changed!\n");
		
		// Nama harus terdiri dari huruf alfabet (spasi menjadi pilihan) dan panjang nama diantara 5 sampai 40 karakter (inklusif).
		do {
			System.out.print("Input Name [5 - 40 Letters AND Letters Only]: "); name = scan.nextLine(); name.strip();
		} while(!name.matches("[a-zA-Z\u0020]+") || name.length()<5 || name.length()>40);
		
		// Umur harus numerik dan seorang pemain harus berumur diantara 18 sampai 39 tahun (inkulsif diskrit).
		do {
			System.out.print("Input Age [18 - 39 years old AND Numeric (Discrete) Only]: "); tempAge = scan.nextLine(); tempAge.strip();
		} while(!tempAge.matches("[0-9]+") || Integer.parseInt(tempAge)<18 || Integer.parseInt(tempAge)>39);
		
		int age = Integer.parseInt(tempAge);
		
		// Kewarganegaraan harus terdiri dari huruf alfabet (spasi menjadi pilihan) dan panjangnya diantara 3 sampai 20 karakter
		do {
			System.out.print("Input Nationality [3 - 20 Letters AND Letters Only]: "); nationality = scan.nextLine(); nationality.strip();
		} while(!nationality.matches("[a-zA-Z\u0020]+") || nationality.length()<3 || nationality.length()>20);
		
		// Nomor Jersey harus numerik diskrit.
		do {
			System.out.print("Input Jersey Number [Numeric (Discrete) Only]: "); tempJerseyNumber = scan.nextLine(); tempJerseyNumber.strip();
		} while(!tempJerseyNumber.matches("[0-9]+"));
		
		int jerseyNumber = Integer.parseInt(tempJerseyNumber);
		
		// Klub harus terdiri dari huruf alfabet (spasi menjdi pilihan) dan 
		// jika pemain tidak sedang berada di klub mana pun, atribut "club" mereka dimasukkan "FREE".
		do {
			System.out.print("Input Club [Letters Only (Type FREE if the player is not in a club]: "); club = scan.nextLine(); club.strip();
		} while(!club.matches("[a-zA-Z\u0020]+"));
		
		// Umumnya, posisi pemain sepak bola ada empat: Forward(pemain lini depan), 
		// Center(pemain lini tengah), Back(pemain lini belakang), dan Keeper(penjaga gawang).
		do {
			System.out.print("Input Position [Forward OR Center OR Back OR Keeper (case insensitive)]: "); position = scan.nextLine(); position.strip();
		} while(!position.equalsIgnoreCase("Forward") && !position.equalsIgnoreCase("Center") && !position.equalsIgnoreCase("Back") && !position.equalsIgnoreCase("Keeper"));
		
		// Menghasilkan ID secara random dan unik
		do {
			player_id = genID(position);
		} while(!checkID(player_id));
		
		// Masuk ke blok yang berbeda sesuai dengan posisi
		if(position.equalsIgnoreCase("Forward")) {
			// Jika posisi pemain baru adalah "Forward", pengguna diminta untuk memasukkan data "kick_power".
			// "kick_power" harus numerik diskrit dan bernilai di antara 50 sampai 100 (inklusif).
			String tempKick_power; int kick_power;
			do {
				System.out.print("Input Kick Power [50 - 100 AND Numeric (Discrete) Only]: "); tempKick_power = scan.nextLine(); tempKick_power.strip();
			} while(!tempKick_power.matches("[0-9]+") || Integer.parseInt(tempKick_power)<50 || Integer.parseInt(tempKick_power)>100);
			
			kick_power = Integer.parseInt(tempKick_power);
			
			// Membuat Objek "Forward" baru dan memasukkannya ke dalam ArrayList.
			Player newForward = new Forward(name,nationality,club,position,player_id,age,jerseyNumber,kick_power);
			newForward.genStamina(); // Menghasilkan stamina berdasarkan umur.
			player_list.add(newForward);
		} 
		else if(position.equalsIgnoreCase("Center")) {
			// Jika posisi pemain baru adalah "Center", pengguna diminta untuk memasukkan data "pass_accuracy".
			// "pass_accuracy" harus numerik diskrit dan bernilai di antara 50 sampai 100 (inklusif).
			String tempPass_acc; int pass_accuracy;
			do {
				System.out.print("Input Pass Accuracy [50 - 100 AND Numeric (Discrete) Only]: "); tempPass_acc = scan.nextLine(); tempPass_acc.strip();
			} while(!tempPass_acc.matches("[0-9]+") || Integer.parseInt(tempPass_acc)<50 || Integer.parseInt(tempPass_acc)>100);
			
			pass_accuracy = Integer.parseInt(tempPass_acc);
			
			// Membuat Objek "Center" baru dan memasukkannya ke dalam ArrayList.
			Player newCenter = new Center(name,nationality,club,position,player_id,age,jerseyNumber,pass_accuracy);
			newCenter.genStamina(); // Menghasilkan stamina berdasarkan umur.
			player_list.add(newCenter);
		} 
		else if(position.equalsIgnoreCase("Back")) {
			// Jika posisi pemain baru adalah "Back", pengguna diminta untuk memasukkan data "defend_capability".
			// "defend_capability" harus numerik diskrit dan bernilai di antara 50 sampai 100 (inklusif).
			String tempDefend_cap; int defend_capability;
			do {
				System.out.print("Input Defend Capability [50 - 100 AND Numeric (Discrete) Only]: "); tempDefend_cap = scan.nextLine(); tempDefend_cap.strip();
			} while(!tempDefend_cap.matches("[0-9]+") || Integer.parseInt(tempDefend_cap)<50 || Integer.parseInt(tempDefend_cap)>100);
			
			defend_capability = Integer.parseInt(tempDefend_cap);
			
			// Membuat Objek "Back" baru dan memasukkannya ke dalam ArrayList.
			Player newBack = new Back(name,nationality,club,position,player_id,age,jerseyNumber,defend_capability);
			newBack.genStamina(); // Menghasilkan stamina berdasarkan umur.
			player_list.add(newBack);
		}
		else if(position.equalsIgnoreCase("Keeper")) {
			// Jika posisi pemain baru adalah "Keeper", atribut spesialnya akan dihasilkan secara acak (tidak seperti posisi-posisi lain).
			// Membuat Objek "Keeper" baru dan memasukkannya ke dalam ArrayList.
			Player newKeeper = new Keeper(name,nationality,club,position,player_id,age,jerseyNumber);
			newKeeper.genStamina(); ((Keeper) newKeeper).genArmStrength(); // Menghasilkan stamina dan "arm_strength" berdasarkan umur.
			player_list.add(newKeeper);
		}
		
		System.out.printf("\nNew Player '%s' has been added...\n",name);
	}
	
	public final void showList() {
		// Fungsi untuk menampilkan isi ArrayList.
		// Jika ArrayList kosong, masuk blok "if". Selain itu, masuk blok "else".
		if(player_list.isEmpty()) System.out.println("\nCurrently No Players Registered Here...");
		else {
			System.out.printf("\n| %-3s | %-5s | %-40s | %-20s | %-3s | %-13s | %-20s | %-8s | %-7s | %-25s |\n","No.",
					"ID","Name","Nationality","Age","Jersey Number","Club","Position","Stamina","Special");
			for(int i=0;i<player_list.size();i++) {
				Player curr = player_list.get(i);
				System.out.printf("| %-3s | %-5s | %-40s | %-20s | %-3d | %-13d | %-20s | %-8s | %-7d | ",i+1,curr.getPlayer_id(),
						curr.getName(),curr.getNationality(),curr.getAge(),curr.getJerseyNumber(),curr.getClub(),
						curr.getPosition(),curr.getStamina());
				if(curr instanceof Forward) {
					System.out.printf("%-10s: %-3d           |\n","Kick Power",((Forward) curr).getKick_power());
				}
				else if(curr instanceof Center) {
					System.out.printf("%-13s: %-3d        |\n","Pass Accuracy",((Center) curr).getPass_accuracy());
				}
				else if(curr instanceof Back) {
					System.out.printf("%-17s: %-3d    |\n","Defend Capability",((Back) curr).getDefend_capability());
				}
				else if(curr instanceof Keeper) {
					System.out.printf("%-12s: %-3d         |\n","Arm Strength",((Keeper) curr).getArm_strength());
				}
			}
		} return;
	}
	
	public final void aboutUpdate(Player curr) {
		// Fungsi Utama untuk mengubah data pemain.
		// Menampilkan pilihan atribut pemain yang bisa diubah.
		System.out.println("\nNote: As mentioned earlier, the attributes Name, Nationality AND Position CANNOT BE CHANGED!");
		System.out.println("Another Note: You may add a new player and remove the current one if you wish to change these attributes!");
		System.out.println("Another Note: ID CANNOT BE CHANGED!!!");
		System.out.println("\nWhich Information you want to update?:");
		System.out.println("1. Age");
		System.out.println("2. Jersey Number");
		System.out.println("3. Club");
		System.out.println("4. Stamina");
		if(curr instanceof Forward) {
			System.out.println("5. Kick Power");
		}
		else if(curr instanceof Center) {
			System.out.println("5. Pass Accuracy");
		}
		else if(curr instanceof Back) {
			System.out.println("5. Defend Capability");
		}
		else if(curr instanceof Keeper) {
			System.out.println("5. Arm Strength");
		}
		
		// Pengguna memilih atribut apa yang ingin diubah.
		String choice1;
		do {
			System.out.print("Your Choice: "); choice1 = scan.nextLine(); choice1.strip();
		} while(!choice1.matches("[0-9]+")||Integer.parseInt(choice1)<1||Integer.parseInt(choice1)>5);
		
		int choice = Integer.parseInt(choice1);
		
		switch(choice) {
		case 1: {
			// Jika Pengguna ingin mengubah data umur, 
			// umur baru harus lebih dari umur sekarang dan kurang dari 39 tahun (inklusif numerik diskrit).
			String temp_age;
			System.out.printf("\nCurrent Age: %d\n",curr.getAge());
			do {
				System.out.printf("New Age [%d-39 years old]: ",curr.getAge()); temp_age = scan.nextLine(); temp_age.strip();
			} while(!temp_age.matches("[0-9]+") || Integer.parseInt(temp_age)<curr.getAge() || Integer.parseInt(temp_age)>39);
			
            int age = Integer.parseInt(temp_age);
            
            // Mengonfirmasi keputusan pengguna.
			String confirm;
			
			do {
				System.out.print("\nAre you sure you want to change this player's age? [yes OR no (case insensitive)]: ");
				confirm = scan.nextLine(); confirm.strip();
			} while(!confirm.equalsIgnoreCase("yes") && !confirm.equalsIgnoreCase("no"));
			
			if(confirm.equalsIgnoreCase("yes")) {
				System.out.println("\nChanging..."); curr.setAge(age);
			}
			else if(confirm.equalsIgnoreCase("no")) {
				System.out.println("\nCancelling...");
			}
			break;
		}
		case 2: {
			// Jika pengguna ingin mengubah data nomor jersey, nomor jersey baru harus numerik diskrit.
			String temp_jersey;
			System.out.printf("\nCurrent Jersey Number: %d\n",curr.getJerseyNumber());
			do {
				System.out.printf("New Jersey Number: "); temp_jersey = scan.nextLine(); temp_jersey.strip();
			} while(!temp_jersey.matches("[0-9]+"));
			
            int jersey = Integer.parseInt(temp_jersey);
            
            // Mengonfirmasi keputusan pengguna.
			String confirm;
			
			do {
				System.out.print("\nAre you sure you want to change this player's jersey number? [yes OR no (case insensitive)]: ");
				confirm = scan.nextLine(); confirm.strip();
			} while(!confirm.equalsIgnoreCase("yes") && !confirm.equalsIgnoreCase("no"));
			
			if(confirm.equalsIgnoreCase("yes")) {
				System.out.println("\nChanging..."); curr.setJerseyNumber(jersey);
			}
			else if(confirm.equalsIgnoreCase("no")) {
				System.out.println("\nCancelling...");
			}
			break;
		}
		case 3: {
			// Jika pengguna ingin mengubah data club, 
			// club baru harus terdiri dari huruf alfabet (spasi menjadi pilihan) dan 
			// tidak boleh sama dengan "club" dulunya.
			String club;
			System.out.printf("\nCurrent Club: %s\n",curr.getClub());
			do {
				System.out.printf("New Club [Different From Current Club AND Type FREE if the player leaves a club and currently not in a club]: "); club = scan.nextLine(); club.strip();
			} while(!club.matches("[a-zA-Z\u0020]+") || club.equalsIgnoreCase(curr.getClub()));
			
			// Mengonfirmasi keputusan pengguna.
			String confirm;
			
			do {
				System.out.print("\nAre you sure you want to change this player's club? [yes OR no (case insensitive)]: ");
				confirm = scan.nextLine(); confirm.strip();
			} while(!confirm.equalsIgnoreCase("yes") && !confirm.equalsIgnoreCase("no"));
			
			if(confirm.equalsIgnoreCase("yes")) {
				System.out.println("\nChanging..."); curr.setClub(club);
			}
			else if(confirm.equalsIgnoreCase("no")) {
				System.out.println("\nCancelling...");
			}
			break;
		}
		case 4: {
			// Jika pengguna ingin mengubah data stamina, stamina harus numerik diskrit dan bernilai lebih dari 50 dan kurang dari 100 (inklusif).
			String temp_stamina;
			System.out.printf("\nCurrent Stamina: %d\n",curr.getStamina());
			do {
				System.out.printf("New Stamina [50 - 100 AND Numeric (Discrete) Only]: ",curr.getAge()); temp_stamina = scan.nextLine(); temp_stamina.strip();
			} while(!temp_stamina.matches("[0-9]+") || Integer.parseInt(temp_stamina)<50 || Integer.parseInt(temp_stamina)>100);
			
            int stamina = Integer.parseInt(temp_stamina);
            
            // Mengonfirmasi keputusan pengguna.
			String confirm;
			
			do {
				System.out.print("\nAre you sure you want to change this player's age? [yes OR no (case insensitive)]: ");
				confirm = scan.nextLine(); confirm.strip();
			} while(!confirm.equalsIgnoreCase("yes") && !confirm.equalsIgnoreCase("no"));
			
			if(confirm.equalsIgnoreCase("yes")) {
				System.out.println("\nChanging..."); curr.setStamina(stamina);
			}
			else if(confirm.equalsIgnoreCase("no")) {
				System.out.println("\nCancelling...");
			}
			break;
		}
		case 5: {
			if(curr instanceof Forward) {
				// Jika pengguna ingin mengubah data kick power, kickpow harus numerik diskrit dan bernilai lebih dari 50 dan kurang dari 100 (inklusif).
				String temp_kickpow;
				System.out.printf("\nCurrent Kick Power: %d\n",((Forward) curr).getKick_power());
				do {
					System.out.printf("New Kick Power [50 - 100 AND Numeric (Discrete) Only]: "); temp_kickpow = scan.nextLine(); temp_kickpow.strip();
				} while(!temp_kickpow.matches("[0-9]+") || Integer.parseInt(temp_kickpow)<50 || Integer.parseInt(temp_kickpow)>100);
				
	            int kickpow = Integer.parseInt(temp_kickpow);
	            
	            // Mengonfirmasi keputusan pengguna.
				String confirm;
				
				do {
					System.out.print("\nAre you sure you want to change this player's kick power? [yes OR no (case insensitive)]: ");
					confirm = scan.nextLine(); confirm.strip();
				} while(!confirm.equalsIgnoreCase("yes") && !confirm.equalsIgnoreCase("no"));
				
				if(confirm.equalsIgnoreCase("yes")) {
					System.out.println("\nChanging..."); ((Forward) curr).setKick_power(kickpow);
				}
				else if(confirm.equalsIgnoreCase("no")) {
					System.out.println("\nCancelling...");
				}
			}
			else if(curr instanceof Center) {
				// Jika pengguna ingin mengubah data pass accuracy, passacc harus numerik diskrit dan bernilai lebih dari 50 dan kurang dari 100 (inklusif).
				String temp_passacc;
				System.out.printf("\nCurrent Pass Accuracy: %d\n",((Center) curr).getPass_accuracy());
				do {
					System.out.printf("New Pass Accuracy [50 - 100 AND Numeric (Discrete) Only]: "); temp_passacc = scan.nextLine(); temp_passacc.strip();
				} while(!temp_passacc.matches("[0-9]+") || Integer.parseInt(temp_passacc)<50 || Integer.parseInt(temp_passacc)>100);
				
	            int passacc = Integer.parseInt(temp_passacc);
	            
	            // Mengonfirmasi keputusan pengguna.
				String confirm;
				
				do {
					System.out.print("\nAre you sure you want to change this player's pass accuracy? [yes OR no (case insensitive)]: ");
					confirm = scan.nextLine(); confirm.strip();
				} while(!confirm.equalsIgnoreCase("yes") && !confirm.equalsIgnoreCase("no"));
				
				if(confirm.equalsIgnoreCase("yes")) {
					System.out.println("\nChanging..."); ((Center) curr).setPass_accuracy(passacc);
				}
				else if(confirm.equalsIgnoreCase("no")) {
					System.out.println("\nCancelling...");
				}
			}
			else if(curr instanceof Back) {
				// Jika pengguna ingin mengubah data defend capability, defend harus numerik diskrit dan bernilai lebih dari 50 dan kurang dari 100 (inklusif).
				String temp_defend;
				System.out.printf("\nCurrent Defend Capability: %d\n",((Back) curr).getDefend_capability());
				do {
					System.out.printf("New Defend Capability [50 - 100 AND Numeric (Discrete) Only]: "); temp_defend = scan.nextLine(); temp_defend.strip();
				} while(!temp_defend.matches("[0-9]+") || Integer.parseInt(temp_defend)<50 || Integer.parseInt(temp_defend)>100);
				
	            int defend = Integer.parseInt(temp_defend);
	            
	            // Mengonfirmasi keputusan pengguna.
				String confirm;
				
				do {
					System.out.print("\nAre you sure you want to change this player's defend capability? [yes OR no (case insensitive)]: ");
					confirm = scan.nextLine(); confirm.strip();
				} while(!confirm.equalsIgnoreCase("yes") && !confirm.equalsIgnoreCase("no"));
				
				if(confirm.equalsIgnoreCase("yes")) {
					System.out.println("\nChanging..."); ((Back) curr).setDefend_capability(defend);
				}
				else if(confirm.equalsIgnoreCase("no")) {
					System.out.println("\nCancelling...");
				}
			}
			else if(curr instanceof Keeper) {
				// Jika pengguna ingin mengubah data arm strength, arm harus numerik diskrit dan bernilai lebih dari 39 dan kurang dari 100 (inklusif).
				String temp_arm;
				System.out.printf("\nCurrent Arm Strength: %d\n",((Keeper) curr).getArm_strength());
				do {
					System.out.printf("New Arm Strength [39 - 100 AND Numeric (Discrete) Only]: "); temp_arm = scan.nextLine(); temp_arm.strip();
				} while(!temp_arm.matches("[0-9]+") || Integer.parseInt(temp_arm)<39 || Integer.parseInt(temp_arm)>100);
				
	            int arm = Integer.parseInt(temp_arm);
	            
	            // Mengonfirmasi keputusan pengguna.
				String confirm;
				
				do {
					System.out.print("\nAre you sure you want to change this player's arm strength? [yes OR no (case insensitive)]: ");
					confirm = scan.nextLine(); confirm.strip();
				} while(!confirm.equalsIgnoreCase("yes") && !confirm.equalsIgnoreCase("no"));
				
				if(confirm.equalsIgnoreCase("yes")) {
					System.out.println("\nChanging..."); ((Keeper) curr).setArm_strength(arm);
				}
				else if(confirm.equalsIgnoreCase("no")) {
					System.out.println("\nCancelling...");
				}
			}
			break;
		}
		default: {
			// Jika pilihan menu selain 1, 2, 3, 4, dan 5.
			System.out.println("\nError Occured..."); return;
		}
		}
		// Jika program berhasil mengubah data pemain, program akan mencetak pesan berikut ini dan return.
		System.out.println("\nSuccessfully Update the Player's Information"); return;
	}
	
	public final void updatePlayer() {
		// Fungsi Awal untuk mengubah data pemain.
		// Jika ArrayList tidak kosong, masuk ke blok "if".
		if(!player_list.isEmpty()) {
			// Pengguna memilih data pemain yang mana yang ingin diubah.
			String choice;
			showList();
			System.out.print("\nChoose Which Player's Information to be updated [by ID]: "); 
			choice = scan.nextLine(); choice.strip();
			
			Player curr;
			
			// Masuk ke fungsi utama untuk mengubah data pemain (Jika ID ditemukan).
			for(int i=0;i<player_list.size();i++) {
				curr = player_list.get(i);
				if(curr.getPlayer_id().equals(choice)) {
					aboutUpdate(curr); return;
				}
			}
			// Jika ID tidak ditemukan
			System.out.println("\nID Not Found..."); return;
		}
		// Jika ArrayList kosong, program menceta pesan berikut ini dan return.
		System.out.println("\nThere is currently no players in the list..."); return;
	}
	
	public final void deletePlayer() {
		// Fungsi untuk menghapus pemain dari ArrayList.
		// Jika ArrayList tidak kosong, masuk ke blok "if".
		if(!player_list.isEmpty()) {
			// Pengguna memilih pemain mana yang ingin dihapus dari list berdasarkan urutan pemain dimasukkan.
			String choice;
			showList();
			System.out.print("\nChoose Which Player's Information to be deleted [by ID]: "); 
			choice = scan.nextLine(); choice.strip();
			
			Player curr;
			
			// Program mencoba mencari ID di ArrayList
			for(int i=0;i<player_list.size();i++) {
				curr = player_list.get(i);
				// Masuk ke blok "if" jika ID ditemukan
				if(curr.getPlayer_id().equals(choice)) {
					// Meyakinkan Pengguna untuk menghapus data pemain atau tidak.
					String confirm;
					
					do {
						System.out.print("\nAre you sure you want to delete this player's information? [yes OR no (case insensitive)]: ");
						confirm = scan.nextLine(); confirm.strip();
					} while(!confirm.equalsIgnoreCase("yes") && !confirm.equalsIgnoreCase("no"));
					
					String name = player_list.get(i).getName();
					
					if(confirm.equalsIgnoreCase("yes")) {
						// Jika Pengguna benar-benar yakin ingin menghapus data pemain yang telah dipilih.
						System.out.println("\nDeleting..."); player_list.remove(i);
						System.out.printf("\nThe Player '%s' has been removed from the list...\n",name);
					}
					else if(confirm.equalsIgnoreCase("no")) {
						// Jika pengguna tidak ingin menghapus data pemain.
						System.out.println("\nCancelling...");
					}
					return;
				}
			}
			// Jika ID tidak ditemukan
			System.out.println("\nID Not Found..."); return;
		}
		// Jika ArrayList kosong, langsung mencetak pesan berikut ini dan return. 
		System.out.println("\nThere is currently no players in the list..."); return;
	}
	
	public Main() {
		// "Main" disini berperan sebagai "Driver Program".
		int menu;
		
		do {
			start_menu(); menu = scan.nextInt(); scan.nextLine();
			
			switch(menu) {
			case 1: {
				addPlayer(); pressEnterToContinue(); break;
			}
			case 2: {
				showList(); pressEnterToContinue(); break;
			}
			case 3: {
				updatePlayer(); pressEnterToContinue(); break;
			}
			case 4: {
				deletePlayer(); pressEnterToContinue(); break;
			}
			case 5: {
				System.out.println("\nExiting Program..."); player_list.clear();
				pressEnterToContinue(); break;
			}
			default: {
				// Jika pilihan menu selain 1, 2, 3, 4, dan 5.
				System.out.println("\nInvalid Choice..."); pressEnterToContinue(); break;
			}
			}
		} while(menu!=5);
		scan.close();
	}

	public static void main(String[] args) {
		new Main(); // Objek dari kelas "Main" digunakan untuk menjalankan program.
	}
}
