INSERT INTO USERS (name, lastname, email, password,username, age) VALUES ('admin', 'admin', 'admin@coore.co','$2a$10$JwR3miWLoNs3nfcPw4OChevwab4jEOAi3/6kYtNFEZ0SSi/vbCK9e', 'admin', 25);
INSERT INTO USERS (name, lastname, email, password,username, age) VALUES ('user', 'user', 'admin@coore.co','$2a$10$JwR3miWLoNs3nfcPw4OChevwab4jEOAi3/6kYtNFEZ0SSi/vbCK9e', 'user', 25);
--- password: 123456
INSERT INTO ROLES (name, permission, description) VALUES ('ROLE_ADMIN', 'ADMIN', 'ADMIN');
INSERT INTO ROLES (name, permission, description) VALUES ('ROLE_USER', 'user', 'user');

INSERT INTO USER_ROLES (user_id, role_id) VALUES (1, 1);
INSERT INTO USER_ROLES (user_id, role_id) VALUES (2, 2);


INSERT INTO Category (name, description) VALUES ('Fruit', 'Fruit');
INSERT INTO Category (name, description) VALUES ('Vegetable', 'Vegetable');
INSERT INTO Category (name, description) VALUES ('FOOD', 'FOOD');
INSERT INTO Category (name, description) VALUES ('DRINK', 'DRINK');

INSERT INTO PRODUCT (name, codigo, price) VALUES ('Apple', 'C1', 10);
INSERT INTO PRODUCT (name, codigo, price) VALUES ('Banana', 'C2', 20);
INSERT INTO PRODUCT (name, codigo, price) VALUES ('Orange', 'C3', 30);

INSERT INTO PRODUCT_CATEGORY (product_id, category_id) VALUES (1, 1);
INSERT INTO PRODUCT_CATEGORY (product_id, category_id) VALUES (2, 1);
INSERT INTO PRODUCT_CATEGORY (product_id, category_id) VALUES (3, 1);