public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main thread: Start");
        System.out.println("Main thread: I'm creating new thread.");
        Worker taskThread = new Worker("worker");
        taskThread.setListener(new WorkerListener() {
            @Override
            public void onWorkerStarted() {
                System.out.println("@onWorkerStarted: Worker started.");
            }

            @Override
            public void onWorkerStoped() {
                System.out.println("@onWorkerStoped: Worker stopped.");
            }

            @Override
            public void onTaskStarted(int taskNumber, String taskName) {
                System.out.println("@onTaskStarted: Task started: " + taskNumber +" "+ taskName);
            }

            @Override
            public void onTaskCompleted(int taskNumber, String taskName) {
                System.out.println("@onTaskCompleted: Task completed: " + taskNumber +" "+ taskName);
            }
        });

        taskThread.enqueueTask("10secondCPU100", new MyTask1());
        taskThread.enqueueTask("10secondCPU0", new MyTask2());
        taskThread.enqueueTask("10secondYield", new MyTask3());
        taskThread.enqueueTask("10secondFibonacci", new MyTask4());
        taskThread.enqueueTask("10secondMultiplication", new MyTask5());


        taskThread.start();
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        taskThread.stop();
        taskThread.join();

    }
}
