SET search_path TO public;
CREATE TABLE IF NOT EXISTS public.responsibilities
(
    id           BIGSERIAL PRIMARY KEY,
    full_name    VARCHAR(255),
    functions    VARCHAR(255),
    structure_id BIGINT,
    is_active    BOOLEAN,
    CONSTRAINT fk_structure FOREIGN KEY (structure_id) REFERENCES public.structures(id) ON DELETE CASCADE
);