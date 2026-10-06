/**
 * Nguyễn Phúc Trường An
 * 202419022
 */
public class SalesEmployee extends Employee {

    private double baseSalary;
    private double salesRevenue;
    private double commissionRate;

    // ── Constructor nạp chồng ────────────────────────────────────────────────

    /** Constructor rút gọn */
    public SalesEmployee(String id, String name,
            double baseSalary, double salesRevenue, double commissionRate) {
        this(id, name, "Unassigned", baseSalary, salesRevenue, commissionRate, 0);
    }

    /** Constructor đầy đủ */
    public SalesEmployee(String id, String name, String department,
            double baseSalary, double salesRevenue,
            double commissionRate, double bonus) {
        super(id, name, department);
        if (baseSalary < 0)
            throw new IllegalArgumentException("Lương cơ bản không được âm.");
        if (salesRevenue < 0)
            throw new IllegalArgumentException("Doanh số không được âm.");
        if (commissionRate <= 0 || commissionRate > 0.3)
            throw new IllegalArgumentException("Tỷ lệ hoa hồng phải trong khoảng (0, 0.3].");

        this.baseSalary = baseSalary;
        this.salesRevenue = salesRevenue;
        this.commissionRate = commissionRate;

        if (bonus > 0)
            addBonus(bonus);
    }

    // ── Override ─────────────────────────────────────────────────────────────

    @Override
    public double calculateGrossPay() {
        return baseSalary + salesRevenue * commissionRate + getMonthlyBonus();
    }

    @Override
    public String getEmployeeType() {
        return "Nhân viên kinh doanh";
    }

    @Override
    public void displayPayrollInfo() {
        System.out.printf("%-20s : %s%n", "Mã nhân sự", getEmployeeId());
        System.out.printf("%-20s : %s%n", "Họ tên", getFullName());
        System.out.printf("%-20s : %s%n", "Phòng ban", getDepartment());
        System.out.printf("%-20s : %s%n", "Loại", getEmployeeType());
        System.out.printf("%-20s : %,.0f đ%n", "Lương cơ bản", baseSalary);
        System.out.printf("%-20s : %,.0f đ%n", "Doanh số", salesRevenue);
        System.out.printf("%-20s : %.0f%%%n", "Tỷ lệ HH", commissionRate * 100);
        System.out.printf("%-20s : %,.0f đ%n", "Hoa hồng", salesRevenue * commissionRate);
        System.out.printf("%-20s : %,.0f đ%n", "Thưởng", getMonthlyBonus());
        System.out.printf("%-20s : %,.0f đ%n", "▶ Thu nhập", calculateGrossPay());
    }

    // ── Setter ───────────────────────────────────────────────────

    public void setSalesRevenue(double salesRevenue) {
        if (salesRevenue < 0)
            throw new IllegalArgumentException("Doanh số không được âm.");
        this.salesRevenue = salesRevenue;
    }

    // ── Getter ───────────────────────────────────────────────────────────────

    public double getBaseSalary() {
        return baseSalary;
    }

    public double getSalesRevenue() {
        return salesRevenue;
    }

    public double getCommissionRate() {
        return commissionRate;
    }
}
