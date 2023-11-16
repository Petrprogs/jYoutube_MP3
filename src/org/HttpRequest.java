package org;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

public class HttpRequest {

	static byte[] get_request(String url) throws IOException {
		System.out.println("GET " + url);
		HttpConnection hc = (HttpConnection) Connector.open(url);
		InputStream is = null;
		ByteArrayOutputStream o = null;
		try {
			hc.setRequestMethod("GET");
			int r = hc.getResponseCode();
			if (r != 200)
				throw new IOException(r + " " + hc.getResponseMessage());
			is = hc.openInputStream();
			o = new ByteArrayOutputStream();
			byte[] buf = new byte[256];
			int len;
			while ((len = is.read(buf)) != -1) {
				o.write(buf, 0, len);
			}
			return o.toByteArray();
		} catch (NullPointerException e) {
			e.printStackTrace();
			throw new IOException(e.toString());
		} finally {
			if (is != null)
				is.close();
			if (hc != null)
				hc.close();
			if (o != null)
				o.close();
		}
	}
	
	public static String EncodeUrl(String s) {
		StringBuffer sbuf = new StringBuffer();
		int len = s.length();
		for (int i = 0; i < len; i++) {
			int ch = s.charAt(i);
			if ((65 <= ch) && (ch <= 90)) {
				sbuf.append((char) ch);
			} else if ((97 <= ch) && (ch <= 122)) {
				sbuf.append((char) ch);
			} else if ((48 <= ch) && (ch <= 57)) {
				sbuf.append((char) ch);
			} else if (ch == 32) {
				sbuf.append("%20");
			} else if ((ch == 45) || (ch == 95) || (ch == 46) || (ch == 33) || (ch == 126) || (ch == 42) || (ch == 39)
					|| (ch == 40) || (ch == 41) || (ch == 58) || (ch == 47)) {
				sbuf.append((char) ch);
			} else if (ch <= 127) {
				sbuf.append(hex(ch));
			} else if (ch <= 2047) {
				sbuf.append(hex(0xC0 | ch >> 6));
				sbuf.append(hex(0x80 | ch & 0x3F));
			} else {
				sbuf.append(hex(0xE0 | ch >> 12));
				sbuf.append(hex(0x80 | ch >> 6 & 0x3F));
				sbuf.append(hex(0x80 | ch & 0x3F));
			}
		}
		return sbuf.toString();
	}
	
	private static String hex(int ch) {
		String x = Integer.toHexString(ch);
		return "%" + (x.length() == 1 ? "0" : "") + x;
	}
}
