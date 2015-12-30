package send.post.service;

import java.util.Map;

import send.common.common.CommandMap;

public interface PostService {
	//글 목록 화면 조회
	Map<String, Object> selectUserFriendPostList(CommandMap commandMap) throws Exception;
	//게시글 상세조회
	Map<String, Object> selectPostList(CommandMap commandMap) throws Exception;


}
