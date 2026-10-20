SET search_path TO public;
CREATE TABLE IF NOT EXISTS public.chats
(
    id            BIGSERIAL PRIMARY KEY,
    user_id       BIGINT,
    user_young_id BIGINT,
    message       VARCHAR(255),
    path_private  VARCHAR(255),
    voice         VARCHAR(255),
    created       TIMESTAMP(6) WITHOUT TIME ZONE,
    updated       TIMESTAMP(6) WITHOUT TIME ZONE,
    is_deleted    BOOLEAN,
    CONSTRAINT fk_chat_user FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE,
    CONSTRAINT fk_chat_user_young FOREIGN KEY (user_young_id) REFERENCES public.user_young(id) ON DELETE CASCADE
);
