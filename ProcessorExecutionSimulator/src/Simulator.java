public class Simulator {
    private QueueTask queue;
    private Scheduler scheduler;
    private Processor[] processors;
    private Clock clock;
    private Data data;
    public Simulator(Data data) {
        this.data = data;
        queue = new QueueTask();
        clock = new Clock(data);
        processors = new Processor[data.getNumberProcessors()];
        for (int i = 0; i < data.getNumberProcessors(); i++) {
            processors[i] = new Processor(queue, i + 1);
        }
        scheduler = new Scheduler(queue, processors);
    }
    public void addTask() {
        for (int i = 0; i < data.getTestCases(); i++) {
            if (!data.getTaskInformation()[i].isDone()) {
                queue.getQueueTask().add(data.getTaskInformation()[i]);
                data.getTaskInformation()[i].Done();
            }
        }
    }
    public void start() {
        boolean[] simulateHelper = new boolean[data.getNumberClockCycles() + 1];
        addTask();
        while (clock.getClock() <= data.getNumberClockCycles()) {
            if (!simulateHelper[clock.getClock()]) {
                clock.simulateClock();
                queue.simulateQueue();
                scheduler.run();
                for (Processor processor : processors) {
                    processor.simulateProcessor();
                }
                simulateHelper[clock.getClock()] = true;
            }
            addTask();
            clock.setClock();
        }
    }
}