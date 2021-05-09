package bot.database;

import bot.utility.Print;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Connects to databases; provides the functionality to
 * query a database
 */
public class Database {

    // The folder with the databases
    private final String DATABASE_DIR = "database/";

    // The name of the database to connect to
    private final String DATABASE_NAME;

    /**
     * Creates a Database object with the connection established to the given database
     * @param databaseName the name of the database
     */
    public Database(String databaseName) {
        DATABASE_NAME = databaseName;
    }

    /**
     * Establishes a connection with the database;
     * @return the connection
     */
    private Connection getConnection() {

        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_DIR + DATABASE_NAME);
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
        }
        return connection;
    }

    /**
     * Insert user into Names table
     */
    public void insertIntoNames(String name, String nickname) {

        final String INSERT_USER = "INSERT OR REPLACE INTO Names(UniqueAlias, Name, Nickname) VALUES(?,?,?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER)) {

            statement.setString(1, getFirstName(name));
            statement.setString(2, name);
            statement.setString(3, nickname);
            statement.executeUpdate();
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }
    }

    /**
     * Return the first name
     */
    private String getFirstName(String fullName) {
        int index = fullName.indexOf(" ");
        return index == -1 ? fullName : fullName.substring(0, index);
    }

    public String getFullName(String firstName) {

        final String SELECT_FULLNAME = "SELECT Name FROM Names WHERE UniqueAlias = ? COLLATE NOCASE";
        String fullName = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_FULLNAME)) {

            statement.setString(1, firstName);
            try (ResultSet results = statement.executeQuery()) {
                fullName = results.getString("Name");
            } catch (SQLException exception) {
                System.err.println(exception.getMessage());
            }
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }
        return fullName;
    }

    public String getFullNameFromNick(String nickname) {

        final String SELECT_FULLNAME = "SELECT Name FROM Names WHERE Nickname = ?";
        String fullName = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_FULLNAME)) {

            statement.setString(1, nickname);
            try (ResultSet results = statement.executeQuery()) {
                fullName = results.getString("Name");
            } catch (SQLException exception) {
                System.err.println(exception.getMessage());
            }
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }
        return fullName;
    }

    /**
     * Get names from Names table
     */
    public List<String> getNames() {

        final String SELECT_USERS = "SELECT Name FROM Names";
        List<String> users = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USERS)) {

            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    users.add(results.getString("Name"));
                }
            } catch (SQLException exception) {
                System.err.println(exception.getMessage());
            }
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }
        return users;
    }

    /**
     * Get aliases from Names table
     */
    public List<String> getAliases() {

        final String SELECT_USERS = "SELECT UniqueAlias FROM Names";
        List<String> users = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USERS)) {

            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    users.add(results.getString("UniqueAlias"));
                }
            } catch (SQLException exception) {
                System.err.println(exception.getMessage());
            }
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }
        return users;
    }

    /**
     * Inserts new debt entry into Debts table
     */
    public void insertIntoDebts(String lendee, String lender, double amount) {

        final String INSERT_DEBT = "INSERT OR REPLACE INTO Debts(Lendee, Lender, Amount) VALUES(?,?,?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_DEBT)) {

            statement.setString(1, lendee);
            statement.setString(2, lender);
            statement.setDouble(3, amount);
            statement.executeUpdate();
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }
    }

    public void updateDebt(String lendee, String lender, double amount) {

        final String UPDATE_DEBT = "UPDATE Debts SET Amount = " +
                                   "(SELECT Amount FROM Debts WHERE Lendee = ? AND Lender = ?) + ? " +
                                   "WHERE Lendee = ? AND Lender = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_DEBT)) {

            statement.setString(1, lendee);
            statement.setString(2, lender);
            statement.setDouble(3, amount);
            statement.setString(4, lendee);
            statement.setString(5, lender);
            statement.executeUpdate();
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }
    }

    public double getAmount(String lender, String lendee) {

        final String SELECT_AMOUNT = "SELECT Amount FROM Debts WHERE lender = ? AND lendee = ?";
        double amount = 0;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_AMOUNT)) {

            statement.setString(1, lender);
            statement.setString(2, lendee);
            try (ResultSet results = statement.executeQuery()) {
                amount = results.getDouble("Amount");
            } catch (SQLException exception) {
                System.err.println(exception.getMessage());
            }
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }
        return amount;
    }

    /**
     * Returns entire Debts table
     */
    public String getDebtsTable() {

        final String SELECT_TABLE = "SELECT * FROM Debts";
        StringBuilder builder = new StringBuilder();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_TABLE)) {

            try (ResultSet results = statement.executeQuery()) {

                while (results.next()) {
                    String lendee = results.getString("Lendee");
                    String lender = results.getString("Lender");
                    double amount = results.getDouble("Amount");

                    builder.append(lendee + " owes " + lender + " " + Print.moneyPrecision(amount) + "\n");
                }
            } catch (SQLException exception) {
                System.err.println(exception.getMessage());
            }
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }
        return builder.toString();
    }
}