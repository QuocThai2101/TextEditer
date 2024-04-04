package BaiTapFile;

import java.io.File;
import java.util.Scanner;

public class FileSize {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhập đường dẫn của tập tin:");
        String filePath = scanner.nextLine();

        File file = new File(filePath);

        if (file.exists() && file.isFile()) {
            long fileSizeInBytes = file.length();
            double fileSizeInKB = (double) fileSizeInBytes / 1024;
            double fileSizeInMB = fileSizeInKB / 1024;

            System.out.println("Kích thước của tập tin:");
            System.out.printf("Bytes: %d%n", fileSizeInBytes);
            System.out.printf("KB: %.2f%n", fileSizeInKB);
            System.out.printf("MB: %.2f%n", fileSizeInMB);
        } else {
            System.out.println("Đường dẫn không hợp lệ hoặc tập tin không tồn tại.");
        }

        scanner.close();
    }
}
