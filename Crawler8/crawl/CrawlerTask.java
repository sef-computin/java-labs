package crawl;

import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.Runnable;

public class CrawlerTask implements Runnable{
	private URLPool pool;
	//private URLPool result;
	public static int maxdepth; 

	public CrawlerTask(URLPool pool, int maxdepth){
		this.pool = pool;
		this.maxdepth = maxdepth;
		//this.result = res;
	}

	public static final String LINK = "<a href=\"http";

	public void crawl() throws MalformedURLException, UnknownHostException, IOException, SocketTimeoutException, InterruptedException {
		do{
		URLDepthPair currentPage;
		synchronized(pool){
			
			if(pool.isEmpty()){
				System.out.println("pool is Empty");
				return;
			}
			currentPage = pool.getNewURL();
		}
		try{
			Socket my_socket = new Socket();
			my_socket.connect(new InetSocketAddress(currentPage.getHost(), 80), 5000);
			System.out.println(currentPage.toString());

			PrintWriter wtr = new PrintWriter(my_socket.getOutputStream(), true);
			wtr.println("GET "+currentPage.getPath()+" HTTP/1.1");
			
        		wtr.println("Host: "+currentPage.getHost());
	        	wtr.println("Connection: close");
        		wtr.println("");
        		wtr.flush();
        		
        	//result.add(currentPage);
        		
        		BufferedReader bufRead = new BufferedReader(new InputStreamReader(my_socket.getInputStream()));
			String line;
			while((line = bufRead.readLine()) != null){
				if (line.contains(LINK) && (currentPage.getDepth()<this.maxdepth)){
					int found_indx = line.indexOf(LINK)+9;
					String cutLine = line.substring(found_indx);
					cutLine = cutLine.substring(0, cutLine.indexOf("\""));
					synchronized (pool){
						pool.add(new URLDepthPair(cutLine, currentPage.getDepth()+1));
						
					}
				}
			}
			my_socket.close();
		}
			catch(SocketTimeoutException e) {
				System.out.println(e.toString());
				continue;
			}
		} while(true);
	}

	public void run(){
		try{
			crawl();
		} catch(Exception e){System.out.println(e.toString());}
	}
}