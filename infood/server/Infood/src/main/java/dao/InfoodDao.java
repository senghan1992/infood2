package dao;

import java.util.ArrayList;
import java.util.List;

import vo.FoodVO;
import vo.MemberVO;
import vo.StationVO;
import vo.content_tipVO;

public interface InfoodDao {
	//닉네임 중복체크
	public MemberVO check(String nikname);
	//이메일 중복체크
	public MemberVO email_check(String email);
	//회원가입
	public int join(String email, String pwd, String nikname);
	//로그인(등록되지 않은 사용자인 경우)
	public MemberVO user_check(String email);
	//로그인(등록은 되어있는데 아이디 혹은 비밀번호 오류인 경우)
	public MemberVO login(String email, String shaPwd);
	//로그인 성공시 최근 로그인 현재 시간으로 바꾸기
	public int last_login(String email, String shaPwd);
	//홈화면 리스트
	public List<FoodVO> food_list();
	
	//맛집 리스트 올리기
	public int upload_content_food(int user_idx, String user_nikname, String filename, String subway, String food, String content);
	
	//tip 저장하기
	public int upload_content_tip(List<String> image_list, Object[] contents, String title, String user_nikname);
	
	//tip 가져오기
	public List<content_tipVO> tip_list();
	
	//실시간 지하철 역 검색
	public List<StationVO> station();
	
	//지하철역 주변으로 음식 정보 가져오기
	public List<FoodVO> food_station(String subway);
	
	//지하철역 정보를 통해 가져오기
	public List<FoodVO> search(String station);
	
}
