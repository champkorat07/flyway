ALTER TABLE PROJ
      FOREIGN KEY RPP (MAJPROJ) REFERENCES PROJ
              ON DELETE CASCADE;

ALTER TABLE DEPT
      FOREIGN KEY RDD (ADMRDEPT) REFERENCES DEPT
              ON DELETE CASCADE;
 
ALTER TABLE DEPT
      FOREIGN KEY RDE (MGRNO) REFERENCES EMP
              ON DELETE SET NULL;