package send.user.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import send.common.common.CommandMap;
import send.common.util.CommonUtils;
import send.common.util.FileUtils;
import send.user.dao.UserDAO;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Resource(name="userDAO")
	private UserDAO userDAO;


	/* 
	 * 마이 페이지
	 * selectUserInfo
	 * ### 순서도 ###
	 * 1. 해당 정보로 사용자 단건 조회
	 * 2. 미조회 및 밒수파라메터 미 입력시 해당 에러코드 전송
	 * 
	 */
	@Override
	public Map<String, Object> selectUserInfo(CommandMap commandMap) throws Exception {
		Map<String, Object> userInfo = new HashMap<String, Object>();
		Map<String, Object> content = new HashMap<String, Object>();
		Map<String, Object> messageMap = new HashMap<String, Object>();
		
        messageMap.put("type"		, "response");
        messageMap.put("HttpStatus"	, HttpStatus.OK);
        messageMap.put("message"	, HttpStatus.OK.name());
        messageMap.put("code"		, HttpStatus.OK.value());
		
        if(commandMap.isEmpty() == false){
        	userInfo = userDAO.selectUserDetail(commandMap.getMap());
    		
    		if(userInfo != null){
    			content.put("userInfo", userInfo);
	    		messageMap.put("content", content);
    		}else{
    			CommonUtils.noContentResult(messageMap);
    		}
		}else{
			CommonUtils.badRequestResult(messageMap);
		}
		return messageMap;
	}

	@Override
	public void insertUser(Map<String, Object> map, HttpServletRequest request) throws Exception {
		userDAO.insertUser(map);
	}
	
	@Override
	public void updateUser(Map<String, Object> map, HttpServletRequest request) throws Exception{
//		userDAO.updateUser(map);
		
	}

	@Override
	public void deleteUser(Map<String, Object> map) throws Exception {
//		userDAO.deleteUser(map);
	}

}
