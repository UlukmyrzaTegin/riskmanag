package rts.forms;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

import rts.appUI.MyUI;
import rts.tiles.FooterUI;
import rts.tiles.HeaderUI;
import rts.tiles.MainTiles;

public class MainView extends VerticalLayout implements View {
	private static final long serialVersionUID = -8361384254676931917L;
	private MyUI app;
	public MainView(MyUI app) {
		this.app = app;
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		if(getUI().getSession().getAttribute("users_id") == null) { 
			app.getNavigator().navigateTo("");
		}
		else {
			setSizeFull();
			//removeAllComponents();
			MainTiles mainUI = new MainTiles();
			removeAllComponents();
			addComponent(mainUI);
			mainUI.getHeaderLayout().addComponent(new HeaderUI(app.getNavigator()));
			mainUI.getMenuLayout().addComponent(new OperMenu(mainUI,app));
			mainUI.getFooterLayout().addComponent(new FooterUI());			
		}
	}
}
