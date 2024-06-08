import java.util.Random;

public class Test {
    private int count = 0;

    public void increment() {
        count++;
    }

    public void decrement() {
        count--;
    }

    public void printFinalValue() {
        System.out.println("counter=" + count);
    }

    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
        Random random = new Random();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    test.increment();
                    try {
                        Thread.sleep(random.nextInt(5));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    test.decrement();
                    try {
                        Thread.sleep(random.nextInt(5));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        //выводим результат общего ресурса
        test.printFinalValue();

    }
}
