package XmlANDJson;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class    WebCrawler {

    public static void main(String[] args) {
        String url = "https://www.vietjack.com/toan-10-kn/"; // Thay đổi url này thành trang web bạn muốn lấy dữ liệu

        try {
            // Lấy HTML từ trang web
            Document doc = Jsoup.connect(url).get();

            // Tạo một đối tượng JSON để lưu dữ liệu
            JSONObject jsonData = new JSONObject();
            JSONArray jsonArray = new JSONArray();

            // Tạo một đối tượng Element để lưu dữ liệu XML
            Element rootElement = new Element("data");

            // Lấy các phần tử HTML cần thiết từ trang web
            Elements elements = doc.select("your-selector"); // Thay "your-selector" bằng selector của bạn

            for (Element element : elements) {
                // Lấy dữ liệu từ các phần tử HTML và thêm vào JSONObject và Element
                JSONObject item = new JSONObject();
                item.put("data1", element.select("selector1").text()); // Thay "selector1" bằng selector của bạn
                item.put("data2", element.select("selector2").text()); // Thay "selector2" bằng selector của bạn
                jsonArray.put(item);

                Element dataElement = new Element("item");
                dataElement.appendChild(new Element("data1").text(element.select("selector1").text()));
                dataElement.appendChild(new Element("data2").text(element.select("selector2").text()));
                rootElement.appendChild(dataElement);
            }

            // Lưu dữ liệu vào tệp JSON
            jsonData.put("data", jsonArray);
            FileWriter jsonWriter = new FileWriter("data.json");
            jsonWriter.write(jsonData.toString());
            jsonWriter.close();

            // Lưu dữ liệu vào tệp XML
            org.jdom2.Document xmlDocument = new org.jdom2.Document(rootElement);
            org.jdom2.output.XMLOutputter xmlOutputter = new org.jdom2.output.XMLOutputter();
            xmlOutputter.output(xmlDocument, new FileWriter("data.xml"));

            // In thông báo khi hoàn thành
            System.out.println("Dữ liệu đã được lưu vào tệp JSON và XML thành công!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
