package com.team.springsns.dao;

import com.team.springsns.model.MemberInfo;

public interface MemberDaoInterface {

	public MemberInfo getMemberInfo (String userId);
	public int insertMemberInfo(MemberInfo memberInfo);
}
