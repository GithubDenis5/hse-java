public class Request implements Comparable<Request> {
    // private final int id; // для дебага
    private final int startFloor;
    private final int endFloor;

    public Request(int startFloor, int endFloor/*, int id*/) {
        this.startFloor = startFloor;
        this.endFloor = endFloor;
        // this.id = id;
    }

    public int getStartFloor() {
        return startFloor;
    }

    public int getEndFloor() {
        return endFloor;
    }

//    public int getId() {
//        return id;
//    }

    @Override
    public int compareTo(Request other) {
        return Integer.compare(this.startFloor, other.startFloor);
    }
}
