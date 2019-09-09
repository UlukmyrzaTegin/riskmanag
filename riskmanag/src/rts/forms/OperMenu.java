package rts.forms;

import org.vaadin.alump.materialicons.MaterialIcons;

import com.github.appreciated.material.MaterialTheme;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.themes.ValoTheme;

import rts.appUI.MyUI;
import rts.tiles.MainTiles;


@SuppressWarnings("serial")
public class OperMenu extends CustomComponent {

	private AbsoluteLayout mainLayout;
	private MenuBar menuBar;
	private MainTiles mainUI;
	private MyUI app;

	public OperMenu(MainTiles mainUI, MyUI app) {
		this.app = app;
		this.mainUI = mainUI;
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");

		// top level components properties
		setWidth("100.0%");
		setHeight("100.0%");

		// menuBar
		menuBar = new MenuBar();
		menuBar.setImmediate(false);
	//	menuBar.setWidth("100.0%");
	//	menuBar.setHeight("-1px");
		menuBar.setWidth(100, Unit.PERCENTAGE);
		menuBar.setHeight(100, Unit.PERCENTAGE);
		MenuBar.MenuItem docsMenu = menuBar.addItem(" Документы", FontAwesome.ARCHIVE, null);
	//	MenuBar.MenuItem dicsMenu = menuBar.addItem("Отчёты", MaterialIcons.EVENT_NOTE, null);
		MenuBar.Command myCommand = new MenuBar.Command() {
			@Override
			public void menuSelected(MenuItem selectedItem) {
				mainUI.getBodyLayout().removeAllComponents();
				if (selectedItem.getText() == "Документы") {
					mainUI.getBodyLayout().addComponent(new OperView(app));
				}
			}
		};

		docsMenu.addItem("-", null, null);
		docsMenu.addItem("Документы", null, myCommand);
		docsMenu.addItem("-", null, null);
//		dicsMenu.addItem("-", null, null);
//		dicsMenu.addItem("Отчет", null, myCommand);
//		dicsMenu.addItem("-", null,null);
		mainLayout.addComponent(menuBar, "top:0.0px; left:0.0px");
		menuBar.setImmediate(true);
		return mainLayout;
	}
}
