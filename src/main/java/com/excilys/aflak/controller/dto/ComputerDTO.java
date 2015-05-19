package com.excilys.aflak.controller.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.excilys.aflak.validator.Date;

public class ComputerDTO {
	@NotNull
	private long id;
	@Length(max = 200)
	private String name;
	@Length(max = 10)
	@Date
	private String introduced;
	@Length(max = 10)
	@Date
	private String discontinued;
	private long companyId;
	@Length(max = 200)
	private String companyName;

	public ComputerDTO() {
		System.err.println("dans mon computerDTO");
	}

	public ComputerDTO(long id, String name, String introduced,
			String discontinued, long companyId, String companyName) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
		this.companyName = companyName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "ComputerBuilderDTO [id=" + id + ", name=" + name
				+ ", introduced=" + introduced + ", discontinued="
				+ discontinued + ", companyId=" + companyId + ", companyName="
				+ companyName + "]";
	}

	public static class ComputerBuilderDTO {
		private long id;
		private String name;
		private String introduced;
		private String discontinued;
		private long companyId;
		private String companyName;

		private ComputerBuilderDTO() {
		}

		public static ComputerBuilderDTO createDefaultComputer() {
			return new ComputerBuilderDTO();
		}

		public ComputerBuilderDTO withId(final long id) {
			this.id = id;
			return this;
		}

		public ComputerBuilderDTO withName(final String name) {
			this.name = name;
			return this;
		}

		public ComputerBuilderDTO withIntroduced(final String introduced) {
			this.introduced = introduced;
			return this;
		}

		public ComputerBuilderDTO withDiscontinued(final String discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		public ComputerBuilderDTO withCompanyId(final long companyId) {
			this.companyId = companyId;
			return this;
		}

		public ComputerBuilderDTO withCompanyName(final String companyName) {
			this.companyName = companyName;
			return this;
		}

		public ComputerDTO build() {
			if (name == null) {
				throw new IllegalArgumentException(
						"You can't create a computer without a name ");
			}
			return new ComputerDTO(id, name, introduced, discontinued,
					companyId, companyName);
		}
	}
}