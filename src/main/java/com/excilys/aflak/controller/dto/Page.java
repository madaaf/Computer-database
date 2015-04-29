package com.excilys.aflak.controller.dto;

public class Page {

	private int limit = 10;
	private int start = 0;
	private int end = start + 5;

	private String search = "";
	private String way = "ASC";
	private String colomn = null;

	private int listComputers;
	private int nbrOfPages;
	private int nbrComputers;

	public Page(int limit, int start, int end, String search, String way,
			String colomn, int listComputers, int nbrOfPages, int nbrComputers) {
		super();
		this.limit = limit;
		this.start = start;
		this.end = end;
		this.search = search;
		this.way = way;
		this.colomn = colomn;
		this.listComputers = listComputers;
		this.nbrOfPages = nbrOfPages;
		this.nbrComputers = nbrComputers;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public String getColomn() {
		return colomn;
	}

	public void setColomn(String colomn) {
		this.colomn = colomn;
	}

	public int getListComputers() {
		return listComputers;
	}

	public void setListComputers(int listComputers) {
		this.listComputers = listComputers;
	}

	public int getNbrOfPages() {
		return nbrOfPages;
	}

	public void setNbrOfPages(int nbrOfPages) {
		this.nbrOfPages = nbrOfPages;
	}

	public int getNbrComputers() {
		return nbrComputers;
	}

	public void setNbrComputers(int nbrComputers) {
		this.nbrComputers = nbrComputers;
	}

	public static class PageBuilder {

		private int limit = 10;
		private int start = 0;
		private int end = start + 5;

		private String search = "";
		private String way = "ASC";
		private String colomn = null;

		private int listComputers;
		private int nbrOfPages;
		private int nbrComputers;

		private PageBuilder() {
		}

		public static PageBuilder getDefaultPage() {
			return new PageBuilder();
		}

		public PageBuilder withLimit(final int limit) {
			this.limit = limit;
			return this;
		}

		public PageBuilder withStart(final int start) {
			this.start = start;
			return this;
		}

		public PageBuilder withEnd(final int end) {
			this.end = end;
			return this;
		}

		public PageBuilder withSearch(final String search) {
			this.search = search;
			return this;
		}

		public PageBuilder withWay(final String way) {
			this.way = way;
			return this;
		}

		public PageBuilder withColomn(final String colomn) {
			this.colomn = colomn;
			return this;
		}

		public PageBuilder withListComputers(final int listComputers) {
			this.listComputers = listComputers;
			return this;
		}

		public PageBuilder withNbrOfPages(final int nbrOfPages) {
			this.nbrOfPages = nbrOfPages;
			return this;
		}

		public PageBuilder withNbrComputers(final int nbrComputers) {
			this.nbrComputers = nbrComputers;
			return this;
		}

		public Page build() {
			return new Page(limit, start, end, search, way, colomn,
					listComputers, nbrOfPages, nbrComputers);
		}

	}

	public static void main(String[] args) {
		Page page = PageBuilder.getDefaultPage().build();
		page.setStart(100);
		System.out.println(page.getStart());

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
