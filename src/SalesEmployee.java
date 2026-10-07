/**
 * Nguyen Chinh Thai
 * 202419094
 */
public class SalesEmployee extends Employee {

    private double baseSalary;
    private double salesRevenue;
    private double commissionRate;

    public SalesEmployee(String id, String name,
            double baseSalary, double salesRevenue, double commissionRate) {
        this(id, name, "Unassigned", baseSalary, salesRevenue, commissionRate, 0);
    }

    public SalesEmployee(String id, String name, String department,
            double baseSalary, double salesRevenue,
            double commissionRate, double bonus) {
        super(id, name, department);
        if (baseSalary < 0)
            throw new IllegalArgumentException("Luong co ban >= 0");
        if (salesRevenue < 0)
            throw new IllegalArgumentException("Doanh so >= 0");
        if (commissionRate <= 0 || commissionRate > 0.3)
            throw new IllegalArgumentException("Ty le hoa hong thuoc (0, 0.3].");

        this.baseSalary = baseSalary;
        this.salesRevenue = salesRevenue;
        this.commissionRate = commissionRate;

        if (bonus > 0)
            addBonus(bonus);
    }

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
        System.out.printf("%-20s : %s%n", "Ma nhan su", getEmployeeId());
        System.out.printf("%-20s : %s%n", "Ho ten", getFullName());
        System.out.printf("%-20s : %s%n", "Phong ban", getDepartment());
        System.out.printf("%-20s : %s%n", "Loai", getEmployeeType());
        System.out.printf("%-20s : %,.0f đ%n", "Luong co ban", baseSalary);
        System.out.printf("%-20s : %,.0f đ%n", "Doanh so", salesRevenue);
        System.out.printf("%-20s : %.0f%%%n", "Ty le hoa hong", commissionRate * 100);
        System.out.printf("%-20s : %,.0f đ%n", "Hoa hong", salesRevenue * commissionRate);
        System.out.printf("%-20s : %,.0f đ%n", "Thuong", getMonthlyBonus());
        System.out.printf("%-20s : %,.0f đ%n", "▶ Thu nhap", calculateGrossPay());
    }

    public void setSalesRevenue(double salesRevenue) {
        if (salesRevenue < 0)
            throw new IllegalArgumentException("Doanh so >= 0");
        this.salesRevenue = salesRevenue;
    }

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
