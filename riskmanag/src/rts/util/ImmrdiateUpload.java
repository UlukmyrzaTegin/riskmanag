package rts.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.cxf.jaxws.JaxWsClientFactoryBean;

import com.github.appreciated.material.MaterialTheme;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.server.VaadinService;
import com.vaadin.server.communication.FileUploadHandler;
import com.vaadin.shared.ui.upload.UploadClientRpc;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.FailedListener;
import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.Upload.FinishedListener;
import com.vaadin.ui.Upload.ProgressListener;
import com.vaadin.ui.Upload.StartedEvent;
import com.vaadin.ui.Upload.StartedListener;
import com.vaadin.ui.Upload.SucceededEvent;

import rts.domain.Attachment;
import rts.service.DataResponse;
import rts.service.DocumentService;
import rts.service.JaxWsProxyFactoryBeanAddress;
import rts.service.UnitResponse;

//import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

@SuppressWarnings("serial")
public class ImmrdiateUpload extends VerticalLayout {
	
	private Label status = new Label();
	@SuppressWarnings("deprecation")
	private ProgressIndicator pIndicator = new ProgressIndicator();
	private FileUploader receiver = new FileUploader();
	private HorizontalLayout progressLayout = new HorizontalLayout();
	private Upload upload = new Upload(null, receiver); //null, receiver
	
	public File file;
	private ArrayList<Attachment> attachments;
	private VerticalLayout list_att = new VerticalLayout();
	
	public ImmrdiateUpload(ArrayList<Attachment> attachments) {
		this.attachments = attachments;
		setSpacing(true);
		status.setVisible(false);
		
		addComponent(status);
		addComponent(upload);
		addComponent(progressLayout);
		addComponent(list_att);
		
		upload.setImmediate(true);
		upload.setButtonCaption("Add file");
		
		progressLayout.setSpacing(true);
		progressLayout.setVisible(false);	
		progressLayout.addComponent(pIndicator);
		progressLayout.setComponentAlignment(pIndicator, Alignment.MIDDLE_LEFT);
		
		final Button cancelProcessing = new Button("Cancel");
		cancelProcessing.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				upload.interruptUpload();				
			}
		});
		
		cancelProcessing.setStyleName("danger");
		cancelProcessing.setIcon(FontAwesome.MINUS_CIRCLE);
		progressLayout.addComponent(cancelProcessing);
		
		upload.addSucceededListener(receiver); //receiver
		upload.addFailedListener(new FailedListener() {
			
			@Override
			public void uploadFailed(FailedEvent event) {
				// TODO Auto-generated method stub
				String msg = "File " + event.getFilename() + " не загружен.";
				Notification.show(msg, Type.ERROR_MESSAGE);				
			}
		});
		
		upload.addStartedListener(new StartedListener() {
			
			@Override
			public void uploadStarted(StartedEvent event) {
				// TODO Auto-generated method stub
				upload.setVisible(false);
				progressLayout.setVisible(true);
				pIndicator.setValue(0f);
				pIndicator.setPollingInterval(500);
				status.setValue("Uploading file \"" + event.getFilename() + "\"");
				status.setVisible(true);				
			}
		});
		
		upload.addProgressListener(new ProgressListener() {
			
			@Override
			public void updateProgress(long readBytes, long contentLength) {
				// TODO Auto-generated method stub
				pIndicator.setValue(new Float(readBytes / (float) contentLength));				
			}
		});
		
		upload.addFinishedListener(new FinishedListener() {
			
			@Override
			public void uploadFinished(FinishedEvent event) {
				// TODO Auto-generated method stub
				progressLayout.setVisible(false);
				upload.setVisible(true);
				status.setVisible(false);				
			}
		});
		if (attachments.size() > 0)
			updateListAttachments();
	}
	
	public class FileUploader implements Upload.Receiver, Upload.SucceededListener {
		private String orignFileName, orignMIMEType;
		
		@Override
		public OutputStream receiveUpload(String filename, String MIMEType) {
			// TODO Auto-generated method stub
			orignFileName = filename;
			orignMIMEType = MIMEType;			
			FileOutputStream fos = null;
			
			try {
				file = File.createTempFile("file-", ".kcd");
				fos = new FileOutputStream(file); // OPen the file for writing				
			} catch (final java.io.FileNotFoundException e) {
				System.err.println("Error in Vdocflow -> ImmediateUpload -> FileUploader -> receiveUpload(): " + e.getStackTrace());
				return null;
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return null;
			}
			
			return fos;
		}
		
		@Override
		public void uploadSucceeded(Upload.SucceededEvent event) {
			// TODO Auto-generated method stub
			String msg = "File \"" + event.getFilename()+ "\" успешно загружен.";
			Notification.show(msg, Type.HUMANIZED_MESSAGE);
			
			Attachment uplFile = new Attachment();
			uplFile.setFile(file);
			uplFile.setFileName(orignFileName);
			uplFile.setMIMEType(orignMIMEType);
			uplFile.setFileStorageId(null);
			attachments.add(uplFile);
			if(attachments.size() > 0)
				updateListAttachments();			
		}			
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	
	private void updateListAttachments() {
		list_att.removeAllComponents();
		String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
	//	FileResource deleteResourse = new FileResource(new File(basepath + "/WEB-INF/resources/images/delete.gif"));
	//	FileResource loadResourse = new FileResource(new File(basepath + "/WEB-INF/resources/images/dowload.jpg"));
	//	FileResource searchResourse = new FileResource(new File(basepath + "/WEB-INF/resources/images/search.gif"));
		
		for(final Attachment tmp : attachments) {
			HorizontalLayout ltmp = new HorizontalLayout();
			Label fName = new Label(tmp.getFileName());
			ltmp.addComponent(fName);
			fName.setWidth("300px");
/////////////// Btn View
			Button viewBtn = new Button("View");
			viewBtn.setData(tmp);
			viewBtn.addStyleName(ValoTheme.BUTTON_SMALL+" "+ValoTheme.BUTTON_FRIENDLY);
			viewBtn.addClickListener(new ClickListener() {
				
				@Override
				public void buttonClick(ClickEvent event) {
					// TODO Auto-generated method stub
					Window window = new Window();
					window.setResizable(true);
					window.setCaption("Просмотр");
					window.setWidth("90%");
					window.setHeight("90%");
					window.center();
					if(tmp.getFileStorageId() != null && tmp.getFile() == null) {
						System.out.println(tmp.getFileStorageId());
						JaxWsClientFactoryBean factory = new JaxWsClientFactoryBean();
						factory.setServiceClass(DocumentService.class);
						factory.setAddress(JaxWsProxyFactoryBeanAddress.getAddress());
						DocumentService service = (DocumentService) factory.create();
						DataResponse data = service.getData(tmp.getFileStorageId().intValue(), 0);
						InputStream inputStream = null;
						OutputStream outputStream = null;
						try {
							inputStream = data.getData().getInputStream();
							File newFile = File.createTempFile("file-", ".kcd");
							outputStream = new FileOutputStream(newFile);
							int read = 0;
							byte[] bytes = new byte[1024];
							while ((read = inputStream.read(bytes)) != -1) {
								outputStream.write(bytes, 0, read);
							}
							tmp.setFile(newFile);							
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						} finally {
							if(inputStream != null) {
								try {
									inputStream.close();									
								} catch (Exception e2) {
									// TODO: handle exception
									e2.printStackTrace();
								}
							}
							if(outputStream != null) {
								try {
									outputStream.close();
								} catch (Exception e2) {
									// TODO: handle exception
									e2.printStackTrace();
								}
							}
						}
						
						StreamSource s = new StreamSource() {
							
							@Override
							public InputStream getStream() {
								// TODO Auto-generated method stub
								try {
									FileInputStream fis = new FileInputStream(tmp.getFile());
									return fis;
								} catch (Exception e) {
									// TODO: handle exception
									e.printStackTrace();
									return null;
								}								
							}
						};
						
						StreamResource r = new StreamResource(s, tmp.getFile().getName());
						Embedded e = new Embedded();
						e.setSizeFull();
						e.setType(Embedded.TYPE_BROWSER);
						r.setMIMEType(tmp.getMIMEType());
						e.setSource(r);
						window.setContent(e);
						getUI().addWindow(window);
					}					
				}
			});
			viewBtn.setIcon(FontAwesome.PLAY_CIRCLE); //searchResourse
			ltmp.addComponent(viewBtn);
			
///// Btn Loading		
			Button loadBtn = new Button("Loading...");
			loadBtn.setData(tmp);
			//loadBtn.setCaption(MaterialTheme.BUTTON_BORDER);
			loadBtn.addStyleName(ValoTheme.BUTTON_SMALL+ " "+ ValoTheme.BUTTON_PRIMARY);
			
			if(tmp.getFileStorageId() != null && tmp.getFile() == null) {
				JaxWsClientFactoryBean factory = new JaxWsClientFactoryBean();
				factory.setServiceClass(DocumentService.class);
				factory.setAddress(JaxWsProxyFactoryBeanAddress.getAddress());
				DocumentService service = (DocumentService) factory.create();
				final DataResponse data = service.getData(tmp.getFileStorageId().intValue(), 0);
				UnitResponse unit = service.getInformation(tmp.getFileStorageId().intValue(), 0);
				
				StreamSource streamResource = new StreamSource() {
					
					@Override
					public InputStream getStream() {
						// TODO Auto-generated method stub
						try {
							return data.getData().getInputStream();
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						return null;
					}
				};
				
				StreamResource resource = new StreamResource(streamResource, unit.getFileName());
				if(unit.getContentType() != null) resource.setMIMEType(unit.getContentType());
				FileDownloader downloader = new FileDownloader(resource);
				downloader.extend(loadBtn);
			}
			
			loadBtn.setIcon(FontAwesome.CLOUD_DOWNLOAD); //loadResourse
			loadBtn.addStyleName(ValoTheme.BUTTON_SMALL+ " "+ValoTheme.BUTTON_PRIMARY );
			ltmp.addComponent(loadBtn);
///////////////Btn deleted			
			Button delBtn = new Button("Deleted");
			delBtn.setData(tmp);
			//delBtn.setIcon(FontAwesome.DELICIOUS);
			delBtn.addClickListener(new Button.ClickListener() {
				
				@Override
				public void buttonClick(ClickEvent event) {
					// TODO Auto-generated method stub
					Notification.show(((Attachment)event.getButton().getData()).getFileName() + " -deleted");
					attachments.remove((Attachment) event.getButton().getData());
					updateListAttachments();					
				}
			});
			
			delBtn.setIcon(FontAwesome.CLOSE); //deleteResourse
			delBtn.addStyleName(ValoTheme.BUTTON_SMALL+" "+ ValoTheme.BUTTON_DANGER);
			ltmp.addComponent(delBtn);
			list_att.addComponent(ltmp);
		}
	}  // End updateListAttachments()
	
	
}


