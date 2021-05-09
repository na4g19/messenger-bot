package bot;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Reader implements  Runnable {

    private WebDriver driver;

    // Read messages not yet processed
    private BlockingQueue<String> readMessages = new LinkedBlockingQueue<>();

    // Message block = multiple messages in a row sent by the same person
    private final String MESSAGE_BLOCKS = "//div[contains(@aria-label,'Žinutės')]/div";

    // Blocks of messages sent by other people (not bot)
    private final String SENT_BLOCKS = "//div[contains(@aria-label,'Žinutės')]/div[contains(@data-testid, 'incoming_group')]";

    // Messages sent by other people (not bot)
    private final String INCOMING_MESSAGES = "//div[contains(@role,'row')]";

    // Used to mark a message block
    private final String IDENTIFIER_NAME = "is-last-message-identifier";

    public Reader(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void run() {
         while (true) {
             readNewMessage();
         }
    }

    /**
     * Waits for a new message to appear to add it
     * to the readMessages queue
     */
    private void readNewMessage() {

        String lastMessages = SENT_BLOCKS + "[last()]" + INCOMING_MESSAGES;
        WebDriverWait wait = new WebDriverWait(driver, 1000 * 1000 * 1000);

        try {
            int blocks = driver.findElements(By.xpath(MESSAGE_BLOCKS)).size();
            int lastBlockSize = driver.findElements(By.xpath(lastMessages)).size();
            WebElement topMostDiv = driver.findElement(By.xpath(MESSAGE_BLOCKS + "[1]"));

            wait.until((ExpectedCondition<Boolean>) webDriver -> {

                /*
                 * Wait until:
                 * 1. A new message block appears
                 * 2. A new message appears in the bottom-most block
                 * 3. Top-most block disappears and bottom-most block is sent by another person (not bot)
                 */
                return webDriver.findElements(By.xpath(MESSAGE_BLOCKS)).size() > blocks
                        || webDriver.findElements(By.xpath(lastMessages)).size() > lastBlockSize
                        || (hasTopDisappeared(topMostDiv) && isBottomReceived(driver));
            });

            WebElement bottomMostDiv = driver.findElement(By.xpath(MESSAGE_BLOCKS + "[last()]"));

            // If this message was sent by another person (ignores notifications)
            if (isBlockReceived(bottomMostDiv)) {

                // FIXME: 30/04/2021 change
                //String newMessage = last.findElement(By.xpath(INCOMING_MESSAGES + "[last()]")).getText();

                // Get last sent message row location
                List<WebElement> msgList = bottomMostDiv.findElements(By.xpath(INCOMING_MESSAGES));
                WebElement messageRow = msgList.get(msgList.size() - 1);

                System.out.println("New message data: \n-----------\n" + messageRow.getText() + "\n-----------\n");
                readMessages.add(messageRow.getText());
            }
        } catch (StaleElementReferenceException exception) {

        }
    }

    /**
     * Check if element is still in DOM
     */
    private boolean hasTopDisappeared(WebElement element) {

        try {
            // Sketchy
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute(arguments[1], 'true')", element, IDENTIFIER_NAME);
            return false;
        } catch (StaleElementReferenceException e) {
            return true;
        }
    }

    /**
     * Checks if the bottom-most block is received form another person
     */
    private boolean isBottomReceived(WebDriver driver) {
        return isBlockReceived(driver.findElement(By.xpath(MESSAGE_BLOCKS + "[last()]")));
    }

    /**
     * Checks if element is a block of messages that was received from another person
     */
    private boolean isBlockReceived(WebElement element) {
        String attribute = element.getAttribute("data-testid");
        return attribute != null && attribute.equals("incoming_group");
    }

    /**
     * Waits for new message to be present
     * @return the new message
     */
    public String getNewMessage() {

        String newMessage = null;
        try {
            newMessage = readMessages.take();
        } catch (InterruptedException exception) {
            System.err.println("Interrupted while waiting for new message");
        }
        return newMessage;
    }
}
