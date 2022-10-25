package hw7;

import hw4.Graph;
import java.io.*;
import java.util.*;



/**
 * MapParser will process data from two files into one graph.
 */
public class MapParser {
	public static void readfile(String nodeF, String edgeF, Graph<Building,Double> map)
	throws IOException{
		BufferedReader nodeReader = new BufferedReader(new FileReader(nodeF));
		String line =null;
		HashMap<Integer,Building > pos = new HashMap<Integer,Building>();
		while((line =nodeReader.readLine())!=null) {
			String[] data = line.split(",");
			String title = data[0];
			int id = Integer.valueOf(data[1]);
			int x =Integer.valueOf(data[2]);
			int y = Integer.valueOf(data[3]);
			Building building = new Building(title,id,x,y);
			map.addNode(building);
			pos.put(id, building);
		}
		
		BufferedReader edgeReader = new BufferedReader(new FileReader(edgeF));
		while((line = edgeReader.readLine())!=null) {
			String[] data = line.split(",");
            int id1 = Integer.valueOf(data[0]);
            int id2 = Integer.valueOf(data[1]);
			Building building1 = pos.get(id1);
			Building building2 = pos.get(id2);
			double dis = Math.sqrt(Math.pow(building1.getY()-building2.getY(),2)+Math.pow(building1.getX()-building2.getX(), 2));
			map.addEdge(building1, building2,dis);
			map.addEdge(building2, building1, dis);
		}
		nodeReader.close();
		edgeReader.close();
	}
}