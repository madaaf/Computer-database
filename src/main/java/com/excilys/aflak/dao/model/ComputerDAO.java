package com.excilys.aflak.dao.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.excilys.aflak.dao.inter.IDAOComputer;
import com.excilys.aflak.dao.mapper.MapperComputer;
import com.excilys.aflak.dao.utils.TimeConvertorDAO;
import com.excilys.aflak.model.Computer;

@Repository
public class ComputerDAO implements IDAOComputer {

	// INSTANCE;
	private static Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Long create(Computer computer) {
		String sql = "INSERT INTO computer (name,introduced,discontinued,company_id) VALUES(?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		this.jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, computer.getName());
				ps.setTimestamp(2, TimeConvertorDAO
						.convertLocalDateTimeToTimestamp(computer
								.getIntroduced()));
				ps.setTimestamp(3, TimeConvertorDAO
						.convertLocalDateTimeToTimestamp(computer
								.getDiscontinued()));
				if (computer.getCompany() == null) {
					ps.setNull(4, Types.BIGINT);
				} else {
					ps.setLong(4, (computer.getCompany().getId()));
				}
				return ps;
			}
		}, keyHolder);
		System.err.print(keyHolder.getKey());
		return (Long) keyHolder.getKey();
	}

	@Override
	public boolean delete(Long id) {
		int row = this.jdbcTemplate.update("DELETE FROM computer WHERE id = ?",
				Long.valueOf(id));
		return (row == 1);
	}

	@Override
	public boolean update(Computer computer) {
		Long idCompany = null;
		Long idComputer = null;

		if (computer.getCompany() != null) {
			idCompany = computer.getCompany().getId();
		}
		if (computer.getId() > 0) {
			idComputer = computer.getId();
		}

		int row = this.jdbcTemplate
				.update("UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?",
						computer.getName(), TimeConvertorDAO
								.convertLocalDateTimeToTimestamp(computer
										.getIntroduced()), TimeConvertorDAO
								.convertLocalDateTimeToTimestamp(computer
										.getDiscontinued()), idCompany,
						idComputer);
		System.err.println(row);
		return (row == 1);
	}

	@Override
	public Computer find(Long id) {
		try {
			return this.jdbcTemplate
					.queryForObject(
							"select computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name AS 'company_name' from computer left join company on  computer.company_id = company.id WHERE computer.id = ?",
							new Object[] { id }, new MapperComputer());
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public List<Computer> list() {
		return this.jdbcTemplate
				.query("select computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name AS 'company_name' from computer left join company on  computer.company_id = company.id",
						new MapperComputer());

	}

	@Override
	public List<Computer> getSomeComputers(int debut, int nbr) {
		String sql = "select computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name AS 'company_name' from computer left join company on  computer.company_id = company.id LIMIT "
				+ nbr + " OFFSET " + debut;
		return this.jdbcTemplate.query(sql, new MapperComputer());

	}

	@Override
	public int getSizeTabComputers() {
		return this.jdbcTemplate.queryForObject(
				"select COUNT(*) from computer", Integer.class);

	}

	@Override
	public List<Computer> getSomeFilteredComputer(String filtre, String colomn,
			String way, int debut, int limit) {
		filtre = "%" + filtre + "%";
		String sql = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name AS 'company_name' from computer left join company on computer.company_id = company.id WHERE computer.name like ? or company.name like ? ORDER BY "
				+ colomn + " " + way + " LIMIT ? OFFSET ?";

		return this.jdbcTemplate.query(sql, new Object[] { filtre, filtre,
				limit, debut }, new MapperComputer());

	}

	@Override
	public int getSizeFilteredComputer(String filtre) {
		String ok = "SELECT  COUNT(*) from computer left join company on computer.company_id = company.id WHERE computer.name like ? or company.name like ? ";
		filtre = "%" + filtre + "%";
		return this.jdbcTemplate.queryForObject(ok, new Object[] { filtre,
				filtre }, Integer.class);

	}

	@Override
	public void deleteComputerFromCompany(Long companyId) {
		this.jdbcTemplate.update("DELETE from computer WHERE company_id = ?",
				new Object[] { companyId });

	}
}
