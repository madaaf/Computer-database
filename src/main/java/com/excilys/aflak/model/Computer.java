package com.excilys.aflak.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "computer")
public class Computer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private LocalDateTime introduced;
	private LocalDateTime discontinued;
	@OneToOne
	private Company company;

	public Computer() {

	}

	private Computer(long id, String name, LocalDateTime introduced,
			LocalDateTime discontinued, Company ccompagny) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = ccompagny;
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

	public LocalDateTime getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDateTime introduced) {
		this.introduced = introduced;
	}

	public LocalDateTime getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDateTime discontinued) {
		this.discontinued = discontinued;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (id != other.id)
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {

		return new StringBuilder(" ").append(id).append("\t|\t").append(name)
				.append("\t|\t ").append(introduced).append("\t|\t")
				.append(discontinued).append("\t|\t").append(company)
				.toString();
	}

	public static class ComputerBuilder {
		private Long id = -1L;
		private String name;
		private LocalDateTime introduced;
		private LocalDateTime discontinued;
		private Company company;

		private ComputerBuilder() {
		}

		public static ComputerBuilder createDefaultComputer() {
			return new ComputerBuilder();
		}

		public ComputerBuilder withId(final Long id) {
			this.id = id;
			return this;
		}

		public ComputerBuilder withName(final String name) {
			this.name = name;
			return this;
		}

		public ComputerBuilder withIntroduced(final LocalDateTime introduced) {
			this.introduced = introduced;
			return this;
		}

		public ComputerBuilder withDiscontinued(final LocalDateTime discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		public ComputerBuilder withCompany(final Company company) {
			this.company = company;
			return this;
		}

		public Computer build() {
			if (name == null) {
				throw new IllegalArgumentException(
						"You can't create a computer without a name ");
			}
			return new Computer(id, name, introduced, discontinued, company);
		}
	}
}
