/*==============================================================*/
/* Table: Author                                                */
/*==============================================================*/
create table Author
(
   id                   integer not null,
   firstName            varchar(20),
   secondName           varchar(20),
   lastName             varchar(20),
   birthDate            date,
   deathDate            date,
   primary key (id)
);

/*==============================================================*/
/* Table: Basket                                                */
/*==============================================================*/
create table Basket
(
   id                   integer not null auto_increment,
   primary key (id)
);

/*==============================================================*/
/* Table: Book                                                  */
/*==============================================================*/
create table Book
(
   id                   integer not null,
   bookCode             varchar(23),
   year                 date,
   title                varchar(70),
   status               enum('available','booked','borrowed'),
   catalogId            integer,
   authorId             integer,
   basketId             integer,
   primary key (id)
);

/*==============================================================*/
/* Table: Catalog                                               */
/*==============================================================*/
create table Catalog
(
   id                   integer not null auto_increment,
   title                varchar(40),
   parentId             integer,
   primary key (id)
);

/*==============================================================*/
/* Table: Profile                                               */
/*==============================================================*/
create table Profile
(
   id                   integer not null,
   firstName            varchar(20),
   secondName           varchar(20),
   lastName             varchar(20),
   birthDate            timestamp,
   phoneNumber          varchar(20),
   address              varchar(50),
   email                varchar(30),
   userId               integer,
   primary key (id)
);

/*==============================================================*/
/* Table: Request                                               */
/*==============================================================*/
create table Request
(
   id                   integer not null,
   handOutDate          timestamp,
   handOverDate         timestamp,
   deadline             timestamp,
   userId               integer,
   bookId               integer,
   primary key (id)
);

/*==============================================================*/
/* Table: User                                                  */
/*==============================================================*/
create table User
(
   id                   integer not null,
   login                varchar(30),
   password             varchar(64),
   role                 enum('admin','user'),
   basketId             integer,
   primary key (id)
);

alter table Book add constraint catalog_book_fk foreign key (catalogId)
      references Catalog (id) on delete restrict on update restrict;

alter table Book add constraint author_book_fk foreign key (authorId)
      references Author (id) on delete restrict on update restrict;

alter table Book add constraint basket_book_fk foreign key (basketId)
      references Basket (id) on delete restrict on update restrict;
	  
alter table Catalog add constraint catalog_catalog_fk foreign key (parentId)
      references Catalog (id) on delete restrict on update restrict;

alter table Profile add constraint user_profile_fk foreign key (userId)
      references User (id) on delete restrict on update restrict;

alter table Request add constraint user_request_fk foreign key (userId)
      references User (id) on delete restrict on update restrict;

alter table Request add constraint book_request_fk foreign key (bookId)
      references Book (id) on delete restrict on update restrict;

alter table User add constraint basket_user_fk foreign key (basketId)
      references Basket (id) on delete restrict on update restrict;