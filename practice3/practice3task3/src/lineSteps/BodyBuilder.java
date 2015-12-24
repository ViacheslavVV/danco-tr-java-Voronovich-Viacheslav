package lineSteps;

import element.Element;
import interfaces.ILineStep;
import interfaces.IProductPart;
import productParts.Body;

public class BodyBuilder extends Element 
	implements ILineStep {
	
	public BodyBuilder() {
		super("BodyBuilder");
		System.out.println(this.toString()+"has been created.");
	}

	@Override
	public IProductPart buildProductPart() {
		return new Body();
	}

}
