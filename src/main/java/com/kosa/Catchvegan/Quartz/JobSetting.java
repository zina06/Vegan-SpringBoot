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
    }
}
