package wp.routeplanner;
import java.util.List;
public class AllAvailableRoutes extends CityGraph {
	public  AllAvailableRoutes(int vertices) {
		super(vertices);
	}
	public static void avalaibleRoutes(List<Integer> localPathList, List<List<String>> llp,List<String> list)
	{
		for(int k=0;k<llp.size();k++)
		{
			for(int i=0;i<localPathList.size();i++)
			{
				int j=i+1;
				if(j<localPathList.size())
				{
					if(llp.get(k).get(0).equalsIgnoreCase(list.get(localPathList.get(i))) && llp.get(k).get(1).equalsIgnoreCase(list.get(localPathList.get(j))))
					{	
						for(int m=0;m<llp.get(k).size();m++)
						{
							System.out.print(llp.get(k).get(m)+"   \t  ");
						}
						System.out.print("\n");
					}
				}	
			}
		}
	}
	
}
