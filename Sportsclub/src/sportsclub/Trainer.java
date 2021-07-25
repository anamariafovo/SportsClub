package sportsclub;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;



public class Trainer extends Member{

	private Map<Sports, Level> accreditations;
	
	/** CTOR **/
	public Trainer(String name, Map<Sports, Level> accreditations) {						
		
		super(name, accreditations);  //super constructor
		
		Map<Sports, Level> cpy = new LinkedHashMap<Sports, Level>(accreditations);
		this.accreditations = cpy;  //shallow copy
	}

	/** GET THE SPORTS THIS TRAINER TEACHES **/
	public Map<Sports, Level> getAccreditations() {												

		Map<Sports, Level> cpy = new LinkedHashMap<Sports, Level>(accreditations);
		return cpy;
	}
	
	@Override
	public Set<Sports> getBillableSports(){
		
		Set<Sports> s = new LinkedHashSet<>();
		s = super.getBillableSports();  //call to super function
		
		for(Iterator<Sports> it = s.iterator(); it.hasNext();)
		{
			Sports entry = it.next();
			if(accreditations.containsKey(entry))
				it.remove();  //remove sports that are in accreditations
		}		
		return s;
	}

	@Override
	public String toString() {																	
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append(", accreditations: ");
		builder.append(accreditations);
		return builder.toString();
	}
	
	
//Extension
	
	/** REMOVE SPORTS **/
	public boolean removeSports(Sports s) {
		
		if(super.removeSports(s))
		{
			accreditations.remove(s);
			return true;
		}
		
		return false;
	}
}
