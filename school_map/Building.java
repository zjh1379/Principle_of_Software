package hw7;
/**
 * Building has x,y coordinate, title and id.
 */
public class Building implements Comparable<Building> {
	private String title;
	private int id;
	private int x;
	private int y;
	/**
	 * @effects Constructer
	 */
	public Building() {
		this.x =0;
		this.y= 0;
		this.title ="";
		this.id =0;
	}
	/**
	 * 
	 * @param tempTitle
	 * @param tempID
	 * @param tempX
	 * @param tempY
	 * @effects Constructs a new building object
	 */
	public Building(String tempTitle, int tempID, int tempX, int tempY) {
		this.title = tempTitle;
		this.id = tempID;
		this.x = tempX;
		this.y = tempY;
	}
	public String getTitle(){
		return this.title;
	}
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	public int getId(){
		return this.id;
	}
	/**
	 * @return true if two building are the same
	 */
	
	public boolean equals(String bu) {
		if(this.title.equals(bu)||Integer.toString(this.id).equals(bu)) {
			return true;
		}
		return false;
	}
	@Override
	public int compareTo(Building bu) {
		return this.id-bu.getId();
	}
	@Override 
	public int hashCode()
	{
	    return id;  
	}
	/**
	 * 
	 * @param building: destination building
	 * @return the direction from start building
	 */
	public String getDirection(Building building){
		double angle;
		double dist = Math.sqrt(Math.pow(this.y-building.getY(),2)+Math.pow(building.getX()-this.x, 2));
		
		if (x > building.getX()) { 
			angle = Math.toDegrees(Math.acos((building.getY()-this.y)/dist));
            if (0 <= angle && angle < 22.5) 
                return "South";
            else if (22.5 <= angle && angle < 67.5)
                return "SouthWest";
            else if (67.5 <= angle && angle < 112.5)
                return "West";
            else if (112.5 <= angle && angle < 157.5)
                return "NorthWest";
            else 
                return "North"; 
        }
        else {
        	angle = Math.toDegrees(Math.acos((y-building.getY())/dist));
            if (0 <= angle && angle < 22.5) 
                return "North";
            else if (22.5 <= angle && angle < 67.5)
                return "NorthEast"; 
            else if (67.5 <= angle && angle < 112.5)
                return "East";
            else if (112.5 <= angle && angle < 157.5)
                return "SouthEast";
            else   
                return "South";
        } 
	}

}