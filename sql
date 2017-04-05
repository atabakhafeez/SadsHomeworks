
UPDATE QNEW 
   SET QNEW.VAL = M.VAL 
   FROM QNEW  INNER JOIN  M ON QNEW.ROW = M.ROW AND QNEW.COL = M.COl;


UPDATE QNEW SET val = b.val FROM (SELECT row, col, val AS v FROM QNEW) a INNER JOIN M b on a.row = b.row AND a.col = b.col
