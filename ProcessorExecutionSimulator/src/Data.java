import java.io.*;
public class Data {
    private final int numberProcessors;
    private final int numberClockCycles;
    private int testCases;
    private Task[] task;
    public Data(int numberProcessors, int numberClockCycles, String filePath) {
        this.numberProcessors = numberProcessors;
        this.numberClockCycles = numberClockCycles;
        read(filePath);
    }
    public int getTestCases() {
        return testCases;
    }
    public int getNumberProcessors() {
        return numberProcessors;
    }
    public Task[] getTaskInformation() {
        return task;
    }
    public int getNumberClockCycles() {
        return numberClockCycles;
    }
    private void read(String filePath) {
        try (BufferedReader buffer = new BufferedReader(new FileReader(filePath))) {
            testCases = Integer.parseInt(buffer.readLine().trim());
            task = new Task[testCases];
            for (int i = 0; i < testCases; i++) {
                String line = buffer.readLine().trim();
                String[] numbers = line.split("\\s+");
                int num1 = Integer.parseInt(numbers[0]);
                int num2 = Integer.parseInt(numbers[1]);
                int num3 = Integer.parseInt(numbers[2]);
                task[i] = new Task(num1, num2, num3, i + 1);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        arrangeTasks();
        for (Task task : this.task) {
            System.out.println("Task ID: " + task.getId() + " Creation Time: " + task.getCreationTime() + " Execution Time: " + task.getExecutionTime() + " Priority:" + task.getPriority());
        }
    }

    public void arrangeTasks(){
        for(int i = 0; i < getTestCases(); i++){
            for(int j = 0; j < getTestCases(); j++){
                if(task[i].getCreationTime() == task[j].getCreationTime()){
                    if(task[i].getPriority() > task[j].getPriority()){
                        Task temp = task[i];
                        task[i] = task[j];
                        task[j] = temp;
                    }
                    else if(task[i].getPriority()== task[j].getPriority()){
                        if(task[i].getExecutionTime()> task[j].getExecutionTime()) {
                            Task temp = task[j];
                            task[j] = task[i];
                            task[i] = temp;
                        }
                    }
                }
            }
        }
        int Processor1Time = task[0].getExecutionTime(), Processor2Time = task[1].getExecutionTime();
        int time = Math.min(Processor1Time, Processor2Time);
        for(int index = 2; index < getTestCases(); index++){
            int IndexOfMostPriority = GetMostPriority(index, time);
            if(IndexOfMostPriority != index){
                if(Processor1Time < Processor2Time){
                    Processor1Time += task[IndexOfMostPriority].getExecutionTime();
                }
                else{
                    Processor2Time += task[IndexOfMostPriority].getExecutionTime();
                }
                time = Math.min(Processor1Time, Processor2Time);
                Task temp = task[IndexOfMostPriority];
                task[IndexOfMostPriority] = task[index];
                task[index] = temp;
            }
        }
    }
    private int GetMostPriority(int startIndex, int time) {
        int left = startIndex, right = getTestCases() - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(time >= task[mid].getCreationTime()){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }
        int mostPriority = startIndex;
        for(int index = startIndex + 1; index < left; index++){
            if(task[index].getPriority() > task[startIndex].getPriority() || (task[index].getPriority() == task[startIndex].getPriority() && task[index].getExecutionTime() > task[startIndex].getExecutionTime())){
                mostPriority = index;
            }
        }
        return mostPriority;
    }
}
