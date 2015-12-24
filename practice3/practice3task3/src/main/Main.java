package main;

import assemblyLines.NotebookAsseblyLine;
import interfaces.IAssemblyLine;
import interfaces.IProduct;
import lineSteps.*;
import productParts.Motherboard;
import products.Notebook;

public class Main {

	public static void main(String[] args) {
		
		IAssemblyLine notebookAssemblyLine = new NotebookAsseblyLine(new BodyBuilder(), new MonitorBuilder(), new MotherboardBuilder() );
		IProduct notebook = notebookAssemblyLine.assembleProduct(new Notebook());
	}

}
