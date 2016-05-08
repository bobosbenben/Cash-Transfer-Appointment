package en.common.util.helper;



import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


public class JacksonsUtil {
	
	private ObjectMapper objectMapper;  
	
	public static JacksonsUtil me() {  
        return new JacksonsUtil();  
    } 
	
	private JacksonsUtil() {  
        objectMapper = new ObjectMapper(); 
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);  
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);  
        
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));  
    } 
	
	 public String readAsString(Object obj) {  
	        try {  
	            return objectMapper.writeValueAsString(obj);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	            throw new RuntimeException("解析对象错误");  
	        }  
	    }  
	 public <T> T json2Obj(String json, Class<T> clazz) {  
	        try {  
	            return objectMapper.readValue(json, clazz);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	            throw new RuntimeException("解析json错误");  
	        }  
	    }  
	 
	 public List< Object> json2List(String json,Object obj) {  
	        try {  
	            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, obj.getClass()));  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	            throw new RuntimeException("解析json错误");  
	        }  
	    } 
	 public Map<?,?> json2MapForString(String json) {  
	        try {  
	            return objectMapper.readValue(json,Map.class);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	            throw new RuntimeException("解析json错误");  
	        }  
	    } 
}
