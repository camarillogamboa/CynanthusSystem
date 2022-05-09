package edu.cynanthus.dayi.web;

import edu.cynanthus.auri.consumer.AuriSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cynanthus/dayi")
public class ViewController {

    private final AuriSession auriSession;

    @Autowired
    public ViewController(AuriSession auriSession) {
        this.auriSession = auriSession;
    }

    @GetMapping
    public String index(Model model) {

        return "index";
    }

}
