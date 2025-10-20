package pekan4;

public class user {

	private String name;
	
	public user() {
		this.name = "pengguna umum ";
	}
	public user(String name) { 
		this.name = name;
	}
	
	//menampilkan detail buku menggunakan polymorphism
	public void viewBookDetails( Book book) {
		System.out.println(" judul : " + book.getTitle() );
		System.out.println(" author : " + book.getAuthor());
		System.out.println(" tersedia : " + (book.isAvailable() ?  "ya" : "tidak"));

	//polymorphism : cek tipe buku 
	if ( book instanceof Novel) {
		Novel novel = ( Novel ) book;
		System.out.println("genre : " + novel.getGenre());}
	}
	
	public void borrowBook(Book book) {
		if (book.isAvailable()) {
			book.borrowBook();
			System.out.println("buku \" " + book.getTitle() + "\" berhasil dipinjam oleh" + this.name );
		} else {
			System.out.println("maaf" + this.name + ", buku \" " + book.getTitle() + "\" sedang tidak tersedia." );
		}
	}
	
	//mengembalikan buku 
	public void returnBook (Book book) {
		if (!book.isAvailable()) {
			book.returnBook();
			System.out.println("buku \"" + book.getTitle() + "\" berhasil dikembalikan." );
		} else {
			System.out.println("buku \"" + book.getTitle() + "\" sudah tersedia.");
		}
	}
	
	
}
