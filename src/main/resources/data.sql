INSERT INTO CATEGORY (id, name)
VALUES (1, 'Battle Royale'),
       (2, 'MMO RPG'),
       (3, 'MOBA');

INSERT INTO GAME (id, name, category)
VALUES (1, 'APEX Legends', 1),
       (2, 'Guild Wars 2', 2),
       (3, 'League of Legends', 3);

INSERT INTO MEMBER (id, username, password_hash, is_admin)
VALUES ('9135f12e-1b66-4ee6-bbae-df37303cc154', 'admin', '$2a$10$aDD6I9Ej5.W8busvlsdPx.JvMWyJX8cOeOfVb.3q73KH2swww/N9C',
        true);