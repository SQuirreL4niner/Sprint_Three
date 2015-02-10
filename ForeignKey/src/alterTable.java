

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class alterTable {






	private Connection con;
	private Statement stmt;
	private ResultSet rs;


	public void insertRecords() throws SQLException{

		try {


			//////////////////////  JDBC CONNECTION with ORACLE						///////////////////////////////////////////////

			//load and register JDBC Driver for Oracle DBMS
			Class.forName("oracle.jdbc.OracleDriver");

			//Create connection object
			con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Admin","none");

			//Create Statement object
			stmt= con.createStatement();

			//enable transcation by setting auto-commit to false

			con.setAutoCommit(false);
			

			
			
			String test = "CREATE TABLE Users (" +
					"Users_iD numeric(10) not null, "+
					"uName varchar2(200) not null, "+
					"uPass varchar2(200) not null, "+
					"CONSTRAINT Users_pk PRIMARY KEY (Users_iD, uName, uPass))";

		    System.out.println("Users table generated successfully");
		    
		    
		    String test_2 = "CREATE TABLE Accounts ("+
					"Accounts_iD numeric(10) not null, "+
					"Users_iD numeric(10) not null, "+
					"uName varchar2(200) not null, "+
					"uPass varchar2(200) not null, "+
					"CONSTRAINT fk_Users "+
					"FOREIGN KEY (Users_iD, uName, uPass) "+
					"REFERENCES Users(Users_iD, uName, uPass))";
		    
		    System.out.println("Accounts table generated successfully");
			stmt.executeUpdate(test);
		    stmt.executeUpdate(test_2);

					




			con.commit();

		} catch (ClassNotFoundException e) {
			con.rollback();
			e.printStackTrace();

		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();

		}

		finally{

			con.close();
		}


	}


		



public static void main(String[] args) throws SQLException {

	alterTable trans= new alterTable();

	trans.insertRecords();
}	






}
