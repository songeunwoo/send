package send.user.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import send.common.common.CommandMap;

public interface UserService {
	//사용자 정보조회
	Map<String, Object> selectUserInfo(CommandMap commandMap) throws Exception;
	//사용자 저장
	void insertUser(Map<String, Object> map, HttpServletRequest request) throws Exception;
	//사용자 수정
	void updateUser(Map<String, Object> map, HttpServletRequest request) throws Exception;
	//사용자 삭제
	void deleteUser(Map<String, Object> map) throws Exception;


}
