package crawl;

import java.net.*;

public class URLDepthPair{
	
	URL url;

	private int depth;

	public URLDepthPair(String url, int depth) throws MalformedURLException {
		this.url = new URL(url);
		this.depth = depth;
	}
	

	public String getHost(){
		return this.url.getHost();
	}
	public String getPath(){
		return this.url.getPath();
	}
	public int getDepth(){
		return this.depth;
	}

	@Override
	public String toString(){
		return "link: "+url.toString()+"  depth: "+this.depth;
	}
	@Override
	public boolean equals(Object obj){
		if (obj instanceof URLDepthPair) {
            URLDepthPair objURL = (URLDepthPair)obj;
            if (objURL.url.equals(this.url)) return true;
            else return false;
        }
        return false;

	}
}
