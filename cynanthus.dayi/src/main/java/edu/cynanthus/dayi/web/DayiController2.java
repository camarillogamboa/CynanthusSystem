package edu.cynanthus.dayi.web;

import edu.cynanthus.auri.consumer.AuriSession;
import edu.cynanthus.domain.ServerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DayiController2 extends CommonController{

    @Autowired
    public DayiController2(AuriSession auriSession) {
        super(auriSession);
    }

    @GetMapping
    public String home(Model model){
        model.addAttribute("emptyServer", new ServerInfo());

        return "index";
    }

}
