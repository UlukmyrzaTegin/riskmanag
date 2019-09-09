package rts.forms;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;

import org.apache.commons.io.output.ByteArrayOutputStream;

import com.vaadin.server.FileDownloader;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component.Event;
import com.vaadin.ui.Component.Listener;
import com.vaadin.ui.Link;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import rts.resourses.ButtonsPanel;

public class ReportFormView implements Serializable, Listener {
	private static final long serialVersionUID = 4690083727683014881L;
	private JasperPrint jasperPrint;
	private String reoprtName;
	private ButtonsPanel buttonsPanel = new ButtonsPanel();
	private Boolean showButtonPanel = true;
	private Button exportToXls, exportToWord;
	@SuppressWarnings("unused")
	private Link link;
	@SuppressWarnings("unused")
	private Button dowlButton;
	
	public ReportFormView(JasperPrint jasperPrint, String reportName) {
		// TODO Auto-generated constructor stub
		this.jasperPrint = jasperPrint;
		this.reoprtName = reportName;
	}
	
     private void showViewer() {
		// TODO Auto-generated method stub
		if (jasperPrint ==null) 
			return;
		Window window = new Window();
		window.setResizable(true);
		window.setCaption("Просмотр: " + reoprtName);
		window.setWidth("97%");
		window.setHeight("97%");
		window.center();
		window.setModal(true);
		
		if (jasperPrint != null) {
			final ByteArrayOutputStream stream = new ByteArrayOutputStream();
			try {
				JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
			} catch (JRException e1) {
				e1.printStackTrace();
			}
			
			StreamSource source = new StreamSource() {
				private static final long serialVersionUID = -214640282765790731L;

				@Override
				public InputStream getStream() {
					// TODO Auto-generated method stub
					try {
						return new ByteArrayInputStream(stream.toByteArray());
					} catch (Exception e) {
						e.printStackTrace();
						return null;
					}
				}
			};
			StreamResource resource = new StreamResource(source, reoprtName + ".pdf");
			BrowserFrame browser = new BrowserFrame();
			browser.setSource(resource);
			browser.setSizeFull();
			if (showButtonPanel) {
				 final VerticalLayout vLayout = new VerticalLayout();
				 vLayout.setImmediate(false);
				 vLayout.setWidth("100.0%");
				 vLayout.setHeight("100.0%");
				 vLayout.setMargin(false);
				 exportToXls = new Button();
				 exportToXls.setDescription("Export to Excel");
				 exportToXls.setIcon(FontAwesome.FILE_EXCEL_O);
				 exportToXls.setStyleName(ValoTheme.BUTTON_LINK);
				 exportToXls.addListener(this);
				 FileDownloader fdXls = new FileDownloader(generateXlsStream());
				 fdXls.extend(exportToXls);
				 buttonsPanel.addButton(exportToXls);
				 
				 exportToWord = new Button();
				 exportToWord.setDescription("Export to Word");
				 exportToWord.setIcon(FontAwesome.FILE_WORD_O);
				 exportToWord.setStyleName(ValoTheme.BUTTON_QUIET);
				 exportToWord.addListener(this);
				 FileDownloader fdRtf = new FileDownloader(generateRtfStream());
				 fdRtf.extend(exportToWord);
				 buttonsPanel.addButton(exportToWord);
				 buttonsPanel.setbAlignment(Alignment.MIDDLE_LEFT);
				 vLayout.addComponent(buttonsPanel.buildButtonsPanel());
				 vLayout.addComponent(browser);
				 vLayout.setExpandRatio(browser, 1.0f);
				 window.setContent(vLayout);		 
			}
			else
				window.setContent(browser);
		}
		UI.getCurrent().addWindow(window);
	}

	public JasperPrint getJasperPrint() {
		return jasperPrint;
	}

	public void setJasperPrint(JasperPrint jasperPrint) {
		this.jasperPrint = jasperPrint;
	}

	public String getReoprtName() {
		return reoprtName;
	}

	public void setReoprtName(String reoprtName) {
		this.reoprtName = reoprtName;
	}

	public Boolean getShowButtonPanel() {
		return showButtonPanel;
	}

	public void setShowButtonPanel(Boolean showButtonPanel) {
		this.showButtonPanel = showButtonPanel;
	}

	@Override
	public void componentEvent(Event event) {
		// TODO Auto-generated method stub
		if (event.getClass() == Button.ClickEvent.class) {
			if(event.getSource() == exportToXls) {
				
			}
			else if (event.getSource() == exportToWord) {}
		}
		
	}
	
	private StreamResource generateXlsStream() {
		// TODO Auto-generated method stub
		final ByteArrayOutputStream stream = new ByteArrayOutputStream();
		JRXlsExporter exporterXLS = new JRXlsExporter();
		exporterXLS.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporterXLS.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
		//exporterXLS.setParameter(JRExporterParameter.IS, value);
		
		StreamResource resource = null;
		try {
			exporterXLS.exportReport();
			StreamSource source = new StreamSource() {
				private static final long serialVersionUID = 1L;
				@Override
				public InputStream getStream() {
					// TODO Auto-generated method stub
					try {
						return new ByteArrayInputStream(stream.toByteArray());
					} catch (Exception e) {
						e.printStackTrace();
						return null;
					}
				}
			};
			resource = new StreamResource(source, "" + reoprtName + ".xls");
		} catch (JRException e1) {
			e1.printStackTrace();
		}
		return resource;
	}
	
	private StreamResource generateRtfStream() {
		// TODO Auto-generated method stub
		final ByteArrayOutputStream stream = new ByteArrayOutputStream();
		JRDocxExporter exporterRTF = new JRDocxExporter();
		exporterRTF.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
		exporterRTF.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, stream);
		StreamResource resource = null;
		try {
			exporterRTF.exportReport();
			StreamSource rSource = new StreamSource() {
				private static final long serialVersionUID = 273094320961105995L;
				@Override
				public InputStream getStream() {
					// TODO Auto-generated method stub
					try {
						return new ByteArrayInputStream(stream.toByteArray());
					} catch (Exception e) {
						e.printStackTrace();
						return null;
					}
				}
			};
			resource = new StreamResource(rSource, " " + reoprtName + ".docx");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return resource;
	}
}
