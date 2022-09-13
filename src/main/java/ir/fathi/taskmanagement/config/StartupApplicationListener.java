package ir.fathi.taskmanagement.config;

import ir.fathi.taskmanagement.service.StartupService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private final StartupService startupService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        startupService.rollAndUserAdminProvider();
    }


}
