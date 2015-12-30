package send.common.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;


public class CommonUtils {
	private static final Logger log = Logger.getLogger(CommonUtils.class);
	
	public static String getRandomString(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static void printMap(Map<String,Object> map){
		Iterator<Entry<String,Object>> iterator = map.entrySet().iterator();
		Entry<String,Object> entry = null;
		log.debug("--------------------printMap--------------------\n");
		while(iterator.hasNext()){
			entry = iterator.next();
			log.debug("key : "+entry.getKey()+",\tvalue : "+entry.getValue());
		}
		log.debug("");
		log.debug("------------------------------------------------\n");
	}
	
	public static void printList(List<Map<String,Object>> list){
		Iterator<Entry<String,Object>> iterator = null;
		Entry<String,Object> entry = null;
		log.debug("--------------------printList--------------------\n");
		int listSize = list.size();
		for(int i=0; i<listSize; i++){
			log.debug("list index : "+i);
			iterator = list.get(i).entrySet().iterator();
			while(iterator.hasNext()){
				entry = iterator.next();
				log.debug("key : "+entry.getKey()+",\tvalue : "+entry.getValue());
			}
			log.debug("\n");
		}
		log.debug("------------------------------------------------\n");
	}
	
	/**
     * 문자열이 널이나 공백일 경우 지정 문자열로 치환.
     * 
     * @param str 비교 대상 문자열
     * @param defaultStr 치환 문자열
     * @return 문자열
     */
    public static String chkNull(String str, String defaultStr) {
        if (str != null && !str.equals("")) {
            return str;
        } else {
            return defaultStr;
        }
    }
    
    public static boolean isEmpty(String str) {
        return (str == null || "".equals(str.trim()));
    }
    
    public static Map<String, Object> noContentResult(Map<String, Object> messageMap){
		messageMap.put("HttpStatus"	, HttpStatus.NO_CONTENT);
		messageMap.put("code"		, HttpStatus.NO_CONTENT.value());
		messageMap.put("message"	, HttpStatus.NO_CONTENT.name());
		return messageMap;
	}
	
    public static Map<String, Object> badRequestResult(Map<String, Object> messageMap){
		messageMap.put("HttpStatus"	, HttpStatus.BAD_REQUEST);
		messageMap.put("code"		, HttpStatus.BAD_REQUEST.value());
		messageMap.put("message"	, HttpStatus.BAD_REQUEST.name());
		return messageMap;
	}
	
    public static Map<String, Object> internalServerError(Map<String, Object> messageMap){
		 messageMap.put("HttpStatus", HttpStatus.INTERNAL_SERVER_ERROR);
        messageMap.put("code"		, HttpStatus.INTERNAL_SERVER_ERROR.value());
        messageMap.put("message"	, HttpStatus.INTERNAL_SERVER_ERROR.name());
		return messageMap;
	}
}
