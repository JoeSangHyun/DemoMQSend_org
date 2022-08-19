package org.example;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {
        DemoMQSend demoMQSend = new DemoMQSend();

        AgvEquipInfo agvEquipInfo = new AgvEquipInfo("2","gv02","pod02","1",
                "12.487293","23.12093u0","0","0");

        demoMQSend.Send(agvEquipInfo);
    }
}
