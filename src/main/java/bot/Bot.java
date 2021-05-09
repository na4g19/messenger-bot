package bot;

import bot.antlr.CommandParser;
import bot.init.StartUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

public class Bot {

    final private WebDriver driver;
    final private Interactor interactor;
    final private Reader reader;
    final private CommandParser commandParser = new CommandParser();

    // private static final String URL = "https://www.facebook.com/messages/t/2889860461051990"; //REAL
    // private static final String URL = "https://www.facebook.com/messages/t/4754882441249080"; //ALT
    private static final String URL = "https://www.facebook.com/messages/t/3754157064643284";    //MAIN

    // FIXME: 02/05/2021 PAKEITUS PETRA I VYTAUTA REIKIA PAKEIST BOT_NAME
    // private static final String EMAIL = "botasvytautas@gmail.com"; EXTRA EMAIL DO NOT DEELYT
    private static final String EMAIL = "botaspetras@gmail.com";
    private static final String PASSWORD = "salokym1";

    // Bot fb profile name
    public static final String BOT_NAME = "Botas Petras";
    private String newestMessage;

    /**
     * Creates a bot that logs in to Facebook
     */
    public Bot() {

        // Disable popup
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);

        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless");
        // options.addArguments("--disable-gpu");//reikia LinuxOptions.addArguments("start-maximized");
        options.addArguments("disable-notifications");
        options.addArguments("allow-running-insecure-content");
        options.setExperimentalOption("prefs", prefs);

        // System.setProperty("webdriver.chrome.driver","/home/mykolas/Bot/FbBotGrupe/chromedriver");

        driver = new ChromeDriver(options);
        interactor = new Interactor(driver);
        reader = new Reader(driver);

        Scheduler.startScheduler(interactor);

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

        System.out.println("Initialisation actions");
        StartUp start = new StartUp(driver);

        System.out.println("Starting reader");
        new Thread(reader).start();
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

        newestMessage = reader.getNewMessage();
        Message message = new Message(newestMessage);

        // FIXME: 29/04/2021 add better incorrect message handling
        if (message.getCommand() == null) {
            System.out.println("Unknown message format");
            return;
        }

        // hidden command
        Scheduler.scheduleProcess(new Process(message));

        // regular command
        Scheduler.scheduleProcess(new Process(commandParser, message));
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