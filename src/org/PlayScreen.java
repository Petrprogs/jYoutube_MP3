package org;

import java.io.IOException;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.StringItem;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;

import cc.nnproject.json.JSON;
import cc.nnproject.json.JSONObject;

public class PlayScreen implements ItemCommandListener {
	
	private Command stop_cmd;
	private Player p;

	public Displayable PlayScreen(String url) throws MediaException, IOException {
		System.out.print(url);
		Form f = new Form("Online play");
		StringItem stop_bt = new StringItem("", "Stop player", Item.BUTTON);
		stop_cmd = new Command("Stop player", Command.SCREEN, 0);
		stop_bt.addCommand(stop_cmd);
		stop_bt.setItemCommandListener(this);
		f.append(stop_bt);
		p = Manager.createPlayer(url);
		p.realize();
		p.prefetch();
		p.start();
		return f;
		
	}

	public void commandAction(Command arg0, Item arg1) {
		if (arg0 == stop_cmd) {
			try {
				p.stop();
				p.deallocate();
				YTMP3.display.setCurrent(new TrackActionScreen().TrackActionScreen("http://192.168.1.112:8080/" + SearchScreen.data_json.getString("url")));
			} catch (MediaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
