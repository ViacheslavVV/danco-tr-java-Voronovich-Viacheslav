package dao;

import classes.Flight;

public class FlightManager {

	private Flight[] flights;
	private final int MAX_FLIGHT_NUMBER;
	
	public FlightManager(Flight[] flights) {
		MAX_FLIGHT_NUMBER = flights.length;
		this.flights = flights;
	}

	public int getMAX_FLIGHT_NUMBER() {
		return MAX_FLIGHT_NUMBER;
	}
	
	public void createFlight(Flight flight){
		
		int index = getVocantFlightNumber();
		if (index != -1)
		{
			flights[index] = flight;
		}
	}
	
	public void deleteFlight(Flight flight){
	
		int index = getFlightIndex(flight);
		if (index != -1)
		{
			flights[index] = null;
		}
	}
	
	public void updateFlight(Flight flight){
		
		int index = getFlightIndex(flight);
		if (index != -1)
		{
			flights[index] = flight;
		}
	}
	
	public Flight getFlight(int flightId){
		
		int index = getFlightIndexById(flightId);
		if (index != -1)
		{
			return flights[index];
		}
		return null;
	}
	
	public Flight[] getAllFlights(){
		
		return this.flights;
	}
	
	private int getVocantFlightNumber()	{
		
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
		return getFlightIndexById(flight.getId());
	}
	
	private int getFlightIndexById(int id)
	{
		for (int i=0; i<this.flights.length; i++)
		{
			if (flights[i]!= null && flights[i].getId() == id)
			{
				return i;
			}
		}
		return -1;
	}
}
