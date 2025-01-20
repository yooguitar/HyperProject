package com.kh.hyper.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.hyper.common.ModelAndViewUtil;
import com.kh.hyper.member.model.service.MemberService;
import com.kh.hyper.member.model.vo.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/*
 @Component : 보편적으로 사용하는 bean 등록 애노테이션
 			@Controller bean으로도 등록 가능하다.(annotation-driven 있어야됨)
			servlet-context.xml 참고
 *Component는 mvc 패턴등을 포함하는 큰 개념임
*/

@Slf4j
@Controller
@RequiredArgsConstructor // 롬복에서 제공하는 애노테이션, 생성자를 자동으로 만들어준다.
//@RequestMapping(value="member")
public class MemberController {
	private final MemberService memberService; 
	// private final BCryptPasswordEncoder passwordEncoder;
	private final ModelAndViewUtil mv;
	// private String name; => final키워드가 붙지 않았다면 생성자 매개변수로 포함되지 않는다!
	
	
	/*
	//@Autowired // 필드 주입 => 장점 : 쓸 내용이 적다 나머지 다 단점임. => 순환 의존성이 발생
	private MemberService memberService;
	private BCryptPasswordEncoder passwordEncoder;
	
	// Spring D.I(DependencyInjection)
	@Autowired // 생성자 주입 => 매개변수와 일치하는 타입의 Bean객체를 검색해서 인자값으로 주입해준다. 권장방법
	public MemberController(MemberService memberService, BCryptPasswordEncoder passwordEncoder) {
		this.memberService = memberService;
		this.passwordEncoder = passwordEncoder;
	}
	*/
	
	
	/*
	 * handler mapping클래스로 요청을 처리할 메소드를 찾아야함
	 * getHandler() => 누가 처리하지..?
	 * 
	 * login.me
	 * insert.me
	 * update.me
	 */
	
//	@RequestMapping(value="login.me") // RequestMapping타입의 애노테이션을 등록함으로서 HandlerMapping을 등록
//	public void login() {
//		// 서블릿을 계속 만들지 않고 요청을 처리해줄 메소드를 만들어서 리퀘스트 매핑만 해주면 된다
//		// 리퀘스트 매핑값이 겹칠 경우 서버 켤 때 에러 발생
//		System.out.println("로그인 요청을 보냈니?");
//	}
//	@RequestMapping(value="insert")
//	public void insert() {
//		System.out.println("추가?");
//	}
//	@RequestMapping(value="delete")
//	public void update() {
//		System.out.println("삭제야?");
//	}
	
	/*
	 * Spring에서 요청 시 전달 값(Parameter)를 받아서 사용하는 방법
	 * 
	 * 1. HttpServletRequest를 이용해서 전달받기 (기존의 JSP /Servlet 방식)
	 * 
	 * 핸들러의 매개변수로 HttpServletRequest타입을 작성해두면
	 * DispatcherServlet이 해당 메서드를 호출할 때 request객체를 전달해서 매개변수로 주입해줌!
	 * 
	 *	@RequestMapping(value="login.me")
	 *	public String login(HttpServletRequest request) {
	 *		String id = request.getParameter("id");
	 *		String pwd = request.getParameter("pwd");
	 *		System.out.println(id);
	 *		System.out.println(pwd);
	 *		return "main";
	 *	}
	 *
	 */
	
	/*
	 * 2. @RequestParam 애노테이션을 이용하는 방법
	 * 
	 * request.getParameter("키")로 밸류를 뽑아오는 역할을 대신해주는 애노테이션
	 * value속성의 값으로 jsp작성한 키 값을 적으면 알아서 해당 매개변수에 주입을 해줌
	 * 만약, 넘어온 값이 비어있는 형태라면 defaultValue속성으로 기본값을 지정해둘 수 있음
	 * 
	 *	@RequestMapping("login.me")
	 *	public String login(@RequestParam(value="id", defaultValue="user01") String id,
	 *							// 입력한 값이 없을 경우 발생할 수 있는 예외들을 방지
	 *						@RequestParam(value="pwd") String pwd) {
	 *		System.out.println(id);
	 *		System.out.println(pwd);
	 *		return "main";
	 *	}
	 */ 
	
	/*
	 * 3. RequestParam 애노테이션을 생략하는 방법 
	 *
	 * 단, 매개변수 명을 jsp에서 전달한 key값과 동일하게 작성해야 자동으로 값이 주입
	 * 단점은 키값의 의미가 명확하지 않을 수 있고, defaultValue속성을 사용할 수 없다
	 *	@RequestMapping("login.me")
	 *	public String login(String id, String pwd) {
	 *		System.out.println(id);
	 *		System.out.println(pwd);
	 *		
	 *		Member member = new Member();
	 *		member.setUserId(id);
	 *		member.setUserPwd(pwd);
	 *	
	 *		return "main";
	 *	}
	 */
	 
//	   4. 커맨드 객체 방식
//	   
//	 	1. 전달되는 키 값과 객체의 필드명이 동일해야함
//		2. 기본생성자가 반드시 존재해야함
//		3. setter메소드가 반드시 존재해야함
//		 대소문자를 구분하며 키값을 정 확 히 입력해야함.
//		Spring에서 해당 객체를 기본생성자를 통해서 생성한 후 내부적으로 setter메소드를 찾아서 요청 시 전달값을 해당 필드에 담아줌
//		=> setter injection

	
//	 	@RequestMapping("login.me")
//	 	public String login(Member member) {
//	 		// System.out.println(member);
//	 		
//	 		// 기존방식은 서비스와 의존관계가 생긴다.
//	 		// new MemberService().login(member);
//	 		// Service클래스의 수정이 일어날 경우 Service클래스를 의존하고 있던 Controller가 영향을 받게된다 어이없어
//	 		
//	 		// 전략패턴을 학습해야함
//	 		// + IOC, DI
//	 		
//	 		Member loginMember = memberService.login(member);
//	 		
//	 		/*
//	 		if(loginMember != null) {
//	 			System.out.println("로그인 성공");
//	 		} else {
//	 			System.out.println("로그인 실패");
//	 		}
//	 		  
//	 		여기서 생각해볼점 2가지
//	 		Q.return으로 문자열 main이 갔는데 진짜 main페이지로 감
//	 		Q.loginMember는 어디로?
//
//	 		return "main";
//	 		handler adapter로 돌아간다 => modelAndView로 가게됨
//	 		*/
//
//	 	}
	 
	 /*
	  * Client의 요청 처리 후 응답데이터를 담아서 응답페이지로 포워딩 또는 URL재요청 하는 방법
	  * 					
	  * 1. 스프링에서 제공하는 Model객체를 사용하는 방법 	*model은 데이터를 의미
	  * 포워딩할 응답 뷰로 전달하고자 하는 데이터를 맵형식(key-value)으로 담을 수 있는 영역
	  * Model객체는 requestScope
	  * 
	  * 단, setAttribute()가 아닌 addAttribute()를 호출해야함
	  * 
	  * 
	  */
	
	
//	@PostMapping("login.me")
//	public String login(Member member, Model model, HttpSession session) {
//		
//		Member loginMember = memberService.login(member);
//		
//		if(loginMember != null) { // 정보가 있다 => loginMember를 sessionScope에 담기
//			// sesseion은 HttpSession 타입
//			session.setAttribute("loginUser", loginMember);
//			
//			// 포워딩하면 url이 계속 남게됨
//			// return "main"; => fowarding => Redirect
//			// sendRedirect
//			// localhost/spring 	/
//			
//			// redirect:요청할URL
//			
//			return "redirect:/";
//			
//		} else { // 로그인 실패 => 에러문구를 requestScope에 담아서 에러페이지로 포워딩
//			model.addAttribute("errorMsg", "로그인 실패");
//			/*
//			 *.	/WEB-INF/views/common/error_page.jsp
//			 *		
//			 *	String Type return -> viewName에 대입
//			 *	-> DispatcherServlet -> ViewResolver
//			 *
//			 *	- prefix : /WEB-INF/views/
//			 *
//			 *	- 중간 : return viewName;
//			 *
//			 *	- suffix : .jsp
//			 */
//			return "common/error_page"; // 뺄거 빼고 요거만 돌려주면 됨
//		}
//	}
	
	
	/*
	 * 2. ModelAndView타입을 사용하는 방법
	 * 
	 * Model은 데이터를 key-value세트로 담을 수 있는 객체
	 * View는 응답 뷰에 대한 정보를 담을 수 있음
	 * 
	 * Model객체와 View가 결합된 형태의 객체
	 */
	
	// 로그인 핸들러
	@PostMapping("login.me")
	public ModelAndView login(Member member, 
							HttpSession session
						// , ModelAndView mv
							) {
		
		// 사용자가 입력한 비밀번호 : member => memberPwd필드(평문 == plaintext)
		
		// Member Table의 USER_PWD 컬럼에는 암호문이 들어있기 때문에
		// WHERE 조건절의 결과가 절대로 true가 될 순 없음
		
		Member loginMember = memberService.login(member);
		
		// Member타입의 loginMember의 userPwd 필드 : DB에 기록된 암호화된 비밀번호
		// Member타입의 member의 userPwd 필드 : 사용자가 입력한 평문 비밀번호 
		
		// $2a$salt값....
		// 1234
		// 똑같은 버전/반복횟수/salt값 을 돌리면 같은 암호코드가 나온다
		// 하지만 평문으로 되돌릴 수는 없다
		
		// BCryptPasswordEncoder객체 : matches()
		// matches(평문, 암호문)
		// 암호문에 포함되어있는 버전과 반복횟수과 솔트값을 가지고 인자로 전달된 평문을 다시 암호화를 거쳐서
		// 두 번째 인자로 전달된 암호문과 같은지 다른지 비교한 결과값을 반환 => 논리값을 반환한다, 두 값이 일치하면 true
		
		/*
		// 앞 조건에 false라면 숏서킷연산이 되어 뒤의 조건은 검사하지 않는다 
		if(loginMember != null && passwordEncoder.matches(member.getUserPwd(), loginMember.getUserPwd())) {
			session.setAttribute("loginUser", loginMember);
			mv.setViewName("redirect:/");
			//System.out.println("성공");
			// view에 대한 정보를 viewName 필드에 담아준다
			session.setAttribute("alertMsg", "로그인성공");
		} else {
			// 에러메세지는 requestScope에 담으면 됨
			mv.addObject("errorMsg", "로그인실패2");
			mv.setViewName("common/error_page");
		}
		*/
		
		// 이제 로그인 검사를 서비스에서 처리하고 결과만 받음
		// 요기부터는 컨트롤러가 요청받기, 응답하기 만 수행 한다
		session.setAttribute("loginUser", loginMember);
		session.setAttribute("alertMsg", "로그인성공");
		//mv.setViewName("redirect:/");
		return mv.setViewNameAndData("redirect:/", null);
		
	} // login
	
	@GetMapping("logout.me")
	public String logout(HttpSession session) {
		session.removeAttribute("loginUser");
		return "redirect:/";
	}

// 	ModelAndView 방식 
//	@GetMapping
//	public ModelAndView logout(HttpSession session, ModelAndView mv) {
//		session.removeAttribute("loginUser");
//		mv.setViewName("redirect:/");
//	}
	
	@GetMapping("enrollform.me")
	public String enrollForm() {
		// /WEB-INF/views/member/enroll_form.jsp
		// /WEB-INF/views/                  .jsp
		return "member/enroll_form";
	}

	
	@PostMapping("sign-up.me")
	public ModelAndView signUp(Member member,
							  // ModelAndView mv,
							   HttpSession session
							   // HttpServletRequest request
							   ) {
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		// member.setUserName(request.getParameter("userName"));
		
		// log.info("{}", member);
		
		/*
			 1. 한글 문자가 깨짐 => 인코딩!! --> web.xml에서 스프링에서 제공하는 인코딩 필터를 등록해보자
			 
			 2. 나이를 입력하지 않았을 경우 int 자료형 age필드에 빈 문자열이 bind되기 때문에
			 Parsing 과정에서 String -> int  NumberFormatException 발생
			 => 클라이언트에게 400 Bad Request 응답
			 
			 3. 비밀번호가 사용자가 입력한 그대로의 평문(plain text)
			 
				 평문(Plaintext)   : 해독 가능한 형태의 메시지
				 암호문(Ciphertext) : 해독 불가능한 형태의 메시지
				 암호화(Encryption) : 평문을 암호문으로 변환하는 과정
				 복호화(Decryption) : 암호문을 평문으로 변환하는 과정
			 
			 Bcrypt 이용해서 암호화 -> Spring Security Modules에서 제공(pom.xml)
			 passwordEncoder를 .xml파일을 이용해서 configurationBean으로 Bean으로 등록
			 => web.xml에서 spring-security, xml파일을 로딩할 수 있도록 추가
			 
			 평문 출력
			 System.out.println("평문 : " + member.getUserPwd());
			 log.info("평문 : {}", member.getUserPwd());
			 암호화 작업(암호문을 만드는 방법)
			 String encPwd = passwordEncoder.encode(member.getUserPwd());
			 암호문 출력
			 System.out.println("암호문 : " + encPwd);
			 log.info("암호문 : {}", encPwd);
			 member.setUserPwd(encPwd);
			 Member객체 userPwd 필드에 평문이 아닌 암호문을 담아서 INSERT를 수행
		*/
		/*
		if(memberService.join(member) > 0) {
			session.setAttribute("alertMsg", "회원가입성공");
			mv.setViewName("redirect:/");
		} else {
			// ModelAndView로 보내겠다.
			mv.addObject("errorMsg", "회원가입실패").setViewName("common/error_page");
			//					/WEB-INF/views/		common/error_page		.jsp
		}
		*/
		
		
		memberService.login(member);
		session.setAttribute("alertMsg", "가입성공성공");
		//mv.setViewName("redirect:/");
		return mv.setViewNameAndData("redirect:/", null);
	}
	
	
	@GetMapping("mypage.me")
	public String mypage() {
		return "member/my_page"; 	// return "/WEB-INF/views/  member/my_page  .jsp";
	}
	
	
	@PostMapping("update.me")
	// 갱신 된 내용을 session의 loginUser에 덮어씌워 줘야한다
	public ModelAndView update(Member member,
							  // ModelAndView mv,
			
							   HttpSession session) {
		/*
		if(memberService.updateMember(member) > 0) {
			// DB로부터 수정된 회원정보를 다시 조회해서
			// sessionScope의 loginUser라는 키값으로 덮어씌울것!
			// **다시 조회 할 때 id, pwd를 입력받지 않았지만 이미 loginUser에 새 값을 덮어씌웠기 때문에 그냥 갔다올 수 있다
			session.setAttribute("loginUser", memberService.login(member));
			session.setAttribute("alert", "정보수정에 성공");
			mv.setViewName("redirect:mypage.me");
		} else {
			// 수정 실패 => 에러문구 담아서 에러페이지로 포워딩
			mv.addObject("errorMsg", "정보수정에 실패").setViewName("common/error_page");
		}
		
		session.setAttribute("loginUser", memberService.updateMember(member));
		session.setAttribute("alert", "정보수정에 성공");
		mv.setViewName("redirect:mypage.me");
		return mv;
		 */
		// session.setAttribute("loginUser", memberService.login(member));
		//mv.setViewName("redirect:mypage.me");
		memberService.updateMember(member, session);
		session.setAttribute("alert", "정보수정에 성공");
		return mv.setViewNameAndData("redirect:mypage.me", null);
		
	}
	
	
	@PostMapping("delete.me")
	public ModelAndView delete(String userPwd, HttpSession session
			//, ModelAndView mv
			) {
		/*
		userPwd : 회원 탈퇴 요청 시 사용자가 입력한 비밀번호 평문
		session의 loginUser객체의 userPwd필드 : DB에 기록된 암호화된 비밀번호
		Member loginUser = ((Member)session.getAttribute("loginUser"));
		String encPwd = loginUser.getUserPwd();
		if(passwordEncoder.matches(userPwd, encPwd)) {
			비밀번호가 사용자가 입력한 평문을 이용해서 만든 비밀번호일 경우
			if(memberService.deleteMember(loginUser, session) > 0) {
				session.removeAttribute("loginUser");
				session.setAttribute("alertMsg", "잘가");
				return "redirect:/";
			} else {
				session.setAttribute("alertMsg", "탈퇴실패 관리자한테 문의해");
				return "common/errorPage";
			}
		} else {
			session.setAttribute("alertMsg", "비밀번호가 일치하지 않는다");
			return "redirect:mypage.me";
		}
		
		memberService.deleteMember(userPwd, session);
		session.removeAttribute("loginUser");
		session.setAttribute("alertMsg", "잘가");
		mv.setViewName("redirect:/");
		return mv;
		
		 */
		
		memberService.deleteMember(userPwd, session);
		session.removeAttribute("loginUser");
		session.setAttribute("alertMsg", "잘가");
		return mv.setViewNameAndData("redirect:/", null);
	}
	
	
	
	/*
	// 중복 줄이기 위해 분리, 하지만 모든 컨트롤러가 사용할 수 있는곳에 놓겠다 => 
	private ModelAndView setViewNameAndData(String viewName, String key, Object data) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		if(key != null && data != null) {
			mv.addObject(key, data);
		}
		return mv;
	}
	*/
	
	
	/*-------------- ajax-------------------------------------------------------*/
	
	@ResponseBody
	@GetMapping("idcheck")
	public String checkId(String userId) {
//		String result = memberService.checkId(userId);
//		log.info("아이디 중복이 발생했는가 : {}", result);
		return memberService.checkId(userId);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
