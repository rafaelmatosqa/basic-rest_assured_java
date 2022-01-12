import com.matos.Basic;
import com.matos.SumValidation;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestRun {

    @Test
    public void runAllTests() throws IOException {
        Basic basic = new Basic();
        SumValidation sumValidation = new SumValidation();
        basic.flowE2E();
        sumValidation.sumCourses();

    }
}
