DROP TABLE IF EXISTS lottery CASCADE;
DROP TABLE IF EXISTS user_ticket CASCADE;

CREATE TABLE users (
    id VARCHAR(10) PRIMARY KEY NOT NULL
);

CREATE TABLE lottery (
    id VARCHAR(6) PRIMARY KEY NOT NULL,
    price DECIMAL,
    amount INTEGER
);
CREATE TABLE user_ticket (
    user_id VARCHAR(10) Not Null,
    lottery_id VARCHAR(6)  NOT NULL,
    lottery_amount INTEGER,
    cost DECIMAL,
    PRIMARY KEY (user_id, lottery_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (lottery_id) REFERENCES lottery(id)


);
INSERT INTO users(id) VALUES ('1111111111');
INSERT INTO lottery(id, price, amount) VALUES ('010101', 100.0, 1);
INSERT INTO user_ticket(user_id, lottery_id, lottery_amount, cost)  VALUES ('1111111111', '010101', 1, 100.0);
