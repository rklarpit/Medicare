package edu.tamu.isys.ratings;

import java.io.IOException;

//import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
public class RatingMapper extends
Mapper<LongWritable, Text, Text, Text>{
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		 String MovieData = value.toString();
		 RatingAnalyzer MovieEntry = new RatingAnalyzer(MovieData);//Declare object for Rate Analyzer class
         String mapperKey = new String();
         int len = MovieEntry.getLength();//will store the length of the fenre member of the object MovieEntry
         String ratingValue = "";
         ratingValue = MovieEntry.getRating();//Will store the rating of every instance of MovieEntry
         String movieValue = "";
         movieValue = MovieEntry.getMovie() + "<<>>" + ratingValue;
         Text mValue = new Text(movieValue);//Object of Text type that will store the value to be written in context.write method
         for(int i=0;i < len; i++){
         mapperKey = MovieEntry.getGenre2(i);
         if (!mapperKey.isEmpty()){
         Text mKey = new Text(mapperKey);//Object of Text type that will store the key (Genre) to be written in context.write method
         context.write(mKey,mValue);
         }
         }
}
}
