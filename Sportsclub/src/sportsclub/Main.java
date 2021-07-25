package sportsclub;

import java.math.BigDecimal;
import java.util.Map;

public class Main {

	
	public static void main(String[] args) {	
		
		task1();
		task2();
		//task3();
	}
	
	//TASK 1
	private static void task1() {
		
		System.out.println("\n--- TASK 1 ---");
		
		//create trainers
		Member alex = new Trainer("alex", Map.of(Sports.CLIMBING, Level.ADVANCED, Sports.FOOTBALL, Level.PROFESSIONAL, Sports.GOLF, Level.NORMAL));
		Member loredana = new Trainer("lori", Map.of(Sports.ARCHERY, Level.ADVANCED, Sports.HANDBALL, Level.PROFESSIONAL));
		
		//create members		
		Member daniel = new Member("daniel", Map.of(Sports.FOOTBALL, Level.ADVANCED, Sports.HANDBALL, Level.BEGINNER));
		System.out.println("\n" + daniel);
		
		Member ana = new HonoraryMember("ana", Map.of(Sports.ARCHERY, Level.ADVANCED, Sports.DIVING, Level.NORMAL));
		System.out.println("\n" + ana);
		System.out.printf("Honorary members so far: %s %n", HonoraryMember.getCount());
		
		//create sports club
		SportsClub club = new SportsClub("club", BigDecimal.TEN);
		club.addMember(loredana);
		club.addMember(alex);
		club.addMember(ana);
		club.addMember(daniel);
		
		//show sports and members
		System.out.println("\nSports: " + club.getSports());
		System.out.println("\nMembers: " + club.getMembers());
		
		//show members' fee
		System.out.printf("\nAna's fee: %.0f", club.calculateMembershipFee(ana));  //outputs '10' -> no one can teach DIVING
		System.out.printf("\nDaniel's fee: %.0f", club.calculateMembershipFee(daniel));
		
	}
	
	//TASK 2
	private static void task2() {
		
		System.out.println("\n\n--- TASK 2 ---");
		
		Member ana = new HonoraryMember("ana", Map.of(Sports.ARCHERY, Level.ADVANCED, Sports.DIVING, Level.NORMAL));
		System.out.println("\n" + ana.removeSports(Sports.ARCHERY));  //outputs 'true'
		
		Member daniel = new Member("daniel", Map.of(Sports.FOOTBALL, Level.ADVANCED, Sports.HANDBALL, Level.BEGINNER));
		System.out.println(daniel.removeSports(Sports.ARCHERY));  //outputs 'false' -> ARCHERY not learned
		
	}
/*	
	//TASK 3
	private static void task2() {
		
		System.out.println("\n\n--- TASK 3 ---");
		
		//test more here ...
	}
*/
}
