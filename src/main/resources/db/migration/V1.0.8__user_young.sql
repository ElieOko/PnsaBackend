SET search_path TO public;
CREATE TABLE IF NOT EXISTS public.user_young
(
    id           BIGSERIAL PRIMARY KEY,
    phone_number VARCHAR(255),
    is_lock      BOOLEAN,
    is_active    BOOLEAN
);