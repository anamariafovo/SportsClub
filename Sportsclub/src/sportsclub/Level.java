package sportsclub;

public enum Level {
	
	BEGINNER("Beginner"),
	NORMAL("Normal"),
	ADVANCED("Advanced"),
	PROFESSIONAL("Professional");
	
	String mappedName;
	
	/** CTOR **/	
	private Level(String name) {
		
		if(name == null || name.isEmpty())
			throw new IllegalArgumentException("error");

		mappedName = name;
	}
	
	/** GET THE NAME OF ELEMENT **/	
	public String getMappedName() {
		
		return mappedName;
	}	
	
	/** RETURN NEXT ELEMENT **/	
	public Level next() {
		
		Level[] lvl = Level.values();  //returns array with the enumeration's elements
	
		if(this.ordinal() == Level.values().length-1)
			return lvl[this.ordinal()];
		else
			return lvl[(this.ordinal() + 1)];  //index of elem + 1
	}
	
	
	@Override
	public String toString() {
		
		return this.mappedName;
	}
}
