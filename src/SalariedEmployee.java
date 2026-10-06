/**
 * Nguyễn Phúc Trường An
 * 202419022
 */
public class SalariedEmployee extends Employee {

    private double monthlySalary;
    private double responsibilityAllowance;

    // ── Constructor nạp chồng ────────────────────────────────────────────────

    /** Constructor rút gọn: phụ cấp = 0, department = "Unassigned" */
    public SalariedEmployee(String id, String name, double monthlySalary) {
        this(id, name, "Unassigned", monthlySalary, 0, 0);
    }

    /** Constructor đầy đủ */
    public SalariedEmployee(String id, String name, String department,
            double monthlySalary, double allowance, double bonus) {
        super(id, name, department);
        if (monthlySalary < 0)
            throw new IllegalArgumentException("Lương tháng không được âm.");
        if (allowance < 0)
            throw new IllegalArgumentException("Phụ cấp không được âm.");

        this.monthlySalary = monthlySalary;
        this.responsibilityAllowance = allowance;

        if (bonus > 0)
            addBonus(bonus);
    }

    // ── Override ─────────────────────────────────────────────────────────────

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
        System.out.printf("%-20s : %s%n", "Mã nhân sự", getEmployeeId());
        System.out.printf("%-20s : %s%n", "Họ tên", getFullName());
        System.out.printf("%-20s : %s%n", "Phòng ban", getDepartment());
        System.out.printf("%-20s : %s%n", "Loại", getEmployeeType());
        System.out.printf("%-20s : %,.0f đ%n", "Lương cơ bản", monthlySalary);
        System.out.printf("%-20s : %,.0f đ%n", "Phụ cấp", responsibilityAllowance);
        System.out.printf("%-20s : %,.0f đ%n", "Thưởng", getMonthlyBonus());
        System.out.printf("%-20s : %,.0f đ%n", "▶ Thu nhập", calculateGrossPay());
    }

    // ── Getters ───────────────────────────────────────────────────────────────

    public double getMonthlySalary() {
        return monthlySalary;
    }

    public double getResponsibilityAllowance() {
        return responsibilityAllowance;
    }
}
