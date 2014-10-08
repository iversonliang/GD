package com.GD.web.controller;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.GD.dao.VideoInfoDao;
import com.GD.handler.CommentHandler;
import com.GD.handler.VideoHandler;
import com.GD.interceptor.LoginRequired;
import com.GD.model.CaptureVideo;
import com.GD.model.Comment;
import com.GD.model.Notice;
import com.GD.model.User;
import com.GD.model.Video;
import com.GD.model.VideoInfo;
import com.GD.model.WPPosts;
import com.GD.service.ApplyService;
import com.GD.service.CommentService;
import com.GD.service.LikeVideoService;
import com.GD.service.NoticeService;
import com.GD.service.TempImgService;
import com.GD.service.UserService;
import com.GD.service.VideoService;
import com.GD.type.ErrorTipsType;
import com.GD.type.HomeType;
import com.GD.type.SortType;
import com.GD.type.SourceSiteType;
import com.GD.type.StatusType;
import com.GD.type.TimeLimitType;
import com.GD.type.VideoGradeType;
import com.GD.type.VideoSourceType;
import com.GD.type.VideoType;
import com.GD.util.CaptureVideoUtil;
import com.GD.util.CheckUtil;
import com.GD.util.Constants;
import com.GD.util.DateUtil;
import com.GD.util.FileUtil;
import com.GD.util.Json;
import com.GD.util.Pager;
import com.GD.util.RequestUtil;
import com.GD.util.ViewUtil;
import com.GD.web.form.VideoForm;
import com.GD.web.form.VideoSearchForm;
import com.GD.web.vo.CommentVO;
import com.GD.web.vo.VideoVO;

@Controller
@RequestMapping(value = VideoController.DIR)
public class VideoController {
	
	public static final String DIR = "/video";
	
	@Autowired
	private ApplyService applyService;
	@Autowired
	private CommentHandler commentHandler;
	@Autowired
	private VideoHandler videoHandler;
	@Autowired
	private UserService userService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private LikeVideoService likeVideoService;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private TempImgService tempImgService;
	@Autowired
	private VideoService videoService;
	@Autowired
	private VideoInfoDao videoInfoDao;
	
	
	@LoginRequired
	@RequestMapping(value = "/contribute.do", method = RequestMethod.GET)
	public ModelAndView contribute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		int userId = (Integer) session.getAttribute("userId");
		boolean isActivate = applyService.isActivate(userId);
		ModelAndView model = ViewUtil.getView(DIR);
		if (!isActivate) {
			model = new ModelAndView("redirect:/inviteCode/index.do");
		}
		return model;
	}

	@LoginRequired
	@ResponseBody
	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	public Map<String, Object> add(HttpServletRequest request, HttpServletResponse response, HttpSession session, VideoForm form) {
		int userId = (Integer) session.getAttribute("userId");
		boolean result = false;
		Video video = null;
		String message = "";
		int errorCode = -1;
		try {
			video = videoHandler.getVideo(userId, form);
			result = videoService.add(video);
		} catch (Exception e) {
			e.printStackTrace();
			message = e.getMessage();
			errorCode = ErrorTipsType.toType(message).getKey();
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		map.put("message", message);
		map.put("errorCode", errorCode);
		return map;
	}
	
	@RequestMapping(value = "/video.do", method = RequestMethod.GET)
	public ModelAndView video(HttpServletRequest request, HttpServletResponse response, HttpSession session, int vid) {
		videoService.play(vid, session.getId());
		Video video = videoService.get(vid);
		User user = userService.get(video.getUserId());
		int pageSize = 10;
		List<Video> userVideoList = videoService.list(user.getUserId(), VideoSourceType.ALL, 0, 3);
		int commentCount = commentService.count(vid);
		List<Comment> commentList = commentService.list(vid, 0, pageSize);
		List<CommentVO> commentVoList = commentHandler.toVoList(commentList);
		boolean isLiked = false;
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId != null) {
			isLiked = likeVideoService.isLiked(userId, vid);
		}
		ModelAndView model = ViewUtil.getView(DIR);
		model.addObject("video", video);
		model.addObject("videoId", video.getVideoId());
		model.addObject("user", user);
		model.addObject("userVideoList", userVideoList);
		model.addObject("commentList", commentVoList);
		model.addObject("isLiked", isLiked);
		System.out.println("isLike:" + isLiked);
		model.addObject("hasMore", commentCount > pageSize);
		return model;
	}
	
	@RequestMapping(value = "/loadComment.do", method = RequestMethod.GET)
	public ModelAndView loadComment(HttpServletRequest request, HttpServletResponse response, HttpSession session, int vid, int page) {
		int count = commentService.count(vid);
		int pageSize = 10;
		Pager pager = new Pager(count, page, pageSize, "/video/loadComment.do", null);
		List<Comment> commentList = commentService.list(vid, pager.getFirst(), pageSize);
		List<CommentVO> commentVoList = commentHandler.toVoList(commentList);
		ModelAndView model = ViewUtil.getSpecifiedView("comment", "loadComment");
		model.addObject("videoId", vid);
		model.addObject("commentList", commentVoList);
		model.addObject("nextPage", page+1);
		model.addObject("hasMore", pager.getFirst() + pageSize < count);
		return model;
	}
	
	@RequestMapping(value = "/search.do", method = RequestMethod.GET)
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response, HttpSession session, VideoSearchForm form, Integer page) {
		if (page == null || page < 1) {
			page = 1;
		}
		Integer userId = (Integer) session.getAttribute("userId");
		boolean isLogin = userId != null;
		
		VideoType videoType = form.getVideoType() == null ? VideoType.ALL : VideoType.toType(form.getVideoType());
		SortType sortType = form.getSortType() == null ? SortType.ALL : SortType.toType(form.getSortType());
		VideoGradeType videoGradeType = form.getVideoGradeType() == null ? VideoGradeType.ALL : VideoGradeType.toType(form.getVideoGradeType());
		TimeLimitType timeLimitType = form.getTimeLimitType() == null ? TimeLimitType.ALL : TimeLimitType.toType(form.getTimeLimitType());
		
		int count = videoService.count(StatusType.NORMAL, videoType, HomeType.IGNORE, videoGradeType, VideoSourceType.ALL, timeLimitType, form.getKeyword(), false);
		Pager pager = new Pager(count, page, 16, "/video/search.do", null);
		List<Video> list = videoService.list(StatusType.NORMAL, videoType, HomeType.IGNORE, videoGradeType, VideoSourceType.ALL, sortType, timeLimitType, false, form.getKeyword(), pager.getFirst(), 16);
		List<VideoVO> voList = videoHandler.toVoList(list);
		ModelAndView model = ViewUtil.getView(DIR);
		model.addObject("isLogin", isLogin);
		model.addObject("pager", pager);
		model.addObject("videoVoList", voList);
		model.addObject("nav", "search");
		model.addObject("videoTypeList", VideoType.values());
		model.addObject("videoGradeTypeList", VideoGradeType.values());
		model.addObject("sortTypeList", SortType.values());
		model.addObject("timeLimitTypeList", TimeLimitType.values());
		model.addObject("keyword", form.getKeyword());
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value = "/love.do", method = RequestMethod.GET)
	public Map<String, Object> love(HttpServletRequest request, HttpServletResponse response, HttpSession session, int vid) {
		boolean isLogin = false;
		boolean result = false;
		Integer userId = (Integer)session.getAttribute("userId");
		int loveNum = 0;
		if (userId != null) {
			isLogin = true;
			videoService.checkVideo(vid);
			result = videoService.love(userId, vid);
			Video video = videoService.get(vid);
			loveNum = video.getLove();
			String username = (String) session.getAttribute("username");
			Notice notice = new Notice();
			notice.setImgUrl(Constants.SYS_DEFAULT_IMG);
			notice.setPosttime(new Date());
			User loveUser = userService.get(username);
			Assert.notNull(loveUser, "没有找到对应的用户[" + username + "]");
			String content = "你的视频《<a target=\"_blank\" href=\"/video/video.do?vid=" + vid + "\">" + video.getName() + "</a>》被<a target=\"_blank\" href=\"/video/personal.do?userId=" + loveUser.getUserId() + "\">" + username + "</a>点赞喔";
			notice.setContent(content);
			notice.setUserId(userId);
			noticeService.add(notice);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		map.put("isLogin", isLogin);
		map.put("loveNum", loveNum);
		return map;
	}
	
	@RequestMapping(value = "/personal.do", method = RequestMethod.GET)
	public ModelAndView personal(HttpServletRequest request, HttpServletResponse response, HttpSession session, int userId, Integer page, Integer type) {
		Object idObj = session.getAttribute("userId");
		if (page == null || page < 1) {
			page = 1;
		}
		if (type == null || type < 1) {
			type = 1;
		}
		int myId = 0;
		if (idObj != null) {
			myId = (Integer) idObj;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("type", type);
		
		int totalCount;
		List<Video> list;
		Pager pager;
		if (type == 3) {
			totalCount = likeVideoService.count(userId);
			pager = new Pager(totalCount, page, 16, "/video/personal.do", params);
			list = videoService.listLike(userId,  pager.getFirst(), 16);
		} else {
			VideoSourceType videoSourceType = VideoSourceType.toType(type);
			totalCount  = videoService.countByUser(userId, videoSourceType);
			pager = new Pager(totalCount, page, 16, "/video/personal.do", params);
			list = videoService.list(userId, videoSourceType, pager.getFirst(), 16);
		}
		
		boolean isMyPage = myId == userId;
		User user = userService.get(userId);
		List<VideoVO> videoVoList = videoHandler.toVoList(list);
		ModelAndView model = ViewUtil.getView(DIR);
		model.addObject("user", user);
		model.addObject("isMyPage", isMyPage);
		model.addObject("videoVoList", videoVoList);
		model.addObject("pager", pager);
		model.addObject("type", type);
		return model;
	}
	
	@LoginRequired
	@RequestMapping(value = "/delete.do", method = RequestMethod.GET)
	public String delete(HttpServletRequest request, HttpServletResponse response, HttpSession session, int vid, Integer type) {
		if (type == null) {
			type = 1;
		}
		if (type < 1 || type > 3) {
			throw new IllegalArgumentException("非法type[" + type + "]");
		}
		int userId = (Integer) session.getAttribute("userId");
		videoService.checkAuthor(userId, vid);
		if (type < 3) {
			videoService.del(userId, vid);
		}
		likeVideoService.delete(userId, vid);
		return "redirect:/video/personal.do?vid=" + vid + "&type=" + type + "&userId=" + userId;
	}
	
	@LoginRequired
	@RequestMapping(value = "/edit.do", method = RequestMethod.GET)
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response, HttpSession session, int vid) {
		int userId = (Integer) session.getAttribute("userId");
		Video video = videoService.checkAuthor(userId, vid);
		ModelAndView model = new ModelAndView();
		model.addObject("video", video);
		model.addObject("videoTypeList", VideoType.values());
		model.addObject("videoSourceTypeList", VideoSourceType.values());
		
		return model;
	}
	
	@LoginRequired
	@RequestMapping(value = "/cover.do", method = RequestMethod.GET)
	public ModelAndView cover(HttpServletRequest request, HttpServletResponse response, HttpSession session, int vid) {
		int userId = (Integer) session.getAttribute("userId");
		videoService.checkAuthor(userId, vid);
		Video video = videoService.get(vid);
		ModelAndView model = new ModelAndView();
		model.addObject("video", video);
		return model;
	}
	
	@LoginRequired
	@ResponseBody
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public Map<String, Object> update(HttpServletRequest request, HttpServletResponse response, HttpSession session, int vid, int videoSourceType, String name, int videoType, String label, String description) {
		int userId = (Integer) session.getAttribute("userId");
		Video video = videoService.checkAuthor(userId, vid);
		video.setVideoSourceType(videoSourceType);
		video.setName(name);
		video.setVideoType(videoType);
		video.setLabel(label);
		video.setDescription(description);
		CheckUtil.checkUpdateVideo(video);
		boolean result = videoService.update(video);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		return map;
	}
	
	/**
	 * 文件上传
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@LoginRequired
	@RequestMapping(value = "/upload.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> upload(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String uploadType = "coverImg";
		String SAVE_PATH = "/data/wwwdata/img/" + uploadType + "/";
		FileUtil.checkFolderPath(SAVE_PATH);
		Collection<Part> parts = null;
		try {
			request.setCharacterEncoding("UTF-8");
			parts = request.getParts();
		} catch (Exception e) {
			e.printStackTrace();
		}
		InputStream is = null;
		FileOutputStream fos = null;
		boolean result = false;
		String message = "";
		int errorCode = -1;
		String url = "";
		// 遍历所有的表单内容，将表单中的文件写入上传文件目录
		for (Iterator<Part> iterator = parts.iterator(); iterator.hasNext();) {
			Part part = iterator.next();
			// 从Part的content-disposition中提取上传文件的文件名
			String fileName = RequestUtil.getFileName(part);
			if (StringUtils.isNotEmpty(fileName)) {
				fileName = FileUtil.parseFileName(fileName);
				String savePath = SAVE_PATH + fileName;
				System.out.println("savePath:" + savePath);
				url = "/img/" + uploadType + "/" + fileName;
				// inputStream转成outputStream并存储
				try {
					is = part.getInputStream();
					fos = new FileOutputStream(savePath);
					OutputStream optS = (OutputStream) fos;
					int c;
					while ((c = is.read()) != -1) {
						optS.write(c);
					}
					optS.flush();
					tempImgService.add(uploadType, fileName);
					result = true;
				} catch (IOException e) {
					e.printStackTrace();
					message = ErrorTipsType.UPLOAD_ERROR.getDesc();
					errorCode = ErrorTipsType.UPLOAD_ERROR.getKey();
				}
			}
		}
		IOUtils.closeQuietly(is);
		IOUtils.closeQuietly(fos);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("url", url);
		map.put("result", result);
		map.put("message", message);
		map.put("errorCode", errorCode);
		// 显示上传的文件列表
		return map;
	}
	
	@LoginRequired
	@ResponseBody
	@RequestMapping(value = "/updateCoverImg.do", method = RequestMethod.POST)
	public Map<String, Object> updateCoverImg(HttpServletRequest request, HttpServletResponse response, HttpSession session, int vid, String url) {
		int userId = (Integer) session.getAttribute("userId");
		videoService.checkAuthor(userId, vid);
		Video video = videoService.get(vid);
		video.setImgUrl(url);
		boolean result = videoService.update(video);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		map.put("message", result ? "" : ErrorTipsType.UPLOAD_ERROR.getDesc());
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/export.do", method = RequestMethod.GET)
	public Map<String, Object> export() {
		List<WPPosts> list = videoInfoDao.listWPPosts();
		int total = 0;
		int htmlCount = 0;
		for (WPPosts post : list) {
			if (post.getPostContent().contains("yinyuetai")) {
				continue;
			}
			total++;
			System.out.println("total:" + total + "  " + DateUtil.date2String(new Date()));
			List<String> labelList = videoInfoDao.listLabel(post.getId());
			Set<String> set = new HashSet<String>();
			String videoLabel = "";
			for (String label : labelList) {
				boolean result = set.add(label);
				if (result) {
					videoLabel += label + "//";
				}
			}
			VideoInfo obj = videoInfoDao.get(post.getId());
			if (obj != null) {
				continue;
			}
			VideoInfo info = new VideoInfo();
			info.setId(post.getId());
			info.setLabel(videoLabel);
			info.setPostDate(post.getPostDate());
			info.setPostName(post.getPostName());
			info.setPostTitle(post.getPostTitle());
			
			String youkuId = null;
			if (post.getPostContent().contains("iframe")) {
				youkuId = this.getYoukuIdByStr(post.getPostContent());
			} else {
				htmlCount++;
				try {
					youkuId = this.getYoukuIdByHtml(post.getPostName());
				} catch (Exception e) {
					System.out.println("抓取页面出错:" + post.getPostName());
					continue;
				}
			}
			System.out.println(youkuId);
			info.setYoukuId(youkuId);
			
			videoInfoDao.add(info);
			if (total % 100 == 0) {
//				System.out.println(Json.toJson(info));
//				System.out.println(Json.toJson(post));
				System.out.println("total:" + total + " htmlCount:" + htmlCount);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", list.size());
		return map;
	}
	
	public static String getYoukuIdByStr(String str) {
		Pattern pattern = Pattern.compile("(VideoIDS=[a-z0-9A-Z]+)");
		Matcher m = pattern.matcher(str);
		String youkuId = null;
		if (m.find()) {
			String[] temp = m.group().split("=");
			youkuId = temp[1];
		}
		return youkuId;
	}
	
	public static  String getYoukuIdByHtml(String postName) throws Exception {
		URL myurl = new URL("http://www.goodancer.com/" + postName);
		// 从URL对象中获取输入流
		InputStreamReader isr = new InputStreamReader(myurl.openStream());
		// 封装
		BufferedReader br = new BufferedReader(isr);
		// readLine获取文本
		String line = br.readLine();
		String html = null;
		while (line != null) {
			html += line + "\r\n";
			line = br.readLine();
		}
		// 关闭流
		br.close();
		String result = getYoukuIdByStr(html);
		if (StringUtils.isEmpty(result)) {
			result = getYoukuIdByHtmlObject(html);
		}
		return result;
	}
	
	public static String  getYoukuIdByHtmlObject(String html) {
		Pattern pattern = Pattern.compile("<iframe .+ src=\"http://player.youku.com/embed/[a-z0-9A-Z]+\" .+>");
		Matcher m = pattern.matcher(html);
		String youkuId = null;
		if (m.find()) {
			String iframe = m.group();
			pattern = Pattern.compile("src=\"http://player.youku.com/embed/[a-z0-9A-Z]+\"");
			m = pattern.matcher(iframe);
			if (m.find()) {
				String str = m.group().replaceAll("\"", "");
				youkuId = str.split("/")[4];
			}
		}
		return youkuId;
	}
	
	@RequestMapping(value = "/importVideo.do", method = RequestMethod.GET)
	public void importVideo() throws Exception {
		List<VideoInfo> list = videoInfoDao.listVideoInfo();
		for (VideoInfo videoInfo : list) {
			if (StringUtils.isEmpty(videoInfo.getYoukuId())) {
				continue;
			}
			try {
				Video video = new Video();
				video.setDel(false);
				video.setDescription("");
				video.setHomeType(HomeType.RECOMMEND.getKey());
				CaptureVideo captureVideo = CaptureVideoUtil.getYouKuCaptureVideoById(videoInfo.getYoukuId(), "");
				video.setImgUrl(captureVideo.getThumbnail());
				video.setSourceSiteType(SourceSiteType.YOUKU.getKey());
				if (StringUtils.isEmpty(video.getName())) {
					video.setName(captureVideo.getTitle());
				}
				video.setPlayUrl(captureVideo.getFlashUrl());
				video.setIndexNum(0);
				video.setLabel(videoInfo.getLabel().replaceAll("//", " "));
				video.setName(videoInfo.getPostTitle());
				video.setNickname("iverson");
				video.setPosttime(videoInfo.getPostDate());
				video.setSourceSiteType(SourceSiteType.YOUKU.getKey());
				video.setStatus(StatusType.NORMAL.getKey());
				video.setUrl("http://v.youku.com/v_show/id_" + videoInfo.getYoukuId() + ".html?from=y1.1-2.20001-0.1-1");
				video.setUserId(5);
				video.setVideoGradeType(VideoGradeType.ALL.getKey());
				VideoSourceType videoSourceType = videoInfo.getLabel().contains("国内") ? VideoSourceType.ORIGINAL : VideoSourceType.REPRINT;
				video.setVideoSourceType(videoSourceType.getKey());
				VideoType videoType = VideoType.ALL;
				if (videoInfo.getLabel().contains(VideoType.AD_VIDEO.getDesc())) {
					videoType = VideoType.AD_VIDEO;
				} else if (videoInfo.getLabel().toLowerCase().contains(VideoType.BATTLE.getDesc().toLowerCase())) {
					videoType = VideoType.BATTLE;
				} else if (videoInfo.getLabel().contains(VideoType.CHOREOGRAPHY.getDesc())) {
					videoType = VideoType.CHOREOGRAPHY;
				} else if (videoInfo.getLabel().contains(VideoType.INTERVIEW.getDesc())) {
					videoType = VideoType.INTERVIEW;
				} else if (videoInfo.getLabel().toLowerCase().contains(VideoType.SOLO.getDesc().toLowerCase())) {
					videoType = VideoType.SOLO;
				} else if (videoInfo.getLabel().contains(VideoType.TEACH.getDesc())) {
					videoType = VideoType.TEACH;
				} else if (videoInfo.getLabel().contains(VideoType.VIDEO.getDesc())) {
					videoType = VideoType.VIDEO;
				} 
				video.setVideoType(videoType.getKey());
				videoService.add(video);
			} catch (Exception e) {
				System.out.println("插入视频出错:" + Json.toJson(videoInfo));
				continue;
			}
			
			
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("编舞//Storm//Shine//Mannequin//国内作品//个人舞//".contains("国内"));
	}
}
