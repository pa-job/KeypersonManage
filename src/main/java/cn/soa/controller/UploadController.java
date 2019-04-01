package cn.soa.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.soa.entity.CountryAreaView;
import cn.soa.entity.headResult.ResultJson;
import cn.soa.service.inter.AreaVideoInter;

@RestController
@RequestMapping(value="/video",produces={"application/json; charset=UTF-8"})
public class UploadController {
	@Autowired
	private AreaVideoInter  AreaVideo;
	@Value("${spring.fileupload.destination}")
	private String path;
	@RequestMapping(value="/uploadMultiFile/{id}",method=RequestMethod.POST)
	public ResultJson<Integer> uploadMultiFile(@PathVariable("id") Integer id ,@RequestParam("file")MultipartFile[] files){
		//判断视频是否全部上传成功
		System.out.println(path);
		Boolean flag=true;
		StringBuffer sb = new StringBuffer();
		List<CountryAreaView> list= new ArrayList<CountryAreaView>();
		if(files!=null&&files.length>0) {
			for(int i=0;i<files.length;i++) {
				if(files[i].isEmpty()) {
					flag=false;
					sb.append(""+i+",");
					continue;
				};
				//获取成功上传的视屏的基本信息，存储到对应的文件夹，并存入数据库
				String name=files[i].getOriginalFilename();
				String Url=path.replace(" ", "")+File.separator+files[i].getOriginalFilename();
				list.add(new CountryAreaView(id,name,Url));
				try {
					System.out.println(path+File.separator+files[i].getOriginalFilename());
					FileUtils.writeByteArrayToFile(new File(Url), files[i].getBytes());
					
				} catch (IOException e) {
					return new ResultJson<Integer>(1,"文件保存异常");
				}
			};
			if(list.size()>0) {
				//文件上传成功的同时写入数据库
				AreaVideo.insetAreaVideoBatch(list);
			}
			if(flag) {
				return new ResultJson(0,"视频上传成功");
			}else {
				return new ResultJson(0,"第"+sb+"个视频上传失败，请重新上传");
			}
		};
		 return new ResultJson(1,"视频上传失败，请重新上传");

	}
}
