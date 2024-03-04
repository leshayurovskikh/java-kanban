import Service.TaskManager;
import Tasks.Status;
import Tasks.Task;

public class Main {

     public static void main(String[] args) {
         Task task1 = new Task ("Построить дом", "Разработать схему дома", Status.NEW);
         Task task2 = new Task ("Купить квартиру", "Начать копить деньги", Status.NEW);

    }
}
