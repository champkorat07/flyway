
INSERT INTO PERSON (
   ID
  ,NAME
)(
   Select MAX(ID)+1 -- ID - INTEGER
  ,'Mr.Max' -- NAME - VARCHAR(100)
  From PERSON
);