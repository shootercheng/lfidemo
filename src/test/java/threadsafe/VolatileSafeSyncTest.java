package threadsafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author chengdu
 * @date 2019/8/25.
 */
public class VolatileSafeSyncTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(VolatileSafeSyncTest.class);

    private static AtomicBoolean isRunning = new AtomicBoolean(false);


    public static void testSyncVolatile(){
        if (!isRunning.get()){
            synchronized (VolatileSafeSyncTest.class) {
                if (!isRunning.get()) {
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
        } else {
            LOGGER.info("handler is running");
        }
    }
}
