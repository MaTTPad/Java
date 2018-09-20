public class MyTask5 implements Task {
    @Override
    public void run(int taskNumber) throws InterruptedException {
        long t1=System.currentTimeMillis();
        int i=1;
        while(t1+10000>System.currentTimeMillis())
        {
            i*=10;
            if(Thread.interrupted())
                break;
        }
    }
}

