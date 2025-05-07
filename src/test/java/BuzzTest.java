import base.BaseTest;
import com.orangehrm.page.BuzzPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class BuzzTest extends BaseTest {
    private BuzzPage buzzPage;

    @BeforeMethod
    public void goToBuzz(){
        loginPage.login("Admin", "admin123");
        buzzPage = new BuzzPage();
        buzzPage.navigateToBuzz();
    }

    //To post tests
    String textPost = "Today, I am happy!";
    String imagePath = System.getProperty("user.dir") + "/src/test/DataTest/image.jpg";
    String videoUrl = "https://youtu.be/J7I6C2tYNfo?si=F5a5RlShytkeg0HS";
    String comment = "Nice post!";
    String sharedText = "Reshare.";


    @Test
    public void createNormalPostTest(){
        buzzPage.writeTextPost(textPost);
        buzzPage.postNormalPost();
        Assert.assertTrue(buzzPage.isPostPresent(textPost));
    }
    @Test
    public void createPostWithImageTest(){
        buzzPage.writeTextPost(textPost);
        buzzPage.createPostWithPhoto(imagePath);
        Assert.assertTrue(buzzPage.isPostPresent(textPost));
        Assert.assertTrue(buzzPage.isImageDisplayedInLatestPost());
    }
    @Test
    public void createPostWithVideoTest() throws InterruptedException {
        buzzPage.writeTextPost(textPost);
        buzzPage.createPostWithVideo(videoUrl);
        Assert.assertTrue(buzzPage.isPostPresent(textPost));
        Assert.assertTrue(buzzPage.isVideoDisplayedInLatestPost());
    }

    @Test
    public void likePostTest(){
        buzzPage.likeLatestPost();
        Assert.assertTrue(buzzPage.isLatestPostLiked());
    }
    @Test
    public void commentPostTest() {
        buzzPage.commentOnLatestPost(comment);
        Assert.assertTrue(buzzPage.isCommentVisible());
    }
    @Test
    public void sharePostTest() {
        buzzPage.shareLatestPost(sharedText);
        Assert.assertTrue(buzzPage.isPostPresent(sharedText));
    }

}
