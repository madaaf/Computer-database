package com.excilys.aflak.dao.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.aflak.dao.inter.IDAOCompany;
import com.excilys.aflak.dao.mapper.MapperCompany;
import com.excilys.aflak.model.Company;

@Repository
public class CompanyDAO implements IDAOCompany {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static Logger logger = LoggerFactory.getLogger(CompanyDAO.class);

	@Override
	public List<Company> list() {
		return this.jdbcTemplate.query("select * from company",
				new MapperCompany());
	}

	@Override
	public Company find(Long id) {
		return this.jdbcTemplate.queryForObject(
				"SELECT * from company WHERE id = ?", new Object[] { id },
				new MapperCompany());
	}

	@Override
	public boolean delete(Long id) {
		int rowDeleted = this.jdbcTemplate.update(
				"DELETE FROM company WHERE id = ?", id);
		return (rowDeleted == 1);
	}

}
