package net.codejava.user.db;

import org.springframework.jdbc.datasource.DriverManagerDataSource;


public class DBDataSource {

	private static DriverManagerDataSource dataSource;
	
	public static DriverManagerDataSource setup() {
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/ff1");
		dataSource.setUsername("root");
		dataSource.setPassword("ciaolo");
		return dataSource;
	}
	
	
	
}
