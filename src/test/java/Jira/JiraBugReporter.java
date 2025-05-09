package Jira;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.Base64;

public class JiraBugReporter {

    private static final String JIRA_URL = "https://yassmenhussin105.atlassian.net";
    private static final String JIRA_API = "/rest/api/3/issue";
    private static final String EMAIL = "yassmenhussin105@gmail.com";
    private static final String API_TOKEN = "ATATT3xFfGF0UtqbvkzNiHzcEj2wKs8JcBioF5vfOxfnkYytdbSM2r-FeSwZClpbAZAsvFLxYnlcSfhD8zbOXyWCNSW7zFQ0K1WJcVwUohh970dhmmuUCVij5awDgBbc4q_ATI4s37bu3uLn1JsY7zNyeAi6XLvlVBWfJJl0fzd6gT1wRtnjWFI=C122D234";
    private static final String PROJECT_KEY = "FP";

    public static void createIssue(String summary, String description) {
        try {
            String auth = EMAIL + ":" + API_TOKEN;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

            JSONObject payload = new JSONObject();
            JSONObject fields = new JSONObject();
            fields.put("summary", summary);
            fields.put("issuetype", new JSONObject().put("name", "Bug"));
            fields.put("project", new JSONObject().put("key", PROJECT_KEY));

            JSONObject descriptionADF = new JSONObject();
            descriptionADF.put("type", "doc");
            descriptionADF.put("version", 1);

            JSONArray content = new JSONArray();
            JSONObject paragraph = new JSONObject();
            paragraph.put("type", "paragraph");

            JSONArray paragraphContent = new JSONArray();
            JSONObject text = new JSONObject();
            text.put("type", "text");
            text.put("text", description);

            paragraphContent.put(text);
            paragraph.put("content", paragraphContent);
            content.put(paragraph);

            descriptionADF.put("content", content);
            fields.put("description", descriptionADF);

            payload.put("fields", fields);

            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost(JIRA_URL + JIRA_API);
            post.setHeader("Authorization", "Basic " + encodedAuth);
            post.setHeader("Content-Type", "application/json");
            post.setEntity(new StringEntity(payload.toString()));

            HttpResponse response = client.execute(post);
            String result = EntityUtils.toString(response.getEntity());
            System.out.println("Jira Response: " + result);
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}