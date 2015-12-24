package dao;

import classes.Passenger;

public class PassengerManager {

	private Passenger[] passengers;
	private final int MAX_PASSENGER_NUMBER;
	
	public PassengerManager(Passenger[] passengers) {
		MAX_PASSENGER_NUMBER = passengers.length;
		this.passengers = passengers;
	}

	public int getMAX_PASSENGER_NUMBER() {
		return MAX_PASSENGER_NUMBER;
	}
		
	public void createPassenger(Passenger passenger){
		
		int index = getVocantPassengerNumber();
		if (index != -1)
		{
			passengers[index] = passenger;
		}
	}
	
	public void deletePassenger(Passenger passenger){
	
		int index = getPassengerIndex(passenger);
		if (index != -1)
		{
			passengers[index] = null;
		}
	}
	
	public void updatePassenger(Passenger passenger){
		
		int index = getPassengerIndex(passenger);
		if (index != -1)
		{
			passengers[index] = passenger;
		}
	}
	
	public Passenger getPassenger(int passengerId){
		
		int index = getPassengerIndexById(passengerId);
		if (index != -1)
		{
			return passengers[index];
		}
		return null;
	}
	
	public Passenger[] getAllPassengers(){
		
		return this.passengers;
	}
	
	private int getVocantPassengerNumber()	{
		
		for (int i=0; i<passengers.length; i++)
		{
			if (passengers[i] == null)
			{
				return i;
			}
		}
		return -1;
	}
	
	private int getPassengerIndex(Passenger passenger)
	{
		return getPassengerIndexById(passenger.getId());
	}
	
	private int getPassengerIndexById(int id)
	{
		for (int i=0; i<this.passengers.length; i++)
		{
			if (passengers[i]!= null && passengers[i].getId() == id)
			{
				return i;
			}
		}
		return -1;
	}
}
