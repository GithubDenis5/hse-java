import java.util.Random;

public class ElevatorsSimulator {
    private final Elevator elevator1;
    private final Elevator elevator2;

    public ElevatorsSimulator() {
        this.elevator1 = new Elevator(1);
        this.elevator2 = new Elevator(2);
    }

    public void startSimulation() {
        Thread thread1 = new Thread(elevator1);
        Thread thread2 = new Thread(elevator2);

        thread1.start();
        thread2.start();

        Random random = new Random();
        // int reqId = 0;

        while (true) {
            int startFloor = random.nextInt(10);
            int endFloor;
            do {
                endFloor = random.nextInt(10);
            } while (endFloor == startFloor); // проверка на совпадения начального и конечного этажа

            Request request = new Request(startFloor, endFloor/*, reqId*/);


            // Выбор лифта для добавления заявки
            if (elevator1.isFree() && elevator2.isFree()) {
                // Оба лифта свободны, выбираем тот, который ближе
                if (Math.abs(elevator1.getCurrentFloor() - startFloor) <= Math.abs(elevator2.getCurrentFloor() - startFloor)) {
                    elevator1.addPickupRequest(request);
                } else {
                    elevator2.addPickupRequest(request);
                }
            } else if (elevator1.isFree()) {
                // Если лифт 1 свободен
                elevator1.addPickupRequest(request);
            } else if (elevator2.isFree()) {
                // Если лифт 2 свободен
                elevator2.addPickupRequest(request);
            } else {
                // Если оба лифта заняты, выбираем тот, который ближе
                if (Math.abs(elevator1.getCurrentFloor() - startFloor) <= Math.abs(elevator2.getCurrentFloor() - startFloor)) {
                    elevator1.addPickupRequest(request);
                } else {
                    elevator2.addPickupRequest(request);
                }
            }


            System.out.println("Вызов: с " + startFloor + " на " + endFloor/* + "\t\t" + reqId*/);

            // reqId++;

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
