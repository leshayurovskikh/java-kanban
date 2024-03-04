package Tasks;
import java.util.Objects;

public class Task {



    protected String name;

    protected String description;

    protected int id;

    protected Status status;

    public Task(String name, String description, Status status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        String result = "Задача: " + name + ", Подробности: " + description + ", Номер задачи: " + id + ", Статус " + status  +" .\n";

        return result;
    }
}





