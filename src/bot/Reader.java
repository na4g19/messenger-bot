package bot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class Reader
{

    WebDriver driver;

    /**
     * Reader constructor
     * @param driver
     */
    public Reader(WebDriver driver)
    {
        this.driver = driver;
    }

    /**
     * Waits for new message to appear and reads it
     *
     * @return the new message
     */

    public String returnNewMsgData()
    {
        String divLocation = "//div[contains(@data-testid,'messenger_incoming_text_row')]";

        WebDriverWait wait = new WebDriverWait(driver, 1000 * 1000 * 1000);
        int elementCount = driver.findElements(By.xpath(divLocation)).size();

        // Waits until a new message appears
        System.out.println("Waiting for message");
        wait.until(webDriver -> webDriver.findElements(By.xpath(divLocation)).size() > elementCount);

        List<WebElement> msgList = driver.findElements(By.xpath(divLocation));
        WebElement parent = msgList.get(msgList.size() - 1).findElement(By.xpath("./.."));

        System.out.println("New message data: \n-----------\n" + parent.getText() + "\n-----------\n");

        return parent.getText();
    }
}
