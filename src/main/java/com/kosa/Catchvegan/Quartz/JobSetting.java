package com.kosa.Catchvegan.Quartz;

import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.quartz.JobBuilder;
import javax.annotation.PostConstruct;

@Configuration
public class JobSetting {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private ReserveJob reserveJob;

    @Autowired
    private DeleteJob deleteJob;


    @PostConstruct
    public void start() throws SchedulerException {

        JobDetail jobDetail = JobBuilder.newJob(reserveJob.getClass())
                .withIdentity("reserveJob", "group1")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("reserveTrigger", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 6 * * ?"))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);

        JobDetail jobDetail1 = JobBuilder.newJob(deleteJob.getClass())
                .withIdentity("deleteJob", "group2")
                .build();
        Trigger trigger1 = TriggerBuilder.newTrigger()
                .withIdentity("deleteTrigger", "group2")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 * * ? * *"))
                .build();
        scheduler.scheduleJob(jobDetail1,trigger1);
    }
}
