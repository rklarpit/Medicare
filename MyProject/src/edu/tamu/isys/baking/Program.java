package edu.tamu.isys.baking;

public class Program {
    public static void main(String[] args){             
	Scone myScone = new Scone();
                 Scone yourScone = new Scone();
                 
                 int maxTemp = 550;
                 int currentTemp = 0;
                 
                 while (currentTemp < maxTemp) {
                	 myScone.heatUp();
                	 System.out.println(myScone.getStatus());
                	 currentTemp +=10;
                 }
}
}
