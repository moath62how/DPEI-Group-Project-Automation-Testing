import base.BaseTest;
import com.orangehrm.page.BuzzPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
    String editedText = "Edited Post.";

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
        Assert.assertTrue(buzzPage.isCommentDone());
    }
    @Test
    public void sharePostTest() {
        buzzPage.shareLatestPost(sharedText);
        Assert.assertTrue(buzzPage.isPostShared());
    }

    @Test
    public void showLikedListOfMostLikedPostsTest(){
        String numberOfLikes = buzzPage.getNumberOfLikesOfMostLikedPosts();
        if(!numberOfLikes.equals("0 Likes"))
            Assert.assertTrue(buzzPage.isLikesListDisplayed());
    }
    @Test
    public void showSharesListOfMostLatestPostTest(){
        String numberOfLikes = buzzPage.getNumberOfSharesOfLatestPost();
        if(!numberOfLikes.equals("0 Shares"))
            Assert.assertTrue(buzzPage.isSharesListDisplayed());
    }

    @Test
    public void showCommentsListOfMostCommentedPostsTest(){
        buzzPage.getCommentsListOfMostCommentedPosts();
        Assert.assertTrue(buzzPage.isCommentsListDisplayed());
    }

    @Test
    public void deletePostTest(){
        buzzPage.goToLatestPostOptions();
        buzzPage.goToDeletePost();
        buzzPage.confirmDeletePost();
        Assert.assertTrue(buzzPage.isPostDeleted());
    }

    @Test
    public void editMyPostTest(){
        buzzPage.goToLatestPostOptions();
        if(buzzPage.editPostPossibility()) {
            buzzPage.goToEditMyPost();
            buzzPage.editPost(editedText);
            Assert.assertTrue(buzzPage.isPostedited());
        }
    }
}
