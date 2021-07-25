package sportsclub;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;



public class Member implements Comparable<Member>, RemovableSports{

	private String name;
	private Map<Sports, Level> sportsMap = new LinkedHashMap<>();
	
	/** CTOR **/
	public Member(String name) {  																													
		
		if(name == null || name.isEmpty())
			throw new IllegalArgumentException("error");
		this.name = name;
	}
	
	/** CTOR **/
	public Member(String name, Map<Sports, Level> sportsLevelMap){  													
		
		this(name);  //call this constructor
		
		if(sportsLevelMap == null || sportsLevelMap.isEmpty())			
			throw new IllegalArgumentException("error");
		
		for(Map.Entry<Sports, Level> entry : sportsLevelMap.entrySet())
		{
			if(entry.getKey() == null || entry.getValue() == null)
				throw new IllegalArgumentException("error");
		}
		
		Map<Sports, Level> cpy = new LinkedHashMap<Sports, Level>(sportsLevelMap);
		this.sportsMap = cpy;
	}
	
	/** GET NAME **/
	public String getName() {  																														
		
		return name;
	}
	
	/** GET SPORTS **/
	public Map<Sports, Level> getSports(){																								
		
		Map<Sports, Level> cpy = new LinkedHashMap<Sports, Level>(sportsMap);
		
		return cpy;
	}
	
	/** GET BILLABLE SPORTS **/
	/* All sports are billable for a normal member */
	public Set<Sports> getBillableSports(){  																					
		
		Set<Sports> s = new LinkedHashSet<Sports>();  
		
		s = this.getSports().keySet();
		
		return s;
	}
	
	/** LEARN A SPORT **/
	public Level learn(Sports newSports, Level newLevel) {																
		
		if(newSports == null || newLevel == null)
			throw new IllegalArgumentException("error");
			
		Level currLevel = sportsMap.get(newSports);  //get lvl of the sport from sports map
		
		if(currLevel == null)
		{
			sportsMap.put(newSports, Level.BEGINNER);
			return Level.BEGINNER;
		}
		
		//Level nextLevel = currLevel.next();
		
		if(newLevel.compareTo(currLevel) >= 1)
		{
			sportsMap.put(newSports, currLevel.next());  //set the lvl to next lvl
			return currLevel.next();
		}

		return currLevel;
	}

	@Override
	public String toString() {																															
		StringBuilder builder = new StringBuilder();
		builder.append("name: ");
		builder.append(name);
		builder.append(", sports: ");
		builder.append(sportsMap);
		return builder.toString();
		//return String.format("name: %s, sports: %s", name, sports);
	}
	
	@Override
	public int compareTo(Member other) {																									
		
		return this.name.compareTo(other.name);  //case sensitive
	}

	@Override
	public boolean equals(Object obj) {	 																									
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {	 																																
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	
//Extention
	
	/** REMOVE PORTS **/
	public boolean removeSports(Sports s) {
		
		if(sportsMap.containsKey(s))
		{
			if(sportsMap.remove(s) != null)
			{
				return true;
			}
		}
		
		return false;
	}
}
