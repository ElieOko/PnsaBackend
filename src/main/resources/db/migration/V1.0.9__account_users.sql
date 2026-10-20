SET search_path TO public;
CREATE TABLE IF NOT EXISTS public.account_users
(
    id         BIGSERIAL PRIMARY KEY,
    account_id BIGINT,
    user_id    BIGINT,
    is_active  BOOLEAN,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE,
    CONSTRAINT fk_account FOREIGN KEY (account_id) REFERENCES public.accounts(id) ON DELETE CASCADE
);