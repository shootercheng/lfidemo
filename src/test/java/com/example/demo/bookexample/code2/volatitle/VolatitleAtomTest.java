package com.example.demo.bookexample.code2.volatitle;

/**
 * @author chengdu
 * @date 2019/8/6.
 */
public class VolatitleAtomTest {

    private static int i;
    private static volatile int j;

    private static class VoAtomThread extends Thread {

        public VoAtomThread(String name){
            super.setName(name);
        }

        @Override
        public void run(){
            for (int x = 0; x < 10000; x++){
                i++;
                j++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        VoAtomThread voAtomThread = new VoAtomThread("volatile atom test");
        voAtomThread.start();
        voAtomThread.join();
        System.out.println(i + "__" + j);

        VoAtomThread[] voAtomThreads = new VoAtomThread[10];
        for (int k = 0; k < 10; k++){
            voAtomThreads[k] = new VoAtomThread("voatomthread " + k);
            voAtomThreads[k].start();
        }

        for (int k = 0; k < 10; k++){
            VoAtomThread voAtom = voAtomThreads[k];
            voAtom.join();
        }
        System.out.println(i + "__" + j);
    }
}
