SET search_path TO public;
CREATE TABLE IF NOT EXISTS public.users
(
    id  BIGSERIAL PRIMARY KEY,
    phone_number  VARCHAR(255),
    email         VARCHAR(255),
    password      VARCHAR(255),
    password_text VARCHAR(255),
    is_lock       BOOLEAN,
    is_active     BOOLEAN
    );
CREATE TABLE IF NOT EXISTS public.days
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255)
);
