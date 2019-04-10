package cn.soa.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.soa.dao.KeyPersonMapper;
import cn.soa.dao.MicroblogDynamicMapper;
import cn.soa.dao.SystemConfigMapper;
import cn.soa.entity.KeyPerson;
import cn.soa.entity.MicroblogDynamic;
import cn.soa.entity.SystemConfig;
import cn.soa.service.inter.BlogS;
import cn.soa.util.Native2AsciiUtils;
import lombok.Data;

@Service
public class BlogSI implements BlogS{
	private static Logger logger = LoggerFactory.getLogger( BlogSI.class );
	
	@Autowired
	private MicroblogDynamicMapper blogMapper;
	
	@Autowired
	private KeyPersonMapper kpMapper;
	
	@Autowired
	private SystemConfigMapper scMapper;
	
	@Override
	public int saveSCS( String cookie ) {
		SimpleDateFormat simdf = new SimpleDateFormat("YYYY-M-dd");
		Calendar cal = Calendar.getInstance();
		String ymd = simdf.format( cal.getTime() );
		SystemConfig s = new SystemConfig();
		s.setSid( "1" );
		s.setStime(ymd);
		s.setScontent( cookie );
		s.setSname( "cookie值" );
		logger.debug("--S----------保存cookie-----------" + s);
		try {
			int j = scMapper.delete();
			int i = scMapper.insert(s);
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}		
	}
	
	@Override
	public List<MicroblogDynamic> getBlogS(){
		try {
			List<MicroblogDynamic> blogs = blogMapper.selectAll();
			logger.debug("--S----------获取所有微博信息-----------" + blogs);
			return blogs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public SystemConfig getCookieStrS() {
		try {
			SystemConfig system = scMapper.selectById( "1" );
			return system;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	@Override
	public List<MicroblogDynamic> startPullBlogS() {
		ArrayList<MicroblogDynamic> newBlogs = new ArrayList<MicroblogDynamic>();
		try {
			List<KeyPerson> blogPersons = getBlogPersonAllS();		
			SystemConfig systemconfig = getCookieStrS();
			logger.debug("---S-----startPullBlogS---------" + blogPersons );
			for( KeyPerson k : blogPersons ) {
				ArrayList<MicroblogDynamic> blogContents ;
				List<String> blogs ;
				try {
					blogContents = blogMapper.selectByNum( k.getMircoblogNum() );
					blogs = getBlogInfo( k.getMircoblogNum(), systemconfig.getScontent());						
					logger.debug("---S-----startPullBlogS---------" + k.getMircoblogNum());
					logger.debug("---S-----startPullBlogS---------" + systemconfig.getScontent());				
					logger.debug("---S-----startPullBlogS---------" + blogContents );
					logger.debug("---S-----startPullBlogS---------" + blogs );
					if( blogContents == null || blogContents.isEmpty() ) {
						if( blogs != null  ) {
							for( String s : blogs ) {
								MicroblogDynamic newBlog = new MicroblogDynamic();
								newBlog.setMircoblogNum( k.getMircoblogNum() );
								newBlog.setMircoblogContent( filterEmoji1( s ) );
								newBlog.setRemark1( k.getName() );
								try {
									int i = blogMapper.insertSelective( newBlog );
									if( i > 0 ) newBlogs.add( newBlog );
								} catch (Exception e) {
									e.printStackTrace();
								}													
							}
						}
					}else {
						if( blogs != null  ) {
							for( String s : blogs ) {
								int flag = 1;
								if( blogContents != null && !blogContents.isEmpty()) {
									for( MicroblogDynamic  m : blogContents ) {
										if( m.getMircoblogContent() != null ) {
											if( !m.getMircoblogContent().contains(s.substring(3,8))) {
												 flag = 2;
											 }
										}								 
									}
								}
								
								if( flag == 2 ) {
									MicroblogDynamic newBlog = new MicroblogDynamic();
									newBlog.setMircoblogNum( k.getMircoblogNum() );
									newBlog.setMircoblogContent(  filterEmoji1( s ) );
									newBlog.setRemark1( k.getName() );
									try {
										int i = blogMapper.insertSelective( newBlog );
										if( i > 0 ) newBlogs.add( newBlog );
									} catch (Exception e) {
										e.printStackTrace();
									}		
								}
							}
						}	
					} 
				}catch (Exception e) {
					e.printStackTrace();
				}	
			}
			return newBlogs;
		} catch (Exception e) {
			e.printStackTrace();
			return newBlogs;
		}
	}
	
	@Override
	public List<KeyPerson> getBlogPersonAllS() {
		try {
			List<KeyPerson> blogPersons = kpMapper.selectByMark();
			return blogPersons;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public int saveBlocContent(String blogNum,String blogContent ) {
		try {
			MicroblogDynamic blog = new MicroblogDynamic();
			blog.setMircoblogContent(blogContent);
			blog.setMircoblogNum(blogNum);
			int i = blogMapper.insertSelective(blog);
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	@Override
	public List<String> getBlogInfo( String blogNum, String cookieStr ){
		ArrayList<String> blogContents = new ArrayList<String>();
		try {
			Map<String, String> map = new HashMap<>();  
	        map.put("Cookie", cookieStr );
	        //map.put请根据自己的微博cookie得到  
	  
	        Response res = Jsoup.connect("http://weibo.com/" + blogNum )
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
	
	@Override
	public String getHtml(String s) {  
        String content = s.split("\"html\":\"")[1]  
                .replaceAll("(\\\\t|\\\\n|\\\\r)", "").replaceAll("\\\\\"", "\"")  
                .replaceAll("\\\\/", "/");  
        content = content.substring(0,  
                content.length() <= 13 ? content.length()  
                        : content.length() - 13);  
        return Native2AsciiUtils.ascii2Native(content);  
    } 
	
	
	public static void main(String[] args) {
		String num = "louiskoo2008";
		String cookieStr = "SINAGLOBAL=4699324671137.901.1544972775797; UM_distinctid=1688a0243ab59a-0c9382d6bf7d24-594d2a16-100200-1688a0243ae2fa; YF-V5-G0=73b58b9e32dedf309da5103c77c3af4f; _s_tentry=www.ichartjs.cn; Apache=107149619925.75044.1554306713830; ULV=1554306713857:1:1:1:107149619925.75044.1554306713830:; login_sid_t=2d562fc19f15bb8d07d95f70aac1c6a3; cross_origin_proto=SSL; Ugrow-G0=169004153682ef91866609488943c77f; TC-V5-G0=0cd4658437f38175b9211f1336161d7d; appkey=; WBtopGlobal_register_version=edef3632d17f5fb3; un=15123395154; httpsupgrade_ab=SSL; SSOLoginState=1554453484; wvr=6; wb_timefeed_5195741723=1; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9Wh96D2N7cZBzk.2Pgihf09f5JpX5K2hUgL.Fo-p1K-NSh2Neoe2dJLoI0qLxKML1-zLBonLxKML1h.L1hMLxKBLBonLB-BLxKML1-2L1hBLxKBLBonL1KqLxKML1K-L12et; ALF=1586359734; SCF=AjrR03KQDEArNkyGjSjAA_61dp72Hano129rRSj_XXQtV_LfoHiF-yuQwyTxHRWnC5tD5ocGQPTUlcuqyPTdWmY.; SUB=_2A25xqMpqDeRhGeNP4lcW9C_LyT-IHXVS37yirDV8PUNbmtANLUH2kW9NTkbuAkCgXfOcROzqFjmG-3sPBMXKXL6y; SUHB=0oUoNRE7saHC53; YF-Page-G0=f3b64f1a7830d84b4697ff4a88f85125|1554823772|1554823747; UOR=,,localhost:8080; wb_view_log_5195741723=1366*7681; TC-Page-G0=4c4b51307dd4a2e262171871fe64f295|1554865473|1554865473; webim_unReadCount=%7B%22time%22%3A1554865572135%2C%22dm_pub_total%22%3A4%2C%22chat_group_pc%22%3A0%2C%22allcountNum%22%3A28%2C%22msgbox%22%3A2%7D";
		BlogSI blogSI = new BlogSI();
		List<String> blogInfo = blogSI.getBlogInfo( num, cookieStr );
		for( String s : blogInfo ) {
			System.out.println( s );
		}
		
	}
	
	public String filterEmoji( String source ) { 
		if (source != null && source.length() > 0) { 
			return source.replaceAll("[\ud800\udc00-\udbff\udfff\ud800-\udfff]", ""); 
		} else { 
			return source; 
		} 
	}
	
	public String filterEmoji1( String source ) { 
		byte[] b_text=source.getBytes();
		for (int i = 0; i < b_text.length; i++) 
		{ 
		    if((b_text[i] & 0xF8)== 0xF0){ 
		        for (int j = 0; j < 4; j++) {                         
		        b_text[i+j]=0x30;                    
		    } 
		    i+=3; 
		    } 
		} 
		String title=new String(b_text);
        title.replace("0000","");
		return title;
	}
}
