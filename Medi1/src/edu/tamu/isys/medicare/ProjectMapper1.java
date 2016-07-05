package edu.tamu.isys.medicare;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class ProjectMapper1 extends Mapper<LongWritable, Text, Text, Text> {
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String ProjectData = value.toString();
		ProjectAnalyzer1 ProjectEntry = new ProjectAnalyzer1(ProjectData);
		String ProjectDisease = "";
		ProjectDisease = ProjectEntry.getDisease();
		String ProjectValue = "";
		ProjectValue = ProjectEntry.getTotal_Cured() + ";;" + ProjectEntry.getHospital_Name() + " "
				+ ProjectEntry.getHospital_Street_Address() + " " + ProjectEntry.getHospital_City() + " "
				+ ProjectEntry.getHospital_State() + " " + ProjectEntry.getHospital_Zip();
		Text mapperKey = new Text(ProjectDisease);
		Text mapperValue = new Text(ProjectValue);
		if (ProjectDisease.contains(edu.tamu.isys.medicare.Program.s2))
		{
			if (edu.tamu.isys.medicare.Program.s3) {
				context.write(mapperKey, mapperValue);
			} else if (edu.tamu.isys.medicare.Program.s4) {
				if(ProjectValue.contains(edu.tamu.isys.medicare.Program.s1))
				{
					context.write(mapperKey, mapperValue);
				}
			}
		}
	}
}