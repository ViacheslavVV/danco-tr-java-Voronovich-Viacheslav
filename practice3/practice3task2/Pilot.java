public class Pilot extends Employee
{
	private String rank;
	
	public Pilot()
	{
		super(false);
		System.out.println("Pilot");
	}
	
	public String getRank()
	{
		return rank;
	}
	public void setRank(String rank)
	{
		this.rank = rank;
	}
	
	public void takeOfPlain()
	{
		
	}
	public void landPlane()
	{
	
	}
	public void startPlane()
	{
		
	}
	public void stopPlane()
	{
		
	}
}