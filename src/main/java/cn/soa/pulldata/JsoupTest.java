package cn.soa.pulldata;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupTest {
	
	public static void main(String[] args) {
		String name = "孙杨";
//		String url = "https://s.weibo.com/weibo?q=" + name + "&Refer=index&page=1";
		String url = "https://weibo.com/sunyangswim";
		try {
			Document doc = Jsoup.connect(url).get();
			System.out.println(doc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
