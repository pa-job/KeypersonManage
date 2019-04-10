package cn.soa.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.soa.entity.MicroblogDynamic;
import cn.soa.entity.SystemConfig;
import cn.soa.entity.headResult.ResultJson;
import cn.soa.entity.headResult.UserTableJson;
import cn.soa.service.inter.BlogS;

@RestController
@RequestMapping("/blog")
public class BlogContro {
	private static Logger logger = LoggerFactory.getLogger( BlogContro.class );
	@Autowired
	private BlogS blogS;
	
	@GetMapping("/list")
	public UserTableJson<List<MicroblogDynamic>> getGlogsC() {
		logger.debug("--C----------获取所有微博信息-----------");
		List<MicroblogDynamic> blogs = blogS.getBlogS();
		if( blogs != null ) {
			return new UserTableJson<List<MicroblogDynamic>>( 
					"", 0,"成功", blogs, blogs.size(), true );
		}
		return new UserTableJson<List<MicroblogDynamic>>( 
				"", 0, "失败", blogs, 0, true );
	}
	
	@GetMapping("/system")
	public ResultJson<SystemConfig> getSystemCC() {
		logger.debug("--C----------获取系统配置cookie-----------");
		SystemConfig systemconfig = blogS.getCookieStrS();
		if( systemconfig != null ) {
			return new ResultJson<SystemConfig>( 0, "查询数据成功", systemconfig );
		}else {
			return new ResultJson<SystemConfig>( 1, "查询数据失败", null );
		}
	}
	
	@PostMapping("/system")
	public ResultJson<Integer> saveSCC( @RequestParam("cookie") String cookie ){
		int i = blogS.saveSCS(cookie);
		if( i > 0 ) {
			return new ResultJson<Integer>( 0, "保存数据成功", 1 );
		}
		return new ResultJson<Integer>( 1, "保存数据失败", null );
	}
	
	@PostMapping("/start")
	public ResultJson<List<MicroblogDynamic>> getNewBlogC(){
		logger.debug("--C----------启动微博监听-----------");
		//检查
		SimpleDateFormat simdf = new SimpleDateFormat("YYYY-M-dd");
		Calendar cal = Calendar.getInstance();
		String ymd = simdf.format( cal.getTime() );
		logger.debug( ymd );
		SystemConfig systemconfig = blogS.getCookieStrS();
		if( ymd.equals( systemconfig.getStime() )) {
			//开启监听
			List<MicroblogDynamic> newBlogs = blogS.startPullBlogS();
			return new ResultJson<List<MicroblogDynamic>>( 0, "监听开启", newBlogs );
		}
		return new ResultJson<List<MicroblogDynamic>>( 1, "未开启监听", null );
	}
}
