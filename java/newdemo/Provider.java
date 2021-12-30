package newdemo;

import javax.xml.ws.Endpoint;
/**
 * Hello world!
 */
public final class Provider {

    private static final String URL = "http://localhost:9000/FileBrowser";

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        
        FileBrowser fBrowser = new FileBrowser();
        System.out.println("Publishing File Browser Service");
        Endpoint.publish(URL, fBrowser);
        System.out.println("File Browser Service Published");
    }
}
