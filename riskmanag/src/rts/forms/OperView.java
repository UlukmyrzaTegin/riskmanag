package rts.forms;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.document.DateTools.Resolution;
import org.vaadin.alump.materialicons.MaterialIcons;
import org.vaadin.dialogs.ConfirmDialog;

import rts.forms.ApplicationFormEditView;

import rts.appUI.MyUI;
import rts.service.ReportService;
import rts.service.RtsService;
import rts.util.ConfirmDialogFactory;
import rts.util.TableView;

import com.github.appreciated.material.MaterialTheme;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.shared.ui.ui.NotificationRole;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
//import com.vaadin.ui.Component;
//import com.vaadin.ui.Calendar;
import com.vaadin.ui.Component.Listener;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.ValoTheme;


public class OperView extends TableView implements Listener {
	private static final long serialVersionUID = -2339856381309773979L;
	private Button findButton, newButton, editButton, reportButton, dowlButton, deleteButton;
	private PropertysetItem item;
	private FieldGroup binder;
	private OptionGroup group;
	private DateField dateFrom, dateTo;
	private Window subWindow;
	private MyUI app;
	private Map<String, Object> filterMap = new HashMap<String, Object>();
	
    public OperView(MyUI app ) {
    	this.app = app;
    	Calendar calendar = Calendar.getInstance();
    	calendar.set(Calendar.DAY_OF_MONTH, 1);
    	item = new PropertysetItem();
    	item.addItemProperty("dateFrom",new ObjectProperty<Date>(calendar.getTime()));
    	item.addItemProperty("dateTo", new ObjectProperty<Date>(new Date()));
     	item.addItemProperty("id", new ObjectProperty<String>(" "));
    	item.addItemProperty("branch_name", new ObjectProperty<String>(" "));
    	item.addItemProperty("subdivision_name", new ObjectProperty<String>(" ")); 
    	item.addItemProperty("department_name", new ObjectProperty<String>(" ")); 
    	item.addItemProperty("section_name", new ObjectProperty<String>(" "));
    	item.addItemProperty("k_name", new ObjectProperty<String>(" "));
    	item.addItemProperty("date_emergence", new ObjectProperty<Date>(new Date()));
    	item.addItemProperty("date_insertion", new ObjectProperty<Date>(new Date())); 
    	item.addItemProperty("t_name", new ObjectProperty<String>(" "));
    	item.addItemProperty("event_name", new ObjectProperty<String>(" "));
    	item.addItemProperty("event_name1", new ObjectProperty<String>(" ")); 
    	item.addItemProperty("sum_losses", new ObjectProperty<String>(" "));
    	item.addItemProperty("sum_income", new ObjectProperty<String>(" "));
    	item.addItemProperty("l_name", new ObjectProperty<String>(" ")); 
    	item.addItemProperty("sum_losses_kgs", new ObjectProperty<String>(" "));
    	item.addItemProperty("date_detected", new ObjectProperty<Date>(new Date()));
    	item.addItemProperty("e_name", new ObjectProperty<String>(" "));
    	item.addItemProperty("bl_name", new ObjectProperty<String>(" ")); 
    	item.addItemProperty("bl_name1", new ObjectProperty<String>(" "));
    	item.addItemProperty("bl_name2", new ObjectProperty<String>(" "));
    	item.addItemProperty("a_name", new ObjectProperty<String>(" "));
    	item.addItemProperty("a_name1", new ObjectProperty<String>(" "));
    	item.addItemProperty("a_name2", new ObjectProperty<String>(" "));
    	item.addItemProperty("p_name1", new ObjectProperty<String>(" "));
    	item.addItemProperty("p_name2", new ObjectProperty<String>(" ")); 
    	item.addItemProperty("event_description", new ObjectProperty<String>(" "));
    	item.addItemProperty("how_detected", new ObjectProperty<String>(" "));
    	item.addItemProperty("s_name", new ObjectProperty<String>(" "));
    	item.addItemProperty("sum_compensation_kgs", new ObjectProperty<String>(" "));
    	item.addItemProperty("date_compensation", new ObjectProperty<Date>(new Date()));
    	item.addItemProperty("comment_compensation", new ObjectProperty<String>(" "));
    	item.addItemProperty("o_name", new ObjectProperty<String>(" ")); 
    	item.addItemProperty("o_podr", new ObjectProperty<String>(" "));
    	item.addItemProperty("decision", new ObjectProperty<String>(" "));
        binder = new FieldGroup(item);
    	
    	propClass = new Object[] { Integer.class,     String.class,   String.class,       String.class,       String.class,    String.class,                  Date.class,            Date.class,        String.class,  String.class,    String.class,             String.class,          String.class,             String.class,             String.class,           Date.class,         String.class,   String.class,                           String.class,                String.class,              String.class,               String.class,  String.class,   String.class,         String.class,          String.class,         String.class,               String.class,                String.class,              Date.class,         String.class,                                 String.class,							   String.class, 							   String.class};
    	visibleColumns = new String[] {"doc_no",     "branch_name",  "subdivision_name",  "department_name",  "section_name",  "k_name",                      "date_emergence",     "date_insertion",  "t_name",        "event_name",     "event_name1",          "sum_losses",          "sum_income",             "l_name",                 "sum_losses_kgs",     "date_detected",     "e_name",       "bl_name",                              " bl_name1",                  "bl_name2",               "a_name",                   "a_name1",     "a_name2",       "p_name1",            "p_name2",             "event_description",  "how_detected",             "s_name",                  "sum_compensation_kgs",     "date_compensation", "comment_compensation",                       "o_name",                  			   "o_podr",    								"decision", };
    	columnHeaders = new String[] {"№ загрузки",  "Филиал",       "Подразделения",     "Отдел",            "Сектор",        "ФИО лица, вносящего записи",  "Дата возникновения", "Дата внесения",   "Тип записи", "Типы событий",   "Тип события/ уровень 2", "Сумма потерь/ ущерба", "Сумма дохода события",   "Валюта потерь/ ущерба",  "Сумма ущерба в KGS",  "Дата обнаружения", "Эффект",       "Бизнес линии (деятельность по банку)", "Наименование Бизнес линии", "Бизнес линии/ уровень 2", "Направление деятельности", "Продукт",     "Операция",     "Процесс/ уровень 1", "Процесс/ уровень 2",  "Описание события",   "Каким образом обнаружено", "Сумма возмещения в валюте", "Сумма возмещения в KGS", "Дата возмещения",    "Комментарии о возмещении, статус события",  "ФИО ответственного лица за возмещение", "Ответственное подразделение за возмещение", "Принятые решения/ Приложения по минимизации риска"};
    	    	    	
    	//top-level component properties
    	setWidth(100, Unit.PERCENTAGE);
    	setHeight(100, Unit.PERCENTAGE);
      	
    	VerticalLayout vLayout = new VerticalLayout();
    	vLayout.setImmediate(false);
    	vLayout.setWidth("100.0%");
    	vLayout.setHeight("100.0%");
    	vLayout.setMargin(false);
 // создания tableLayout
    	VerticalLayout tableLayout = createTableView();
    	vLayout.addComponent(buildFilterPanel());
    	vLayout.addComponent(buildComandPanel());
    	vLayout.addComponent(tableLayout);
    	vLayout.setExpandRatio(tableLayout, 1.0f);
    	setCompositionRoot(vLayout);
	}
    
	@SuppressWarnings("deprecation")
	private Panel buildFilterPanel() {
		// TODO Auto-generated method stub
		Panel panel = new Panel();
		panel.setWidth("200%"); 
		panel.setHeight("60px"); 
		HorizontalLayout hLayout = new HorizontalLayout();
		dateFrom = new DateField("Дата с");
		dateFrom.setDateFormat("dd-MM-yyyy");
		dateFrom.setWidth("150px");
		dateFrom.setHeight("27px");
		dateTo = new DateField("Дата по");
		dateTo.setDateFormat("dd-MM-yyyy");
		dateTo.setWidth("150px");
		dateTo.setHeight("27px");
		binder.bind(dateFrom, "dateFrom");
		binder.bind(dateTo, "dateTo");
		hLayout.setWidth("-1px"); 
		hLayout.setHeight("-1px");
		hLayout.setSpacing(true);
		hLayout.addComponent(dateFrom);
		hLayout.addComponent(dateTo);

///// Поисковик		
		TextField idTF = (TextField) binder.buildAndBind("ID", "id", TextField.class);
		idTF.setHeight("27px");
		idTF.setWidth("80px");
		TextField k_nameTF = (TextField) binder.buildAndBind("ФИО лица, вносящего записи", "k_name", TextField.class);
		k_nameTF.setWidth("200px");
		k_nameTF.setHeight("27px");
		TextField id_branchTF = (TextField) binder.buildAndBind("Филиал", "branch_name", TextField.class);
		id_branchTF.setWidth("150px");
		id_branchTF.setHeight("27px");
		TextField id_typeTF = (TextField) binder.buildAndBind("Тип записи", "t_name", TextField.class);
		id_typeTF.setWidth("150px");
		id_typeTF.setHeight("27px");
		TextField id_eventTF = (TextField) binder.buildAndBind("Тип события", "event_name", TextField.class);
		id_eventTF.setWidth("150px");
		id_eventTF.setHeight("27px");
		TextField id_blTF = (TextField) binder.buildAndBind("Бизнес линии", "bl_name", TextField.class);
		id_blTF.setWidth("150px");
		id_blTF.setHeight("27px");
	
		hLayout.addComponent(idTF);
		hLayout.addComponent(k_nameTF);
		hLayout.addComponent(id_branchTF);
		hLayout.addComponent(id_typeTF);
		hLayout.addComponent(id_eventTF);
		hLayout.addComponent(id_blTF);
		findButton = new Button("Найти запись");
		findButton.addStyleName(MaterialTheme.BUTTON_SMALL);
		findButton.setIcon(FontAwesome.SEARCH);
		findButton.setHeight("27px");
		findButton.addListener(this);	
		hLayout.addComponent(findButton);
		hLayout.setComponentAlignment(findButton, Alignment.BOTTOM_LEFT);
		panel.setContent(hLayout);
		return panel;
	}
	
	private Button buildButton(HorizontalLayout hLayout, String caption) {
		Button button = new Button(caption);
		button.addListener(this);
		hLayout.addComponent(button);
		hLayout.setComponentAlignment(button, Alignment.MIDDLE_CENTER);
		return button;
	}

	private Panel buildComandPanel() {
		Panel panel = new Panel();
		panel.setWidth("100%"); 
		panel.setHeight("-1px"); 
		final HorizontalLayout hLayoutMain = new HorizontalLayout();
		final HorizontalLayout hLayoutCB = new HorizontalLayout();
		hLayoutCB.setWidth("-1px");
		hLayoutCB.setHeight("30px"); //30
		hLayoutCB.setSpacing(true);
		
		newButton = buildButton(hLayoutCB, "Новый");
		newButton.addStyleName(ValoTheme.BUTTON_SMALL + " " + MaterialTheme.BUTTON_FRIENDLY);
		newButton.setIcon(FontAwesome.PLUS);
		editButton = buildButton(hLayoutCB, "Редактировать");
		editButton.addStyleName(ValoTheme.BUTTON_SMALL + " " + MaterialTheme.BUTTON_FRIENDLY);
		editButton.setIcon(FontAwesome.EDIT);
		deleteButton = buildButton(hLayoutCB, "Удалить");
		deleteButton.addStyleName(ValoTheme.BUTTON_SMALL + " " + MaterialTheme.BUTTON_DANGER);
		deleteButton.setIcon(FontAwesome.TRASH_O);
		reportButton = buildButton(hLayoutCB, "Печать");
		reportButton.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		reportButton.setIcon(FontAwesome.PRINT);
		dowlButton = buildButton(hLayoutCB, "Скачать");
		dowlButton.addStyleName(MaterialTheme.BUTTON_BORDERLESS_COLORED);
		dowlButton.setIcon(FontAwesome.DOWNLOAD);
		hLayoutCB.setComponentAlignment(reportButton, Alignment.MIDDLE_RIGHT);
				
		final HorizontalLayout hLayoutRB = new HorizontalLayout();
		hLayoutRB.setSpacing(true);
		hLayoutMain.setWidth("100%"); 
		hLayoutMain.addComponent(hLayoutCB);
		hLayoutMain.addComponent(hLayoutRB);
		hLayoutMain.setComponentAlignment(hLayoutCB, Alignment.MIDDLE_LEFT);
		hLayoutMain.setComponentAlignment(hLayoutRB, Alignment.MIDDLE_RIGHT);
		panel.setContent(hLayoutMain);
		return panel;
	}
	
	@Override
	public void componentEvent(Event event)
	{
		if (event.getClass()== Button.ClickEvent.class) {
			if (event.getSource() == findButton) {
				filterMap.clear();
				filterMap.put("rr.date_insertion >=", new java.sql.Date(((Date) binder.getField("dateFrom").getValue()).getTime()));
				filterMap.put("rr.date_insertion <=", new java.sql.Date(((Date) binder.getField("dateTo").getValue()).getTime()));
				
			/*	if (binder.getField("branch").getValue() != "" && ((String) binder.getField("branch").getValue()).length() !=0) {
					filterMap.put("cast(records.id as varchar) = ", binder.getField("branch").getValue());
				} */
								
				changePage();
				
			} else if (event.getSource() == newButton)
				showSubWindow("newApp");
			else if (event.getSource() == editButton)
				showSubWindow("editApp");
		/*	else if (event.getSource() == reportButton)
				reportAppForm(); */
			else if (event.getSource() == deleteButton)
				deleteAppForm();
		}
		
	}
	
	/*private void reportAppForm() {
		// TODO Auto-generated method stub
		final Object rowId = table.getValue();
		if (table.getItem(rowId).getItemProperty("id").getValue()!= null) {
			ReportService.reportByIdDetailSpravka((Integer) table.getItem(rowId).getItemProperty("id").getValue(),
					app.getDbHelper().getConnectionPool(), app);
		}
		
	}  */

	@Override
	protected void changePage() {
		setNumberOfPages(RtsService.getTotalNumberOfConsolEstim(
				filterMap, app.getDbHelper().getConnectionPool()));
		RtsService.getConsolEstim(table, filterMap,
				(Integer) navigatorItem.getItemProperty("pageSize").getValue(),
				(Integer) navigatorItem.getItemProperty("pageSize").getValue()
				        * (Integer) navigatorItem.getItemProperty("curPage").getValue()
				        - (Integer) navigatorItem.getItemProperty("pageSize").getValue(),
				app.getDbHelper().getConnectionPool());  
		
	}

	private void showSubWindow(String type) {
		if (type == "newApp") {
			subWindow = new Window(" Ввод");
			subWindow.setContent(new ApplicationFormEditView(null, subWindow, app));	
		}
		else {
			Object rowId = table.getValue();
			if (rowId == null)
				return;
			subWindow = new Window("- "
					+ rowId.toString());
			subWindow.setContent(new ApplicationFormEditView((Integer)table.getContainerProperty(rowId, "doc_no").getValue(), subWindow, app)); //doc_no
		}
		subWindow.setHeight("660px"); 
		subWindow.setWidth("895px"); 
		subWindow.setIcon(FontAwesome.HOME);
		subWindow.setResizable(false);
		subWindow.setModal(true);
		getUI().addWindow(subWindow);
	}
	
	@SuppressWarnings("unused")
	private void deleteAppForm() {
		final Object rowId = table.getValue();
		ConfirmDialog.setFactory(ConfirmDialogFactory.getConfirmDialogFactory());
		ConfirmDialog.show(getUI(), "Удалить запись ?", 
				new  ConfirmDialog.Listener() {
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("deprecation")
					@Override
					public void onClose(ConfirmDialog dialog) {
						// TODO Auto-generated method stub
						if (dialog.isConfirmed()) {
							String msString = RtsService.deleteAppForm(
									(Integer) table.getItem(rowId).getItemProperty("id").getValue(),
									app.getDbHelper().getConnectionPool());
							if (msString.length() > 0)
								table.removeItem(rowId);
							Notification.show("Запись удален!","",Notification.TYPE_WARNING_MESSAGE);
						} else {
							
						}
					}
				});
	}
}


