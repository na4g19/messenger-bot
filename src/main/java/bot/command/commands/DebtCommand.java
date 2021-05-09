package bot.command.commands;

import bot.Scheduler;
import bot.command.HelpDocsBuilder;
import bot.database.Database;
import bot.utility.Print;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Command to track who lends what amount
 */
public class DebtCommand extends Command {

    private String lender;
    private String lendee;
    private double amount;

    /**
     * Show everyone's debts
     */
    public DebtCommand() {
        functionMode = 0;
    }

    /**
     * Lender splints amount between all members. (Doesn't add debt to himself)
     * @param lender full name or nickname
     */
    public DebtCommand(String lender, double amount) {
        functionMode = 1;
        this.lender = lender;
        this.amount = amount;
    }

    /**
     * Lender lends amount to lendee
     * @param lender full name or nickname
     * @param lendee first name
     */
    public DebtCommand(String lender, String lendee, double amount) {
        functionMode = 2;
        this.lender = lender;
        this.lendee = lendee;
        this.amount = amount;
    }

    @Override
    public void execute() {

        Database database = new Database("main.db");
        repairNames();

        switch (functionMode) {
            case 0 :
                Scheduler.scheduleSendText(database.getDebtsTable());
                break;
            case 1 :
                splitMoney(lender, amount);
                Scheduler.scheduleSendText(printNewAmounts(lender));
                break;
            case 2 :

                // Can't lend to yourself
                if (!lender.equals(lendee)) {
                    lendMoney(lender, lendee, amount);
                    double amount = database.getAmount(lender, lendee);
                    Scheduler.scheduleSendText(lendee + " owes " + lender + " " + Print.moneyPrecision(amount));
                }
        }
    }

    /**
     * Get full names from given names
     */
    private void repairNames() {
        Database database = new Database("main.db");

        if (lender != null) {
            lender = database.getFullNameFromNick(lender);
        }
        if (lendee != null) {
            lendee = database.getFullName(lendee);
        }
    }

    /**
     * Add debt amount
     */
    private void lendMoney(String lender, String lendee, double amount) {
        Database database = new Database("main.db");
        database.updateDebt(lendee, lender, amount);
    }

    /**
     * Splits amount between everyone else but the lender
     */
    private void splitMoney(String lender, double amount) {

        Database database = new Database("main.db");
        List<String> lendees = database.getNames();
        double lendAmount = amount / lendees.size();

        for (String lendee : lendees) {
            if (!lender.equals(lendee)) {
                database.updateDebt(lendee, lender, lendAmount);
            }
        }
    }

    /**
     * Print new debt amounts after splitting
     */
    private String printNewAmounts(String lender) {

        Database database = new Database("main.db");
        List<String> lendees = database.getNames();

        StringBuilder amounts = new StringBuilder();

        for (String lendee : lendees) {
            if (!lender.equals(lendee)) {

                double amount = database.getAmount(lender, lendee);
                amounts.append(lendee + " owes " + lender + " " + Print.moneyPrecision(amount) + "\n");
            }
        }
        return amounts.toString();
    }

    @Override
    public HelpDocsBuilder constructDocs() {
        return null;
    }
}
