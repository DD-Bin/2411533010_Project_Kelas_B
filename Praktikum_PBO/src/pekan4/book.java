package pekan4;

public class book {
	private String title;
	private String author;
	private boolean isAvailable;
	
	public book ( String title, String author) {
		this.title = title;
		this.author= author;
		this.isAvailable = true;
	}
	
	public String getTitle() { return title; }
	public String getAuthor() { return author; }
	public boolean isAvailable() { return isAvailable; }
	
	public void borrowBook() { this.isAvailable = false; }
	public void returnBook() { this.isAvailable = true; }

}
