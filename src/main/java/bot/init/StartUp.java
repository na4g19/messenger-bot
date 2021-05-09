package bot.init;

import bot.database.Database;
import org.openqa.selenium.WebDriver;

import java.util.List;

/**
 * Any actions that have to be executed once the bot starts up
 */
public class StartUp {

    private WebDriver driver;

    public StartUp(WebDriver driver) {
        this.driver = driver;
        initialise();
    }

    private void initialise() {
        MemberFetcher memberFetcher = new MemberFetcher(driver);
        memberFetcher.locateMembers();
        updateDebtsTable();
    }

    /**
     * Adds new entries to Debts table
     */
    private void updateDebtsTable() {

        Database database = new Database("main.db");
        List<String> users = database.getNames();

        for (String lender : users) {
            for (String lendee : users) {

                if (!lender.equals(lendee)) {
                    database.insertIntoDebts(lender, lendee, 0);
                }
            }
        }
    }
}
