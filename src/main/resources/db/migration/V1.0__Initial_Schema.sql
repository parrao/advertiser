CREATE TABLE IF NOT EXISTS advertiser  (
  id BIGINT NOT NULL AUTO_INCREMENT  PRIMARY KEY
  ,contact_name VARCHAR(255) NOT NULL
  ,credit_limit BIGINT
);

insert into advertiser(contact_name,credit_limit) values('Parthi',10000);
insert into advertiser(contact_name,credit_limit) values('Frank',15000);