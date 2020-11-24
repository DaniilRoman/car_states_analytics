create extension "uuid-ossp";

insert into role values (uuid_generate_v4(), 'ROLE_USER', CURRENT_DATE, CURRENT_DATE),
                        (uuid_generate_v4(), 'ROLE_ADMIN', CURRENT_DATE, CURRENT_DATE);
