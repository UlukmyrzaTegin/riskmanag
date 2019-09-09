package rts.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class ApplicationForm implements Serializable {
	private static final long serialVersionUID = 8561191071142952885L;
	private Integer id;
	private Activities activities;
	private Activities_lvl1 activities_lvl1;
	private Activities_lvl2 activities_lvl2;
	private Branch branch;
	private Business_line business_line;
	private Business_line_lvl1 business_line_lvl1;
	private Business_line_lvl2 business_line_lvl2;
	private Department department;
	private Events event;
	private Event_lvl1 event_lvl1;
	private Process_lvl1 process_lvl1;
	private Process_lvl2 process_lvl2;
	private Records records;
	private Section section;
	private Subdivision subdivision;
	private Users users;
	private TypeZapisi typeZapisi;	
	private LossCurrency lossCurrency;
	private SumVal sumVal;
	private Effect effect;
	private ArrayList<Attachment> attachments;
	
	public ApplicationForm() {
		activities = new Activities();
		activities_lvl1 = new Activities_lvl1();
		activities_lvl2 = new Activities_lvl2();
		branch = new Branch();
		business_line = new Business_line();
		business_line_lvl1 = new Business_line_lvl1();
		business_line_lvl2 = new Business_line_lvl2();
		department = new Department();
		event = new Events();
		event_lvl1 = new Event_lvl1();
		process_lvl1 = new Process_lvl1();
		process_lvl2 = new Process_lvl2();
		records = new Records();
		section = new Section();
		sumVal = new SumVal();
		subdivision = new Subdivision();
		users = new Users();
		effect = new Effect();
		typeZapisi = new TypeZapisi();	
		lossCurrency = new LossCurrency();
		attachments = new ArrayList<Attachment>();

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Activities getActivities() {
		return activities;
	}

	public void setActivities(Activities activities) {
		this.activities = activities;
	}

	public Activities_lvl1 getActivities_lvl1() {
		return activities_lvl1;
	}

	public void setActivities_lvl1(Activities_lvl1 activities_lvl1) {
		this.activities_lvl1 = activities_lvl1;
	}

	public Activities_lvl2 getActivities_lvl2() {
		return activities_lvl2;
	}

	public void setActivities_lvl2(Activities_lvl2 activities_lvl2) {
		this.activities_lvl2 = activities_lvl2;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Business_line getBusiness_line() {
		return business_line;
	}

	public void setBusiness_line(Business_line business_line) {
		this.business_line = business_line;
	}

	public Business_line_lvl1 getBusiness_line_lvl1() {
		return business_line_lvl1;
	}

	public void setBusiness_line_lvl1(Business_line_lvl1 business_line_lvl1) {
		this.business_line_lvl1 = business_line_lvl1;
	}

	public Business_line_lvl2 getBusiness_line_lvl2() {
		return business_line_lvl2;
	}

	public void setBusiness_line_lvl2(Business_line_lvl2 business_line_lvl2) {
		this.business_line_lvl2 = business_line_lvl2;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Events getEvent() {
		return event;
	}

	public void setEvent(Events event) {
		this.event = event;
	}

	public Event_lvl1 getEvent_lvl1() {
		return event_lvl1;
	}

	public void setEvent_lvl1(Event_lvl1 event_lvl1) {
		this.event_lvl1 = event_lvl1;
	}

	public Process_lvl1 getProcess_lvl1() {
		return process_lvl1;
	}

	public void setProcess_lvl1(Process_lvl1 process_lvl1) {
		this.process_lvl1 = process_lvl1;
	}

	public Process_lvl2 getProcess_lvl2() {
		return process_lvl2;
	}

	public void setProcess_lvl2(Process_lvl2 process_lvl2) {
		this.process_lvl2 = process_lvl2;
	}

	public Records getRecords() {
		return records;
	}

	public void setRecords(Records records) {
		this.records = records;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Subdivision getSubdivision() {
		return subdivision;
	}

	public void setSubdivision(Subdivision subdivision) {
		this.subdivision = subdivision;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public LossCurrency getLossCurrency() {
		return lossCurrency;
	}

	public void setLossCurrency(LossCurrency lossCurrency) {
		this.lossCurrency = lossCurrency;
	}

	public SumVal getSumVal() {
		return sumVal;
	}

	public void setSumVal(SumVal sumVal) {
		this.sumVal = sumVal;
	}
	
	public TypeZapisi getTypeZapisi() {
		return typeZapisi;
	}

	public void setTypeZapisi(TypeZapisi typeZapisi) {
		this.typeZapisi = typeZapisi;
	}

	public Effect getEffect() {
		return effect;
	}

	public void setEffect(Effect effect) {
		this.effect = effect;
	}
	
	public ArrayList<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(ArrayList<Attachment> attachments) {
		this.attachments = attachments;
	}

}
