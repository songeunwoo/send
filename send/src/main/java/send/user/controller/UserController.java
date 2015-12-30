package send.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import send.common.common.CommandMap;
import send.common.util.CommonUtils;
import send.user.service.UserService;

/**
 * @FileName	: UserController.java
 * @Project	: send
 * @Date		: 2015. 12. 28. 
 * @작성자		: "GeunWoo Son (songeunwoo@nhn.com)"

 * @변경이력	: 최초생성
 * @프로그램 설명 : 
 */
@RestController
@RequestMapping("/user")
public class UserController {
	Logger log = Logger.getLogger(this.getClass());
	private static final String ERROR_LOGGING_PREFIX = "@@@Error-UserController::";
	
	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping(value="/userInfo")
    public ResponseEntity<Map<String, Object>> userInfo(CommandMap commandMap){
		Map<String, Object> messageMap = new HashMap<String, Object>();
		
		try {
			messageMap = userService.selectUserInfo(commandMap);
		} catch (Exception e) {
			log.error(ERROR_LOGGING_PREFIX + "userInfo " + e);
			CommonUtils.internalServerError(messageMap);
		}

		return new ResponseEntity<Map<String, Object>>(messageMap , (HttpStatus) messageMap.get("HttpStatus") );
    }
	
}

