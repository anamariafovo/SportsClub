package sportsclub;

import java.math.BigDecimal;


public enum Sports {

	ARCHERY,
	BASKETBALL,
	CLIMBING {
		
		@Override
		public BigDecimal getFeeFactor() {
			
			BigDecimal fee = new BigDecimal("1.2");
			return fee;
		}
	},
	DIVING {
		
		@Override
		public BigDecimal getFeeFactor() {
			
			BigDecimal fee = new BigDecimal("1.8");
			return fee;
		}
	},
	FOOTBALL,
	GOLF {
		@Override
		public BigDecimal getFeeFactor() {
			
			BigDecimal fee = new BigDecimal("2.1");
			return fee;
		}
	},
	HANDBALL,
	HOCKEY,
	MOUNTAINBIKING,
	PARKOUR;
	
	/** DEFAULT FEE FACTOR **/	
	/* Diving, Golf and Climbing have a different fee */	
	public BigDecimal getFeeFactor() {
		
		BigDecimal fee = new BigDecimal("1");
		return fee;
	}
	
	/** GET THE TOTAL FEE **/	
	public BigDecimal getFee(BigDecimal feePerSports) {
		
		BigDecimal fee = feePerSports.multiply(this.getFeeFactor());
		return fee;
	}
}
