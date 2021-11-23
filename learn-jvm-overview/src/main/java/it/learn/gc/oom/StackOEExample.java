package it.learn.gc.oom;

/**
 * StackOverflowError example
 * <p>
 * 虚拟机栈 和 本地方法栈溢出示例
 * 由于HotSpot虚拟机中并不区分虚拟机栈和本地方法栈，因此对于HotSpot来说，-Xoss参数（设置本地方法栈大小）虽然存在，但实际上是没有任何效果的，栈容量只能由(-Xss)参数来设定。
 * 关于虚拟机栈和本地方法栈，在《Java虚拟机规范》中描述了两种异常：
 * 1）如果线程请求的栈深度大于虚拟机所允许的最大深度，或者栈内存可用容量不足容纳新增栈帧时，将抛出StackOverflowError异常。
 * 2）如果虚拟机的栈内存允许动态扩展，当扩展栈容量无法申请到足够的内存时，将抛出 OutOfMemoryError异常。
 * <p>
 * demo1, VM Args：-Xss128k
 */
public class StackOEExample {

    private int stackLength = 1;

    public static void main(String[] args) throws Throwable {
        StackOEExample oom = new StackOEExample();
        // stack overflow caused by stack depth too small
//         oom.stackDepth();

        // stack overflow caused by stack frame larger than available stack mem
//         oom.stackCapacity();

        // VM Args: -Xss256m
        // oom cased by too much thread need to allocate stack mem
         oom.oomByMultiThread();
    }

    public void stackDepth() {
        stackLength++;
        try {
            stackDepth();
        } catch (StackOverflowError error) {
            System.out.println("stack depth :" + stackLength);
            //error.printStackTrace();
        }
    }

    public void stackCapacity() {
        // 8B * 128 = 1024B = 1KB
        long unused1, unused2, unused3, unused4, unused5,
                unused6, unused7, unused8, unused9, unused10,
                unused11, unused12, unused13, unused14, unused15,
                unused16, unused17, unused18, unused19, unused20,
                unused21, unused22, unused23, unused24, unused25,
                unused26, unused27, unused28, unused29, unused30,
                unused31, unused32, unused33, unused34, unused35,
                unused36, unused37, unused38, unused39, unused40,
                unused41, unused42, unused43, unused44, unused45,
                unused46, unused47, unused48, unused49, unused50,
                unused51, unused52, unused53, unused54, unused55,
                unused56, unused57, unused58, unused59, unused60,
                unused61, unused62, unused63, unused64, unused65,
                unused66, unused67, unused68, unused69, unused70,
                unused71, unused72, unused73, unused74, unused75,
                unused76, unused77, unused78, unused79, unused80,
                unused81, unused82, unused83, unused84, unused85,
                unused86, unused87, unused88, unused89, unused90,
                unused91, unused92, unused93, unused94, unused95,
                unused96, unused97, unused98, unused99, unused100,
                unused101, unused102, unused103, unused104, unused105,
                unused106, unused107, unused108, unused109, unused110,
                unused111, unused112, unused113, unused114, unused115,
                unused116, unused117, unused118, unused119, unused120,
                unused121, unused122, unused123, unused124, unused125,
                unused126, unused127, unused128;
        stackLength++;
        try {
//            stackCapacity();
        } catch (StackOverflowError error) {
            System.out.println("stack depth :" + stackLength);
            System.out.println("stack overflow");
            error.printStackTrace();
        }
//        unused1 = unused2 = unused3 = unused4 = unused5 = unused6 = unused7 = unused8 = unused9 = unused10 = unused11 = unused12 = unused13 = unused14 = unused15 = unused16 = unused17 = unused18 = unused19 = unused20 =
//        unused21 = unused22 = unused23 = unused24 = unused25 = unused26 = unused27 = unused28 = unused29 = unused30 = unused31 = unused32 = unused33 = unused34 = unused35 = unused36 = unused37 = unused38 = unused39 = unused40 =
//        unused41 = unused42 = unused43 = unused44 = unused45 = unused46 = unused47 = unused48 = unused49 = unused50 = unused51 = unused52 = unused53 = unused54 = unused55 = unused56 = unused57 = unused58 = unused59 = unused60 =
//        unused61 = unused62 = unused63 = unused64 = unused65 = unused66 = unused67 = unused68 = unused69 = unused70 = unused71 = unused72 = unused73 = unused74 = unused75 = unused76 = unused77 = unused78 = unused79 = unused80 =
//        unused81 = unused82 = unused83 = unused84 = unused85 = unused86 = unused87 = unused88 = unused89 = unused90 = unused91 = unused92 = unused93 = unused94 = unused95 = unused96 = unused97 = unused98 = unused99 = unused100 = 0;
    }

    public void oomByMultiThread() {
        while (true) {
            new Thread(() -> waitHere()).start();
            System.out.println("thread count :" + stackLength++);
        }
    }

    private void waitHere() {
        stackCapacity();

        synchronized (this) {
            try {
                this.wait(60 * 60 * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
