INSERT INTO USERS (name, lastname, email, password,username, age) VALUES ('admin', 'admin', 'admin@coore.co''123456', 'admin', 25);

INSERT INTO ROLES (name, permission, description) VALUES ('ROLE_ADMIN', 'ADMIN', 'ADMIN');

INSERT INTO USER_ROLES (user_id, role_id) VALUES (1, 1);

INSERT INTO Category (name, description) VALUES (1, 'Fruit', 'Fruit');
INSERT INTO Category (name, description) VALUES (2, 'Vegetable', 'Vegetable');
INSERT INTO Category (name, description) VALUES (3, 'FOOD', 'FOOD');
INSERT INTO Category (name, description) VALUES (4, 'DRINK', 'DRINK');