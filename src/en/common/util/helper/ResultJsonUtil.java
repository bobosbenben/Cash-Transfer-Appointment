package en.common.util.helper;


public class ResultJsonUtil {
	
	StringBuffer sb;
	
	 public ResultJsonUtil(String body) {
	        sb = new StringBuffer();
	        
	        sb.append("{");
	    }
	  public ResultJsonUtil(Long nsize) {
	        this("");
	        sb.append("totalRecords:").append(nsize).append(",");
	        sb.append("record : [");
	    }
	  
	  public void beginItem() {
	        sb.append("{");
	    }
	  public void endItem() {
	        sb.append("},");
	    }
	  public void endItems() {
	        sb.append("}");
	    }
	  public void addContainers(String s) {
	        sb.append(s);
	    }
	  public void addsign() {
	        sb.append(",");
	    }
	  public String getJsonStr() {
	       sb.append("]}");
	        return sb.toString();
	    }
	  public String getNullStr() {
	        sb.append("]}");
	        return sb.toString();
	    }
}
