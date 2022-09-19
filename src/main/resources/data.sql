INSERT INTO CATEGORY (id, name)
VALUES ('4be5f5bf-8eb5-44ea-8eb5-a5e807856d09', 'Battle Royale'),
       ('e1eec954-6ef4-4926-8183-7575af189f2a', 'MMO RPG'),
       ('692fa28c-fafd-442b-8022-4ed792995053', 'MOBA');

INSERT INTO GAME (id, name, category)
VALUES ('413e2297-b84b-42ef-97ed-16a8a9d1d671', 'APEX Legends', '4be5f5bf-8eb5-44ea-8eb5-a5e807856d09'),
       ('b8160463-01a0-4c7a-bd46-5b3716dbe4c6', 'Guild Wars 2', 'e1eec954-6ef4-4926-8183-7575af189f2a'),
       ('3c13c533-fbac-4881-b94d-f95cb2ef16c8', 'League of Legends', '692fa28c-fafd-442b-8022-4ed792995053');

INSERT INTO MEMBER (id, username, password_hash, is_admin)
VALUES ('9135f12e-1b66-4ee6-bbae-df37303cc154', 'admin', '$2a$10$aDD6I9Ej5.W8busvlsdPx.JvMWyJX8cOeOfVb.3q73KH2swww/N9C', true); -- Password: password1234