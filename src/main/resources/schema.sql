CREATE TABLE user_
(
    id            BIGSERIAL PRIMARY KEY,
    name          VARCHAR(200),
    mail          VARCHAR(255)            NOT NULL,
    password      VARCHAR(60)             NOT NULL,
    creation_date TIMESTAMP DEFAULT now() NOT NULL
);

CREATE UNIQUE INDEX users_unique_email_idx ON user_ (mail);

CREATE TABLE role_
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(120)
);

CREATE UNIQUE INDEX users_role_idx ON role_ (name);

CREATE TABLE privilege_
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(120)
);

CREATE TABLE user_role
(
    user_id BIGSERIAL NOT NULL,
    role_id BIGSERIAL NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_ (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role_ (id) ON DELETE CASCADE
);

CREATE TABLE role_privilege
(
    role_id      BIGSERIAL NOT NULL,
    privilege_id BIGSERIAL NOT NULL,
    FOREIGN KEY (role_id) REFERENCES role_ (id) ON DELETE CASCADE,
    FOREIGN KEY (privilege_id) REFERENCES privilege_ (id)
);

CREATE TABLE IF NOT EXISTS restaurant
(
    id      BIGSERIAL PRIMARY KEY,
    name    varchar(255) NOT NULL,
    phone   varchar(255) NOT NULL,
    address varchar(255) NOT NULL,
    info    varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS menu_category
(
    id            BIGSERIAL PRIMARY KEY,
    name          varchar(255) NOT NULL,
    restaurant_id BIGSERIAL    NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS dish
(
    id               BIGSERIAL PRIMARY KEY,
    restaurant_id    BIGSERIAL    NOT NULL,
    menu_category_id BIGSERIAL    NOT NULL,
    name             varchar(255) NOT NULL,
    cost             decimal      NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE,
    FOREIGN KEY (menu_category_id) REFERENCES menu_category (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS order_
(
    id      BIGSERIAL PRIMARY KEY,
    date    TIMESTAMP DEFAULT now() NOT NULL,
    cost    decimal                 NOT NULL,
    status  serial                  NOT NULL,
    user_id bigserial               not null,
    foreign key (user_id) references user_ (id) on delete cascade
);

create table if not exists order_dish
(
    order_id bigserial not null,
    dish_id  bigserial not null,
    foreign key (order_id) references order_ (id) on delete cascade,
    foreign key (dish_id) references dish (id) on delete cascade
);

CREATE TABLE IF NOT EXISTS restaurant_table
(
    id            BIGSERIAL PRIMARY KEY,
    restaurant_id bigserial,
    number        serial  NOT NULL,
    seats         serial  NOT NULL,
    free          boolean NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS reservation
(
    id      bigserial primary key,
    date    timestamp DEFAULT now() not null,
    guests  serial                  not null,
    status  serial                  not null,
    user_id bigserial               not null,
    foreign key (user_id) references user_ (id) on delete cascade
);

create table if not exists restaurant_table_reservation
(
    restaurant_table_id bigserial not null,
    reservation_id      bigserial not null,
    foreign key (restaurant_table_id) references restaurant_table (id) on delete cascade,
    foreign key (reservation_id) references reservation (id) on delete cascade
);

create table if not exists payment
(
    id       bigserial primary key,
    date     timestamp default now() not null,
    cost     decimal                 not null,
    order_id bigserial               not null,
    foreign key (order_id) references order_ (id) on delete cascade
);
