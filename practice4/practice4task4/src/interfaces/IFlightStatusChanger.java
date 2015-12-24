package interfaces;

import classes.Flight;

public interface IFlightStatusChanger {

	void setOkStatus(Flight flight);
	void setLateStatus(Flight flight);
	void setCanceledStatus(Flight flight);
}
