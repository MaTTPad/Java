public interface WorkerListener {
    void onWorkerStarted();
    void onWorkerStoped();
    void onTaskStarted( int taskNumber, String taskName );
    void onTaskCompleted( int taskNumber, String taskName );
}
