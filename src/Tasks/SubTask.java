package Tasks;

public class SubTask extends Task {
    private int idOfEpic;


    public SubTask(String name, String description, Status status, int idOfEpic) {

        super(name,
                description,
                status);

        this.idOfEpic = idOfEpic;
    }

    public int getIdOfEpic() {
        return idOfEpic;
    }



    @Override
    public String toString() {
        String result = "Подзадача: " + name + ", Подробности: " + description + ", Номер основной задачи: " + idOfEpic +" Номер подзадачи: " +  id +  ", Статус " + status  +" .\n";

        return result;
    }
}
