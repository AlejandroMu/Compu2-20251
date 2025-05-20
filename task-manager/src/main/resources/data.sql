INSERT INTO users (username, password, email, first_name, last_name, phone_number, address, is_active)
VALUES ('jdoe', '$2a$10$Xyfe1NDvBXTIgUn5na7TruVM7NqU3okrr4zfGh3AU5M56ljW/85iK', 'jdoe@example.com', 'John', 'Doe', '1234567890', '123 Main St', true);

INSERT INTO users (username, password, email, first_name, last_name, phone_number, address, is_active)
VALUES ('asmith', '$2a$10$Xyfe1NDvBXTIgUn5na7TruVM7NqU3okrr4zfGh3AU5M56ljW/85iK', 'asmith@example.com', 'Alice', 'Smith', '9876543210', '456 Oak Ave', false);


INSERT INTO permission (name, description) VALUES ('CREATE_TASK', 'Permite crear una nueva tarea');
INSERT INTO permission (name, description) VALUES ('UPDATE_TASK', 'Permite actualizar una tarea existente');
INSERT INTO permission (name, description) VALUES ('DELETE_TASK', 'Permite eliminar una tarea');
INSERT INTO permission (name, description) VALUES ('VIEW_TASK', 'Permite ver los detalles de una tarea');

INSERT INTO role (name, description) VALUES ('ADMIN', 'Administrador con acceso total');
INSERT INTO role (name, description) VALUES ('USER', 'Usuario básico que gestiona sus tareas');


INSERT INTO role_permission (role_id, permission_id) VALUES (1, 1);
INSERT INTO role_permission (role_id, permission_id) VALUES (1, 2);
INSERT INTO role_permission (role_id, permission_id) VALUES (1, 3);
INSERT INTO role_permission (role_id, permission_id) VALUES (1, 4);

INSERT INTO role_permission (role_id, permission_id) VALUES (2, 4);

INSERT INTO user_role (role_id, user_id) VALUES (1, 1);
INSERT INTO user_role (role_id, user_id) VALUES (2, 2);

-- Tareas para To Do = 1)
INSERT INTO task (name, description, notes, priority )
VALUES ('Planificar sprint', 'Organizar tareas del próximo sprint', 'Reunión con el equipo',  1);

INSERT INTO task (name, description, notes, priority)
VALUES ('Diseñar wireframes', 'Bocetos para la nueva interfaz', 'Enviar a revisión con UX', 2);

-- Tareas para Doing = 2)
INSERT INTO task (name, description, notes, priority)
VALUES ('Implementar login', 'Funcionalidad de autenticación', 'Falta revisar validaciones', 1);

INSERT INTO task (name, description, notes, priority)
VALUES ('Base de datos', 'Diseño de relaciones para las entidades principales', 'Coordinar con backend',  3);

-- Tareas para Done = 3)
INSERT INTO task (name, description, notes, priority)
VALUES ('Configurar CI/CD', 'Pipeline automatizado en Jenkins', 'Merge a main completo', 2);

INSERT INTO task (name, description, notes, priority)
VALUES ('Documentación inicial', 'README y estructura de carpetas', 'Subido a GitHub', 4);

-- Tarea 1 asignada a usuarios 1 y 2
INSERT INTO user_task (user_id, task_id) VALUES (1, 1);
INSERT INTO user_task (user_id, task_id) VALUES (2, 1);

-- Tarea 2 solo asignada al usuario 1
INSERT INTO user_task (user_id, task_id) VALUES (1, 2);

-- Tarea 3 solo asignada al usuario 2
INSERT INTO user_task (user_id, task_id) VALUES (2, 3);

-- Tarea 4 asignada a ambos
INSERT INTO user_task (user_id, task_id) VALUES (1, 4);
INSERT INTO user_task (user_id, task_id) VALUES (2, 4);

-- Tarea 5 solo asignada a usuario 1
INSERT INTO user_task (user_id, task_id) VALUES (1, 5);

-- Tarea 6 solo asignada a usuario 2
INSERT INTO user_task (user_id, task_id) VALUES (2, 6);