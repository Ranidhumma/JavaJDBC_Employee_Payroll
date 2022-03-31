import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

public class JDBC {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/payroll_services";
		String uname = "root";
		String password = "Pass@12";

		Connection connection = null;

		/* to check if my driver is loaded or not */
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
		listDrivers();
		/* to establish connection with the data base */

		try {
			System.out.println("Connecting to database " + url);
			connection = DriverManager.getConnection(url, uname, password);
			System.out.println("connection is successful!!!!!" + connection);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void listDrivers() {
		Enumeration<Driver> driverList = DriverManager.getDrivers();
		while (driverList.hasMoreElements()) {
			Driver driverClass = driverList.nextElement();
			System.out.println(" " + driverClass.getClass().getName());
		}
	}
}
