package org.hrd.uploadmultifiles.utinity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Component
public class FileUploader {
	private String absolutePath;
	private String resourcePath;
	private CommonsMultipartFile commonsMultipartFile;
	private MultipartFile multipartFile;
	private String filename;
	private String resourcesHandler;
	
	public FileUploader(){}

	public FileUploader(String resourcePath,String resourcesHandler,CommonsMultipartFile file){
		this.setResourcePath(resourcePath);
		this.commonsMultipartFile=file;
		this.setResourcesHandler(resourcesHandler);
	}
	
	public FileUploader(String resourcePath,String resourcesHandler,MultipartFile file){
		this.setResourcePath(resourcePath);
		this.multipartFile=file;
		this.setResourcesHandler(resourcesHandler);
	}
	//upload new image oldFileName=null but it !null replace image with oldFileName
	public boolean upload(String oldFileName){
		String name=null;
		boolean uploadSuccess=false;
		if(this.multipartFile!=null || this.commonsMultipartFile!=null){
			if(this.commonsMultipartFile==null)
			name=this.multipartFile.getOriginalFilename();
			else name=this.commonsMultipartFile.getOriginalFilename();
			
			//byte[] bytes =file.getBytes();
			
			if(oldFileName==null){
				UUID uuid=UUID.randomUUID();
				String extension=name.substring(name.indexOf(".")+1);
				//get file name 
				this.filename=uuid.toString()+"."+extension;
			}else{
				this.filename=oldFileName;
			}
			File originalPath = new File(this.getResourcePath());

			if (!originalPath.exists()) {
				//System.out.println("NOT EXISTS");
				originalPath.mkdirs();
				
			}
			//System.out.println("parent :"+originalPath.getParent());
			//String baseUrl="http://localhost:2017";
			this.setAbsolutePath(this.getResourcesHandler()+this.filename);
			BufferedOutputStream bus=null;
			try{
				byte[] bytes=new byte[1024];
				if(this.commonsMultipartFile==null) bytes =multipartFile.getBytes();
				else bytes =commonsMultipartFile.getBytes();
				bus=new BufferedOutputStream(new FileOutputStream(
						new File(this.getResourcePath()+"/"+this.filename)));
				bus.write(bytes);
				uploadSuccess=true;
			}catch(FileNotFoundException e){
				e.printStackTrace();
			}catch(IOException isE){
				isE.printStackTrace();
			}finally{
				try {
					bus.close();
				} catch (Exception e) {
					e.printStackTrace();
				}		
			}
				
		}
		return uploadSuccess;
	}
	
	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getAbsolutePath() {
		return absolutePath;
	}
	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public String getResourcesHandler() {
		return resourcesHandler;
	}

	public void setResourcesHandler(String resourcesHandler) {
		this.resourcesHandler = resourcesHandler;
	}

	public CommonsMultipartFile getCommonsMultipartFile() {
		return commonsMultipartFile;
	}

	public void setCommonsMultipartFile(CommonsMultipartFile commonsMultipartFile) {
		this.commonsMultipartFile = commonsMultipartFile;
	}

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
}
