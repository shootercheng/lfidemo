package threadsafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author chengdu
 * @date 2019/8/25.
 */
public class AtomicBooleanTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AtomicBooleanTest.class);

    private static AtomicBoolean isRunning = new AtomicBoolean(false);

    public static void testAtomic(){
        if (!isRunning.get()){
            LOGGER.info("start running---------------");
            isRunning.set(true);
            try {
                Thread.sleep(4000);
                LOGGER.info("end running------------------");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                isRunning.set(false);
            }
        } else {
            LOGGER.info("handler is running");
        }
    }
}
