SELECT * FROM SERVICE_ALLOCATION

SELECT * FROM SECONDMENT

SELECT COUNT(*) FROM TEACHING_REQUIREMENT

SELECT * FROM TEACHING_REQUIREMENT
    WHERE SCHOOL_YEAR_ID=9

DELETE TEACHING_REQUIREMENT
     WHERE SCHOOL_YEAR_ID=9

SELECT COUNT(*) FROM OutstandingImprovement i
    WHERE i.EMPLOYEE_ID IS NOT NULL

SELECT COUNT(*) FROM PERMANENT_TRANSFER p
    WHERE p.EMPLOYEE_ID IS NOT NULL

SELECT * FROM EMPLOYMENT;

SELECT COUNT(*) FROM EMPLOYMENT em 
    INNER JOIN EMPLOYEE e ON e.ID=em.EMPLOYEE_ID
WHERE 

SELECT COUNT(*) FROM EMPLOYMENT em 
    WHERE em.SCHOOL_YEAR_ID=1 
    AND em.EMPLOYMENT_TYPE='REGULAR' 
    AND em.IS_ACTIVE = 0
    AND em.TERMINATED_DATE IS NOT NULL
    AND em.EMPLOYEE_ID IN (SELECT e2.EMPLOYEE_ID FROM EMPLOYMENT e2 WHERE e2.SCHOOL_YEAR_ID=9)



SELECT COUNT(*) FROM EMPLOYMENT em 
    WHERE em.EMPLOYEE_ID IN (SELECT DISTINCT e2.EMPLOYEE_ID FROM EMPLOYMENT e2 WHERE e2.SCHOOL_YEAR_ID=9)
    
SELECT COUNT(*) FROM EMPLOYMENT e2 
    WHERE e2.SCHOOL_YEAR_ID=1
    AND e2.IS_ACTIVE=0
    AND e2.EMPLOYMENT_TYPE='REGULAR'
    AND e2.TERMINATED_DATE='2010-08-31'

SELECT COUNT(*) FROM EMPLOYMENT e2 
    WHERE e2.SCHOOL_YEAR_ID=9
    AND e2.EMPLOYMENT_TYPE='REGULAR'


UPDATE PERMANENT_TRANSFER
    SET IS_PROCESSED = 0

UPDATE OutstandingImprovement
    SET IS_PROCESSED = 0


UPDATE EMPLOYMENT 
    SET IS_ACTIVE=1
WHERE SCHOOL_YEAR_ID=1
    AND EMPLOYMENT_TYPE='REGULAR'
    AND TERMINATED_DATE='2010-08-31'


SELECT * FROM EMPLOYEE e
    INNER JOIN EMPLOYMENT e2  ON e2.EMPLOYEE_ID=e.ID
WHERE e2.SCHOOL_YEAR_ID=1
    AND e2.EMPLOYMENT_TYPE='REGULAR'
    AND e2.TERMINATED_DATE='2010-08-31'
    

SELECT e.CURRENT_EMPLOYMENT_ID, * FROM OutstandingImprovement i
    INNER JOIN EMPLOYEE e ON i.EMPLOYEE_ID= e.ID

SELECT em.ID,em.EMPLOYEE_ID AS EMPLOYMENT_ID FROM EMPLOYMENT em
    INNER JOIN OutstandingImprovement i ON i.EMPLOYEE_ID=em.EMPLOYEE_ID



SELECT em.ID,em.EMPLOYEE_ID AS EMPLOYMENT_ID FROM EMPLOYMENT em
    INNER JOIN PERMANENT_TRANSFER t ON t.EMPLOYEE_ID=em.EMPLOYEE_ID
WHERE t.TRANSFER_TYPE='WITHIN_PYSDE'


SELECT COUNT(*) FROM OutstandingImprovement



BEGIN TRAN
UPDATE EMPLOYEE 
    SET CURRENT_EMPLOYMENT_ID=null
    WHERE ID IN (
SELECT e.ID FROM EMPLOYEE e
    INNER JOIN EMPLOYMENT e2  ON e2.EMPLOYEE_ID=e.ID
WHERE e2.SCHOOL_YEAR_ID=1
    AND e2.EMPLOYMENT_TYPE='REGULAR'
    AND e2.TERMINATED_DATE='2010-08-31'
)

UPDATE EMPLOYMENT
    SET SUPERSEDED_BY_ID = NULL, IS_ACTIVE=1
    WHERE SUPERSEDED_BY_ID IS NOT NULL
    AND TERMINATED_DATE='2010-08-31'


DELETE EMPLOYMENT WHERE SCHOOL_YEAR_ID=9
COMMIT TRAN

ROLLBACK TRAN




BEGIN TRAN
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=6563  WHERE ID= 6251             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=6836  WHERE ID= 6889             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=6896  WHERE ID= 7583             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=6902  WHERE ID= 7007             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=6923  WHERE ID= 9240             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=6983  WHERE ID= 7805             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=7075  WHERE ID= 6837             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=7095  WHERE ID= 7263             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=7098  WHERE ID= 8647             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=7128  WHERE ID= 7897             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=7134  WHERE ID= 9239             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=7137  WHERE ID= 8295             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=7143  WHERE ID= 8585             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=7251  WHERE ID= 9359             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=7289  WHERE ID= 8014             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=7394  WHERE ID= 7819             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=7458  WHERE ID= 6342             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=7465  WHERE ID= 7201             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=7524  WHERE ID= 8932             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=7549  WHERE ID= 7682             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=7682  WHERE ID= 7590             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=7695  WHERE ID= 9015             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=7718  WHERE ID= 6684             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=7788  WHERE ID= 9288             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=7860  WHERE ID= 9202             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=7885  WHERE ID= 8270             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=8162  WHERE ID= 7014             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=8246  WHERE ID= 7049             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=8282  WHERE ID= 7444             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=8500  WHERE ID= 8212             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=8517  WHERE ID= 6706             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=8593  WHERE ID= 8431             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=8615  WHERE ID= 8676             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=8621  WHERE ID= 9356             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=8748  WHERE ID= 7437             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=8767  WHERE ID= 7421             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=8822  WHERE ID= 6552             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=8854  WHERE ID= 7498             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=8889  WHERE ID= 8796             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=8897  WHERE ID= 6311             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=8904  WHERE ID= 6963             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=8918  WHERE ID= 6374             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=8976  WHERE ID= 7931             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=8998  WHERE ID= 9096             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9030  WHERE ID= 9299             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9063  WHERE ID= 8564             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9064  WHERE ID= 8565             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9116  WHERE ID= 9346             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9136  WHERE ID= 6675             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9239  WHERE ID= 9180             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9244  WHERE ID= 6456             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9256  WHERE ID= 9084             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9286  WHERE ID= 6992             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9299  WHERE ID= 7042             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9311  WHERE ID= 7145             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9317  WHERE ID= 7807             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9321  WHERE ID= 7169             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9553  WHERE ID= 8901             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9663  WHERE ID= 7842             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9675  WHERE ID= 9400             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9680  WHERE ID= 9408             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9681  WHERE ID= 9409             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9682  WHERE ID= 9411             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9683  WHERE ID= 9413             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9684  WHERE ID= 9415             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9685  WHERE ID= 9417             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9686  WHERE ID= 9418             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9687  WHERE ID= 9420             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9689  WHERE ID= 9423             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9691  WHERE ID= 9426             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9696  WHERE ID= 9431             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9698  WHERE ID= 9433             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9699  WHERE ID= 9434             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9703  WHERE ID= 9438             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9705  WHERE ID= 9440             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9708  WHERE ID= 9443             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9711  WHERE ID= 9447             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9725  WHERE ID= 9462             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9735  WHERE ID= 9472             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9737  WHERE ID= 9474             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9738  WHERE ID= 9475             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9740  WHERE ID= 9477             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9741  WHERE ID= 9478             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9743  WHERE ID= 9480             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9751  WHERE ID= 9489             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9754  WHERE ID= 9492             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9755  WHERE ID= 9493             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9756  WHERE ID= 9494             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9782  WHERE ID= 9528             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9783  WHERE ID= 9529             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9784  WHERE ID= 9530             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9785  WHERE ID= 9531             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9786  WHERE ID= 9532             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9787  WHERE ID= 9533             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9788  WHERE ID= 9534             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9789  WHERE ID= 9535             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9790  WHERE ID= 9536             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9791  WHERE ID= 9537             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9792  WHERE ID= 9538             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9793  WHERE ID= 9539             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9794  WHERE ID= 9540             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9795  WHERE ID= 9541             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9796  WHERE ID= 9542             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9797  WHERE ID= 9544             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9799  WHERE ID= 9546             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9800  WHERE ID= 9547             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9801  WHERE ID= 9548             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9856  WHERE ID= 9612             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9857   WHERE ID=9613             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=9858   WHERE ID=9627             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=10042  WHERE ID=9802             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=10073  WHERE ID=9837             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=10074  WHERE ID=9838             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=10075  WHERE ID=9839             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=10076  WHERE ID=9840             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=10077  WHERE ID=9841             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=10078  WHERE ID=9842             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=10079  WHERE ID=9843             
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=10293  WHERE ID=10006            
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=10294  WHERE ID=10007            
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=10295  WHERE ID=10008            
UPDATE EMPLOYEE  SET CURRENT_EMPLOYMENT_ID=10342  WHERE ID=10030 

COMMIT TRAN


UPDATE EMPLOYEE SET CURRENT_EMPLOYMENT_ID=6996   WHERE ID=9283             
UPDATE EMPLOYEE SET CURRENT_EMPLOYMENT_ID= 7029  WHERE ID= 9007             
UPDATE EMPLOYEE SET CURRENT_EMPLOYMENT_ID= 7136 WHERE ID=  7350             
 UPDATE EMPLOYEE SET CURRENT_EMPLOYMENT_ID=7378  WHERE ID= 6721             
 UPDATE EMPLOYEE SET CURRENT_EMPLOYMENT_ID=7641  WHERE ID= 7147             
 UPDATE EMPLOYEE SET CURRENT_EMPLOYMENT_ID=7686  WHERE ID= 9277             
UPDATE EMPLOYEE SET CURRENT_EMPLOYMENT_ID= 7741  WHERE ID= 9276             
 UPDATE EMPLOYEE SET CURRENT_EMPLOYMENT_ID=7763  WHERE ID= 7806             
UPDATE EMPLOYEE SET CURRENT_EMPLOYMENT_ID= 7821  WHERE ID= 7737             
 UPDATE EMPLOYEE SET CURRENT_EMPLOYMENT_ID=7880  WHERE ID= 9274             
UPDATE EMPLOYEE SET CURRENT_EMPLOYMENT_ID= 8193  WHERE ID= 6715             
 UPDATE EMPLOYEE SET CURRENT_EMPLOYMENT_ID=8216  WHERE ID= 6256             
 UPDATE EMPLOYEE SET CURRENT_EMPLOYMENT_ID=8980  WHERE ID= 6371             
 UPDATE EMPLOYEE SET CURRENT_EMPLOYMENT_ID=9006  WHERE ID= 6875             
 UPDATE EMPLOYEE SET CURRENT_EMPLOYMENT_ID=9193  WHERE ID= 6883             
 UPDATE EMPLOYEE SET CURRENT_EMPLOYMENT_ID=9672  WHERE ID= 9397