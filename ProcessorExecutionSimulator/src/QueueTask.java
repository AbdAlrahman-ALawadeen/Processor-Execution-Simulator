import java.util.LinkedList;
import java.util.Queue;
public class QueueTask {
    private Queue<Task> queueTask;
    public QueueTask() {
        queueTask = new LinkedList<>();
    }
    public Queue<Task> getQueueTask() {
        return queueTask;
    }
    public void simulateQueue() {
        System.out.println("inQueue : ");
        for (Task task : queueTask) {
            System.out.println("Task ID " + task.getId() + " || CreationTime :  " + task.getCreationTime() + " || ExecutionTime : " + task.getExecutionTime() + " || Priority : " + task.getPriority());
        }
        System.out.println("--------------------");
    }
}