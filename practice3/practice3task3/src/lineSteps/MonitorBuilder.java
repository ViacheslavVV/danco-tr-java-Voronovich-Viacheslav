package lineSteps;

import element.Element;
import interfaces.ILineStep;
import interfaces.IProductPart;
import productParts.Monitor;

public class MonitorBuilder extends Element
	implements ILineStep {

	public MonitorBuilder() {
		super("MonitorBuilder");
		System.out.println(this.toString()+"has been created.");
	}

	@Override
	public IProductPart buildProductPart() {
		return new Monitor();
	}

}
