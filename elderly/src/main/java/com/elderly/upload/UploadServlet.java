/*package com.elderly.upload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cloud.hdfs.HDFSOperation;
import com.cloud.model.Resource;
import com.cloud.service.ResourceServiceI;
import com.cloud.service.impl.ResourceServiceImpl;



public class UploadServlet extends HttpServlet {

    public static final String UPLOAD_DIR = "/tmp";
   // private Configuration conf =null;
    private ResourceServiceI resourceService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int resumableChunkNumber        = getResumableChunkNumber(request);
       
        ResumableInfo info = getResumableInfo(request);
     
       RandomAccessFile raf = new RandomAccessFile(info.resumableFilePath, "rw");

        //Seek to position
        raf.seek((resumableChunkNumber - 1) * (long)info.resumableChunkSize);

        //Save to file
        InputStream is = request.getInputStream();
        long readed = 0;
        long content_length = request.getContentLength();
        byte[] bytes = new byte[1024 * 100];
        while(readed < content_length) {
            int r = is.read(bytes);
            if (r < 0)  {
                break;
            }
            raf.write(bytes, 0, r);
            readed += r;
        }
        raf.close();

        

        //Mark as uploaded.
        info.uploadedChunks.add(new ResumableInfo.ResumableChunkNumber(resumableChunkNumber));
        if (info.checkIfUploadFinished()) { //Check if all chunks uploaded, and change filename
        	Resource resource=new Resource();
        	resource.setResourceName(info.resumableFilename);
        	resource.setSize((int)info.resumableTotalSize);       	
        	resource.setUploaderId(Integer.parseInt(request.getParameter("uploaderId")));
        	resource.setUploadTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        	resource.setFolderId(Integer.parseInt(request.getParameter("folderId")));
        	resource.setStatus(0); 
        	resource.setDescription("");
        	resource.setPath("");
        	resource.setDownloadTimes(0);
        	resource.setType(getType(info.resumableFilename));
        	AbstractApplicationContext ctx
            = new ClassPathXmlApplicationContext(new String []{"classpath:spring.xml","classpath:spring-mybatis.xml"});
        	resourceService = (ResourceServiceI)ctx.getBean("resourceService");
        	resourceService.addResource(resource);
        	 response.getWriter().print("All finished.");
        	HDFSOperation hdfs=new HDFSOperation();
        	hdfs.sendFile("/upload",info.resumableFilePath);
            if(deleteFile(info.resumableFilePath)){
            	ResumableInfoStorage.getInstance().remove(info);
            }else{
            	System.out.println("删除文件失败");
            }
           
        } else {
            response.getWriter().print("Upload");
        }
    }

    public String getType(String fileName){
    	String mainType=null;
    	String[] str=fileName.trim().split("\\.");
    	String type = str[str.length-1];
    	if("avi".equals(type)||"flv".equals(type)||"rm".equals(type)||"rmvb".equals(type)||"mkv".equals(type)||"mp4".equals(type))
    	{
    		mainType="video";
    	}
    	else if("doc".equals(type)||"pdf".equals(type)||"txt".equals(type)||"xls".equals(type)||"docx".equals(type)||"ppt".equals(type)||"pptx".equals(type)||"xlsx".equals(type))
    	{
    		mainType="doc";
    	}else if("mp3".equals(type)||"wav".equals(type)||"afc".equals(type))
    	{
    		mainType="music";
    	}else if("jpg".equals(type)||"jpge".equals(type)||"gif".equals(type)||"png".equals(type)||"bmp".equals(type))
    	{
    		mainType="picture";
    	}else{
    		mainType="others";
    	}
    	return mainType;
    }
    public boolean deleteFile(String sPath) {  
        boolean flag = false;  
        File file = new File(sPath);  
        // 路径为文件且不为空则进行删除  
        if (file.isFile() && file.exists()) { 
            file.delete();  
            flag = true;  
        }  
        return flag;  
    }  
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int resumableChunkNumber        = getResumableChunkNumber(request);

        ResumableInfo info = getResumableInfo(request);

        if (info.uploadedChunks.contains(new ResumableInfo.ResumableChunkNumber(resumableChunkNumber))) {
            response.getWriter().print("Uploaded."); //This Chunk has been Uploaded.
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private int getResumableChunkNumber(HttpServletRequest request) {
        return HttpUtils.toInt(request.getParameter("resumableChunkNumber"), -1);
    }

    private ResumableInfo getResumableInfo(HttpServletRequest request) throws ServletException {
        String base_dir = UPLOAD_DIR;

        int resumableChunkSize          = HttpUtils.toInt(request.getParameter("resumableChunkSize"), -1);
        long resumableTotalSize         = HttpUtils.toLong(request.getParameter("resumableTotalSize"), -1);
        String resumableIdentifier      = request.getParameter("resumableIdentifier");
        String resumableFilename        = request.getParameter("resumableFilename");
        String resumableRelativePath    = request.getParameter("resumableRelativePath");
        //Here we add a ".temp" to every upload file to indicate NON-FINISHED
        String resumableFilePath        = new File(base_dir, resumableFilename).getAbsolutePath() + ".temp";

        ResumableInfoStorage storage = ResumableInfoStorage.getInstance();

        ResumableInfo info = storage.get(resumableChunkSize, resumableTotalSize,
                resumableIdentifier, resumableFilename, resumableRelativePath, resumableFilePath);
        if (!info.vaild())         {
            storage.remove(info);
            throw new ServletException("Invalid request params.");
        }
        return info;
    }
}
*/