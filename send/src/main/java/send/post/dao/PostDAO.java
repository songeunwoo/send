package send.post.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import send.common.dao.AbstractDAO;

@Repository("postDAO")
public class PostDAO extends AbstractDAO{

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectUserFriendPostList(Map<String, Object> map) throws Exception{
		return selectList("post.selectUserFriendPostList", map);
	}

	public void updateHitCnt(Map<String, Object> map) throws Exception{
		update("post.updateHitCnt", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectPostDetail(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectOne("post.selectPostDetail", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectPostReplyList(Map<String, Object> map) throws Exception{
		return selectList("post.selectPostReplyList", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectPostHashTagList(Map<String, Object> map) throws Exception{
		return selectList("post.selectPostHashTagList", map);
	}

}
