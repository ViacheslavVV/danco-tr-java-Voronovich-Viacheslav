public class Employee implements IWorker
{
	private String name;
	private String experience;
	public Employee(boolean isFather)
	{
		if (isFather)
		System.out.println("Employee");
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getExperience()
	{
		return experience;
	}
	public void setExperience(String experience)
	{
		this.experience = experience;
	}
	@Override
	public void doWork()
	{
		
	}
}