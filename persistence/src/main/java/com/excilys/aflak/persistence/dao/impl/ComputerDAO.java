package com.excilys.aflak.persistence.dao.impl;

import com.excilys.aflak.persistence.dao.IComputerDAO;
import com.excilys.aflak.model.Computer;
import com.excilys.aflak.model.QCompany;
import com.excilys.aflak.model.QComputer;
import com.excilys.aflak.persistence.dto.PageRequest;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.hibernate.HibernateDeleteClause;
import com.mysema.query.jpa.hibernate.HibernateQuery;
import com.mysema.query.types.OrderSpecifier;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComputerDAO implements IComputerDAO {

  @Autowired
  SessionFactory sessionFactory;

  @Override
  public Computer find(Long id) {
    QComputer qComputer = QComputer.computer;

    JPQLQuery query = new HibernateQuery(sessionFactory.getCurrentSession());

    Computer c = query.from(qComputer)
        .where(qComputer.id.eq(id))
        .uniqueResult(qComputer);
    return c;
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
  public int count(String filter) {
    QComputer qComputer = QComputer.computer;
    QCompany qCompany = QCompany.company;
    filter = "%" + filter + "%";

    JPQLQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
    long x = query
        .from(qComputer)
        .leftJoin(qComputer.company, qCompany)
        .where(qComputer.name.like(filter)
        .or(qCompany.name.like(filter)))
        .count();
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
  public List<Computer> list(int offset, int limit) {
    QComputer qComputer = QComputer.computer;
    QCompany qCompany = QCompany.company;
    JPQLQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
    return query.from(qComputer).leftJoin(qComputer.company, qCompany)
        .limit(limit).offset(offset).list(qComputer);
  }

  @Override
  public List<Computer> list(PageRequest request) {

    String filter = "%" + request.filter + "%";

    QComputer qComputer = QComputer.computer;
    QCompany qCompany = QCompany.company;
    JPQLQuery query = new HibernateQuery(sessionFactory.getCurrentSession());

    OrderSpecifier<?> order;
    switch (request.field) {
      case "computer.name":
        order = Sort.ASC.equals(request.sort) ? qComputer.name.asc() : qComputer.name.desc();
        break;
      case "computer.introduced":
        order = Sort.ASC.equals(request.sort) ? qComputer.introduced.asc() : qComputer.introduced.desc();
        break;
      case "computer.discontinued":
        order = Sort.ASC.equals(request.sort) ? qComputer.discontinued.asc() : qComputer.discontinued.desc();
        break;
      case "computer.company":
        order = Sort.ASC.equals(request.sort) ? qComputer.company.name.asc() : qComputer.company.name.desc();
        break;
      default:
        order = qComputer.name.asc();
    }

    List<Computer> c = query
        .from(qComputer)
        .orderBy(order)
        .leftJoin(qComputer.company, qCompany)
        .where(qComputer.name.like(filter).or(qCompany.name.like(filter)))
        .offset(request.pageSize * (request.pageNumber - 1))
        .limit(request.pageSize)
        .list(qComputer);
      return c;

  }

  @Override
  public void deleteComputerFromCompany(Long companyId) {
    QComputer qComputer = QComputer.computer;
    new HibernateDeleteClause(sessionFactory.getCurrentSession(), qComputer)
        .where(qComputer.company.id.eq(companyId)).execute();

  }

}
