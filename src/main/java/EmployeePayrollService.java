import java.util.List;

public class EmployeePayrollService {

	private List<EmployeePayrollData> employeePayrollList;

	public enum IOSevice {
		CONSOLE_IO, FILE_IO, DB_IO, REST_IO
	}

	public List<EmployeePayrollData> readEmployeePayrollData(IOSevice ioSevice) {
		if (ioSevice.equals(IOSevice.DB_IO))
			this.employeePayrollList = new EmployeePayrollDBService().readData();

		return this.employeePayrollList;
	}

}
