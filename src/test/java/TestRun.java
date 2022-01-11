import com.matos.Basic;
import com.matos.SumValidation;
import org.testng.annotations.Test;

public class TestRun {

    @Test
    public void runAllTests(){
        Basic basic = new Basic();
        SumValidation sumValidation = new SumValidation();
        basic.flowE2E();
        sumValidation.sumCourses();
    }
}
