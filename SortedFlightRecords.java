package wp.routeplanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortedFlightRecords extends RoutePlanner{
	public static void sortTheflightRecords(String sourceCity) 
	{
		String csvFile = "routes.csv";
		
	    String line = "";
	    String name[]=null;
	    String cvsSplitBy = ",";
	    List<List<String>> llp = new ArrayList<>();
	    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
	        while ((line = br.readLine()) != null) { 
	        		name = line.split(cvsSplitBy);
	        		if(name[0].equalsIgnoreCase(sourceCity))
	        			llp.add(Arrays.asList(line.split(cvsSplitBy)));
	        }
	        llp.sort((Comparator<? super List<String>>) new Comparator<List<String>>() {
	            @Override
	            public int compare(List<String> o1, List<String> o2) {
	                return o1.get(1).compareTo(o2.get(1));
	            }
	        });
	        

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    System.out.println("\n******* All flight from source city In sorted manner****\n");
	    System.out.format("%1s %15s %18s %14s %18s", "From","To","Flight No","Time","Fair\n\n");
		for(int i=0;i<llp.size();i++)
		{
				for(int j=0;j<llp.get(0).size();j++)
				{
					
					System.out.print(llp.get(i).get(j)+"   \t  ");
				}
				System.out.print("\n");	
		}
	}
}
