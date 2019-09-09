package rts.view;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
//import java.sql.*;

import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;
import org.vaadin.alump.materialicons.MaterialIcons;

import com.github.appreciated.material.MaterialTheme;

//import org.apache.commons.crypto.Crypto;

import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.UserError;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.PopupView.Content;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
//import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import rts.appUI.MyUI;
import rts.util.Crypto;

public class LoginView extends VerticalLayout implements View {
	private static final long serialVersionUID = -6888545467042277381L;
	private TextField username = new TextField();
	private PasswordField paswd = new PasswordField();
	private Button loginButton;
	private Link hLink;

    public LoginView( final MyUI app) {
    	setSizeFull();

    	//Add vertical layout for TextField
    	VerticalLayout vLayout = new VerticalLayout();

    	// UI Elements
    	username = new TextField("Имя пользователя");
    	username.addStyleName(ValoTheme.LABEL_COLORED);
        username.setIcon(FontAwesome.USER);
    	username.setWidth(100.0f, Unit.PERCENTAGE);
    	username.setValue("******************");
       	vLayout.addComponent(username);

    	paswd = new PasswordField("Пароль");
       	paswd.addStyleName(ValoTheme.LABEL_BOLD);
       	paswd.setIcon(FontAwesome.LOCK);
    	paswd.setWidth(100.0f, Unit.PERCENTAGE);
    	paswd.setValue("1q2w");
    	vLayout.addComponent(paswd);

    	hLink = new Link("Помощь",new ExternalResource("https://*****.***.**"));
		hLink.setTargetName("_blank");
		hLink.setIcon(FontAwesome.QUESTION_CIRCLE);
    	hLink.setDescription("Руководство пользователя"); 
    	
    	// add horizontal layout for buttons and Link
    	HorizontalLayout hLayout = new HorizontalLayout();

    	//UI Elements - Buttons
    	loginButton = new Button("Войти",
    			new Button.ClickListener() {
    	private static final long serialVersionUID = -5514448801678437295L;

    	    @Override
			public void buttonClick(ClickEvent event) {
				JDBCConnectionPool connectionPool = app.getDbHelper().getConnectionPool();
				Connection conn = null;
				System.out.println(Crypto.getEncodedStringAlg1(username.getValue(), paswd.getValue()));
				try {
					conn = connectionPool.reserveConnection();
					conn.setAutoCommit(false);
					CallableStatement proc = conn.prepareCall(
							"SELECT " +
									"   usr.* " +
									" FROM " +
									"   public.users usr " +
									"WHERE " +
									"   username = ? and password = ?");
					proc.setString(1, username.getValue());
					//proc.setString(2, Crypto.getEncodedStringAlg1(username.getValue(), paswd.getValue()));
					proc.setString(2, paswd.getValue());
					ResultSet rs = proc.executeQuery();
					if (rs != null && rs.next()) {
						UI.getCurrent().getSession().setAttribute("users_id", rs.getInt("id"));
						UI.getCurrent().getSession().setAttribute("users_firstname", rs.getString("firstname"));
						UI.getCurrent().getSession().setAttribute("users_surname", rs.getString("surname"));
						UI.getCurrent().getSession().setAttribute("users_role", rs.getInt("role"));
						if (rs.getInt("role")==1){
		            		app.getNavigator().navigateTo(MyUI.OperView);
		            	}
			            else
							Notification.show("Ошибка:", "<br/> Не достаточно прав для входа в систему, обращайтесь в ОИТ", Notification.Type.ERROR_MESSAGE);
		                    username.setValue("");
		                    paswd.setValue("");
		            }
		            else
						Notification.show("Ошибка:", "Имя пользователя или пароль не верно", Notification.Type.ERROR_MESSAGE);
		          conn.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		            connectionPool.releaseConnection(conn);
		        }
             }
        });

    	hLayout.addComponent(loginButton);
    	hLayout.setSpacing(true);
    	loginButton.addStyleName(MaterialTheme.BUTTON_CUSTOM + " " + MaterialTheme.BUTTON_PRIMARY);
    	loginButton.setIcon(FontAwesome.SIGN_OUT);
    	loginButton.setDescription("Для осуществления входа в систему, введите имя пользователя и пароль.");

    	// hLink for Help
    	hLayout.addComponent(hLink);
        hLayout.setSpacing(true);

        // Form Layout
    	FormLayout form = new FormLayout(username, paswd, hLayout, vLayout);
    	form.setMargin(true);

    	// Panel
    	Panel loginPanel = new Panel(" <center><b> Процессс регистрации операционных потерь </b></center> ", form);
    	loginPanel.addStyleName(ValoTheme.PANEL_WELL+" "+ ValoTheme.LABEL_COLORED);
    	loginPanel.addStyleName(MaterialTheme.UPLOAD_FLOATING_ACTION);
    	loginPanel.setWidth("450");
    
    	// add Components
    	addComponent(loginPanel);
    	setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
    	setHeight("100%");
 	}

	@Override
	public void enter(ViewChangeEvent event) {

	}
    	}
