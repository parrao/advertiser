CREATE TABLE IF NOT EXISTS advertiser  (
  id IDENTITY NOT NULL AUTO_INCREMENT  PRIMARY KEY
  ,contact_name VARCHAR(255) NOT NULL
  ,credit_limit DECIMAL
);

insert into advertiser(contact_name,credit_limit) values('Parthi',10000.00);
insert into advertiser(contact_name,credit_limit) values('Frank',15000.00);