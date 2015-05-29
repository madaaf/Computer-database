package com.excilys.aflak.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Page {

	private int pageSize = 10;
	private int pageNumber = 1;

	private String filter = "";
	private String sort = "ASC";
	private String field = "computer.name";
	// nbr of pages
	private int totalPage;
	// nbr of computers
	private int totalComputer;

	private List<ComputerDTO> listComputers;
	private static final List<Integer> limits = new ArrayList<Integer>(
			Arrays.asList(10, 50, 100));

	public Page() {
		System.err.println(toString());
	}

	public Page(int pageSize, int pageNumber, String filter, String sort,
			String field, int totalPage, int totalComputer,
			List<ComputerDTO> listComputers) {
		super();
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
		this.filter = filter;
		this.sort = sort;
		this.field = field;
		this.totalPage = totalPage;
		this.totalComputer = totalComputer;
		this.listComputers = listComputers;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (limits.contains(pageSize)) {
			this.pageSize = pageSize;
		}
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		if (sort.equals("ASC")) {
			sort = "DESC";
		} else if (sort.equals("DESC")) {
			sort = "ASC";
		} else {
			sort = "ASC";
		}
		this.sort = sort;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public List<ComputerDTO> getListComputers() {
		return listComputers;
	}

	public void setListComputers(List<ComputerDTO> listComputers) {
		this.listComputers = listComputers;
	}

	public int getTotalPage() {
		float nbrOfPagesF = totalComputer / (float) pageSize;
		this.totalPage = (int) Math.ceil(nbrOfPagesF);
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalComputer() {
		return totalComputer;
	}

	public void setTotalComputer(int totalComputer) {
		this.totalComputer = totalComputer;
	}

	@Override
	public String toString() {
		return "Page [pageSize=" + pageSize + ", pageNumber=" + pageNumber
				+ ", filter=" + filter + ", sort=" + sort + ", field=" + field
				+ ", listComputers=" + listComputers + ", totalPage="
				+ totalPage + ", totalComputer=" + totalComputer + "]";
	}

}
