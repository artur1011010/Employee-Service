import org.junit.jupiter.api.Test;


public class EmployeeTest {
    Service service = new Service();

    @Test
    public void test() {
        service.checkEmployeeAfterDate();
    }
}