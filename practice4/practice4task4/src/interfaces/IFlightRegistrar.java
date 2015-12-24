package interfaces;

import classes.*;

public interface IFlightRegistrar {

	void registerPassenger(Flight flight, Passenger passenger);
	void cancelPassengerRegistration(Flight flight, Passenger passenger);
	
}
