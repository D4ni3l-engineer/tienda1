INSERT INTO rol(nombre) VALUES ('ROLE_ADMIN');
INSERT INTO rol(nombre) VALUES ('ROLE_VENDEDOR');
INSERT INTO rol(nombre) VALUES ('ROLE_ALMACENERO');

INSERT INTO usuario(id_rol, nombre, apellidop, apellidom, correo, contrasenia) VALUES (1,'admin','admin','admin','admin@gmail.com','$2a$12$cABBHTUUiowB2H6os1zaF.3KqJ1yet.J1oozN4rL9/wLe6UeRPmRm');
INSERT INTO usuario(id_rol, nombre, apellidop, apellidom, correo, contrasenia) VALUES (2,'vendedor','vendedor','vendedor','vendedor@gmail.com','$2a$12$cABBHTUUiowB2H6os1zaF.3KqJ1yet.J1oozN4rL9/wLe6UeRPmRm');