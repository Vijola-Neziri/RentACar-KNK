    CREATE TABLE pagesa (
   id_pagesa int NOT NULL,
   klient_id int not null,
   data_e_pageses date NOT NULL,
   shuma float NOT NULL,
   PRIMARY KEY (id_pagesa),
   FOREIGN KEY (klient_id) REFERENCES klientet(klient_id ) on delete cascade

);
