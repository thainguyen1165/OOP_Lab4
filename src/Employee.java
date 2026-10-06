/**
 * Nguyen Chinh Thai
 * 202419094
 */
public abstract class Employee {

    private String employeeId;
    private String fullName;
    private String department;
    private double monthlyBonus;

    public Employee(String employeeId, String fullName) {
        this(employeeId, fullName, "Unassigned");
    }

    public Employee(String employeeId, String fullName, String department) {
        if (employeeId == null || employeeId.isBlank())
            throw new IllegalArgumentException("Mã nhân sự không được rỗng.");
        if (fullName == null || fullName.isBlank())
            throw new IllegalArgumentException("Họ tên không được rỗng.");
        if (department == null || department.isBlank())
            throw new IllegalArgumentException("Phòng ban không được rỗng.");

        this.employeeId = employeeId.trim();
        this.fullName = fullName.trim();
        this.department = department.trim();
        this.monthlyBonus = 0;
    }

    public void addBonus(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Khoản thưởng phải lớn hơn 0.");
        this.monthlyBonus += amount;
    }

    public void addBonus(double amount, String reason) {
        if (reason == null || reason.isBlank())
            throw new IllegalArgumentException("Lý do thưởng không được rỗng.");
        addBonus(amount);
        System.out.printf("  [Thưởng] %s | +%,.0f đ | Lý do: %s%n",
                fullName, amount, reason);
    }

    public void addBonus(double rate, double referenceAmount, String reason) {
        if (rate <= 0 || rate > 0.5)
            throw new IllegalArgumentException("Tỷ lệ thưởng phải trong khoảng (0, 0.5].");
        if (referenceAmount <= 0)
            throw new IllegalArgumentException("Giá trị tham chiếu phải lớn hơn 0.");
        if (reason == null || reason.isBlank())
            throw new IllegalArgumentException("Lý do thưởng không được rỗng.");

        double amount = rate * referenceAmount;
        addBonus(amount);
        System.out.printf("  [Thưởng] %s | %.0f%% × %,.0f = +%,.0f đ | Lý do: %s%n",
                fullName, rate * 100, referenceAmount, amount, reason);
    }

    public void resetBonus() {
        this.monthlyBonus = 0;
    }

    public abstract double calculateGrossPay();

    public abstract String getEmployeeType();

    public abstract void displayPayrollInfo();

    public String getEmployeeId() {
        return employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDepartment() {
        return department;
    }

    public double getMonthlyBonus() {
        return monthlyBonus;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s) – %s",
                employeeId, fullName, department, getEmployeeType());
    }
}
