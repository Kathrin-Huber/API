import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Iterator;
import java.util.ServiceLoader;

import org.kitodo.adder.api.Adder;

/**
 * Created by al-huber on 12.01.2017.
 */
public class Main {
    private static ServiceLoader<Adder> loader;


    public static void main(String[] args) throws MalformedURLException {

        File loc = new File("plugins");

        URLClassLoader sysLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        URL urls[] = sysLoader.getURLs(), udir = loc.toURI().toURL();
        String udirs = udir.toString();
        for (int i = 0; i < urls.length; i++)
            if (urls[i].toString().equalsIgnoreCase(udirs)) {
                return;
            }
        Class<URLClassLoader> sysClass = URLClassLoader.class;
        try {
            Method method = sysClass.getDeclaredMethod("addURL", new Class[]{URL.class});
            method.setAccessible(true);
            method.invoke(sysLoader, new Object[] {udir});
        } catch (Throwable t) {
            t.printStackTrace();
        }

        loader = ServiceLoader.load(Adder.class);
        Integer result = null;
        Iterator<Adder> adders = loader.iterator();
        while (result == null && adders.hasNext()) {
            Adder d = adders.next();
            result = d.add(2,4);
        }
        System.out.println(result);

    }
}
