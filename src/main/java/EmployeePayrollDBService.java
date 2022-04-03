import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollDBService {
	/* This method is used to getconnection */
	private Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/payroll_services";
		String uname = "root";
		String password = "Jia@21AZ";
		Connection connection = null;
		System.out.println("Connecting to database " + url);
		try {
			connection = DriverManager.getConnection(url, uname, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("connection is successful!!!!!" + connection);
		return connection;
	}

	public List<EmployeePayrollData> readData() {
		String sql = "select * from employee_payroll";
		List<EmployeePayrollData> employeePayrollList = new ArrayList<EmployeePayrollData>();
		try (Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				double salary = resultSet.getDouble("salary");
				LocalDate startDate = resultSet.getDate("startDate").toLocalDate();
				EmployeePayrollData employeePayrollData = new EmployeePayrollData(id, name, salary, startDate);
				employeePayrollList.add(employeePayrollData);
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeePayrollList;
	}

}
