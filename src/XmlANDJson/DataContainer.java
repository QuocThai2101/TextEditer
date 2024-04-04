package XmlANDJson;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "data")
public class DataContainer {
    private List<DataItem> items;

    public DataContainer() {
    }

    public DataContainer(List<DataItem> items) {
        this.items = items;
    }

    @XmlElement(name = "item")
    public List<DataItem> getItems() {
        return items;
    }

    public void setItems(List<DataItem> items) {
        this.items = items;
    }
}
