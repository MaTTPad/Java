public class MyTask4 implements Task {

    @Override
    public void run(int taskNumber) throws InterruptedException {

        long t1=System.currentTimeMillis();
        while(t1+10000>System.currentTimeMillis())
        {
       fibbonaci(10000);
            if(Thread.interrupted())
                break;
        }
    }


    void fibbonaci(double n){
        double prev=0d, next=1d, result=0d;
        for (int i = 0; i < n; i++) {
            result=prev+next;
            prev=next;
            next=result;
        }
    }
}
