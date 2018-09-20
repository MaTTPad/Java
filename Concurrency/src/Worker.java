import java.util.concurrent.LinkedBlockingQueue;

public class Worker {
    public LinkedBlockingQueue<Container> tasks;
    WorkerListener listener;
    Thread myThread;
    String workerName;
    Worker(String name) {
        workerName=name;
        tasks = new LinkedBlockingQueue<Container>();
    }

    boolean cont = true;
    boolean started = false;
    boolean working=false;

    public synchronized void start() {


        if(started)
            return;
        started=true;

        String name="Worker " + workerName + " thread";
        myThread=new Thread(name){
            @Override
            public synchronized void run() {
                int taskNumber=1;
                listener.onWorkerStarted();
                while (true) {
                    try {
                        if(tasks.size()==0) {
                            System.out.println("Queue is empty. Waiting for tasks.");
                        }

                        Container temp=tasks.take();
                        working=true;
                        listener.onTaskStarted(taskNumber,temp.getName());
                        temp.getTask().run(taskNumber);
                        listener.onTaskCompleted(taskNumber,temp.getName());
                        taskNumber++;
                        working=false;
                    } catch (InterruptedException e) {
                        break;
                    }
                }
                started=false;
                listener.onWorkerStoped();
            }
        };
        myThread.start();



    }




    public synchronized void enqueueTask(String taskName, Task task) throws InterruptedException {
        Container temp=new Container(task,taskName);
        tasks.put(temp);
        notify();
    }

    public synchronized void stop() {

        if(started==false)
            return;

        System.out.println("Stop function called.");
        myThread.interrupt();
    }

    void setListener(WorkerListener wl) {
        listener = wl;
    }

    boolean isStarted() {
        if (started)
            return true;
        else
            return false;
    }

    boolean isWorking() {
        if (working)
            return true;
        else
            return false;
    }

    public void join() throws InterruptedException {
        if(started)
        myThread.join();
    }



}