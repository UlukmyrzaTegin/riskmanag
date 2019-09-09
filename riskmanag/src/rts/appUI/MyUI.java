package rts.appUI;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import rts.data.DatabaseHelper;
import rts.forms.MainView;
import rts.view.ErrorPageView;
import rts.view.LoginView;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@SuppressWarnings("serial")
public class MyUI extends UI {
	
	private Navigator navigator;
	private DatabaseHelper dbHelper;
	private static final String MainView = "";
	public static final String OperView = "KKB";

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	setSizeFull();
    	
    	// login
    	navigator = new Navigator(this, this);
    	dbHelper = new DatabaseHelper();
       	navigator.addView(MainView, new LoginView(this));
    	navigator.addView(OperView, new MainView(this)); //this
    	navigator.setErrorView(new ErrorPageView());
    	navigator.navigateTo(MainView);
    }
    
    public Navigator getNavigator() {
		return navigator;
	}

	public DatabaseHelper getDbHelper() {
		return dbHelper;
	}

	public void setDbHelper(DatabaseHelper dbHelper) {
		this.dbHelper = dbHelper;
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
