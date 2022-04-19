package wp.routeplanner;
import java.util.List;
import java.util.Scanner;

public class DirectFlights extends RoutePlanner {
	private static Scanner scanner=new Scanner(System.in);
	
	public static int showDirectFights(List<List<String>> records) 
	{
		
		int flag=0;
		String sourceCity="";
		System.out.println("\n******All Available Flights From Source City******\n");
		System.out.println("Enter the source city :");
		    	
		try {
				sourceCity = scanner.nextLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.format("%1s %15s %18s %14s %18s", "From","To","Flight No","Time","Fair\n\n");
		for(int i=0;i<records.size();i++)
		{
			
			if(records.get(i).get(0).equalsIgnoreCase(sourceCity))
			{
				flag=1;
				for(int j=0;j<records.get(0).size();j++)
				{
					System.out.print(records.get(i).get(j)+"   \t  ");
				}
				System.out.print("\n");
				
			}
		}
		if(flag==0)
		{
			System.out.println("We dont have any flight at this time originiting from "+ sourceCity);
			
		}
		if(flag!=0)SortedFlightRecords.sortTheflightRecords(sourceCity);
		
		return flag;
	}
	
}
