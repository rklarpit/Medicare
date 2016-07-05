package edu.tamu.isys.project;
import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;
public class ProjectMapper2 extends
Mapper<LongWritable, Text, Text, Text>{
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException{
		System.out.println("I am Here");
		String ProjectData = value.toString();
		ProjectAnalyzer1 ProjectEntry = new ProjectAnalyzer1(ProjectData);
		String ProjectDisease = "";
		ProjectDisease = ProjectEntry.getDisease();
		String ProjectCCharge = ProjectEntry.getAverage_Covered_Charges();
		System.out.println("Covered Charge is:" + ProjectCCharge);
		float cost1 = Float.parseFloat(ProjectEntry.getAverage_Covered_Charges().replace('"', ' ').trim().replace("$", "").trim().replace(",", "")) + Float.parseFloat(ProjectEntry.getAverage_Medicare_Payments().replace('"', ' ').trim().replace("$", "").trim().replace(",", "")) + Float.parseFloat(ProjectEntry.getAverage_Total_Payments().replace('"', ' ').trim().replace("$", "").trim().replace(",", ""));
		System.out.println("Cost1 is " + cost1);
		//float Cost = Float.valueOf(ProjectEntry.getAverage_Covered_Charges().replace('"', ' ').trim().replace("$", "").trim().replace(",", "") + ProjectEntry.getAverage_Medicare_Payments().replace('"', ' ').trim().replace("$", "").trim().replace(",", "") + ProjectEntry.getAverage_Total_Payments().replace('"', ' ').trim().replace("$", "").trim().replace(",", "")) ;
		//System.out.println("Cost is :" +  Float.valueOf(ProjectEntry.getAverage_Covered_Charges().replace('"', ' ').trim().replace("$", "").trim().replace(",", "")) + Float.valueOf(ProjectEntry.getAverage_Medicare_Payments().replace('"', ' ').trim().replace("$", "").trim().replace(",", "")) + Float.valueOf(ProjectEntry.getAverage_Total_Payments().replace('"', ' ').trim().replace("$", "").trim().replace(",", "")));
		String ProjectValue =String.valueOf(cost1) + ";;" + ProjectEntry.getHospital_Name() + " " + ProjectEntry.getHospital_Street_Address() + " " + ProjectEntry.getHospital_City() + " " + ProjectEntry.getHospital_State() + " " + ProjectEntry.getHospital_Zip();
		Text mapperKey = new Text(ProjectDisease);
		Text mapperValue = new Text(ProjectValue);
		if (ProjectDisease.contains("ACUTE MYOCARDIAL INFARCTION, DISCHARGED ALIVE W MCC") && ProjectEntry.getHospital_City().contains("DOTHAN"))
		{
			System.out.println(ProjectEntry.getHospital_City());
			context.write(mapperKey,mapperValue);
}
		}
		
//		String ProjectData = value.toString();
//		ProjectAnalyzer1 ProjectEntry = new ProjectAnalyzer1(ProjectData);
//		String ProjectDisease = "";
//		ProjectDisease = ProjectEntry.getDisease();
//		String ProjectValue = "";
//		ProjectValue =ProjectEntry.getTotal_Cured() + ";;" + ProjectEntry.getHospital_Name() + " " + ProjectEntry.getHospital_Street_Address() + " " + ProjectEntry.getHospital_City() + " " + ProjectEntry.getHospital_State() + " " + ProjectEntry.getHospital_Zip();
//		//System.out.println(ProjectDisease +" " + ProjectValue);
//		Text mapperKey = new Text(ProjectDisease);
//		Text mapperValue = new Text(ProjectValue);
//		if (ProjectDisease.contains("ACUTE MYOCARDIAL INFARCTION, DISCHARGED ALIVE W MCC") && ProjectEntry.getHospital_City().contains("DOTHAN"))
//		{
//			System.out.println(ProjectEntry.getHospital_City());
//			context.write(mapperKey,mapperValue);
}
		


