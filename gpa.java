import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cgpa")
public class CgpaCalculatorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Serve the HTML form
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>CGPA Calculator</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <h1>CGPA Calculator</h1>");
        out.println("    <form action='/cgpa' method='post'>");
        out.println("        <div id='grades-container'>");
        out.println("            <div>");
        out.println("                <label for='grade1'>Grade 1:</label>");
        out.println("                <input type='number' step='0.01' name='grades' required>");
        out.println("                <label for='credit1'>Credit 1:</label>");
        out.println("                <input type='number' step='0.01' name='credits' required>");
        out.println("            </div>");
        out.println("        </div>");
        out.println("        <button type='button' onclick='addFields()'>Add More</button>");
        out.println("        <button type='submit'>Calculate CGPA</button>");
        out.println("    </form>");
        out.println("    <script>");
        out.println("        let counter = 1;");
        out.println("        function addFields() {");
        out.println("            counter++;");
        out.println("            const container = document.getElementById('grades-container');");
        out.println("            const div = document.createElement('div');");
        out.println("            div.innerHTML = `<label for='grade${counter}'>Grade ${counter}:</label>");
        out.println("            <input type='number' step='0.01' name='grades' required>");
        out.println("            <label for='credit${counter}'>Credit ${counter}:</label>");
        out.println("            <input type='number' step='0.01' name='credits' required>`;");
        out.println("            container.appendChild(div);");
        out.println("        }");
        out.println("    </script>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Get input parameters from request
        String[] grades = request.getParameterValues("grades");
        String[] credits = request.getParameterValues("credits");

        if (grades == null || credits == null || grades.length != credits.length) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input");
            return;
        }

        // Calculate CGPA
        double totalPoints = 0;
        double totalCredits = 0;

        for (int i = 0; i < grades.length; i++) {
            double grade = Double.parseDouble(grades[i]);
            double credit = Double.parseDouble(credits[i]);
            totalPoints += grade * credit;
            totalCredits += credit;
        }

        double cgpa = totalPoints / totalCredits;

        // Send response
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>CGPA Calculator Result</h1>");
        out.println("<p>Your CGPA is: " + String.format("%.2f", cgpa) + "</p>");
        out.println("<a href='/cgpa'>Calculate Again</a>");
        out.println("</body></html>");
    }
}
