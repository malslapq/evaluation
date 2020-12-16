package com.my.DTO;
//페이징처리와 검색처리
public class PageDTO {
	private int curPage = 1; //현재페이지
	private int perPage = 5; //한페이지당 게시물수
	private int perBlock = 5; //한화면의 페이지수
	private int totPage ,startNo, endNo, startPage, endPage, searchcurPage; 
	private String key, search, searchtype;
	
	public void pageset(int totcontent) {
		this.totPage = totcontent/this.getPerPage();
		if (totcontent%this.getPerPage() != 0) this.totPage = (this.totPage+1);
		this.startNo = ((this.curPage-1)*this.perPage)+1;
		this.endNo = (this.startNo+this.perPage-1);
		this.startPage = this.curPage-(this.curPage-1)%this.perBlock;
		this.endPage = this.startPage+this.perPage-1;
		if(this.endPage > this.totPage) this.endPage = this.totPage;
		this.searchcurPage = 0;
	}
	
	public PageDTO() {
		super();
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPerPage() {
		return perPage;
	}
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	public int getPerBlock() {
		return perBlock;
	}
	public void setPerBlock(int perBlock) {
		this.perBlock = perBlock;
	}
	public int getTotPage() {
		return totPage;
	}
	public void setTotPage(int totPage) {
		this.totPage = totPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getStartNo() {
		return startNo;
	}
	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}
	public int getEndNo() {
		return endNo;
	}
	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	@Override
	public String toString() {
		return "PageDTO [curPage=" + curPage + ", perPage=" + perPage + ", perBlock=" + perBlock + ", totPage="
				+ totPage + ", startPage=" + startPage + ", endPage=" + endPage + ", startNo=" + startNo + ", endNo="
				+ endNo + ", key=" + key + ", search=" + search + ", searchtype=" + searchtype+"]";
	}

	public String getSearchtype() {
		return searchtype;
	}

	public void setSearchtype(String searchtype) {
		this.searchtype = searchtype;
	}

	public int getSearchcurPage() {
		return searchcurPage;
	}

	public void setSearchcurPage(int searchcurPage) {
		this.searchcurPage = searchcurPage;
	}
	
	
}

