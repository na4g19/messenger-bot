package bot;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Writes messages into chat
 */
public class Interactor {

    WebDriver driver;

    public Interactor(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Composes the given text into a message and sends it
     * @param text the text to be sent
     */
    public void sendText(String text) {
        driver.findElement(By.xpath("//div[@role='textbox']")).sendKeys(text + Keys.ENTER);
    }

    /**
     * Attempts to send a file from data folder.
     * VERY BETA MANY FILE TYPES AND SIZES may not work
     * @param fileName
     */
    public void sendMedia(String fileName) {

        Path path = Paths.get(String.format("data\\%s", fileName));
        WebElement input = null;

        try {
            input = driver.findElement(By.xpath("//div[@data-visualcompletion='ignore']//input[1]"));
        } catch (NoSuchElementException e) {
            System.err.println("Input element no found");
        }

        try {
            input.sendKeys(path.toAbsolutePath().toString());
            driver.findElement(By.xpath("//div[@aria-label='Norėdami išsiųsti, spauskite „Enter“']")).click();
        } catch (InvalidArgumentException e) {
            System.err.println("File not found");
        }

    }

    /**
     * Attempts to change group photo
     * @param fileName
     */
    public void changeGroupPhoto(String fileName) {

        Path path = Paths.get(String.format("data\\%s", fileName));

        try {
            WebElement input = driver.findElement(By.xpath("//input[@accept='.jpg,.png,.jpeg,.bmp,.tif,.tiff']"));
            input.sendKeys(path.toAbsolutePath().toString());
        }

        // If button does not exist tries to click on dropdown button to revel it and click it
        catch (NoSuchElementException e) {

            WebElement moreFunctionDropDown = driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[1]/div[1]" +
                    "/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]" +
                    "/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/i[1]"));

            moreFunctionDropDown.click();

            WebElement input = driver.findElement(By.xpath("//input[@accept='.jpg,.png,.jpeg,.bmp,.tif,.tiff']"));

            try {
                input.sendKeys(path.toAbsolutePath().toString());
            } catch (InvalidArgumentException z) {
                System.err.println("File not found");
            }
        }

    }

    /**
     * Sends group emoji
     */
    public void sendGroupEmoji() {
        driver.findElement(By.xpath("//div[@role='main']//span[2]")).click();
    }

    /**
     * Changes group name
     * @param name new name
     */
    public void changeGroupName(String name) {

        //Tries to click on change chat name button
        try {
            WebElement changeChatName = driver.findElement(By.xpath("//span[contains(text(),'Change Chat Name')]"));
            changeChatName.click();
        }
        //If button does not exist tries to click on dropdown button to reveal it and click it
        catch (NoSuchElementException e) {
            WebElement moreFunctionDropDown = driver.findElement(By.xpath(
                    "//body[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]" +
                            "/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div" +
                            "[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/i[1]"));

            moreFunctionDropDown.click();
            WebElement changeNameElement = driver.findElement(By.xpath("//span[contains(text(),'Change Chat Name')]"));
            changeNameElement.click();
        }

        WebDriverWait wait = new WebDriverWait(driver, 25);

        //Wait for chat name textbox to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Change Chat Name']//div//div//div//label")));
        WebElement textBox = driver.findElement(By.xpath("//div[@aria-label='Change Chat Name']//div//div//div//label"));

        textBox.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        textBox.sendKeys(name);

        String element = "//body/div/div/div[@data-pagelet='root']/div/div/div/div/div/div[@data-pagelet='root']" +
                "/div/div/div/div[@aria-label='Change Chat Name']/div/div/div/div/div/div[@aria-label=" +
                "'Išsaugoti']/div/div/span[@dir='auto']/span[1]";

        //Wait for save button to become active
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
        driver.findElement(By.xpath(element)).click();
    }
}
