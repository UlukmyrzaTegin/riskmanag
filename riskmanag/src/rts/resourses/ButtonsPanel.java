package rts.resourses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;

public class ButtonsPanel implements Serializable {
	private static final long serialVersionUID = 8436903885273990386L;
	private Panel panel = new Panel();
	private List<Button> buttons = new ArrayList<Button>();
	private Alignment bAlignment = Alignment.MIDDLE_RIGHT;
	
	public ButtonsPanel() {
	}
	
	public Panel buildButtonsPanel() {
		panel.setImmediate(false);
		panel.setWidth("100%");
		panel.setHeight("-1px");
		int offset = 0;
		if (bAlignment.equals(Alignment.MIDDLE_RIGHT))
			offset = 1;
		GridLayout gridLayout = new GridLayout(buttons.size()+1, 1);
		for (int i = 0; i < buttons.size(); i++) {
			Button button = buttons.get(i);
			gridLayout.addComponent(button, i+offset,0);
			gridLayout.setComponentAlignment(button, Alignment.MIDDLE_CENTER);
		}
		gridLayout.setSpacing(true);
		gridLayout.setWidth("100%");
		gridLayout.setHeight("-1px");
		if (bAlignment.equals(Alignment.MIDDLE_RIGHT)) 
			gridLayout.setColumnExpandRatio(0, 1.0f);
		else
			gridLayout.setColumnExpandRatio(buttons.size(), 1.0f);
		panel.setContent(gridLayout);
		return panel;
	}
	
	public void addButton(Button button) {
		buttons.add(button);
	}

	public List<Button> getButtons() {
		return buttons;
	}

	public void setButtons(List<Button> buttons) {
		this.buttons = buttons;
	}

	public Alignment getbAlignment() {
		return bAlignment;
	}

	public void setbAlignment(Alignment bAlignment) {
		this.bAlignment = bAlignment;
	}
	
}
