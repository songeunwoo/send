package send.user.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import send.common.dao.AbstractDAO;

@Repository("userDAO")
public class UserDAO extends AbstractDAO{

	public void insertUser(Map<String, Object> map) throws Exception{
		insert("user.insertUser", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectUserDetail(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectOne("user.selectUserDetail", map);
	}
	

}
