package rts.util;

import org.vaadin.alump.materialicons.MaterialIcons;

import com.github.appreciated.material.MaterialTheme;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class CommandButtonsLayout extends CustomComponent {
	private static final long serialVersionUID = -8436903885273990386L;
	private Button cancel, submit;

	public CommandButtonsLayout() {
		HorizontalLayout hLayoutButtons = new HorizontalLayout();
		cancel = new Button("Отмена");
		cancel.setIcon(FontAwesome.MINUS_CIRCLE);
		cancel.addStyleName(ValoTheme.BUTTON_SMALL+ " " +ValoTheme.BUTTON_DANGER);
		cancel.setWidth("100px");
		submit = new Button("Сохранить");
		submit.setIcon(FontAwesome.SAVE);
		submit.addStyleName(ValoTheme.BUTTON_SMALL+ " " +ValoTheme.BUTTON_PRIMARY);
		submit.setWidth("120px");
		hLayoutButtons.addComponent(submit);
		hLayoutButtons.addComponent(cancel);
		hLayoutButtons.setMargin(false);
		hLayoutButtons.setSpacing(true);
		hLayoutButtons.setComponentAlignment(submit, Alignment.MIDDLE_CENTER);
		hLayoutButtons.setComponentAlignment(cancel, Alignment.MIDDLE_CENTER);
		setCompositionRoot(hLayoutButtons);
	}

	public Button getCancel() {
		return cancel;
	}

	public Button getSubmit(){
		return submit;
	}

}
