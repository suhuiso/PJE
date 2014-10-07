package controller;

import model.AbstractModel;

public abstract class AbstractController {
	
	protected AbstractModel model;
	protected String query = "";
	
	public AbstractController ( AbstractModel model ){
		this.model = model;
	}

	public void setQuery ( String query ){
		this.query = query;
		this.control();
	}
	
	abstract void control();
}
