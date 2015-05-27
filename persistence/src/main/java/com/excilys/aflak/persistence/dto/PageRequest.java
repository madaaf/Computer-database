package com.excilys.aflak.persistence.dto;

import com.excilys.aflak.persistence.dao.ICrudDAO;

/**
 * Created by loic on 27/05/2015.
 */
public class PageRequest {
  public final String filter;
  public final String field;
  public final ICrudDAO.Sort sort;
  public final int pageSize;
  public final int pageNumber;


  public PageRequest(String filter, String field, ICrudDAO.Sort sort, int pageSize, int pageNumber) {
    this.filter = filter;
    this.field = field;
    this.sort = sort;
    this.pageSize = pageSize;
    this.pageNumber = pageNumber;
  }

  public static Builder builder() {
    return new Builder();
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
