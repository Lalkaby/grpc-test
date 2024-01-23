create table custom_user(
    id uuid  NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    username text NOT NULL UNIQUE,
    password text NOT NULL,
    email text,
    roles text[]);