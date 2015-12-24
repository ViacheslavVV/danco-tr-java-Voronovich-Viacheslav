package element;

public class Element {

	private String Name;
	
	public Element()
	{
		
	}
	
	protected Element(String name)
	{
		this.setName(name);
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
	
	@Override
	public String toString() {
		return this.Name + " ";
	}
	
}
