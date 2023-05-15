   CREATE TABLE Admins (
    Admin_emri VARCHAR(50),
    telefoni_admin VARCHAR(50),
	adresa_admin VARCHAR(50) NOT NULL,
    Admin_ID INT NOT NULL,
    PRIMARY KEY (Admin_ID),
    FOREIGN KEY (Admin_ID) REFERENCES User(User_ID) ON DELETE CASCADE
);

create table klientet(
klient_id int not null,
emri_klient varchar(50),
mbimeri_klient varchar(50),
gjinia varchar(50),
makina_id int ,
brand_makina varchar(50),
model_makina varchar(50),
total int not null,
date_rented date,
date_returned date,
PRIMARY KEY (klient_id),
FOREIGN KEY (klient_id) REFERENCES User(User_ID) ON DELETE CASCADE,
foreign key(makina_id) references makina(makina_id) ON DELETE CASCADE
);
