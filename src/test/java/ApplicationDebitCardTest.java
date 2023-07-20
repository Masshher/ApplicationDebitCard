import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class ApplicationDebitCardTest {



    @Test
    void shouldTest() {
        open("http://localhost:9999");
    }
}
