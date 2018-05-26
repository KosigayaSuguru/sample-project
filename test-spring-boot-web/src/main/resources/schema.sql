drop table if exists test3;

CREATE TABLE test3
(
    id INT,
    test2_id INT,
    enumtest ENUM('enum1','enum2','enum3'),
    update_date TIMESTAMP
);

INSERT INTO test3
VALUES
    (1, 20, 'enum1', CURRENT_TIMESTAMP());
