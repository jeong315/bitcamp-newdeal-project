package trade.assignment.web.json;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import trade.assignment.domain.Member;
import trade.assignment.dto.RelationDTO;
import trade.assignment.service.MemberService;

@RestController
@RequestMapping("/edit")
public class ProfileController {
	 @Autowired 
	 MemberService memberService;
	 
		@RequestMapping(value="{name}", method=RequestMethod.GET)
	    public Object get(@PathVariable("name") String name,HttpSession session ) throws Exception {
	        
	        Member vo = 
	                (Member)session.getAttribute("loginUser");
	        
	        System.out.println(vo);
	        RelationDTO dto=new RelationDTO();
	        dto.setLoginid(vo.getNo());
	        dto.setName(vo.getName());
	        Member member = memberService.userRead(dto);
	        
	        System.out.println(member);
	        System.out.println("=======");
	        HashMap<String, Object> result = new HashMap<>();
	       result.put("status", "success"); 
	       result.put("data", member);
	      // result.put("list", list); 
	       
	        return result;
	        
	    }
		//프로필편집
		@RequestMapping(value = {"/profile/edit", "/profile/passwordChange"}, method = RequestMethod.GET)
		public Object profileEdit(Model model, HttpServletRequest request)throws Exception{
			System.out.println("profile edit GET..............");
			HttpSession session = request.getSession();
			HashMap<String, Object> result = new HashMap<>();
			if(session != null){
				
				//로그인 된 user정보 읽어들임
				Member user = (Member) session.getAttribute("loginUser");
				
				//유저 id로 갱신된 데이터 새로 읽기
				user = memberService.read(user.getNo());
				System.out.println(user.toString());
		
				model.addAttribute("Member", user);
				model.addAttribute("reqURL", request.getRequestURI());
				result.put("Member", user);
				result.put("reqURL", request.getRequestURI());
				
			}
			return result;
		}
		

		//pw중복확인
		@RequestMapping(value = "/profile/edit/chkPW", method = RequestMethod.GET)
		public Object checkPW(@RequestParam("id") int id, @RequestParam("password") String password) throws Exception {
			
			  HashMap<String, Object> result = new HashMap<>();
			System.out.println("비번확인으로하러");
			System.out.println("checkPW...................");
			System.out.println("id : " + id);
			System.out.println("password : " + password);
			  try {
			Member passCh = memberService.checkPassWord(id, password);
			if(passCh == null) {
				System.out.println("-------");
				return 0;
			}
			result.put("status","success");
			System.out.println("성공");
			 }
			  catch(Exception e) {
			  result.put("status","success");
			  }
			return 1;
		}
		
		// 비밀번호 수정
		@RequestMapping(value = "/profile/passwordChange", method = RequestMethod.POST)
		public Object passwordChangePOST(Member user, RedirectAttributes rttr,  HttpSession session) throws Exception {
			System.out.println("profile edit POST..............");
			
			
			System.out.println(user.toString());
			
			HashMap<String, Object> result = new HashMap<>();
			try {
				memberService.modifypassUser(user);
				rttr.addFlashAttribute("result", "passwordChange");
				result.put("result", "passwordChange");
				 result.put("status", "success"); 
			
			} catch (Exception e) {
				e.printStackTrace();
				rttr.addFlashAttribute("result", "fail");
			}
			System.out.println(rttr.toString());
			return result;
		}
		
		//프로필 사진 수정
		@RequestMapping(value = "/profile/edit/modifyPhoto", method = RequestMethod.POST)
		public Object profilePhothoEdit (@RequestParam("no") int no, @RequestParam("profilephoto") String profilephoto,HttpSession session) throws Exception {
			
			//@RequestParam("id") int id, @RequestParam("fileName") String fileName,
			System.out.println("profilePhothoEdit POST..............");
			System.out.println(profilephoto.toString());
			
			HashMap<String, Object> result = new HashMap<>();
			System.out.println("비번확인으로하러");
			System.out.println("checkPW...................");
			System.out.println("id : " + no);
			System.out.println("fileName : " + profilephoto);
			try { 
				//memberService.modifyPhoto(id, fileName);
				memberService.modifyPhoto(no,profilephoto);
				result.put("status", "success"); 
			}
			catch(Exception e) {
				e.printStackTrace();
				
				
			}
			 
			 return 1;
		}
}
