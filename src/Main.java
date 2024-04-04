import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class NhanVien {
    private String maNhanVien;
    private String hoTen;
    private int tuoi;
    private double luong;

    public NhanVien(String maNhanVien, String hoTen, int tuoi, double luong) {
        this.maNhanVien = maNhanVien;
        this.hoTen = hoTen;
        this.tuoi = tuoi;
        this.luong = luong;
    }

    // Các phương thức setter và getter
    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    @Override
    public String toString() {
        return "Mã nhân viên: " + maNhanVien + ", Họ và tên: " + hoTen + ", Tuổi: " + tuoi + ", Lương: " + luong;
    }

    public static class TextEditor {
        private List<String> lines;
        private File file;

        public TextEditor() {
            this.lines = new ArrayList<>();
        }

        public void loadFile(String fileName) {
            this.file = new File(fileName);

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
                System.out.println("File loaded successfully.");
            } catch (IOException e) {
                System.out.println("Error loading file: " + e.getMessage());
            }
        }

        public void saveFile(String fileName) {
            this.file = new File(fileName);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
                System.out.println("File saved successfully.");
            } catch (IOException e) {
                System.out.println("Error saving file: " + e.getMessage());
            }
        }

        public void recursiveDirectoryTraversal(File directory) {
            if (directory.isDirectory()) {
                File[] files = directory.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isDirectory()) {
                            recursiveDirectoryTraversal(file);
                        } else {
                            System.out.println(file.getAbsolutePath());
                        }
                    }
                }
            }
        }

        public void addLine(String line) {
            lines.add(line);
        }

        public void removeLine(int index) {
            if (index >= 0 && index < lines.size()) {
                lines.remove(index);
            }
        }

        public void displayLines() {
            for (String line : lines) {
                System.out.println(line);
            }
        }

        public void processLinesWithLambda() {
            lines.forEach(line -> {
                System.out.println("Processing line: " + line);
                // Perform other operations on the line
            });
        }

        public static void main(String[] args) {
            TextEditor editor = new TextEditor();
            editor.loadFile("sample.txt");

            editor.addLine("This is line 1");
            editor.addLine("This is line 2");
            editor.addLine("This is line 3");

            editor.displayLines();

            editor.removeLine(1);

            editor.displayLines();

            editor.saveFile("output.txt");

            File directory = new File("path/to/directory");
            editor.recursiveDirectoryTraversal(directory);

            editor.processLinesWithLambda();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<NhanVien> danhSachNhanVien = new ArrayList<>();

        // Nhập thông tin cho mảng n nhân viên từ bàn phím
        System.out.print("Nhập số lượng nhân viên: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Đọc bỏ dòng thừa

        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin cho nhân viên thứ " + (i + 1) + ":");
            System.out.print("Mã nhân viên: ");
            String maNhanVien = scanner.nextLine();
            System.out.print("Họ và tên: ");
            String hoTen = scanner.nextLine();
            System.out.print("Tuổi: ");
            int tuoi = scanner.nextInt();
            System.out.print("Lương: ");
            double luong = scanner.nextDouble();
            scanner.nextLine(); // Đọc bỏ dòng thừa

            NhanVien nv = new NhanVien(maNhanVien, hoTen, tuoi, luong);
            danhSachNhanVien.add(nv);
        }

        // Ghi thông tin n nhân viên vào file nhanvien.txt
        try (FileWriter writer = new FileWriter("nhanvien.txt")) {
            for (NhanVien nv : danhSachNhanVien) {
                writer.write(nv.toString() + "\n");
            }
            System.out.println("Đã ghi thông tin nhân viên vào file nhanvien.txt.");
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi ghi file: " + e.getMessage());
        }

        // Đọc file nhanvien.txt và in danh sách nhân viên ra màn hình
        System.out.println("Danh sách nhân viên từ file nhanvien.txt:");
        try (BufferedReader reader = new BufferedReader(new FileReader("nhanvien.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi đọc file: " + e.getMessage());
        }
    }
}

