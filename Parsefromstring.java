//this code sanitize the code of word press by connect fuction 
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.*;
class Parsefromstring {

	public static void main(String[] args) {
		String html ="<html><body>"
				+"<video width=\"320\" height=\"240\" controls><source onerror=\"alert('XSS Attack Xd')\"><source src=\"movie.mp4\" type=\"video/mp4\">Your browser does not support the video tag.</video>-"
                +"</body></html>";			
		try
		{
		String cleanhtml = Jsoup.clean(html, Whitelist.relaxed());
		Document doc = Jsoup.connect(cleanhtml).get();
		Element body = doc.body();
		System.out.println(body);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	 public static Whitelist relaxed() { 
	        return new Whitelist() 
	                .addTags( 
	                        "a", "b", "blockquote", "br", "caption", "cite", "code", "col", 
	                        "colgroup", "dd", "div", "dl", "dt", "em", "h1", "h2", "h3", "h4", "h5", "h6", 
	                        "i", "img", "li", "ol", "p", "pre", "q", "small", "strike", "strong", 
	                        "sub", "sup", "table", "tbody", "td", "th", "tr", "u", 
	                        "ul","script","video") 

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


