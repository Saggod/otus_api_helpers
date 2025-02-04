CREATE TABLE IF NOT EXISTS users
(id INTEGER,
name VARCHAR(255),
age INTEGER,
cource VARCHAR(255),
email VARCHAR(255)
);

INSERT INTO users (id, name, age, cource, email) VALUES
(1, 'Name_1', 28, 'QA1', 'test1@test.test'),
(2, 'Name_1', 28, 'QA-PRO', 'test2@test.test'),
(3, 'Dev_3', 30, 'Data Science', 'test3@test.test');