package pekan4;

public class Main {
	public static void main(String[] args) {
		//membuat objek buku dari berbagai jenis
		Book novel = new Novel ("Laskar pelangi", "Andrea Hirata", "Drama");
		Book megazine = new Megazine ("National Geographic", "Various Authors", "Science");
		Book textbook = new Textbook ("Pemrograman Java", "Anonimous", "Informatika");
		
		//membuat objek user
		user user = new user();
		
		//menampulkan detai buku menggunakan polymorphism
		System.out.println("\n\n=== Detail Buku ===");
		user.viewBookDetails(novel);
		System.out.println();
		user.viewBookDetails(megazine);
		System.out.println();
		user.viewBookDetails(textbook);
		
		//meminjam Buku
		System.out.println("\n\n=== Proses Peminjaman Buku ===");
		user.borrowBook(novel);
		user.borrowBook(megazine);
		
		//menampilkan status ketersediaan 
		System.out.println("\n\nStatus Buku Setelah Dipinjam : ");
		System.out.println(novel.getTitle() + "tersedia : " + novel.isAvailable());
		System.out.println(megazine.getTitle() + "tersedia : " + megazine.isAvailable());
	
		//mengembalikan buku 
		System.out.println("\n\n=== Proses Pengembalian Buku ===");
		user.returnBook(novel);
		
		//menampilkan status ketersediaan setelah pengembalian
		System.out.println("\n\nStatus buku Setelah Dikembalikan :");
		System.out.println(novel.getTitle() + " tersedia " + novel.isAvailable());
		
		
		
	}
	
	

}
