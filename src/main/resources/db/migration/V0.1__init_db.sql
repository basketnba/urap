CREATE TABLE "user"
(
    id            UUID DEFAULT uuid_generate_v4() NOT NULL,
    name          VARCHAR(255)                    NOT NULL,
    date_of_birth TIMESTAMP WITHOUT TIME ZONE     NOT NULL,
    email         VARCHAR(255)                    NOT NULL,
    user_status   VARCHAR(64)                     NOT NULL,
    created_at    TIMESTAMP WITHOUT TIME ZONE     NOT NULL,
    created_by    VARCHAR(255)                    NOT NULL,
    updated_at    TIMESTAMP WITHOUT TIME ZONE     NOT NULL,
    updated_by    VARCHAR(255)                    NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);