/**
 * Nguyen Chinh Thai
 * 202419094
 */
public class SalariedEmployee extends Employee {

    private double monthlySalary;
    private double responsibilityAllowance;

    public SalariedEmployee(String id, String name, double monthlySalary) {
        this(id, name, "Unassigned", monthlySalary, 0, 0);
    }

    public SalariedEmployee(String id, String name, String department,
            double monthlySalary, double allowance, double bonus) {
        super(id, name, department);
        if (monthlySalary < 0)
            throw new IllegalArgumentException("Luong >= 0.");
        if (allowance < 0)
            throw new IllegalArgumentException("Phu cap >= 0");

        this.monthlySalary = monthlySalary;
        this.responsibilityAllowance = allowance;

        if (bonus > 0)
            addBonus(bonus);
    }

    @Override
    public double calculateGrossPay() {
        return monthlySalary + responsibilityAllowance + getMonthlyBonus();
    }

    @Override
    public String getEmployeeType() {
        return "Nhân viên lương cố định";
    }

    @Override
    public void displayPayrollInfo() {
        System.out.println("─────────────────────────────────────────────");
        System.out.printf("%-20s : %s%n", "Ma nhan su", getEmployeeId());
        System.out.printf("%-20s : %s%n", "Ho ten", getFullName());
        System.out.printf("%-20s : %s%n", "Phong ban", getDepartment());
        System.out.printf("%-20s : %s%n", "Loai", getEmployeeType());
        System.out.printf("%-20s : %,.0f đ%n", "Luong co ban", monthlySalary);
        System.out.printf("%-20s : %,.0f đ%n", "phu cap", responsibilityAllowance);
        System.out.printf("%-20s : %,.0f đ%n", "Thuong", getMonthlyBonus());
        System.out.printf("%-20s : %,.0f đ%n", "▶ Thu nhap", calculateGrossPay());
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    public double getResponsibilityAllowance() {
        return responsibilityAllowance;
    }
}
