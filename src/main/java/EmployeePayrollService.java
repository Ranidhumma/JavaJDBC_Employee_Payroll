import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {

	public enum IOService {
		CONSOLE_IO, FILE_IO, DB_IO, REST_IO
	}

	public static final String IOSevice = null;

	public List<EmployeePayrollData> employeePayrollList;

	public EmployeePayrollService() {
		// TODO Auto-generated constructor stub
	}

	public EmployeePayrollService(List<EmployeePayrollData> employeePayrollList) {
		// TODO Auto-generated constructor stub
		this.employeePayrollList = employeePayrollList;
	}

	public void readEmployeeData(Scanner consoleInputReader) {
		System.out.println("Enter employee ID : ");
		int id = Integer.parseInt(consoleInputReader.nextLine());
		System.out.println("Enter employee name : ");
		String name = consoleInputReader.nextLine();
		System.out.println("Enter employee salary : ");
		double salary = Double.parseDouble(consoleInputReader.nextLine());
		employeePayrollList.add(new EmployeePayrollData(id, name, salary));
	}

	public List<EmployeePayrollData> readEmployeepayrollData(IOService ioService) {
		if (ioService.equals(IOService.FILE_IO))
			return new EmployeePayrollFileIOService().readData();
		else if (ioService.equals(IOService.DB_IO))
			return new EmployeePayrollDBService().readData();
		else
			return null;
	}

	public void writeEmployeeData(IOService ioService) {
		if (ioService.equals(IOService.CONSOLE_IO))
			System.out.println("\nWriting Employee Payroll Roster to Console\n" + employeePayrollList);
		else if (ioService.equals(IOService.FILE_IO))
			new EmployeePayrollFileIOService().writeData(employeePayrollList);
	}

	public void printData(IOService ioService) {
		new EmployeePayrollFileIOService().printData();
	}

	public long countEntries(IOService iOService) {
		// TODO Auto-generated method stub
		if (iOService.equals(IOService.FILE_IO))
			return new EmployeePayrollFileIOService().countEntries();
		return 0;
	}

	public static void main(String[] args) {
		System.out.println("Welcome To Employee Payroll Application\n");
		List<EmployeePayrollData> employeePayrollList = new ArrayList<EmployeePayrollData>();
		EmployeePayrollService employeePayrollService = new EmployeePayrollService(employeePayrollList);
		Scanner consoleInputReader = new Scanner(System.in);
		employeePayrollService.readEmployeeData(consoleInputReader);
		employeePayrollService.writeEmployeeData(IOService.CONSOLE_IO);
	}

}
