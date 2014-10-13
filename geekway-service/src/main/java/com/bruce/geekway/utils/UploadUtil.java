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

import com.bruce.foundation.util.Md5Util;
import com.bruce.geekway.constants.ConstConfig;

/**
 * 
 * @author liqian
 * 
 */
public class UploadUtil {

	public static final String FILE_SEPARTOR = System.getProperty("file.separator");

	private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

	/**
	 * 返回系统随机构造的文件名
	 * 
	 * @param userId
	 * @param fileName
	 * @param placeHolder
	 * @param time
	 * @return
	 */
	public static String getFileNameWithPlaceHolder(String userId, String fileName, String placeHolder, long time) {
		String extName = fileName.substring(fileName.lastIndexOf(".") + 1);

		StringBuffer sb = new StringBuffer();
		if (StringUtils.isNotBlank(userId)) {
			sb.append(userId);
			sb.append("_");
		}
		sb.append(Md5Util.md5Encode(userId + "_" + time));
		if (StringUtils.isNotEmpty(placeHolder)) {
			sb.append("_");
			sb.append(placeHolder);
		}
		sb.append(".");
		sb.append(extName);

		return sb.toString();
	}

	/**
	 * 生成文件名（格式为$userId_67bdd8e83ee08669ced059c3c4306ef.jpg）
	 * 
	 * @param userId
	 * @param fileName
	 * @return
	 */
	public static String getFileName(String userId, String fileName) {
		return getFileNameWithPlaceHolder(userId, fileName, null, System.currentTimeMillis());
	}

	// /**
	// * 获取文件保存的base路径，通常为$localDir/staticFile）
	// * @param userId
	// * @return
	// */
	// public static String getBasePath() {
	// String basePath = ConfigUtil.getString("upload_path_base");
	// return basePath;
	// }
	//
	//
	// public static String getBaseUrl() {
	// String domain = ConfigUtil.getString("img_domain");
	// String contextPath = ConfigUtil.getString("contextPath");
	// String basePath = domain + contextPath +
	// ConfigUtil.getString("upload_url_base");
	// return basePath;
	// }

	private static String getDictionary() {
		return getDictionary(System.currentTimeMillis());
	}

	private static String getDictionary(long time) {
		return simpleDateFormat.format(new Date(time));
	}

	/**
	 * 获取文件保存的相对路径(1、用于文件寻址，2、用于拼url)
	 * 
	 * @param userId
	 * @return
	 */
	public static String getFilePath(long time) {
		String filePath = ConfigUtil.getString("upload_path_file") + UploadUtil.FILE_SEPARTOR + getDictionary(time);
		return filePath;
	}

	/**
	 * 获取文件保存的相对路径(1、用于文件寻址，2、用于拼url)
	 * 
	 * @param userId
	 * @return
	 */
	public static String getFilePath() {
		return getFilePath(System.currentTimeMillis());
	}

	/**
	 * 获取图片保存的相对路径
	 * 
	 * @param userId
	 * @return
	 */
	public static String getImagePath(long time) {
		String imagePath = ConfigUtil.getString("upload_path_image") + UploadUtil.FILE_SEPARTOR + getDictionary(time);
		return imagePath;
	}

	/**
	 * 获取图片保存的相对路径
	 * 
	 * @param userId
	 * @return
	 */
	public static String getImagePath() {
		return getImagePath(System.currentTimeMillis());
	}

	/**
	 * 保存文件，返回url
	 * 
	 * @param data
	 * @param basePath
	 * @param dictionary
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	// public static String saveFile(byte[] data, String basePath, String
	// dictionary, String filename) throws IOException{
	// File dir = new File(basePath + UploadUtil.FILE_SEPARTOR + dictionary);
	// if(!dir.exists()){
	// dir.mkdirs();
	// }
	// File file = new File(dir, filename);
	// FileCopyUtils.copy(data, file);
	// return getBaseUrl() + UploadUtil.FILE_SEPARTOR + dictionary +
	// UploadUtil.FILE_SEPARTOR + filename;
	// }

	/**
	 * 保存文件，返回文件
	 * 
	 * @param data
	 * @param basePath
	 * @param dictionary
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static File saveFile(byte[] data, String dirPath, String filename) throws IOException {
		File dir = new File(dirPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File file = new File(dir, filename);
		FileCopyUtils.copy(data, file);
		return file;
	}

	/**
	 * 根据url判断文件是否存在
	 * 
	 * @param fileUrl
	 * @return
	 */
	public static boolean fileExists(String fileUrl) {
		File file = loadFileByUrl(fileUrl);
		if (file != null) {
			return true;
		}
		return false;
	}

	/**
	 * 根据url构造file
	 * 
	 * @param fileUrl
	 * @return
	 */
	public static File loadFileByUrl(String fileUrl) {
		if (fileUrl != null) {
			String abFilePath = fileUrl.replace(ConstConfig.UPLOAD_URL_BASE, ConstConfig.UPLOAD_PATH_BASE);
			File file = new File(abFilePath);
			if (file.exists()) {
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

	/**
	 * 获取七牛的缩略图链接
	 * 
	 * @param originalUrl
	 * @param width
	 * @param height
	 * @return
	 */
	public static String getQiniuResizeImageUrl(String originalUrl, int width, int height) {
		String itemUrl = originalUrl;
		if (width > 0) {
			itemUrl = originalUrl + "?imageView/2/w/" + width;
		}
		if (height > 0) {
			itemUrl = itemUrl + "/h/" + height;
		}
		return itemUrl;
	}

	public static void main(String[] args) {
	}
}
