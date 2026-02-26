package daw.pi.platinum.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean("steamSyncAchievementsApiCallExecutor")
    public Executor steamSyncAchievementsApiCallExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(8);
        executor.setQueueCapacity(800);
        executor.setThreadNamePrefix("steamSyncAchievementsApiCall-");
        executor.initialize();
        return executor;
    }

    @Bean("steamSyncGamesApiCallExecutor")
    public Executor steamSyncGamesApiCallExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(6);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("steamSyncGamesApiCall-");
        executor.initialize();
        return executor;
    }
}
