
/**
 * Nguyen Chinh Thai
 * 202419094
 */
import java.util.ArrayList;
import java.util.List;

public class Payroll {

    private String period;
    private List<Employee> employees;

    public Payroll(String period) {
        if (period == null || period.isBlank())
            throw new IllegalArgumentException("Ky luong khong duoc rong");
        this.period = period.trim();
        this.employees = new ArrayList<>();
    }

    public boolean addEmployee(Employee employee) {
        if (employee == null)
            return false;
        if (findEmployee(employee.getEmployeeId()) != null) {
            System.out.printf("⚠ Nhan su [%s] da ton tai trong bang luong %s.%n",
                    employee.getEmployeeId(), period);
            return false;
        }
        employees.add(employee);
        return true;
    }

    public Employee findEmployee(String employeeId) {
        for (Employee e : employees) {
            if (e.getEmployeeId().equalsIgnoreCase(employeeId))
                return e;
        }
        return null;
    }

    public double calculateTotalPayroll() {
        double total = 0;
        for (Employee e : employees) {
            total += e.calculateGrossPay();
        }
        return total;
    }

    public double calculatePayrollByDepartment(String department) {
        double total = 0;
        for (Employee e : employees) {
            if (e.getDepartment().equalsIgnoreCase(department)) {
                total += e.calculateGrossPay();
            }
        }
        return total;
    }

    public Employee findHighestPaidEmployee() {
        if (employees.isEmpty())
            return null;
        Employee highest = employees.get(0);
        for (Employee e : employees) {
            if (e.calculateGrossPay() > highest.calculateGrossPay()) {
                highest = e;
            }
        }
        return highest;
    }

    public void displayPayroll() {
        System.out.println("═════════════════════════════════════════════");
        System.out.printf("        BANG LUONG KY %s  (%d nhan su)%n",
                period, employees.size());
        System.out.println("═════════════════════════════════════════════");

        if (employees.isEmpty()) {
            System.out.println("  (Danh sach nhan su khong duoc trong)");
        } else {
            for (Employee e : employees) {
                e.displayPayrollInfo();
            }
            System.out.println("─────────────────────────────────────────────");
            System.out.printf("%-20s : %,.0f đ%n", "TONG BANG LUONG", calculateTotalPayroll());

            Employee top = findHighestPaidEmployee();
            if (top != null) {
                System.out.printf("%-20s : %s (%,.0f đ)%n",
                        "Thu nhap cao nhat", top.getFullName(), top.calculateGrossPay());
            }
        }
        System.out.println("═════════════════════════════════════════════");
    }

    public String getPeriod() {
        return period;
    }

    public int getEmployeeCount() {
        return employees.size();
    }
}
