package monikamichael.money_manager.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/new_data")
public class NewDataController {
    @RequestMapping(method = RequestMethod.GET)
    public String doGet(ModelMap model) {
        model.addAttribute("message", "NEW DATA");
        return "new_data";
    }
}
