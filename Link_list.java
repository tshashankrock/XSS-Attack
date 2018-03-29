import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.*;
import java.io.IOException;
import org.jsoup.safety.Whitelist;
/**
 * Example program to list links from a URL.
 */
public class Link_list{
    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner(System.in);
        print("Enter the url to which you want to parse");
        String url =sc.next();
        String cleanhtml = Jsoup.clean(url, Whitelist.relaxed());
        print("Fetching %s...", url);

        Document doc = Jsoup.connect(cleanhtml).get();
        Elements links = doc.select("a[href]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");

        print("\nMedia: (%d)", media.size());
        for (Element src : media) {
            if (src.tagName().equals("img"))
                print(" * %s: <%s> %sx%s (%s)",
                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
                        trim(src.attr("alt"), 20));
            else
                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
        }

        print("\nImports: (%d)", imports.size());
        for (Element link : imports) {
            print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));
        }

        print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
        }
    }
    public static Whitelist relaxed() { 
        return new Whitelist() 
                .addTags( 
                        "a", "b", "blockquote", "br", "caption", "cite", "code", "col", 
                        "colgroup", "dd", "div", "dl", "dt", "em", "h1", "h2", "h3", "h4", "h5", "h6", 
                        "i", "img", "li", "ol", "p", "pre", "q", "small", "strike", "strong", 
                        "sub", "sup", "table", "tbody", "td", "tfoot", "th", "thead", "tr", "u", 
                        "ul") 

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
    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }
    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
}