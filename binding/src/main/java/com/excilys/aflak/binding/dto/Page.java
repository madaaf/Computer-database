package com.excilys.aflak.binding.dto;

import com.excilys.aflak.mapper.ComputerMapper;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.persistence.dto.PageRequest;
import com.excilys.aflak.service.ComputerService;
import com.excilys.aflak.service.validator.ComputerValidator;
import com.excilys.aflak.validator.Date.Pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Page {

	private int pageSize = 10;
	private int pageNumber = 1;

	private String filter = "";
	private String sort = "ASC";
	private String field = "computer.name";

	private List<ComputerDTO> listComputers;

	private static final List<String> limits = new ArrayList<String>(
			Arrays.asList("10", "50", "100"));

	private int pageCount;
	private int totalCount;

	public Page() {
		System.err.println(toString());
	}

	public void initialize() {
		//FIXME Mada
		//limit = 10;
		//page = 0;
		//end = page + 5;
		//pageNum = 0;
		filter = "";
		sort = "ASC";
		field = "computer.id";
	}

	public Page(int pageNum, int limit, int page, int end, String search,
			String way, String colomn, List<ComputerDTO> listComputers,
			int nbrOfPages, int nbrComputers) {
		super();
		//FIXME
//		this.pageNum = pageNum;
//		this.limit = limit;
//		this.page = page;
//		this.end = end;
		this.filter = search;
		this.sort = way;
		this.field = colomn;
		this.listComputers = listComputers;
		this.pageCount = nbrOfPages;
		this.totalCount = nbrComputers;
	}

	//FIXME take this out to somewhere else.
	public void populate(ComputerService computerService, Pattern pattern) {
		totalCount = computerService.count(filter);
		//FIXME Mada
//		if (limits.contains(getLimit())) {
//			setLimit(getLimit());
//		}
//
//		float nbrOfPagesF = (float) getTotalCount() / (float) getLimit();
//		setPageCount((int) Math.ceil(nbrOfPagesF));
//
//		if (getPageNum() < getPageCount()) {
//			setPage(getPageNum());
//			setEnd(getPage() + 5);
//		}
//
//		if (getSort().equals("ASC")) {
//			setSort("DESC");
//		} else {
//			setSort("ASC");
//		}
//
//		if (getDeletedSuccess() != null) {
//			setDeletedSuccess("deletedSuccess");
//		}
//
//		if (getInvalidArgument() != null) {
//			setInvalidArgument("setInvalidArgument");
//		}

		List<Computer> computers = computerService.list(
				PageRequest.builder()
				.filter(ComputerValidator.validateFilter(filter))
				.field(ComputerValidator.validateField(field))
				.sort(ComputerValidator.validateSort(sort))
				.pageNumber(pageNumber)
				.pageSize(pageSize).build());

		listComputers = new ComputerMapper(pattern).toListDto(computers);

	}

	//FIXME add toString hashcode equals

}
