package com.excilys.aflak.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.aflak.model.Company;

public class CompanyDAO extends DAO<Company> {

	public CompanyDAO(Connection connect) {
		super(connect);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Company obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Company obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Company find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Company> list() {
		List<Company> listCompany = new ArrayList<Company>();
		Company company = new Company();
		try {

			Statement state = SdzConnection.getInstance().createStatement();
			ResultSet result = state.executeQuery("select * from company");
			ResultSetMetaData resultMeta = result.getMetaData();

			while (result.next()) {
				company = new Company(result.getInt("id"),
						result.getString("name"));
				listCompany.add(company);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return listCompany;
	}
}
