public class Container {
    Task task;
    String name;

    Container(Task task, String name)
    {
        this.task=task;
        this.name=name;
    }

    public Task getTask() {
        return task;
    }

    public String getName() {
        return name;
    }
}
