CREATE TABLE my_db_progress.users (
  id int NOT NULL AUTO_INCREMENT,
  username varchar(255) NOT NULL,
  email varchar(255),
  password varchar(255) NOT NULL,
  active boolean,
  PRIMARY KEY (id)
);

CREATE TABLE my_db_progress.user_role (
  user_id int NOT NULL,
  roles varchar(255),
  PRIMARY KEY (user_id), 
  foreign key (user_id) references my_db_progress.users(id)
);

CREATE TABLE my_db_progress.workspaces (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(255),
  PRIMARY KEY (id)
);

CREATE TABLE my_db_progress.user_workspace (
  user_id int NOT NULL,
  workspace_id int NOT NULL,
  PRIMARY KEY (user_id, workspace_id),
  FOREIGN KEY (user_id) REFERENCES my_db_progress.users(id),
  FOREIGN KEY (workspace_id) REFERENCES my_db_progress.workspaces(id)
);