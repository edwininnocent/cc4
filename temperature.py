from flask import Flask, request, render_template_string
import os

app = Flask(__name__)

# Define temperature conversion functions
def celsius_to_fahrenheit(celsius):
    return (celsius * 9/5) + 32

def fahrenheit_to_celsius(fahrenheit):
    return (fahrenheit - 32) * 5/9

# Define the HTML template as a string
HTML_TEMPLATE = """
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Temperature Converter</title>
</head>
<body>
    <h1>Temperature Converter</h1>
    <form method="post">
        <label for="temp">Enter temperature:</label>
        <input type="number" id="temp" name="temp" step="any" required><br><br>
        
        <label for="scale">Select scale to convert from:</label>
        <select id="scale" name="scale" required>
            <option value="Celsius">Celsius</option>
            <option value="Fahrenheit">Fahrenheit</option>
        </select><br><br>
        
        <input type="submit" value="Convert">
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
        temp = request.form.get('temp', type=float)
        scale = request.form.get('scale')

        if scale == 'Celsius':
            converted_temp = celsius_to_fahrenheit(temp)
            result = f"{temp} °C = {converted_temp:.2f} °F"
        elif scale == 'Fahrenheit':
            converted_temp = fahrenheit_to_celsius(temp)
            result = f"{temp} °F = {converted_temp:.2f} °C"
        else:
            result = "Invalid scale selected."

    return render_template_string(HTML_TEMPLATE, result=result)

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=int(os.getenv('PORT', 8080)))
