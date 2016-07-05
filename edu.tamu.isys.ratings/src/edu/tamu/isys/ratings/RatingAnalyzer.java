package edu.tamu.isys.ratings;
import java.text.ParseException;
import java.util.ArrayList;

public class RatingAnalyzer {
	//Declare the members of RatingAnalyzer
	private String Genre = "";
	private String Movie = "";
	private String Rating = "";
	private String error = "";
	private int length=0;
	private ArrayList<String> Genre2 = new ArrayList<String>();// This ArryaList will store the values  of different Genres for each movie
	public  RatingAnalyzer(String MovieData) {
		try {
			parseMovieDetails(MovieData);
    }catch(Exception e) {
	error = e.getStackTrace().toString() + "***" + MovieData;
    }
    }
	/*public String getGenre() {
		return Genre;
	}*/
	public String getMovie() {
		return Movie;
	}
	public String getRating() {
		return Rating;
	}
	public String getError() {
		return error;
	}
	/*public boolean hasError() {
		return !error.isEmpty();
	}*/
	public int getLength() {
		return length;
	}
    public String getGenre2(int index){
		return Genre2.get(index);
	}
    private  void parseMovieDetails(String MovieData) throws ParseException {
		String[] MovieParts = MovieData.split("::");
		this.Genre= MovieParts[2].trim();
		this.length = Genre.split(",").length;
		String [] TempGenre = Genre.split(",");
		for (int i=0 ; i < length; i++)
			Genre2.add(TempGenre[i].trim());
		this.Movie= MovieParts[1].trim();
		this.Rating= MovieParts[6].trim();
	}		
}