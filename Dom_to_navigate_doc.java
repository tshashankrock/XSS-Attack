import org.jsoup.Jsoup;
import org.jsoup.nodes.Node;
import org.jsoup.safety.Whitelist;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import java.io.*;

class Dom_to_navigate_doc {
	public static void main(String[] args) {
		try {
			//String cleanhtml = Jsoup.clean("http://localhost/Minorproject/wordpress/", Whitelist.relaxed());
			Document doc = Jsoup.connect("http://localhost/Minorproject/wordpress/").get();
			Elements links=doc.getElementsByTag("body");
			for(Element link:links)
			{
				//System.out.println("Link: "+link.attr("href"));
				System.out.println("Text: "+link.text());
				System.out.println("Data content: "+link.data());
				System.out.println("Attributes: "+link.attributes());
				System.out.println("");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
