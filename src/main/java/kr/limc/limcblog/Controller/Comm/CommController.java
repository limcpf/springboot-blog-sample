package kr.limc.limcblog.Controller.Comm;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(originPatterns = "*")
public class CommController {

    @GetMapping(path = "/ping")
    public String connectTest() {
        return "pong";
    }

    
}
