package edu.tamu.isys.medicare;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class ProjectMapper2 extends Mapper<LongWritable, Text, Text, Text> {
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		//System.out.println("I am Here");
		String ProjectData = value.toString();
		ProjectAnalyzer1 ProjectEntry = new ProjectAnalyzer1(ProjectData);
		String ProjectDisease = "";
		ProjectDisease = ProjectEntry.getDisease();
		String ProjectCCharge = ProjectEntry.getAverage_Covered_Charges();
		float cost1 = Float
				.parseFloat(ProjectEntry.getAverage_Covered_Charges().replace('"', ' ').trim().replace("$", "").trim()
						.replace(",", ""))
				+ Float.parseFloat(ProjectEntry.getAverage_Medicare_Payments().replace('"', ' ').trim().replace("$", "")
						.trim().replace(",", ""))
				+ Float.parseFloat(ProjectEntry.getAverage_Total_Payments().replace('"', ' ').trim().replace("$", "")
						.trim().replace(",", ""));
		String ProjectValue = String.valueOf(cost1) + ";;" + ProjectEntry.getHospital_Name() + " "
				+ ProjectEntry.getHospital_Street_Address() + " " + ProjectEntry.getHospital_City() + " "
				+ ProjectEntry.getHospital_State() + " " + ProjectEntry.getHospital_Zip();
		Text mapperKey = new Text(ProjectDisease);
		Text mapperValue = new Text(ProjectValue);
		if (ProjectDisease.contains(edu.tamu.isys.medicare.Project1.s2))
		{
			if (edu.tamu.isys.medicare.Project1.s5) {
				context.write(mapperKey, mapperValue);
			} else if (edu.tamu.isys.medicare.Project1.s6) {
				if(ProjectValue.contains(edu.tamu.isys.medicare.Project1.s1))
				{
					context.write(mapperKey, mapperValue);
				}
			}
		}
	}

}