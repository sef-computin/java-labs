package crawl;

import java.io.*;
import java.net.*;
import java.util.*;

public class Crawler{
	List<URLDepthPair> pagesVisited;
	public static final String LINK = "<a href=\"http";
	List<URLDepthPair> pagesToVisit;

	public Crawler(){
		pagesVisited = new LinkedList<URLDepthPair>();
		pagesToVisit = new LinkedList<URLDepthPair>();
	}
	
	public void crawl(String website_url, int maxdepth) throws MalformedURLException, UnknownHostException, IOException, SocketTimeoutException {
		URLDepthPair website = new URLDepthPair(website_url, 0);
		
		pagesToVisit.add(website);
		while (!pagesToVisit.isEmpty()) {
			
			URLDepthPair currentPage = pagesToVisit.get(0);
			pagesToVisit.remove(0);
			try{
			Socket my_socket = new Socket();
			my_socket.connect(new InetSocketAddress(currentPage.getHost(), 80), 5000);
			pagesVisited.add(currentPage);
			System.out.println(currentPage.toString());
			

			PrintWriter wtr = new PrintWriter(my_socket.getOutputStream(), true);
			wtr.println("GET "+website.getPath()+" HTTP/1.1");
			
        		wtr.println("Host: "+website.getHost());
	        	wtr.println("Connection: close");
        		wtr.println("");
        		wtr.flush();
        		
        		BufferedReader bufRead = new BufferedReader(new InputStreamReader(my_socket.getInputStream()));
			String line;
			while((line = bufRead.readLine()) != null){
				if (line.contains(LINK) && (currentPage.getDepth()<maxdepth)){
					int found_indx = line.indexOf(LINK)+9;
					String cutLine = line.substring(found_indx);
					cutLine = cutLine.substring(0, cutLine.indexOf("\""));
					pagesToVisit.add(new URLDepthPair(cutLine, currentPage.getDepth()+1));
				}
				
			}
			my_socket.close();
		}
			catch(SocketTimeoutException e) {
				System.out.println(e.toString());
				continue;
			}

		}
	} 
				
		
		
	
	public void showWebsites(){
		for (int i = 0; i< pagesVisited.size();i++){
			System.out.println(pagesVisited.get(i).toString());
		}
	}

}
