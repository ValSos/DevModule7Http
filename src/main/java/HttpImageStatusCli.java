import java.io.IOException;
import java.util.Scanner;

public class HttpImageStatusCli {
    private HttpStatusImageDownloader downloader;

    public HttpImageStatusCli() {
        downloader = new HttpStatusImageDownloader();
    }
    public void askStatus(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter HTTP status code: ");
        String input = scanner.nextLine();
        try {
            int statusCode = Integer.parseInt(input);
            downloader.downloadStatusImage(statusCode);
        } catch (NumberFormatException | IOException e) {
            System.out.println("Please enter valid number");
        }
    }
}
