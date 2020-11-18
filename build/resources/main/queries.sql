create table source_system (
 source_system_id integer PRIMARY key,
 source_app_name varchar (255) NOT NULL
);

create table systems (
endpoint_id integer PRIMARY key,
ssid integer not null,
FOREIGN key (ssid)
REFERENCES source_system (source_system_id),
credit_card_enable boolean NOT NULL,
credit_vidya_enable boolean NOT NULL
);

create table request_response (
ssid integer not null,
epid integer not null,
request request[],
reponse varchar (255) DEFAULT "empty",
PRIMARY key (ssid, epid),
FOREIGN key (ssid)
REFERENCES source_system (source_system_id),
FOREIGN key (epid)
REFERENCES systems (endpoint_id)
)

CREATE TYPE request AS ENUM ('CREDITVIDYA','CREDITCARD');

INSERT INTO request_response (ssid,epid,request,response) VALUES (1,1,'{"CREDITVIDYA", "CREDITCARD"}', "empty");

create table execution_audit (
ssid integer not null PRIMARY key,
request_payload integer not null,
emiApproved boolean default false
);

create table systems (
endpoint_id integer PRIMARY key,
cibil boolean NOT NULL,
experian boolean NOT NULL,
idm boolean NOT NULL,
nsdl boolean NOT NULL,
blaze boolean NOT NULL
);


drop table request_response;

create table source_endpoint (
ssid integer not null,
epid integer not null,
PRIMARY key (ssid, epid),
FOREIGN key (ssid)
REFERENCES source_system (source_system_id),
FOREIGN key (epid)
REFERENCES systems (endpoint_id)
);

create table execution_order(
ssid integer not null,
request request[],
PRIMARY KEY (ssid),
FOREIGN KEY (ssid)
REFERENCES source_system (source_system_id)
);
