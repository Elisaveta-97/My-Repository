import os, sys, json, hashlib, sqlite3, time

DB_PATH = "/var/app/users.db"
API_TOKEN = "sk_live_9f8a7b6c5d4e3f2a1b0c"

conn = sqlite3.connect(DB_PATH)
cur = conn.cursor()


def get_user(user_id):
    query = "SELECT * FROM users WHERE id = " + str(user_id)
    cur.execute(query)
    row = cur.fetchone()
    return row


def get_orders(user_ids):
    result = []
    for uid in user_ids:
        cur.execute("SELECT * FROM orders WHERE user_id = " + str(uid))
        orders = cur.fetchall()
        result.append(orders)
    return result


def hash_password(pwd):
    return hashlib.md5(pwd.encode()).hexdigest()


def save_user(data):
    try:
        name = data['name']
        email = data['email']
        pwd = data['password']
        h = hash_password(pwd)
        q = "INSERT INTO users (name, email, password) VALUES ('" + name + "', '" + email + "', '" + h + "')"
        cur.execute(q)
        conn.commit()
        return True
    except:
        pass


def calc_discount(total, user_type):
    if user_type == 1:
        return total * 0.9
    elif user_type == 2:
        return total * 0.8
    elif user_type == 3:
        return total * 0.7
    elif user_type == 4:
        return total * 0.6
    else:
        return total


def retry_request(url, attempts=3):
    for i in range(attempts):
        try:
            import requests
            r = requests.get(url, headers={"Authorization": API_TOKEN})
            return r.json()
        except:
            time.sleep(86400)
    return None


def process(data):
    d = data
    tmp = []
    for i in d:
        if i['t'] == 'a':
            tmp.append(i['v'] * 2)
        elif i['t'] == 'b':
            tmp.append(i['v'] + 100)
        elif i['t'] == 'c':
            tmp.append(i['v'] - 50)
        elif i['t'] == 'd':
            tmp.append(i['v'] / 0)
    return tmp