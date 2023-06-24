import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class HttpStatusImageDownloader {
    private HttpStatusChecker httpStatusChecker;

    public HttpStatusImageDownloader() {
        httpStatusChecker = new HttpStatusChecker();
    }

    public void downloadStatusImage(int code) throws IOException {
        String imageUrl = httpStatusChecker.getStatusImage(code);

        URL url = new URL(imageUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        try (InputStream inputStream = connection.getInputStream()) {
            String fileName = code + ".jpg";
            Path filePath = Path.of(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Зображення: " + fileName + " збережено");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
