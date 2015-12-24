package classes;

public class Passenger {

	static private int passengerId = 1;
	private String name;
	private Flight flight;
	private int id;
	
	public Passenger(String name) {
		this.name = name;
		this.id = Passenger.passengerId++;
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

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}
}
