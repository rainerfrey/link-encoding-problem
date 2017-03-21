package de.mrfrey;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class TestController {
    @GetMapping("/")
    public Resource test(
            @RequestParam(name = "date1", required = false) @DateTimeFormat(iso = DATE_TIME) Date date1,
            @RequestParam(name = "date2", required = false) @DateTimeFormat(iso = DATE_TIME) Date date2) {
        Link template = linkTo(methodOn(TestController.class).test(date1, null)).withRel("template");
        Link expanded = linkTo(methodOn(TestController.class).test(date1, null)).withRel("expanded").expand();
        return new Resource("", template, expanded);
    }
}
