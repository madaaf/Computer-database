package com.excilys.aflak.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.aflak.model.QUser;
import com.excilys.aflak.model.User;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.hibernate.HibernateQuery;

@Repository
public class UserDAO {

	@Autowired
	SessionFactory sessionFactory;

	public User findByUserName(String username) {

		QUser qUser = QUser.user;
		JPQLQuery query = new HibernateQuery(sessionFactory.getCurrentSession());

		User user = query.from(qUser).where(qUser.username.eq(username))
				.uniqueResult(qUser);
		return user;

	}
}
