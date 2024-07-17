drop table if exists products;
drop table if exists users;
create table users (id bigserial primary key, username varchar(255) unique);
create table products(id bigserial primary key, accnumber varchar(25), accrest numeric, prodtype varchar(30), userid bigint,
CONSTRAINT fk_userid
      FOREIGN KEY(userid)
        REFERENCES users(id)
);