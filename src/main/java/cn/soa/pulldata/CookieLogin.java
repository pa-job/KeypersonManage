package cn.soa.pulldata;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.DefaultCookieSpec;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import cn.soa.util.HttpUtils;


/**
 * 
 * 
 * @author zkw
 *
 */
public class CookieLogin {
    private HttpClient client;
    private HttpPost post;
    private HttpGet get;
    private BasicCookieStore cookieStore;

    public CookieLogin() {
        //cookie策略，不设置会拒绝cookie rejected，设置策略保存cookie信息
        cookieStore = new BasicCookieStore();
        CookieSpecProvider myCookie = new CookieSpecProvider() {

            public CookieSpec create(HttpContext context) {
                return new DefaultCookieSpec();
            }
        };
        Registry<CookieSpecProvider> rg = RegistryBuilder.<CookieSpecProvider> create().register("myCookie", myCookie)
                .build();

        client = HttpClients.custom().setDefaultCookieStore(cookieStore).setDefaultCookieSpecRegistry(rg).build();
        get = new HttpGet();
        post = new HttpPost();
    }

    public void Login() throws ClientProtocolException, IOException, URISyntaxException {

        String LoginUrl = "https://s.weibo.com/weibo?q=%E6%98%93%E5%BB%BA%E8%81%94&Refer=index";
        String cont =  HttpUtils.getData(LoginUrl);
//        get.setURI(new URI(LoginUrl));
//        get.addHeader("Host", "rm.api.weibo.com");
//        get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36");
//        get.addHeader("Accept", "*/*");
//        get.addHeader("Accept-Language", "zh-CN,zh;q=0.9");
//        get.addHeader("Accept-Encoding", "gzip, deflate, br");
//        get.addHeader("Referer", "https://weibo.com/5195741723/profile?topnav=1&wvr=6&is_all=1");
//        get.addHeader(new BasicHeader("Cookie", "SINAGLOBAL=4699324671137.901.1544972775797; UM_distinctid=1688a0243ab59a-0c9382d6bf7d24-594d2a16-100200-1688a0243ae2fa; YF-V5-G0=73b58b9e32dedf309da5103c77c3af4f; _s_tentry=www.ichartjs.cn; Apache=107149619925.75044.1554306713830; ULV=1554306713857:1:1:1:107149619925.75044.1554306713830:; login_sid_t=2d562fc19f15bb8d07d95f70aac1c6a3; cross_origin_proto=SSL; Ugrow-G0=169004153682ef91866609488943c77f; TC-V5-G0=0cd4658437f38175b9211f1336161d7d; appkey=; WBtopGlobal_register_version=edef3632d17f5fb3; un=15123395154; httpsupgrade_ab=SSL; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9Wh96D2N7cZBzk.2Pgihf09f5JpX5o275NHD95QfeK.fS0BpS0z0Ws4Dqcjci--NiKLFi-zRi--NiKn4iKnNi--Xi-zRi-82i--NiKLWiKnXi--Xi-zRiK.ci--NiK.fiKyh; ALF=1585905380; SSOLoginState=1554369380; SUB=_2A25xobs0DeRhGeNP4lcW9C_LyT-IHXVS1qv8rDV8PUNbmtAKLUfFkW9NTkbuAj8woI0jI-BPVkPvFpeUSYHkUdsx; SUHB=010GrDy83cXpSL; wvr=6; TC-Page-G0=c427b4f7dad4c026ba2b0431d93d839e|1554371623|1554371621; wb_view_log_5195741723=1366*7681; UOR=,,www.baidu.com; YF-Page-G0=da1eb9ea7ccc47f9e865137ccb4cf9f3|1554433123|1554433117; webim_unReadCount=%7B%22time%22%3A1554433543903%2C%22dm_pub_total%22%3A3%2C%22chat_group_pc%22%3A0%2C%22allcountNum%22%3A25%2C%22msgbox%22%3A2%7D"));
//        get.getParams().setParameter("http.protocol.allow-circular-redirects", true);
//        get.getParams().setParameter("http.protocol.max-redirects", 110);
//        
//        HttpResponse resp = client.execute(get);
//        HttpEntity entity = resp.getEntity();
//        String cont = EntityUtils.toString(entity);
        Document doc = Jsoup.parse(cont);
        System.out.println("返回页面内容："+doc.outerHtml());
//        System.out.println("获取的微博内容:" + cont);

    }

    public HttpClient getClient() {
        return client;
    }

    public void setClient(HttpClient client) {
        this.client = client;
    }

    public HttpPost getPost() {
        return post;
    }

    public void setPost(HttpPost post) {
        this.post = post;
    }

    public HttpGet getGet() {
        return get;
    }

    public void setGet(HttpGet get) {
        this.get = get;
    }

    public BasicCookieStore getCookieStore() {
        return cookieStore;
    }

    public void setCookieStore(BasicCookieStore cookieStore) {
        this.cookieStore = cookieStore;
    }

    public static void main(String[] args) throws ClientProtocolException, IOException, URISyntaxException {
        new CookieLogin().Login();
    }
}
