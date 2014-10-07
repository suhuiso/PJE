package controller;

import model.AbstractModel;

public class AppController extends AbstractController {

	public AppController (AbstractModel model) {
		super( model );
	}

	@Override
	void control() {
		// TODO Validation du champ de recherche
		
		this.model.search( this.query );
	}

}
