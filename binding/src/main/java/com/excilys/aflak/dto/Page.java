package com.excilys.aflak.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.excilys.aflak.mapper.ComputerMapper;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.service.ComputerService;
import com.excilys.aflak.validator.Date.Pattern;

public class Page {

	private int limit = 10;
	private int start = 0;
	private int end = start + 5;
	private int pageNum = 0;

	private String search = "";
	private String way = "ASC";
	private String colomn = "computer.id";

	private List<ComputerDTO> listComputers = new ArrayList<ComputerDTO>();
	private static final List<String> limits = new ArrayList<String>(
			Arrays.asList("10", "50", "100"));

	private int nbrOfPages;
	private int nbrComputers;
	private String deletedSuccess = null;
	private String invalidArgument = null;

	public Page() {
		System.err.println(toString());
	}

	public void initialize() {
		limit = 10;
		start = 0;
		end = start + 5;
		pageNum = 0;
		search = "";
		way = "ASC";
		colomn = "computer.id";
	}

	public Page(int pageNum, int limit, int start, int end, String search,
			String way, String colomn, List<ComputerDTO> listComputers,
			int nbrOfPages, int nbrComputers) {
		super();
		this.pageNum = pageNum;
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

	public String getInvalidArgument() {
		return invalidArgument;
	}

	public void setInvalidArgument(String invalidArgument) {
		this.invalidArgument = invalidArgument;
	}

	public String getDeletedSuccess() {
		return deletedSuccess;
	}

	public void setDeletedSuccess(String deletedSuccess) {
		this.deletedSuccess = deletedSuccess;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
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

	public List<ComputerDTO> getListComputers() {
		return listComputers;
	}

	public void setListComputers(List<ComputerDTO> listComputers) {
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

	public void populate(ComputerService serviceComputer, Pattern pattern) {
		setNbrComputers(serviceComputer.count(getSearch()));
		if (limits.contains(getLimit())) {
			setLimit(getLimit());
		}

		float nbrOfPagesF = (float) getNbrComputers() / (float) getLimit();
		setNbrOfPages((int) Math.ceil(nbrOfPagesF));

		if (getPageNum() < getNbrOfPages()) {
			setStart(getPageNum());
			setEnd(getStart() + 5);
		}

		if (getWay().equals("ASC")) {
			setWay("DESC");
		} else {
			setWay("ASC");
		}

		if (getDeletedSuccess() != null) {
			setDeletedSuccess("deletedSuccess");
		}

		if (getInvalidArgument() != null) {
			setInvalidArgument("setInvalidArgument");
		}

		ComputerMapper mapper = new ComputerMapper(pattern);
		List<Computer> listComputers = null;
		// remplir la liste de computer en fonction de la recherche
		if (getLimit() * getStart() < getNbrComputers()) {
			listComputers = serviceComputer.list(getSearch(), getColomn(),
					getWay(), getLimit() * getStart(), getLimit());
		} else {
			listComputers = serviceComputer.list(getSearch(), getColomn(),
					getWay(), 0, getLimit());

		}

		List<ComputerDTO> listComputersDTO = mapper.toListDto(listComputers);
		setListComputers(listComputersDTO);

	}

	@Override
	public String toString() {
		return "Page [limit=" + limit + ", start=" + start + ", end=" + end
				+ ", pageNum=" + pageNum + ", search=" + search + ", way="
				+ way + ", colomn=" + colomn + ", listComputers="
				+ listComputers + ", nbrOfPages=" + nbrOfPages
				+ ", nbrComputers=" + nbrComputers + "]";
	}

}