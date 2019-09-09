package rts.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Records {
	
	@SuppressWarnings("unused")
	private static final long serialversionUID = -3856518279921410532L; 
	private Integer id_record;
	private Branch id_branch;
	private Subdivision id_subdivision;
	private Department id_department;
	private Section id_section;
	private Branch id_branch_regist;
	private Subdivision id_subdivision_regist;
	private Department id_department_regist;
	private Section id_section_regist;
	private Date date_emergence;
	private Date date_insertion;
	private TypeZapisi id_type;
	private Events id_event;
	private Event_lvl1 id_event1;
	private String sum_losses;
	private String sum_income;
	private LossCurrency id_loss;
	private String sum_losses_kgs;
	private Date date_detected;
	private Business_line id_bl;
	private Business_line_lvl1 id_bl1;
	private Business_line_lvl2 id_bl2;
	private Activities id_activities;
	private Activities_lvl1 id_activities1;
	private Activities_lvl2 id_activities2;
	private Process_lvl1 id_process1;
	private Process_lvl2 id_process2;
	private String event_description;
	private String how_detected;
	private String sum_compensation_val;
	private String sum_compensation_kgs;
	private Date date_compensation;
	private String status_compensation;
	private String comment_compensation;
	private String decision;
	private SumVal id_sumVal;
	private String o_name;
	private String o_podr;
	private String k_name;
	private Effect id_effect;
	
	public Integer getId_record() {
		return id_record;
	}
	public void setId_record(Integer id_record) {
		this.id_record = id_record;
	}
	public Branch getId_branch_regist() {
		return id_branch_regist;
	}
	public void setId_branch_regist(Branch id_branch_regist) {
		this.id_branch_regist = id_branch_regist;
	}
	public Subdivision getId_subdivision_regist() {
		return id_subdivision_regist;
	}
	public void setId_subdivision_regist(Subdivision id_subdivision_regist) {
		this.id_subdivision_regist = id_subdivision_regist;
	}
	public Department getId_department_regist() {
		return id_department_regist;
	}
	public void setId_department_regist(Department id_department_regist) {
		this.id_department_regist = id_department_regist;
	}
	public Section getId_section_regist() {
		return id_section_regist;
	}
	public void setId_section_regist(Section id_section_regist) {
		this.id_section_regist = id_section_regist;
	}
	public Date getDate_emergence() {
		return date_emergence;
	}
	public void setDate_emergence(Date date_emergence) {
		this.date_emergence = date_emergence;
	}
	public Date getDate_insertion() {
		return date_insertion;
	}
	public void setDate_insertion(Date date_insertion) {
		this.date_insertion = date_insertion;
	}

	public Event_lvl1 getId_event1() {
		return id_event1;
	}
	public void setId_event1(Event_lvl1 id_event1) {
		this.id_event1 = id_event1;
	}
	public String getSum_losses() {
		return sum_losses;
	}
	public void setSum_losses(String sum_losses) {
		this.sum_losses = sum_losses;
	}
	public String getSum_income() {
		return sum_income;
	}
	public void setSum_income(String sum_income) {
		this.sum_income = sum_income;
	}
	public Date getDate_detected() {
		return date_detected;
	}
	public void setDate_detected(Date date_detected) {
		this.date_detected = date_detected;
	}
	public Business_line getId_bl() {
		return id_bl;
	}
	public void setId_bl(Business_line id_bl) {
		this.id_bl = id_bl;
	}
	public Business_line_lvl1 getId_bl1() {
		return id_bl1;
	}
	public void setId_bl1(Business_line_lvl1 id_bl1) {
		this.id_bl1 = id_bl1;
	}
	public Business_line_lvl2 getId_bl2() {
		return id_bl2;
	}
	public void setId_bl2(Business_line_lvl2 id_bl2) {
		this.id_bl2 = id_bl2;
	}
	public Activities getId_activities() {
		return id_activities;
	}
	public void setId_activities(Activities id_activities) {
		this.id_activities = id_activities;
	}
	public Activities_lvl1 getId_activities1() {
		return id_activities1;
	}
	public void setId_activities1(Activities_lvl1 id_activities1) {
		this.id_activities1 = id_activities1;
	}
	public Activities_lvl2 getId_activities2() {
		return id_activities2;
	}
	public void setId_activities2(Activities_lvl2 id_activities2) {
		this.id_activities2 = id_activities2;
	}
	public Process_lvl1 getId_process1() {
		return id_process1;
	}
	public void setId_process1(Process_lvl1 id_process1) {
		this.id_process1 = id_process1;
	}
	public Process_lvl2 getId_process2() {
		return id_process2;
	}
	public void setId_process2(Process_lvl2 id_process2) {
		this.id_process2 = id_process2;
	}
	public String getEvent_description() {
		return event_description;
	}
	public void setEvent_description(String event_description) {
		this.event_description = event_description;
	}
	public String getHow_detected() {
		return how_detected;
	}
	public void setHow_detected(String how_detected) {
		this.how_detected = how_detected;
	}
	public String getSum_compensation_val() {
		return sum_compensation_val;
	}
	public void setSum_compensation_val(String sum_compensation_val) {
		this.sum_compensation_val = sum_compensation_val;
	}
	public String getSum_compensation_kgs() {
		return sum_compensation_kgs;
	}
	public void setSum_compensation_kgs(String sum_compensation_kgs) {
		this.sum_compensation_kgs = sum_compensation_kgs;
	}
	public Date getDate_compensation() {
		return date_compensation;
	}
	public void setDate_compensation(Date date_compensation) {
		this.date_compensation = date_compensation;
	}
	public String getStatus_compensation() {
		return status_compensation;
	}
	public void setStatus_compensation(String status_compensation) {
		this.status_compensation = status_compensation;
	}
	public String getComment_compensation() {
		return comment_compensation;
	}
	public void setComment_compensation(String comment_compensation) {
		this.comment_compensation = comment_compensation;
	}

	public String getDecision() {
		return decision;
	}
	public void setDecision(String decision) {
		this.decision = decision;
	}
	public Events getId_event() {
		return id_event;
	}
	public void setId_event(Events id_event) {
		this.id_event = id_event;
	}

	public LossCurrency getId_loss() {
		return id_loss;
	}
	public void setId_loss(LossCurrency id_loss) {
		this.id_loss = id_loss;
	}

	public SumVal getId_sumVal() {
		return id_sumVal;
	}
	public void setId_sumVal(SumVal id_sumVal) {
		this.id_sumVal = id_sumVal;
	}
	
	public TypeZapisi getId_type() {
		return id_type;
	}
	public void setId_type(TypeZapisi id_type) {
		this.id_type = id_type;
	}
	public Department getId_department() {
		return id_department;
	}
	public void setId_department(Department id_department) {
		this.id_department = id_department;
	}
	public Section getId_section() {
		return id_section;
	}
	public void setId_section(Section id_section) {
		this.id_section = id_section;
	}
	public String getO_name() {
		return o_name;
	}
	public void setO_name(String o_name) {
		this.o_name = o_name;
	}
	public String getO_podr() {
		return o_podr;
	}
	public void setO_podr(String o_podr) {
		this.o_podr = o_podr;
	}
	public String getK_name() {
		return k_name;
	}
	public void setK_name(String k_name) {
		this.k_name = k_name;
	}
	public Effect getId_effect() {
		return id_effect;
	}
	public void setId_effect(Effect id_effect) {
		this.id_effect = id_effect;
	}
	public Subdivision getId_subdivision() {
		return id_subdivision;
	}
	public void setId_subdivision(Subdivision id_subdivision) {
		this.id_subdivision = id_subdivision;
	}
	public String getSum_losses_kgs() {
		return sum_losses_kgs;
	}
	public void setSum_losses_kgs(String sum_losses_kgs) {
		this.sum_losses_kgs = sum_losses_kgs;
	}
	public Branch getId_branch() {
		return id_branch;
	}
	public void setId_branch(Branch id_branch) {
		this.id_branch = id_branch;
	}
}
