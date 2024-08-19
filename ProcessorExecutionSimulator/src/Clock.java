public class Clock {
    private int clock;
    private final int numberClockCycles;
    public Clock(Data data) {
        clock = 1;
        this.numberClockCycles = data.getNumberClockCycles();
    }
    public int getClock() {
        return clock;
    }
    public void setClock() {
        clock++;
    }
    public void simulateClock() {
        System.out.print("-- cycle -- " + clock);
        System.out.println();
    }
}