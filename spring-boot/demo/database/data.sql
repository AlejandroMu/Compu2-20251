-- Generado por Oracle SQL Developer Data Modeler 23.1.0.087.0806
--   en:        2025-03-13 13:02:19 COT
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g



DROP TABLE categoria CASCADE CONSTRAINTS;

DROP TABLE compra CASCADE CONSTRAINTS;

DROP TABLE inventario_entrada CASCADE CONSTRAINTS;

DROP TABLE inventario_salida CASCADE CONSTRAINTS;

DROP TABLE permiso CASCADE CONSTRAINTS;

DROP TABLE permisos CASCADE CONSTRAINTS;

DROP TABLE permisov1 CASCADE CONSTRAINTS;

DROP TABLE precio CASCADE CONSTRAINTS;

DROP TABLE prod_categoria CASCADE CONSTRAINTS;

DROP TABLE producto CASCADE CONSTRAINTS;

DROP TABLE role CASCADE CONSTRAINTS;

DROP TABLE roles CASCADE CONSTRAINTS;

DROP TABLE usuario CASCADE CONSTRAINTS;

DROP TABLE venta CASCADE CONSTRAINTS;

-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE categoria (
    id           INTEGER NOT NULL 
--  ERROR: Datatype UNKNOWN is not allowed 
    ,
    name         VARCHAR2 
--  ERROR: VARCHAR2 size not specified 
    ,
    descripcion  VARCHAR2 
--  ERROR: VARCHAR2 size not specified 
    ,
    categoria_id NUMBER NOT NULL
);

ALTER TABLE categoria ADD CONSTRAINT categoria_pk PRIMARY KEY ( categoria_id );

CREATE TABLE compra (
    id          INTEGER NOT NULL,
    proveedor   VARCHAR2 
--  ERROR: VARCHAR2 size not specified 
    ,
    cod_factura VARCHAR2 
--  ERROR: VARCHAR2 size not specified 
    ,
    usuario_id  INTEGER NOT NULL
);

ALTER TABLE compra ADD CONSTRAINT compra_pk PRIMARY KEY ( id );

CREATE TABLE inventario_entrada (
    producto_id INTEGER NOT NULL,
    compra_id   INTEGER NOT NULL,
    cantidad    INTEGER,
    precio_unit NUMBER
);

ALTER TABLE inventario_entrada ADD CONSTRAINT inventario_pk PRIMARY KEY ( producto_id,
                                                                          compra_id );

CREATE TABLE inventario_salida (
    producto_id INTEGER NOT NULL,
    venta_id    INTEGER NOT NULL,
    cantidad    INTEGER,
    precio_unit NUMBER
);

ALTER TABLE inventario_salida ADD CONSTRAINT inventariov1_pk PRIMARY KEY ( producto_id,
                                                                           venta_id );

CREATE TABLE permiso (
    id   INTEGER NOT NULL,
    name VARCHAR2 
--  ERROR: VARCHAR2 size not specified 

);

ALTER TABLE permiso ADD CONSTRAINT permiso_pk PRIMARY KEY ( id );

CREATE TABLE permisos (
    role_id    INTEGER NOT NULL,
    permiso_id INTEGER NOT NULL
);

ALTER TABLE permisos ADD CONSTRAINT permisos_pk PRIMARY KEY ( role_id,
                                                              permiso_id );

CREATE TABLE permisov1 (
    id   INTEGER NOT NULL,
    name VARCHAR2 
--  ERROR: VARCHAR2 size not specified 

);

ALTER TABLE permisov1 ADD CONSTRAINT permisov1_pk PRIMARY KEY ( id );

CREATE TABLE precio (
    id          INTEGER NOT NULL,
    value       NUMBER,
    "date"      DATE,
    producto_id INTEGER NOT NULL,
    nota        VARCHAR2 
--  ERROR: VARCHAR2 size not specified 

);

ALTER TABLE precio ADD CONSTRAINT permisov2_pk PRIMARY KEY ( id );

CREATE TABLE prod_categoria (
    producto_id            INTEGER NOT NULL,
    categoria_categoria_id NUMBER NOT NULL
);

ALTER TABLE prod_categoria ADD CONSTRAINT prod_categoria_pk PRIMARY KEY ( producto_id,
                                                                          categoria_categoria_id );

CREATE TABLE producto (
    id     INTEGER NOT NULL,
    name   VARCHAR2 
--  ERROR: VARCHAR2 size not specified 
    ,
    codigo VARCHAR2 
--  ERROR: VARCHAR2 size not specified 

);

ALTER TABLE producto ADD CONSTRAINT producto_pk PRIMARY KEY ( id );

CREATE TABLE role (
    id   INTEGER NOT NULL,
    name VARCHAR2 
--  ERROR: VARCHAR2 size not specified 

);

ALTER TABLE role ADD CONSTRAINT role_pk PRIMARY KEY ( id );

CREATE TABLE roles (
    usuario_id INTEGER NOT NULL,
    role_id    INTEGER NOT NULL
);

ALTER TABLE roles ADD CONSTRAINT roles_pk PRIMARY KEY ( usuario_id,
                                                        role_id );

CREATE TABLE usuario (
    id             INTEGER NOT NULL,
    nombre         VARCHAR2 
--  ERROR: VARCHAR2 size not specified 
    ,
    apellido       VARCHAR2 
--  ERROR: VARCHAR2 size not specified 
    ,
    password       VARCHAR2 
--  ERROR: VARCHAR2 size not specified 
    ,
    username       VARCHAR2 
--  ERROR: VARCHAR2 size not specified 
    ,
    fecha_registro DATE,
    activo         CHAR(1)
);

ALTER TABLE usuario ADD CONSTRAINT usuario_pk PRIMARY KEY ( id );

CREATE TABLE venta (
    id          INTEGER NOT NULL,
    fecha_venta DATE,
    total       NUMBER,
    usuario_id  INTEGER NOT NULL
);

ALTER TABLE venta ADD CONSTRAINT venta_pk PRIMARY KEY ( id );

ALTER TABLE compra
    ADD CONSTRAINT compra_usuario_fk FOREIGN KEY ( usuario_id )
        REFERENCES usuario ( id );

ALTER TABLE inventario_entrada
    ADD CONSTRAINT inventario_compra_fk FOREIGN KEY ( compra_id )
        REFERENCES compra ( id );

ALTER TABLE inventario_entrada
    ADD CONSTRAINT inventario_producto_fk FOREIGN KEY ( producto_id )
        REFERENCES producto ( id );

ALTER TABLE inventario_salida
    ADD CONSTRAINT inventariov1_producto_fk FOREIGN KEY ( producto_id )
        REFERENCES producto ( id );

ALTER TABLE inventario_salida
    ADD CONSTRAINT inventariov1_venta_fk FOREIGN KEY ( venta_id )
        REFERENCES venta ( id );

ALTER TABLE permisos
    ADD CONSTRAINT permisos_permiso_fk FOREIGN KEY ( permiso_id )
        REFERENCES permiso ( id );

ALTER TABLE permisos
    ADD CONSTRAINT permisos_role_fk FOREIGN KEY ( role_id )
        REFERENCES role ( id );

ALTER TABLE precio
    ADD CONSTRAINT precio_producto_fk FOREIGN KEY ( producto_id )
        REFERENCES producto ( id );

ALTER TABLE prod_categoria
    ADD CONSTRAINT prod_categoria_categoria_fk FOREIGN KEY ( categoria_categoria_id )
        REFERENCES categoria ( categoria_id );

ALTER TABLE prod_categoria
    ADD CONSTRAINT prod_categoria_producto_fk FOREIGN KEY ( producto_id )
        REFERENCES producto ( id );

ALTER TABLE roles
    ADD CONSTRAINT roles_role_fk FOREIGN KEY ( role_id )
        REFERENCES role ( id );

ALTER TABLE roles
    ADD CONSTRAINT roles_usuario_fk FOREIGN KEY ( usuario_id )
        REFERENCES usuario ( id );

ALTER TABLE venta
    ADD CONSTRAINT venta_usuario_fk FOREIGN KEY ( usuario_id )
        REFERENCES usuario ( id );

CREATE SEQUENCE categoria_categoria_id_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER categoria_categoria_id_trg BEFORE
    INSERT ON categoria
    FOR EACH ROW
    WHEN ( new.categoria_id IS NULL )
BEGIN
    :new.categoria_id := categoria_categoria_id_seq.nextval;
END;
/



-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            14
-- CREATE INDEX                             0
-- ALTER TABLE                             27
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           1
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          1
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                  15
-- WARNINGS                                 0
