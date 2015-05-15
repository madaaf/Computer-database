package com.excilys.aflak.model;

import javax.persistence.Entity;

@Entity
public class Company {

	private Long id;
	private String name;

	private Company(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return new StringBuilder("Id : ").append(id).append("\t|\t : ")
				.append(name).toString();

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public static class CompanyBuilder {
		private Long id = null;
		private String name = null;

		private CompanyBuilder() {
		}

		public static CompanyBuilder crateDefaultCompany() {
			return new CompanyBuilder();
		}

		public CompanyBuilder withId(final Long id) {
			this.id = id;
			return this;
		}

		public CompanyBuilder withName(final String name) {
			this.name = name;
			return this;
		}

		public Company build() {

			return new Company(id, name);

		}
	}
}
