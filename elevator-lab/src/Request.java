public class Request implements Comparable<Request> {
    private final int startFloor;
    private final int endFloor;

    public Request(int startFloor, int endFloor) {
        this.startFloor = startFloor;
        this.endFloor = endFloor;
    }

    public int getStartFloor() {
        return startFloor;
    }

    public int getEndFloor() {
        return endFloor;
    }

    @Override
    public int compareTo(Request other) {
        return Integer.compare(this.startFloor, other.startFloor);
    }
}
