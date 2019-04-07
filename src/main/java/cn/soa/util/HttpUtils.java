package cn.soa.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtils {
	private static Logger Log  = LoggerFactory.getLogger(HttpUtils.class);
	 
	 
    // 1.使用get方式发送报文
    public static String getData(String url) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        get.addHeader("Host", "weibo.com");
        get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36");
        get.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        get.addHeader("Accept-Language", "zh-CN,zh;q=0.9");
        get.addHeader("Cache-Control", "max-age=0");
        get.addHeader("Connection", "keep-alive");
        get.addHeader("Upgrade-Insecure-Requests", "1");
        get.addHeader("Accept-Encoding", "gzip, deflate, br");
//        get.addHeader("Referer", "https://weibo.com/5195741723/profile?topnav=1&wvr=6&is_all=1");
        get.addHeader(new BasicHeader("Cookie", "SINAGLOBAL=4699324671137.901.1544972775797; UM_distinctid=1688a0243ab59a-0c9382d6bf7d24-594d2a16-100200-1688a0243ae2fa; YF-V5-G0=73b58b9e32dedf309da5103c77c3af4f; _s_tentry=www.ichartjs.cn; Apache=107149619925.75044.1554306713830; ULV=1554306713857:1:1:1:107149619925.75044.1554306713830:; login_sid_t=2d562fc19f15bb8d07d95f70aac1c6a3; cross_origin_proto=SSL; Ugrow-G0=169004153682ef91866609488943c77f; TC-V5-G0=0cd4658437f38175b9211f1336161d7d; appkey=; WBtopGlobal_register_version=edef3632d17f5fb3; un=15123395154; httpsupgrade_ab=SSL; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9Wh96D2N7cZBzk.2Pgihf09f5JpX5o275NHD95QfeK.fS0BpS0z0Ws4Dqcjci--NiKLFi-zRi--NiKn4iKnNi--Xi-zRi-82i--NiKLWiKnXi--Xi-zRiK.ci--NiK.fiKyh; ALF=1585905380; SSOLoginState=1554369380; SUB=_2A25xobs0DeRhGeNP4lcW9C_LyT-IHXVS1qv8rDV8PUNbmtAKLUfFkW9NTkbuAj8woI0jI-BPVkPvFpeUSYHkUdsx; SUHB=010GrDy83cXpSL; wvr=6; TC-Page-G0=c427b4f7dad4c026ba2b0431d93d839e|1554371623|1554371621; wb_view_log_5195741723=1366*7681; UOR=,,www.baidu.com; YF-Page-G0=da1eb9ea7ccc47f9e865137ccb4cf9f3|1554433123|1554433117; webim_unReadCount=%7B%22time%22%3A1554433543903%2C%22dm_pub_total%22%3A3%2C%22chat_group_pc%22%3A0%2C%22allcountNum%22%3A25%2C%22msgbox%22%3A2%7D"));
        get.getParams().setParameter("http.protocol.allow-circular-redirects", true);
        get.getParams().setParameter("http.protocol.max-redirects", 110);
        try{
            CloseableHttpResponse response = client.execute(get);
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode==200){
                Log.info("远程调用成功.line={}",response.getStatusLine());
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity,"UTF-8");
            }
            return  null;
        }catch (IOException e){
            Log.error("远程调用失败.e={}",e.getMessage());
        }
        return  null;
    }
    public static String post(String type,String url,String data){
        String result = "";
        switch (type){
            case "xml":
                result =  postData(url,data,ContentType.APPLICATION_XML.toString());
                break;
            case "json":
                result = postData(url,data,ContentType.APPLICATION_JSON.toString());
                break;
            default:
                break;
        }
        return  result;
    }
 
 
    // 使用POST方法发送XML或者json数据
    public static String postData(String url, String xmlData,String contentType){
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        post.addHeader("Content-type",contentType);
        try{
            StringEntity entity = new StringEntity(xmlData);
            post.setEntity(entity);
            CloseableHttpResponse response = client.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode==200){
                Log.info("远程调用成功.line={}",response.getStatusLine());
                HttpEntity responseEntity = response.getEntity();
                return EntityUtils.toString(responseEntity);
            }
        }catch (IOException e){
            Log.error("远程调用失败.e={}",e.getMessage());
        }
        return  null;
    }
 
    // 使用POST方法发送FORM表单数据
    public static String postForm(String url, Map<String,String> map){
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        post.addHeader("Content-type",ContentType.APPLICATION_FORM_URLENCODED.toString());
        try{
            List<BasicNameValuePair> list = new ArrayList<>();
            for(Map.Entry<String,String> entry : map.entrySet()){
                list.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
            }
 
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list);
            post.setEntity(urlEncodedFormEntity);
            CloseableHttpResponse response = client.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode==200){
                Log.info("远程调用成功.line={}",response.getStatusLine());
                HttpEntity responseEntity = response.getEntity();
                return EntityUtils.toString(responseEntity);
            }
        }catch (IOException e){
            Log.error("远程调用失败.e={}",e.getMessage());
        }
        return  null;
    }
}
