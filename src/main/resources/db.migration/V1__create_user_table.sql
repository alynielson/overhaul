CREATE TABLE user (
  id SERIAL NOT NULL CONSTRAINT user_pk PRIMARY KEY,
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(40) NOT NULL,
  role VARCHAR(50) NOT NULL,
  created_by VARCHAR(255),
  created_date TIMESTAMP,
  last_modified_by VARCHAR(255),
  last_modified_date TIMESTAMP
)