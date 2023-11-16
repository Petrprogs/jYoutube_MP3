package org;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextBox;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;

import cc.nnproject.json.JSON;
import cc.nnproject.json.JSONArray;
import cc.nnproject.json.JSONException;
import cc.nnproject.json.JSONObject;

public class SearchScreen implements CommandListener {

	private Command select;
	private Command back;
	private JSONArray data_array;
	static List list;
	static JSONObject data_json;

	public Displayable SearchScreen(String query) throws UnsupportedEncodingException, IOException {
		String yt_search = new String(HttpRequest.get_request("http://nnp.nnchan.ru/mahoproxy.php?u=" + HttpRequest.EncodeUrl("https://me0xn4hy3i.execute-api.us-east-1.amazonaws.com/staging/api/resolve/resolveYoutubeSearch?search=" + HttpRequest.EncodeUrl(query))),
				"UTF-8");
		data_array = JSON.getObject(yt_search).getArray("data");
		list = new List("Search results", List.IMPLICIT);
		select = new Command("Select", Command.OK, 0);
		back = new Command("Back", Command.BACK, 0);
		list.addCommand(back);
		list.setSelectCommand(select);
		list.setCommandListener(this);
		for (Enumeration e = data_array.elements(); e.hasMoreElements();) {
			JSONObject video_data = (JSONObject) e.nextElement();
			list.append(video_data.getString("title"), null);
		}

		return list;
	}

	private String replace(String needle, String replacement, String haystack) {
	    String result = "";
	    int index = haystack.indexOf(needle);
	    if(index==0) {
	        result = replacement+haystack.substring(needle.length());
	        return replace(needle, replacement, result);
	    }else if(index>0) {
	        result = haystack.substring(0,index)+ replacement +haystack.substring(index+needle.length());
	        return replace(needle, replacement, result);
	    }else {
	        return haystack;
	    }
	}
	
	public void commandAction(Command arg0, Displayable arg1) {
		if (arg0 == select) {
			for (Enumeration e = data_array.elements(); e.hasMoreElements();) {
				JSONObject video_data = (JSONObject) e.nextElement();
				if (video_data.getString("title") == list.getString(list.getSelectedIndex())) {
					try {
						String url_download = new String(
								HttpRequest.get_request(
										YTMP3.YTDLP_SERVER + "/download.php?id=" + video_data.getString("videoId")),
								"UTF-8");
						data_json = JSON.getObject(url_download);
						YTMP3.display.setCurrent(new TrackActionScreen()
								.TrackActionScreen("http://192.168.1.112:8080/" + data_json.getString("url")));
					} catch (JSONException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (MediaException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
		if (arg0 == back) {
			new YTMP3().startApp();
		}

	}
}
