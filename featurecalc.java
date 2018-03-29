import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Stack;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class featurecalc {
	public static String s="",s1,s2,s3,s4;
	public static int bracket,start,end,current,what,rec=0;
	public static void main(String[] args)throws Exception{
		Document doc=Jsoup.connect("http://localhost/1.php").get();
		Elements elem=doc.getElementsByTag("script");
		for(Element e:elem)
		{
			s+=e.data();
		}
		Stack<String> stack=new Stack<String>();
		int l1=s.length();
		s1="";

		current=0;
		for(int i=0;i<l1;i++)
		{
			char c=s.charAt(i);
			if(c!='\''&&c!='"'&&c!='\n')
			{
				s4+=c;
			}
		}
		s=s4;
		int l=s.length();
		for(int i=0;i<l;i++)
		{
			if(s.charAt(i)=='/'&&(i+1)<l&&s.charAt(i+1)=='*')
			{
				while(i<l)
				{
					if(s.charAt(i)=='*'&&(i+1)<l&&s.charAt(i+1)=='/')
						break;
					i++;
				}
				i++;
				i++;
			}
			if(s.charAt(i)!=';')
			{
				if(s.charAt(i)!='\"'&&s.charAt(i)!='\''&&s.charAt(i)!='{'&&s.charAt(i)!='}'&&s.charAt(i)!='\n')
				s1+=s.charAt(i);
			}
			else
			{
				stack.push(s1);
				s1="";
			}
		}
		create(stack);
		System.out.println("Complete");
	}
	public static void create(Stack<String> s)throws Exception
	{
		while(!s.empty())
		{
			String str=s.peek();
			s.pop();
			int l=str.length();
			bracket=0;
			for(int i=0;i<l;i++)
			{
				char c=str.charAt(i);
				if(c=='(')
				{
					bracket++;
				}
			}
			if(bracket==0)
			{

					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jsoup","root","");
					Statement stm=con.createStatement();
					String s31="INSERT INTO func values(\""+str+"\")";

					stm.execute(s31);

				continue;
			}
			if(bracket==1)
			{
				simplespf(str);
				continue;
			}

		}
	}
	public static void simplespf(String str) throws Exception
	{
		Stack<String> st=new Stack<String>();
		int l=str.length();
		s1="";
		for(int i=0;i<l;i++)
		{
			char c=str.charAt(i);
			if(c=='(')
			{
				s2="";
				i++;
				while(i<l&&str.charAt(i)!=')')
				{
					if(str.charAt(i)==',')
					{
						st.push(s2);
						s2="";
						i++;
						continue;
					}
					s2+=str.charAt(i);
					i++;
				}
				st.push(s2);
				//break;
				s1+=" "+st.size();
				while(!st.empty())
				{
					s1+=" ";
					s1+=st.peek();
					st.pop();
				}
				s1+=" ";
			}
			else
			{
				s1+=c;
			}
		}

			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jsoup","root","");
			Statement stm=con.createStatement();
			System.out.println(s1);
			String s31="INSERT INTO func Values('"+s1+"')";

			stm.execute(s31);
	}
}
