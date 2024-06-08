-- liquibase formatted sql

-- changeset popova:create_image

CREATE TABLE image_entity
(
	id BIGINT PRIMARY KEY NOT NULL,
	"data"     bytea NULL,
	file_path VARCHAR NULL,
	file_size BIGINT NOT NULL,
	media_type VARCHAR NULL
);

-- changeset popova:create_users
CREATE TABLE users (
	id BIGINT PRIMARY KEY NOT NULL,
	first_name VARCHAR NULL,
	last_name VARCHAR NULL,
	login VARCHAR NULL,
	password VARCHAR NULL,
	phone VARCHAR NULL,
	role varchar(255) NOT NULL,
	image_id SERIAL,
	CONSTRAINT login_unique UNIQUE (login),
	CONSTRAINT role_check CHECK (((role)::text = ANY ((ARRAY['USER'::character varying, 'ADMIN'::character varying])::text[])))
);
CREATE TABLE ads (
	id BIGINT PRIMARY KEY NOT NULL,
	descriptions VARCHAR NULL,
	price SERIAL ,
	title VARCHAR NULL,
	image_id SERIAL,
	user_id SERIAL NOT NULL
);
CREATE TABLE comment (
	id BIGINT PRIMARY KEY NOT NULL,
	created_at TIMESTAMP NULL,
	text VARCHAR ,
	ads_id SERIAL NOT NULL,
	user_entity_id SERIAL NOT NULL
);
ALTER TABLE users ADD CONSTRAINT fk_users_image FOREIGN KEY (image_id) REFERENCES image_entity(id);
ALTER TABLE ads ADD CONSTRAINT fk_ads_image FOREIGN KEY (image_id) REFERENCES image_entity(id);
ALTER TABLE ads ADD CONSTRAINT fk_ads_users FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;
ALTER TABLE comment ADD CONSTRAINT fk_comment_ads FOREIGN KEY (ads_id) REFERENCES ads(id) ON DELETE CASCADE;
ALTER TABLE comment ADD CONSTRAINT fk_comment_users FOREIGN KEY (user_entity_id) REFERENCES users(id);