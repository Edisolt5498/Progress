/*create table notes (
    id integer not null auto_increment,
    workspace_id integer,
    duration bigint not null,
    end_time bigint not null,
    start_time bigint not null,
    tag varchar(255),
    text varchar(255),
    primary key (id)
);
create table user_role (
    user_id integer not null,
    roles enum ('ADMIN','USER')
);
create table user_workspace (
    user_id integer not null,
    workspace_id integer not null
);
create table users (
    active bit not null,
    id integer not null auto_increment,
    activation_code varchar(255),
    email varchar(255),
    password varchar(255) not null,
    username varchar(255) not null,
    primary key (id)
);
create table workspaces (
    id integer not null auto_increment,
    name varchar(255),
    primary key (id)
);

alter table notes
    add constraint note_workspaces_fk
    foreign key (workspace_id) references workspaces (id);
alter table user_role
    add constraint user_role_users_fk
    foreign key (user_id) references users (id);
alter table user_workspace
    add constraint user_workspace_users_fk
    foreign key (user_id) references users (id);
alter table user_workspace
    add constraint user_workspace_workspaces_fk
    foreign key (workspace_id) references workspaces (id);*/