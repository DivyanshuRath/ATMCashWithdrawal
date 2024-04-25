import com.atm.ATMWithdrawalProcess;

import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static int response;

    public static void main(String[] args) {

        ATMWithdrawalProcess process = new ATMWithdrawalProcess();

        Thread withdrawalThread1 = new Thread(() ->
        {
            response = (process.withdrawAmount(1200));
            if (response == -1)
                logger.info("Issue with ATM, please try later");
            else
                logger.info("Please collect the cash");
        });
        Thread withdrawalThread2 = new Thread(() -> {
            response = process.withdrawAmount(8);
            if (response == -1)
                logger.info("Issue with ATM, please try later");
            else
                logger.info("Please collect the cash");
        });

        withdrawalThread1.start();
        withdrawalThread2.start();

    }
}