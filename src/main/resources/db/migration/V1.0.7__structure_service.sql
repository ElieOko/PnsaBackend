SET search_path TO public;

CREATE TABLE IF NOT EXISTS public.accounts(
   id  BIGSERIAL PRIMARY KEY,
   name BIGINT,
   is_active BOOLEAN
);

CREATE TABLE IF NOT EXISTS public.services(
    id  BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    is_active BOOLEAN
);

CREATE TABLE IF NOT EXISTS public.refresh_tokens(
  id BIGSERIAL PRIMARY KEY,
  user_id BIGINT NOT NULL,
  expires_at TIMESTAMP,
  hasked_token TEXT NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS public.structure_services
(
    id           BIGSERIAL PRIMARY KEY,
    structure_id BIGINT,
    service_id   BIGINT,
    CONSTRAINT fk_structure FOREIGN KEY (structure_id) REFERENCES public.structures(id) ON DELETE CASCADE,
    CONSTRAINT fk_service FOREIGN KEY (service_id) REFERENCES public.services(id) ON DELETE CASCADE
);