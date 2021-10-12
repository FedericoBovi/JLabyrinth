package jlabyrinth.controller;

import jlabyrinth.controller.ControllerForModel;
import jlabyrinth.controller.IControllerForModel;
import jlabyrinth.model.Model;
import jlabyrinth.view.View;

public class ControllerForModel implements IControllerForModel {
	
	private static ControllerForModel instance = null;

	private ControllerForModel() {
		//to-do
	}
	
	public void exit(){
		System.exit(0);
	}
	
	public void saveAndExit(){
		String file = null;
		file = View.getInstance().saveFile();
		if(file!=null){
			try{
				Model.getInstance().saveModelToFile(file);
				exit();
			}
			catch(java.io.IOException ioe){
				View.getInstance().showCannotWriteFileMessage(file);
			}
		}
	}

	public static IControllerForModel getInstance() {
		if (instance == null)
			instance = new ControllerForModel();
		return instance;

	}
}
