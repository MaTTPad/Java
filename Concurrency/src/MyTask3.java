public class MyTask3 implements Task {
    @Override
    public void run(int taskNumber) throws InterruptedException {
        long t1=System.currentTimeMillis();
        while(t1+10000>System.currentTimeMillis())
        {
            Thread.yield();
            if(Thread.interrupted())
                break;
        }
    }
}
