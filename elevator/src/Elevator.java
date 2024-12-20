import java.util.*;
import java.util.concurrent.*;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class Elevator implements Runnable{
    private final int id;
    private final int maxFloors;
    private final Queue<RequestForFloor> requests;
    private final AtomicInteger currentFloor; //потокобезопаснй int
    private boolean movingUp;
    private final Thread elevatorThread;
    private volatile boolean running;

    public Elevator(int id, int maxFloors) {
        this.id = id;
        this.maxFloors = maxFloors;
        this.requests = new ConcurrentLinkedDeque<>();
        this.currentFloor = new AtomicInteger(1);
        this.movingUp = true;
        this.running = true;
        this.elevatorThread = new Thread(this);

    }

    public void start(){
        this.elevatorThread.start();
    }

    public void stop() {
        this.running = false;
        this.elevatorThread.interrupt();
    }

    // добавление запроса с этажами
    public void addRequest(RequestForFloor request) {
        requests.offer(request);
    }

    // вычисление цены перемещения
    public int calculateRequestCost(RequestForFloor request) {
        return Math.abs(currentFloor.get() - request.getCurrentFloor());
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                if (!running)
                    return;
            }

            if (!requests.isEmpty()) {
                List<RequestForFloor> sortedRequests = new ArrayList<>(requests);
                sortedRequests.sort(Comparator.comparingInt(RequestForFloor::getCurrentFloor));

                RequestForFloor request = sortedRequests.get(0);
                int targetFloor = request.getCurrentFloor();

                // движения до этажа с пассажирами
                if (currentFloor.get() < targetFloor) {
                    currentFloor.incrementAndGet();
                    movingUp = true;
                } else if (currentFloor.get() > targetFloor) {
                    currentFloor.decrementAndGet();
                    movingUp = false;
                }
//                } else {
//                    // подбираем пассажира
//                    System.out.println("Лифт #" + id + " забрал пассажира с этажа" + currentFloor);
//                    sortedRequests.remove(request);
//                    requests.remove(request);
//
//                    RequestForFloor newRequest = new RequestForFloor(currentFloor.get(), request.getToFloor());
//                    requests.offer(newRequest);
//                }

                for (RequestForFloor req : sortedRequests) {
                    if (req.getCurrentFloor() == currentFloor.get()) {
                        if (!req.isRunning()) {
                            System.out.println("Лифт #" + id + " подобрал пассажира с этажа" + currentFloor + "    reqId: " + req.getId());
                        }
                        requests.remove(req);
                        requests.offer(new RequestForFloor(req.getId(), currentFloor.get(), req.getToFloor(), true));
                    }
                }

                // высадка пассажиров
                for (RequestForFloor req : new ArrayList<>(requests)) {
                    if (req.getToFloor() == currentFloor.get()) {
                        System.out.println("Лифт #" + id + " высадил пассажира на этаже" + currentFloor  + "    reqId: " + req.getId());
                        requests.remove(req);
                    }
                }
            }
        }
    }
}

