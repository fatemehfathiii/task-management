package ir.fathi.taskmanagement.random_object;

import java.security.SecureRandom;

public class GenerateRandomNumber {

    private static final SecureRandom secureRandom;

    static {
        secureRandom=new SecureRandom();
    }


    public static int generateNumber(){
        return secureRandom.nextInt(1000,10000000);
    }

}
