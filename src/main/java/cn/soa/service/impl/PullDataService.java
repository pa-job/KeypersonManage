package cn.soa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.soa.dao.MicroblogDynamicMapper;
import cn.soa.entity.MicroblogDynamic;
import cn.soa.util.Native2AsciiUtils;

@Service
public class PullDataService {
	@Autowired	
	private MicroblogDynamicMapper microblogDynamicMapper;
	
	public int saveBlocContent(String blogNum,String blogContent ) {
		try {
			MicroblogDynamic blog = new MicroblogDynamic();
			blog.setMircoblogContent(blogContent);
			blog.setMircoblogNum(blogNum);
			int i = microblogDynamicMapper.insertSelective(blog);
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public List<String> getBlogInfo( String blogNum, String cookieStr ){
		ArrayList<String> blogContents = new ArrayList<String>();
		try {
			Map<String, String> map = new HashMap<>();  
	        map.put("Cookie", cookieStr );
	        //map.put请根据自己的微博cookie得到  
	  
	        Response res = Jsoup.connect("http://weibo.com/u/" + blogNum )
	                .cookies(map).method(Method.GET).execute();  
	        String s = res.body();  
	        String[] ss = s.split("<script>FM.view");  
	        int i = 0;  
	        List<String> list = new ArrayList<>();        
	        for (String x : ss) {   
	        	if(i == 28) {
	             	 if (x.contains("\"html\":\"")){           	 
	                     String value = getHtml(x);  
	                     Document doc = Jsoup.parse(value);
	                     list.add(value);     
	                     int k = 1;
	                     Elements links = doc.getElementsByAttributeValue("node-type", "feed_list_content");
	                     for (Element link : links) {
	                         try {
	                        	 String content = link.text();
	                        	 blogContents.add(content);
	                         } catch (NullPointerException e) {
	                             continue;
	                         }
	                     }
	                     System.out.println(blogContents);
	                     return blogContents;
	                 }
	             	 break;
	             }
	        	i++;
	        }  
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public static String getHtml(String s) {  
        String content = s.split("\"html\":\"")[1]  
                .replaceAll("(\\\\t|\\\\n|\\\\r)", "").replaceAll("\\\\\"", "\"")  
                .replaceAll("\\\\/", "/");  
        content = content.substring(0,  
                content.length() <= 13 ? content.length()  
                        : content.length() - 13);  
        return Native2AsciiUtils.ascii2Native(content);  
    } 
	

}	
