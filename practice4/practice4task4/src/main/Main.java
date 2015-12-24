package main;

import classes.Flight;
import classes.Passenger;
import dao.FlightManager;

public class Main {

	public static void main(String[] args) {
		
		FlightManager flightManager = new FlightManager(1);
		Passenger passenger = new Passenger("Ivan Ivanov");
		Passenger passenger2 = new Passenger("Sasha Ivanov");
		Passenger passenger3 = new Passenger("Ira Ivanova");
		Passenger passenger4 = new Passenger("Egor Ivanov");
		Passenger passenger5 = new Passenger("Natasha Ivanova");
		Flight flight = new Flight("Grodno - Minsk", 454, 30);
		Flight flight2 = new Flight("Minsk - Grodno", 121, 36);
		flightManager.addFlight(flight);
		flightManager.addFlight(flight2);
		
		flightManager.registerPassenger(flight, passenger3);
		flightManager.registerPassenger(flight, passenger2);
		flightManager.registerPassenger(flight, passenger);
		System.out.println(passenger3);
		System.out.println(flight.toString());
		System.out.println(flight2.toString());
		
		flightManager.registerPassenger(flight2, passenger5);
		flightManager.registerPassenger(flight2, passenger4);
		flightManager.setCanceledStatus(flight);
		flightManager.cancelPassengerRegistration(flight, passenger3);
		System.out.println(passenger3);
		System.out.println(passenger4);
		System.out.println(passenger5);
		System.out.println(flight2.toString());
		System.out.println(flight.toString());
		
		flightManager.cancelPassengerRegistration(flight2, passenger4);
		flightManager.registerPassenger(flight, passenger3);
		flightManager.setLateStatus(flight);
		System.out.println(passenger3);
		System.out.println(passenger3);
		System.out.println(flight2.toString());
		System.out.println(flight.toString());
		
		flightManager.getCanceledAndLateFlightInfo();
		flightManager.setCanceledStatus(flight2);
		flightManager.getCanceledAndLateFlightInfo();
		
	}

}
