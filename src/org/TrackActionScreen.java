package org;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.StringItem;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;

import cc.nnproject.json.JSON;
import cc.nnproject.json.JSONException;
import cc.nnproject.json.JSONObject;

public class TrackActionScreen implements ItemCommandListener, CommandListener {
	
	private Command play_cmd;
	private Command download_cmd;
	private String mp3_url;
	private Command back_cmd;

	public Displayable TrackActionScreen(String url) throws MediaException, IOException {
		mp3_url = new String();
		mp3_url = url;
		System.out.print(url);
		Form f = new Form("What to do?");
		StringItem play_bt = new StringItem("", "Play online", Item.BUTTON);
		StringItem download_bt = new StringItem("", "Download", Item.BUTTON);
		play_cmd = new Command("Play", Command.SCREEN, 0);
		download_cmd = new Command("Download", Command.SCREEN, 0);
		back_cmd = new Command("Back", Command.SCREEN, 0);
		play_bt.addCommand(play_cmd);
		play_bt.setItemCommandListener(this);                                  
		download_bt.addCommand(download_cmd);
		download_bt.setItemCommandListener(this);
		f.append(play_bt);
		f.append(download_bt);
		f.addCommand(back_cmd);
		f.setCommandListener(this);
		return f;
		
	}

	public void commandAction(Command arg0, Item arg1) {
		if (arg0 == play_cmd) {
			try {
				YTMP3.display.setCurrent(new PlayScreen().PlayScreen(mp3_url));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MediaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if (arg0 == download_cmd) {
			new DownloadScreen().DownloadScreen(mp3_url);
		}
		
	}

	public void commandAction(Command arg0, Displayable arg1) {
		if (arg0 == back_cmd) {
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

		
	}
