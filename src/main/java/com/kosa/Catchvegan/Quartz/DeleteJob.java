package com.kosa.Catchvegan.Quartz;

import com.kosa.Catchvegan.Service.ReserveService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class DeleteJob extends QuartzJobBean {

    private static final Logger log = LoggerFactory.getLogger(DeleteJob.class);
    @Autowired
    private ReserveService service;
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("Delete Job Started");
        service.noReserve();
    }
}
