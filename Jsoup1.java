import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeVisitor;

public class Jsoup1 {
	static int i = 0, line = 1;
	public static void main(String args[]) throws Exception {
		String url = "http://localhost/MinorProject/wordpress/";
		Document document = Jsoup.connect(url).get();
		document.traverse(new NodeVisitor() {
			public void head(Node node, int depth) {
				if (node.nodeName() != "#text" && node.nodeName() != "#data") {
					int x = Jsoup1.i++;
					for (int j = 0; j < x; j++)
						System.out.print("\t");
					String s = node.attributes().toString();
					System.out.println("Entering tag:\t" + node.nodeName());
				}

			}

			public void tail(Node node, int depth) {
				if (node.nodeName() != "#text" && node.nodeName() != "#data") {
					int x = --Jsoup1.i;
					for (int j = 0; j < x; j++)
						System.out.print("\t");
					System.out.println("Exiting tag:\t " + node.nodeName());
				}

			}
		});

	}
}
