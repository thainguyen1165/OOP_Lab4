/**
 * Nguyen Chinh Thai
 * 202419094
 */
public class HourlyEmployee extends Employee {

    private static final double REGULAR_HOURS = 160.0;
    private static final double OVERTIME_RATE = 1.5;
    private static final double MAX_HOURS = 250.0;

    private double hourlyRate;
    private double workedHours;

    public HourlyEmployee(String id, String name,
            double hourlyRate, double workedHours) {
        this(id, name, "Unassigned", hourlyRate, workedHours, 0);
    }

    public HourlyEmployee(String id, String name, String department,
            double hourlyRate, double workedHours, double bonus) {
        super(id, name, department);
        if (hourlyRate <= 0)
            throw new IllegalArgumentException("Don gia > 0");
        if (workedHours < 0 || workedHours > MAX_HOURS)
            throw new IllegalArgumentException(
                    "So gio lam thuoc [0, 250].");

        this.hourlyRate = hourlyRate;
        this.workedHours = workedHours;

        if (bonus > 0)
            addBonus(bonus);
    }

    @Override
    public double calculateGrossPay() {
        double basePay;
        if (workedHours <= REGULAR_HOURS) {
            basePay = workedHours * hourlyRate;
        } else {
            basePay = REGULAR_HOURS * hourlyRate
                    + (workedHours - REGULAR_HOURS) * hourlyRate * OVERTIME_RATE;
        }
        return basePay + getMonthlyBonus();
    }

    @Override
    public String getEmployeeType() {
        return "Nhân viên theo giờ";
    }

    @Override
    public void displayPayrollInfo() {
        boolean hasOvertime = workedHours > REGULAR_HOURS;
        double regularPay = Math.min(workedHours, REGULAR_HOURS) * hourlyRate;
        double overtimePay = hasOvertime
                ? (workedHours - REGULAR_HOURS) * hourlyRate * OVERTIME_RATE
                : 0;

        System.out.println("─────────────────────────────────────────────");
        System.out.printf("%-20s : %s%n", "Ma nhan su", getEmployeeId());
        System.out.printf("%-20s : %s%n", "Ho ten", getFullName());
        System.out.printf("%-20s : %s%n", "Phong ban", getDepartment());
        System.out.printf("%-20s : %s%n", "Loai", getEmployeeType());
        System.out.printf("%-20s : %,.0f đ/h%n", "Đon gia", hourlyRate);
        System.out.printf("%-20s : %.0f h%n", "So gio", workedHours);
        System.out.printf("%-20s : %,.0f đ%n", "Luong thuong", regularPay);
        if (hasOvertime)
            System.out.printf("%-20s : %,.0f đ%n", "Luong OT", overtimePay);
        System.out.printf("%-20s : %,.0f đ%n", "Thuong", getMonthlyBonus());
        System.out.printf("%-20s : %,.0f đ%n", "▶ Thu nhap", calculateGrossPay());
    }

    public void setWorkedHours(double workedHours) {
        if (workedHours < 0 || workedHours > MAX_HOURS)
            throw new IllegalArgumentException("So gio lam thuoc [0, 250].");
        this.workedHours = workedHours;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getWorkedHours() {
        return workedHours;
    }
}
