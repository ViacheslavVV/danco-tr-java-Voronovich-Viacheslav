package classes;

import enums.FlightStatus;
import interfaces.*;

public class FlightManager implements IFlightRegistrar, IFlightStatusChanger {

	private int number;
	private Flight[] flights;
	static final private int MAX_FLIGHT_NUMBER = 20;
	
	public FlightManager(int number) {
		this.setNumber(number);
		flights = new Flight[MAX_FLIGHT_NUMBER];
	}
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public void setOkStatus(Flight flight) {
		flight.setFlightStatus(FlightStatus.Ok);
	}

	@Override
	public void setLateStatus(Flight flight) {
		flight.setFlightStatus(FlightStatus.Late);
	}

	@Override
	public void setCanceledStatus(Flight flight) {
		flight.setFlightStatus(FlightStatus.Canceled);
	}

	@Override
	public void registerPassenger(Flight flight, Passenger passenger) {
		
		flight.addPassenger(passenger);
	}

	@Override
	public void cancelPassengerRegistration(Flight flight, Passenger passenger) {
		
		flight.removePassenger(passenger);
	}

	public Flight[] getFlights() {
		return flights;
	}

	public void setFlights(Flight[] flights) {
		this.flights = flights;
	}
	
	public void addFlight(Flight flight)
	{
		flights[getVocantFlightNumber()] = flight;
	}
	public void removeFlight(Flight flight)
	{
		flights[getFlightIndex(flight)] = null;
	}
	
	private int getVocantFlightNumber()
	{
		for (int i=0; i<flights.length; i++)
		{
			if (flights[i] == null)
			{
				return i;
			}
		}
		return -1;
	}
	
	private int getFlightIndex(Flight flight)
	{
		for (int i=0; i<flights.length; i++)
		{
			if (flights[i].equals(flight))
			{
				return i;
			}
		}
		return -1;
	}

	public void getCanceledAndLateFlightInfo()
	{
		System.out.println("Late and Canceled flights");
		for (Flight flight: flights)
		{
			if (flight != null && !flight.getFlightStatus().equals(FlightStatus.Ok))
				System.out.println(flight);
		}
	}
	

}
