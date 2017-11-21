package main;

import guiDelegate.GuiDelegate;
import model.MandelbrotCalculator;
import model.MandelbrotModel;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MandelbrotModel model = new MandelbrotModel();
		// pass the model object to the delegate, so that it can observe, display, and change the model
		GuiDelegate delegate = new GuiDelegate(model); 
	}

}
