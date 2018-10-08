package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.FoodVO;
import vo.MemberVO;
import vo.StationVO;
import vo.content_tipVO;

@Repository
public class InfoodDaoImpl implements InfoodDao {
	
	@Autowired
	SqlSession sqlSession;

	@Override
	public MemberVO check(String nikname) {
		MemberVO vo = sqlSession.selectOne("user.check", nikname);
		return vo;
	}
	
	@Override
	public int join(String email, String pwd, String nikname) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("pwd", pwd);
		map.put("nikname", nikname);
		
		int result = sqlSession.insert("user.join",map);
		return result;
	}

	@Override
	public MemberVO login(String email, String shaPwd) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("pwd", shaPwd);
		
		MemberVO vo = sqlSession.selectOne("user.login",map);
		return vo;
	}

	@Override
	public MemberVO user_check(String email) {
		MemberVO vo = sqlSession.selectOne("user.user_check",email);
		return vo;
	}

	@Override
	public int last_login(String email, String shaPwd) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("pwd", shaPwd);
		int result = sqlSession.update("user.last_login",map);
		return result;
	}

	@Override
	public MemberVO email_check(String email) {
		MemberVO vo = sqlSession.selectOne("user.email_check", email);
		return vo;
	}

	@Override
	public List<FoodVO> food_list() {
		List<FoodVO> list = sqlSession.selectList("content.food_list");
		return list;
	}

	@Override
	public List<StationVO> station(String station) {
		List<StationVO> list = sqlSession.selectList("content.station",station);
		return list;
	}

	@Override
	public int upload_content_food(int user_idx, String user_nikname, String filename, String subway, String food,
			String content) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_idx", user_idx);
		map.put("user_nikname", user_nikname);
		map.put("filename", filename);
		map.put("subway", subway);
		map.put("food", food);
		map.put("content", content);
		int result = sqlSession.insert("content.content_food", map);
		return result;
	}

	@Override
	public int upload_content_tip(List<String> image_list, Object[] contents, String title, String user_nikname) {
		int result = 0;
		Map<String, Object> map; 
		switch(image_list.size()) 
		{
		case 1:
			map = new HashMap<String, Object>();
			map.put("user_nikname", user_nikname);
			map.put("title", title);
			map.put("content1", contents[0]);
			map.put("content_image1", image_list.get(0));
			result = sqlSession.insert("content.content_tip1", map);
			break;
		case 2:
			map = new HashMap<String, Object>();
			map.put("user_nikname", user_nikname);
			map.put("title", title);
			map.put("content1", contents[0]);
			map.put("content2", contents[1]);
			map.put("content_image1", image_list.get(0));
			map.put("content_image2", image_list.get(1));
			result = sqlSession.insert("content.content_tip2", map);
			break;
		case 3:
			map = new HashMap<String, Object>();
			map.put("user_nikname", user_nikname);
			map.put("title", title);
			map.put("content1", contents[0]);
			map.put("content2", contents[1]);
			map.put("content3", contents[2]);
			map.put("content_image1", image_list.get(0));
			map.put("content_image2", image_list.get(1));
			map.put("content_image3", image_list.get(2));
			result = sqlSession.insert("content.content_tip3", map);
			break;
		case 4:
			map = new HashMap<String, Object>();
			map.put("user_nikname", user_nikname);
			map.put("title", title);
			map.put("content1", contents[0]);
			map.put("content2", contents[1]);
			map.put("content3", contents[2]);
			map.put("content4", contents[3]);
			map.put("content_image1", image_list.get(0));
			map.put("content_image2", image_list.get(1));
			map.put("content_image3", image_list.get(2));
			map.put("content_image4", image_list.get(3));
			result = sqlSession.insert("content.content_tip4", map);
			break;
		case 5:
			map = new HashMap<String, Object>();
			map.put("user_nikname", user_nikname);
			map.put("title", title);
			map.put("content1", contents[0]);
			map.put("content2", contents[1]);
			map.put("content3", contents[2]);
			map.put("content4", contents[3]);
			map.put("content5", contents[4]);
			map.put("content_image1", image_list.get(0));
			map.put("content_image2", image_list.get(1));
			map.put("content_image3", image_list.get(2));
			map.put("content_image4", image_list.get(3));
			map.put("content_image5", image_list.get(4));
			result = sqlSession.insert("content.content_tip5", map);
			break;
		}
		
		return result;
	}

	@Override
	public List<content_tipVO> tip_list() {
		List<content_tipVO> list = sqlSession.selectList("content.tip_list");
		return list;
	}

}
