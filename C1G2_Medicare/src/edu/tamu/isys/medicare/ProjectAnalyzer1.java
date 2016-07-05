package edu.tamu.isys.medicare;



import java.text.ParseException;

public class ProjectAnalyzer1 {
             private String Disease = "";
             private String Hospital_Id = "";
             private String Hospital_Name = "";
             private String Hospital_Street_Address = "";
             private String Hospital_City  = "";
             private String Hospital_State = "";
             private String Hospital_Zip = "";
             private String Hospital_County = "";
             private String Total_Cured = "";
             private String Average_Covered_Charges = "";
             private String Average_Total_Payments = "";
             private String Average_Medicare_Payments = "";
             private String error = "";
             public  ProjectAnalyzer1(String ProjectData) {
         		try {
         			parseProjectDetails(ProjectData);
             }catch(Exception e) {
         	error = e.getStackTrace().toString() + "***" + ProjectData;
             }
             }
			private void parseProjectDetails(String ProjectData) throws ParseException{
				// TODO Auto-generated method stub
				String[] ProjectParts = ProjectData.split(";");
				this.Disease = ProjectParts[0].trim();
				this.Hospital_Id = ProjectParts[1].trim();
				this.Hospital_Name = ProjectParts[2].trim();
				this.Hospital_Street_Address = ProjectParts[3].trim();
				this.Hospital_City = ProjectParts[4].trim();
				this.Hospital_State = ProjectParts[5].trim();
				this.Hospital_Zip = ProjectParts[6].trim();
				this.Hospital_County = ProjectParts[7].trim();
				this.Total_Cured = ProjectParts[8].trim();
				this.Average_Covered_Charges = ProjectParts[9].trim();
				this.Average_Total_Payments = ProjectParts[10].trim();
				this.Average_Medicare_Payments = ProjectParts[11].trim();
				
			}
			public String getDisease() {
				return Disease;
			}
			public String Hospital_Id() {
				return Hospital_Id;
			}
			public String getHospital_Name() {
				return Hospital_Name;
			}
			public String getHospital_Street_Address() {
				return Hospital_Street_Address;
			}
			public String getHospital_City() {
				return Hospital_City;
			}
			public String getHospital_State() {
				return Hospital_State;
			}
			public String getHospital_Zip() {
				return Hospital_Zip;
			}
			public String getHospital_County() {
				return Hospital_County;
			}public String getTotal_Cured() {
				return Total_Cured;
			}
			public String getAverage_Covered_Charges() {
				return Average_Covered_Charges;
			}
			public String getAverage_Total_Payments() {
				return Average_Total_Payments;
			}
			public String getAverage_Medicare_Payments() {
				return Average_Medicare_Payments;
			}
             
}
