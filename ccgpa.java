package com.example.cgpacalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

@Controller
class CGPAController {

    private static final String HTML_TEMPLATE = """
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>CGPA Calculator</title>
    </head>
    <body>
        <h1>CGPA Calculator</h1>
        <form action="/calculate" method="post">
            <label for="subject1">Enter grade for Subject 1:</label>
            <input type="number" id="subject1" name="subject1" step="any" required><br><br>

            <label for="subject2">Enter grade for Subject 2:</label>
            <input type="number" id="subject2" name="subject2" step="any" required><br><br>

            <label for="subject3">Enter grade for Subject 3:</label>
            <input type="number" id="subject3" name="subject3" step="any" required><br><br>

            <label for="subject4">Enter grade for Subject 4:</label>
            <input type="number" id="subject4" name="subject4" step="any" required><br><br>

            <input type="submit" value="Calculate CGPA">
        </form>

        <p>%s</p>
    </body>
    </html>
    """;

    @GetMapping("/")
    public @ResponseBody String index() {
        return String.format(HTML_TEMPLATE, "");
    }

    @PostMapping("/calculate")
    public @ResponseBody String calculateCGPA(
            @RequestParam double subject1,
            @RequestParam double subject2,
            @RequestParam double subject3,
            @RequestParam double subject4) {

        double cgpa = (subject1 + subject2 + subject3 + subject4) / 4.0;
        String result = String.format("Your CGPA is: %.2f", cgpa);
        return String.format(HTML_TEMPLATE, result);
    }
}
