package controllers;

import classes.Flight;
import classes.Passenger;
import dao.FlightManager;
import dao.PassengerManager;
import enums.FlightStatus;
import interfaces.IFlightRegistrar;
import interfaces.IFlightStatusChanger;

public class AirportController implements IFlightRegistrar, IFlightStatusChanger{

	
	private Flight[] flights;
	private Passenger[] passengers;
	private FlightManager flightManager;
	private PassengerManager passengerManager;
	
	public AirportController(int maxFlightsNumber, int maxPassengerNumber) {
		
		flights = new Flight[maxFlightsNumber];
		passengers = new Passenger[maxPassengerNumber];
		flightManager = new FlightManager(this.flights);
		passengerManager = new PassengerManager(this.passengers);
	}

	public FlightManager getFlightManager() {
		return flightManager;
	}

	public PassengerManager getPassengerManager() {
		return passengerManager;
	}

	@Override
	public void setOkStatus(Flight flight) {

		Flight currentFlight = flightManager.getFlight(flight.getId());
		if (currentFlight != null)
		{
			flight.setFlightStatus(FlightStatus.Ok);
			flightManager.updateFlight(currentFlight);
		}
		
	}

	@Override
	public void setLateStatus(Flight flight) {

		Flight currentFlight = flightManager.getFlight(flight.getId());
		if (currentFlight != null)
		{
			flight.setFlightStatus(FlightStatus.Late);
			flightManager.updateFlight(currentFlight);
		}
		
	}

	@Override
	public void setCanceledStatus(Flight flight) {
		
		Flight currentFlight = flightManager.getFlight(flight.getId());
		if (currentFlight != null)
		{
			flight.setFlightStatus(FlightStatus.Canceled);
			flightManager.updateFlight(currentFlight);
		}
	}

	@Override
	public void registerPassenger(Flight flight, Passenger passenger) {
		
		if (flightManager.getFlight(flight.getId()) == null)
		{
			return;
		}
		if (passengerManager.getPassenger(passenger.getId()) == null)
		{
			passengerManager.createPassenger(passenger);	
		}
		//?
	}

	@Override
	public void cancelPassengerRegistration(Flight flight, Passenger passenger) {
		// TODO Auto-generated method stub
		
	}

}
