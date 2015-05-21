package com.excilys.aflak.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.aflak.inter.IDAOCompany;
import com.excilys.aflak.model.Company;
import com.excilys.aflak.model.QCompany;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.hibernate.HibernateDeleteClause;
import com.mysema.query.jpa.hibernate.HibernateQuery;

@Repository
public class CompanyDAO implements IDAOCompany {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Company> list() {
		QCompany qCompany = QCompany.company;
		JPQLQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		return query.from(qCompany).list(qCompany);
	}

	@Override
	public Company find(Long id) {
		QCompany qCompany = QCompany.company;
		JPQLQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		return query.from(qCompany).where(qCompany.id.eq(id))
				.uniqueResult(qCompany);
	}

	@Override
	public void delete(Long id) {
		QCompany qCompany = QCompany.company;
		new HibernateDeleteClause(sessionFactory.getCurrentSession(), qCompany)
				.where(qCompany.id.eq(id)).execute();

	}

}
