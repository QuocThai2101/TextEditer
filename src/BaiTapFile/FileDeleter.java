package BaiTapFile;
import java.io.File;
import java.util.Scanner;

public class FileDeleter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhập đường dẫn của thư mục hoặc tập tin:");
        String path = scanner.nextLine();

        File file = new File(path);

        if (file.exists()) {
            deleteFileOrDirectory(file);
            System.out.println("Đã xóa thành công.");
        } else {
            System.out.println("Thư mục hoặc tập tin không tồn tại.");
        }

        scanner.close();
    }

    private static void deleteFileOrDirectory(File file) {
        if (file.isDirectory()) {
            // Nếu đối tượng là thư mục, xóa tất cả các tập tin và thư mục con trước
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    deleteFileOrDirectory(f);
                }
            }
        }
        // Xóa tập tin hoặc thư mục
        file.delete();
    }
}
