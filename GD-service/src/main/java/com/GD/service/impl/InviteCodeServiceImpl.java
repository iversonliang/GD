package com.GD.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.GD.dao.InviteCodeDao;
import com.GD.model.Apply;
import com.GD.model.InviteCode;
import com.GD.service.ApplyService;
import com.GD.service.InviteCodeService;
import com.GD.type.InviteCodeStatusType;
import com.GD.util.CodeUtil;
import com.GD.util.Constants;

@Service
public class InviteCodeServiceImpl implements InviteCodeService {
	
	@Autowired
	private ApplyService applyService;
	@Autowired
	private InviteCodeDao inviteCodeDao;

	@Override
	public boolean create(int num) {
		for (int i=0;i<num;i++) {
			String code = CodeUtil.generateString(15);
			InviteCode inviteCode = new InviteCode();
			inviteCode.setInviteCodeId(code);
			inviteCode.setPosttime(new Date());
			inviteCode.setUseTime(Constants.DEFAULT_DATE);
			inviteCode.setStatus(InviteCodeStatusType.NOT_SEND.getKey());
			inviteCodeDao.add(inviteCode);
		}
		return true;
	}

	@Override
	public int count() {
		return inviteCodeDao.count();
	}

	@Override
	public List<InviteCode> list(int start, int size) {
		return inviteCodeDao.list(start, size);
	}

	@Override
	public boolean use(int userId, String inviteCodeId) {
		return inviteCodeDao.use(userId, inviteCodeId);
	}

	@Override
	public boolean send(String inviteCodeId) {
		return inviteCodeDao.send(inviteCodeId);
	}

	@Override
	public String random() {
		InviteCode inviteCode = inviteCodeDao.random();
		Assert.notNull(inviteCode, "没有可以发放的邀请码");
		return inviteCode.getInviteCodeId();
	}

	@Override
	public boolean activateUser(int userId, String inviteCodeId) {
		InviteCode inviteCode = inviteCodeDao.get(inviteCodeId);
		Assert.notNull(inviteCode, "没有对应的邀请码记录");
		InviteCodeStatusType type = InviteCodeStatusType.toType(inviteCode.getStatus());
		Assert.isTrue(type != InviteCodeStatusType.USE, "改邀请码已经使用[ " + userId + " ][ " + inviteCodeId + " ]");
		inviteCodeDao.use(userId, inviteCodeId);
		Apply apply = applyService.getByUser(userId);
		if (apply == null) {
			apply = new Apply();
			apply.setUserId(userId);
			apply.setPass(true);
			apply.setPosttime(new Date());
			apply.setInviteCodeId(inviteCodeId);
			return applyService.add(apply);
		} else {
			return applyService.activate(userId);
		}
	}

}
