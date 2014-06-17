package com.GD.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.GD.model.CaptureVideo;


public class CaptureVideoUtil {
	/**
	 * 获取视频信息
	 * 
	 * @param url
	 * @return
	 * @throws Exception 
	 */
	public static CaptureVideo getCaptureVideoInfo(String url) throws Exception {
		CaptureVideo video = null;

		if (url.indexOf(Constants.VIDEO_DOMAIN_YOUKU) != -1) {
			try {
				video = getYouKuCaptureVideo(url);
			} catch (Exception e) {
				video = null;
			}
		} else if (url.indexOf(Constants.VIDEO_DOMAIN_TUDOU) != -1) {
			try {
				video = getTudouCaptureVideo(url);
			} catch (Exception e) {
				video = null;
			}
		} else if (url.indexOf(Constants.VIDEO_DOMAIN_KU6) != -1) {
			try {
				video = getKu6CaptureVideo(url);
			} catch (Exception e) {
				video = null;
			}
		} else if (url.indexOf(Constants.VIDEO_DOMAIN_CN6) != -1) {
			try {
				video = get6CaptureVideo(url);
			} catch (Exception e) {
				video = null;
			}
		} else if (url.indexOf(Constants.VIDEO_DOMAIN_WOLE) != -1) {
			try {
				video = get56CaptureVideo(url);
			} catch (Exception e) {
				video = null;
			}
		} else if (url.indexOf(Constants.VIDEO_DOMAIN_SINA) != -1) {
			try {
				video = getSinaCaptureVideo(url);
			} catch (Exception e) {
				video = null;
			}
		} else if (url.indexOf(Constants.VIDEO_DOMAIN_SOHU) != -1) {
			try {
				video = getSohuCaptureVideo(url);
			} catch (Exception e) {
				video = null;
			}
		} else if (url.indexOf(Constants.VIDEO_DOMAIN_IFENG) != -1) {
			try {
				video = getIfengCaptureVideo(url);
			} catch (Exception e) {
				video = null;
			}
		} else if (url.indexOf(Constants.VIDEO_DOMAIN_YINYUETAI) != -1) {
			try {
				video = getYinYueTaiCaptureVideo(url);
			} catch (Exception e) {
				video = null;
			}
		} else {
			//链接地址不在支持的列表中时返回原链接地址以及链接的页面标题
			Document doc = getURLContent(url);
			video = new CaptureVideo();
			video.setTitle(doc.title());
			video.setPageUrl(url);
		}

		return video;
	}

	/**
	 * 获取优酷视频
	 * 
	 * @param url 视频URL
	 */
	public static CaptureVideo getYouKuCaptureVideo(String url) throws Exception {
		Document doc = getURLContent(url);
		/**
		 * 获取视频地址
		 */
		String flash = getElementAttrById(doc, "link2", "value");
		
		int no = url.indexOf("id_");
        String videoId = url.substring(no+3, url.indexOf(".html"));
        //获取视频信息数据的URL对象
        URL myurl = new URL("http://v.youku.com/player/getPlayList/VideoIDS/"+videoId+"/timezone/+08/version/5/source/out?password=&ran=2513&n=3");
        //从URL对象中获取输入流
        InputStreamReader isr = new InputStreamReader(myurl.openStream());
        //封装
        BufferedReader br = new BufferedReader(isr);
        //readLine获取文本
        String urls = br.readLine();
        //关闭流
        br.close();
        //获取json对象
        JSONObject json = JSONObject.fromObject(urls);
        //获取json数据（data内）
        JSONArray arr = json.getJSONArray("data");
        JSONObject jsonObj = JSONObject.fromObject(arr.get(0));
		
		CaptureVideo video = new CaptureVideo();
		video.setTitle((String)jsonObj.get("title"));
		video.setThumbnail((String)jsonObj.get("logo"));
		video.setFlashUrl(flash);
//		video.setTime(time);
		video.setSource("优酷视频");
		video.setPageUrl(url);
//		video.setSummary(summary);
//		video.setHtmlCode(htmlCode);

		return video;
	}

	/**
	 * 获取土豆视频
	 * 
	 * @param url 视频URL
	 */
	public static CaptureVideo getTudouCaptureVideo(String url) throws Exception {
		Document doc = getURLContent(url);
		String content = doc.html();
		int beginLocal = content.indexOf("<script>document.domain");
		int endLocal = content.indexOf("</script>");
		content = content.substring(beginLocal, endLocal);
		
		/**
		 * 视频标题
		 */
		String title = doc.title().split("_")[0].trim();

		/**
		 * 获取视频地址
		 */
		String flash = getScriptVarByName("iid_code", content);
		flash = "http://www.tudou.com/v/" + flash.split("=")[1].trim() + "/v.swf";

		/**
		 * 获取视频缩略图
		 */
		String pic = getScriptVarByName("thumbnail", content);
		pic = pic.split("=")[1].trim();
		
		/**
		 * 视频简介
		 */
		String summary = doc.select("meta[name=Description]").attr("content");

		/**
		 * 获取视频时间
		 */
		String time = getScriptVarByName("time", content);

		CaptureVideo video = new CaptureVideo();
		video.setTitle(title);
		video.setThumbnail(pic);
		video.setFlashUrl(flash);
		video.setTime(time);
		video.setSource("土豆视频");
		video.setPageUrl(url);
		video.setSummary(summary);
		video.setHtmlCode(getHtmlCode(flash));

		return video;
	}

	/**
	 * 获取酷6视频
	 * 
	 * @param url 视频URL
	 */
	public static CaptureVideo getKu6CaptureVideo(String url) throws Exception {
		Document doc = getURLContent(url);
		
		/**
		 * 获取视频标题
		 */
		String title = doc.title().split(" ")[0].trim();

		/**
		 * 获取视频地址
		 */
		String flash = getElementAttrById(doc, "outSideSwfCode", "value");
		
		/**
		 * 获取视频地址
		 */
		String htmlCode = getElementAttrById(doc, "outSideHtmlCode", "value");

		/**
		 * 获取视频缩略图
		 */
		Element picEt = doc.getElementById("plCaptureVideosList");
		String time = null;
		String pic = null;
		if (picEt != null) {
			Elements pics = picEt.getElementsByTag("img");
			pic = pics.get(0).attr("src");

			/**
			 * 获取视频时长
			 */
			Element timeEt = picEt.select("span.review>cite").first();
			time = timeEt.text();
		} else {
			pic = doc.getElementsByClass("s_pic").first().text();
		}
		
		/**
		 * 视频简介
		 */
		String summary = doc.select("meta[name=Description]").attr("content");


		CaptureVideo video = new CaptureVideo();
		video.setTitle(title);
		video.setThumbnail(pic);
		video.setFlashUrl(flash);
		video.setTime(time);
		video.setSource("酷6视频");
		video.setPageUrl(url);
		video.setSummary(summary);
		video.setHtmlCode(htmlCode);
		return video;

	}

	/**
	 * 获取6间房视频
	 * 
	 * @param url 视频URL
	 */
	public static CaptureVideo get6CaptureVideo(String url) throws Exception {
		Document doc = getURLContent(url);
		
		/**
		 * 视频标题
		 */
		String title = doc.title().split("-")[0].trim();

		/**
		 * 获取视频缩略图
		 */
		Element picEt = doc.getElementsByClass("summary").first();
		String pic = picEt.getElementsByTag("img").first().attr("src");

		
		/**
		 * 视频简介
		 */
		String summary = doc.select("meta[name=Description]").attr("content");

		/**
		 * 获取视频地址
		 */
		Element flashEt = doc.getElementById("video-share-code");
		doc = Jsoup.parse(flashEt.attr("value"));
		String flash = doc.select("embed").attr("src");

		CaptureVideo video = new CaptureVideo();
		video.setTitle(title);
		video.setThumbnail(pic);
		video.setFlashUrl(flash);
		video.setSource("6间房");
		video.setPageUrl(url);
		video.setSummary(summary);
		video.setHtmlCode(getHtmlCode(flash));

		return video;
	}

	/**
	 * 获取56视频
	 * 
	 * @param url 视频URL
	 */
	public static CaptureVideo get56CaptureVideo(String url) throws Exception {
		Document doc = getURLContent(url);
		String content = doc.html();
		
		/**
		 * 视频标题
		 */
		String title = doc.getElementById("CaptureVideoTitle").select("h1").text();

		/**
		 * 获取视频缩略图
		 */
		int begin = content.indexOf("\"img\":\"");
		content = content.substring(begin + 7, begin + 200);
		int end = content.indexOf("\"};");
		String pic = content.substring(0, end).trim();
		pic = pic.replaceAll("\\\\", "");

		/**
		 * 获取视频地址
		 */
		String flash = "http://player.56.com" + url.substring(url.lastIndexOf("/"), url.lastIndexOf(".html")) + ".swf";
		
		/**
		 * 视频简介
		 */
		String summary = doc.select("meta[name=Description]").attr("content");

		CaptureVideo video = new CaptureVideo();
		video.setTitle(title);
		video.setThumbnail(pic);
		video.setFlashUrl(flash);
		video.setSource("56视频");
		video.setPageUrl(url);
		video.setSummary(summary);
		video.setHtmlCode(getHtmlCode(flash));
		return video;
	}
	
	/**
	 * 获取新浪视频
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static CaptureVideo getSinaCaptureVideo(String url) throws Exception {
		Document doc = getURLContent(url);
		
		/**
		 * 视频标题
		 */
		String title = doc.getElementById("videoTitle").text();
		
		/**
		 * 视频简介
		 */
		String summary = doc.getElementById("videoContent").text();
		
		String content = doc.html();
		int beginLocal = content.indexOf("document.domain");
		int endLocal = content.indexOf("</script>");
		content = content.substring(beginLocal+2, endLocal);
		
		/**
		 * 视频缩略图
		 */
		String pic = getScriptVarByName("pic", content);
		
		/**
		 * flash地址
		 */
		String flash = getScriptVarByName("swfOutsideUrl", content);
		
		CaptureVideo video = new CaptureVideo();
		video.setTitle(title);
		video.setThumbnail(pic);
		video.setFlashUrl(flash);
		video.setSource("新浪视频");
		video.setPageUrl(url);
		video.setSummary(summary);
		video.setHtmlCode(getHtmlCode(flash));
		return video;
	}
	
	/**
	 * 获取搜狐视频
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static CaptureVideo getSohuCaptureVideo(String url) throws Exception {
		Document doc = getURLContent(url);
		
		/**
		 * 视频标题
		 */
		String title = doc.title().split("-")[0].trim();
		
		/**
		 * 视频简介
		 */
		String summary = doc.select(".vIntro.clear > p").text();
		
		/**
		 * 视频缩略图
		 */
		String thumbnail = doc.getElementById("thumbnail").attr("src");
		
		String videoId = thumbnail.split("_")[2];
		
		/**
		 * 视频FLASH地址
		 */
		String flash = "http://share.vrs.sohu.com/"+videoId+"/v.swf&autoplay=false";
		
		CaptureVideo video = new CaptureVideo();
		video.setTitle(title);
		video.setThumbnail(thumbnail);
		video.setFlashUrl(flash);
		video.setSource("搜狐视频");
		video.setPageUrl(url);
		video.setSummary(summary);
		video.setHtmlCode(getHtmlCode(flash));
		return video;
	}
	
	/**
	 * 获取凤凰视频
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static CaptureVideo getIfengCaptureVideo(String url) throws Exception {
		Document doc = getURLContent(url);
		String content = doc.html();
		int beginLocal = content.indexOf("videoinfo={");
		int endLocal = content.indexOf("</script>");
		content = content.substring(beginLocal, endLocal);
		content = content.replaceAll("\"", "").replaceAll("\n", "").trim();
		content = content.substring(11, content.length()-2);
		String[] params = content.split(",");
		
		/**
		 * 视频标题
		 */
		String title = doc.select(".playtitle > h1").text();
		
		/**
		 * 视频简介
		 */
		String summary = doc.getElementById("full_des").text();
		summary = summary.replaceAll("<<收起", "");
		
		/**
		 * 视频FLASH地址
		 */
		String videoId = url.substring(url.lastIndexOf("/"), url.lastIndexOf(".shtml"));
		String flash = "http://v.ifeng.com/include/exterior.swf?guid="+videoId+"&AutoPlay=false";
		
		/**
		 * 视频缩略图
		 */
		String thumbnail = params[5].substring(4, params[5].length());
		
		/**
		 * 视频时长
		 */
		String time = params[3].substring(9, params[3].length());
		long ss = Long.parseLong(time);
		long hour=ss/3600;
	    long minute=ss%3600/60;
	    long second=ss%60;
		
		CaptureVideo video = new CaptureVideo();
		video.setTitle(title);
		video.setThumbnail(thumbnail);
		video.setFlashUrl(flash);
		video.setTime(hour+":"+minute+":"+second);
		video.setSource("凤凰视频");
		video.setPageUrl(url);
		video.setSummary(summary);
		video.setHtmlCode(getHtmlCode(flash));
		return video;
	}
	
	
	/**
	 * 获取音悦台MV
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static CaptureVideo getYinYueTaiCaptureVideo(String url) throws Exception {
		Document doc = getURLContent(url);
		
		/**
		 * 获取视频地址
		 */
		String flash = getElementAttrById(doc, "flashCode", "value");
		
		/**
		 * 视频标题
		 */
		String title = doc.getElementById("videoTitle").html()+" - "+doc.getElementById("videoArtistName").html();

		/**
		 * 视频内容
		 */
		String summary = doc.getElementById("videoContent").select("span").first().html();
		
		/**
		 * 视频缩略图
		 */
		String thumbnail = doc.select("meta[property=og:image]").attr("content");
		
		CaptureVideo video = new CaptureVideo();
		video.setTitle(title);
		video.setThumbnail(thumbnail);
		video.setFlashUrl(flash);
		video.setSource("音悦台MV");
		video.setPageUrl(url);
		video.setSummary(summary);
		video.setHtmlCode(getHtmlCode(flash));
		return video;
	}
	/**
	 * 获取script某个变量的值
	 * 
	 * @param name 变量名称
	 * @return 返回获取的值
	 */
	private static String getScriptVarByName(String name, String content) {
		String script = content;
		int begin = script.indexOf(name);
		script = script.substring(begin + name.length() + 2);
		int end = script.indexOf(",");
		script = script.substring(0, end);
		String result = script.replaceAll("'", "");
		return result.trim();
	}

	/**
	 * 根据HTML的ID键及属于名，获取属于值
	 * 
	 * @param id HTML的ID键
	 * @param attrName 属于名
	 * @return 返回属性值
	 */
	private static String getElementAttrById(Document doc, String id, String attrName) throws Exception {
		Element et = doc.getElementById(id);
		String attrValue = et.attr(attrName);
		return attrValue;
	}
	
	/**
	 * 根据FLASH地址生成页面代码
	 * @param flashUrl
	 * @return
	 * @throws Exception
	 */
	private static String getHtmlCode(String flashUrl) throws Exception {
		return "<embed src=\""+flashUrl+"\" allowFullScreen=\"true\" quality=\"high\" width=\"480\" height=\"400\" align=\"middle\" allowScriptAccess=\"always\" type=\"application/x-shockwave-flash\"></embed>";
	}
	
	public static CaptureVideo getQQVideo(String url) throws Exception {  
        Document doc = getURLContent(url);  
        /** 
         * 视频标题 
         */  
        String vid = url.substring(url.lastIndexOf("/")+1,url.lastIndexOf("."));  
        System.out.println(vid);  
        String title = doc.title().split("-")[0].trim();  
        /** 
         * 视频地址 
         */  
        String flash = "http://imgcache.qq.com/tencentvideo_v1/player/TencentPlayer.swf?vid="+vid;  
        CaptureVideo video = new CaptureVideo();  
        video.setTitle(title);  
        video.setSource("腾讯视频");  
        video.setFlashUrl(flash);  
        return video;  
    }  
	
	public static CaptureVideo getNetEaseVideo(String url) throws Exception {  
        Document doc = getURLContent(url);  
  
        String content = doc.html();  
        content = content.substring(content.indexOf("topicid"));  
        content = content.substring(0,content.indexOf("</script>"));  
        content = content.replaceAll("\"","").replaceAll("\n","").trim();  
        String contents[] = content.split(";");  
        /** 
         * 视频标题 
         */  
        String title = getScriptVarByName("title=",contents);  
        String topicid = getScriptVarByName("topicid=",contents);  
        String vid = getScriptVarByName("vid=",contents);  
        /** 
         * 视频缩略图 
         */  
        String imgpath = getScriptVarByName("imgpath=",contents);  
  
        /** 
         * 视频地址 
         */  
        String flash = "http://img1.cache.netease.com/flvplayer081128/~false~"+topicid+"_"+vid+"~"+imgpath.substring(7,imgpath.length()-4)+"~.swf";  
        CaptureVideo video = new CaptureVideo();  
        video.setTitle(title);  
        video.setThumbnail(imgpath);  
        video.setFlashUrl(flash);  
        video.setSource("网易视频");  
        video.setPageUrl(url);  
        // video.setSummary(summary);  
        video.setHtmlCode(getHtmlCode(flash));  
        return video;  
    }  
  
	private static String getScriptVarByName(String name, String contents[]) {
		String text = "";
		for (String s : contents) {
			if (s.contains(name)) {
				text = s.substring(s.indexOf(name) + name.length());
				break;
			}
		}
		return text;
	}

	/**
	 * 获取网页的内容
	 */
	private static Document getURLContent(String url) throws Exception {
		Document doc = Jsoup.connect(url).data("query", "Java").userAgent("Mozilla").cookie("auth", "token").timeout(5000).get();
		return doc;
	}
	
	public static void main(String[] args) throws Exception {
//		CaptureVideo video = CaptureVideoUtil.getYouKuCaptureVideo("http://v.youku.com/v_show/id_XNTA1MzI1NDQ4.html");
//		System.out.println(video.getFlashUrl());
//		System.out.println(video.getTitle());
//		System.out.println(video.getThumbnail());
		
		
		String url = "http://v.youku.com/v_show/id_XNTA1MzI1NDQ4.html";
	}
}
