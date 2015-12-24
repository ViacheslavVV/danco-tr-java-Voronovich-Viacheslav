public class Flight
{
	private String name;
	private int number;
	private Pilot pilot;
	private AirPlane airPlane;
	
	public Flight(Pilot pilot, AirPlane airPlane)
	{
		this.setPilot(pilot);
		this.setAirPlane(airPlane);
		System.out.println("Flight");
	}
	
	public void setPilot(Pilot pilot)
	{
		this.pilot = pilot;
	}
	
	public void setAirPlane(AirPlane airPlane)
	{
		this.airPlane = airPlane;
	}
	
	public Pilot getPilot()
	{
		return this.pilot;
	}
	
	public AirPlane getAirPlane()
	{
		return this.airPlane;
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getNumber()
	{
		return number;
	}
	public void setNumber(int number)
	{
		this.number = number;
	}
}