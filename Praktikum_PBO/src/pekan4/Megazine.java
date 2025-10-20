package pekan4;

public class Megazine extends Book {
	
	private String genre; 

	public Megazine(String title, String author, String genre	) {
		super(title, author);
		this.genre = genre;
	}
	
	public String getGenre() { return genre;}

}
