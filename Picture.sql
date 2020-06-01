DROP TABLE "pictures" CASCADE;

CREATE TABLE "pictures" (
  id bigserial NOT NULL,
  name character varying(200),
  author character varying(200),
  year integer,
  material character varying(200),
  height numeric,
  width numeric,
  CONSTRAINT "pictures_pk" PRIMARY KEY (id)
);

INSERT INTO pictures(name, author, year, material, height, width) values ('Мона Лиза', 'Леонардо да Винчи', 1503, 'Маслянные краски', 77, 53);
INSERT INTO pictures(name, author, year, material, height, width) values ('Звездная ночь', 'Винсент Ван Гог', 1889, 'Маслянные краски', 73.7, 92.1);
INSERT INTO pictures(name, author, year, material, height, width) values ('Тайная вечеря', 'Леонардо да Винчи', 1495, 'Темпера', 460, 880);
INSERT INTO pictures(name, author, year, material, height, width) values ('Черный квадрат', 'Казимир Малевич', 1915, 'Маслянные краски', 80, 80);
INSERT INTO pictures(name, author, year, material, height, width) values ('Рождение Венеры', 'Сандро Боттичелли', 1484, 'Темпера', 172.5, 278.5);
INSERT INTO pictures(name, author, year, material, height, width) values ('Утро в сосновом лесу', 'Иван Иванович Шишкин', 1889, 'Маслянные краски', 139, 213);
INSERT INTO pictures(name, author, year, material, height, width) values ('Постоянство памяти', 'Сальвадор Дали', 1931, 'Гобелен ручной работы', 24, 33);
INSERT INTO pictures(name, author, year, material, height, width) values ('Девочка на шаре', 'Пабло Пикассо', 1905, 'Маслянные краски', 147, 95);
INSERT INTO pictures(name, author, year, material, height, width) values ('Девятый вал', 'Иван Константинович Айвазовский', 1850, 'Маслянные краски', 221, 332);
