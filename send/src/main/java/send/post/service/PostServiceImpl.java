package send.post.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import send.common.common.CommandMap;
import send.common.util.CommonUtils;
import send.common.util.FileUtils;
import send.post.dao.PostDAO;

@Service("postService")
@Transactional
public class PostServiceImpl implements PostService{
	Logger log = Logger.getLogger(this.getClass());
	private static final int DEFAULT_PAGE_SIZE = 10;
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Resource(name="postDAO")
	private PostDAO postDAO;

	/* 
	 * 게시글 목록조회
	 * 
	 * ### 순서도 ###
	 * 1. 해당 정보로 친구 리스트 & 마이페이지 글 목록 조회
	 * 2. 각 게시글마다 해쉬태그 정보 리스트로 추출 및 삽입
	 * 3. 유저정보 미조회& 친구리스트 미조회시 해당 에러코드 보냄
	 * 
	 */
	@Override
	public Map<String, Object> selectUserFriendPostList(CommandMap commandMap) throws Exception {
		int page = 1;
		int startNo = 0;
		List<Map<String, Object>> userFriendPostList = null;
		Map<String, Object> content = new HashMap<String, Object>();
		Map<String, Object> messageMap = new HashMap<String, Object>();
		
        messageMap.put("type"		, "response");
        messageMap.put("code"		, HttpStatus.OK);
        messageMap.put("HttpStatus"	, HttpStatus.OK);
        messageMap.put("message"	, HttpStatus.OK.name());
		
        if(commandMap.isEmpty() == false && commandMap.get("idx") != null){
    		if(commandMap.getMap().get("page") != null){
    			page = Integer.parseInt(CommonUtils.chkNull(String.valueOf(commandMap.get("page")), "1"));
    		}
    		
    		if(page == 1) startNo = 0;
    		else startNo = (page -1) * 10;
    		
    		commandMap.put("startNo", startNo);
    		commandMap.put("pageRow", DEFAULT_PAGE_SIZE);
    		
    		userFriendPostList = postDAO.selectUserFriendPostList(commandMap.getMap());
    		
    		if(userFriendPostList.size() > 0){
    			for (Map<String, Object> map : userFriendPostList) {
    				map.put("hashTagList", postDAO.selectPostHashTagList(map));
				}
				content.put("userFriendPostList", userFriendPostList);
	    		messageMap.put("content", content);
    		}else{
    			noContentResult(messageMap);
    		}
    	
		}else{
			badRequestResult(messageMap);
		}
		return messageMap;
	}

	/*
	 * 게시글 상세보기
	 * 	 
	 * ### 순서도 ###
	 * 1. commandMap isEmpty 여부 조회
	 * 2. 해당글 조회수 증가
	 * 3. 해당글에 대한 정보조회
	 * 4. 해당글에 해쉬태그 리스트 추가
	 * 5. 해당글에 대한 답변글 조회
	 * 6. 해당글은 postInfo 답글리스트는 postReplyList 로 messageMap에 넣어 반환
	 * 7. 해당글에 대한 정보가 없을시 NO_CONTENT , NOTHING_RESULT 를 반환
	 */
	@Override
	public Map<String, Object> selectPostList(CommandMap commandMap) throws Exception {
		
		List<Map<String, Object>> postReplyList = null;
		Map<String, Object> content = new HashMap<String, Object>();
		Map<String, Object> postDetailInfo = new HashMap<String, Object>();
		Map<String, Object> messageMap = new HashMap<String, Object>();
		
        messageMap.put("type"		, "response");
        messageMap.put("HttpStatus"	, HttpStatus.OK);
        messageMap.put("message"	, HttpStatus.OK.name());
        messageMap.put("code"		, HttpStatus.OK.value());
		
        if(commandMap.isEmpty() == false && commandMap.get("pIdx") != null){
        	postDAO.updateHitCnt(commandMap.getMap());
        	postDetailInfo = postDAO.selectPostDetail(commandMap.getMap());
        	
        	if(postDetailInfo != null){
        		postDetailInfo.put("hashTagList", postDAO.selectPostHashTagList(postDetailInfo));
        		postReplyList = postDAO.selectPostReplyList(commandMap.getMap());
        		
        		content.put("postDetailInfo", postDetailInfo);
        		content.put("postReplyList", postReplyList);
        		messageMap.put("content", content);
        	}
        	else{
        		noContentResult(messageMap);
        	}
		}else{
			badRequestResult(messageMap);
		}
		return messageMap;
	}

	private Map<String, Object> noContentResult(Map<String, Object> messageMap){
		messageMap.put("HttpStatus"	, HttpStatus.NO_CONTENT);
		messageMap.put("code"		, HttpStatus.NO_CONTENT.value());
		messageMap.put("message"	, HttpStatus.NO_CONTENT.name());
		return messageMap;
	}
	
	private Map<String, Object> badRequestResult(Map<String, Object> messageMap){
		messageMap.put("HttpStatus"	, HttpStatus.BAD_REQUEST);
		messageMap.put("code"		, HttpStatus.BAD_REQUEST.value());
		messageMap.put("message"	, HttpStatus.BAD_REQUEST.name());
		return messageMap;
	}

}
