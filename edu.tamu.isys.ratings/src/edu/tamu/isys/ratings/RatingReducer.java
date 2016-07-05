package edu.tamu.isys.ratings;
import java.io.IOException;
import java.lang.String;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;


import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
public class RatingReducer extends
Reducer<Text, Text, Text, Text>{
	//Initialize class members
	private String MovieString="";
	private String MovieName = "";
	private String RatingValue = "";
	private String CheckName ="";
    int sum =0;
    String TopMovie = "";
    double AvgRating =0.0;
    int count = 0;
    double TopRating =0.0;
	public void reduce(Text key, Iterable<Text> values,
			Context context) throws IOException, InterruptedException {
		
		ArrayList<String> mov = new ArrayList<String>();//this will store all the values for each Genre in every reducer run
		 
		TreeMap<Double, String> tmap=new TreeMap<Double, String>();// TreeMap used to generate the top rated movie at the end of the class
		int movlen =0;
		for (Text value : values) { //this for loop will populate the arraylist mov
			MovieString=   value.toString();
			mov.add(MovieString);
			
		
         }
	     movlen = mov.size();
	     String temp ="";
	     for (int x=0; x<movlen; x++) // use bubble sort to sort the arraylist mov
         {
           for (int i=0; i < movlen-x-1; i++) {
             if (mov.get(i).compareTo(mov.get(i+1)) > 0)
             {
                 temp = mov.get(i);
                 mov.set(i,mov.get(i+1) );
                 mov.set(i+1, temp);
             }
         }
     }
	 

	 for (int x=0; x<movlen; x++)//loop used to split movies and rating and find average rating
	 {
		 String[] Parts = mov.get(x).split("<<>>");//split the value sent from mapper for each movie entry
			this.MovieName = Parts[0].trim();//stores MovieName for that iteration
			if (MovieName.compareTo(CheckName) != 0){//enters this block for every new movie encountered 
			if (count !=0){
					AvgRating = (double)sum/count;
					tmap.put(AvgRating, CheckName);//stores value of AvgRating and Movie Name of already processed movie
				    sum =0;	
					AvgRating =0 ;
					count =0;
				}
				}
			CheckName = MovieName;
			count ++;
			this.RatingValue = Parts[1].trim();
			sum += Integer.parseInt(RatingValue);//changes the value of rating from string type to integer type and adds to sum to find total of rating
			if(x == movlen -1){//block to store average for the last movie in last iteration
				   AvgRating = (double)sum/count;
				   tmap.put(AvgRating, CheckName);
			    }
			}
	      
	      Map.Entry<Double, String> entry=tmap.lastEntry();//find the last entry of TreeMap to get the top rated movie
	      DecimalFormat df = new DecimalFormat("#.##");// required to get precision for Rating to 2 decimal points
		  Text TopValue = new Text (entry.getValue()+"("+Double.valueOf(df.format(entry.getKey()))+")");
		  context.write(key,TopValue);
	 }
	 
	
}