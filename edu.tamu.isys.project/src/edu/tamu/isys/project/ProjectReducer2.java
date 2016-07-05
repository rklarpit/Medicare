package edu.tamu.isys.project;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
public class ProjectReducer2 extends
Reducer<Text, Text, Text, Text> {
	
	private String ProjectValue = "";
    private float Cost;
    private String Address;
    public void reduce(Text key, Iterable<Text> values,
			Context context) throws IOException, InterruptedException {
        
    	TreeMap<Float, String> tmap=new TreeMap<Float, String>();
    	for (Text value : values) { //this for loop will populate the arraylist mov
			ProjectValue=   value.toString();
			String[] Parts = ProjectValue.split(";;");
			Cost = Float.parseFloat(Parts[0].trim());
			Address = Parts[1].trim();
			System.out.println("Cost is: " + Cost + "for :" + Address);
			tmap.put(Cost, Address);
    	}
    	Map.Entry<Float,String> entry= tmap.firstEntry();
    	System.out.println("For Disease: " +  key + "Best Treatment is available at :" + entry.getValue() + "with a total Discharge of :" + entry.getKey())  ;
    	Text RValue = new Text ("Cheapest hospital for the treatment is:"+ entry.getValue() + "( Cost is" + entry.getKey() +")");
    	context.write(key,RValue);
    	
    	
    }

}
