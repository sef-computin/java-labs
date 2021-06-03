
import java.io.*;
import java.net.*;
import java.util.*;
import crawl.*;

public class WebCrawl{
	public static void main(String args[]) throws MalformedURLException, UnknownHostException, IOException, InterruptedException{
		if (args.length < 2){
		 System.out.println("not enough args");
		 return;
		}
		URLDepthPair starturl = new URLDepthPair(args[0], 0);
		URLPool pool = new URLPool(starturl);
		//int count = Integer.parseInt(args[2]);
		//for (int i = 0; i<count;i++){
		//	if (i==0) new Thread(new CrawlerTask(pool,Integer.parseInt(args[1]))).start();
		//	else{ Thread t = new Thread(new CrawlerTask(pool,Integer.parseInt(args[1])));
		//			t.sleep(500);
		//			t.start();}
		//}
		CrawlerTask Spider = new CrawlerTask(pool, Integer.parseInt(args[1]));
		Thread t1 = new Thread(Spider);
		Thread t2 = new Thread(Spider);
		Thread t3 = new Thread(Spider);
		t1.start();
		t2.sleep(700);
		t3.sleep(700);
		t2.start();
		t3.start();
	}
}