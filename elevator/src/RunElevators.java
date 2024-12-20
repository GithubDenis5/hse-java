import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RunElevators {
    private int requestId;
    private final int floors;
    private final List<Elevator> elevators;
    private final ExecutorService requestGenerator;

    public RunElevators(int floors, int elevatorCount) {
        this.requestId = 0;
        this.floors = floors;
        this.elevators = new ArrayList<>();

        for (int i = 0; i < elevatorCount; i++) {
            elevators.add(new Elevator(i, floors));
        }

        this.requestGenerator = Executors.newSingleThreadExecutor();
    }

    public void startElevators() {
        elevators.forEach(Elevator::start);
        requestGenerator.execute(this::genRequests);
    }

    // генерация запросов для лифтов
    private void genRequests() {
        Random random = new Random();
        while (true) {
            try {
                Thread.sleep(random.nextInt(5000)+2000);
                int currentFloor = random.nextInt(floors) + 1;
                int toFloor;
                do {
                    toFloor = random.nextInt(floors) + 1;
                } while (toFloor == currentFloor);

                RequestForFloor request = new RequestForFloor(requestId, currentFloor, toFloor, false);
                requestId += 1;
                System.out.println("Создан запрос: " + request.getInfo());
                addToElevator(request);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void addToElevator(RequestForFloor request) {
        Elevator best = null;
        int minCost = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            int cost = elevator.calculateRequestCost(request);
            if (cost < minCost) {
                minCost = cost;
                best = elevator;
            }
        }

        if (best != null) {
            best.addRequest(request);
        }
    }

}
