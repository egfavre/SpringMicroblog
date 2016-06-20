package com.egfavre;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by user on 6/20/16.
 */
@Controller
public class MicroblogController {

    ArrayList<Message> messages = new ArrayList();

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        model.addAttribute("messages", messages);
        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String username) {
        session.setAttribute("username", username);
        return "redirect:/";
    }

    @RequestMapping(path = "/addMessage", method = RequestMethod.POST)
    public String addMessage(HttpSession session, String text) {
        Message message = new Message(messages.size() + 1, text);
        messages.add(message);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public String delete(int id){
        messages.remove(id-1);
        return "redirect:/";
    }
}