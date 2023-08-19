--liquibase formatted sql

--changeset ivanchikov:1
CREATE INDEX student_name ON student(name);

--changeset ivanchikov:2
CREATE INDEX faculties_name_color ON faculty(name, color);