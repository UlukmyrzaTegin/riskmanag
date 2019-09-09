package rts.forms;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.github.appreciated.material.MaterialTheme;
import com.vaadin.data.Validator;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.MethodProperty;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.colorpicker.Color;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component.Listener;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import rts.appUI.MyUI;
import rts.domain.Activities;
import rts.domain.Activities_lvl1;
import rts.domain.Activities_lvl2;
import rts.domain.ApplicationForm;
import rts.domain.Branch;
import rts.domain.Business_line;
import rts.domain.Business_line_lvl1;
import rts.domain.Business_line_lvl2;
import rts.domain.Department;
import rts.domain.Effect;
import rts.domain.Event_lvl1;
import rts.domain.Events;
import rts.domain.LossCurrency;
import rts.domain.Process_lvl1;
import rts.domain.Process_lvl2;
import rts.domain.Records;
import rts.domain.Section;
import rts.domain.Subdivision;
import rts.domain.SumVal;
import rts.domain.TypeZapisi;
import rts.service.ActivitiesService;
import rts.service.Activities_lvl1Service;
import rts.service.Activities_lvl2Service;
import rts.service.BranchService;
import rts.service.Business_lineService;
import rts.service.Business_line_lvl1Service;
import rts.service.Business_line_lvl2Service;
//import rts.service.DepartmentService;
import rts.service.EffectService;
import rts.service.Event_lvl1Service;
import rts.service.EventsService;
import rts.service.LossCurrencyService;
import rts.service.Process_lvl1Service;
import rts.service.Process_lvl2Service;
import rts.service.RtsService;
import rts.service.SBranchService;
//import rts.service.SectionService;
//import rts.service.SubdivisionService;
import rts.service.SumValService;
import rts.service.TypeZapisiService;
import rts.util.CommandButtonsLayout;
import rts.util.EnhancedFieldGroupFactory;
import rts.util.ImmrdiateUpload;

public class ApplicationFormEditView extends CustomComponent implements Listener {
	private static final long serialVersionUID = 6256540909821472692L;
	private ApplicationForm applicationForm;
	private Button submit, cancel, dButton;
	private FieldGroup binder;
	private Window subWindow;
	private MyUI app;
	private ComboBox cBoxBranch, cBoxDep, cBoxSec, cBoxSubdivision, cBoxBusLine, cBoxBusLine1, cBoxBusLine2, cBoxEvent, cBoxEvent1, cBoxLoss, cBoxType, cBoxVal, cBoxAct, cBoxAct1, cBoxAct2, cBoxPro1, cBoxPro2, cBoxEff;
	private ArrayList<Branch> branch;
	private ArrayList<Subdivision> subdivisions;
	private ArrayList<Department> departments;
	private ArrayList<Section> sections;
	private ArrayList<Events> events;
	private ArrayList<Event_lvl1> event_lvl1s;
	private ArrayList<Business_line> business_lines;
	private ArrayList<Business_line_lvl1> business_line_lvl1s;
	private ArrayList<Business_line_lvl2> business_line_lvl2s;
	private ArrayList<Process_lvl1> process_lvl1s;
	private ArrayList<Process_lvl2> process_lvl2s;
	private ArrayList<TypeZapisi> typeZapisis;
	private ArrayList<LossCurrency> lossCurrencies;
	private ArrayList<Activities> activities;
	private ArrayList<Activities_lvl1> activities_lvl1s;
	private ArrayList<Activities_lvl2> activities_lvl2s;
	private ArrayList<SumVal> sumVals;
	private ArrayList<Effect> effects;
	private ImmrdiateUpload files;

	public ApplicationFormEditView(Integer id, Window sWindow, MyUI app) {
		this.app = app;
		subWindow = sWindow;

		branch = BranchService.getBranch(app.getDbHelper().getConnectionPool());
	//	subdivisions = SubdivisionService.getSubdivision(app.getDbHelper().getConnectionPool());
	//	departments = DepartmentService.getDepartment(app.getDbHelper().getConnectionPool());
	//	sections = SectionService.getSection(app.getDbHelper().getConnectionPool());
		subdivisions = SBranchService.getSubdivision(app.getDbHelper().getConnectionPool());
		departments = SBranchService.getDepartment(app.getDbHelper().getConnectionPool());
		sections = SBranchService.getSection(app.getDbHelper().getConnectionPool());
		events = EventsService.getEvent(app.getDbHelper().getConnectionPool());
		event_lvl1s = Event_lvl1Service.getEvent_lvl1(app.getDbHelper().getConnectionPool());
		business_lines = Business_lineService.getBusiness_line(app.getDbHelper().getConnectionPool());
		business_line_lvl1s = Business_line_lvl1Service.getBusiness_line_lvl1(app.getDbHelper().getConnectionPool());
		business_line_lvl2s = Business_line_lvl2Service.getBusiness_line_lvl2(app.getDbHelper().getConnectionPool());
		process_lvl1s = Process_lvl1Service.getProcess_lvl1(app.getDbHelper().getConnectionPool());
		process_lvl2s = Process_lvl2Service.getProcess_lvl2(app.getDbHelper().getConnectionPool());
		typeZapisis = TypeZapisiService.getTypeZapisi(app.getDbHelper().getConnectionPool());
		lossCurrencies = LossCurrencyService.getLossCurrency(app.getDbHelper().getConnectionPool());
		activities = ActivitiesService.getActivities(app.getDbHelper().getConnectionPool());
		activities_lvl1s = Activities_lvl1Service.getActivities_lvl1(app.getDbHelper().getConnectionPool());
		activities_lvl2s = Activities_lvl2Service.getActivities_lvl2(app.getDbHelper().getConnectionPool());
		sumVals = SumValService.getSumVal(app.getDbHelper().getConnectionPool());
		effects = EffectService.getEffect(app.getDbHelper().getConnectionPool());
		//Integer num = id;
	    if (id == null)
			applicationForm = new ApplicationForm();
		else
			applicationForm = RtsService.getApplicationFormById(id, app.getDbHelper());

		BeanItem<ApplicationForm> agrItem = new BeanItem<ApplicationForm>(applicationForm);
		agrItem.addItemProperty("id_record", new MethodProperty<Records>(applicationForm.getRecords(), "id_record"));
		agrItem.addItemProperty("id_branch", new MethodProperty<Records>(applicationForm.getRecords(), "id_branch"));
		agrItem.addItemProperty("id_subdivision", new MethodProperty<Records>(applicationForm.getRecords(), "id_subdivision"));
		agrItem.addItemProperty("id_department", new MethodProperty<Records>(applicationForm.getRecords(), "id_department"));
		agrItem.addItemProperty("id_section", new MethodProperty<Records>(applicationForm.getRecords(), "id_section"));
		agrItem.addItemProperty("date_emergence", new MethodProperty<Records>(applicationForm.getRecords(), "date_emergence"));
		agrItem.addItemProperty("date_insertion", new MethodProperty<Records>(applicationForm.getRecords(), "date_insertion"));
		agrItem.addItemProperty("id_type", new MethodProperty<Records>(applicationForm.getRecords(), "id_type"));
		agrItem.addItemProperty("id_event", new MethodProperty<Records>(applicationForm.getRecords(), "id_event"));
		agrItem.addItemProperty("id_event1", new MethodProperty<Records>(applicationForm.getRecords(), "id_event1"));
		agrItem.addItemProperty("sum_losses", new MethodProperty<Records>(applicationForm.getRecords(), "sum_losses"));
		agrItem.addItemProperty("sum_income", new MethodProperty<Records>(applicationForm.getRecords(), "sum_income"));
		agrItem.addItemProperty("id_loss", new MethodProperty<Records>(applicationForm.getRecords(), "id_loss"));
		agrItem.addItemProperty("sum_losses_kgs", new MethodProperty<Records>(applicationForm.getRecords(), "sum_losses_kgs"));
		agrItem.addItemProperty("date_detected", new MethodProperty<Records>(applicationForm.getRecords(), "date_detected"));
		agrItem.addItemProperty("id_effect", new MethodProperty<Records>(applicationForm.getRecords(), "id_effect"));
		agrItem.addItemProperty("id_bl", new MethodProperty<Records>(applicationForm.getRecords(), "id_bl"));
		agrItem.addItemProperty("id_bl1", new MethodProperty<Records>(applicationForm.getRecords(), "id_bl1"));
		agrItem.addItemProperty("id_bl2", new MethodProperty<Records>(applicationForm.getRecords(), "id_bl2"));
		agrItem.addItemProperty("id_activities", new MethodProperty<Records>(applicationForm.getRecords(), "id_activities"));
		agrItem.addItemProperty("id_activities1", new MethodProperty<Records>(applicationForm.getRecords(), "id_activities1"));
		agrItem.addItemProperty("id_activities2", new MethodProperty<Records>(applicationForm.getRecords(), "id_activities2"));
		agrItem.addItemProperty("id_process1", new MethodProperty<Records>(applicationForm.getRecords(), "id_process1"));
		agrItem.addItemProperty("id_process2", new MethodProperty<Records>(applicationForm.getRecords(), "id_process2"));
		agrItem.addItemProperty("event_description", new MethodProperty<Records>(applicationForm.getRecords(), "event_description"));
		agrItem.addItemProperty("how_detected", new MethodProperty<Records>(applicationForm.getRecords(), "how_detected"));
		agrItem.addItemProperty("id_sumVal", new MethodProperty<Records>(applicationForm.getRecords(), "id_sumVal"));
		agrItem.addItemProperty("sum_compensation_kgs", new MethodProperty<Records>(applicationForm.getRecords(), "sum_compensation_kgs"));
		agrItem.addItemProperty("date_compensation", new MethodProperty<Records>(applicationForm.getRecords(), "date_compensation"));
		agrItem.addItemProperty("comment_compensation", new MethodProperty<Records>(applicationForm.getRecords(), "comment_compensation"));
		agrItem.addItemProperty("o_name", new MethodProperty<Records>(applicationForm.getRecords(), "o_name"));
		agrItem.addItemProperty("o_podr", new MethodProperty<Records>(applicationForm.getRecords(), "o_podr"));
		agrItem.addItemProperty("k_name", new MethodProperty<Records>(applicationForm.getRecords(), "k_name"));
		agrItem.addItemProperty("decision", new MethodProperty<Records>(applicationForm.getRecords(), "decision"));
		agrItem.addItemProperty("id_branch_regist", new MethodProperty<Records>(applicationForm.getRecords(), "id_branch_regist"));
		agrItem.addItemProperty("id_subdivision_regist", new MethodProperty<Records>(applicationForm.getRecords(), "id_subdivision_regist"));
		agrItem.addItemProperty("id_department_regist", new MethodProperty<Records>(applicationForm.getRecords(), "id_department_regist"));
		agrItem.addItemProperty("id_section_regist", new MethodProperty<Records>(applicationForm.getRecords(), "id_section_regist"));
		binder = new FieldGroup(agrItem);
		binder.setBuffered(false);
		binder.setFieldFactory(new EnhancedFieldGroupFactory());
		setSizeFull();
		setCompositionRoot(buildMainLayout());
	}

	public void componentEvent(Event event) {
		if(event.getClass() == Button.ClickEvent.class) {
			if(event.getSource() == submit) {
				String errMsg = RtsService.saveApplicationForm(applicationForm, app.getDbHelper().getConnectionPool());
				if (errMsg.isEmpty()) {
					Notification.show("Успешно сохранено", Notification.Type.TRAY_NOTIFICATION);
					this.getUI().removeWindow(subWindow);
					}
				else
					Notification.show("Ошибка", errMsg, Notification.Type.WARNING_MESSAGE);
			}
			else if(event.getSource() == cancel)
				this.getUI().removeWindow(subWindow);
		}
	/*	else if(event.getClass() == ComboBox.ValueChangeEvent.class) {
			if (event.getSource() == cBoxBranch) {
				CreatSubdivisionList();
			} else if (event.getSource() == cBoxSubdivision) {
				CreatDepartmentList();
			} else if (event.getSource() == cBoxDep) {
				CreatSectionList();
			}
		}
		*/
	}

	private AbsoluteLayout buildMainLayout() {
		// TODO Auto-generated method stub
		AbsoluteLayout mainLayout = new AbsoluteLayout();
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.setHeight("620px");
		vLayout.setWidth("885px");

		TabSheet tab = new TabSheet();
		tab.setHeight("100%");
		tab.setWidth("100%");

		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");

		tab.addTab(createTabFilesLayout(), "Файлы", FontAwesome.FILES_O);
		tab.addStyleName(ValoTheme.TABSHEET_FRAMED);
		vLayout.addComponent(tab);
		vLayout.setExpandRatio(tab, 1.0f);
		CommandButtonsLayout hLayoutButtons = new CommandButtonsLayout();
		cancel = hLayoutButtons.getCancel();
		cancel.addListener(this);
		submit = hLayoutButtons.getSubmit();
		submit.addListener(this);
		vLayout.addComponent(hLayoutButtons);
		hLayoutButtons.setWidth("-1px");
		vLayout.setComponentAlignment(hLayoutButtons, Alignment.BOTTOM_RIGHT);
		mainLayout.addComponent(vLayout, "top:5.0px; left:5.Opx");
		submit.setVisible(true);
		return mainLayout;
	}

	@SuppressWarnings("rawtypes")
	private void buildAndBind(Layout layout, String caption, Object propertyId, Class fieldType) {
		if (fieldType == DateField.class) {
			@SuppressWarnings("unchecked")
			DateField field = (DateField) binder.buildAndBind(caption, propertyId, fieldType);
			field.setDateFormat("dd-MM-yyyy");
			layout.addComponent(field);
		}
		else
			if(fieldType == CheckBox.class)
			{
				CheckBox field = (CheckBox) binder.buildAndBind(caption, propertyId);
				layout.addComponent(field);
			}
			else
			{
				AbstractTextField field = (AbstractTextField) binder.buildAndBind(caption, propertyId);
				field.setWidth("300px");
				field.setNullRepresentation(" ");
				layout.addComponent(field);
			}
	}

	    private FormLayout createTabFilesLayout() {
		FormLayout tabLayout = new FormLayout();
		
////// добавим материалы
		files = new ImmrdiateUpload(applicationForm.getAttachments());
		files.setCaption("Прикрепление файлы:");
		tabLayout.addComponent(files);
//// Branch
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setWidth("-1px");
		hLayout.setHeight("-1px");
		hLayout.setSpacing(true);
		cBoxBranch = new ComboBox();
		cBoxBranch.setInputPrompt("Филиал");
		cBoxBranch.setWidth("250px");
		cBoxBranch.setHeight("33px");
		cBoxBranch.setImmediate(true);
		cBoxBranch.setNullSelectionAllowed(false);
		cBoxBranch.setInvalidAllowed(false);
		Iterator<Branch> bran = branch.iterator();
		while(bran.hasNext()) {
			Branch rt = bran.next();
			cBoxBranch.addItem(rt);
			cBoxBranch.setItemCaption(rt, rt.getBranch_name().trim());
			if (applicationForm.getId() != null) {
				if (rt.getId_branch().equals(applicationForm.getRecords().getId_branch_regist().getId_branch()))
				{
					applicationForm.getRecords().setId_branch(rt);
				}
			}
		}
		hLayout.addComponent(cBoxBranch);
		binder.bind(cBoxBranch, "id_branch");
		tabLayout.addComponent(hLayout);

////// Subdivision - Подразделения
		HorizontalLayout hLayout1 = new HorizontalLayout();
		cBoxSubdivision = new ComboBox();
		cBoxSubdivision.setInputPrompt("Подразделения");
		cBoxSubdivision.setWidth("250px");
		cBoxSubdivision.setHeight("33px");
		cBoxSubdivision.setImmediate(true);
		cBoxSubdivision.setNullSelectionAllowed(false);
		cBoxSubdivision.setInvalidAllowed(false);
	/*	CreatSubdivisionList();
		tabLayout.addComponent(cBoxSubdivision);
		binder.bind(cBoxSubdivision, "id_subdivision");
		cBoxSubdivision.addListener(this);
		*/

		Iterator<Subdivision> sub = subdivisions.iterator();
		while (sub.hasNext()) {
			Subdivision rt = sub.next();
			cBoxSubdivision.addItem(rt);
			cBoxSubdivision.setItemCaption(rt, rt.getSubdivision().trim());;
			if (applicationForm.getId() != null) {
				if (rt.getId_subdivision().equals(applicationForm.getRecords().getId_subdivision_regist().getId_subdivision())) {
					applicationForm.getRecords().setId_subdivision(rt);
				}

			}
		}

		hLayout1.addComponent(cBoxSubdivision);
		binder.bind(cBoxSubdivision, "id_subdivision");
		tabLayout.addComponent(hLayout1);

//// Отдел department
		HorizontalLayout hLayout2 = new HorizontalLayout();
		cBoxDep = new ComboBox();
		cBoxDep.setInputPrompt("Отдел");
		cBoxDep.setWidth("300px");
		cBoxDep.setHeight("33px");
		cBoxDep.setImmediate(true);
		cBoxDep.setNullSelectionAllowed(false);
	/*	CreatDepartmentList();
		tabLayout.addComponent(cBoxDep);
		binder.bind(cBoxDep, "id_department");
		cBoxDep.addListener(this); */

		Iterator<Department> dep = departments.iterator();
		while (dep.hasNext()) {
			Department rt = dep.next();
			cBoxDep.addItem(rt);
			cBoxDep.setItemCaption(rt, rt.getDepartment_name().trim());
			if(applicationForm.getId() != null) {
				if(rt.getId_department().equals(applicationForm.getRecords().getId_department_regist().getId_department())) {
					applicationForm.getRecords().setId_department(rt);
				}
			}
		}
		hLayout2.addComponent(cBoxDep);
		binder.bind(cBoxDep, "id_department");
		tabLayout.addComponent(hLayout2);

/// Подотдел section
		HorizontalLayout hLayout3 = new HorizontalLayout();
		cBoxSec = new ComboBox();
		cBoxSec.setInputPrompt("Сектор");
		cBoxSec.setWidth("300px");
		cBoxSec.setHeight("33px");
		cBoxSec.setImmediate(true);
		cBoxSec.setNullSelectionAllowed(false);
	/*	CreatSectionList();
		tabLayout.addComponent(cBoxSec);
		cBoxSec.addListener(this); */

		Iterator<Section> sec = sections.iterator();
		while(sec.hasNext()) {
			Section rt = sec.next();
			cBoxSec.addItem(rt);
			cBoxSec.setItemCaption(rt, rt.getSection_name().trim());
			if(applicationForm.getId()!=null) {
				if(rt.getId_section().equals(applicationForm.getRecords().getId_section_regist().getId_section())){
					applicationForm.getRecords().setId_section(rt);
				}
			}
		}
		hLayout3.addComponent(cBoxSec);
		binder.bind(cBoxSec, "id_section");
		tabLayout.addComponent(hLayout3);


/// ФИО лица, вносящего записи
		HorizontalLayout hLayout4 = new HorizontalLayout();
		hLayout4.setWidth("-1px");
		hLayout4.setHeight("-1px");
		hLayout4.setSpacing(true);
		buildAndBind(hLayout4, "ФИО лица, вносящего записи", "k_name", TextField.class);
		tabLayout.addComponent(hLayout4);

//// Дата возникновения	//// Дата внесения события в БД
		HorizontalLayout hLayout5 = new HorizontalLayout();
		hLayout5.setWidth("-1px");
		hLayout5.setHeight("-1px");
		hLayout5.setSpacing(true);
		buildAndBind(hLayout5, "Дата возникновения", "date_emergence", DateField.class);
		buildAndBind(hLayout5, "Дата внесения ", "date_insertion", DateField.class);
		tabLayout.addComponent(hLayout5);

//// Тип записи
		HorizontalLayout hLayout6 = new HorizontalLayout();
		cBoxType = new ComboBox();
		cBoxType.setWidth("250px");
		cBoxType.setHeight("33px");
		cBoxType.setImmediate(true);
		cBoxType.setNewItemsAllowed(false);
		cBoxType.setInputPrompt("Тип записи");
		Iterator<TypeZapisi> type = typeZapisis.iterator();
		while (type.hasNext()) {
			TypeZapisi rt = type.next();
			cBoxType.addItem(rt);
			cBoxType.setItemCaption(rt, rt.getName().trim());
			if(applicationForm.getId() != null) {
				if (rt.getId().equals(applicationForm.getRecords().getId_type().getId())) {
					applicationForm.getRecords().setId_type(rt);
				}
			}
		}
		hLayout6.addComponent(cBoxType);
		binder.bind(cBoxType, "id_type");
		tabLayout.addComponent(hLayout6);

///// Events - событие
		HorizontalLayout hLayout7 = new HorizontalLayout();
		cBoxEvent = new ComboBox();
		cBoxEvent.setInputPrompt("Типы событий");
		cBoxEvent.setWidth("300px");
		cBoxEvent.setHeight("33px");
		cBoxEvent.setImmediate(true);
		cBoxEvent.setNullSelectionAllowed(false);
		cBoxEvent.setInvalidAllowed(false); // new add
		Iterator<Events> event = events.iterator();
		while (event.hasNext())
		{
			Events rt = event.next();
			cBoxEvent.addItem(rt);
			cBoxEvent.setItemCaption(rt, rt.getEvent_name().trim());
			if (applicationForm.getId() != null)
			{
				if (rt.getId_event().equals(applicationForm.getRecords().getId_event().getId_event()))
				{
					applicationForm.getRecords().setId_event(rt);
				}
			}
		}
		hLayout7.addComponent(cBoxEvent);
		binder.bind(cBoxEvent, "id_event");
		cBoxEvent.addListener(this);
		tabLayout.addComponent(hLayout7);

////// Event1 - Событие 2
		HorizontalLayout hLayout8 = new HorizontalLayout();
		cBoxEvent1 = new ComboBox(); // Событие уровень 2 => event1
		cBoxEvent1.setInputPrompt("Тип события/ уровень 2");
		cBoxEvent1.setWidth("300px");
		cBoxEvent1.setHeight("33px");
		cBoxEvent1.setImmediate(true);
		cBoxEvent1.setNullSelectionAllowed(false);
		cBoxEvent1.setInvalidAllowed(false);
	//	CreatEvent_lvl1List();
	//	binder.bind(cBoxEvent1, "id_event1");
	//	tabLayout.addComponent(hLayout8);
		cBoxEvent1.addListener(this);
		Iterator<Event_lvl1> event1 = event_lvl1s.iterator();
		while (event1.hasNext())
		{
			Event_lvl1 rt = event1.next();
			cBoxEvent1.addItem(rt);
			cBoxEvent1.setItemCaption(rt, rt.getEvent_name().trim());
			if (applicationForm.getId() != null)
			{
				if (rt.getId_event().equals(applicationForm.getRecords().getId_event1().getId_event()))
				{
					applicationForm.getRecords().setId_event1(rt);
				}
			}
		}
		hLayout8.addComponent(cBoxEvent1);
		binder.bind(cBoxEvent1, "id_event1");
		tabLayout.addComponent(hLayout8);

///// Сумма потерь/ущерба
		HorizontalLayout hLayout9 = new HorizontalLayout();
		hLayout9.setWidth("-1px");
		hLayout9.setHeight("-3px");
		hLayout9.setSpacing(true);
		buildAndBind(hLayout9, "Сумма потерь/ущерба", "sum_losses", TextArea.class);
		tabLayout.addComponent(hLayout9);

//// Сумма дохода события
		HorizontalLayout hLayout10 = new HorizontalLayout();
		hLayout10.setWidth("-1px");
		hLayout10.setHeight("-1px");
		hLayout10.setSpacing(true);
		buildAndBind(hLayout10, "Сумма дохода события", "sum_income", String.class);
		tabLayout.addComponent(hLayout10);

///// Валюта потерь/ущерба
		HorizontalLayout hLayout11 = new HorizontalLayout();
		cBoxLoss = new ComboBox();
		cBoxLoss.setInputPrompt("Валюта потерь/ущерба");
		cBoxLoss.setWidth("250px");
		cBoxLoss.setHeight("33px");
		cBoxLoss.setImmediate(true);
		cBoxLoss.setNullSelectionAllowed(false);
		Iterator<LossCurrency> loss = lossCurrencies.iterator();
		while (loss.hasNext()) {
			LossCurrency rt = loss.next();
			cBoxLoss.addItem(rt);
			cBoxLoss.setItemCaption(rt, rt.getName().trim());
			if (applicationForm.getId() != null)
			{
				if (rt.getId().equals(applicationForm.getRecords().getId_loss().getId()))
				{
					applicationForm.getRecords().setId_loss(rt);
				}
			}
		}
		hLayout11.addComponent(cBoxLoss);
		binder.bind(cBoxLoss, "id_loss");
		tabLayout.addComponent(hLayout11);


/// Сумма ущерба в KGS
		HorizontalLayout hLayout12 = new HorizontalLayout();
		hLayout12.setWidth("-1px");
		hLayout12.setHeight("-1px");
		hLayout12.setSpacing(true);
		buildAndBind(hLayout12, "Сумма ущерба в KGS", "sum_losses_kgs", String.class);
		tabLayout.addComponent(hLayout12);

/// Дата обнаружения
		HorizontalLayout hLayout13 = new HorizontalLayout();
		hLayout13.setWidth("-1px");
		hLayout13.setHeight("-1px");
		hLayout13.setSpacing(true);
		buildAndBind(hLayout13, "Дата обнаружения", "date_detected", DateField.class);
		tabLayout.addComponent(hLayout13);

///// Доход / расход
		HorizontalLayout hLayout14 = new HorizontalLayout();
		cBoxEff = new ComboBox();
		cBoxEff.setInputPrompt("Эффект");
		cBoxEff.setWidth("250px");
		cBoxEff.setHeight("33px");
		cBoxEff.setImmediate(true);
		cBoxEff.setNullSelectionAllowed(false);
		Iterator<Effect> eff = effects.iterator();
		while(eff.hasNext()) {
			Effect rt = eff.next();
			cBoxEff.addItem(rt);
			cBoxEff.setItemCaption(rt, rt.getName().trim());
			if(applicationForm.getId() != null) {
				if (rt.getId().equals(applicationForm.getRecords().getId_effect().getId())) {
					applicationForm.getRecords().setId_effect(rt);
				}
			}
		}
		hLayout14.addComponent(cBoxEff);
		binder.bind(cBoxEff, "id_effect");
		tabLayout.addComponent(hLayout14);


////// Бизнес линия
		HorizontalLayout hLayout15 = new HorizontalLayout();
		cBoxBusLine = new ComboBox();
		cBoxBusLine.setInputPrompt("Бизнес линии (деятельность по банку)");
		cBoxBusLine.setWidth("350px");
		cBoxBusLine.setHeight("33px");
		cBoxBusLine.setImmediate(true);
		cBoxBusLine.setNullSelectionAllowed(false);
		Iterator<Business_line> busL = business_lines.iterator();
		while (busL.hasNext()) {
			Business_line rt = busL.next();
			cBoxBusLine.addItem(rt);
			cBoxBusLine.setItemCaption(rt, rt.getBl_name().trim());
			if (applicationForm.getId() != null)
			{
				if (rt.getId_bl().equals(applicationForm.getRecords().getId_bl().getId_bl()))
				{
					applicationForm.getRecords().setId_bl(rt);
				}
			}
		}
		hLayout15.addComponent(cBoxBusLine);
		binder.bind(cBoxBusLine, "id_bl");
		tabLayout.addComponent(hLayout15);

//// Бизнес линия lvl1
		HorizontalLayout hLayout16 = new HorizontalLayout();
		cBoxBusLine1 = new ComboBox();
		cBoxBusLine1.setInputPrompt("Наименование Бизнес линии");
		cBoxBusLine1.setWidth("300px");
		cBoxBusLine1.setHeight("33px");
		cBoxBusLine1.setImmediate(true);
		cBoxBusLine1.setNullSelectionAllowed(false);
		Iterator<Business_line_lvl1> busL1 = business_line_lvl1s.iterator();
		while (busL1.hasNext()) {
			Business_line_lvl1 rt = busL1.next();
			cBoxBusLine1.addItem(rt);
			cBoxBusLine1.setItemCaption(rt, rt.getBl_name().trim());
			if(applicationForm.getId() != null) {
				if (rt.getId_bl1().equals(applicationForm.getRecords().getId_bl1().getId_bl())) {
					applicationForm.getRecords().setId_bl1(rt);
				}
			}
		}
		hLayout16.addComponent(cBoxBusLine1);
		binder.bind(cBoxBusLine1, "id_bl1");
		tabLayout.addComponent(hLayout16);

///// Бизнес линии lvl2
		HorizontalLayout hLayout17 = new HorizontalLayout();
		cBoxBusLine2 = new ComboBox();
		cBoxBusLine2.setInputPrompt("Бизнес линии/ уровень 2");
		cBoxBusLine2.setWidth("300px");
		cBoxBusLine2.setHeight("33px");
		cBoxBusLine2.setImmediate(true);
		cBoxBusLine2.setNullSelectionAllowed(false);
		Iterator<Business_line_lvl2> bushL2 = business_line_lvl2s.iterator();
		while (bushL2.hasNext()) {
			Business_line_lvl2 rt = bushL2.next();
			cBoxBusLine2.addItem(rt);
			cBoxBusLine2.setItemCaption(rt, rt.getBl_name().trim());
			if (applicationForm.getId()!= null) {
				if (rt.getId_bl2().equals(applicationForm.getRecords().getId_bl2().getId_bl2())) {
					applicationForm.getRecords().setId_bl2(rt);
				}
			}
		}
		hLayout17.addComponent(cBoxBusLine2);
		binder.bind(cBoxBusLine2, "id_bl2");
		tabLayout.addComponent(hLayout17);  
		

//// Направления деятельности activities
		HorizontalLayout hLayout18 = new HorizontalLayout();
		cBoxAct = new ComboBox();
		cBoxAct.setInputPrompt("Направления деятельности");
		cBoxAct.setWidth("300px");
		cBoxAct.setHeight("33px");
		cBoxAct.setImmediate(true);
		cBoxAct.setNullSelectionAllowed(false);
		Iterator<Activities> act = activities.iterator();
		while (act.hasNext()) {
			Activities rt = act.next();
			cBoxAct.addItem(rt);
			cBoxAct.setItemCaption(rt, rt.getA_name().trim());
			if (applicationForm.getId()!= null) {
				if(rt.getId().equals(applicationForm.getRecords().getId_activities().getId())) {
					applicationForm.getRecords().setId_activities(rt);
				}
			}
		}
		hLayout18.addComponent(cBoxAct);
		binder.bind(cBoxAct, "id_activities");
		tabLayout.addComponent(hLayout18);

//// Операция act1
		HorizontalLayout hLayout19 = new HorizontalLayout();
		cBoxAct1 = new ComboBox();
		cBoxAct1.setWidth("300px");
		cBoxAct1.setHeight("33px");
		cBoxAct1.setImmediate(true);
		cBoxAct1.setNullSelectionAllowed(false);
		cBoxAct1.setInputPrompt("Продукт");
		Iterator<Activities_lvl1> act1 = activities_lvl1s.iterator();
		while (act1.hasNext()) {
			Activities_lvl1 rt = act1.next();
			cBoxAct1.addItem(rt);
			cBoxAct1.setItemCaption(rt, rt.getA_name1().trim());
			if(applicationForm.getId()!= null) {
				if(rt.getId().equals(applicationForm.getRecords().getId_activities1().getId())) {
					applicationForm.getRecords().setId_activities1(rt);
				}
			}
		}
		hLayout19.addComponent(cBoxAct1);
		binder.bind(cBoxAct1, "id_activities1");
		tabLayout.addComponent(hLayout19);

//// Система act2
		HorizontalLayout hLayout20 = new HorizontalLayout();
		cBoxAct2 = new ComboBox();
		cBoxAct2.setInputPrompt("Операция");
		cBoxAct2.setWidth("300px");
		cBoxAct2.setHeight("33px");
		cBoxAct2.setImmediate(true);
		cBoxAct2.setNullSelectionAllowed(false);
		Iterator<Activities_lvl2> act2 = activities_lvl2s.iterator();
		while (act2.hasNext()) {
			Activities_lvl2 rt = act2.next();
			cBoxAct2.addItem(rt);
			cBoxAct2.setItemCaption(rt, rt.getA_name2().trim());
			if (applicationForm.getId() != null) {
				if (rt.getId().equals(applicationForm.getRecords().getId_activities2().getId())) {
					applicationForm.getRecords().setId_activities2(rt);
				}
			}
		}
		hLayout20.addComponent(cBoxAct2);
		binder.bind(cBoxAct2, "id_activities2");
		tabLayout.addComponent(hLayout20);

///// Процесс process1
		HorizontalLayout hLayout21 = new HorizontalLayout();
		cBoxPro1 = new ComboBox();
		cBoxPro1.setInputPrompt("Процесс/ уровень1");
		cBoxPro1.setWidth("300px");
		cBoxPro1.setHeight("33px");
		cBoxPro1.setImmediate(true);
		cBoxPro1.setNullSelectionAllowed(false);
		Iterator<Process_lvl1> pro1 = process_lvl1s.iterator();
		while (pro1.hasNext()) {
			Process_lvl1 rt = pro1.next();
			cBoxPro1.addItem(rt);
			cBoxPro1.setItemCaption(rt, rt.getName().trim());
			if(applicationForm.getId() != null) {
				if(rt.getId().equals(applicationForm.getRecords().getId_process1().getId())) {
					applicationForm.getRecords().setId_process1(rt);
				}
			}
		}
		hLayout21.addComponent(cBoxPro1);
		binder.bind(cBoxPro1, "id_process1");
		tabLayout.addComponent(hLayout21);

///// Процесс process 2
		HorizontalLayout hLayout22 = new HorizontalLayout();
		cBoxPro2 = new ComboBox();
		cBoxPro2.setInputPrompt("Процесс/ уровень2");
		cBoxPro2.setWidth("300px");
		cBoxPro2.setHeight("33px");
		cBoxPro2.setImmediate(true);
		cBoxPro2.setNullSelectionAllowed(false);
		Iterator<Process_lvl2> pro2 = process_lvl2s.iterator();
		while (pro2.hasNext()) {
			Process_lvl2 rt = pro2.next();
			cBoxPro2.addItem(rt);
			cBoxPro2.setItemCaption(rt, rt.getName().trim());
			if(applicationForm.getId() != null) {
				if(rt.getId().equals(applicationForm.getRecords().getId_process2().getId())) {
					applicationForm.getRecords().setId_process2(rt);
				}
			}
		}
		hLayout22.addComponent(cBoxPro2);
		binder.bind(cBoxPro2, "id_process2");
		tabLayout.addComponent(hLayout22);

//// Описание события
		HorizontalLayout hLayout23 = new HorizontalLayout();
		hLayout23.setWidth("-1px");
		hLayout23.setHeight("-1px");
		hLayout23.setSpacing(true);
		buildAndBind(hLayout23, "Описание события", "event_description", TextField.class);
		tabLayout.addComponent(hLayout23);

//// Каким образом обнаружено
		HorizontalLayout hLayout24 = new HorizontalLayout();
		hLayout24.setWidth("-1px");
		hLayout24.setHeight("-1px");
		hLayout24.setSpacing(true);
		buildAndBind(hLayout24, "Каким образом обнаружено", "how_detected", TextField.class);
		tabLayout.addComponent(hLayout24);

//// Сумма возмещение в валюте
		HorizontalLayout hLayout25 = new HorizontalLayout();
		cBoxVal = new ComboBox();
		cBoxVal.setInputPrompt("Сумма возмещения в валюте");
		cBoxVal.setWidth("300px");
		cBoxVal.setHeight("33px");
		cBoxVal.setImmediate(true);
		cBoxVal.setNullSelectionAllowed(false);
		Iterator<SumVal> sumV = sumVals.iterator();
		while (sumV.hasNext()) {
			SumVal rt  = sumV.next();
			cBoxVal.addItem(rt);
			cBoxVal.setItemCaption(rt, rt.getName().trim());
			if (applicationForm.getId()!= null) {
				if (rt.getId().equals(applicationForm.getRecords().getId_sumVal().getId())) {
					applicationForm.getRecords().setId_sumVal(rt);
					}
				}
			}
		hLayout25.addComponent(cBoxVal);
		binder.bind(cBoxVal, "id_sumVal");
		tabLayout.addComponent(hLayout25);

//// Сумма возмещения
		HorizontalLayout hLayout26 = new HorizontalLayout();
		hLayout26.setWidth("-1px");
		hLayout26.setHeight("-1px");
		hLayout26.setSpacing(true);
		buildAndBind(hLayout26, "Сумма возмещения в KGS", "sum_compensation_kgs", TextField.class);
		tabLayout.addComponent(hLayout26);

//// Дата возмещения
		HorizontalLayout hLayout27 = new HorizontalLayout();
		hLayout27.setWidth("-1px");
		hLayout27.setHeight("-1px");
		hLayout27.setSpacing(true);
		buildAndBind(hLayout27, "Дата возмещения", "date_compensation", DateField.class);
		tabLayout.addComponent(hLayout27);

//// Комментарии о возмещении, статус события
		HorizontalLayout hLayout28 = new HorizontalLayout();
		hLayout28.setWidth("-1px");
		hLayout28.setHeight("-1px");
		hLayout28.setSpacing(true);
		buildAndBind(hLayout28, "Комментарии о возмещении, статус события", "comment_compensation", TextArea.class);
		tabLayout.addComponents(hLayout28);

//// Ответственное лицо и подразделение за возмещение
		HorizontalLayout hLayout29 = new HorizontalLayout();
		hLayout29.setWidth("-1px");
		hLayout29.setHeight("-1px");
		hLayout29.setSpacing(true);
		buildAndBind(hLayout29, "ФИО ответственного лица за возмещение", "o_name", TextArea.class);
		tabLayout.addComponents(hLayout29);

		HorizontalLayout hLayout30 = new HorizontalLayout();
		hLayout30.setWidth("-1px");
		hLayout30.setHeight("-1px");
		hLayout30.setSpacing(true);
		buildAndBind(hLayout30, "Ответственное подразделение за возмещение ", "o_podr", TextArea.class);
		tabLayout.addComponent(hLayout30);

//// Принятые решение / приложение по минизации риска
		HorizontalLayout textField31 = new HorizontalLayout();
		textField31.setWidth("300px");
		textField31.setHeight("40px");
		textField31.setSpacing(true); 
		buildAndBind(textField31, "Принятые решения/Приложения по минимизации риска", "decision", TextArea.class);
		tabLayout.addComponent(textField31);

		return tabLayout;
	    }	

//// Subdivision
/*	private void CreatSubdivisionList()
	{
		if (applicationForm.getRecords().getId_branch()!=null)
		{
			subdivisions = SBranchService.getSubdivisionById(applicationForm.getRecords().getId_branch_regist().getId_branch(), app.getDbHelper().getConnectionPool());
			cBoxSubdivision.removeAllItems();
			Iterator<Subdivision> subd = subdivisions.iterator();
			while (subd.hasNext())
			{
				Subdivision rt = subd.next();
				cBoxSubdivision.addItem(rt);
				cBoxSubdivision.setItemCaption(rt, rt.getSubdivision().trim());
				if(applicationForm.getId() !=null)
				{
					if (rt.getId_subdivision().equals(applicationForm.getRecords().getId_subdivision().getId_subdivision()))
					{
						applicationForm.getRecords().setId_subdivision(rt);
						}
					}
				}
			}
		}

////Department
	private void CreatDepartmentList() {
	if(applicationForm.getRecords().getId_subdivision_regist()!=null) {
		departments = SBranchService.getDepartmentById(applicationForm.getRecords().getId_subdivision_regist().getId_subdivision(), app.getDbHelper().getConnectionPool());
		cBoxDep.removeAllItems();
		Iterator<Department> dep = departments.iterator();
		while(dep.hasNext()) {
			Department rt = dep.next();
			cBoxDep.addItem(rt);
			cBoxDep.setItemCaption(rt, rt.getDepartment_name().trim());
			if(applicationForm.getId()!=null) {
				if(rt.getId_department().equals(applicationForm.getRecords().getId_department().getId_department())) {
					applicationForm.getRecords().setId_department(rt);
					}
				}
			}
		}
	}


	private void CreatSectionList() {
//	if(applicationForm.getRecords().getId_department()!=null) {
	if(applicationForm.getRecords().getId_department() != null) {
		sections = SBranchService.getSectionById(applicationForm.getRecords().getId_section_regist().getId_section(), app.getDbHelper().getConnectionPool());
		cBoxSec.removeAllItems();
		Iterator<Section> sec = sections.iterator();
		while(sec.hasNext()) {
			Section rt = sec.next();
			cBoxSec.addItem(rt);
			cBoxSec.setItemCaption(rt, rt.getSection_name().trim());
			if(applicationForm.getId()!=null) {
				if(rt.getId_section().equals(applicationForm.getRecords().getId_section().getId_section())) {
					applicationForm.getRecords().setId_section(rt);
					}
				}
			}
		}
	}
	*/

}
