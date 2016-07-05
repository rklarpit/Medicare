package edu.tamu.isys.baking;

public class Scone {
           private Integer temperature;
           private String status;
           
           public void heatUp(){
        	   temperature += 10;
        	   
        	   if (temperature <= 430) {
        		   status = "Ooey gooey";
        	   }else if (temperature >= 350 && temperature <= 400){
        		   status = "Getting There!";
        	   }else if (temperature >= 400 && temperature >= 450){
        		   status = "GBD (Golden Brown Delicious";
        	   }else if (temperature >= 450 && temperature >= 500){
        		   status = "Mmm mmm good";
        	   }else if (temperature >= 450){
        		   status = "Smokin, I'm burnt";
        	   }
           }
           
           public String getStatus(){
        	   return status;
           }
           
           public String getTemperature(){
        	   return temperature.toString() + " F";
           }
          
}
