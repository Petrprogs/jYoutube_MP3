package org;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.microedition.lcdui.*;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.io.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import cc.nnproject.json.*;

import org.SearchScreen;

public class YTMP3 extends MIDlet {

	public static Display display;
	public static TextBox tf;
	public static String YTDLP_SERVER = "http://192.168.1.112:8080";  

	public YTMP3() {
		display = Display.getDisplay(this);
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub

	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	protected void startApp() {
		display.setCurrent(new QueryScreen().QueryScreen());

	}


}
