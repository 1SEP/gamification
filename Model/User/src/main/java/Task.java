/**
 * Created by ramil on 03.07.2015.
 */
import com.google.common.base.MoreObjects;

public class Task {
    private String text;
    Task(String text) throws Exception {
        if(text.length() < 5)
            throw new Exception("length <5 ");
        this.text = text;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("List text", text)
                .toString();
    }
}
