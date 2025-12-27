

DELETE FROM teachers;
INSERT INTO teachers (firstName, lastName, email, age, academicDegree, isCoordinator, passwordHash, gender, version)
VALUES
    ('Ricardo', 'Oliveira', 'ricardo.history@edu.com', 45, 'History', true, 'history123', 'Male', 0),
    ('Beatriz', 'Costa', 'beatriz.eng@edu.com', 32, 'English', false, 'english456', 'Female', 0),
    ('Nuno', 'Mendes', 'nuno.geo@edu.com', 38, 'Geography', false, 'geo789', 'Male', 0),
    ('Sofia', 'Martins', 'sofia.history@edu.com', 50, 'History', true, 'past321', 'Female', 0),
    ('Tiago', 'Silva', 'tiago.eng@edu.com', 29, 'English', false, 'words654', 'Male', 0);

DELETE FROM students;
INSERT INTO students (firstName, lastName, email, schoolYear, age, parentName, guardianAge, parentEmail, passwordHash, gender, version)
VALUES
    ('Alice', 'Smith', 'alice.smith@edu.com', 10, 15, 'Robert Smith', 45, 'robert.smith@provider.com', 'hash_pw_1', 'Female',0),
    ('Bruno', 'Fernandes', 'bruno.f@edu.com', 12, 17, 'Maria Fernandes', 40, 'm.fernandes@provider.com', 'hash_pw_2', 'Male',0),
    ('Carla', 'Santos', 'carla.s@edu.com', 9, 14, 'Joao Santos', 50, 'j.santos@provider.com', 'hash_pw_3', 'Female',0),
    ('David', 'Mendes', 'david.m@edu.com', 11, 16, 'Ana Mendes', 38, 'a.mendes@provider.com', 'hash_pw_4', 'Male',0),
    ('Elena', 'Rodriguez', 'elena.r@edu.com', 10, 15, 'Sofia Rodriguez', 42, 's.rodriguez@provider.com', 'hash_pw_5', 'Female',0);

DELETE FROM subjects;
INSERT INTO subjects (name, description, year, module, version)
VALUES
    ('History', 'Ancient civilizations and modern global history', 2025, 1, 0),
    ('English', 'Literature, grammar, and academic writing', 2025, 1, 0),
    ('Geography', 'Physical geography and geopolitical studies', 2025, 1, 0),
    ('History', 'History of European art and culture', 2025, 2, 0),
    ('English', 'Advanced linguistics and communication', 2025, 2, 0);


DELETE FROM appointments;
INSERT INTO appointments (startDate, endDate, status, teacher_fk, subject_fk, version)
VALUES ('2025-01-10T09:00:00', '2025-01-10T12:00:00', 'Done', 1, 1, 0),
       ('2025-01-10T14:00:00', '2025-01-10T16:00:00', 'Done', 2, 2, 0),
       ('2025-01-11T09:00:00', '2025-01-11T11:00:00', 'Pending', 3, 3, 0),
       ('2025-01-11T14:00:00', '2025-01-11T17:00:00', 'Pending', 4, 1, 0),
       ('2025-01-12T10:00:00', '2025-01-12T12:00:00', 'Cancelled', 1, 2, 0);







