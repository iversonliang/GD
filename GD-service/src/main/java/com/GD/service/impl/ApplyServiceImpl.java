package com.GD.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.GD.dao.ApplyDao;
import com.GD.email.MailInfo;
import com.GD.email.MailSender;
import com.GD.model.Apply;
import com.GD.model.Notice;
import com.GD.model.User;
import com.GD.service.ApplyService;
import com.GD.service.InviteCodeService;
import com.GD.service.NoticeService;
import com.GD.service.UserService;
import com.GD.util.Constants;
import com.GD.util.EmailUtil;

@Service
public class ApplyServiceImpl implements ApplyService {

	@Autowired
	private UserService userService;
	@Autowired
	private InviteCodeService inviteCodeService;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private ApplyDao applyDao;

	@Override
	public boolean add(Apply apply) {
		Apply check = applyDao.getByUserId(apply.getUserId());
		Assert.isNull(check, "用户已经提交申请[ " + apply.getUserId() + " ]");
		return applyDao.add(apply);
	}

	@Override
	public boolean pass(int applyId) {
		Apply apply = applyDao.get(applyId);
		Assert.notNull(apply, "没有对应的申请ID[ " + applyId + " ]");
		String inviteCode = inviteCodeService.random();
		inviteCodeService.send(inviteCode);
		Notice notice = new Notice();
		notice.setContent("恭喜你获得激活码：" + inviteCode + "，<a href=\"/inviteCode/index.do\"/>立即激活</a>");
		notice.setPosttime(new Date());
		notice.setUserId(apply.getUserId());
		notice.setImgUrl(Constants.SYS_DEFAULT_IMG);
		boolean result = noticeService.add(notice);
		applyDao.pass(applyId, inviteCode);
		User user = userService.get(apply.getUserId());
		MailInfo mailInfo = EmailUtil.getInviteCodeMailInfo(user.getEmail(), inviteCode);

		try {
			MailSender.sendTextMail(mailInfo);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public boolean isActivate(int userId) {
		Apply apply = applyDao.getByUserId(userId);
		boolean result = false;
		if (apply != null && apply.isPass()) {
			result = true;
		}
		return result;
	}

	@Override
	public int count() {
		return applyDao.count();
	}

	@Override
	public List<Apply> list(int start, int size) {
		return applyDao.list(start, size);
	}

	@Override
	public boolean activate(int userId) {
		return applyDao.activate(userId);
	}

	@Override
	public Apply getByUser(int userId) {
		return applyDao.getByUserId(userId);
	}

}
