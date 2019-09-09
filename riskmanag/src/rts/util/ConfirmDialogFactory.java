/**
 * 
 */
package rts.util;

import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.dialogs.DefaultConfirmDialogFactory;
import org.vaadin.dialogs.ConfirmDialog.Factory;

import com.github.appreciated.material.MaterialTheme;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @ TheSusanin
 *
 */
public class ConfirmDialogFactory {

	public static Factory getConfirmDialogFactory() {
		// TODO Auto-generated method stub
		ConfirmDialog.Factory dFactory = new DefaultConfirmDialogFactory() {
			public ConfirmDialog create(String caption, String message, String okCaption, String cancelCaption) {
				ConfirmDialog cDialog =super.create(cancelCaption, caption, message, 
						okCaption, cancelCaption);
				cDialog.setCaption("Пожалуйства подтвердите:");
				cDialog.getCancelButton().setCaption("Нет");
				cDialog.getOkButton().setCaption("Да");
				
				Button ok = cDialog.getOkButton();
			//	ok.addStyleName(MaterialTheme.BUTTON_FRIENDLY);
				HorizontalLayout buttons = (HorizontalLayout) ok.getParent();
				buttons.removeComponent(ok);
				buttons.addComponent(ok, 1);
				buttons.setComponentAlignment(ok, Alignment.MIDDLE_RIGHT);
				
				// Изменение по умолчанию
				Button cancel = cDialog.getCancelButton();
				cancel.setStyleName(MaterialTheme.BUTTON_DANGER);
				ok.removeStyleName(ValoTheme.BUTTON_FRIENDLY);
				cancel.focus();
				return cDialog;
				
			}	
		};
				return dFactory;
	}

}
