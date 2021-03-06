import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class EmployeePayrollServiceTest {

	@Test
	public void given3Employees_WhenWrittenToFile_ShouldMatchEmployeeEntries() {

		EmployeePayrollData[] arrayOfEmployees = { new EmployeePayrollData(1, "Jeff Bezos", 100000.0, LocalDate.now()),
				new EmployeePayrollData(2, "Bill Gates", 200000.0, LocalDate.now()),
				new EmployeePayrollData(3, "Mark Zuckerberg", 300000.0, LocalDate.now()) };
		EmployeePayrollService employeePayrollService;
		employeePayrollService = new EmployeePayrollService(Arrays.asList(arrayOfEmployees));
		employeePayrollService.writeEmployeeData(EmployeePayrollService.IOService.FILE_IO);

		employeePayrollService.printData(EmployeePayrollService.IOService.FILE_IO);
		long entries = employeePayrollService.countEntries(EmployeePayrollService.IOService.FILE_IO);
		Assert.assertEquals(3, entries);
	}

	@Test
	public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCount() {
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		List<EmployeePayrollData> employeePayrollData = employeePayrollService
				.readEmployeepayrollData(EmployeePayrollService.IOService.DB_IO);
		System.out.println(employeePayrollData);
		Assert.assertEquals(4, employeePayrollData.size());
	}
}
