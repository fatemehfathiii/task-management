package ir.fathi.taskmanagement.config;

import ir.fathi.taskmanagement.exception.RecordNotFoundException;
import ir.fathi.taskmanagement.service.StartupService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    private  final StartupService startupService;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        try{
            startupService.saveAllRole();
        }catch (Exception ignored){
            Logger.getLogger(StartupApplicationListener.class.getName()).info("all the rolls are placed in role table.");
        }

        try {
            startupService.makeUserAdmin();
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
        }
    }

}
