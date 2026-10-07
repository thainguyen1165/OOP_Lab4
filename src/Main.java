/*
 * Nguyen Chinh Thai
 * 202419094
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("Kiem thu du lieu");

        SalariedEmployee e001 = new SalariedEmployee(
                "E001", "Nguyễn Minh An", "Đào tạo",
                15_000_000, 2_000_000, 0);
        e001.addBonus(1_000_000, "Thưởng hoàn thành KPI");

        HourlyEmployee e002 = new HourlyEmployee(
                "E002", "Trần Thu Bình", "Hỗ trợ",
                100_000, 150, 0);
        e002.addBonus(500_000, "Thưởng chuyên cần");

        HourlyEmployee e003 = new HourlyEmployee(
                "E003", "Lê Hoàng Chi", "Hỗ trợ",
                100_000, 170, 0);

        SalesEmployee e004 = new SalesEmployee(
                "E004", "Phạm Quốc Dũng", "Kinh doanh",
                8_000_000, 200_000_000, 0.05, 0);
        e004.addBonus(0.02, 50_000_000, "Thưởng vượt chỉ tiêu");

        Payroll payroll = new Payroll("2026-09");
        payroll.addEmployee(e001);
        payroll.addEmployee(e002);
        payroll.addEmployee(e003);
        payroll.addEmployee(e004);

        payroll.displayPayroll();

        System.out.printf("%n%-25s : %,.0f đ  (mong doi: 33,000,000)%n",
                "Tong phong ho tro",
                payroll.calculatePayrollByDepartment("Ho tro"));

        assertGross("E001", e001.calculateGrossPay(), 18_000_000);
        assertGross("E002", e002.calculateGrossPay(), 15_500_000);
        assertGross("E003", e003.calculateGrossPay(), 17_500_000);
        assertGross("E004", e004.calculateGrossPay(), 19_000_000);
        assertGross("TOTAL", payroll.calculateTotalPayroll(), 70_000_000);

        System.out.println("\nKIEM THU");

        runBoundaryTests();

    }

    private static void runBoundaryTests() {

        // T01: employeeId rỗng
        test("T01 – employeeId rỗng", () -> new SalariedEmployee("", "Tên", 10_000_000));

        // T02: fullName rỗng
        test("T02 – fullName rỗng", () -> new SalariedEmployee("X001", "", 10_000_000));

        // T03: addBonus âm
        test("T03 – addBonus âm", () -> {
            Employee e = new SalariedEmployee("X002", "Test", 5_000_000);
            e.addBonus(-100_000);
        });

        // T04: workedHours = 0 (biên dưới)
        test("T04 – workedHours = 0 (hợp lệ)", () -> {
            HourlyEmployee e = new HourlyEmployee("X003", "Test", 100_000, 0);
            System.out.printf("       grossPay = %,.0f đ%n", e.calculateGrossPay());
        }, false);

        // T05: workedHours = 160 (biên thường)
        test("T05 – workedHours = 160 (biên, hợp lệ)", () -> {
            HourlyEmployee e = new HourlyEmployee("X004", "Test", 100_000, 160);
            System.out.printf("       grossPay = %,.0f đ (mong đợi 16,000,000)%n",
                    e.calculateGrossPay());
        }, false);

        // T06: workedHours = 161 (bắt đầu OT)
        test("T06 – workedHours = 161 (OT, hợp lệ)", () -> {
            HourlyEmployee e = new HourlyEmployee("X005", "Test", 100_000, 161);
            System.out.printf("       grossPay = %,.0f đ (mong đợi 16,150,000)%n",
                    e.calculateGrossPay());
        }, false);

        // T07: workedHours = 250 (biên tối đa)
        test("T07 – workedHours = 250 (hợp lệ)", () -> {
            HourlyEmployee e = new HourlyEmployee("X006", "Test", 100_000, 250);
            System.out.printf("       grossPay = %,.0f đ%n", e.calculateGrossPay());
        }, false);

        // T08: workedHours = 251 (vượt max)
        test("T08 – workedHours = 251 (lỗi)", () -> new HourlyEmployee("X007", "Test", 100_000, 251));

        // T09: commissionRate = 0 (lỗi)
        test("T09 – commissionRate = 0 (lỗi)", () -> new SalesEmployee("X008", "Test", 5_000_000, 100_000_000, 0.0));

        // T10: commissionRate = 0.3 (biên hợp lệ)
        test("T10 – commissionRate = 0.3 (hợp lệ)", () -> {
            SalesEmployee e = new SalesEmployee("X009", "Test", 5_000_000, 100_000_000, 0.3);
            System.out.printf("       grossPay = %,.0f đ%n", e.calculateGrossPay());
        }, false);

        // T11: commissionRate = 0.31 (vượt biên)
        test("T11 – commissionRate = 0.31 (lỗi)",
                () -> new SalesEmployee("X010", "Test", 5_000_000, 100_000_000, 0.31));

        // T12: addBonus rate = 0.5 (biên hợp lệ)
        test("T12 – addBonus rate = 0.5 (hợp lệ)", () -> {
            Employee e = new SalariedEmployee("X011", "Test", 5_000_000);
            e.addBonus(0.5, 2_000_000, "Thưởng tối đa");
            System.out.printf("       bonus = %,.0f đ (mong đợi 1,000,000)%n",
                    e.getMonthlyBonus());
        }, false);

        // T13: addBonus rate = 0.51 (vượt biên)
        test("T13 – addBonus rate = 0.51 (lỗi)", () -> {
            Employee e = new SalariedEmployee("X012", "Test", 5_000_000);
            e.addBonus(0.51, 2_000_000, "Thưởng vượt");
        });

        // T14: Thêm nhân viên trùng mã
        test("T14 – Trùng mã nhân sự (từ chối)", () -> {
            Payroll p = new Payroll("2026-10");
            Employee a = new SalariedEmployee("DUP1", "Người A", 5_000_000);
            Employee b = new SalariedEmployee("DUP1", "Người B", 6_000_000);
            p.addEmployee(a);
            boolean added = p.addEmployee(b);
            System.out.printf("       Kết quả addEmployee lần 2: %b (mong đợi: false)%n", added);
        }, false);

        // T15: displayPayroll với danh sách rỗng
        test("T15 – Bảng lương rỗng", () -> {
            Payroll p = new Payroll("2026-10");
            p.displayPayroll();
        }, false);
    }

    /** Chạy test và mong đợi exception */
    private static void test(String name, Runnable r) {
        test(name, r, true);
    }

    /** Chạy test, expectException = true nếu mong đợi lỗi */
    private static void test(String name, Runnable r, boolean expectException) {
        System.out.printf("%-45s → ", name);
        try {
            r.run();
            if (expectException) {
                System.out.println("FAIL");
            } else {
                System.out.println("PASS");
            }
        } catch (IllegalArgumentException ex) {
            if (expectException) {
                System.out.printf("PASS (%s)%n", ex.getMessage());
            } else {
                System.out.printf("FAIL (lỗi ngoài dự kiến: %s)%n", ex.getMessage());
            }
        }
    }

    private static void assertGross(String label, double actual, double expected) {
        boolean pass = Math.abs(actual - expected) < 1.0;
        System.out.printf("%-10s : %,.0f đ  %s%n",
                label, actual, pass ? "PASS" : "FAIL (mong doi " + expected + ")");
    }
}