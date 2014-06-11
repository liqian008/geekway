package com.edb.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Util {

	public static String encodeUri(String s) {
		String str = "";
		try {
			str = URLEncoder.encode(s, "UTF-8");
		} catch (Exception e) {
			throw new java.lang.RuntimeException("encode error !");
		}
		return str;
	}

	public static String getMD5(String input) {

		String result = null;
		try {

			MessageDigest md = MessageDigest.getInstance("MD5");

			result = byte2hex(md.digest(input.getBytes("utf-8")));

		} catch (Exception e) {

			throw new java.lang.RuntimeException("sign error !");

		}

		return result;
	}

	public static String md5Signature(TreeMap<String, String> params, String secret) {

		String result = null;

		StringBuffer orgin = getBeforeSign(params, new StringBuffer(secret));

		if (orgin == null)

			return result;

		// orgin.append(secret);

		try {

			MessageDigest md = MessageDigest.getInstance("MD5");

			result = byte2hex(md.digest(orgin.toString().getBytes("utf-8")));

		} catch (Exception e) {

			throw new java.lang.RuntimeException("sign error !");

		}

		return result;

	}

	private static String byte2hex(byte[] b) {

		StringBuffer hs = new StringBuffer();

		String stmp = "";

		for (int n = 0; n < b.length; n++) {

			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));

			if (stmp.length() == 1)

				hs.append("0").append(stmp);

			else

				hs.append(stmp);

		}

		return hs.toString().toUpperCase();

	}

	private static StringBuffer getBeforeSign(TreeMap<String, String> params, StringBuffer orgin) {

		if (params == null)

			return null;

		Map<String, String> treeMap = new TreeMap<String, String>();

		treeMap.putAll(params);

		Iterator<String> iter = treeMap.keySet().iterator();
		while (iter.hasNext()) {

			String name = (String) iter.next();

			orgin.append(name).append(params.get(name));

		}
		return orgin;

	}

	public static String getResult(String urlStr, String content) {

		URL url = null;

		HttpURLConnection connection = null;

		try {

			url = new URL(urlStr);

			connection = (HttpURLConnection) url.openConnection();

			connection.setDoOutput(true);

			connection.setDoInput(true);

			connection.setRequestMethod("POST");

			connection.setUseCaches(false);

			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			connection.connect();

			DataOutputStream out = new DataOutputStream(connection.getOutputStream());

			out.write(content.getBytes("utf-8"));

			out.flush();

			out.close();

			BufferedReader reader =

			new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));

			StringBuffer buffer = new StringBuffer();

			String line = "";

			while ((line = reader.readLine()) != null) {

				buffer.append(line);

			}

			reader.close();

			return buffer.toString();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			if (connection != null) {

				connection.disconnect();

			}

		}

		return null;

	}

}
