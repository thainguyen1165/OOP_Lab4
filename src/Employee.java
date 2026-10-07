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
            throw new IllegalArgumentException("Ma nhan su khong duoc rong");
        if (fullName == null || fullName.isBlank())
            throw new IllegalArgumentException("Ho ten khong duoc rong");
        if (department == null || department.isBlank())
            throw new IllegalArgumentException("Phong ban khong duoc rong");

        this.employeeId = employeeId.trim();
        this.fullName = fullName.trim();
        this.department = department.trim();
        this.monthlyBonus = 0;
    }

    public void addBonus(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Khoan thuong >= 0");
        this.monthlyBonus += amount;
    }

    public void addBonus(double amount, String reason) {
        if (reason == null || reason.isBlank())
            throw new IllegalArgumentException("Ly do thuong khong duoc rong");
        addBonus(amount);
        System.out.printf("  [Thuong] %s | +%,.0f đ | Lý do: %s%n",
                fullName, amount, reason);
    }

    public void addBonus(double rate, double referenceAmount, String reason) {
        if (rate <= 0 || rate > 0.5)
            throw new IllegalArgumentException("Ty le thuong phai thuoc (0, 0.5].");
        if (referenceAmount <= 0)
            throw new IllegalArgumentException("Gia tri tham chieu > 0");
        if (reason == null || reason.isBlank())
            throw new IllegalArgumentException("Ly do thuong khong duoc rong");

        double amount = rate * referenceAmount;
        addBonus(amount);
        System.out.printf("  [Thuong] %s | %.0f%% × %,.0f = +%,.0f đ | Ly do: %s%n",
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
