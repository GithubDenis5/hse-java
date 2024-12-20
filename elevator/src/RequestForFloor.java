class RequestForFloor {
    private final int id;
    private final int currentFloor;
    private final int toFloor;
    private boolean running;

    public boolean isRunning() {
        return running;
    }

    public int getId() {
        return id;
    }

    public RequestForFloor(int id, int currentFloor, int toFloor, boolean running) {
        this.running = running;
        this.id = id;
        this.currentFloor = currentFloor;
        this.toFloor = toFloor;
    }

    public String getInfo() {
        return " id: "+ id + " Отправление: " + currentFloor + "; Этаж прибытия: " + toFloor;
    }

    public int getToFloor() {
        return toFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }
}