SET search_path TO public;
CREATE TABLE IF NOT EXISTS public.structure_types(
    id    BIGSERIAL PRIMARY KEY,
    name  VARCHAR(255),
    is_active BOOLEAN
    );

CREATE TABLE IF NOT EXISTS public.structures
(
    id                BIGSERIAL PRIMARY KEY,
    user_id           BIGINT,
    structure_type_id BIGINT,
    province_id       BIGINT,
    name              VARCHAR(255),
    description       VARCHAR(255),
    is_active         BOOLEAN,
    latitude          DOUBLE PRECISION,
    longitude         DOUBLE PRECISION,
    created_date      TIMESTAMP(6) WITHOUT TIME ZONE,
    updated_date      TIMESTAMP(6) WITHOUT TIME ZONE,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE,
    CONSTRAINT fk_structure_type FOREIGN KEY (structure_type_id) REFERENCES public.structure_types(id) ON DELETE CASCADE
);