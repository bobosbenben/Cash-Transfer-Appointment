package en.common.frame.filter;

import java.util.Enumeration;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import en.common.util.helper.Base64;
import en.common.util.helper.StringUtil;

public class PropertyPlaceholderConfigurerMine extends PropertyPlaceholderConfigurer{

	@Override 
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) 
                    throws BeansException { 
		     Enumeration<Object> en =  props.keys();
		     Base64 encode = new Base64();
		     while(en.hasMoreElements()){
		    	 String key = (String)en.nextElement();
		    	 String nr = props.getProperty(key);
		    	 if(!StringUtil.isEmpty(nr) && !"liscence".equals(key)){
		    		 props.setProperty(key, encode.decode64(nr));
		    	 }
		     }
            super.processProperties(beanFactory, props); 

    } 
}
