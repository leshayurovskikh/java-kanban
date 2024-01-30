import java.util.ArrayList;
import java.util.HashMap;
    public class TaskManager {
        private int idCount = 0;

        private HashMap<Integer, Task> tasks = new HashMap<>();
        private HashMap<Integer, Epic> epics = new HashMap<>();
        private HashMap<Integer, Subtask> subtasks = new HashMap<>();
        public int addNewTask(Task task) {
            final int id = ++idCount;
            task.setId(id);
            tasks.put(id, task);
            System.out.println("Таск добавлен: " + task);
            return id;
        }

        public ArrayList<Task> getTasks() {
            ArrayList<Task> taskToPrint = new ArrayList<>(tasks.values());
            System.out.println(taskToPrint);
            return taskToPrint;
        }

        public Task getTask(int id) {
            System.out.println(tasks.get(id));
            return tasks.get(id);
        }

        public void updateTask(Task task) {
            final int id = task.getId();
            final Task savedTask = tasks.get(id);
            if (savedTask == null) {
                return;
            }
            tasks.put(id, task);
            System.out.println("Таск № " + id + " изменён.");
        }
        public void deleteTasks() {
            tasks.clear();
            System.out.println("Все таски удалены.");
        }
        public void deleteTaskById(int id) {
            tasks.remove(id);
            System.out.println("Такс № " + id + " удалён.");
        }
        public int addNewEpic(Epic epic) {
            final int id = ++idCount;
            epic.setId(id);
            epics.put(id, epic);
            System.out.println("Эпик добавлен: " + epic);
            return id;
        }
        public ArrayList<Epic> getEpics() {
            ArrayList<Epic> epicToPrint = new ArrayList<>(epics.values());
            System.out.println(epicToPrint);
            return epicToPrint;
        }
        public Epic getEpic(int id) {
            System.out.println(epics.get(id));
            return epics.get(id);
        }
        public void updateEpic(Epic epic) {
            final Epic savedEpic = epics.get(epic.getId());
            savedEpic.setName(epic.getName());
            savedEpic.setDescription(epic.getDescription());
            if (savedEpic == null) {
                return;
            }
            epics.put(savedEpic.getId(), savedEpic);
            System.out.println("Эпик № " + savedEpic.getId() + " изменён.");
        }

        /// Удаление всех эпиков
        public void deleteEpics() {
            epics.clear();
            deleteSubtasks();
            System.out.println("Все Epic удалены.");
        }
        public void deleteEpicById(int id) {
            ArrayList<Integer> subtasksToRemove = epics.get(id).getSubtasksId();
            for (Integer removeSubtask : subtasksToRemove) {
                subtasks.remove(removeSubtask);
            }
            epics.remove(id);
            System.out.println("Эпик № " + id + " и его Subtask удалёны.");
        }
        public void updateEpicStatus(int epicId) {
            Epic epic = epics.get(epicId);
            ArrayList<Integer> subtasksToCheck = epic.getSubtasksId();
            ArrayList<TaskStatus> subtasksStatus = new ArrayList<>();

            for (Subtask subtask : subtasks.values()) {
                for (Integer subtasksId : subtasksToCheck) {
                    if (subtasksId.equals(subtask.getId())) {
                        subtasksStatus.add(subtask.getStatus());
                    }
                }
            }

            if (!(subtasksStatus.isEmpty())) {
                if (!(subtasksStatus.contains(TaskStatus.NEW) &&
                        !(subtasksStatus.contains(TaskStatus.IN_PROGRESS)))) {
                    epics.get(epicId).setStatus(TaskStatus.DONE);
                    System.out.println("Эпик под № " + epicId + " выполнен.");
                } else {
                    System.out.println("Эпик под № " + epicId + " не выполнен");
                }
            }
        }
        public ArrayList<Subtask> getEpicSubtasks(int epicId) {
            ArrayList<Subtask> tasks = new ArrayList<>();
            Epic epic = epics.get(epicId);
            if (epic == null) {
                return null;
            }
            for (int id : epic.getSubtasksId()) {
                tasks.add(subtasks.get(id));
            }
            return tasks;
        }

        /// Добавление сабтаска и привязка к эпику
        public Integer addNewSubtask(Subtask subtask) {
            int epicId = subtask.getEpicId();
            Epic epic = epics.get(epicId);
            if (epic == null) {
                return null;
            }
            int id = ++idCount;
            subtask.setId(id);
            subtasks.put(id, subtask);
            epic.getSubtasksId().add(subtask.getId());
            updateEpicStatus(epicId);
            System.out.println("Новый Subtask добавлен: " + subtask);
            return id;
        }
        public ArrayList<Subtask> getSubtasks() {
            ArrayList<Subtask> subtasksToPrint = new ArrayList<>(subtasks.values());
            System.out.println(subtasksToPrint);
            return subtasksToPrint;
        }
        /// Вывод сабтаска по id
        public Subtask getSubtask(int id) {
            System.out.println(subtasks.get(id));
            return subtasks.get(id);
        }
        /// Апдейт сабтаска
        public void updateSubtask(Subtask subtask) {
            int id = subtask.getId();
            int epicId = subtask.getEpicId();
            Subtask savedSubtask = subtasks.get(id);
            if (savedSubtask == null) {
                return;
            }
            Epic epic = epics.get(epicId);
            if (epic == null) {
                return;
            }
            subtasks.put(id, subtask);
            updateEpicStatus(epicId);
        }
        public void deleteSubtasks() {
            for (Epic epic : epics.values()) {
                epic.getSubtasksId().clear();
                updateEpicStatus(epic.getId());
            }
            subtasks.clear();
            System.out.println("Все Subtask удалены.");
        }
        public void deleteSubtaskById(int id) {
            Subtask subtask = subtasks.remove(id);
            if (subtask == null) {
                return;
            }
            Epic epic = epics.get(subtask.getEpicId());
            epic.getSubtasksId().remove(id);
            updateEpicStatus(epic.getId());
            System.out.println("Сабтаск под № " + id + " удалён.");
        }

    }

