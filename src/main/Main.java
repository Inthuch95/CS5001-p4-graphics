package main;

import guiDelegate.GuiDelegate;
import model.MandelbrotCalculator;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MandelbrotCalculator model = new MandelbrotCalculator();
		// pass the model object to the delegate, so that it can observe, display, and change the model
		GuiDelegate delegate = new GuiDelegate(model); 
	}

}
