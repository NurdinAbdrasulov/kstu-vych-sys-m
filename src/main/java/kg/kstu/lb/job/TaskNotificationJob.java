//package kg.kstu.lb.job;
//
//import lombok.AllArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import static lombok.AccessLevel.PRIVATE;
//
//@Slf4j
//@Component
//@AllArgsConstructor
//@FieldDefaults(level = PRIVATE, makeFinal = true)
//public class TaskNotificationJob {
//
////    @Scheduled(fixedRate = 1000L)// Каждый будний день в 10:00.
//    public void run() {
//        taskService.sendCodeReviewTasksToChannel();
//    }
//
////    @Scheduled(fixedRate = 1000L)/// Каждый будний день в 9:30.
//    public void runSendingTestingTask() {
//        taskService.sendTestingTasksToChannel();
//    }
//
////    @Scheduled(cron = "0 0 11 * * MON-FRI", zone = "GMT+6")// Каждый будний день в 11:00.
//    public void runStatusReminder() {
//        taskService.remindStatus();
//    }
//
////    @Scheduled(cron = "0 30 10 26-31 * MON-FRI", zone = "GMT+6")// Каждый месяц в будние дни с 26-31 в 10:30
//    public void runMonthlyReportReminder() {
//        taskService.remindToCompleteMonthReport();
//    }
//
////    @Scheduled(cron = "0 0 15 26-31 * MON-FRI", zone = "GMT+6")// Каждый месяц в будние дни с 26-31 в 15:00
//    public void runDonateReminder() {
//        taskService.remindToDonateOnePercentOfSalary();
//    }
//
//}
