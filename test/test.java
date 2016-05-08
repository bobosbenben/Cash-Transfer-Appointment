import static org.junit.Assert.*;

import org.junit.Test;

import en.common.util.helper.Base64;

import java.util.Arrays;


public class test {

	@Test
	public void test() {
		System.out.println(new Base64().encode64("jdbc:mysql://58.18.252.244:3307/test?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&autoReconnect=true"));
		System.out.println(new Base64().encode64("rcb"));
		System.out.println(new Base64().encode64("root"));
		System.out.println(new Base64().encode64("110826"));
		System.out.println(new Base64().decode64("0krGYwUxqWZ8a3fQjtNBLw5Q21NF2gFTa3qJMyExMxA1L1VSzgVR2wUxrlVxzVVS0WNTzgUJMyryalVJjti1Y1FFakfHbgVwrW8H21rn2kaJMyrVVEYROCUwNlnJak5EYXrJVgJRzUjJ0gf10W5wjtNEY15SbkVwbfrTtlVQ2CUwNkfZbg5szWNT2k8JY3qJMyryalVJ"));
//		String ss = "00001";
//		System.out.println(ss+"001");
//		String s = "00001001";
//		System.out.println("0000"+String.valueOf((Integer.valueOf(s)+1)));


		int mn[] = new int[64];
		for (int i=0;i<64;i++){
			mn[i]=10+i;
		}
		int abc[] = Arrays.copyOfRange(mn,0,5);
		for (int i=0;i<abc.length;i++) System.out.println(abc[i]);
	}

}
