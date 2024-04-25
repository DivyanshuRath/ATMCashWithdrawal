import com.atm.ATMWithdrawalProcess;

import java.util.LinkedHashMap;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static int response;

    private static LinkedHashMap withdrawnDenomination = new LinkedHashMap<>();

    public static void main(String[] args) {

        ATMWithdrawalProcess process = new ATMWithdrawalProcess();

        Thread withdrawalThread1 = new Thread(() ->
        {
            withdrawnDenomination = (process.withdrawAmount(1200));
            if (withdrawnDenomination.isEmpty())
                logger.info("Issue with ATM, please try later");
            else{
                logger.info("Please collect the cash");
                logger.info(withdrawnDenomination.toString());
            }
        });
        Thread withdrawalThread2 = new Thread(() -> {
            withdrawnDenomination = process.withdrawAmount(18);
            if (withdrawnDenomination.isEmpty())
                logger.info("Issue with ATM, please try later");
            else{
                logger.info("Please collect the cash");
                logger.info(withdrawnDenomination.toString());
            }
        });

        withdrawalThread1.start();
        withdrawalThread2.start();

    }
}