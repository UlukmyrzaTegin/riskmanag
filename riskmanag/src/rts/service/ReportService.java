package rts.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.output.ByteArrayOutputStream;

import com.itextpdf.text.pdf.interfaces.PdfViewerPreferences;
import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.part.FinalFillingPrintPart;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import rts.appUI.MyUI;
import rts.data.DatabaseConnection;

public class ReportService implements Serializable {
	
/*	private static final long serialVersionUID = 5821393856802669177L;
	@SuppressWarnings("deprecation")
	public static void reportById(Integer value, JDBCConnectionPool connectionPool, MyUI app) {
		// TODO Auto-generated method stub
		
		String filesource = "";
		
		Map<String, Object> queryParameters = new HashMap<String, Object>();
		//queryParameters.put("id", id);
		
		
		DatabaseConnection dbcon = new DatabaseConnection();
		try {
			Class.forName(dbcon.getDriverName());
		} catch (ClassNotFoundException e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(dbcon.getConnectionString(),
					dbcon.getUsername(), dbcon.getPassword());
		} catch (SQLException e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		
		JasperPrint jasperPrint = null;
		try {
			jasperPrint = JasperFillManager.fillReport(filesource, queryParameters, connection);
			if (jasperPrint != null) {
				JasperExportManager.exportReportToPdfFile(jasperPrint, "d://risk.pdf");
				Window window = new Window();
				window.setResizable(true);
				window.setCaption("PDF example!");
				window.setWidth("90%");
				window.setHeight("90%");
				window.center();
				StreamSource source = new StreamSource() {
					private static final long serialVersionUID = -4189723111436837616L;

					@Override
					public InputStream getStream() {
						// TODO Auto-generated method stub
						try {
							File file = new File("d://risk.pdf");
							FileInputStream fStream = new FileInputStream(file);
							return fStream;
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
							return null;
						}
					}
				};
				StreamResource resource = new StreamResource(source, "d://risk.pdf");
				Embedded embedded = new Embedded();
				embedded.setSizeFull();
				embedded.setType(Embedded.TYPE_BROWSER);
				resource.setMIMEType("application/pdf");
				embedded.setSource(resource);
				window.setContent(embedded);
				app.addWindow(window);
				
			}
					
		} catch (JRException e) {
			// TODO: handle exception
			Notification.show("3");
			e.printStackTrace();
		}
	}
	*/
	
	public static Button reportButton;
	private JasperDesign jasperDesign;
	
	@SuppressWarnings("unchecked")
	public static void reportByIdDetailSpravka(Integer id,
			JDBCConnectionPool connectionPool, MyUI app) throws JRException, ClassNotFoundException, SQLException {
		String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
		JasperDesign jasperDesign = JRXmlLoader.load(basepath+ "");
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		@SuppressWarnings("rawtypes")
		Map queryParameters = new HashMap();
		queryParameters.put("id_record", id);
		queryParameters.put("SUBREPORT_DIR", basepath+ "/WEB-INF/jasper/");
		DatabaseConnection dbCon = new DatabaseConnection();
		Class.forName(dbCon.getDriverName());
		Connection connection = null;
		connection = DriverManager.getConnection(dbCon.getConnectionString(), dbCon.getUsername(), dbCon.getPassword());
		JasperPrint jasperPrint = null;
		jasperPrint = JasperFillManager.fillReport(jasperReport, queryParameters, connection);
		app.addWindow(PdfViewer(jasperPrint));
	}
	
	public void reportByIdDetailSmeta(Integer id,
			JDBCConnectionPool connectionPool, MyUI app) throws JRException, ClassNotFoundException, SQLException {
		String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
		JasperDesign jasperDesign = JRXmlLoader.load(basepath+"//");
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		Map queryParameters = new HashMap();
		queryParameters.put("id_record", id);
		queryParameters.put("SUBREPORT_DIR", basepath + "/WEB-INF/jasper/");
		DatabaseConnection dbCon = new DatabaseConnection();
		Class.forName(dbCon.getDriverName());
		Connection connection  = null;
		connection = DriverManager.getConnection(dbCon.getConnectionString(), dbCon.getUsername(), dbCon.getPassword());
		JasperPrint jasperPrint = null;
		jasperPrint = JasperFillManager.fillReport(jasperReport, queryParameters, connection);
		app.addWindow(PdfViewer(jasperPrint));	
	}
	
	@SuppressWarnings("deprecation")
	private static Window PdfViewer(final JasperPrint jasperPrint) throws JRException {
		final Window window = new Window();
		if (jasperPrint != null) {
			final VerticalLayout vLayout = new VerticalLayout();
			vLayout.setImmediate(false);
			vLayout.setWidth("100.0%");
			vLayout.setHeight("100.0%");
			vLayout.setMargin(false);
			HorizontalLayout hLayout = new HorizontalLayout();
			hLayout.setWidth("100%");
			hLayout.setHeight("-1px");
			hLayout.setSpacing(true);
			reportButton = new Button("Export to XLS");
			reportButton.addListener(new ClickListener() {
				
				@SuppressWarnings("deprecation")
				@Override
				public void buttonClick(ClickEvent event) {
					if (event.getClass() == Button.ClickEvent.class ) {
						if (event.getSource() == reportButton) {
							String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
							System.out.println(basepath);
						
							final ByteArrayOutputStream stream = new ByteArrayOutputStream();
							JRXlsExporter exporterXLS = new JRXlsExporter();
							exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
							exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, stream);
							/*
							 * 
							 */
							try {
								exporterXLS.exportReport();
								StreamSource source = new StreamSource() {
									private static final long serialVersionUID = -6369425476675317299L;

									@Override
									public InputStream getStream() {
										try {
											return new ByteArrayInputStream(stream.toByteArray());
										} catch (Exception e) {
											e.printStackTrace();
											return null;
										}
									}
								};
								StreamResource resource = new StreamResource(source, "RISK_MANAGMENTA_XLS.xls");
								Embedded embedded = new Embedded();
								embedded.setSizeFull();
								embedded.setType(Embedded.TYPE_BROWSER);
								resource.setMIMEType("application/x-msexcel");
								embedded.setSource(resource);
								window.setContent(embedded);
								return;
								
							} catch (JRException e) {
								e.printStackTrace();
							}
						}
						
					}
					
					
				}
			});
			hLayout.addComponent(reportButton);
			hLayout.setComponentAlignment(reportButton, Alignment.BOTTOM_LEFT);
			vLayout.addComponent(hLayout);
			
			final ByteArrayOutputStream stream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
			window.setResizable(true);
			window.setCaption("PDF просмотр");
			window.setWidth("100%");
			window.setHeight("100%");
			window.center();
			StreamSource source = new StreamSource() {
				private static final long serialVersionUID = 762107894651651677L;
				@Override
				public InputStream getStream() {
					try {
						return new ByteArrayInputStream(stream.toByteArray());
					} catch (Exception e) {
						e.printStackTrace();
						return null;
					}
				}
			};
			StreamResource resource = new StreamResource(source, "RISK_MANAGMENTA_DOCX.pdf");
			Embedded embedded = new Embedded();
			embedded.setSizeFull();
			embedded.setType(Embedded.TYPE_BROWSER);
			resource.setMIMEType("application/pdf");
			embedded.setSource(resource);
			HorizontalLayout hLayout1 = new HorizontalLayout();
			hLayout1.setHeight("100%");
			hLayout1.setWidth("100%");
			hLayout1.addComponent(embedded);
			vLayout.addComponent(hLayout1);
			vLayout.setExpandRatio(hLayout1, 1.0f);
			window.setContent(vLayout);
			}
		return window;
	}
}
