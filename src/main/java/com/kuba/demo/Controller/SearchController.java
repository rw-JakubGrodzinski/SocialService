package com.kuba.demo.Controller;

import com.kuba.demo.Model.User;
import com.kuba.demo.Repository.UserRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/main")
@SessionAttributes("searchedNames")
public class SearchController
{
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchForUsers (Model model, HttpSession session)
    {
        if(session.getAttribute("searchedNames") == null)
        {
            model.addAttribute("searchedNames", new ArrayList<User>());
        }
        return "search";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchPost (HttpServletRequest request, Model model)
    {
        List<User> users = userRepository.findUsersLike(request.getParameter("searchedPhrase"));
        model.addAttribute("searchedNames", users);
        return "redirect:/main/search";
    }
}
