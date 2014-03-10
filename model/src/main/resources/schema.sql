CREATE TABLE PROJECT_STATE(
    ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,
    NAME VARCHAR(20) NOT NULL);

CREATE TABLE ADDRESS(
    ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,
    STREET VARCHAR(100) NOT NULL,
    HOUSE_NUMBER VARCHAR(10),
    ZIP VARCHAR(10) NOT NULL,
    CITY CHAR(50),
    PROVINCE VARCHAR(50),
    COUNTRY VARCHAR(50) NOT NULL);

CREATE TABLE PROJECT(
    ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,
    PROJECT_NUMBER VARCHAR(50) NOT NULL,
    PROJECT_NAME VARCHAR(100) NOT NULL,
    BEGIN_DATE DATE NOT NULL,
    END_DATE DATE NOT NULL,
    BUDGET_IN_HOURS NUMERIC(10,2) NOT NULL,
    DESCRIPTION VARCHAR(200),
    PROJECT_STATE_ID INTEGER NOT NULL,
    CONSTRAINT UQ_PROJECT_1 UNIQUE(PROJECT_NUMBER),
    CONSTRAINT FK_PROJECT_1 FOREIGN KEY(PROJECT_STATE_ID) REFERENCES PROJECT_STATE(ID));

CREATE TABLE PROJECT_ACTIVITY(
    ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,
    PROJECT_ACTIVITY_NAME VARCHAR(100) NOT NULL,
    PROJECT_ID INTEGER NOT NULL,
    CONSTRAINT UQ_PROJECT_ACTIVITY_1 UNIQUE(PROJECT_ACTIVITY_NAME,PROJECT_ID),
    CONSTRAINT FK_PROJECT_ACTIVITY_1 FOREIGN KEY(PROJECT_ID) REFERENCES PROJECT(ID));

CREATE TABLE CONTACT_PERSON(
    ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,
    TITLE VARCHAR(10),
    NAME VARCHAR(50) NOT NULL,
    SNAME VARCHAR(50) NOT NULL,
    TEL VARCHAR(20),
    MOBILE VARCHAR(20),
    FAX VARCHAR(20),
    EMAIL VARCHAR(50),
    ADDRESS_ID INTEGER NOT NULL,
    CONSTRAINT FK_CONTACT_PERSON_1 FOREIGN KEY(ADDRESS_ID) REFERENCES ADDRESS(ID),
    CONSTRAINT UQ_CONTACT_PERSON_1 UNIQUE(NAME,SNAME,ID));

CREATE TABLE "USER"(
    ID INTEGER NOT NULL PRIMARY KEY,
    LOGIN VARCHAR(8) NOT NULL,
    PASSWORD VARCHAR(8) NOT NULL,
    ADMIN BOOLEAN NOT NULL,
    CONSTRAINT UQ_USER_1 UNIQUE(LOGIN),
    CONSTRAINT FK_USER_1 FOREIGN KEY(ID) REFERENCES CONTACT_PERSON(ID));

CREATE TABLE WORK_DAY(
    ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,
    DAY_DATE DATE NOT NULL,
    BEGIN_TIME TIME NOT NULL,
    END_TIME TIME NOT NULL,
    USER_ID INTEGER NOT NULL,
    CONSTRAINT UQ_WORK_DAY_1 UNIQUE(DAY_DATE,USER_ID),
    CONSTRAINT FK_WORK_DAY_1 FOREIGN KEY(USER_ID) REFERENCES "USER"(ID));

CREATE TABLE USER_PROJECT_ACTIVITY_MAPPING(
    ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,
    PROJECT_ACTIVITY_ID INTEGER NOT NULL,
    USER_ID INTEGER NOT NULL,
    BUDGET_IN_HOURS NUMERIC(10,2) DEFAULT 1.0 NOT NULL,
    CONSTRAINT UQ_USER_PROJECT_ACTIVITY_MAPPING_1 UNIQUE(PROJECT_ACTIVITY_ID,USER_ID),
    CONSTRAINT FK_USER_PROJECT_ACTIVITY_MAPPING_1 FOREIGN KEY(USER_ID) REFERENCES "USER"(ID),
    CONSTRAINT FK_USER_PROJECT_ACTIVITY_MAPPING_2 FOREIGN KEY(PROJECT_ACTIVITY_ID) REFERENCES PROJECT_ACTIVITY(ID));

CREATE TABLE WORK_DAY_TIME_RECORD(
    ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,
    BEGIN_TIME TIME,
    END_TIME TIME,
    AMOUNT_OF_HOURS DECIMAL(4,2) NOT NULL,
    DAY_ID INTEGER NOT NULL,
    USER_PROJECT_ACTIVITY_ID INTEGER NOT NULL,
    COMMENT VARCHAR(100),
    CONSTRAINT UQ_WORK_DAY_TIME_RECORD_1 UNIQUE(DAY_ID,USER_PROJECT_ACTIVITY_ID,BEGIN_TIME,END_TIME),
    CONSTRAINT FK_WORK_DAY_TIME_RECORD_1 FOREIGN KEY(DAY_ID) REFERENCES WORK_DAY(ID),
    CONSTRAINT FK_WORK_DAY_TIME_RECORD_2 FOREIGN KEY(USER_PROJECT_ACTIVITY_ID) REFERENCES USER_PROJECT_ACTIVITY_MAPPING(ID));

CREATE TABLE USER_PROJECT_ACTIVITY_PRICE(
    ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,
    UPA_ID INTEGER NOT NULL,
    BEGIN_PERIOD DATE NOT NULL,
    END_PERIOD DATE NOT NULL,
    PRICE DECIMAL(5,2) NOT NULL,
    LIST_CNT INTEGER NOT NULL,
    CONSTRAINT UQ_UPAP_PERIOD_1 UNIQUE(UPA_ID,BEGIN_PERIOD,END_PERIOD),
    CONSTRAINT FK_UPAP_1 FOREIGN KEY(UPA_ID) REFERENCES USER_PROJECT_ACTIVITY_MAPPING(ID));

CREATE TABLE CUSTOMER(
    ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,
    NAME VARCHAR(50) NOT NULL,
    EXT_NAME VARCHAR(25),
    ADDRESS_ID INTEGER NOT NULL,
    CONSTRAINT UQ_CUSTOMER_1 UNIQUE(NAME,EXT_NAME),
    CONSTRAINT FK_CUSTOMER_1 FOREIGN KEY(ADDRESS_ID) REFERENCES ADDRESS(ID));

CREATE TABLE CUSTOMER_CONTACT(
    ID INTEGER NOT NULL PRIMARY KEY,
    CUSTOMER_ID INTEGER NOT NULL,
    CONSTRAINT FK_CUSTOMER_CONTACT_1 FOREIGN KEY(ID) REFERENCES CONTACT_PERSON(ID),
    CONSTRAINT FK_CUSTOMER_CONTACT_2 FOREIGN KEY(CUSTOMER_ID) REFERENCES CUSTOMER(ID));

CREATE TABLE CONTRACT(
    ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,
    CONTRACT_NUMBER VARCHAR(10) NOT NULL,
    PROJECT_ID INTEGER NOT NULL,
    USER_ID INTEGER NOT NULL,
    OBSOLETE BOOLEAN DEFAULT FALSE NOT NULL,
    CUSTOMER_CONTACT_ID INTEGER NOT NULL,
    CONSTRAINT UQ_CONTRACT_1 UNIQUE(CONTRACT_NUMBER),
    CONSTRAINT FK_CONTRACT_1 FOREIGN KEY(PROJECT_ID) REFERENCES PROJECT(ID),
    CONSTRAINT FK_CONTRACT_2 FOREIGN KEY(USER_ID) REFERENCES "USER"(ID),
    CONSTRAINT FK_CONTRACT_3 FOREIGN KEY(CUSTOMER_CONTACT_ID) REFERENCES CUSTOMER_CONTACT(ID));

CREATE TABLE VAT(
    ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,
    BEGIN_PERIOD DATE NOT NULL,
    END_PERIOD DATE NOT NULL,
    PRICE DECIMAL(5,2) NOT NULL,
    CONSTRAINT UQ_VAT_PERIOD_1 UNIQUE(BEGIN_PERIOD,END_PERIOD));

CREATE TABLE CONFIG(
    ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,
    SCALE INTEGER NOT NULL,
    CONSTRAINT UQ_CONFIG_1 UNIQUE(SCALE));

CREATE TABLE VERSIONINFO(
    ID INTEGER GENERATED BY DEFAULT  AS IDENTITY(START WITH 0) NOT NULL PRIMARY KEY,
    VERSION VARCHAR(10) NOT NULL,
    INFO VARCHAR(100) NOT NULL,
    CONSTRAINT UQ_VERSIONINFO_1 UNIQUE(VERSION));