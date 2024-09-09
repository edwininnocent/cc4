from flask import Flask, request, render_template
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
    
    return render_template('index.html', result=result)

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=int(os.getenv('PORT', 8080)))


Flask==2.3.2
gunicorn==20.1.0

