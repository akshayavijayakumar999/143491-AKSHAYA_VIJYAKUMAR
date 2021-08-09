package sbiBankPkg;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SBIBank extends Bank {

	Connection con;
	Statement stmt;
	String sql;
	ResultSet rs;

	void create_database(Connection con,Statement stmt) throws SQLException
	{
		sql="create database sbibank";
		stmt.executeUpdate(sql);
	}

	void create_table_cust_det(Connection con,Statement stmt) throws SQLException
	{
		sql="create table cust_details("
				+ "cust_id int primary key auto_increment,"
				+ "cust_name varchar(200) not null,"
				+ "cust_age int not null,"
				+ "CHECK (cust_age>18)"
				+ ");";
		stmt.executeUpdate(sql);
	}

	void create_table_acc_det(Connection con,Statement stmt) throws SQLException
	{
		sql="create table account_details("
				+ "acc_no int primary key auto_increment,"
				+ " acc_id int not null,"
				+ "acc_name varchar(200) not null,"
				+"FOREIGN KEY (acc_id) REFERENCES cust_details (cust_id) ON DELETE CASCADE"+ ");";
		stmt.executeUpdate(sql);
		sql="alter table account_details auto_increment=1000;";
		stmt.executeUpdate(sql);
	}

	void customer_details(String name,int age,Connection con,Statement stmt) throws SQLException
	{
		sql = "insert into cust_details (cust_name,cust_age) values " + "( '" + name + " ',"+ age + ")";

		if (age>18) 
		{
			stmt.executeUpdate(sql);

			System.out.println("...Successfully Registered...Thank you you for choosing our service...");
			System.out.println();
			System.out.println("Please proceed with the basic deposit - Rs 1000/only");

		}
		else
		{
			System.out.println();
			System.out.println("*Age should be greater than 18*");
			System.out.println();
		}
	}

	void show_cust_details(Connection con,Statement stmt) throws SQLException
	{
		rs = stmt.executeQuery("SELECT * FROM cust_details");
		while (rs.next()) {
			// Retrieve by column name
			System.out.print( rs.getInt(1) + ". ");
			System.out.print("Cutomer Name: " + rs.getString(2));
			System.out.println(", Cutomer Age: " + rs.getInt(3));


		}
	}
	void trig_acc_det(Connection con,Statement stmt) throws SQLException
	{
		sql="create trigger cust_insert"
				+"after insert on cust_details"
				+"for each row"
				+"insert into account_details(acc_id, acc_name) values (new.cust_id, new.cust_name);";
		stmt.executeUpdate(sql);

	}

}