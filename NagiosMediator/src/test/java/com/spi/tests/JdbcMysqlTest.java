package com.spi.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdbcMysqlTest
{
	protected static final Logger LOGGER = LoggerFactory.getLogger(JdbcMysqlTest.class);

	@Before
	public void prepareDatabase()
	{
		String dbUrl = "jdbc:mysql://localhost:3306/world";
		String dbClass = "com.mysql.jdbc.Driver";
		String query1 = "CREATE DATABASE IF NOT EXISTS VBS_Device_Monitoring_test;";
		String query2 = "USE VBS_Device_Monitoring_test;";
		String query3 = "CREATE TABLE IF NOT EXISTS test_table (ID INT(2), Name CHAR(20), CountryCode CHAR(3));";
		String query4 = "INSERT INTO test_table (ID, Name, CountryCode) VALUES (1, 'Kabul', 'AFG');";
		String query5 = "INSERT INTO test_table (ID, Name, CountryCode) VALUES (1, 'Kandahar', 'AFG');";
		String username = "root";
		String password = "root";
		try
		{
			Class.forName(dbClass);
			Connection connection = DriverManager.getConnection(dbUrl, username, password);
			Statement statement = connection.createStatement();
			statement.executeUpdate(query1);
			statement.executeUpdate(query2);
			statement.executeUpdate(query3);
			statement.executeUpdate(query4);
			statement.executeUpdate(query5);
			connection.close();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testRead()
	{
		String dbUrl = "jdbc:mysql://localhost:3306/VBS_Device_Monitoring_test";
		String dbClass = "com.mysql.jdbc.Driver";
		String query = "SELECT * FROM test_table WHERE CountryCode = 'AFG';";
		String username = "root";
		String password = "root";
		try
		{

			Class.forName(dbClass);
			Connection connection = DriverManager.getConnection(dbUrl, username, password);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next())
			{
				int id = resultSet.getInt("ID");
				String name = resultSet.getString("Name");
				String countryCode = resultSet.getString("CountryCode");
				System.out.println("ID : " + id + " Name : " + name + " Country Code : " + countryCode);
			}
			connection.close();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testInsert()
	{
		String dbUrl = "jdbc:mysql://localhost:3306/VBS_Device_Monitoring_test";
		String dbClass = "com.mysql.jdbc.Driver";
		String query = "INSERT INTO test_table (ID, Name, CountryCode) VALUES (1, 'Herat', 'AFG');";
		String query2 = "SELECT * FROM test_table WHERE CountryCode = 'AFG';";
		String username = "root";
		String password = "root";
		try
		{
			Class.forName(dbClass);
			Connection connection = DriverManager.getConnection(dbUrl, username, password);
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			ResultSet resultSet = statement.executeQuery(query2);
			while (resultSet.next())
			{
				int id = resultSet.getInt("ID");
				String name = resultSet.getString("Name");
				String countryCode = resultSet.getString("CountryCode");
				System.out.println("ID : " + id + " Name : " + name + " Country Code : " + countryCode);
			}
			connection.close();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@After
	public void removeDatabase()
	{
		String dbUrl = "jdbc:mysql://localhost:3306/test";
		String dbClass = "com.mysql.jdbc.Driver";
		String query = "DROP DATABASE IF EXISTS VBS_Device_Monitoring_test;";
		String username = "root";
		String password = "root";
		try
		{
			Class.forName(dbClass);
			Connection connection = DriverManager.getConnection(dbUrl, username, password);
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			connection.close();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
