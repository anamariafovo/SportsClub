package sportsclub;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;



public class SportsClub {

	private String name;
	private BigDecimal feePerSports;
	private Set<Member> members = new LinkedHashSet<>();
	private Map<Sports, Set<Trainer>> offeredSports = new LinkedHashMap<>();
	
	/** CTOR **/
	public SportsClub(String name, BigDecimal feePerSports) {										
		
		if(name == null || name.isEmpty() || feePerSports == null)
			throw new IllegalArgumentException("error");
		
		this.name = name;
		this.feePerSports = feePerSports;
	}
	
	/** GET NAME **/
	public String getName() {																		
		
		return this.name;
	}
	
	/** GET THE FEE PER SPORTS **/
	public BigDecimal getFeePerSports() {															
		
		return this.feePerSports;
	}
	
	/** GET MEMBERS **/
	public Set<Member> getMembers(){																
		
		Set<Member> cpy = new LinkedHashSet<Member>(members);
		return cpy;
	}
	
	/** GET SPORTS **/
	public Set<Sports> getSports(){																	
		
		Set<Sports> s = new LinkedHashSet<Sports>();
		
		for(Sports val : offeredSports.keySet())
			s.add(val);
		
		return s; 
	}
	
	/** GET MEMBERSHIP FEE OF A PARTICUALR MEMBER **/
	public BigDecimal calculateMembershipFee(Member member) {										
		
		if(members.contains(member) == false)
			throw new IllegalArgumentException("error");
		
		Set<Sports> s = member.getBillableSports();
		BigDecimal fee = new BigDecimal("0");
		
		for(Iterator<Sports> it = s.iterator(); it.hasNext();)
		{
			Sports elem = it.next();		
			if(!offeredSports.containsKey(elem))
				it.remove();
			else 
				fee = fee.add(elem.getFee(this.feePerSports));
		}		
		return fee;	
	}
	
	/** REGISTER SPORTS **/
	public boolean registerSports(Member member, Sports sports, Level level) {					
		
		if(!members.contains(member))
			throw new IllegalArgumentException("error");
		
		boolean help = false;
		
		if(offeredSports.get(sports) != null)
		{
			for(Trainer t : offeredSports.get(sports))
				if(t.getAccreditations().get(sports).compareTo(level) >= 0)  //check if there is an available trainer
					help = true;
		}
		if(help==false)
			return false;
		
		if(member.learn(sports, level).compareTo(level) == 0)  //member learned the sport successfully
				return true;
		
		return false;	
	}
	
	/** ADD MEMBER **/
	public boolean addMember(Member member) {													
		
		if(member == null)
			return false;
		
		if(member instanceof Trainer)
		{
			for(Sports sp : ((Trainer) member).getAccreditations().keySet())
			{
				if(this.offeredSports.containsKey(sp))
					this.offeredSports.get(sp).add((Trainer)member);
				else {
					this.offeredSports.put(sp, new HashSet<>(Arrays.asList((Trainer)member)));
				}
			}
		}
		return this.members.add(member);
	}
	
	/** REMOVE MEMBER **/
	public boolean removeMember(Member member) {													
		
		if(member instanceof Trainer)
		{	
			for(Sports sp : ((Trainer) member).getAccreditations().keySet())
			{
				this.offeredSports.get(sp).remove((Trainer)member);
				if(this.offeredSports.get(sp).isEmpty())
					this.offeredSports.remove(sp);			
			}
		}
		return this.members.remove(member);
	}

	@Override
	public String toString() {																		
		StringBuilder builder = new StringBuilder();
		builder.append("SportsClub[name: ");
		builder.append(name);
		builder.append(", feePerSports: ");
		builder.append(feePerSports);
		builder.append(", offeredSports: ");
		builder.append(offeredSports);
		builder.append("]");
		return builder.toString();
	}
}
