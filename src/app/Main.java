package app;

import view.AppViewTmp;
import model.*;
import controller.*;

public class Main {
	
	public static void main(String[] args) {
		AppModel search = new AppModel();
		AppController controller = new AppController( search );
		AppViewTmp searchView = new AppViewTmp( controller );
		
		search.addObserver( searchView );
	}	
}
