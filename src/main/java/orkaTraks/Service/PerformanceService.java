package orkaTraks.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import orkaTraks.Entity.Task;
import orkaTraks.Repo.Performanceser;

import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PerformanceService {
    @Autowired
    private Performanceser performanceser;
    public List<Task> getWeeklyTasksByEmpId(Integer empId) {
        List<Task> allTasks = performanceser.findByEmpId(empId);

        // Get the current date and calculate the start of the week (Monday)
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = today.with(DayOfWeek.SATURDAY);

        // Filter tasks to include only those within the start and end of the week
        List<Task> weeklyTasks = allTasks.stream()
                .filter(task -> {
                    LocalDate taskDate = task.getDate(); // Assuming Task has a getDate() method
                    return (taskDate.isEqual(startOfWeek) || taskDate.isAfter(startOfWeek)) &&
                            (taskDate.isEqual(endOfWeek) || taskDate.isBefore(endOfWeek));
                })
                .collect(Collectors.toList());

        return weeklyTasks;
    }


    public Map<YearMonth, List<Task>> getAllMonthlyTasksByEmpId(Integer empId) {
        // Fetch all tasks for the given employee ID
        List<Task> allTasks = performanceser.findByEmpId(empId);

        // Group tasks by the YearMonth of their date
        Map<YearMonth, List<Task>> tasksByMonth = allTasks.stream()
                .collect(Collectors.groupingBy(
                        task -> YearMonth.from(task.getDate()) // Assuming Task has a getDate() method
                ));

        return tasksByMonth;
    }

}
