package com.bruce.designer.data;

import java.util.List;

public class PagingData<T> {

	/*当前页*/
	private int currentPage;
	/*每页条数*/
	private int pageSize;
	/*总页数*/
	private int totalPage;
	/*总条数*/
	private int totalCount;
	/*分页起始条*/
	private int offset;
	
	private List<T> pageData;
	
	
//	public PagingData(int currentPage, int pageSize){
//		this.currentPage = currentPage <= 0 ? 1 : currentPage;
//		this.pageSize = pageSize;
//	}
	
	public PagingData(List<T> pageData, int totalCount, int currentPage, int pageSize) {
		this.pageData = pageData;
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getOffset() {
		offset = (currentPage-1) * pageSize;
		return offset;
	}

	public int getTotalPage() {
		totalPage = (int) Math.ceil(totalCount/Double.parseDouble(String.valueOf(pageSize)));
		return totalPage;
	}
	
	public List<T> getPageData() {
		return pageData;
	}

	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}
	
}
