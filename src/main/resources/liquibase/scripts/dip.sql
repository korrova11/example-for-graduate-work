-- liquibase formatted sql

-- changeset popova:create_bd

CREATE TABLE image_entity (
	id SERIAL PRIMARY KEY NOT NULL,
	data OID NULL,
	file_path VARCHAR NULL,
	file_size BIG SERIAL NOT NULL,
	media_type VARCHAR NULL,

);
CREATE TABLE users (
	id SERIAL PRIMARY KEY NOT NULL,
	first_name VARCHAR NULL,
	last_name VARCHAR NULL,
	login VARCHAR NULL,
	password VARCHAR NULL,
	phone VARCHAR NULL,
	role SMALL SERIAL,
	image_id SERIAL NULL,
	CONSTRAINT login_unique UNIQUE (login),
	CONSTRAINT role_check CHECK (((role)::text = ANY ((ARRAY['USER'::character varying, 'ADMIN'::character varying])::text[])))
);
CREATE TABLE ads (
	id SERIAL PRIMARY KEY NOT NULL,
	descriptions VARCHAR NULL,
	price SERIAL NULL,
	title VARCHAR NULL,
	image_id SERIAL NULL,
	user_id SERIAL NULL,

);
CREATE TABLE comment (
	id SERIAL PRIMARY KEY NOT NULL,
	created_at TIMESTAMP NULL,
	text VARCHAR NULL,
	ads_id SERIAL NULL,
	user_entity_id SERIAL NULL,

);
ALTER TABLE users ADD CONSTRAINT fk_users_image FOREIGN KEY (image_id) REFERENCES image_entity(id);
ALTER TABLE ads ADD CONSTRAINT fk_ads_image FOREIGN KEY (image_id) REFERENCES image_entity(id);
ALTER TABLE ads ADD CONSTRAINT fk_ads_users FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;
ALTER TABLE comment ADD CONSTRAINT fk_comment_ads FOREIGN KEY (ads_id) REFERENCES ads(id) ON DELETE CASCADE;
ALTER TABLE comment ADD CONSTRAINT fk_comment_users FOREIGN KEY (user_entity_id) REFERENCES users(id);