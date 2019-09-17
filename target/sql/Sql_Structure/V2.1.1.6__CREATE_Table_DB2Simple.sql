CREATE TABLE ACT
      (ACTNO    SMALLINT       NOT NULL,
       ACTKWD   CHAR(6)        NOT NULL,
       ACTDESC  VARCHAR(20)    NOT NULL,
       PRIMARY KEY (ACTNO)             );

CREATE TABLE  EMP_PHOTO_RESUME
               (EMPNO      CHAR(06) NOT NULL,
                EMP_ROWID  CHAR(06) NOT NULL ,
                PSEG_PHOTO BLOB(500K),
                BMP_PHOTO  BLOB(100K),
                RESUME        CLOB(5K),
                PRIMARY KEY  (EMPNO));

CREATE TABLE DEPT
      (DEPTNO    CHAR(3)           NOT NULL,
       DEPTNAME  VARCHAR(36)       NOT NULL,
       MGRNO     CHAR(6)                   ,
       ADMRDEPT  CHAR(3)           NOT NULL,
       LOCATION  CHAR(16)                  ,
       PRIMARY KEY (DEPTNO)                );

CREATE TABLE EMP
      (EMPNO     CHAR(6)                          NOT NULL,
       FIRSTNME  VARCHAR(12)                      NOT NULL,
       MIDINIT   CHAR(1)                          NOT NULL,
       LASTNAME  VARCHAR(15)                      NOT NULL,
       WORKDEPT  CHAR(3)                                  ,
       PHONENO   CHAR(4)         CONSTRAINT NUMBER CHECK
                 (PHONENO >= '0000' AND
                  PHONENO <= '9999')                      ,
       HIREDATE  DATE                                     ,
       JOB       CHAR(8)                                  ,
       EDLEVEL   SMALLINT                                 ,
       SEX       CHAR(1)                                  ,
       BIRTHDATE DATE                                     ,
       SALARY    DECIMAL(9,2)                             ,
       BONUS     DECIMAL(9,2)                             ,
       COMM      DECIMAL(9,2)                             ,
       PRIMARY KEY (EMPNO)                                ,
       FOREIGN KEY RED (WORKDEPT) REFERENCES DEPT
                 ON DELETE SET NULL                       );
CREATE TABLE PROJ
               (PROJNO   CHAR(6) PRIMARY KEY NOT NULL,
                PROJNAME VARCHAR(24)    NOT NULL WITH DEFAULT
                  'PROJECT NAME UNDEFINED',
                DEPTNO   CHAR(3)        NOT NULL REFERENCES
                  DEPT ON DELETE RESTRICT,
                RESPEMP  CHAR(6)        NOT NULL REFERENCES
                  EMP ON DELETE RESTRICT,
                PRSTAFF  DECIMAL(5, 2)          ,
                PRSTDATE DATE                   ,
                PRENDATE DATE                   ,
                MAJPROJ  CHAR(6));

CREATE TABLE PROJACT
      (PROJNO    CHAR(6)                         NOT NULL,
       ACTNO     SMALLINT                        NOT NULL,
       ACSTAFF   DECIMAL(5,2)                            ,
       ACSTDATE  DATE                            NOT NULL,
       ACENDATE  DATE                                    ,
       PRIMARY KEY (PROJNO, ACTNO, ACSTDATE),
       FOREIGN KEY RPAP (PROJNO) REFERENCES PROJ
                                       ON DELETE RESTRICT,
       FOREIGN KEY RPAA (ACTNO) REFERENCES ACT
                                       ON DELETE RESTRICT);

CREATE TABLE EMPPROJACT
      (EMPNO     CHAR(6)                         NOT NULL,
       PROJNO    CHAR(6)                         NOT NULL,
       ACTNO     SMALLINT                        NOT NULL,
       EMPTIME   DECIMAL(5,2)                            ,
       EMSTDATE  DATE                                    ,
       EMENDATE  DATE                                    ,
       FOREIGN KEY REPAPA (PROJNO, ACTNO, EMSTDATE)
                 REFERENCES PROJACT
                                       ON DELETE RESTRICT,
       FOREIGN KEY REPAE (EMPNO) REFERENCES EMP
                                       ON DELETE RESTRICT);

CREATE TABLE DEMO_UNICODE                           
            (LOWER_A_TO_Z      CHAR(26)                     ,
             UPPER_A_TO_Z      CHAR(26)                     ,
             ZERO_TO_NINE      CHAR(10)                     ,
             X00_TO_XFF    VARCHAR(256)        FOR BIT DATA) ;



