import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeVisitor;
import org.jsoup.nodes.DataNode;
import java.util.*;
import java.io.IOException;
import org.jsoup.safety.Whitelist;
public class traverse{
    static int i = 0, line = 1;
    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the url to which you want to parse");
        String url =sc.next();
        String cleanhtml = Jsoup.clean(url, Whitelist.relaxed());
        System.out.println("Fetching "+url+"....");
        Document doc = Jsoup.connect(cleanhtml).get();
        doc.traverse(new NodeVisitor() {
			public void head(Node node, int depth) {
				if (node.nodeName() != "#text" && node.nodeName() != "#data") {
					int x = traverse.i++;
					for (int j = 0; j < x; j++)
						System.out.print("\t");
					String s = node.attributes().toString();
					System.out.println("Entering tag:\t" + node.nodeName());
				}

			}

			public void tail(Node node, int depth) {
				if (node.nodeName() != "#text" && node.nodeName() != "#data") {
					int x = --traverse.i;
					for (int j = 0; j < x; j++)
						System.out.print("\t");
					System.out.println("Exiting tag:\t " + node.nodeName());
				}

			}
        });
}
    public static Whitelist relaxed() { 
        return new Whitelist() 
                .addTags( 
                        "a", "b", "blockquote", "br", "caption", "cite", "code", "col", 
                        "colgroup", "dd", "div", "dl", "dt", "em", "h1", "h2", "h3", "h4", "h5", "h6", 
                        "i", "img", "li", "ol", "p", "pre", "q", "small", "strike", "strong", 
                        "sub", "sup", "table", "tbody", "td", "th", "tr", "u", 
                        "ul","script") 

                .addAttributes("a", "href", "title") 
                .addAttributes("blockquote", "cite") 
                .addAttributes("col", "span", "width") 
                .addAttributes("colgroup", "span", "width") 
                .addAttributes("img", "align", "alt", "height", "src", "title", "width") 
                .addAttributes("ol", "start", "type") 
                .addAttributes("q", "cite") 
                .addAttributes("table", "summary", "width") 
                .addAttributes("td", "abbr", "axis", "colspan", "rowspan", "width") 
                .addAttributes( 
                        "th", "abbr", "axis", "colspan", "rowspan", "scope", 
                        "width") 
                .addAttributes("ul", "type") 

                .addProtocols("a", "href", "ftp", "http", "https", "mailto") 
                .addProtocols("blockquote", "cite", "http", "https") 
                .addProtocols("img", "src", "http", "https") 
                .addProtocols("q", "cite", "http", "https") 
                ; 
    }
}