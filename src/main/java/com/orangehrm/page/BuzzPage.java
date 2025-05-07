package com.orangehrm.page;

import com.base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class BuzzPage extends BasePage {
    private final By buzzTab = By.xpath("//span[normalize-space()='Buzz']");
    private final By postTextArea = By.cssSelector(".oxd-buzz-post-input");
    private final By postButton = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--main']");

    private final By sharePhotosButton = By.xpath("//button[normalize-space()='Share Photos']");
    private final By addPhotosButton = By.xpath("//input[@class='oxd-file-input']");
    private final By sharePostWithPhotosButton = By.xpath("//div[@class='oxd-form-actions orangehrm-buzz-post-modal-actions']//button[1]");

    private final By shareVideoButton = By.xpath("//button[normalize-space()='Share Video']");
    private final By videoUrlText = By.xpath("//textarea[@class='oxd-textarea oxd-textarea--active oxd-textarea--resize-vertical']");
    private final By sharePostWithVideoButton = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[3]/button[1]");

    private final By postedContent = By.cssSelector(".oxd-grid-1.orangehrm-buzz-newsfeed-posts");
    private final By postedPhoto = By.cssSelector(".orangehrm-buzz-post-body-picture");
    private final By postedVideo = By.cssSelector(".orangehrm-buzz-video-frame");

    private final By likeButton = By.cssSelector(".orangehrm-heart-icon");
    private final By checkLikedButton = By.cssSelector(".orangehrm-like-animation");

    private final By commentButton = By.xpath("//div[@class='orangehrm-buzz-newsfeed']//div[1]//div[1]//div[3]//div[1]//button[1]//i[1]");
    private final By commentField = By.xpath("//input[@placeholder='Write your comment...']");
    private final By toastText = By.xpath("//div[@class='oxd-toast-content oxd-toast-content--success']");

    private final By shareIcon = By.xpath("//div[@class='orangehrm-buzz-newsfeed']//div[1]//div[1]//div[3]//div[1]//button[2]");
    private final By shareTextPost = By.xpath("//textarea[contains(@class, 'oxd-buzz-post-input')]");
    private final By shareButton = By.xpath("//div[@role='dialog']//form[@class='oxd-form']//button[1]");

    private final By postDropdown = By.cssSelector(".oxd-icon bi-three-dots");
    private final By deletePostOption = By.xpath("//p[normalize-space()='Delete Post']");
    private final By confirmDelete = By.xpath("//button[normalize-space()='Yes, Delete']");
    private final By cancelDelete = By.xpath("//button[normalize-space()='No, Cancel']");

    public void navigateToBuzz() {
        click(buzzTab);
    }

    public void writeTextPost(String text){
        set(postTextArea, text);
    }
    public void postNormalPost(){
        click(postButton);
    }

    public void createPostWithPhoto(String imagePath) {
        click(sharePhotosButton);
        uploadFile(addPhotosButton, imagePath);
        click(sharePostWithPhotosButton);
    }

    public void createPostWithVideo(String url) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        click(shareVideoButton);
        find(videoUrlText).sendKeys(url);
        //click(sharePostWithVideoButton);
        WebElement postBtn = wait.until(ExpectedConditions.elementToBeClickable(sharePostWithVideoButton));
        try {
            postBtn.click();
        } catch (ElementClickInterceptedException e) {
           ((JavascriptExecutor) driver).executeScript("arguments[0].click();", postBtn);
        }
   }

    public String getLatestPostContent() {
        List<WebElement> posts = findElements(postedContent);
        return posts.getFirst().getText();
    }

    public boolean isPostPresent(String text) {
        return getLatestPostContent().contains(text);
    }

    public boolean isImageDisplayedInLatestPost() {
        try {
            return find(postedPhoto).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public boolean isVideoDisplayedInLatestPost() {
        try {
            return find(postedVideo).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public void likeLatestPost() {
        click(likeButton);
    }

    public boolean isLatestPostLiked() {
        return find(checkLikedButton).getAttribute("class").contains("orangehrm-like-animation");
    }

    public void commentOnLatestPost(String comment) {
        click(commentButton);
        set(commentField, comment + Keys.ENTER);
    }
    public boolean isCommentVisible() {
        System.out.println(find(toastText).getText());
        return find(toastText).getText().contains("Success");
    }
    public void shareLatestPost(String sharedText) {
        try {
            click(shareIcon);
            set(shareTextPost, sharedText);
            click(shareButton);
        } catch (Exception e) {
            System.out.println("Failed to share the latest post: " + e.getMessage());
        }
    }
}
