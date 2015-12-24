package classes;

public abstract class AEmployee {

	static private int employeeId = 1;
	protected final int id;
	public int getId() {
		return id;
	}

	protected String name;
	protected double salary;
	protected String post;
	protected Company company;
	
	public AEmployee(String name, double salary, String post) {
		this.name = name;
		this.salary = salary;
		this.post = post;
		this.id = employeeId++;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}


	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	abstract public void doWork();
}
