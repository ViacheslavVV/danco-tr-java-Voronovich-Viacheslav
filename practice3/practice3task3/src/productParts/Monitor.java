package productParts;

import element.Element;
import interfaces.IProductPart;

public class Monitor extends Element
	implements IProductPart {
	
	public Monitor() {
		super("Monitor");
		System.out.println(this.toString()+"has been created.");
	}

}
