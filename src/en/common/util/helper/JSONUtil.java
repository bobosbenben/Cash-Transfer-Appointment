package en.common.util.helper;

public class JSONUtil {
	private StringBuffer sb;
	public JSONUtil(){
		sb= new StringBuffer("{");
	}
	public void addJsonString(String title,String value){
		if(sb.toString().equals("{"))
			sb=sb.append(title).append(":'"+value+"'");
		else
			sb=sb.append(",").append(title).append(":'"+value+"'");
	}
	public void addJsonString(Object title,Object value){
		if(sb.toString().equals("{"))
			sb=sb.append(title).append(":'"+value+"'");
		else
			sb=sb.append(",").append(title).append(":'"+value+"'");
	}
	public void addJsonString(String title,Long value){
		if(sb.toString().equals("{"))
			sb=sb.append(title).append(":'"+value+"'");
		else
			sb=sb.append(",").append(title).append(":'"+value+"'");
	}
	public void addJsonString(String title,Double value){
		if(sb.toString().equals("{"))
			sb=sb.append(title).append(":"+value);
		else
			sb=sb.append(",").append(title).append(":"+value);
	}
	public String getString(){
		sb=sb.append("}");
		return sb.toString();
	}
}
