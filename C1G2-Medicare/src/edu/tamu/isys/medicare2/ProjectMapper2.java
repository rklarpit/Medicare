//mapper for handling cheapest treatment and cheapest treatment in a particular locality

package edu.tamu.isys.medicare2;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class ProjectMapper2 extends Mapper<LongWritable, Text, Text, Text> {
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String ProjectData = value.toString();
		ProjectAnalyzer1 ProjectEntry = new ProjectAnalyzer1(ProjectData);
		String ProjectDisease = "";
		ProjectDisease = "For disease: " + ProjectEntry.getDisease();	//assign selected disease to a variable
		String ProjectCCharge = ProjectEntry.getAverage_Covered_Charges();
		float cost1 = Float
				.parseFloat(ProjectEntry.getAverage_Covered_Charges().replace('"', ' ').trim().replace("$", "").trim()
						.replace(",", ""))
				+ Float.parseFloat(ProjectEntry.getAverage_Medicare_Payments().replace('"', ' ').trim().replace("$", "")
						.trim().replace(",", ""))
				+ Float.parseFloat(ProjectEntry.getAverage_Total_Payments().replace('"', ' ').trim().replace("$", "")
						.trim().replace(",", ""));
		String ProjectValue = String.valueOf(cost1) + ";;" + ProjectEntry.getHospital_Name() + ", at location "
				+ ProjectEntry.getHospital_Street_Address() + " " + ProjectEntry.getHospital_City() + " "
				+ ProjectEntry.getHospital_State() + " " + ProjectEntry.getHospital_Zip();
		//concatenate average cost and hospital address and store it in ProjectValue separated by ";;"
		Text mapperKey = new Text(ProjectDisease);	//set ProjectDisease as mapperKey
		Text mapperValue = new Text(ProjectValue);	//set ProjectValue as mapperValue
		if (ProjectDisease.contains(edu.tamu.isys.medicare2.Program.s2))
		{	
			if (edu.tamu.isys.medicare2.Program.s5) {	//runs when Cheapest Treatment radio button is selected
				context.write(mapperKey, mapperValue);
			} else if (edu.tamu.isys.medicare2.Program.s6) {	//runs when Cheapest Treatment in a locality radio button is selected
				if(ProjectValue.contains(edu.tamu.isys.medicare2.Program.s1))
				{	//filters out the medical centers where city is different from one that is selected on the GUI
					context.write(mapperKey, mapperValue);
				}
			}
		}
	}

}
