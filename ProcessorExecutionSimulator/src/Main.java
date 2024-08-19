class Main {
    public static void main(String[] args) {
        Data data = new Data(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2]);
        Simulator simulator = new Simulator(data);
        simulator.start();
    }
}