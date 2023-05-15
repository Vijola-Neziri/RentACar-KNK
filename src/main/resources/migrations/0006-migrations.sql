CREATE TABLE rent(
  id int  NOT NULL,
  klient_id int not null,
  makina_id int NOT NULL,
  data_e_rezervimit date NOT NULL,
  data_e_fillimit_te_qirase date NOT NULL,
  data_e_mbarimit_te_qirase date NOT NULL,
  cmimi_total float NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (makina_id) REFERENCES makina(makina_id) on delete cascade,
   FOREIGN KEY(klient_id) REFERENCES klientet(klient_id)
);
