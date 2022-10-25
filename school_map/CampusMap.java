package hw7;

import hw4.Graph;
import java.io.*;
import java.util.*;
import hw6.MarvelPaths2;



/**
 * CampusMap is a map contains path and distance between different buildings.
 *
 */
public class CampusMap {

	private Graph<Building,Double> map;
	public void CampusMap() {
		this.map = new Graph<Building,Double>();
	}
	/**
	 * @param filename1 node file
	 * @param filename2 edge file
	 * @effect create a Map and save information for given two files.
	 * @return an iterator that all buildings' title in alphabetical order
	 */
	public void createNewMap(String filename1, String filename2) {
		try { 
			this.map = new Graph<Building,Double>();
			MapParser.readfile(filename1,filename2, this.map);
		} catch (IOException e) { 
			e.printStackTrace(); 
		}
	}
	public Iterator<String> listBuildings() {   
		Iterator<Building> itr = map.listNodes();
		ArrayList<String> result = new ArrayList<String>();
		Building tempB;
		while (itr.hasNext()) { 
			tempB = itr.next();
			if (tempB.getTitle().equals("")) {
				continue;  
			}
			String name = tempB.getTitle()+","+tempB.getId();
			result.add(name);
		}
		Collections.sort(result);
		return result.iterator();  
	}
	/**
	 * 
	 * @param building1: start building
	 * @param building2: destination 
	 * @return shortest path between start building and destination
	 * @throws NullPointerException if input contains null
	 */
	public String findShortestPath(String building1,String building2) {
		if(building1==null ||building2==null) {
			throw new NullPointerException("Input cannot be null");
		}
		Iterator<Building> nodeItr = this.map.listNodes();
		Building building;
		String output = "";
		Building Start=null;
		Building End=null;
		boolean flag1 = false;
		boolean flag2 = false;
		while(nodeItr.hasNext()) {
			building = nodeItr.next();
			if(building.equals(building1)) {
				if(!building.getTitle().equals("")) {
					flag1 = true;
					Start = building;
				}
			}
			if((!building2.equals(""))&&building.equals(building2)) {
				if(!building.getTitle().equals("")) {
					flag2 = true;
					End = building;
				}
			}
		}
		
		if(!flag1&&!flag2) {
			if(building1.equals(building2)) {
				output = output.concat("Unknown building: [" +building1+"]");
			}
			else {
				output += "Unknown building: [" +building1+"]\n";
				output += "Unknown building: [" +building2+"]";
			}
			return output;
		}
		
		if(!flag1) {
			output += "Unknown building: [" +building1+"]";
			return output;
		}
		
		if(!flag2) {
			output += "Unknown building: [" +building2+"]";
			return output;
		}
		
		if(building1.equals(building2)) {
			output +="Path from "+ Start.getTitle() + " to " +End.getTitle()+":\n";
			output+= String.format("Total distance: %.3f pixel units.", 0.00);
			return output;
		}
		
		HashMap<Building, Double> Dist = new HashMap<Building, Double>();
		ArrayList<Building> Path = new ArrayList<Building>();
		if(MarvelPaths2.Dijkstra(Path,Dist,this.map,Start,End)) {
			output +="Path from "+ Start.getTitle() + " to " +End.getTitle()+":\n";
			for(int i = Path.size()-1; i > 0; i--) 
			{ 
				
				output += "\tWalk "+Path.get(i).getDirection(Path.get(i-1))+" to (";
				if(Path.get(i-1).getTitle().equals("")) {
					output+="Intersection "+Path.get(i-1).getId()+")\n";
				}else {
					output +=Path.get(i-1).getTitle()+")\n";
				}
				
			}
			
			output += String.format("Total distance: %.3f pixel units.", Dist.get(End));
		}else {
			output += "There is no path from "+Start.getTitle()+" to "+End.getTitle()+".";
		}
		
		
		return output;
	}

}