INSERT INTO USUARIO(comuna, `contraseña`, email, nombre_usuario, profile_photo, region, telefono, tipo) VALUES
('Chiguayante', '123456', 'admin@gmail.com', 'admin', null, 'MMM', 'no hay', 'admin'),
('Talcahuyano', '123456', 'usuario@gmail.com', 'usuario', null, 'MMM', 'no hay', 'usuario');

INSERT INTO BANNER(activo, descripcion, nombre) VALUES
(1, 'Regresa desde la estepa para repartir dolor', 'Estepas infinitas'),
(1, 'Desde la realeza al campo de batalla', 'Linaje real'),
(0, 'Banner vacío', 'Vacío');

INSERT INTO BANNER_ITEM(clase, nombre, probabilidad, rareza, tipo, activo) VALUES
('Mirmidón', 'Guy', 15.0, 'S', 'personaje', 1),
('Lord (Roy)', 'Roy', 15.0, 'S', 'personaje', 1),
('Mirmidón', 'SoldadoA', 100.0, 'A', 'personaje', 1),
('Mirmidón', 'SoldadoB', 100.0, 'B', 'personaje', 1);

INSERT INTO BANNER_ITEMS(banner_id, item_id) VALUES
(1, 1),
(1, 3),
(1, 4),
(2, 2),
(2, 3),
(2, 4);