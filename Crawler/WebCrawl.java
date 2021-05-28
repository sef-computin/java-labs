import crawl.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class WebCrawl{
	public static void main(String[] args) throws MalformedURLException, UnknownHostException, IOException {
		if (args.length < 2){
		 System.out.println("not enough args");
		 return;
		}

		String startSite = args[0];
		Crawler crawler = new Crawler();
		crawler.crawl(startSite, Integer.parseInt(args[1]));
		//crawler.showWebsites();
	}
}
