package trade.assignment.web.json;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import trade.assignment.domain.Member;
import trade.assignment.service.MemberService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    
    @Autowired 
    MemberService memberService;
    //@RequestMapping(value ="singUp", method=RequestMethod.POST)
   @PostMapping("signIn")
    public Object signUp(String email, String password,boolean saveEmail) {
        
        HashMap<String, Object> result = new HashMap<>();
        
        try {
            Member loginUser = memberService.getMember(email,password);
            
            System.out.println(loginUser);
            if(loginUser ==null) {
               throw new Exception("로그인 실패");
            }
           
           // session.setAttribute("loginUser",loginUser);
            result.put("status","success");
                
            
        }catch (Exception e) {
            result.put("status", "fail");
            result.put("message", e.getMessage());
        }
            
            
            return result;
    }
}
