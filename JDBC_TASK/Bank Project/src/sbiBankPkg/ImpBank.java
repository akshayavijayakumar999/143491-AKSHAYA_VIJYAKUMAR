package sbiBankPkg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class ImpBank   {
	public static void main(String[] args) {

		int n1,age;
		String name;
		char c1;
		Connection con;
		Statement stmt;

		Scanner s = new Scanner(System.in);
		SBIBank b=new SBIBank();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","root");
			//			
			//			stmt = con.createStatement(); // Statement:Interface
			//				
			//			b.create_database(con, stmt);

			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/sbibank","root","root");
			stmt = con.createStatement();
			//			b.create_table_cust_det(con, stmt);	
			System.out.println("Database created...");

			System.out.println("Table for Customer Details created...");

			//			b.create_table_acc_det(con, stmt);
			System.out.println("Table for Account Details created...");
			//.trig_acc_det(con, stmt);

			do {

				System.out.println("//****** WElCOME TO STATE BANK OF INDIA ******//");
				System.out.println();
				System.out.println("1. New Registration");
				System.out.println("2. User Login ");
				System.out.println("3. Show Customers  ");
				System.out.println("Please Enter Your Choice : ");
				n1 = s.nextInt();
				System.out.println("_____________________________________");
				switch(n1)
				{
				case 1:
					System.out.println();
					System.out.println("Enter your Name:");
					name = s.next();
					System.out.println("Enter your Age:");
					age = s.nextInt();
					System.out.println("_____________________________________");
					b.customer_details(name,age, con, stmt);
					break;

				case 2:
					break;

				case 3:
					System.out.println();
					b.show_cust_details(con,stmt);
					System.out.println("_____________________________________");

					break;

				default:System.out.println("Sorry,You have entered Wrong Choice!!!!!!");
				System.out.println();
				break;
				} // closing first switch
				System.out.println();
				System.out.println("Do you want to Logout?(Y/N): ");	
				c1=s.next().charAt(0); 

			}// closing outer do
			while(c1=='n'||c1=='N');
			s.close();	
		}// Closing Try

		catch (Exception e)
		{
			System.out.println(e);	
		}

		s.close();
	}
}