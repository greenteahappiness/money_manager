package monikamichael.money_manager.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/graphs")
public class GraphsController {

    @RequestMapping(method = RequestMethod.GET)
    public String doGet() {

        return "graphs";
    }
}