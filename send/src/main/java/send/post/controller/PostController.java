package send.post.controller;

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
import send.post.service.PostService;

/**
 * @FileName	: UserController.java
 * @Project	: send
 * @Date		: 2015. 12. 28. 
 * @작성자		: "GeunWoo Son (songeunwoo@nhn.com)"

 * @변경이력	: 최초생성
 * @프로그램 설명 : 
 */
@RestController
@RequestMapping("/post")
public class PostController {
	Logger log = Logger.getLogger(this.getClass());
	private static final String ERROR_LOGGING_PREFIX = "@@@Error-PostController::";
	
	@Resource(name="postService")
	private PostService postService;
	
	/**
	 * @Method Name  : openBoardList
	 * @작성일   : 2015. 12. 28. 
	 * @작성자   : "GeunWoo Son (songeunwoo@nhn.com)"
	 * @변경이력  : 최초생성
	 * @Method 설명 : 게시글 목록 화면 조회
	 *   
	 * @param idx
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value="/postList")
    public ResponseEntity<Map<String, Object>> userList(CommandMap commandMap){
		Map<String, Object> messageMap = new HashMap<String, Object>();
		
		try {
			messageMap = postService.selectUserFriendPostList(commandMap);
		} catch (Exception e) {
			log.error(ERROR_LOGGING_PREFIX + "userList " + e);
			CommonUtils.internalServerError(messageMap);
		}

		return new ResponseEntity<Map<String, Object>>(messageMap , (HttpStatus) messageMap.get("HttpStatus") );
    }
	
	/**
	 * @Method Name  : postDetail
	 * @작성일   : 2015. 12. 28. 
	 * @작성자   : "GeunWoo Son (songeunwoo@nhn.com)"
	 * @변경이력  : 최초 생성
	 * @Method 설명 : 게시글 정보
	 * @param pIdx
	 * @return
	 */
	@RequestMapping(value="/postDetail")
    public ResponseEntity<Map<String, Object>> postDetail(CommandMap commandMap){
		Map<String, Object> messageMap = new HashMap<String, Object>();
		
		try {
			messageMap = postService.selectPostList(commandMap);
		} catch (Exception e) {
			log.error(ERROR_LOGGING_PREFIX + "postDetail " + e);
			CommonUtils.internalServerError(messageMap);
		}

		return new ResponseEntity<Map<String, Object>>(messageMap , (HttpStatus) messageMap.get("HttpStatus") );
    }
	
}

