package com.danco.testclass;
import com.danco.training.annotation.*;

@PrintableObject(name = "Test")
public class TestClass {

	@Printable(order = 2, isDetailedOnly = false, name = "")
	private int Number = 5;
	
	@PrintableRef(order = 1, isDetailedView = false, isRecursive = false)
	private Object object;
}
