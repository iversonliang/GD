package com.GD.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GD.dao.InviteCodeDao;
import com.GD.model.InviteCode;
import com.GD.service.InviteCodeService;
import com.GD.util.CodeUtil;
import com.GD.util.Constants;

@Service
public class InviteCodeServiceImpl implements InviteCodeService {
	
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

}
