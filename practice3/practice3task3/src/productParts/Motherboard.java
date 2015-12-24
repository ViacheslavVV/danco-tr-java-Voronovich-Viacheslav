package productParts;

import element.Element;
import interfaces.IProductPart;

public class Motherboard extends Element
	implements IProductPart {
	
	public Motherboard() {
		super("Motherboard");
		System.out.println(this.toString()+"has been created.");
	}
}
