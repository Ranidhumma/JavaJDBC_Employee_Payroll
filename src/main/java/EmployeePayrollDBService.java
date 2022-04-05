import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollDBService {
	/* This method is used to getconnection */
	private Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_services", "root",
				"Pass@12");

		// connection = DriverManager.getConnection(url, uname, password);

		System.out.println("connection is successful!!!!!" + connection);
		return connection;

	}

	private PreparedStatement employeePayrollDataStatement;
	private static EmployeePayrollDBService employeePayrollDBService;

	/**
	 * create default constructor name as EmployeePayrollDbService
	 */
	public EmployeePayrollDBService() {
	}

	/**
	 * create a method name as getInstance
	 * 
	 * @return employeePayrollDBService
	 */
	public static EmployeePayrollDBService getInstance() {
		if (employeePayrollDBService == null)
			employeePayrollDBService = new EmployeePayrollDBService();
		return employeePayrollDBService;
	}

	/**
	 * create a method name as read data
	 * 
	 * @return employeePayrollList
	 */
	public List<EmployeePayrollData> readData() {
		String sql = "SELECT * FROM employee_payroll;";
		/**
		 * create a list object name as employeePayrollList EmployeePayrollData is a
		 * class
		 */
		List<EmployeePayrollData> employeePayrollList = new ArrayList<EmployeePayrollData>();

		/**
		 * Connection :- A Connection object's database is able to provide information
		 * describing its tables, its supported SQL grammar, its stored procedures, the
		 * capabilities of this connection, and so on. This information is obtained with
		 * the getMetaData method.
		 */
		try (Connection connection = this.getConnection()) {
			/**
			 * Statement :- The object used for executing a static SQL statement and
			 * returning the results it produces.
			 */
			Statement statement = connection.createStatement();
			/**
			 * ResultSet will be scrollable, will not show changes made by others, and will
			 * be updatable
			 */
			ResultSet resultSet = statement.executeQuery(sql);
			employeePayrollList = this.getEmployeePayrollData(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeePayrollList;
	}

	/**
	 * create a method name as updateEmployeeData,this is parameterized method
	 * 
	 * @param name   of employee
	 * @param salary of employee
	 * @return update data using statment name and salary
	 */
	public int updateEmployeeData(String name, double salary) {
		return this.updateDataUsingStatement(name, salary);
	}

	/**
	 * create a method name as updateDataUsingStatement,this is parameterized method
	 * 
	 * @param name   of employee
	 * @param salary of employee
	 * @return sql update statement
	 */
	private int updateDataUsingStatement(String name, double salary) {
		String sql = String.format("UPDATE employee_payroll SET salary = %.2f where name = '%s';", salary, name);
		try (Connection connection = this.getConnection();) {
			Statement statement = connection.createStatement();
			return statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * create a method name as preparedStatementForEmployeeData
	 */
	private void preparedStatementForEmployeeData() {
		try {
			Connection connection = this.getConnection();
			String sql = "Select * from employee_payroll WHERE name = ?";
			employeePayrollDataStatement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * create a method name as getEmployeePayrollData, this is parameterized method
	 * 
	 * @param name of employee
	 * @return employeePayrollList
	 */
	public List<EmployeePayrollData> getEmployeePayrollData(String name) {
		List<EmployeePayrollData> employeePayrollList = null;
		if (this.employeePayrollDataStatement == null)
			this.preparedStatementForEmployeeData();
		try {
			employeePayrollDataStatement.setString(1, name);
			ResultSet resultSet = employeePayrollDataStatement.executeQuery();
			employeePayrollList = this.getEmployeePayrollData(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeePayrollList;
	}

	/**
	 * create a method name as getEmployeePayrollData,this is parameterized method
	 * 
	 * @param resultSet is employee id , salary,name, start
	 * @return employeePayrollList
	 */
	private List<EmployeePayrollData> getEmployeePayrollData(ResultSet resultSet) {
		List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
		try {
			/**
			 * columnLable is id, name, salary,start
			 */
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				double salary = resultSet.getDouble("salary");
				LocalDate startDate = resultSet.getDate("start").toLocalDate();
				employeePayrollList.add(new EmployeePayrollData(id, name, salary, startDate));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeePayrollList;
	}
}
