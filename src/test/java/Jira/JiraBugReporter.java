package Jira;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpResponse;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.Base64;

public class JiraBugReporter {

    private static final String JIRA_URL = "https://yassmenhussin105.atlassian.net";
    private static final String JIRA_API = "/rest/api/3/issue";
    private static final String PROJECT_KEY = "FP";

    private static final String EMAIL = System.getenv("JIRA_EMAIL");
    private static final String API_TOKEN = System.getenv("JIRA_API_TOKEN");

    public static void createIssue(String summary, String description) {
        if (EMAIL == null || API_TOKEN == null) {
            System.err.println("Error: JIRA_EMAIL or JIRA_API_TOKEN environment variable not set.");
            return;
        }

        try {
            String auth = EMAIL + ":" + API_TOKEN;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

            JSONObject payload = new JSONObject();
            JSONObject fields = new JSONObject();
            fields.put("summary", summary);
            fields.put("issuetype", new JSONObject().put("name", "Bug"));
            fields.put("project", new JSONObject().put("key", PROJECT_KEY));

            // Build ADF (Atlassian Document Format) description
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

            // HTTP POST request to Jira API
            try (CloseableHttpClient client = HttpClients.createDefault()) {
                HttpPost post = new HttpPost(JIRA_URL + JIRA_API);
                post.setHeader("Authorization", "Basic " + encodedAuth);
                post.setHeader("Content-Type", "application/json");
                post.setEntity(new StringEntity(payload.toString()));

                HttpResponse response = client.execute(post);
                String result = EntityUtils.toString(response.getEntity());
                System.out.println("Jira Response: " + result);
            }
        } catch (Exception e) {
            System.err.println("Failed to create Jira issue: " + e.getMessage());
            e.printStackTrace();
        }
    }
}