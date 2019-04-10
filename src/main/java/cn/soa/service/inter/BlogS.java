package cn.soa.service.inter;

import java.util.List;

import cn.soa.entity.KeyPerson;
import cn.soa.entity.MicroblogDynamic;
import cn.soa.entity.SystemConfig;

public interface BlogS {

	List<MicroblogDynamic> getBlogS();

	SystemConfig getCookieStrS();

	List<MicroblogDynamic> startPullBlogS();

	List<KeyPerson> getBlogPersonAllS();

	int saveBlocContent(String blogNum, String blogContent);

	List<String> getBlogInfo(String blogNum, String cookieStr);

	String getHtml(String s);

	int saveSCS(String cookie);

}
