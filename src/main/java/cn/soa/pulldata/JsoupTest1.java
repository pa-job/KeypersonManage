package cn.soa.pulldata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jsoup.Jsoup;


import cn.soa.util.Native2AsciiUtils;

public class JsoupTest1 {
	private static Logger logger = LoggerFactory.getLogger( JsoupTest1.class );
	
//	public static void main(String[] args) {
//		Map<String,String> map =new HashMap<String,String>();
//		Response res;
//		try {
//			res = Jsoup.connect("http://weibo.com").cookies(map).method(Method.POST).execute();
//			String s=res.body();
//			System.out.println(s);
//			String[] ss=s.split("<script>FM.view");  
//	        int i=0;  
//	        String content=ss[8].split("\"html\":\"")[1].replaceAll("\\\\n", "").replaceAll("\\\\t", "").replaceAll("\\\\", "");  
//	        content=content.substring(0, content.length()<=13?content.length():content.length()-13);  
//	       // System.out.println(content);  
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
//		
//	}
	public static void main(String[] args) throws IOException {  
        Map<String, String> map = new HashMap<>();  
        map.put("Cookie","SINAGLOBAL=4699324671137.901.1544972775797; UM_distinctid=1688a0243ab59a-0c9382d6bf7d24-594d2a16-100200-1688a0243ae2fa; YF-V5-G0=73b58b9e32dedf309da5103c77c3af4f; _s_tentry=www.ichartjs.cn; Apache=107149619925.75044.1554306713830; ULV=1554306713857:1:1:1:107149619925.75044.1554306713830:; login_sid_t=2d562fc19f15bb8d07d95f70aac1c6a3; cross_origin_proto=SSL; Ugrow-G0=169004153682ef91866609488943c77f; TC-V5-G0=0cd4658437f38175b9211f1336161d7d; appkey=; WBtopGlobal_register_version=edef3632d17f5fb3; un=15123395154; httpsupgrade_ab=SSL; SSOLoginState=1554453484; wvr=6; wb_timefeed_5195741723=1; YF-Page-G0=3d0866500b190395de868745b0875841|1554466651|1554466577; WBStorage=201904081457|undefined; wb_view_log=1366*7681; UOR=,,www.baidu.com; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9Wh96D2N7cZBzk.2Pgihf09f5JpX5K2hUgL.Fo-p1K-NSh2Neoe2dJLoI0qLxKML1-zLBonLxKML1h.L1hMLxKBLBonLB-BLxKML1-2L1hBLxKBLBonL1KqLxKML1K-L12et; ALF=1586242702; SCF=AjrR03KQDEArNkyGjSjAA_61dp72Hano129rRSj_XXQtgglCRwQp_nSP1dVM6t8vL9OwU_B948pe9fi7_1_OAE0.; SUB=_2A25xroFfDeRhGeNP4lcW9C_LyT-IHXVS3fWXrDV8PUNbmtAKLVblkW9NTkbuAoQYs0-kWu5yPkNkL5d0IW20SqkB; SUHB=0G0unp93zz9Exr; wb_view_log_5195741723=1366*7681; TC-Page-G0=7a922a70806a77294c00d51d22d0a6b7|1554706708|1554706708; webim_unReadCount=%7B%22time%22%3A1554706808296%2C%22dm_pub_total%22%3A4%2C%22chat_group_pc%22%3A0%2C%22allcountNum%22%3A27%2C%22msgbox%22%3A2%7D");
        //map.put请根据自己的微博cookie得到  
  
        Response res = Jsoup.connect("http://weibo.com/u/6386087847")  
                .cookies(map).method(Method.GET).execute();  
        String s = res.body();  
        String[] ss = s.split("<script>FM.view");  
        int i = 0;  
        List<String> list = new ArrayList<>();        
        for (String x : ss) {   
        	 System.out.println("-------y------");  
             System.out.println(x);
        	if(i == 28) {
             	 if (x.contains("\"html\":\"")) {           	 
                     String value = getHtml(x);  
                     Document doc = Jsoup.parse(value);
                     list.add(value);     
                     int k = 1;
                     Elements links = doc.getElementsByAttributeValue("node-type", "feed_list_content");
                     for (Element link : links) {
                         try {
                        	 String content = link.text();
                        	 System.out.println("------------content-------------"); 
                        	 System.out.println(content); 
                         } catch (NullPointerException e) {
                             continue;
                         }
                     }
                 }
             }
        	i++;
        }  
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
