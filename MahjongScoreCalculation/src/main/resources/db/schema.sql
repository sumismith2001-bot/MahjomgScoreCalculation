DROP TABLE IF EXISTS OYA_SCORE;
DROP TABLE IF EXISTS KO_SCORE;
DROP TABLE IF EXISTS LIMIT_SCORE;


CREATE TABLE IF NOT EXISTS oya_score (
    id BIGSERIAL PRIMARY KEY,
    fu INT NOT NULL,
    han INT NOT NULL,
    ron INT,
    tsumo_child INT
);

CREATE TABLE IF NOT EXISTS ko_score (
    id BIGSERIAL PRIMARY KEY,
    fu INT NOT NULL,
    han INT NOT NULL,
    ron INT,
    tsumo_child INT,
    tsumo_parent INT
);

CREATE TABLE IF NOT EXISTS limit_score (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    child_ron INT,
    child_tsumo_child INT,
    child_tsumo_parent INT,
    parent_ron INT,
    parent_tsumo_child INT
);

INSERT INTO ko_score (fu, han, ron, tsumo_child, tsumo_parent) VALUES
(20, 2, NULL, 400, 700),
(20, 3, NULL, 700, 1300),
(20, 4, NULL, 300, 2600),

(25, 2, 1600, NULL, NULL),
(25, 3, 3200, 800, 1600),
(25, 4, 6400, 1600, 3200),

(30, 1, 1000, 300, 500),
(30, 2, 2000, 500, 1000),
(30, 3, 3900, 1000, 2000),
(30, 4, 7700, 2000, 3900),

(40, 1, 1300, 400, 700),
(40, 2, 2600, 700, 1300),
(40, 3, 5200, 1300, 2600),

(50, 1, 1600, 400, 800),
(50, 2, 3200, 800, 1600),
(50, 3, 6400, 1600, 3200),

(60, 1, 2000, 500, 1000),
(60, 2, 3900, 1000, 2000),
(60, 3, 7700, 2000, 3900),

(70, 1, 2300, 600, 1200),
(70, 2, 4500, 1200, 2300),

(80, 1, 2600, 700, 1300),
(80, 2, 5200, 1300, 2600),
(80, 3, 8000, 2000, 4000), -- 満貫

(90, 1, 2900, 800, 1500),
(90, 2, 5800, 1500, 2900),

(100, 1, 3200, 800, 1600),
(100, 2, 6400, 1600, 3200),

(110, 1, 3600, NULL, NULL),
(110, 2, 7100, 1800, 3600);

INSERT INTO oya_score (fu, han, ron, tsumo_child) VALUES
(20, 2, NULL, 700),
(20, 3, NULL, 1300),
(20, 4, NULL, 2600),

(25, 2, 2400, NULL),
(25, 3, 4800, 1600),
(25, 4, 9600, 3200),

(30, 1, 1500, 500),
(30, 2, 2900, 1000),
(30, 3, 5800, 2000),
(30, 4, 11600, 3900),

(40, 1, 2000, 700),
(40, 2, 3900, 1300),
(40, 3, 7700, 2600),

(50, 1, 2400, 800),
(50, 2, 4800, 1600),
(50, 3, 9600, 3200),

(60, 1, 2900, 1000),
(60, 2, 5800, 2000),
(60, 3, 11600, 3900),

(70, 1, 3400, 1200),
(70, 2, 6800, 2300),

(80, 1, 3900, 1300),
(80, 2, 7700, 2600),
(80, 3, 12000, 4000), -- 満貫

(90, 1, 4400, 1500),
(90, 2, 8700, 2900),

(100, 1, 4800, 1600),
(100, 2, 9600, 3200),

(110, 1, 5300, NULL),
(110, 2, 10600, 3600);

INSERT INTO limit_score
(name, child_ron, child_tsumo_child, child_tsumo_parent, parent_ron, parent_tsumo_child)
VALUES
('満貫', 8000, 2000, 4000, 12000, 4000),
('跳満', 12000, 3000, 6000, 18000, 6000),
('倍満', 16000, 4000, 8000, 24000, 8000),
('三倍満', 24000, 6000, 12000, 36000, 12000),
('役満', 32000, 8000, 16000, 48000, 16000);
