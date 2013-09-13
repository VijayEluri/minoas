***	Get all Employees along with their EmployeeInfo and their current RankInfo ***
select distinct e.*, ei.*, ri.* from minoas.slavikos.EMPLOYEE e, minoas.slavikos.EMPLOYEE_INFO ei, minoas.slavikos.RANK_INFO ri, minoas.slavikos.EMPLOYMENT em
where ri.EMPLOYEE_INFO_ID = ei.ID and ei.EMPLOYEE_ID = e.ID
and em.EMPLOYEE_ID = e.ID
and ei.CURRENT_RANK_INFO_ID = ri.ID
and em.IS_ACTIVE = 1
and e.ID = 9360			// ������������


select * from minoas.slavikos.EMPLOYEE where ID = 9360
select * from minoas.slavikos.EMPLOYEE_INFO where EMPLOYEE_ID = 9360
select * from minoas.slavikos.RANK_INFO where EMPLOYEE_INFO_ID = 11773

select * from minoas.slavikos.EMPLOYEE where ID = 8850
select * from minoas.slavikos.EMPLOYEE_INFO where ID = 11670
select * from minoas.slavikos.RANK_INFO where EMPLOYEE_INFO_ID = 11670


delete minoas.slavikos.RANK_INFO where ID in (
13364, 13365, 13366, 13367, 13368, 13369, 13370, 13371, 13372, 13373, 13374, 13375, 13376, 13377, 13378, 13379, 13380, 13381, 13382, 13383, 13384, 13385, 13386, 13387, 13388, 13389, 13390, 13391, 13392, 13393, 13394, 13395, 13396, 13397, 13398, 13399, 13400, 13401, 13402, 13403, 13404, 13405, 13406, 13407, 13408, 13409, 13410, 13411, 13412, 13413, 13414, 13415, 13416, 13417, 13418, 13419, 13420, 13421, 13422, 13423, 13424, 13425, 13426, 13427, 13428, 13429, 13430, 13431, 13432, 13433, 13434, 13435, 13436, 13437, 13438, 13439, 13440, 13441, 13442, 13443, 13444, 13445, 13446, 13447)


***	Get all Employees along with their EmployeeInfo and ALL their RankInfos ***
select distinct e.*, ei.*, ri.* from minoas.slavikos.EMPLOYEE e, minoas.slavikos.EMPLOYEE_INFO ei, minoas.slavikos.RANK_INFO ri
where ri.EMPLOYEE_INFO_ID = ei.ID and ei.EMPLOYEE_ID = e.ID
and ei.ID = ri.EMPLOYEE_INFO_ID
and e.IS_ACTIVE = 1



***	Get all current RankInfo for ACTIVE Employees ***
select ri.* 
from minoas.slavikos.EMPLOYEE e, minoas.slavikos.EMPLOYEE_INFO ei, minoas.slavikos.RANK_INFO ri, minoas.slavikos.EMPLOYMENT em
where ri.EMPLOYEE_INFO_ID = ei.ID and ei.EMPLOYEE_ID = e.ID
and em.EMPLOYEE_ID = e.ID
and ei.CURRENT_RANK_INFO_ID = ri.ID
and em.IS_ACTIVE = 1



select distinct e.*, ei.*, ri.* from minoas.slavikos.EMPLOYEE e, minoas.slavikos.EMPLOYEE_INFO ei, minoas.slavikos.RANK_INFO ri
where ri.EMPLOYEE_INFO_ID = ei.ID and ei.EMPLOYEE_ID = e.ID
and ei.ID = ri.EMPLOYEE_INFO_ID
and e.IS_ACTIVE = 1
and e.ID = 9360








select * from minoas.slavikos.EMPLOYEE
where IS_ACTIVE=1 and EMPLOYEE_TYPE = 'REGULAR'

and LAST_NAME= '�����������'
select * from minoas.slavikos.EMPLOYEE where ID = 10746





select e.* from minoas.slavikos.EMPLOYEE e, minoas.slavikos.EMPLOYMENT em
where em.IS_ACTIVE=1 and em.EMPLOYMENT_TYPE = 'REGULAR'
and em.EMPLOYEE_ID = e.ID





and e.ID not in (
select e.ID from minoas.slavikos.EMPLOYEE e, minoas.slavikos.EMPLOYMENT em
where em.IS_ACTIVE=1 and em.EMPLOYMENT_TYPE = 'REGULAR'
and em.EMPLOYEE_ID = e.ID
)





select * from minoas.slavikos.REGULAR_EMPLOYEE_INFO
where EMPLOYEE_ID = 8879
select * from minoas.slavikos.EMPLOYEE_INFO


select * from minoas.slavikos.EMPLOYMENT where ID in (
select CURRENT_EMPLOYMENT_ID from minoas.slavikos.EMPLOYEE
where IS_ACTIVE=1 and EMPLOYEE_TYPE = 'REGULAR' and CURRENT_EMPLOYMENT_ID is not null)


select * from minoas.slavikos.EMPLOYEE_INFO where EMPLOYEE_ID in (
select ID from minoas.slavikos.EMPLOYEE
where IS_ACTIVE=1 and EMPLOYEE_TYPE = 'REGULAR' and CURRENT_EMPLOYMENT_ID is not null)




select EMPLOYEE_ID from minoas.slavikos.EMPLOYEE_INFO where EMPLOYEE_ID in (
select ID from minoas.slavikos.EMPLOYEE
where IS_ACTIVE=1 and EMPLOYEE_TYPE = 'REGULAR' and CURRENT_EMPLOYMENT_ID is not null)







update minoas.slavikos.REGULAR_EMPLOYEE_INFO
set version = 0 where version is NULL


delete minoas.slavikos.REGULAR_EMPLOYEE_INFO where ID = 9847
update minoas.slavikos.EMPLOYEE set REGULAR_EMPLOYMEE_INFO_ID = 8879 where ID = 8879

update minoas.slavikos.REGULAR_EMPLOYEE_INFO
set GOG_APPOINTMENT_NO = '251' where ID = 8879

select TOP 10 EMPLOYEE_ID, PERMANENT_EMPLOYEE_ACT, PERMANENT_EMPLOYEE_ACT_DATE, IS_RECENTLY_HIRED from minoas.slavikos.EMPLOYEE_INFO
where (PERMANENT_EMPLOYEE_ACT is not NULL  AND PERMANENT_EMPLOYEE_ACT_DATE is not null) or IS_RECENTLY_HIRED <> 0


select * from minoas.slavikos.REGULAR_EMPLOYEE_INFO
where ID in (select EMPLOYEE_ID from minoas.slavikos.EMPLOYEE_INFO
where (PERMANENT_EMPLOYEE_ACT is not NULL  AND PERMANENT_EMPLOYEE_ACT_DATE is not null) or IS_RECENTLY_HIRED <> 0)


*
**************************************************************************************

** Move Data 
	- from columns PERMANENT_EMPLOYEE_ACT, PERMANENT_EMPLOYEE_ACT_DATE & IS_RECENTLY_HIRED from table EMPLOYEE_INFO 
	  to table in REGULAR_EMPLOYEE_INFO.
	- from columns APPOINTMENT_GOG & APPOINTMENT_GOG_DATE from table EMPLOYEE_INFO 
	  to table REGULAR_EMPLOYEE_INFO

use minoas

DECLARE @EmployeeInfoCursor CURSOR; 
DECLARE @EMPLOYEE_ID int;
DECLARE @REG_EMPLOYEE_ID int;
DECLARE @PERMANENT_EMPLOYEE_ACT nvarchar(255);
DECLARE @PERMANENT_EMPLOYEE_ACT_DATE datetime;
DECLARE @GOG_APPOINTMENT_NO nvarchar(255);
DECLARE @GOG_APPOINTMENT_DATE datetime;
DECLARE @IS_RECENTLY_HIRED bit;

SET @EmployeeInfoCursor = CURSOR FOR select EMPLOYEE_ID, PERMANENT_EMPLOYEE_ACT, PERMANENT_EMPLOYEE_ACT_DATE, IS_RECENTLY_HIRED, GOG_APPOINTMENT_NO, GOG_APPOINTMENT_DATE from minoas.slavikos.EMPLOYEE_INFO

OPEN @EmployeeInfoCursor 
FETCH NEXT FROM @EmployeeInfoCursor INTO @EMPLOYEE_ID, @PERMANENT_EMPLOYEE_ACT, @PERMANENT_EMPLOYEE_ACT_DATE, @IS_RECENTLY_HIRED, @GOG_APPOINTMENT_NO, @GOG_APPOINTMENT_DATE
WHILE @@FETCH_STATUS = 0 
BEGIN 
	IF(@EMPLOYEE_ID IS NOT NULL)
	BEGIN
		IF(@PERMANENT_EMPLOYEE_ACT IS NOT NULL or @PERMANENT_EMPLOYEE_ACT_DATE is not NULL)
		BEGIN 
			select @REG_EMPLOYEE_ID = EMPLOYEE_ID from minoas.slavikos.REGULAR_EMPLOYEE_INFO where EMPLOYEE_ID = @EMPLOYEE_ID
			IF(@REG_EMPLOYEE_ID IS NOT NULL)
				BEGIN
					update minoas.slavikos.REGULAR_EMPLOYEE_INFO 
					set PERMANENT_EMPLOYEE_ACT = @PERMANENT_EMPLOYEE_ACT, PERMANENT_EMPLOYEE_ACT_DATE = @PERMANENT_EMPLOYEE_ACT_DATE
					where EMPLOYEE_ID = @REG_EMPLOYEE_ID
				END 
			ELSE
				BEGIN
					INSERT INTO slavikos.REGULAR_EMPLOYEE_INFO(INSERTED_ON, VERSION, 
						EMPLOYEE_ID, PERMANENT_EMPLOYEE_ACT, PERMANENT_EMPLOYEE_ACT_DATE)
					VALUES(CURRENT_TIMESTAMP, 0,
						@REG_EMPLOYEE_ID, @PERMANENT_EMPLOYEE_ACT, @PERMANENT_EMPLOYEE_ACT_DATE)
				END

		END

		IF(@IS_RECENTLY_HIRED IS NOT NULL AND @IS_RECENTLY_HIRED = 1)
		BEGIN 
			select @REG_EMPLOYEE_ID = EMPLOYEE_ID from minoas.slavikos.REGULAR_EMPLOYEE_INFO where EMPLOYEE_ID = @EMPLOYEE_ID
			IF(@REG_EMPLOYEE_ID IS NOT NULL)
				BEGIN
					update minoas.slavikos.REGULAR_EMPLOYEE_INFO 
					set IS_RECENTLY_HIRED = @IS_RECENTLY_HIRED
					where EMPLOYEE_ID = @REG_EMPLOYEE_ID
				END 
			ELSE
				BEGIN
					INSERT INTO slavikos.REGULAR_EMPLOYEE_INFO(INSERTED_ON, VERSION, 
						EMPLOYEE_ID, IS_RECENTLY_HIRED)
					VALUES(CURRENT_TIMESTAMP, 0,
						@REG_EMPLOYEE_ID, @IS_RECENTLY_HIRED)
				END
		END

		IF(@GOG_APPOINTMENT_NO IS NOT NULL or @GOG_APPOINTMENT_DATE is not NULL)
		BEGIN 
			select @REG_EMPLOYEE_ID = EMPLOYEE_ID from minoas.slavikos.REGULAR_EMPLOYEE_INFO where EMPLOYEE_ID = @EMPLOYEE_ID
			IF(@REG_EMPLOYEE_ID IS NOT NULL)
				BEGIN
					update minoas.slavikos.REGULAR_EMPLOYEE_INFO 
					set GOG_APPOINTMENT_NO = @GOG_APPOINTMENT_NO, GOG_APPOINTMENT_DATE = @GOG_APPOINTMENT_DATE
					where EMPLOYEE_ID = @REG_EMPLOYEE_ID
				END 
			ELSE
				BEGIN
					INSERT INTO slavikos.REGULAR_EMPLOYEE_INFO(INSERTED_ON, VERSION, 
						EMPLOYEE_ID, GOG_APPOINTMENT_NO, GOG_APPOINTMENT_DATE)
					VALUES(CURRENT_TIMESTAMP, 0,
						@REG_EMPLOYEE_ID, @GOG_APPOINTMENT_NO, @GOG_APPOINTMENT_DATE)
				END
		END
	END
	FETCH NEXT FROM @EmployeeInfoCursor INTO @EMPLOYEE_ID, @PERMANENT_EMPLOYEE_ACT, @PERMANENT_EMPLOYEE_ACT_DATE, @IS_RECENTLY_HIRED, @GOG_APPOINTMENT_NO, @GOG_APPOINTMENT_DATE
END

// Check Query
select e.EMPLOYEE_ID, e.PERMANENT_EMPLOYEE_ACT, e.PERMANENT_EMPLOYEE_ACT_DATE, e.IS_RECENTLY_HIRED, e.GOG_APPOINTMENT_NO, e.GOG_APPOINTMENT_DATE, r.PERMANENT_EMPLOYEE_ACT, r.PERMANENT_EMPLOYEE_ACT_DATE, r.IS_RECENTLY_HIRED, r.GOG_APPOINTMENT_NO, r.GOG_APPOINTMENT_DATE
from minoas.slavikos.EMPLOYEE_INFO e, minoas.slavikos.REGULAR_EMPLOYEE_INFO r
where e.EMPLOYEE_ID = r.EMPLOYEE_ID

*
**************************************************************************************
*


** Transfer the data from ENTRY_INTO_SERVICE_ACT & ENTRY_INTO_SERVICE_DATE from table EMPLOYEE_INFO to table EMPLOYMENT

DECLARE @EmployeeCursor CURSOR; 
DECLARE @EMPLOYEE_ID int;
DECLARE @ENTRY_INTO_SERVICE_ACT nvarchar(255);
DECLARE @ENTRY_INTO_SERVICE_DATE datetime;

SET @EmployeeCursor = CURSOR FOR select EMPLOYEE_ID from minoas.slavikos.EMPLOYEE_INFO where EMPLOYEE_ID in (
					select ID from minoas.slavikos.EMPLOYEE
					where IS_ACTIVE=1 and EMPLOYEE_TYPE = 'REGULAR' and CURRENT_EMPLOYMENT_ID is not null)

OPEN @EmployeeCursor 
FETCH NEXT FROM @EmployeeCursor INTO @EMPLOYEE_ID
WHILE @@FETCH_STATUS = 0 
BEGIN 

	
	IF(@EMPLOYEE_ID IS NOT NULL)
	BEGIN
		select @ENTRY_INTO_SERVICE_ACT = ENTRY_INTO_SERVICE_ACT, @ENTRY_INTO_SERVICE_DATE = ENTRY_INTO_SERVICE_DATE from minoas.slavikos.EMPLOYEE_INFO where EMPLOYEE_ID = @EMPLOYEE_ID

		update minoas.slavikos.EMPLOYMENT 
		set ENTRY_INTO_SERVICE_ACT = @ENTRY_INTO_SERVICE_ACT, ENTRY_INTO_SERVICE_DATE = @ENTRY_INTO_SERVICE_DATE
		where ID in (
			select CURRENT_EMPLOYMENT_ID from minoas.slavikos.EMPLOYEE
			where ID = @EMPLOYEE_ID
		)
	
	END
	FETCH NEXT FROM @EmployeeCursor INTO @EMPLOYEE_ID
END


select * from minoas.slavikos.EMPLOYMENT 
where ID in (
	select CURRENT_EMPLOYMENT_ID from minoas.slavikos.EMPLOYEE
	where IS_ACTIVE=1 and EMPLOYEE_TYPE = 'REGULAR' and CURRENT_EMPLOYMENT_ID is not null
)