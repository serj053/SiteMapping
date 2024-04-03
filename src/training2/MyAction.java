package training2;

import java.util.concurrent.RecursiveAction;

public class MyAction extends RecursiveAction {
    private int value;

    public MyAction(int value) {
        this.value = value;
    }

    @Override
    protected void compute() {
        System.out.println("Incoming " + value);
        if (value <= 4) {
            System.out.println("Before: " + Thread.currentThread().getId() + " " + value);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("After: " + Thread.currentThread().getId() + " " + value);
        }else{
            MyAction m1= new MyAction(value/2);
            MyAction m2 = new MyAction(value/2);
            invokeAll(m1, m2);
        }
    }
}
