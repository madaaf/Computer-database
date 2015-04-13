package com.excilys.aflak.dao;

import java.util.List;

import com.excilys.aflak.model.Computer;

public class Connect {
	public static void main(String[] args) {
		DAO<Computer> computerDao = new ComputerDAO(SdzConnection.getInstance());
		Computer c = computerDao.find(86);
		//List<Computer> lc = computerDao.list();
		Boolean ok = computerDao.delete(1);
		
		System.out.println("delete 2 " + ok);

	}

}
