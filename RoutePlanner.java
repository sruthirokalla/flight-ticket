package wp.routeplanner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class RoutePlanner {
	private static Scanner scanner = new Scanner(System.in);
	
	public static List<List<String>> allFightInformation() 
	{
		String csvFile = "routes.csv";
		
	    String line = "";
	    String cvsSplitBy = ",";
	    List<List<String>> llp = new ArrayList<>();
	    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
	        while ((line = br.readLine()) != null) {
	            llp.add(Arrays.asList(line.split(cvsSplitBy)));
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    System.out.println("******All Available Flights******\n");
	    System.out.format("%1s %15s %18s %14s %18s", "From","To","Flight No","Time","Fair\n\n");
	    for(int m=0;m<llp.size();m++)
		{
	    	for(int n=0;n<llp.get(0).size();n++)
	 		{
	    		System.out.print(llp.get(m).get(n)+"   \t  ");
	 		}
	    	System.out.print("\n");
		}
		
	    return llp;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		List<List<String>> records = new ArrayList<>();
		
        //Sort the flight records.........
		records = allFightInformation();
		
		//All available flight from source city
        int flag = DirectFlights.showDirectFights(records);
        
        
        Set<String> hash_Set = new HashSet<String>(); 
        for(int i=0;i<records.size();i++)
	    {
	    	for(int j=0;j<2;j++)
	    	{
	    		hash_Set.add(records.get(i).get(j));
	    	}
	    }
        
        List<String> list = new ArrayList<String>(hash_Set);
        
        //building a graph for all routes
        int scIndex = 0,dcIndex = 0;
	    int count_1=0;
		CityGraph g = new CityGraph(hash_Set.size());
		for(int i=0;i<records.size();i++)
		{
			for(int j=0;j<2;j++)
			{
				 String city = records.get(i).get(j);
				 for(int k=0;k<list.size();k++)
				 {
					 if(list.get(k).equalsIgnoreCase(city))
					 {
						 if(count_1 == 0)
						 {
							 scIndex = k;
							 count_1++;
						 }
						 else
						 {
							 dcIndex = k;
						 }
					 }
				 }
				
			}
			//System.out.println(scIndex+" "+dcIndex);
			count_1=0;
			 g.addEdge(scIndex, dcIndex);
		}
		
		
		//s=source point  d=destination point
		int s=0 ;
		int d=0 ;
		
		if(flag!=0)
		{
			System.out.println("\n***** All Possible Routes avalible from source to destination******\n");
			System.out.println("enter source city :");
			String sourceCity = scanner.nextLine();
			System.out.println("enter destination city :");
			String destinationCity = scanner.nextLine();
			 for(int k=0;k<list.size();k++)
			 {
				if(list.get(k).equalsIgnoreCase(sourceCity))
				{
					s=k;
					
				}
				if(list.get(k).equalsIgnoreCase(destinationCity))
				{
					d=k;
					
				}
				 
			 }
			
			System.out.println("Following are all different paths from "+ sourceCity + " to " + destinationCity+"\n");
			System.out.format("%1s %15s %18s %14s %18s", "From","To","Flight No","Time","Fair\n\n");
			if(flag!=0)g.printAllPaths(s, d, records,list);
		}
	}

}
