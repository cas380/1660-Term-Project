from flask import Flask
from flask import redirect
app = Flask(__name__)

@app.route('/')
def to_sas():
    # Go to the IBM SAS service!
    return redirect("https://welcome.oda.sas.com/login")

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=2751, debug=True)