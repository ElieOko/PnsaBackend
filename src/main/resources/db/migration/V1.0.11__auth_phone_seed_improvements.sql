SET search_path TO public;

ALTER TABLE public.users
    ADD COLUMN IF NOT EXISTS must_change_password BOOLEAN DEFAULT TRUE;

ALTER TABLE public.user_young
    ADD COLUMN IF NOT EXISTS password VARCHAR(255),
    ADD COLUMN IF NOT EXISTS must_change_password BOOLEAN DEFAULT TRUE;

ALTER TABLE public.accounts
    ALTER COLUMN name TYPE VARCHAR(255) USING name::text;

UPDATE public.users
SET must_change_password = COALESCE(must_change_password, TRUE);

UPDATE public.user_young
SET must_change_password = COALESCE(must_change_password, TRUE);

CREATE UNIQUE INDEX IF NOT EXISTS uk_users_phone_number
    ON public.users (phone_number);

CREATE UNIQUE INDEX IF NOT EXISTS uk_user_young_phone_number
    ON public.user_young (phone_number);

CREATE TABLE IF NOT EXISTS public.provinces
(
    id        BIGSERIAL PRIMARY KEY,
    name      VARCHAR(255),
    is_active BOOLEAN DEFAULT TRUE
);
