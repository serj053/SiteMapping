package training3;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Repeat extends RecursiveAction {
    public static void main(String[] args) {
        new Repeat(16).invoke();//выполнение в общм пуле
//        ForkJoinPool p = new ForkJoinPool();
//        Repeat task = new Repeat(64);
//        p.invoke(task);
    }

    private int threshold;

    public Repeat(int thr) {
        threshold = thr;
    }

    @Override
    protected void compute() {
        System.out.println("incoming threshold = " + threshold+" thread-"
                +Thread.currentThread().getId());
        if (threshold < 4) {
            System.out.println("threshold < 4"+" thread-"
                    +Thread.currentThread().getId());
        } else {
            int t1 = threshold / 2;
            Repeat r1 = new Repeat(t1);
            Repeat r2 = new Repeat(t1);
            //хзапускаем выполнение
            invokeAll(r1, r2);
        }
    }
}
