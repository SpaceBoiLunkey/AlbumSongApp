CREATE TABLE "aws_s3".album_bucket
(
    id int PRIMARY KEY NOT NULL,
    album_name varchar(100) NOT NULL,
    band_name varchar(100) NOT NULL,
    created_at timestamp NOT NULL DEFAULT NOW(),
    is_deleted boolean NOT NULL DEFAULT FALSE
);

CREATE TABLE "aws_s3".album_object
(
    id int PRIMARY KEY NOT NULL,
    album_id int NOT NULL REFERENCES "aws_s3".album_bucket(id) ON DELETE CASCADE ON UPDATE CASCADE,
    song_name varchar(100) NOT NULL,
    created_at timestamp NOT NULL DEFAULT NOW(),
    is_deleted boolean NOT NULL DEFAULT FALSE
);

INSERT INTO "aws_s3".album_bucket
    (id, album_name, band_name, created_at, is_deleted)
    VALUES (1, 'Boston', 'Boston', DEFAULT, FALSE),
           (2, 'Don''t Look Back', 'Boston', DEFAULT, FALSE);

INSERT INTO "aws_s3".album_object
    (id, album_id, song_name, created_at, is_deleted)
    VALUES (1, 1, 'More Than a Feeling', DEFAULT, FALSE),
           (2, 1, 'Peace of Mind', DEFAULT, FALSE),
           (3, 1, 'Foreplay/Long Time', DEFAULT, FALSE),
           (4, 1, 'Rock & Roll Band', DEFAULT, FALSE),
           (5, 1, 'Smokin''', DEFAULT, FALSE),
           (6, 1, 'Hitch a Ride', DEFAULT, FALSE),
           (7, 1, 'Something About You', DEFAULT, FALSE),
           (8, 1, 'Let Me Take You Home Tonight', DEFAULT, FALSE),
           (9, 2, 'Don''t Look Back', DEFAULT, FALSE),
           (10, 2, 'The Journey', DEFAULT, FALSE),
           (11, 2, 'It''s Easy', DEFAULT, FALSE),
           (12, 2, 'A Man I''ll Never Be', DEFAULT, FALSE),
           (13, 2, 'Feelin'' Satisfied', DEFAULT, FALSE),
           (14, 2, 'Party', DEFAULT, FALSE),
           (15, 2, 'Used to Bad News', DEFAULT, FALSE),
           (16, 2, 'Don''t Be Afraid', DEFAULT, FALSE);