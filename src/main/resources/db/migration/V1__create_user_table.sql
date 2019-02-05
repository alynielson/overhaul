CREATE TABLE authority (
  id SERIAL NOT NULL CONSTRAINT authority_pk PRIMARY KEY,
  description VARCHAR(64) NOT NULL);

CREATE TABLE app_user (
 id SERIAL NOT NULL CONSTRAINT app_user_pk PRIMARY KEY,
 username VARCHAR(64) NOT NULL,
 first_name VARCHAR(64) NOT NULL,
 last_name VARCHAR(64) NOT NULL,
 email VARCHAR(64) NOT NULL,
 password VARCHAR(64) NOT NULL,
 account_non_expired BOOLEAN,
 account_non_locked BOOLEAN,
 credentials_non_expired BOOLEAN,
 enabled BOOLEAN
);

CREATE TABLE app_user_authority (
  app_user_id INTEGER NOT NULL,
  authority_id INTEGER NOT NULL,
  CONSTRAINT app_user_authority_pk PRIMARY KEY (app_user_id, authority_id),
  FOREIGN KEY (app_user_id) REFERENCES app_user (id),
  FOREIGN KEY (authority_id) REFERENCES authority (id)
);

INSERT INTO authority (description) values ('Admin');
INSERT INTO authority (description) values ('User');

INSERT INTO app_user (email,first_name, last_name,password,username, account_non_expired, account_non_locked, credentials_non_expired, enabled)
values ('admin@gmail.com','admin', 'admin','$2a$10$TyJG5isp/OyhtgshD5Wbfudx7wTb6HKzPUTJU8IhgJRj733Xrr9Wm','admin', true, true, true, true);

insert into app_user_authority (app_user_id, authority_id)
values ((SELECT id FROM app_user WHERE email = 'admin@gmail.com'), (SELECT id FROM authority WHERE description = 'Admin'));