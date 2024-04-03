package training2;

import java.util.concurrent.ForkJoinPool;

public class Starter {
    public static void main(String[] args) {
        final int MAX= 64;
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new MyAction(MAX));
        System.out.println("FINISH");
    }
}
