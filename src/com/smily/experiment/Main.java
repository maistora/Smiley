package com.smily.experiment;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

import com.smily.experiment.db.entity.Company;
import com.smily.experiment.web.business.CompanyManager;
import com.smily.experiment.web.business.UserManager;
import com.smily.experiment.web.dto.UserDTO;

public class Main {

	public static void main(String[] args) {
		final Scanner in = new Scanner(System.in);
		
		final BigInteger compId = createCompanay(in);
		System.out.println("Created company ID: " + compId);
		
		final BigInteger userId = createUser(in, compId);
		System.out.println("Created user ID: " + userId);
		
		listCompanies();
		listUsers();
		
		findUserByLogin(in);
		findCompanyById(in);
	}

	private static BigInteger createCompanay(Scanner in) {
		System.out.println("======== Create company ========");
		
		System.out.print("Enter company name: ");
		final String companyName = in.nextLine();
		
		System.out.print("Enter company capital: ");
		final String capitalNonConverted = in.nextLine();
		final BigDecimal capital = new BigDecimal(capitalNonConverted);
		
		System.out.print("Enter company address: ");
		final String companyAddress = in.nextLine(); 
		
		final CompanyManager companyManager = new CompanyManager();
		final BigInteger compId = companyManager.createCompany(companyName, capital, companyAddress);
		
		return compId;
	}
	
	private static BigInteger createUser(Scanner in, BigInteger companyId) {
		System.out.println("======== Create user ========");
		
		System.out.print("Login: ");
		final String login = in.nextLine();
		
		System.out.print("Password: ");
		final String password = in.nextLine();
		
		System.out.print("Email: ");
		final String email = in.nextLine();

		final UserManager userManager = new UserManager();
		final BigInteger userId = userManager.createUser(login, password, email, companyId);		
		
		return userId;
	}
	
	private static void listCompanies() {
		System.out.println("======== List companies ========");
		
		final CompanyManager companyManager = new CompanyManager();
		companyManager.listCompanies();
	}
	
	private static void listUsers() {
		System.out.println("======== List users ========");
		
		final UserManager userManager = new UserManager();
		userManager.listUsers();
	}
	
	private static void findUserByLogin(final Scanner in) {
		System.out.println("======== Find user by login ========");
		System.out.print("Login: ");
		final String login = in.nextLine();
		
		final UserManager userManager = new UserManager();
		final UserDTO userDTO = userManager.findUserByLogin(login);
		
		System.out.println(userDTO);
	}

	private static void findCompanyById(final Scanner in) {
		System.out.println("======== Find company by ID ========");
		System.out.print("Company ID: ");
		final String compIdNonFormated = in.nextLine();
		final BigInteger companyId = new BigInteger(compIdNonFormated);
		
		final CompanyManager companyManager = new CompanyManager();
		final Company company = companyManager.findCompanyById(companyId);
		
		System.out.println(company);
	}
}
