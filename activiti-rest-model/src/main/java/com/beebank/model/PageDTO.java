package com.beebank.model;

import java.util.ArrayList;
import java.util.List;

public class PageDTO <T> {
	
	private  int pageNo = 1 ;
    private  int pageSize = Integer.MAX_VALUE;
    private  long totalCount ;
    
    protected List<T> result = new ArrayList<T>();
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		if(pageNo==0){
			pageNo=pageNo+1;
		}
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public List<T> getResult() {
		return result;
	}
	public void setResult(List<T> result) {
		this.result = result;
	}
    
    
    
    
}
