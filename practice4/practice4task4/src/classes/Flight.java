package classes;

import enums.FlightStatus;

public class Flight {
	static private int flightId = 1;
	private String name;
	private int number;
	private int id;
	private Passenger[] passengers;
	private FlightStatus flightStatus;
	private final int MAX_PASSENGERS;
	
	public Flight(String name, int number, int maxPassengers) {
		this.name = name;
		this.number = number;
		MAX_PASSENGERS = maxPassengers;
		passengers = new Passenger[MAX_PASSENGERS];
		flightStatus = FlightStatus.Ok;
		this.id = Flight.flightId++;
	}
	

	public int getMAX_PASSENGERS() {
		return MAX_PASSENGERS;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Passenger[] getPassengers() {
		return passengers;
	}
	public void setPassengers(Passenger[] passengers) {
		this.passengers = passengers;
	}
	public FlightStatus getFlightStatus() {
		return flightStatus;
	}
	public void setFlightStatus(FlightStatus flightStatus) {
		this.flightStatus = flightStatus;
	}

}
