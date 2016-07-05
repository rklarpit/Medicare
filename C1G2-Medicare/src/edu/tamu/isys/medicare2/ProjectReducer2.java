//reducer for handling cheapest treatment and cheapest treatment in a particular locality
package edu.tamu.isys.medicare2;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class ProjectReducer2 extends Reducer<Text, Text, Text, Text> {

	private String ProjectValue = "";
	private float Cost;
	private String Address;

	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

		TreeMap<Float, String> tmap = new TreeMap<Float, String>();
		for (Text value : values) {
			ProjectValue = value.toString();
			String[] Parts = ProjectValue.split(";;");	//splits ProjectValue into avg. cost and address
			Cost = Float.parseFloat(Parts[0].trim());	//convert from string to float
			Address = Parts[1].trim();	//assign value to respective variables
			tmap.put(Cost, Address);	//assign values to a tree map
		}
		Map.Entry<Float, String> entry = tmap.firstEntry();	//get the first entry (the one with the minimum avg cost) from the tree map
		Text RValue = new Text(
				"Cheapest treatment is available at: " + entry.getValue() + " ( Cost is $" + entry.getKey() + ")");
		context.write(key, RValue);
	}

}
