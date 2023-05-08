create database KNKRentCar;

use KNKRentCar;
drop database KNKRentCar;

select * from klientet;

CREATE TABLE makina (
  id_makine int NOT NULL,
  emri_makines varchar(50),
  tabelat_m varchar(50),
  cmimi_me_ac float NOT NULL,
  cmimi_pa_ac float NOT NULL,
  cmimi_me_ac_per_dite float NOT NULL,
  cmimi_pa_ac_per_dite float NOT NULL,
  gjendja_e_makines varchar(10) NOT NULL,
  PRIMARY KEY (id_makine)
);

CREATE TABLE klientet (
  klient_username varchar(50) NOT NULL,
  emri_i_klientit varchar(50) NOT NULL,
  telefoni_i_klientit varchar(15) NOT NULL,
  email_i_klientit varchar(25) NOT NULL,
  adresa_e_klientit varchar(50) NOT NULL,
  fjalekalimi_i_klientit varchar(20) NOT NULL,
  PRIMARY KEY (klient_username)
);

alter table klientet  add column gender varchar(20) not null;
alter table klientet change column gender gjinia varchar(20) not null;



CREATE TABLE klientmakina (
  id_makine int NOT NULL,
  klient_username varchar(50) NOT NULL,
  FOREIGN KEY (id_makine) REFERENCES makina(id_makine),
  FOREIGN KEY (klient_username) REFERENCES klientet(klient_username)
);

CREATE TABLE shofere (
  id_shofere int NOT NULL,
  emri_i_shofereve varchar(50) NOT NULL,
  nr_i_lejes_se_vozitjes varchar(50) NOT NULL,
  telefoni_i_shofereve varchar(15) NOT NULL,
  adresa_e_shofereve varchar(50) NOT NULL,
  gjinia_e_shofereve varchar(10) NOT NULL,
  klient_username varchar(50) NOT NULL,
  gjendja_e_shofereve varchar(10) NOT NULL,
  PRIMARY KEY (id_shofere),
  FOREIGN KEY (klient_username) REFERENCES klientet(klient_username)
);
CREATE TABLE komentet (
  emri varchar(20) NOT NULL,
  e_mail varchar(30) NOT NULL,
  mesazhi varchar(150) NOT NULL,
  PRIMARY KEY (e_mail)
);

CREATE TABLE makinat_e_marre_me_qira (
  id int  NOT NULL,
  klient_username varchar(50) NOT NULL,
  id_makine int NOT NULL,
  id_shofere int NOT NULL,
  data_e_rezervimit date NOT NULL,
  data_e_fillimit_te_qirase date NOT NULL,
  data_e_mbarimit_te_qirase date NOT NULL,
  cmimi_total float NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (klient_username) REFERENCES klientet(klient_username),
  FOREIGN KEY (id_makine) REFERENCES makina(id_makine),
  FOREIGN KEY (id_shofere) REFERENCES shofere(id_shofere)
);

show tables;

-- tabela per kategorite e makinave
CREATE TABLE kategori_makinash (
  id_kategori int NOT NULL,
  emri_kategori varchar(50),
  cmimi_ditor float NOT NULL,
  PRIMARY KEY (id_kategori)
);


-- tabela per pagesat e klienteve
CREATE TABLE pagesa (
  id_pagesa int NOT NULL,
  klient_username varchar(50) NOT NULL,
  data_e_pageses date NOT NULL,
  shuma float NOT NULL,
  PRIMARY KEY (id_pagesa),
  FOREIGN KEY (klient_username) REFERENCES klientet(klient_username)
);

-- tabela per qendrat e makinave
CREATE TABLE qendra_makinave (
  id_qendra int NOT NULL,
  emri_qendres varchar(50),
  adresa_qendres varchar(50),
  PRIMARY KEY (id_qendra)
);

