-- create user, role and permission
CREATE TABLE PERMISSION (
   ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
   NAME VARCHAR(255),
   PRIMARY KEY (ID)
);

ALTER TABLE PERMISSION ADD CONSTRAINT PERMISSION_NAME UNIQUE(NAME);

CREATE TABLE ROLE (
   ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
   NAME VARCHAR(255),
   PRIMARY KEY (ID)
);

ALTER TABLE ROLE ADD CONSTRAINT ROLE_NAME UNIQUE(NAME);

CREATE TABLE ROLES_PERMISSIONS (
   ROLE_ID BIGINT NOT NULL,
   PERMISSION_ID BIGINT NOT NULL,
   PRIMARY KEY (ROLE_ID, PERMISSION_ID)
);

ALTER TABLE ROLES_PERMISSIONS ADD CONSTRAINT ROLES_PERMISSIONS_PERMISSION
   FOREIGN KEY (PERMISSION_ID) REFERENCES PERMISSION;

ALTER TABLE ROLES_PERMISSIONS ADD CONSTRAINT ROLES_PERMISSIONS_ROLE
   FOREIGN KEY (ROLE_ID) REFERENCES ROLE;

CREATE TABLE USER_ (
  ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
  PASSWORD VARCHAR(255),
  USER_NAME VARCHAR(255),
  ACCOUNT_EXPIRED BOOLEAN,
  ACCOUNT_LOCKED BOOLEAN,
  CREDENTIALS_EXPIRED BOOLEAN,
  ENABLED BOOLEAN,
  PRIMARY KEY (ID)
);

ALTER TABLE USER_ ADD CONSTRAINT USER_USER_NAME UNIQUE(USER_NAME);

CREATE TABLE USERS_ROLES (
   USER_ID BIGINT NOT NULL,
   ROLE_ID BIGINT NOT NULL,
   PRIMARY KEY (USER_ID, ROLE_ID)
);

ALTER TABLE USERS_ROLES ADD CONSTRAINT USERS_ROLES_ROLE
   FOREIGN KEY (ROLE_ID) REFERENCES ROLE;

ALTER TABLE USERS_ROLES ADD CONSTRAINT USERS_ROLES_USER_
   FOREIGN KEY (USER_ID) REFERENCES USER_;

-- create oauth2
CREATE TABLE OAUTH_CLIENT_DETAILS (
  CLIENT_ID VARCHAR(255) PRIMARY KEY,
  RESOURCE_IDS VARCHAR(255),
  CLIENT_SECRET VARCHAR(255),
  SCOPE VARCHAR(255),
  AUTHORIZED_GRANT_TYPES VARCHAR(255),
  WEB_SERVER_REDIRECT_URI VARCHAR(255),
  AUTHORITIES VARCHAR(255),
  ACCESS_TOKEN_VALIDITY INTEGER,
  REFRESH_TOKEN_VALIDITY INTEGER,
  ADDITIONAL_INFORMATION VARCHAR(4096),
  AUTOAPPROVE VARCHAR(255)
);

CREATE TABLE OAUTH_CLIENT_TOKEN (
  TOKEN_ID VARCHAR(255),
  TOKEN VARBINARY,
  AUTHENTICATION_ID VARCHAR(255) PRIMARY KEY,
  USER_NAME VARCHAR(255),
  CLIENT_ID VARCHAR(255)
);

CREATE TABLE OAUTH_ACCESS_TOKEN (
  TOKEN_ID VARCHAR(255),
  TOKEN VARBINARY,
  AUTHENTICATION_ID VARCHAR(255) PRIMARY KEY,
  USER_NAME VARCHAR(255),
  CLIENT_ID VARCHAR(255),
  AUTHENTICATION VARBINARY,
  REFRESH_TOKEN VARCHAR(255)
);

CREATE TABLE OAUTH_REFRESH_TOKEN (
  TOKEN_ID VARCHAR(255),
  TOKEN VARBINARY,
  AUTHENTICATION VARBINARY
);

CREATE TABLE OAUTH_CODE (
  CODE VARCHAR(255),
  AUTHENTICATION VARBINARY
);

CREATE TABLE OAUTH_APPROVALS (
  USERID VARCHAR(255),
  CLIENTID VARCHAR(255),
  SCOPE VARCHAR(255),
  STATUS VARCHAR(10),
  EXPIRESAT TIMESTAMP,
  LASTMODIFIEDAT TIMESTAMP
);