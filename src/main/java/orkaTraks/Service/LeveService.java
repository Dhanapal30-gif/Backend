package orkaTraks.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import orkaTraks.Entity.EmpLeave;
import orkaTraks.Entity.LeaveEvent;
import orkaTraks.Repo.LeaveEventRepo;
import orkaTraks.Repo.LeaveRepo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class LeveService {

    @Autowired
    LeaveRepo leaveRepo;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private LeaveEventRepo leaveEventRepo;
    public List<EmpLeave> saveLeaveData(List<EmpLeave> leave) {
        return leaveRepo.saveAll(leave); // Use the repository to save the task
        //return taskRepo.saveAll(tasks);
    }

//    public void sendEmail(String toEmail,
//                          String subject,
//                          String body){
//        SimpleMailMessage message=new SimpleMailMessage();
//        message.setFrom("dhanapal.orka@gmail.com");
//        message.setTo(toEmail);
//        message.setText(body);
//        message.setSubject(subject);
//
//        mailSender.send(message);
//        System.out.println("Email send uerhufhy ");
//
//    }


    @Async("taskExecutor")  // Use custom executor
    public void sendMail(String toEmail, List<EmpLeave> leaveDetails) throws MessagingException {
        // Create the email subject
        String subject = "Leave Notification";

        StringBuilder message = new StringBuilder("Leave Details:\n");
        for (EmpLeave leave : leaveDetails) {
            message.append("Leave Type: ").append(leave.getLeaveType()).append("\n");
            message.append("Start Date: ").append(leave.getStartDate()).append("\n");
            message.append("End Date: ").append(leave.getEndDate()).append("\n");
            message.append("Reason: ").append(leave.getReason()).append("\n");
            message.append("First Half Day: ").append(leave.getFHDay()).append("\n\n");
            message.append("userId: ").append(leave.getEmpId()).append("\n\n");
            message.append("EmpName: ").append(leave.getEmpName()).append("\n\n");
        }

        sendHtmlEmail(toEmail, subject, message.toString());
    }

    private void sendHtmlEmail(String toEmail, String subject, String htmlContent) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom("orkatechnologies1@gmail.com");
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);  // 'true' indicates it's an HTML email

        mailSender.send(mimeMessage);
        System.out.println("Email sent successfully!");
    }

    public List<EmpLeave> getLeaveDetails() {
        return leaveRepo.findAll();
    }
    public List<EmpLeave> updateLeaveStatus(List<EmpLeave> updateLeave) {
        if (updateLeave.isEmpty()) {
            return Collections.emptyList();
        }

        // Get the leaveId and status from the first item in the list
        Integer leaveIdToUpdate = updateLeave.get(0).getLeaveId();
        String statusToUpdate = updateLeave.get(0).getStatus();

        // Retrieve the existing leave from the repository
        Optional<EmpLeave> existingLeaveOpt = leaveRepo.findById(leaveIdToUpdate);

        // If the leave exists, update the status and save it
        if (existingLeaveOpt.isPresent()) {
            EmpLeave existingLeave = existingLeaveOpt.get();
            existingLeave.setStatus(statusToUpdate); // Update the status
            leaveRepo.save(existingLeave); // Save the updated leave
            return List.of(existingLeave); // Return the updated leave
        } else {
            return Collections.emptyList(); // Return an empty list if the leave does not exist
        }
    }


    public List<EmpLeave> getLeave(Integer empId) {
        return leaveRepo.findByEmpId(empId);

    }

    public List<LeaveEvent> holiday() {
        return leaveEventRepo.findAll();
    }
}

