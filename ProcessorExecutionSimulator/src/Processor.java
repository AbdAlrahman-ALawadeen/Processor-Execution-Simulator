public class Processor {
    int id;
    private Task task;
    private QueueTask queue;
    private int remainingExecutionTime;
    public Processor(QueueTask queue, int id) {
        this.queue = queue;
        this.id = id;
        this.task = null;
        this.remainingExecutionTime = 0;
    }
    public boolean isAvailable() {
        return task == null;
    }
    public void setTask(Task task) {
        this.task = task;
        this.remainingExecutionTime = task.getExecutionTime();
    }
    public void simulateProcessor() {
        if (task != null) {
            remainingExecutionTime--;
            if (remainingExecutionTime <= 0) {
                System.out.println("Processor(" + id + ") -- > Task ID :" + task.getId() + " completed!");
                task = null;
            } else {
                System.out.println("Processor(" + id + ") -- > Task ID :" + task.getId());
            }
        } else {
            System.out.println("Processor(" + id + ") -- > Task ID : free!");
        }
    }
}