org
import org.kitodo.adder.api.Adder;

/**
 * Created by al-huber on 12.01.2017.
 */
public class AdderImpl implements Adder{

    public int add(int a, int b) {
        System.out.println("adding " + a + " and " + b);
        return a+b;
    }
}
