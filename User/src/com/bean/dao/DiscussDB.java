package com.bean.dao;

import java.util.Collection;

import com.bean.entity.Discuss;

public interface DiscussDB {

	public void postDiscuss(Discuss discuss,int no);
	public void doDelete(Discuss discuss);
	public Discuss showDital(int no);
	public Collection<Discuss> pageSelect(int curPage,int no);
}
