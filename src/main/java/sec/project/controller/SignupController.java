package sec.project.controller;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Account;
import sec.project.domain.Signup;
import sec.project.repository.AccountRepository;
import sec.project.repository.SignupRepository;

@Controller
public class SignupController {

    @Autowired
    private SignupRepository signupRepository;

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/";
    }

    @RequestMapping("")
    public String home() {
//        Account account = new Account();
//        account.setUsername("red");
//        account.setPassword("red");
//        List<String> auth = new ArrayList<>();
//        auth.add("ADMIN");
//        account.setAuthorities(auth);
//        accountRepository.save(account);
//
//        account = new Account();
//        account.setUsername("admin");
//        account.setPassword("admin");
//        auth = new ArrayList<>();
//        auth.add("ADMIN");
//        account.setAuthorities(auth);
//        accountRepository.save(account);
//
//        account = new Account();
//        account.setUsername("dory");
//        account.setPassword("sins");
//        auth = new ArrayList<>();
//        auth.add("USER");
//        account.setAuthorities(auth);
//        accountRepository.save(account);

        Account account = accountRepository.findByUsername("admin");
        System.out.println(account);
        System.out.println(account.getUsername());
        System.out.println(account.getPassword());
        System.out.println(account.getAuthorities());
        return "index";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup() {
        return "accountform";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm() {
        return "form";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String createAccount(@RequestParam String username, @RequestParam String password) {
        System.out.println("HAAHAHAHAHAHAHA");
        Account account = new Account();
        account.setPassword(password);
        account.setUsername(username);
        List<String> auth = new ArrayList<>();
        auth.add("USER");
        account.setAuthorities(auth);
        accountRepository.save(account);
        return "redirect:/";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, @RequestParam String address) {
        signupRepository.save(new Signup(name, address));
        return "done";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public void handleError(HttpServletResponse response) throws Exception {
        response.sendRedirect("/");
    }
}
