SET search_path TO public;
CREATE TABLE IF NOT EXISTS public.agendas
(
    id           BIGSERIAL PRIMARY KEY,
    day_id       BIGINT,
    structure_id BIGINT,
    hour_start   VARCHAR(255),
    hour_end     VARCHAR(255),
    is_active    BOOLEAN,
    CONSTRAINT fk_structure FOREIGN KEY (structure_id) REFERENCES public.structures(id) ON DELETE CASCADE,
    CONSTRAINT fk_day FOREIGN KEY (day_id) REFERENCES public.days(id) ON DELETE CASCADE
);