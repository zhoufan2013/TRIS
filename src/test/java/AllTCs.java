import com.ai.tc.LoginTC;
import com.ai.tc.MenuTC;
import com.ai.tc.ServiceTC;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by zhoufan on 15/5/8.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTC.class,
        MenuTC.class,
        ServiceTC.class
})
public class AllTCs {

}
