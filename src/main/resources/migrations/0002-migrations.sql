create table User(
    User_ID INT NOT NULL AUTO_INCREMENT,
	user_username VARCHAR(50) NOT NULL,
    emri_user VARCHAR(50) NOT NULL,
    telefoni_user VARCHAR(15) NOT NULL,
    adresa_user VARCHAR(50) NOT NULL,
	Salted_Password VARCHAR(256),
    Salt VARCHAR(44) NOT NULL,
    gjinia VARCHAR(50),
    PRIMARY KEY (User_ID)
    );
