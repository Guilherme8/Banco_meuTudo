INSERT INTO USUARIO(nome, email, senha) VALUES('Guilherme', 'guilherme@email.com', '$2a$10$37VHYVQGEcxaHctX6Xol7OsfX0hmp5K81xgj595G/RNKE9bbbRSxS');
INSERT INTO USUARIO(nome, email, senha) VALUES('Debora', 'debora@email.com', '$2a$10$B1xk0yYaORGByunseIPxmuS6tv23kxzGJoH6aN3PD4RLd7RfcZX8a');

INSERT INTO CONTA(count_number, balance, usuario_id) VALUES(123, 6000.00, 1);
INSERT INTO CONTA(count_number, balance, usuario_id) VALUES(124, 10000.00, 2);

INSERT INTO TRANSACTION(dt_scheduling, balance, installments) VALUES('2022-05-05', 1000.00, 1);

INSERT INTO TRANSACTION_CONTA(conta_id, TRANSACTION_ID) VALUES(1, (select id from TRANSACTION where dt_scheduling = '2022-05-05'));
INSERT INTO TRANSACTION_CONTA(conta_id, TRANSACTION_ID) VALUES(2, (select id from TRANSACTION where dt_scheduling = '2022-05-05'));
