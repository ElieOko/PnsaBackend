SET search_path TO public;
CREATE TABLE IF NOT EXISTS public.address
(
    id           BIGSERIAL PRIMARY KEY,
    avenue       VARCHAR(255),
    full_address VARCHAR(255),
    structure_id BIGINT,
    is_active    BOOLEAN,
    CONSTRAINT fk_structure FOREIGN KEY (structure_id) REFERENCES public.structures(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS public.emails
(
    id           BIGSERIAL PRIMARY KEY,
    full_email   VARCHAR(255),
    structure_id BIGINT,
    is_active    BOOLEAN,
    CONSTRAINT fk_structure FOREIGN KEY (structure_id) REFERENCES public.structures(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS public.phone_numbers
(
    id                BIGSERIAL PRIMARY KEY,
    full_phone_number VARCHAR(255),
    structure_id      BIGINT,
    is_active         BOOLEAN
    );