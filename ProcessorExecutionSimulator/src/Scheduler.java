public class Scheduler {
    private QueueTask queue;
    private Processor[] processors;

    public Scheduler(QueueTask queue, Processor[] processors) {
        this.queue = queue;
        this.processors = processors;
    }

    public Processor availableProcessor() {
        for (Processor processor : processors) {
            if (processor.isAvailable()) {
                return processor;
            }
        }
        return null;
    }

    public void run() {
        while (!queue.getQueueTask().isEmpty()) {
            Processor availableProcessor = availableProcessor();
            if (availableProcessor == null) {
                break;
            }
            Task nextTask = queue.getQueueTask().poll();
            if (nextTask != null) {
                availableProcessor.setTask(nextTask);
            }
        }
    }
}