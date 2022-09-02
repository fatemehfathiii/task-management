package ir.fathi.taskmanagement.random_object;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class GenerateRandomObject {
    private static final SecureRandom secureRandom;

    static {
        secureRandom=new SecureRandom();
    }

    public int generateNumber(){
        return secureRandom.nextInt(1000,10000000);
    }
}
