DROP TABLE IF EXISTS PERSONA;
CREATE TABLE persona (
  id INT PRIMARY KEY AUTO_INCREMENT,
  usuario VARCHAR(10) NOT NULL CHECK (LENGTH(usuario) >= 6),
  password VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  surname VARCHAR(255),
  company_email VARCHAR(255) NOT NULL,
  personal_email VARCHAR(255) NOT NULL,
  city VARCHAR(255) NOT NULL,
  active BOOLEAN NOT NULL,
  created_date DATE,
  imagen_url VARCHAR(255),
  termination_date DATE
);