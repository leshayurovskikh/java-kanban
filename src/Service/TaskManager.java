package Service;

import Tasks.Epic;
import Tasks.Status;
import Tasks.SubTask;
import Tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;

    public class TaskManager {

        private int idCount = 0;

        private HashMap<Integer, Task> tasks = new HashMap<> ( );

        private HashMap<Integer, Epic> epics = new HashMap<> ( );

        private HashMap<Integer, SubTask> subtasks = new HashMap<> ( );

        public int addNewTask(Task task) {
            final int id = ++idCount;
            task.setId (id);
            tasks.put (id, task);
            return id;
        }
        public int addNewEpic(Epic epic) {
            final int id = ++idCount;
            epic.setId (id);
            epics.put (id, epic);
            return id;
        }
        public int addNewSubTask(SubTask subtask) {
            idCount++;
            subtask.setId(idCount);
            subtasks.put(idCount, subtask);
            epics.get(subtask.getIdOfEpic()).getListOfSubtasks().add(subtask);
            updateEpic(epics.get(subtask.getIdOfEpic()));
            return idCount;

        }
        public void deleteTasks() {
            tasks.clear();
        }
        public void deleteSubtasks() {
            for (Epic epic : epics.values()) {
                epic.cleanSubtaskIds();
                updateEpic(epic);
            }
            subtasks.clear();
        }
        public void deleteEpics() {
            epics.clear();
            subtasks.clear();
        }
        public Task getTask(int id) {
            System.out.println(tasks.get(id));
            return tasks.get(id);
        }
        public SubTask getSubtask(int id) {
            System.out.println(subtasks.get(id));
            return subtasks.get(id);
        }
        public Epic getEpic(int id) {
            System.out.println(epics.get(id));
            return epics.get(id);
        }
        public SubTask getEpicSubtasks(int idOfEpic) {
            SubTask transitSubtask = null;
            for (int a : subtasks.keySet()) {
                if (idOfEpic == subtasks.get(a).getIdOfEpic()) {
                    transitSubtask = subtasks.get(a);
                    return transitSubtask;
                }
            }
            return null;
        }

        public void updateEpic(Epic epic) {
            Status status1 = Status.DONE;
            Status status2 = Status.INPROGRESS;
            Status status3 = Status.NEW;
            int count = epic.getListOfSubtasks().size();
            int countOfDone = 0;
            int countOfInprogress = 0;

            for (int i = 0; i < count; i++) {
                SubTask t = (SubTask) epic.getListOfSubtasks().get(i);
                if (t.getStatus().equals(status1)) {
                    countOfDone++;
                } else if (t.getStatus().equals(status2)) {
                    countOfInprogress++;
                }
            }
            if (count == countOfDone) {
                epic.setStatus("DONE");
            } else if (countOfInprogress > 0 || countOfDone > 0) {
                epic.setStatus("IN_PROGRESS");
            } else {
                epic.setStatus("NEW");
            }
        }
        public ArrayList<Task> getTasks() {
            ArrayList<Task> taskToPrint = new ArrayList<>(tasks.values());
            System.out.println(taskToPrint);
            return taskToPrint;
        }

        public ArrayList<SubTask> getSubtasks() {
            ArrayList<SubTask> subtasksToPrint = new ArrayList<>(subtasks.values());
            System.out.println(subtasksToPrint);
            return subtasksToPrint;
        }

        public ArrayList<Epic> getEpics() {
            ArrayList<Epic> epicToPrint = new ArrayList<>(epics.values());
            System.out.println(epicToPrint);
            return epicToPrint;
        }
        public void deleteTaskById(int id) {
            tasks.remove(id);
        }
        public void deleteSubtaskById(int id) {
            SubTask subtask = subtasks.remove(id);
            if (subtask == null) {
                return;
            }
            Epic epic = epics.get(subtask.getIdOfEpic());
            epic.getListOfSubtasks().remove(id);
            updateEpic(epic);

        }
        public void deleteEpicById(int id) {
            epics.remove(id);

            for (SubTask subTask : subtasks.values()) {
                if (subTask.getIdOfEpic() == id) {
                    subtasks.remove(subTask.getId());
                }
            }
        }

        }




