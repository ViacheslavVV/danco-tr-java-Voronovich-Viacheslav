package productParts;

import element.Element;
import interfaces.IProductPart;

public class Body extends Element
	implements IProductPart {
	
	public Body() {
		super("Body");
		System.out.println(this.toString()+"has been created.");
	}

}
