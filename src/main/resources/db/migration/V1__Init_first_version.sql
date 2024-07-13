
CREATE SEQUENCE IF NOT EXISTS short_url_seq;

CREATE TABLE IF NOT EXISTS shorturl
(
    id integer DEFAULT nextval('short_url_seq') NOT NULL,
    url_id character varying(255) NOT NULL,
    long_url text NOT NULL,
    ttl bigint,
    created_on timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    constraint UK_url_id unique (url_id)
    );