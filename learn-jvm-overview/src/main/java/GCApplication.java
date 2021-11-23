import it.learn.gc.pojo.DirtyObj;
import it.learn.gc.pojo.DirtyStaticObj;

import java.util.concurrent.TimeUnit;

public class GCApplication {

    /**
     * java CGApplication 5 0 500 1 -server -Xms512m -Xmx512M -XX:UseConcMarkSweepGC -XX:+PrintGCDetails
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {

        TimeUnit.SECONDS.sleep(20);

        int waitTime = Integer.valueOf(args[0]) * 60 * 1000; // MINUTE

        int forYoung = Integer.valueOf(args[1]);

        int forTimes = Integer.valueOf(args[2]);

        int byteSize = Integer.valueOf(args[3]) * 1024 * 1024; // MB;

        for (int i=0; i < forTimes; i++) {
            if (forYoung == 1) {
                new DirtyObj(byteSize);
            } else {
                DirtyStaticObj.put(byteSize);
            }
            TimeUnit.MILLISECONDS.sleep(200);
        }

        synchronized (GCApplication.class) {
            try {
                GCApplication.class.wait(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
