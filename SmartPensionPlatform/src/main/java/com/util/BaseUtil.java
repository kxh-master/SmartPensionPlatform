package com.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import com.util.CreateId;

public class BaseUtil {
	/**
	 * 获取id
	 * @return
	 */
	public static String getUUID() {
		try {
			return CreateId.getid();
		} catch (Exception e) {
			return UUID.randomUUID().toString();
		}
	}
	
	public static boolean isSuccess(int result) {
		boolean isSuccess = false;
		if(result>0) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	/**
	 * 复制属性值
	 * @param <T>
	 * @param t1
	 * @param t2
	 * @return
	 */
	public static <T> T copyProperties(T t1,T t2) {
		if(t1!=null) {
			BeanUtils.copyProperties(t1, t2);
		}
		return t2;
	}
	
	/**
	 * 	获取两个数组中的不同值
	 * @param t1
	 * @param t2
	 * @return
	 */
	public static <T> List<T> compare(T[] t1, T[] t2) {
		List<T> list = null;
		if(t1==null || t1.length==0) {
			list =Arrays.asList(t2);
		}else if(t2==null || t2.length==0){
			list = Arrays.asList(t1);
		}else {
			List<T> list1 = Arrays.asList(t1);  
		    list = new ArrayList<T>();  
		      for (T t : t2) {  
		          if (!list1.contains(t)) {  
		        	  list.add(t);  
		          }  
		      }  
		}
	    return list;  
	  }
	
	
	/**
     * 获取两个List的不同元素
     * @param list1
     * @param list2
     * @return
     */
    public static List<String> getDiffrent(List<String> list1, List<String> list2) {
        List<String> diff = new ArrayList<String>();
        List<String> maxList = list1;
        List<String> minList = list2;
        if(list1!=null && list2==null) {
        	return list1;
        }else if(list1==null && list2!=null) {
        	return list2;
        }else if(list1!=null && list2!=null){
        	if (list2.size() > list1.size()) {
                maxList = list2;
                minList = list1;
            }
            Map<String, Integer> map = new HashMap<String, Integer>(maxList.size());
            for (String string : maxList) {
                map.put(string, 1);
            }
            for (String string : minList) {
                if (map.get(string) != null) {
                    map.put(string, 2);
                    continue;
                }
                diff.add(string);
            }
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() == 1) {
                    diff.add(entry.getKey());
                }
            }
        }
        return diff;
 
    }

}
