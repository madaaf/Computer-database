package com.excilys.aflak.dto;

import com.excilys.aflak.dao.ICrudDAO;
import com.excilys.aflak.dao.ICrudDAO.Sort;

public class PageRequest {
	// attribute in public because immutable class
	public final String filter;
	public final String field;
	public final ICrudDAO.Sort sort;
	public final int pageSize;
	public final int pageNumber;

	public PageRequest(String filter, String field, Sort sort, int pageSize,
			int pageNumber) {
		super();
		this.filter = filter;
		this.field = field;
		this.sort = sort;
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
	}

	public static class Builder {

		private String filter;
		private String field;
		private ICrudDAO.Sort sort;
		private int pageSize;
		private int pageNumber;

		private Builder() {
		}

		public Builder filter(String filter) {
			this.filter = filter;
			return this;
		}

		public Builder pageSize(int pageSize) {
			this.pageSize = pageSize;
			return this;
		}

		public Builder pageNumber(int pageNumber) {
			this.pageNumber = pageNumber;
			return this;
		}

		public Builder sort(ICrudDAO.Sort sort) {
			this.sort = sort;
			return this;
		}

		public Builder field(String field) {
			this.field = field;
			return this;
		}

		public PageRequest build() {
			return new PageRequest(filter, field, sort, pageSize, pageNumber);
		}

	}

}
