package bot.init;

import bot.Bot;
import bot.database.Database;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Used to keep the user names / nicknames up to date in database
 */
public class MemberFetcher {

    private WebDriver driver;

    private final String customizeButton = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]" +
            "/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]";

    private final String changeNamesButton = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/" +
            "div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[6]/div[1]";

    private final String nicknamesDialog = "//div[contains(@aria-label, 'Nicknames')]/div[last()]/div[1]/div[1]/div[1]" +
            "/div[1]/div/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div/span[1]";

    public MemberFetcher(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Gets current user names / nicknames and adds them to databse
     */
    public void locateMembers() {

        WebDriverWait wait = new WebDriverWait(driver, 100);

        System.out.println("Waiting for customize button to be visible");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(customizeButton)));
        clickButton(customizeButton);

        System.out.println("Waiting for change names button to be visible");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(changeNamesButton)));
        clickButton(changeNamesButton);

        System.out.println("Waiting for nicknames dialog to be visible");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(nicknamesDialog)));

        List<WebElement> names = driver.findElements(By.xpath(nicknamesDialog));
        int nameCount = names.size();

        Database database = new Database("main.db");

        System.out.println("Adding users to database");
        for (int i = 0; i < nameCount; i += 2) {

            String nickname = replaceEmoji(names.get(i).getAttribute("innerHTML"));
            String name = replaceEmoji(names.get(i + 1).getAttribute("innerHTML"));

            // Don't add bot to users database
            if (!name.equals(Bot.BOT_NAME)) {
                database.insertIntoNames(name, nickname);
            }
        }
        System.out.println("Finished adding users");
    }

    /**
     * Replaces span elements with corresponding emojis
     */
    private String replaceEmoji(String text) {

        String spanRegex = "(<span).*?(</span>)";

        Pattern pattern = Pattern.compile(spanRegex);
        Matcher matcher = pattern.matcher(text);
        StringBuffer sb = new StringBuffer();

        // Replace all spans
        while (matcher.find()) {
            matcher.appendReplacement(sb, getImageAlt(matcher.group()));
        }
        matcher.appendTail(sb);

        return sb.toString();
    }

    /**
     * Returns the value of the alt attribute of the given span
     * @return alt attribute value if it exists, empty string otherwise
     */
    private String getImageAlt(String span) {

        String srcRegex = "alt=\"(.*?)\"";
        Pattern pattern = Pattern.compile(srcRegex);
        Matcher matcher = pattern.matcher(span);

        return matcher.find() ? matcher.group(1) : "";
    }

    /**
     * Clicks given button
     * @param button xpath of button
     */
    private void clickButton(String button) {
        driver.findElement(By.xpath(button)).click();
    }
}
