import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Elevator implements Runnable {
    private final int id;
    private int currentFloor;
    private boolean movingUp;
    private final BlockingQueue<Request> pickupRequests;
    private final BlockingQueue<Integer> dropOffRequests;

    public Elevator(int id) {
        this.id = id;
        this.currentFloor = 0;
        this.movingUp = true;
        this.pickupRequests = new PriorityBlockingQueue<>();
        this.dropOffRequests = new PriorityBlockingQueue<>();
    }

    // добавление запроса на доставку
    public synchronized void addPickupRequest(Request request) {
        pickupRequests.offer(request);
    }


    @Override
    public void run() {
        try {
            while (true) {
                // высадка
                if (!dropOffRequests.isEmpty() && dropOffRequests.contains(currentFloor)) {
                    dropOff();
                    continue;
                }

                // подбор попути
                checkForPickups();

                Request request = pickupRequests.poll();
                if (request != null) {
                    pickup(request);
                    continue;
                }

                // движение лифта
                moveElevator();
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    private void pickup(Request request) throws InterruptedException {
        System.out.println("Л" + id + " идет на " + request.getStartFloor()/* + "\t\t" + request.getId()*/);
        moveToFloor(request.getStartFloor());
        System.out.println("Л" + id + " забрал на " + currentFloor);
        dropOffRequests.offer(request.getEndFloor());
    }

    private void dropOff() {
        System.out.println("Л" + id + " высадил на " + currentFloor);
        dropOffRequests.remove(currentFloor);
    }

    private void checkForPickups() {
        for (Request request : pickupRequests) {
            if ((request.getStartFloor() == currentFloor) && ((movingUp && request.getEndFloor() > currentFloor) || (!movingUp && request.getEndFloor() < currentFloor))) {
                pickupRequests.remove(request);
                System.out.println("Л" + id + " подобрал с " + request.getStartFloor() + " на " + request.getEndFloor()/*  + "\t\t" + request.getId()*/);
                dropOffRequests.offer(request.getEndFloor());
            }
        }

    }

    private void moveElevator() {
        if (!dropOffRequests.isEmpty()) {
            int targetFloor = getNextTargetFloor();
            if (currentFloor < targetFloor) {
                moveUp();
            } else if (currentFloor > targetFloor) {
                moveDown();
            }
        } else if (!pickupRequests.isEmpty()) {
            int targetFloor = pickupRequests.peek().getStartFloor();
            if (currentFloor < targetFloor) {
                moveUp();
            } else if (currentFloor > targetFloor) {
                moveDown();
            }
        }
    }


    private int getNextTargetFloor() {
        if (dropOffRequests.isEmpty()) {
            return Integer.MAX_VALUE;
        } else {
            return dropOffRequests.peek();
        }
    }

    private void moveToFloor(int floor) throws InterruptedException {
        while (currentFloor != floor) {
            if (currentFloor < floor) {
                moveUp();
            } else {
                moveDown();
            }
            Thread.sleep(1000);
        }
    }

    private void moveUp() {
        currentFloor++;
        movingUp = true;
        System.out.println("Л" + id + " вверх. Этаж: " + currentFloor);
    }

    private void moveDown() {
        currentFloor--;
        movingUp = false;
        System.out.println("Л" + id + " вниз. Этаж: " + currentFloor);
    }

    public boolean isFree() {
        return pickupRequests.isEmpty() && dropOffRequests.isEmpty();
    }


    public int getCurrentFloor() {
        return currentFloor;
    }
}
