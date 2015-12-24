package products;

import element.Element;
import interfaces.IProduct;
import interfaces.IProductPart;

public class Notebook extends Element
	implements IProduct {

	private IProductPart firstPart;
	private IProductPart secondPart;
	private IProductPart thirdPart;
	
	public Notebook() {
		super("Notebook");
		System.out.println(this.toString()+"has been created.");
	}
	
	@Override
	public void installFirstPart(IProductPart productPart) {
		setFirstPart(productPart);
		System.out.println(this.firstPart.toString()+ "has been installed.");
	}

	@Override
	public void installSecondPart(IProductPart productPart) {
		setSecondPart(productPart);
		System.out.println(this.secondPart.toString()+ "has been installed.");

	}

	@Override
	public void installThirdPart(IProductPart productPart) {
		setThirdPart(productPart);
		System.out.println(this.thirdPart.toString()+ "has been installed.");

	}

	public IProductPart getFirstPart() {
		return firstPart;
	}

	public void setFirstPart(IProductPart firstPart) {
		this.firstPart = firstPart;
	}

	public IProductPart getSecondPart() {
		return secondPart;
	}

	public void setSecondPart(IProductPart secondPart) {
		this.secondPart = secondPart;
	}

	public IProductPart getThirdPart() {
		return thirdPart;
	}

	public void setThirdPart(IProductPart thirdPart) {
		this.thirdPart = thirdPart;
	}

}
