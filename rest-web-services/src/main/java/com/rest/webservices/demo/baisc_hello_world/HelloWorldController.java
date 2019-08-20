package com.rest.webservices.demo.baisc_hello_world;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * When creating a RESTful service we need to define
 *  - what method GET, POST
 *  - URI - where to send the request
 *
 *  - What is dispatcher servlet?
 *      Handler of request (Look the typed URI and the Request method)
 *  - What does dispatcher servlet do?
 *      Convert the Bean to JSON
 */

@RestController // To handle REST request
public class HelloWorldController {

    private final MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    // @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    @GetMapping(path = "/hello-world") // To improve our code reading we can use GetMapping
    public String getMessage() {
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean getHelloWorldBeanMessage() {
        return new HelloWorldBean("Hello World"); // Return as JSON response
    }

    // Customized the response depending from with country it comes from
    // Set the messages accordingly (messages.properties folder)
    // Get the massages.properties accordingly with the request HEADER
    // Accept-Language -> ptbr
    // Set in application.properties
    @GetMapping(path = "/hello-world-internationalized")
    public String getHelloWorldBeanMessageInternationalized() {
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }

    // If the URI typed is "/hello-world/path-variable/example-site" then this method
    // will print "example-site" in the browser
    @GetMapping(path = "/hello-world/path-variable/{name}")
    // We use @PathVariable to get from the URI request
    public HelloWorldBean createHelloWorldBeanPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }
}
