package sec.project.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Account;
import sec.project.domain.Signup;
import sec.project.repository.AccountRepository;
import sec.project.repository.SignupRepository;

@Controller
public class AdminController {

    @Autowired
    private SignupRepository signupRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String defaultMapping(Model model) {
        List<Signup> signups = signupRepository.findAll();
        List<Account> accounts = accountRepository.findAll();
        model.addAttribute("signups", signups);
        model.addAttribute("accounts", accounts);
        return "admin";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String accounts(Model model, @RequestParam String name) {
        List<Signup> signups = signupRepository.findAll();
        List<Account> accounts = accountRepository.findAll();
        model.addAttribute("signups", signups);
        model.addAttribute("accounts", accounts);
        return "admin";
    }

    @RequestMapping(value = "/admin/search", method = RequestMethod.GET)
    public String search(Model model) {
        return "search";
    }

    @RequestMapping(value = "/admin/search", method = RequestMethod.POST)
    public String listSignups(Model model, @RequestParam String name) {
        List<Signup> signupsByName = findSignups(name);
        model.addAttribute("nameSignups", signupsByName);
        return "search";
    }

    List<Signup> findSignups(String name) {
        List<Signup> test = jdbcTemplate.query("SELECT * FROM Signup WHERE name = " + "'" + name + "'",
                (rs, rowNum) -> new Signup(rs.getString("name"), rs.getString("address")));
        for (Signup signup : test) {
            System.out.println("Name: " + signup.getName() + ", address: " + signup.getAddress());
        }
        return test;
    }
}
