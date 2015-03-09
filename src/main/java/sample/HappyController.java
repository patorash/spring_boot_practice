package sample;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@Controller
public class HappyController {
    private static final String TEMPLATE = "Hello, %s";

    @RequestMapping("/happy")
    @ResponseBody
    public HttpEntity<Happy> happy(
        @RequestParam(value = "name", required = false, defaultValue = "Happy") String name
    ) {
        Happy happy = new Happy(String.format(TEMPLATE, name));
        happy.add(linkTo(methodOn(HappyController.class).happy(name)).withSelfRel());

        return new ResponseEntity<Happy>(happy, HttpStatus.OK);
    }
}
