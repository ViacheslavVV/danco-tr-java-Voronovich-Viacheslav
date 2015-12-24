package assemblyLines;

import element.Element;
import interfaces.*;

public class NotebookAsseblyLine extends Element 
	implements IAssemblyLine 
	{

	private ILineStep firstBuilder;
	private ILineStep secondBuilder;
	private ILineStep thirdBuilder;
	
	public NotebookAsseblyLine(ILineStep firstStep, ILineStep secondStep, ILineStep thirdStep ) {
		super("NotebookAssemblyLine");
		setFirstBuilder(firstStep); ;
		setSecondBuilder(secondStep);
		setThirdBuilder(thirdStep);
		System.out.println(this.toString()+"has been created.");
	}

	@Override
	public IProduct assembleProduct(IProduct product) {
		product.installFirstPart(firstBuilder.buildProductPart());
		product.installSecondPart(secondBuilder.buildProductPart());
		product.installThirdPart(thirdBuilder.buildProductPart());
		System.out.println(product.toString()+"has been assembled.");
		return product;
	}

	public ILineStep getFirstBuilder() {
		return firstBuilder;
	}

	public void setFirstBuilder(ILineStep firstBuilder) {
		this.firstBuilder = firstBuilder;
	}

	public ILineStep getSecondBuilder() {
		return secondBuilder;
	}

	public void setSecondBuilder(ILineStep secondBuilder) {
		this.secondBuilder = secondBuilder;
	}

	public ILineStep getThirdBuilder() {
		return thirdBuilder;
	}

	public void setThirdBuilder(ILineStep thirdBuilder) {
		this.thirdBuilder = thirdBuilder;
	}

}
