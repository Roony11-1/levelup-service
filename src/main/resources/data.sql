-- =====================
-- REGIONES
-- =====================
INSERT INTO region (id, region) VALUES (1, 'Región Metropolitana');

-- =====================
-- COMUNAS
-- =====================
INSERT INTO comuna (id, nombre, region_id) VALUES (1, 'Santiago Centro', 1);
INSERT INTO comuna (id, nombre, region_id) VALUES (2, 'Las Condes', 1);
INSERT INTO comuna (id, nombre, region_id) VALUES (3, 'Providencia', 1);

-- =====================
-- USUARIO
-- =====================
INSERT INTO usuario (id, nombre_usuario, email, contraseña, telefono, comuna_id, tipo, profile_photo)
VALUES (1, 'ricardosanchez', 'eltheasasin@gmail.com', '123456', '+56912345678', 1, 'admin', null);
