from flask import Flask, request, render_template_string
import os

app = Flask(__name__)

# Define arithmetic functions
def add(x, y):
    return x + y

def subtract(x, y):
    return x - y

def multiply(x, y):
    return x * y

def divide(x, y):
    if y == 0:
        return "Error: Division by zero is undefined."
    return x / y

# Define the HTML template as a string
HTML_TEMPLATE = """
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Simple Calculator</title>
</head>
<body>
    <h1>Simple Calculator</h1>
    <form method="post">
        <label for="num1">Enter first number:</label>
        <input type="number" id="num1" name="num1" step="any" required><br><br>
        
        <label for="num2">Enter second number:</label>
        <input type="number" id="num2" name="num2" step="any" required><br><br>
        
        <label for="operation">Select operation:</label>
        <select id="operation" name="operation" required>
            <option value="1">Add</option>
            <option value="2">Subtract</option>
            <option value="3">Multiply</option>
            <option value="4">Divide</option>
        </select><br><br>
        
        <input type="submit" value="Calculate">
    </form>

    {% if result %}
        <h2>Result:</h2>
        <p>{{ result }}</p>
    {% endif %}
</body>
</html>
"""

@app.route('/', methods=['GET', 'POST'])
def index():
    result = None
    if request.method == 'POST':
        num1 = request.form.get('num1', type=float)
        num2 = request.form.get('num2', type=float)
        operation = request.form.get('operation')

        if operation == '1':
            result = f"{num1} + {num2} = {add(num1, num2)}"
        elif operation == '2':
            result = f"{num1} - {num2} = {subtract(num1, num2)}"
        elif operation == '3':
            result = f"{num1} * {num2} = {multiply(num1, num2)}"
        elif operation == '4':
            result = f"{num1} / {num2} = {divide(num1, num2)}"
        else:
            result = "Invalid operation selected."
    
    return render_template_string(HTML_TEMPLATE, result=result)

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=int(os.getenv('PORT', 8080)))
