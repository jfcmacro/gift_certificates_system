DROP TABLE IF EXISTS gift_certificate;
CREATE TABLE gift_certificate (
       gift_id		      int not null,
       name		      varchar(30),
       description 	      varchar(50),
       price		      money,
       duration		      int,
       create_date	      timestamp,
       last_update_date	      timestamp,
       PRIMARY KEY(gift_id)
);
DROP TABLE IF EXISTS tag;
CREATE TABLE tag (
       tag_id		      int not null,
       name		      varchar(30),
       PRIMARY KEY(tag_id) 
);
DROP TABLE IF EXISTS gift_tag;
CREATE TABLE gift_tag (
       gift_id		      int not null,
       tag_id		      int not null,
       PRIMARY KEY(gift_id, tag_id),
       FOREIGN KEY(gift_id) REFERENCES gift_certificate(gift_id),
       FOREIGN KEY(tag_id)  REFERENCES tag(tag_id)
);
