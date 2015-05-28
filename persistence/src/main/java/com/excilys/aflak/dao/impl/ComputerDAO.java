package com.excilys.aflak.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.aflak.dao.IComputerDAO;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.model.QCompany;
import com.excilys.aflak.model.QComputer;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.hibernate.HibernateDeleteClause;
import com.mysema.query.jpa.hibernate.HibernateQuery;
import com.mysema.query.types.OrderSpecifier;

@Repository
public class ComputerDAO implements IComputerDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Computer find(Long id) {
		QComputer qComputer = QComputer.computer;
		JPQLQuery query = new HibernateQuery(sessionFactory.getCurrentSession());

		Computer com = query.from(qComputer).where(qComputer.id.eq(id))
				.uniqueResult(qComputer);
		return com;
	}

	@Override
	public void create(Computer computer) {
		sessionFactory.getCurrentSession().save(computer);
	}

	@Override
	public void delete(Long id) {
		QComputer qComputer = QComputer.computer;
		new HibernateDeleteClause(sessionFactory.getCurrentSession(), qComputer)
				.where(qComputer.id.eq(id)).execute();
	}

	@Override
	public void update(Computer computer) {
		sessionFactory.getCurrentSession().update(computer);
	}

	@Override
	public int count() {
		QComputer qComputer = QComputer.computer;
		JPQLQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		long x = query.from(qComputer).count();
		return (int) x;

	}

	@Override
	public int count(String filtre) {
		QComputer qComputer = QComputer.computer;
		QCompany qCompany = QCompany.company;
		filtre = "%" + filtre + "%";
		JPQLQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		long x = query
				.from(qComputer)
				.leftJoin(qComputer.company, qCompany)
				.where(qComputer.name.like(filtre).or(
						qCompany.name.like(filtre))).count();
		return (int) x;
	}

	@Override
	public List<Computer> list() {
		QComputer qComputer = QComputer.computer;
		QCompany qCompany = QCompany.company;
		JPQLQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		return query.from(qComputer).leftJoin(qComputer.company, qCompany)
				.list(qComputer);

	}

	@Override
	public List<Computer> list(int debut, int nbr) {
		QComputer qComputer = QComputer.computer;
		QCompany qCompany = QCompany.company;
		JPQLQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		return query.from(qComputer).leftJoin(qComputer.company, qCompany)
				.limit(nbr).offset(debut).list(qComputer);
	}

	/*
	 * @Override public List<Computer> list(PageRequest pageRequest) { String
	 * filter = "%" + pageRequest.filter + "%";
	 * 
	 * QComputer qComputer = QComputer.computer; QCompany qCompany =
	 * QCompany.company; JPQLQuery query = new
	 * HibernateQuery(sessionFactory.getCurrentSession());
	 * 
	 * OrderSpecifier<?> order; switch (pageRequest.field) { case
	 * "computer.name": order = Sort.ASC.equals(pageRequest.sort) ?
	 * qComputer.name.asc() : qComputer.name.desc(); break; case
	 * "computer.introduced": order = Sort.ASC.equals(pageRequest.sort) ?
	 * qComputer.introduced .asc() : qComputer.introduced.desc(); break; case
	 * "computer.discontinued": order = Sort.ASC.equals(pageRequest.sort) ?
	 * qComputer.discontinued .asc() : qComputer.discontinued.desc(); break;
	 * case "computer.company": order = Sort.ASC.equals(pageRequest.sort) ?
	 * qComputer.company.name .asc() : qComputer.company.name.desc(); break;
	 * default: order = qComputer.id.asc(); }
	 * 
	 * return query .from(qComputer) .orderBy(order)
	 * .leftJoin(qComputer.company, qCompany)
	 * .where(qComputer.name.like(filter).or( qCompany.name.like(filter)))
	 * .offset(pageRequest.pageSize * (pageRequest.pageNumber - 1))
	 * .limit(pageRequest.pageSize).list(qComputer);
	 * 
	 * }
	 */

	@Override
	public List<Computer> list(String filtre, String column, String way,
			int debut, int limit) {
		filtre = "%" + filtre + "%";
		QComputer qComputer = QComputer.computer;
		QCompany qCompany = QCompany.company;
		JPQLQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		OrderSpecifier<?> order;
		switch (column) {
		case "computer.name":
			order = way == ("ASC") ? qComputer.name.asc() : qComputer.name
					.desc();
			break;
		case "computer.introduced":
			order = way == ("ASC") ? qComputer.introduced.asc()
					: qComputer.introduced.desc();
			break;
		case "computer.discontinued":
			order = way == ("ASC") ? qComputer.discontinued.asc()
					: qComputer.discontinued.desc();
			break;
		case "computer.company":
			order = way == ("ASC") ? qComputer.company.name.asc()
					: qComputer.company.name.desc();
			break;
		default:
			order = qComputer.name.asc();
		}
		return query
				.from(qComputer)
				.orderBy(order)
				.leftJoin(qComputer.company, qCompany)
				.where(qComputer.name.like(filtre).or(
						qCompany.name.like(filtre))).limit(2).offset(debut)
				.limit(limit).offset(debut).list(qComputer);
	}

	@Override
	public void deleteComputerFromCompany(Long companyId) {
		QComputer qComputer = QComputer.computer;
		new HibernateDeleteClause(sessionFactory.getCurrentSession(), qComputer)
				.where(qComputer.company.id.eq(companyId)).execute();

	}

}
