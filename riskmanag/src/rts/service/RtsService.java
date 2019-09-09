package rts.service;

import java.io.FileInputStream;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import javax.activation.DataHandler;

import org.apache.commons.io.IOUtils;
import org.apache.cxf.attachment.ByteDataSource;
import org.codehaus.stax2.validation.DTDValidationSchema;

import rts.data.DatabaseHelper;
import rts.domain.ApplicationForm;
import rts.domain.Branch;
import rts.domain.Records;

import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class RtsService implements Serializable{
	
	public static void getConsolEstim(Table table, Map<String, Object> map, int limit, int offset, JDBCConnectionPool connectionPool){
		table.removeAllItems();
	//	Integer users = (Integer) UI.getCurrent().getSession().getAttribute("users");\
		String query =""+
				" SELECT     "
				+ "public.records.id_record,                                                           "
				+" public.branch.branch_name,                                                          "
				+" public.subdivision.subdivision_name,                                                "
				+" public.department.department_name,                                                  "
				+" public.section.section_name,                                                        "
				+" public.activities.a_name,                                                           "
				+" public.activities_lvl1.a_name1,                                                     "
				+" public.activities_lvl2.a_name2,                                                     "
				+" public.process_lvl1.p_name1,                                                        "
				+" public.process_lvl2.p_name2,                                                        "
				+" public.business_line.bl_name,                                                       "
				+" public.business_line_lvl1.bl_name1,                                                 "
				+" public.business_line_lvl2.bl_name2,                                                 "
				+" public.events.event_name,                                                           "
				+" public.event_lvl1.event_name1,                         							   "
				+" public.type_zapisi.t_name,                                                          "
				+" public.loss_currency.l_name,                                                        "
				+" public.sum_val.s_name,                                                              "
				+" public.effect.e_name,                                                               "
				+" public.records.id_branch_regist,                                                    "
				+" public.records.id_subdivision_regist,                                               "
				+ "public.records.id_department_regist,             								   "
				+" public.records.id_section_regist,                                                   "
				+" public.records.date_emergence,                                                      "
				+" public.records.date_insertion,                                                      "
				+" public.records.id_event,                                                            "
				+" public.records.id_event1,                                                           "
				+" public.records.sum_losses,                                                          "
				+" public.records.sum_income,                                                          "
				+" public.records.sum_losses_kgs,                                                      "
				+" public.records.date_detected,                                                       "
				+" public.records.id_bl,                                                               "
				+" public.records.id_bl1,                                                              "
				+" public.records.id_bl2,                                                              "
				+" public.records.id_activities1,                                                      "
				+" public.records.id_activities2, 													   "
				+" public.records.id_process1,                                                         "
				+" public.records.id_process2,                                                         "
				+" public.records.event_description,                                                   "
				+" public.records.how_detected,                                                        "
				+" public.records.sum_compensation_kgs,                                                "
				+" public.records.date_compensation,     											   "
				+" public.records.status_compensation,                                                 "
				+" public.records.comment_compensation,                                                "
				+" public.records.decision,                                                            "
				+" public.records.id_type,                                                             "
				+" public.records.id_loss, 															   "
				+" public.records.id_sumval,                                                           "
				+" public.records.o_name,                                                              "
				+" public.records.o_podr,                                                              "
				+" public.records.k_name,                                                              "
				+" public.records.id_effect        													   "
			
				+" FROM                                                                                      "
				+"  public.records                                                                           "
				+"  LEFT Join public.branch ON public.branch.id_branch = public.records.id_branch_regist     "
				+"  LEFT Join public.subdivision ON public.subdivision.id_branch = public.branch.id_branch   AND public.subdivision.id_subdivision = public.records.id_subdivision_regist                 "
				+"  LEFT Join public.department ON public.department.id_subdivision = public.subdivision.id_subdivision AND public.department.id_department = public.records.id_department_regist         "
				+"  LEFT Join public.section ON public.section.id_department = public.department.id_department AND public.section.id_section = public.records.id_section_regist                           "
				+"  LEFT Join public.activities_lvl1 ON public.activities_lvl1.id = public.records.id_activities1                     																	  "
				+"  LEFT Join public.activities_lvl2 ON public.activities_lvl2.id_ac1 = public.activities_lvl1.id AND public.activities_lvl2.id = public.records.id_activities2           			      "
				+"  LEFT Join public.process_lvl1 ON public.process_lvl1.id_ac1 = public.activities_lvl1.id AND public.process_lvl1.id = public.records.id_process1                                       "
				+"  LEFT Join public.process_lvl2 ON public.process_lvl2.id_pr1 = public.process_lvl1.id AND public.process_lvl2.id = public.records.id_process2                                          "
				+"  LEFT Join public.business_line ON public.business_line.id_bl = public.records.id_bl       																							  "
				+"  LEFT Join public.business_line_lvl1 ON public.business_line_lvl1.id_bl = public.business_line.id_bl AND public.business_line_lvl1.id_bl1 = public.records.id_bl1                      "
				+"  LEFT Join public.business_line_lvl2 ON public.business_line_lvl2.id_bl1 = public.business_line_lvl1.id_bl1 AND public.business_line_lvl2.id_bl2 = public.records.id_bl2               "
				+"  LEFT Join public.events ON public.events.id_event = public.records.id_event        																								      "
				+"  LEFT Join public.event_lvl1 ON public.event_lvl1.id_event = public.events.id_event AND public.event_lvl1.id_event1 = public.records.id_event1               				          "
				+"  LEFT Join public.type_zapisi ON public.type_zapisi.id = public.records.id_type              "
				+"  LEFT Join public.loss_currency ON public.loss_currency.id = public.records.id_loss          "
				+"  LEFT Join public.sum_val ON public.sum_val.id = public.records.id_sumval                    "
				+"  LEFT Join public.effect ON public.effect.id = public.records.id_effect                      "
				+"  LEFT Join public.activities ON public.activities.id = public.activities_lvl1.id_activities  "

				+" where 1=1  ";
			
		Connection conn = null;
		for (Map.Entry<String, Object> entry : map.entrySet()) 
			query += " and "+entry.getKey()+" ? ";
		
		//query += " GROUP BY  ";
		query += " ORDER BY  records.date_insertion  desc";
		query += " LIMIT ? OFFSET ? ";
		try {
			conn = connectionPool.reserveConnection();
			CallableStatement proc = conn.prepareCall(query);
			int i = 1;
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				if  (entry.getValue().getClass() == Integer.class)
					proc.setInt(i++, (Integer) entry.getValue());
				else if (entry.getValue().getClass() == String.class) 
					proc.setString(i++, (String) entry.getValue());
				else if (entry.getValue().getClass() == java.sql.Date.class) 
					proc.setDate(i++, (java.sql.Date) entry.getValue());
			}
			proc.setInt(i++, limit);
			proc.setInt(i++, offset);
			ResultSet rs = proc.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					table.addItem(new Object[] {
							rs.getString("branch_name"),
							rs.getString("subdivision_name"),
							rs.getString("department_name"),
							rs.getString("section_name"),
							rs.getString("k_name"),
							rs.getDate("date_emergence"),
							rs.getDate("date_insertion"),
							rs.getString("t_name"),
							rs.getString("event_name"),
							rs.getString("event_name1"),
							rs.getString("sum_losses"),
							rs.getString("sum_income"),
							rs.getString("l_name"),
							rs.getString("sum_losses_kgs"),
							rs.getDate("date_detected"),
							rs.getString("e_name"),
							rs.getString("bl_name"),
							rs.getString("bl_name1"),
							rs.getString("bl_name2"),
							rs.getString("a_name"),
							rs.getString("a_name1"),
							rs.getString("a_name2"),
							rs.getString("p_name1"),
							rs.getString("p_name2"),
							rs.getString("event_description"),
							rs.getString("how_detected"),
							rs.getString("s_name"),
							rs.getString("sum_compensation_kgs"),
							rs.getDate("date_compensation"),
							rs.getString("comment_compensation"),
							rs.getString("o_name"),
							rs.getString("o_podr"),
							rs.getString("decision"), 
							},rs.getInt("id_record"));
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(conn);
		}
	}	

	public static Integer getTotalNumberOfConsolEstim(Map<String, Object> map, JDBCConnectionPool connectionPool){
		Integer result = 0;
		String query = "" +
				"SELECT " +
				" Count(id_record) as cnt " +
				" FROM " +
				" public.records " +
				"where 1=1 ";
		Connection conn = null;
		for (Map.Entry<String, Object> entry : map.entrySet()) 
			query += " and "+entry.getKey()+" ? ";
		try {
			conn = connectionPool.reserveConnection();

			CallableStatement proc = conn.prepareCall(query);
			int i = 1;
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				query += " and "+entry.getKey()+" ? ";
				if  (entry.getValue().getClass() == Integer.class)
					proc.setInt(i++, (Integer) entry.getValue());
				else if (entry.getValue().getClass() == String.class) 
					proc.setString(i++, (String) entry.getValue());
				else if (entry.getValue().getClass() == java.sql.Date.class) 
					proc.setDate(i++, (java.sql.Date) entry.getValue());
			}
			ResultSet rs = proc.executeQuery();
			if (rs != null && rs.next()) {
				result = rs.getInt("cnt");
				}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(conn);
		}
		return result;
	}	

	public static ApplicationForm getApplicationFormById(Integer id, DatabaseHelper dbHelper) {
		ApplicationForm appForm = new ApplicationForm();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        try {
            conn = dbHelper.getConnectionPool().reserveConnection();
            proc = conn.prepareCall("SELECT * FROM public.records WHERE id = "+id+";");
            rs = proc.executeQuery();
            if (rs != null) {
                rs.next();
               appForm.setId(id);
               //
     
               
               
              // appForm.setMainHeader(mh);
            }
            proc = conn.prepareCall("SELECT * FROM public.main_detail WHERE main_header = "+id+";");
            rs = proc.executeQuery();
            if (rs != null) {
            	while (rs.next()) {
           	/*	Attachment attFile = new Attachment();
            		attFile.setFileName(rs.getString("file_name"));
            		attFile.setMIMEType(rs.getString("mime_type"));
            		attFile.setFileStorageId(rs.getLong("fs"));
          		attachments.add(attFile);
            		MainDetail mainDetail = new MainDetail();
            		mainDetail.setFile_name(rs.getString("file_name"));
            		mainDetail.setMime_type(rs.getString("mime_type"));
            		mainDetail.setFs(rs.getLong("fs"));
            		md.add(mainDetail);   */
            	}
            }
       /*     appForm.setMainDetail(md);
            appForm.setAttachments(attachments);   */
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	dbHelper.getConnectionPool().releaseConnection(conn);
        }		
		
		return appForm;
	}
	
	@SuppressWarnings("resource")
	public static String saveApplicationForm(ApplicationForm appForm, JDBCConnectionPool connectionPool){
		
		Records rds = appForm.getRecords();
		if (rds.getId_branch() == null) 
			return "Не выбран: Филиал";
		if (rds.getId_subdivision()==null) 
			return "Не выбран: Подразделения";
		if (rds.getId_department() == null) 
			return "Не выбран: Отдел";
		if (rds.getId_section() == null) 
			return "Не выбран: Сектор";
		if (rds.getK_name()==null) 
			return "Не заполнено поле: ФИО лица, вносящего записи";
		if (rds.getDate_emergence()==null) 
			return "Не указана: Дата возникновения";
		if (rds.getDate_insertion() == null) 
			return "Не указана: Дата внесения";
		if (rds.getId_type() == null) 
			return "Не выбран: Тип записи";
		if (rds.getId_event()==null) 
			return "Не выбран: Типы событий";
		if (rds.getId_event1()==null) 
			return "Не выбран: Тип события/уровень 2";
		if (rds.getSum_losses()== null) 
			return "Не указен: Сумма потерь/ущерба";
		if (rds.getSum_income()==null)
			return "Не указен: Сумма дохода события";
		if (rds.getId_loss()==null)
			return "Не выбран: Валюта потерь/ущерба";
		if (rds.getSum_compensation_kgs() == null)
		    return "Не указен: Сумма ущерба в KGS";
		if (rds.getDate_detected()==null)
			return "Не указана: Дата обнаружения";
		if (rds.getId_effect()==null) 
			return "Не выбран: Эффект";
		if (rds.getId_bl()==null)
			return "Не выбран: Бизнесс линии (деятельность по банку)";
		if (rds.getId_bl1()==null)
			return "Не выбран: Наименование Бизнесс линии";
		if (rds.getId_bl2()== null)
			return "Не выбран: Бизнес линии/ уровень 2";
		if (rds.getId_activities()==null)
			return "Не выбран: Направления деятельности";
		if (rds.getId_activities1()==null)
			return "Не выбран: Продукт";
		if (rds.getId_activities2()==null)
			return "Не выбран: Операция";
		if (rds.getId_process1() == null)
			return "Не выбран: Процесс/уровень 1";
		if (rds.getId_process2()==null)
			return "Не выбран: Процесс/уровень 2";
		if (rds.getEvent_description()==null)
			return "Не заполнено поле: Описание события";
		if (rds.getHow_detected()==null)
			return "Не заполнено поле: Каким образом обнаружено";
		if (rds.getId_sumVal()==null)
			return "Не заполнено поле: Сумма возмещения в валюте";
		if (rds.getSum_compensation_kgs()==null)
			return "Не заполнено поле: Сумма возмещения в KGS";
		if (rds.getDate_compensation()==null)
			return "Не выбран: Дата возмещения";
		if (rds.getComment_compensation()==null)
			return "Не заполнено поле: Комментарии о возмещении, статус события";
		if (rds.getO_name()==null)
			return "Не заполнено поле: ФИО ответственного лица за возмещение не заполнен";
		if (rds.getO_podr()==null)
			return "Не заполнено поле: Ответственное подразделение за возмещение не заполнен";
		if (rds.getDecision()==null)
			return "Не заполнено поле: Принятые решения/Приложения по минимизации риска не заполнен";
		
		Connection conn = null;
		Integer tmp_id = null;
		try {
			conn = connectionPool.reserveConnection();
			conn.setAutoCommit(false);
			PreparedStatement statement = null;
			ResultSet rSet = null;
		
			/*
			 * 
			 *  здесь должен быть запрос на update and insert 
			 */
			
		
			return null;	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "SQL ERROR";
		} finally {
			connectionPool.releaseConnection(conn);
		}
	}	
	
	public static String deleteAppForm(Integer id, JDBCConnectionPool connectionPool) {
		Connection conn = null;
		try {
			conn = connectionPool.reserveConnection();
			conn.setAutoCommit(false);
			PreparedStatement cat = null;
			PreparedStatement stquery = null;
			stquery = conn.prepareStatement("DELETE FROM ");
			cat = conn.prepareStatement("DELETE FROM ");
			
			stquery.setInt(1, id);
			cat.setInt(1, id);
			
			stquery.executeUpdate();
			cat.executeUpdate();
			
			stquery.close();
			cat.close();
			
			conn.commit();
			return " ";
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "SQL ERROR;";
		}  finally {
			connectionPool.releaseConnection(conn);
		}	
	}
}
