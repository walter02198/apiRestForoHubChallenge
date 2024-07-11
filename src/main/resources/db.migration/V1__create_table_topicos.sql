# noinspection SqlNoDataSourceInspection
create table topicos(

                        id bigint not null auto_increment,
                        titulo varchar(250) not null,
                        mensaje varchar(500) not null unique,
                        fechaCreacion datetime not null,
                        status varchar(100) not null,
                        autor varchar(100) not null,
                        curso varchar(100) not null,

                        primary key (id)



);