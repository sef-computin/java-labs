package crawl;

import java.net.*;
import java.util.*;

public class URLPool{
	private List<URLDepthPair> pool;

	public URLPool(URLDepthPair url){
		this.pool = new LinkedList<URLDepthPair>();
		this.pool.add(url);
	}
	public URLPool(){
		this.pool = new LinkedList<URLDepthPair>();
	}

	public boolean isEmpty(){
		return pool.isEmpty();	
	}
	public void showPool(){
		for (URLDepthPair url : this.pool){
			System.out.println(url.toString());
		}
	}
	public void add(URLDepthPair url){
		synchronized(pool){
			boolean flag = true;
			for (URLDepthPair elem : pool){
				if (elem.equals(url)) flag = false;
			}
			if (flag) pool.add(url);
		}
		
	}
	public URLDepthPair getNewURL() throws InterruptedException{
		synchronized(pool){
			if (!this.pool.isEmpty()){
				return this.pool.remove(0);
			}
			else return null;
	}
	}

}