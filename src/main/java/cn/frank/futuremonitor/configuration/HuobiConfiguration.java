package cn.frank.futuremonitor.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
public class HuobiConfiguration implements SchedulingConfigurer {

    @Value("${task.max-pool-size:24}")
    private int taskMaxPoolSize;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(setTaskSchedulerOrg());
    }

    @Bean(name = "BackEndTask")
    public ThreadPoolTaskScheduler setTaskSchedulerOrg() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(taskMaxPoolSize);
        return scheduler;
    }

}
