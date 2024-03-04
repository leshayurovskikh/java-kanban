package Tasks;
import java.util.ArrayList;
public class Epic extends Task{
    private ArrayList<Object> listOfSubtasks = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description);
        this.status = Status.NEW;

    }

    public ArrayList<Object> getListOfSubtasks() {
        return listOfSubtasks;
    }

    public void cleanSubtaskIds() {
        listOfSubtasks.clear();
    }

    @Override
    public String toString() {
        String result = "Эпик: " + name + ", Подробности: " + description + ", Номер задачи: " + id + ", Статус " + status  +" .\n"
                + "Подзадачи: \n" + listOfSubtasks;;

        return result;
    }
}
