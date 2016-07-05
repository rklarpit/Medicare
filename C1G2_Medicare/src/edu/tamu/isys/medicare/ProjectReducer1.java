package edu.tamu.isys.medicare;


import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
public class ProjectReducer1 extends
Reducer<Text, Text, Text, Text>{
            
            private String ProjectValue = "";
            private int Discharge;
            private String Address;
            public void reduce(Text key, Iterable<Text> values,
        			Context context) throws IOException, InterruptedException {
	
            	TreeMap<Integer, String> tmap=new TreeMap<Integer, String>();
            	for (Text value : values) { 
        			ProjectValue=   value.toString();
        			String[] Parts = ProjectValue.split(";;");
        			Discharge = Integer.parseInt(Parts[0].trim());
        			Address = Parts[1].trim();
        			System.out.println("Discharge is: " + Discharge + "for :" + Address);
        			tmap.put(Discharge, Address);
             }
            	Map.Entry<Integer,String> entry= tmap.lastEntry();
            	System.out.println("For Disease: " +  key + "Best Treatment is available at :" + entry.getValue() + "with a total Discharge of :" + entry.getKey())  ;
            	Text RValue = new Text (entry.getValue() + "(Discharge :" + entry.getKey() +")");
            	context.write(key,RValue);
}
}