package sportsclub;

import java.util.Map;

public class HonoraryMember extends Member{
	
	private static int count = 0;
	
	public HonoraryMember(String name) {
		
		super(name);
		count++;  //keeps track of how many honorary members are created
	}
	
	public HonoraryMember(String name, Map<Sports, Level> sportsLevelMap){  				
		
		super(name, sportsLevelMap);
		count++;
	}
	
	public static int getCount() {
		
		return count;
	}
	
	@Override
	public String toString() {																								
		
		return "[Honorary Member] " + super.toString();
	}
}
