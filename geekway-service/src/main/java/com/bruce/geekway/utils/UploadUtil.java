package com.bruce.geekway.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.FileCopyUtils;

/**
 * 
 * @author liqian
 *
 */
public class UploadUtil {

    public static final String FILE_SEPARTOR = System.getProperty("file.separator");
    
    private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    
    /**
     * 返回系统生成的文件名
     * @param userId
     * @param fileName
     * @param placeHolder
     * @param time
     * @return
     */
    public static String getFileNameWithPlaceHolder(int userId, String fileName, String placeHolder, long time) {
        String extName = fileName.substring(fileName.lastIndexOf(".") + 1);

        StringBuffer sb = new StringBuffer();
        sb.append(userId);
        sb.append("_");
        sb.append(Md5Utils.md5Encode(userId + "_" + time));
        if (StringUtils.isNotEmpty(placeHolder)) {
            sb.append("_");
            sb.append(placeHolder);
        }
        sb.append(".");
        sb.append(extName);

        return sb.toString();
    }

    public static String getFileName(int userId, String fileName) {
        return getFileNameWithPlaceHolder(userId, fileName, null, System.currentTimeMillis());
    }
    
    /**
   	 * 获取文件保存的base路径
   	 * @param userId
   	 * @return
   	 */
   	public static String getBasePath() {
   		String basePath = ConfigUtil.getString("upload_path_base");
   		return basePath;
   	}
   	
//   	public static String getDomain() {
//        String basePath = ConfigUtil.getString("domain");
//        return basePath;
//    }
   	
    public static String getBaseUrl() {
    	String domain = ConfigUtil.getString("img_domain");
    	String contextPath = ConfigUtil.getString("contextPath"); 
    	String basePath = domain + contextPath + ConfigUtil.getString("upload_url_base");
        return basePath;
    }
   	
   	private static String getDictionary() {
        return simpleDateFormat.format(new Date());
    }
   	
   	private static String getDictionary(long time) {
        return simpleDateFormat.format(new Date(time));
    }

   /**
	 * 获取用户图片保存的相对路径
	 * @param userId
	 * @return
	 */
	public static String getImagePath(long time) {
		String imagePath = ConfigUtil.getString("upload_path_image") + UploadUtil.FILE_SEPARTOR + getDictionary(time) ;
		return imagePath;
	}
	
	/**
     * 获取用户图片保存的相对路径
     * @param userId
     * @return
     */
    public static String getImagePath() {
        return getImagePath(System.currentTimeMillis());
    }
    
	
	/**
	 * 保存文件，返回url
	 * @param data
	 * @param basePath
	 * @param dictionary
	 * @param filename
	 * @return
	 * @throws IOException
	 */
    public static String saveFile(byte[] data, String basePath, String dictionary, String filename) throws IOException{
        File dir = new File(basePath + UploadUtil.FILE_SEPARTOR + dictionary);
        if(!dir.exists()){
            dir.mkdirs();
        }
        File file = new File(dir, filename);
    	FileCopyUtils.copy(data, file);
		return getBaseUrl()  + UploadUtil.FILE_SEPARTOR + dictionary + UploadUtil.FILE_SEPARTOR + filename;
    }
    
    
    /**
     * 根据url判断文件是否存在
     * @param fileUrl
     * @return
     */
    public static boolean fileExists(String fileUrl){
    	File file = loadFileByUrl(fileUrl);
    	if(file!=null){
    		return true;
    	}
    	return false;
    }
    
    /**
     * 根据url构造file
     * @param fileUrl
     * @return
     */
    public static File loadFileByUrl(String fileUrl){
    	if(fileUrl!=null){
    		String abFilePath = fileUrl.replace(getBaseUrl(), getBasePath());
    		File file = new File(abFilePath);
    		if(file.exists()){
    			return file;
    		}
    	}
    	return null;
    }
    
    
    
    
	public static byte[] file2bytes(File file) throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(file));
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while (-1 != (len = in.read(buffer, 0, buf_size))) {
				bos.write(buffer, 0, len);
			}
			return bos.toByteArray();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			bos.close();
		}
	}
}
