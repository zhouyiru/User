package com.service;

import java.util.Collection;

import com.bean.entity.Discuss;

public interface DiscussService {

	public void postDiscuss(Discuss discuss,int no);
	public void doDelete(Discuss discuss);
	public Discuss showDital(int no);
	public Collection<Discuss> pageSelect(int curPage,int no);
}
