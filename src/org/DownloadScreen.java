package org;

import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.TextField;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.microedition.io.file.FileConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;

public class DownloadScreen implements CommandListener, ItemCommandListener {
	
	private Command ok;

	public Displayable DownloadScreen(String url) {
		byte[] mp3_bytes;
		FileConnection fc;
		try {
			mp3_bytes = HttpRequest.get_request(url);
			fc = (FileConnection) Connector.open("file:///c:/data/Youtube_Audio/", Connector.WRITE);
			if (!fc.exists()) {
				fc.mkdir();
			}
			fc = (FileConnection) Connector.open("file:///c:/data/Youtube_Audio/" + SearchScreen.list.getString(SearchScreen.list.getSelectedIndex()) + ".mp3", Connector.WRITE);
			fc.create();
			OutputStream os = fc.openOutputStream();
			os.write(mp3_bytes);
			Form f = new Form("Yay!");
			TextField yay = new TextField(null, "File downloaded succesfuly!", 20, 0);
			ok = new Command("OK", Command.OK, 0);
			f.append(yay);
			f.addCommand(ok);
			f.setCommandListener(this);
			YTMP3.display.setCurrent(f);
			System.out.print("MP3 Downloaded");
			os.close();
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
			Form f = new Form("Bruh...");
			TextField bruh = new TextField(null, "File download failed!", 20, 0);
			ok = new Command("OK", Command.OK, 0);
			f.append(bruh);
			f.addCommand(ok);
			f.setCommandListener(this);
			YTMP3.display.setCurrent(f);
		}
		return null;
				
	}

	public void commandAction(Command arg0, Displayable arg1) {
		if (arg0 == ok) {
			try {
				YTMP3.display.setCurrent(new SearchScreen().SearchScreen(QueryScreen.tf.getString()));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void commandAction(Command arg0, Item arg1) {
		
	}
}
