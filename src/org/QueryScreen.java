package org;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextBox;

public class QueryScreen implements CommandListener {
	
	private Command SearchCommand;
	static TextBox tf;

	public Displayable QueryScreen() {
		SearchCommand = new Command("Search", Command.OK, 0);
		tf = new TextBox("Query", "", 20, 0);
		tf.addCommand(SearchCommand);
		tf.setCommandListener(this);
		return tf;
		
	}

	public void commandAction(Command arg0, Displayable arg1) {
		if (arg0 == SearchCommand) {
			try {
				YTMP3.display.setCurrent(new SearchScreen().SearchScreen(tf.getString()));
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
