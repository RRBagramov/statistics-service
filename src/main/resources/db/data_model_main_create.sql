CREATE TABLE visits
(
  id      BIGSERIAL               NOT NULL
    CONSTRAINT visits_pkey
    PRIMARY KEY,
  user_id INTEGER                 NOT NULL,
  url     VARCHAR(150)            NOT NULL,
  date    TIMESTAMP DEFAULT now() NOT NULL
);

CREATE UNIQUE INDEX visits_id_uindex
  ON visits (id);

