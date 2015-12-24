public class Main
{
	public static void main(String[] args)
	{
		Employee employee = new Employee(true);
		Pilot pilot = new Pilot();
		AirPlane airPlane = new AirPlane();
		Flight flight = new Flight(pilot, airPlane);
	}
}