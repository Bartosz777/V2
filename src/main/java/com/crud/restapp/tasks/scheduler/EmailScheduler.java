package com.crud.restapp.tasks.scheduler;


import com.crud.restapp.tasks.config.AdminConfig;
import com.crud.restapp.tasks.domain.Mail;
import com.crud.restapp.tasks.repository.TaskRepository;
import com.crud.restapp.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {
    private static final String SUBJECT = "Tasks: Once a day email.";
    @Autowired
    private SimpleEmailService emailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        if (taskRepository.count() < 2) {
            emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT, "Currently in database you got: " + taskRepository.count() + " task"));
        } else {
            emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT, "Currently in database you got: " + taskRepository.count() + " tasks"));
        }
    }
}
