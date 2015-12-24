package lineSteps;

import element.Element;
import interfaces.ILineStep;
import interfaces.IProductPart;
import productParts.Motherboard;

public class MotherboardBuilder extends Element
	implements ILineStep {
	
	public MotherboardBuilder() {
		super("MotherBoardBuilder");
		System.out.println(this.toString()+"has been created.");
	}

	@Override
	public IProductPart buildProductPart() {
		return new Motherboard();
	}

}
