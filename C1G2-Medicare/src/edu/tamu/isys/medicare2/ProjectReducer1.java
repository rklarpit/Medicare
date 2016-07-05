//reducer for handling best treatment and best treatment in a particular locality

package edu.tamu.isys.medicare2;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class ProjectReducer1 extends Reducer<Text, Text, Text, Text> {

	private String ProjectValue = "";
	private int Discharge;
	private String Address;

	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

		TreeMap<Integer, String> tmap = new TreeMap<Integer, String>();
		for (Text value : values) {
			ProjectValue = value.toString();
			String[] Parts = ProjectValue.split(";;"); //splits ProjectValue into no. of patients cured and address
			Discharge = Integer.parseInt(Parts[0].trim()); //assign value to respective variables
			Address = Parts[1].trim();	//assign value to respective variables
			tmap.put(Discharge, Address); //assign values to a tree map
		}
		Map.Entry<Integer, String> entry = tmap.lastEntry(); //get the last entry (the one with the maximum cured) from the tree map
		Text RValue = new Text(
				"Best treatment is available at: " + entry.getValue() + " (Discharge :" + entry.getKey() + ")");
		context.write(key, RValue);
	}
}