package com.service.impl;

import java.util.Collection;

import com.bean.dao.DiscussDB;
import com.bean.dao.impl.DiscussDBImpl;
import com.bean.entity.Discuss;
import com.service.DiscussService;

public class DiscussServiceImpl implements DiscussService {

	
	DiscussDB discussdb = new DiscussDBImpl();
	@Override
	public void postDiscuss(Discuss discuss, int no) {
		// TODO Auto-generated method stub
        discussdb.postDiscuss(discuss, no);
	}

	@Override
	public void doDelete(Discuss discuss) {
		// TODO Auto-generated method stub
       discussdb.doDelete(discuss);
	}

	@Override
	public Discuss showDital(int no) {
		// TODO Auto-generated method stub
		Discuss discuss = discussdb.showDital(no);
		return discuss;
	}

	@Override
	public Collection<Discuss> pageSelect(int curPage, int no) {
		// TODO Auto-generated method stub
		Collection<Discuss> discuss = discussdb.pageSelect(curPage, no);
		return discuss;
	}

}
