CREATE TABLE komentet (
  emri varchar(20) NOT NULL,
  e_mail varchar(30) NOT NULL,
  mesazhi varchar(150) NOT NULL,
  PRIMARY KEY (e_mail)
);

    CREATE TABLE kategori_makinash (
  id_kategori int NOT NULL,
  emri_kategori varchar(50),
  cmimi_ditor float NOT NULL,
  PRIMARY KEY (id_kategori)
);