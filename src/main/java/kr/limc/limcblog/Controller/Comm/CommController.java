package kr.limc.limcblog.Controller.Comm;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommController {

    @GetMapping(path = "/health")
    public boolean connectTest() {
        return true;
    }

}
