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

    private final By mostRecentFilter = By.xpath("//button[normalize-space()='Most Recent Posts']");
    private final By mostLikedFilter = By.xpath("//button[normalize-space()='Most Liked Posts']");
    private final By mostCommentedFilter = By.xpath("//button[normalize-space()='Most Commented Posts']");

    private final By likeButton = By.cssSelector(".orangehrm-heart-icon");
    private final By checkLikedButton = By.cssSelector(".orangehrm-like-animation");

    private final By commentButton = By.xpath("//div[@class='orangehrm-buzz-newsfeed']//div[1]//div[1]//div[3]//div[1]//button[1]//i[1]");
    private final By commentField = By.xpath("//input[@placeholder='Write your comment...']");
    private final By toastTextAfterComment = By.xpath("//div[@class='oxd-toast-content oxd-toast-content--success']");

    private final By shareIcon = By.xpath("//div[@class='orangehrm-buzz-newsfeed']//div[1]//div[1]//div[3]//div[1]//button[2]");
    private final By shareTextPost = By.xpath("//div[@class='oxd-buzz-post oxd-buzz-post--focus']//textarea[@placeholder=\"What's on your mind?\"]");
    private final By shareButton = By.xpath("//div[@role='dialog']//form[@class='oxd-form']//button[1]");
    private final By toastTextAfterSharing = By.xpath("//p[@class='oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text']");

    private final By postDropdown = By.cssSelector(".oxd-icon.bi-three-dots");
    private final By editPostOption = By.xpath("//p[normalize-space()='Edit Post']");
    private final By editPostTextArea = By.cssSelector(".oxd-buzz-post-input");
    private final By postEdits = By.xpath("//div[@class='oxd-form-actions orangehrm-buzz-post-modal-actions']//button[@type='submit'][normalize-space()='Post']");
    private final By toastEditPostDone = By.xpath("//p[@class='oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text']");

    private final By deletePostOption = By.xpath("//p[normalize-space()='Delete Post']");
    private final By confirmDelete = By.xpath("//button[normalize-space()='Yes, Delete']");
    private final By cancelDelete = By.xpath("//button[normalize-space()='No, Cancel']");
    private final By toastDeletePostDone = By.xpath("//p[@class='oxd-text oxd-text--p oxd-text--toast-title oxd-toast-content-text']");

    private final By likesOfPost = By.xpath("//p[contains(normalize-space(), 'Likes')]");
    private final By likesList = By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-buzz-stats-modal-employee-name'][normalize-space()='Russel Hamilton']");

    private final By sharesOfPost = By.xpath("//p[contains(normalize-space(), 'Shares')]");
    private final By sharesList = By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-buzz-stats-modal-employee-name']");

    private final By commentsOfPost = By.xpath("//p[contains(normalize-space(), 'Comments')]");
    private final By commentsList = By.cssSelector(".orangehrm-buzz-comment");


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

    public void createPostWithVideo(String url) {
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

    public void goToLatestPostOptions(){
        click(postDropdown);
    }

    public void goToDeletePost(){
        click(deletePostOption);
    }
    public void confirmDeletePost(){
        click(confirmDelete);
    }
    public void cancelDeletePost(){
        click(cancelDelete);
    }
    public boolean isPostDeleted(){
        return find(toastDeletePostDone).getText().contains("Success");
    }

    public boolean editPostPossibility(){
        return find(editPostOption).isDisplayed();
    }

    public void goToEditMyPost(){
        click(editPostOption);
    }
    public void editPost(String editedText){
        set(editPostTextArea, editedText);
        click(postEdits);
    }
    public boolean isPostedited(){
        return find(toastEditPostDone).getText().contains("Success");
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
    public boolean isCommentDone() {
        return find(toastTextAfterComment).getText().contains("Success");
    }
    public void shareLatestPost(String sharedText) {
        click(shareIcon);
        //set(shareTextPost, sharedText);
        click(shareButton);
    }
    public boolean isPostShared(){
        return find(toastTextAfterSharing).getText().contains("Success");
    }

    public String getNumberOfLikesOfMostLikedPosts(){
        click(mostLikedFilter);
        return find(likesOfPost).getText();
    }
    public boolean isLikesListDisplayed(){
        click(likesOfPost);
        return find(likesList).isDisplayed();
    }

    public String getNumberOfSharesOfLatestPost(){
        return find(sharesOfPost).getText();
    }
    public boolean isSharesListDisplayed(){
        click(sharesOfPost);
        return find(sharesList).isDisplayed();
    }

    public void getCommentsListOfMostCommentedPosts(){
        click(mostCommentedFilter);
        click(commentsOfPost);
    }
    public boolean isCommentsListDisplayed(){
        return find(commentsList).isDisplayed();
    }
}