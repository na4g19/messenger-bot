package bot;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;

import java.lang.Thread;
import java.lang.*;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import bot.commands.Command;

public class Bot {

    final private WebDriver driver;
    final private Interactor interactor;
    final private Reader reader;
    final private Parser parser;
    final private Interpreter interpreter;

    private static final String URL = "https://www.facebook.com/messages/t/example"; // MAIN
    //private static final String URL = "https://www.facebook.com/messages/t/example"; // TEST
    private static final String EMAIL = "example@gmail.com";
    //private static final String EMAIL = "example@gmail.com";
    private static final String PASSWORD = "example";

    private String newestMessage;

    /**
     * Creates a bot that logs in to Facebook
     */
    public Bot() {

        // Disable popup
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        interactor = new Interactor(driver);
        reader = new Reader(driver);
        parser = new Parser();
        interpreter = new Interpreter();

        WebDriverWait wait = new WebDriverWait(driver, 25);
        login();

        System.out.println("Waiting for messages to appear");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Žinutės']")));

        try {
            System.out.println("Waiting 3 seconds for messages to blink");
            Thread.sleep(3000);
        } catch (InterruptedException exception) {
            System.err.println("Thread interrupted during construction");
        }
        System.out.println("Done waiting");
    }

    public static void main(String[] args) {

        Bot bot = new Bot();

        /*
        Properties commandFile = new Properties();
        InputStream a = Bot.class.getClassLoader().getResourceAsStream("command.properties");
        */

        while (true) {
            bot.executeCommand();
        }
    }

    /**
     * Checks if the new message is a command, if so, attempts to execute it
     */
    private void executeCommand() {

        newestMessage = reader.returnNewMsgData();
        Message message = new Message(newestMessage);

        if (newestMessage.charAt(newestMessage.indexOf("\n") + 1) == '!') {

            try {
                parser.parse(message.getCommand());
                interpreter.interpret(parser.getTokenizer().getTokens(), message);

                Command command = interpreter.getOutputCommand();
                command.execute(interactor);

            } catch (Exception exception) {
                exception.printStackTrace();
                interactor.sendText(exception.getMessage());
            }
        }
    }

    /**
     * Logs in to the bot account
     */
    private void login() {

        driver.get(URL);
        driver.findElement(By.id("email")).sendKeys(EMAIL);
        driver.findElement(By.id("pass")).sendKeys(PASSWORD);


        // If button exists
        try {
            // Cookie confirm
            System.out.println("Waiting for accept all cookies button to appear");
            new WebDriverWait(driver, 25).until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//button[normalize-space()='Accept All']")));
            System.out.println("Clicking accept all button");
            driver.findElement(By.xpath("//button[normalize-space()='Accept All']")).click();

        } catch (Exception exception) {
            System.err.println("Can't locate accept all button");
        }

        try {
            // Wait and click login button
            System.out.println("Waiting for Login Button to appear");
            new WebDriverWait(driver, 25).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Log In']")));
            System.out.println("Clicking login");
            driver.findElement(By.xpath("//input[@value='Log In']")).click();

        } catch (Exception exception) {
            System.err.println("Can't locate login button");
        }
    }
}